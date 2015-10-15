package org.itsnat.droid.impl.xmlinflater.drawable;

import org.itsnat.droid.impl.xmlinflater.ClassDescMgr;
import org.itsnat.droid.impl.xmlinflater.XMLInflateRegistry;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescBitmapDrawable;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescClipDrawable;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescColorDrawable;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescDrawable;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescDrawableOrElementDrawableChild;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescElementDrawableChildBridge;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescLayerDrawable;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescLayerDrawableItem;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescNinePatchDrawable;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescStateListDrawable;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescStateListDrawableItem;

/**
 * Created by jmarranz on 30/04/14.
 */
public class ClassDescDrawableOrElementDrawableChildMgr extends ClassDescMgr<ClassDescDrawableOrElementDrawableChild>
{
    public ClassDescDrawableOrElementDrawableChildMgr(XMLInflateRegistry parent)
    {
        super(parent);
        initClassDesc();
    }

    public ClassDescDrawableOrElementDrawableChild get(String className)
    {
        return classes.get(className);
    }

    @Override
    protected void initClassDesc()
    {
        ClassDescElementDrawableChildBridge childBridge = new ClassDescElementDrawableChildBridge(this);
        addClassDesc(childBridge);

        ClassDescBitmapDrawable bitmap = new ClassDescBitmapDrawable(this);
        addClassDesc(bitmap);

        ClassDescNinePatchDrawable ninePatch = new ClassDescNinePatchDrawable(this);
        addClassDesc(ninePatch);

        ClassDescClipDrawable clip = new ClassDescClipDrawable(this);
        addClassDesc(clip);

        ClassDescColorDrawable color = new ClassDescColorDrawable(this);
        addClassDesc(color);

        ClassDescLayerDrawable layer = new ClassDescLayerDrawable(this);
        addClassDesc(layer);

            ClassDescLayerDrawableItem itemLayer = new ClassDescLayerDrawableItem(this);
            addClassDesc(itemLayer);

        ClassDescStateListDrawable stateList = new ClassDescStateListDrawable(this);
        addClassDesc(stateList);

            ClassDescStateListDrawableItem itemStateList = new ClassDescStateListDrawableItem(this);
            addClassDesc(itemStateList);
    }
}