package com.pudutech.peanut.robot_ui.util;

import android.app.Activity;
import android.content.Context;
import android.provider.Settings;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdkwrap.lib.robot.RobotConfig;

/* loaded from: classes5.dex */
public class SystemBrightManager {
    public static String KEY_SCREEN_BRIGHTNESS = "key_screen_brightness";
    public static final String LOCAL_SAVE_KEY = "SystemBright";
    public static int MAX_VALUE = 100;
    public static int MINI_VALUE = 20;
    private static final String TAG = "SystemBrightManager";

    public static synchronized void setMiniBrightness(Activity activity) {
        synchronized (SystemBrightManager.class) {
            if (getSystemBrightness(activity) == MINI_VALUE) {
                return;
            }
            setSystemBrightness(activity, MINI_VALUE);
        }
    }

    public static synchronized void stopAutoModeAndResetBrightness(Activity activity) {
        synchronized (SystemBrightManager.class) {
        }
    }

    public static boolean isAutoBrightness(Activity activity) {
        try {
            return Settings.System.getInt(activity.getContentResolver(), "screen_brightness_mode") == 1;
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void setSystemBrightness(Activity activity, int i) {
        Pdlog.m3273d(TAG, " set system brightness=" + i);
        try {
            SpUtils.set((Context) activity, KEY_SCREEN_BRIGHTNESS, i);
            RobotConfig.INSTANCE.setScreenLight(i);
        } catch (Exception e) {
            Pdlog.m3274e(TAG, "setSystemBrightness Exception = " + e.getMessage());
        }
    }

    public static int getSystemBrightness(Activity activity) {
        int i = SpUtils.get((Context) activity, KEY_SCREEN_BRIGHTNESS, MAX_VALUE);
        Pdlog.m3273d(TAG, "getSystemBrightness=" + i);
        return i;
    }

    public static void startAutoBrightness(Activity activity) {
        if (isAutoBrightness(activity)) {
            return;
        }
        Settings.System.putInt(activity.getContentResolver(), "screen_brightness_mode", 1);
        activity.getContentResolver().notifyChange(Settings.System.getUriFor("screen_brightness"), null);
    }

    public static void stopAutoBrightness(Activity activity) {
        if (isAutoBrightness(activity)) {
            Settings.System.putInt(activity.getContentResolver(), "screen_brightness_mode", 0);
            activity.getContentResolver().notifyChange(Settings.System.getUriFor("screen_brightness"), null);
        }
    }

    public static void setBrightnessMode(Activity activity, int i) {
        Settings.System.putInt(activity.getContentResolver(), "screen_brightness_mode", i);
    }
}
