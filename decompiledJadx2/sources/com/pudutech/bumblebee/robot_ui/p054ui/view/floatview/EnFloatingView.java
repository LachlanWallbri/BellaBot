package com.pudutech.bumblebee.robot_ui.p054ui.view.floatview;

import android.content.Context;
import android.widget.ImageView;
import com.pudutech.bumblebee.robot_ui.C4188R;

/* loaded from: classes4.dex */
public class EnFloatingView extends FloatingMagnetView {
    private final ImageView mIcon;

    public EnFloatingView(Context context) {
        this(context, C4188R.layout.en_floating_view);
    }

    public EnFloatingView(Context context, int i) {
        super(context, null);
        inflate(context, i, this);
        this.mIcon = (ImageView) findViewById(C4188R.id.icon);
    }

    public void setIconImage(int i) {
        this.mIcon.setImageResource(i);
    }
}
