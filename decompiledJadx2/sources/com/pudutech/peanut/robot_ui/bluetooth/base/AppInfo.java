package com.pudutech.peanut.robot_ui.bluetooth.base;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.util.DisplayMetrics;
import com.pudutech.peanut.robot_ui.bluetooth.print.PrintUtil;

/* loaded from: classes5.dex */
public class AppInfo {
    public static int appCode;
    public static String appVersion;
    public static String btAddress;
    public static String btName;
    public static String dType;
    public static String dVersion;
    public static float density;
    public static int densityDpi;
    public static int height;
    public static int width;

    public static void init(Context context) {
        PackageInfo packageInfo;
        dType = Build.MODEL;
        dVersion = Build.VERSION.SDK_INT + "_" + Build.VERSION.RELEASE;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            e.printStackTrace();
            packageInfo = null;
        }
        if (packageInfo != null) {
            appVersion = packageInfo.versionName;
            appCode = packageInfo.versionCode;
        } else {
            appVersion = "";
            appCode = 0;
        }
        initDisplay(context);
        btAddress = PrintUtil.getDefaultBluethoothDeviceAddress(context);
        btName = PrintUtil.getDefaultBluetoothDeviceName(context);
    }

    public static void initDisplay(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
        density = displayMetrics.density;
        densityDpi = displayMetrics.densityDpi;
    }
}
