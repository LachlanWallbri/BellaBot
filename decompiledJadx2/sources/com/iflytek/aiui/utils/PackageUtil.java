package com.iflytek.aiui.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public class PackageUtil {
    public static PackageInfo getPackageInfo(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 133);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isAppRunForeground(Context context, String str) {
        List<ActivityManager.RunningTaskInfo> runningTasks = ((ActivityManager) context.getSystemService("activity")).getRunningTasks(1);
        return runningTasks != null && runningTasks.size() == 1 && runningTasks.get(0).topActivity.getPackageName().equals(str);
    }

    public static boolean isAppRunning(Context context, String str) {
        Iterator<ActivityManager.RunningAppProcessInfo> it = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses().iterator();
        while (it.hasNext()) {
            if (it.next().processName.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPackageInstalled(Context context, String str) {
        return getPackageInfo(context, str) != null;
    }

    public static void killBackgroundProcesses(Context context, String str) {
        ((ActivityManager) context.getSystemService("activity")).killBackgroundProcesses(str);
    }
}
