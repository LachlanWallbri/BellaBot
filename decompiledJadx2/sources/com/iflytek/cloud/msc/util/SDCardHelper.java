package com.iflytek.cloud.msc.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

/* loaded from: classes3.dex */
public class SDCardHelper {
    public static boolean checkSDCardStatus() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static String getExternalStorageDirectory() {
        return Environment.getExternalStorageDirectory().getAbsolutePath();
    }

    public static long getAvailableSpace(String str) {
        StatFs statFs = new StatFs(str);
        return statFs.getBlockSize() * (statFs.getAvailableBlocks() - 4);
    }

    public static long getAvaliableMemSpace(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        ActivityManager.MemoryInfo memoryInfo = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(memoryInfo);
        return memoryInfo.availMem;
    }
}
