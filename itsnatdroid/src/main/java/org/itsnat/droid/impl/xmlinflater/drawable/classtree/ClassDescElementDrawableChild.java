package org.itsnat.droid.impl.xmlinflater.drawable.classtree;

import org.itsnat.droid.impl.dom.drawable.DOMElemDrawable;
import org.itsnat.droid.impl.xmlinflated.drawable.ElementDrawable;
import org.itsnat.droid.impl.xmlinflated.drawable.ElementDrawableChild;
import org.itsnat.droid.impl.xmlinflater.drawable.AttrDrawableContext;
import org.itsnat.droid.impl.xmlinflater.drawable.ClassDescDrawableMgr;

/**
 * Created by jmarranz on 27/11/14.
 */
public abstract class ClassDescElementDrawableChild<TelementDrawable extends ElementDrawableChild> extends ClassDescDrawable<TelementDrawable>
{
    public ClassDescElementDrawableChild(ClassDescDrawableMgr classMgr, String elemName)
    {
        super(classMgr, elemName, null);
    }

    public ClassDescElementDrawableChild(ClassDescDrawableMgr classMgr, String elemName,ClassDescDrawable<? super TelementDrawable> parentClass)
    {
        super(classMgr, elemName, parentClass);
    }


    public abstract ElementDrawableChild createElementDrawableChild(DOMElemDrawable domElement, DOMElemDrawable domElementParent, ElementDrawable parentChildDrawable, AttrDrawableContext attrCtx);
}
