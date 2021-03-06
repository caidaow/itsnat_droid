package org.itsnat.droid.impl.xmlinflater.drawable.classtree;

import org.itsnat.droid.impl.dom.drawable.DOMElemDrawable;
import org.itsnat.droid.impl.xmlinflated.drawable.ElementDrawable;
import org.itsnat.droid.impl.xmlinflated.drawable.ElementDrawableChild;
import org.itsnat.droid.impl.xmlinflated.drawable.GradientDrawableItemSolid;
import org.itsnat.droid.impl.xmlinflater.drawable.AttrDrawableContext;
import org.itsnat.droid.impl.xmlinflater.drawable.ClassDescDrawableMgr;
import org.itsnat.droid.impl.xmlinflater.shared.attr.AttrDescReflecMethodColor;

/**
 * Created by jmarranz on 10/11/14.
 */
public class ClassDescGradientDrawableItemSolid extends ClassDescElementDrawableChildNormal<GradientDrawableItemSolid>
{
    public ClassDescGradientDrawableItemSolid(ClassDescDrawableMgr classMgr)
    {
        super(classMgr,"shape:solid");
    }

    @Override
    public Class<GradientDrawableItemSolid> getDrawableOrElementDrawableClass()
    {
        return GradientDrawableItemSolid.class;
    }

    @Override
    public ElementDrawableChild createElementDrawableChild(DOMElemDrawable domElement, DOMElemDrawable domElementParent, ElementDrawable parentChildDrawable, AttrDrawableContext attrCtx)
    {
        return new GradientDrawableItemSolid(parentChildDrawable);
    }

    @SuppressWarnings("unchecked")
    protected void init()
    {
        super.init();

        addAttrDescAN(new AttrDescReflecMethodColor(this, "color", 0));
    }

}
