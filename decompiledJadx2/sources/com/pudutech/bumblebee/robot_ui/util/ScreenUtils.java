package com.pudutech.bumblebee.robot_ui.util;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import com.pudutech.base.Pdlog;

/* loaded from: classes4.dex */
public class ScreenUtils {
    public static int getScreenHeight(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static void setScreenLight(Activity activity, float f, boolean z) {
        Window window = activity.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.screenBrightness = 1.0f;
        } else {
            Pdlog.m3273d("ScreenLight", "当前亮度：" + f);
            attributes.screenBrightness = f;
        }
        window.setAttributes(attributes);
    }

    public static void setScreenLight(Activity activity, float f) {
        setScreenLight(activity, f, false);
    }

    public static void setScreenMaxLight(Activity activity) {
        setScreenLight(activity, 0.0f, true);
    }
}
