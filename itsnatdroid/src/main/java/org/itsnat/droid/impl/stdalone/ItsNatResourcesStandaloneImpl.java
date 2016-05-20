package org.itsnat.droid.impl.stdalone;

import android.animation.Animator;
import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LayoutAnimationController;

import org.itsnat.droid.ItsNatDroidException;
import org.itsnat.droid.impl.ItsNatResourcesImpl;
import org.itsnat.droid.impl.dom.ResourceDesc;
import org.itsnat.droid.impl.dom.ResourceDescLocal;
import org.itsnat.droid.impl.dom.ResourceDescRemote;
import org.itsnat.droid.impl.domparser.XMLDOMParserContext;
import org.itsnat.droid.impl.domparser.animator.XMLDOMAnimatorParser;
import org.itsnat.droid.impl.xmlinflater.layout.stdalone.XMLInflaterLayoutStandalone;

/**
 * Created by jmarranz on 12/04/2016.
 */
public class ItsNatResourcesStandaloneImpl extends ItsNatResourcesImpl
{
    protected final XMLInflaterLayoutStandalone xmlInflaterLayoutStandalone;
    protected final XMLDOMParserContext xmlDOMParserContext;

    public ItsNatResourcesStandaloneImpl(XMLInflaterLayoutStandalone xmlInflaterLayoutStandalone)
    {
        super(xmlInflaterLayoutStandalone.getInflatedLayoutStandaloneImpl().getItsNatDroidImpl().getXMLDOMRegistry(),
              xmlInflaterLayoutStandalone.getXMLInflaterContext(),
              xmlInflaterLayoutStandalone.getInflatedLayoutStandaloneImpl().getItsNatDroidImpl().getXMLInflaterRegistry());

        // En este caso PageImpl es null

        this.xmlInflaterLayoutStandalone = xmlInflaterLayoutStandalone;

        Context ctx = xmlInflaterLayoutStandalone.getContext();
        this.xmlDOMParserContext = new XMLDOMParserContext(xmlDOMRegistry,ctx);
    }

    public Context getContext()
    {
        return xmlInflaterLayoutStandalone.getContext();
    }

    private ResourceDesc loadAndCacheResourceDesc(String resourceDescValue)
    {
        ResourceDesc resourceDesc = ResourceDesc.create(resourceDescValue);

        if (resourceDesc instanceof ResourceDescLocal)
        {
            ResourceDescLocal resourceDescLocal = (ResourceDescLocal) resourceDesc;
            XMLDOMAnimatorParser.prepareResourceDescLocalToLoadResource(resourceDescLocal, xmlDOMParserContext);
            return (ResourceDescLocal)resourceDesc;
        }
        else if (resourceDesc instanceof ResourceDescRemote)
        {
            throw new ItsNatDroidException("Remote resource is not allowed in this context, use assets instead");
        }

        return resourceDesc; // Puede ser "compiled"
    }

    @Override
    public Animator getAnimator(String resourceDescValue)
    {
        ResourceDesc resourceDesc = xmlDOMRegistry.getAnimatorResourceDescDynamicCacheByResourceDescValue(resourceDescValue);
        if (resourceDesc == null)
            resourceDesc = loadAndCacheResourceDesc(resourceDescValue);
        return xmlInflaterRegistry.getAnimator(resourceDesc,xmlInflaterContext);
    }

    @Override
    public Animation getAnimation(String resourceDescValue)
    {
        ResourceDesc resourceDesc = xmlDOMRegistry.getAnimationResourceDescDynamicCacheByResourceDescValue(resourceDescValue);
        if (resourceDesc == null)
            resourceDesc = loadAndCacheResourceDesc(resourceDescValue);
        return xmlInflaterRegistry.getAnimation(resourceDesc,xmlInflaterContext);
    }

    @Override
    public LayoutAnimationController getLayoutAnimation(String resourceDescValue)
    {
        ResourceDesc resourceDesc = xmlDOMRegistry.getLayoutAnimationResourceDescDynamicCacheByResourceDescValue(resourceDescValue);
        if (resourceDesc == null)
            resourceDesc = loadAndCacheResourceDesc(resourceDescValue);
        return xmlInflaterRegistry.getLayoutAnimation(resourceDesc,xmlInflaterContext);
    }

    @Override
    public Interpolator getInterpolator(String resourceDescValue)
    {
        ResourceDesc resourceDesc = xmlDOMRegistry.getInterpolatorResourceDescDynamicCacheByResourceDescValue(resourceDescValue);
        if (resourceDesc == null)
            resourceDesc = loadAndCacheResourceDesc(resourceDescValue);
        return xmlInflaterRegistry.getInterpolator(resourceDesc,xmlInflaterContext);
    }

    @Override
    public CharSequence[] getTextArray(String resourceDescValue)
    {
        ResourceDesc resourceDesc = xmlDOMRegistry.geValuesResourceDescDynamicCacheByResourceDescValue(resourceDescValue);
        if (resourceDesc == null)
            resourceDesc = loadAndCacheResourceDesc(resourceDescValue);
        return xmlInflaterRegistry.getTextArray(resourceDesc,xmlInflaterContext);
    }
}
