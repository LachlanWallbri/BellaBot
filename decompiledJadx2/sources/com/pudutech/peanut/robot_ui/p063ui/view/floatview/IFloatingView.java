package com.pudutech.peanut.robot_ui.p063ui.view.floatview;

import android.app.Activity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/* loaded from: classes5.dex */
public interface IFloatingView {
    FloatingView add();

    FloatingView attach(Activity activity);

    FloatingView attach(FrameLayout frameLayout);

    FloatingView customView(int i);

    FloatingView customView(FloatingMagnetView floatingMagnetView);

    FloatingView detach(Activity activity);

    FloatingView detach(FrameLayout frameLayout);

    FloatingMagnetView getView();

    FloatingView icon(int i);

    FloatingView layoutParams(ViewGroup.LayoutParams layoutParams);

    FloatingView listener(MagnetViewListener magnetViewListener);

    FloatingView remove();
}
