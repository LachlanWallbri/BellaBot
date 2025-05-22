package com.pudu.library.loracall.base;

import android.content.Context;
import com.pudutech.base.Pdlog;
import java.lang.reflect.Method;

/* loaded from: classes4.dex */
public class NavigationBar {
    public static final int DISABLE_ALL_MASK = 67043328;
    public static final int DISABLE_BACK = 4194304;
    public static final int DISABLE_CLOCK = 8388608;
    public static final int DISABLE_EXPAND = 65536;
    public static final int DISABLE_HOME = 2097152;
    public static final int DISABLE_NONE = 0;
    public static final int DISABLE_NOTIFICATION_ALERTS = 262144;
    public static final int DISABLE_NOTIFICATION_ICONS = 131072;
    public static final int DISABLE_NOTIFICATION_TICKER = 524288;
    public static final int DISABLE_RECENT = 16777216;
    public static final int DISABLE_SEARCH = 33554432;
    public static final int DISABLE_SYSTEM_INFO = 1048576;
    public static final int ONLY_BACK_MASK = 62849024;

    public static void statusBarDisable(int i, Context context) {
        try {
            disableStatusBar(Class.forName("android.app.StatusBarManager").getMethod("disable", Integer.TYPE), context.getSystemService("statusbar"), i, context);
        } catch (Exception e) {
            Pdlog.m3274e("StatusUtils", e.toString(), e);
        }
    }

    private static void disableStatusBar(Method method, Object obj, int i, Context context) {
        try {
            method.invoke(obj, Integer.valueOf(i));
        } catch (Exception e) {
            Pdlog.m3274e("status", e.toString(), e);
        }
    }
}
