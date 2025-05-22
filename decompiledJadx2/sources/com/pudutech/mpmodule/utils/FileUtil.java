package com.pudutech.mpmodule.utils;

import android.app.Activity;
import androidx.core.app.ActivityCompat;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class FileUtil {
    private static String[] PERMISSIONS_STORAGE = {"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE"};
    private static final int REQUEST_EXTERNAL_STORAGE = 1;

    public static boolean isExists(File file) {
        if (file == null) {
            return false;
        }
        return file.exists();
    }

    public static void verifyStoragePermissions(Activity activity) {
        int checkSelfPermission = ActivityCompat.checkSelfPermission(activity, "android.permission.READ_EXTERNAL_STORAGE");
        if (ActivityCompat.checkSelfPermission(activity, "android.permission.WRITE_EXTERNAL_STORAGE") == 0 && checkSelfPermission == 0) {
            return;
        }
        ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, 1);
    }
}
