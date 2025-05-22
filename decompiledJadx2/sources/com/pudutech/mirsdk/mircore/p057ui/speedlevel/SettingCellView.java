package com.pudutech.mirsdk.mircore.p057ui.speedlevel;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.pudutech.mirsdk.mircore.C5224R;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes6.dex */
public class SettingCellView extends LinearLayoutCompat {
    TextView textView;

    public SettingCellView(Context context) {
        this(context, null);
    }

    public SettingCellView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SettingCellView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i);
    }

    private void init(Context context, AttributeSet attributeSet, int i) {
        inflate(context, C5224R.layout.merge_setting_cell, this);
        this.textView = (TextView) findViewById(C5224R.id.title);
        this.textView.setTextColor(-1);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C5224R.styleable.SettingCellView);
        try {
            this.textView.setText(obtainStyledAttributes.getString(C5224R.styleable.SettingCellView_android_title));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }
}
