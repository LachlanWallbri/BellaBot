package com.pudutech.mpmodule.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class DensityUtil {
    public static int dp2px(int i) {
        return dp2px(i * 1.0f);
    }

    public static int dp2px(float f) {
        return (int) ((f * Resources.getSystem().getDisplayMetrics().density) + 0.5f);
    }

    public static int sp2px(int i) {
        return sp2px(i * 1.0f);
    }

    public static int sp2px(float f) {
        return (int) TypedValue.applyDimension(2, f, Resources.getSystem().getDisplayMetrics());
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
