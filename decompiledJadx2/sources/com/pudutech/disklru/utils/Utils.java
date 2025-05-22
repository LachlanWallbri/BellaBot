package com.pudutech.disklru.utils;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Utils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/disklru/utils/Utils;", "", "()V", "getAppVersion", "", "context", "Landroid/content/Context;", "getDiskCacheDir", "", "disklru_cache_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class Utils {
    public static final Utils INSTANCE = new Utils();

    private Utils() {
    }

    public final String getDiskCacheDir(Context context) {
        String path;
        Intrinsics.checkParameterIsNotNull(context, "context");
        if (Intrinsics.areEqual("mounted", Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            File externalCacheDir = context.getExternalCacheDir();
            return (externalCacheDir == null || (path = externalCacheDir.getPath()) == null) ? "" : path;
        }
        File cacheDir = context.getCacheDir();
        Intrinsics.checkExpressionValueIsNotNull(cacheDir, "context.cacheDir");
        String path2 = cacheDir.getPath();
        Intrinsics.checkExpressionValueIsNotNull(path2, "context.cacheDir.path");
        return path2;
    }

    public final int getAppVersion(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
