package org.itsnat.droid.impl.domparser;

import org.itsnat.droid.ItsNatDroidException;
import org.itsnat.droid.impl.dom.TimestampExtended;
import org.itsnat.droid.impl.dom.TimestampExtendedComparator;
import org.itsnat.droid.impl.dom.XMLDOM;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by jmarranz on 27/10/14.
 */
public class XMLDOMCache<T extends XMLDOM>
{
    public static final int MAX_PAGES = 20;

    protected Map<String,T> registryByMarkup = new HashMap<String, T>();
    protected TreeMap<TimestampExtended,T> registryByTimestamp = new TreeMap<TimestampExtended,T>(new TimestampExtendedComparator()); // Recuerda que es un SortedMap de menor a mayor por defecto.
    protected final boolean allowRepeatedPut; // Los métodos get y put están sincronizados para que haya coherencia siempre en las dos colecciones pero desde fuera permitimos no sincronizar el uso conjunto de get/put por lo que es raro pero posible que dos put se ejecuten al mismo tiempo (uno tras otro por la sincronización) registrando el mismo valor sin provocar error, no pasa nada quedará el último

    /*
    public XMLDOMCache(boolean allowRepeatedPut)
    {
        this.allowRepeatedPut = allowRepeatedPut;
    }
    */
    public XMLDOMCache()
    {
        this.allowRepeatedPut = false;
    }

    public synchronized T get(String markup)
    {
        T xmlDOM = registryByMarkup.get(markup);
        if (xmlDOM == null)
            return null;

        // Actualizamos el timestamp para que quede constancia de que al requerirlo es porque es de uso frecuente y no merece perderse respecto a otros con menos uso
        TimestampExtended timestamp = xmlDOM.getTimestampExtended();
        registryByTimestamp.remove(timestamp);
        timestamp.update();
        generateUniqueTimestamp(timestamp);
        registryByTimestamp.put(timestamp, xmlDOM); // Así hacemos ver que esta página se está usando recientemente y no será candidata a eliminarse

        return xmlDOM;
    }

    private synchronized void remove(String markup)
    {
        T xmlDOM = registryByMarkup.remove(markup);
        if (xmlDOM == null) return;
        registryByTimestamp.remove(xmlDOM.getTimestampExtended());
    }

    public synchronized void put(String markup,T xmlDOM)
    {
        remove(markup);

        if (registryByMarkup.size() >= MAX_PAGES) // El > es por si acaso, un == sería suficiente
        {
            Iterator<Map.Entry<TimestampExtended,T>> it = registryByTimestamp.entrySet().iterator();
            Map.Entry<TimestampExtended,T> oldestItem = it.next();
            it.remove();
            T xmlDOMToRemove = oldestItem.getValue();
            registryByMarkup.remove(xmlDOMToRemove);
        }
        T res;
        res = registryByMarkup.put(markup,xmlDOM);
        if (!allowRepeatedPut && res != null) throw new ItsNatDroidException("Internal Error");
        TimestampExtended timestamp = xmlDOM.getTimestampExtended();
        generateUniqueTimestamp(timestamp);
        res = registryByTimestamp.put(timestamp, xmlDOM);
        if (!allowRepeatedPut && res != null) throw new ItsNatDroidException("Internal Error");
    }

    private synchronized void generateUniqueTimestamp(TimestampExtended timestamp)
    {
        // Es posible cargar varios recursos en el mismo ms por ej en el caso de "values" contenidos en el mismo archivo
        // Evitamos que haya 2 con el nuevo timestamp extendido, de esta forma nos aseguramos la unicidad del registro dado un timestamp/complementary
        while (true)
        {
            T xmlDOMOld = registryByTimestamp.get(timestamp);
            if (xmlDOMOld == null) break;
            timestamp.incComplementary();
        }
    }
}
