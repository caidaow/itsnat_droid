package org.itsnat.droid.impl.dom.layout;

/**
 * Created by jmarranz on 19/01/2016.
 */
public class XMLDOMLayoutPageFragment extends XMLDOMLayoutPage
{
    public XMLDOMLayoutPageFragment()
    {
    }

    @Override
    protected XMLDOMLayout createXMLDOMLayout()
    {
        return new XMLDOMLayoutPageFragment();
    }
}
