package com.pudutech.peanut.robot_ui.p063ui.view.floatview;

import android.content.Context;
import android.widget.ImageView;
import com.pudutech.peanut.robot_ui.C5508R;

/* loaded from: classes5.dex */
public class EnFloatingView extends FloatingMagnetView {
    private final ImageView mIcon;

    public EnFloatingView(Context context) {
        this(context, C5508R.layout.en_floating_view);
    }

    public EnFloatingView(Context context, int i) {
        super(context, null);
        inflate(context, i, this);
        this.mIcon = (ImageView) findViewById(C5508R.id.icon);
    }

    public void setIconImage(int i) {
        this.mIcon.setImageResource(i);
    }
}
