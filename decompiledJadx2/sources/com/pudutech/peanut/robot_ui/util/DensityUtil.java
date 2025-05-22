package com.pudutech.peanut.robot_ui.util;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Window;

/* loaded from: classes5.dex */
public class DensityUtil {
    public float density = Resources.getSystem().getDisplayMetrics().density;

    public static int dp2px(Context context, float f) {
        return (int) TypedValue.applyDimension(1, f, context.getResources().getDisplayMetrics());
    }

    public static int dip2px(Context context, float f) {
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

    public static Integer[] getWidthAndHeight(Window window) {
        if (window == null) {
            return null;
        }
        Integer[] numArr = new Integer[2];
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 19) {
            window.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        }
        numArr[0] = Integer.valueOf(displayMetrics.widthPixels);
        numArr[1] = Integer.valueOf(displayMetrics.heightPixels);
        return numArr;
    }
}
