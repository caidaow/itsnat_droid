package org.itsnat.droid.impl.dom.layout;

/**
 * Created by jmarranz on 19/01/2016.
 */
public class XMLDOMLayoutStandalone extends XMLDOMLayout
{
    @Override
    protected XMLDOMLayout createXMLDOMLayout()
    {
        return new XMLDOMLayoutStandalone();
    }
}
