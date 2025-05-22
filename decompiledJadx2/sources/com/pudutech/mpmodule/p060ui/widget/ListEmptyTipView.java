package com.pudutech.mpmodule.p060ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import com.pudutech.mpmodule.C5441R;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class ListEmptyTipView extends LinearLayout {
    public ListEmptyTipView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(C5441R.layout.module_list_empty_tip_layout, this);
    }

    public ListEmptyTipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }
}
