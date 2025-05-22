package com.danikula.videocache;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* loaded from: classes.dex */
final class StorageUtils {
    private static final String INDIVIDUAL_DIR_NAME = "video-cache";
    private static final Logger LOG = LoggerFactory.getLogger("StorageUtils");

    StorageUtils() {
    }

    public static File getIndividualCacheDirectory(Context context) {
        return new File(getCacheDirectory(context, true), INDIVIDUAL_DIR_NAME);
    }

    private static File getCacheDirectory(Context context, boolean z) {
        String str;
        try {
            str = Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
            str = "";
        }
        File externalCacheDir = (z && "mounted".equals(str)) ? getExternalCacheDir(context) : null;
        if (externalCacheDir == null) {
            externalCacheDir = context.getCacheDir();
        }
        if (externalCacheDir != null) {
            return externalCacheDir;
        }
        String str2 = "/data/data/" + context.getPackageName() + "/cache/";
        LOG.warn("Can't define system cache directory! '" + str2 + "%s' will be used.");
        return new File(str2);
    }

    private static File getExternalCacheDir(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        LOG.warn("Unable to create external cache directory");
        return null;
    }
}
