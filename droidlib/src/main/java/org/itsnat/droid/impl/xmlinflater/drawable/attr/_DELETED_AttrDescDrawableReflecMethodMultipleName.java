package org.itsnat.droid.impl.xmlinflater.drawable.attr;

import org.itsnat.droid.impl.util.MapSmart;
import org.itsnat.droid.impl.xmlinflater.drawable.classtree.ClassDescDrawable;

/**
 * Created by jmarranz on 1/05/14.
 */
public class _DELETED_AttrDescDrawableReflecMethodMultipleName<TdrawableOrElementDrawable> extends _DELETED_AttrDescDrawableReflecMethodNameBased<Integer, TdrawableOrElementDrawable>
{
    public _DELETED_AttrDescDrawableReflecMethodMultipleName(ClassDescDrawable parent, String name, String methodName, MapSmart<String, Integer> valueMap)
    {
        super(parent,name,methodName,getClassParam(),valueMap);
    }

    public _DELETED_AttrDescDrawableReflecMethodMultipleName(ClassDescDrawable parent, String name, MapSmart<String, Integer> valueMap)
    {
        super(parent, name,getClassParam(),valueMap);
    }

    protected static Class<?> getClassParam()
    {
        return int.class;
    }

    @Override
    protected Integer parseNameBasedValue(String value)
    {
        return parseMultipleName(value, valueMap);
    }
}