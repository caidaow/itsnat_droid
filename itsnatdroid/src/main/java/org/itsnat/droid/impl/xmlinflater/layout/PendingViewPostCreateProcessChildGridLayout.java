package org.itsnat.droid.impl.xmlinflater.layout;

import android.view.View;

import org.itsnat.droid.impl.xmlinflater.layout.attr.GridLayout_columnSpec;
import org.itsnat.droid.impl.xmlinflater.layout.attr.GridLayout_rowSpec;

/**
 * Created by jmarranz on 8/09/14.
 */
public class PendingViewPostCreateProcessChildGridLayout extends PendingViewPostCreateProcess
{
    public GridLayout_columnSpec gridLayout_columnSpec;
    public GridLayout_rowSpec gridLayout_rowSpec;

    public PendingViewPostCreateProcessChildGridLayout(View view)
    {
        super(view);
    }

    public void executePendingLayoutParamsTasks()
    {
        super.executePendingLayoutParamsTasks();

        if (gridLayout_columnSpec != null) gridLayout_columnSpec.setAttributes(view);
        if (gridLayout_rowSpec != null) gridLayout_rowSpec.setAttributes(view);
    }
}
