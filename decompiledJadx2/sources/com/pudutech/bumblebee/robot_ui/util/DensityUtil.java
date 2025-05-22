package com.pudutech.bumblebee.robot_ui.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

/* loaded from: classes4.dex */
public class DensityUtil {
    public float density = Resources.getSystem().getDisplayMetrics().density;

    public static int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static float px2dp(int i) {
        return i / Resources.getSystem().getDisplayMetrics().density;
    }

    public int dip2px(float f) {
        return (int) ((f * this.density) + 0.5f);
    }

    public float px2dip(int i) {
        return i / this.density;
    }
}
