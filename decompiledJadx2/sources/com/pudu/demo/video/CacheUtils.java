package com.pudu.demo.video;

import android.content.Context;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.danikula.videocache.file.Md5FileNameGenerator;
import com.pudutech.base.Pdlog;
import java.io.File;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* compiled from: CacheUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\u000e\u0010\r\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u0011\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0016\u0010\u0012\u001a\u00020\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\bJ\u000e\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0015\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\bJ\u000e\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\u0017\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\bJ\u0010\u0010\u0018\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m3961d2 = {"Lcom/pudu/demo/video/CacheUtils;", "", "()V", "DEFAULT_CACHE_COUNT", "", "DEFAULT_CACHE_LENGTH", "", "TAG", "", "cleanDirectory", "", "file", "Ljava/io/File;", "cleanVideoCacheDir", "context", "Landroid/content/Context;", "createNewFile", RequestParameters.SUBRESOURCE_DELETE, "deleteCacheFile", "url", "deleteOrThrow", "getCacheFile", "getFolderSize", "getProxyCacheFile", "getVideoCacheDir", "isCacheFile", "", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class CacheUtils {
    public static final int DEFAULT_CACHE_COUNT = 20;
    public static final long DEFAULT_CACHE_LENGTH = 943718400;
    public static final CacheUtils INSTANCE = new CacheUtils();
    private static final String TAG = "CacheUtils";

    private CacheUtils() {
    }

    public final File getVideoCacheDir(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        return new File(context.getCacheDir(), "video");
    }

    public final void cleanVideoCacheDir(Context context) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        cleanDirectory(getVideoCacheDir(context));
    }

    private final void cleanDirectory(File file) {
        File[] listFiles;
        if (file == null) {
            Intrinsics.throwNpe();
        }
        if (file.exists() && (listFiles = file.listFiles()) != null) {
            for (File contentFile : listFiles) {
                Intrinsics.checkExpressionValueIsNotNull(contentFile, "contentFile");
                delete(contentFile);
            }
        }
    }

    private final void delete(File file) {
        if (file.isFile() && file.exists()) {
            deleteOrThrow(file);
        } else {
            cleanDirectory(file);
            deleteOrThrow(file);
        }
    }

    public final void deleteOrThrow(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        if (file.exists()) {
            if (!file.delete()) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                Object[] objArr = {file.getAbsolutePath()};
                String format = String.format("File %s can't be deleted", Arrays.copyOf(objArr, objArr.length));
                Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
                Pdlog.m3274e(TAG, format);
            }
            StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
            Object[] objArr2 = {file.getAbsolutePath()};
            String format2 = String.format("File %s can  deleted", Arrays.copyOf(objArr2, objArr2.length));
            Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
            Pdlog.m3274e(TAG, format2);
        }
    }

    public final void createNewFile(File file) {
        Intrinsics.checkParameterIsNotNull(file, "file");
        if (file.exists()) {
            file.delete();
            file.createNewFile();
            Pdlog.m3273d(TAG, "createNewFile()  file =" + file.getPath());
        }
    }

    public final long getFolderSize(File file) {
        File[] listFiles;
        Intrinsics.checkParameterIsNotNull(file, "file");
        long j = 0;
        try {
            listFiles = file.listFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (listFiles == null) {
            return -1L;
        }
        for (File value : listFiles) {
            Intrinsics.checkExpressionValueIsNotNull(value, "value");
            j += value.isDirectory() ? getFolderSize(value) : value.length();
        }
        return j;
    }

    public final File getProxyCacheFile(Context context, String url) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return new File(getVideoCacheDir(context), new Md5FileNameGenerator().generate(url));
    }

    public final boolean isCacheFile(Context context, String url) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return getCacheFile(context, url).exists();
    }

    public final void deleteCacheFile(Context context, String url) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(url, "url");
        deleteOrThrow(getCacheFile(context, url));
    }

    public final File getCacheFile(Context context, String url) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(url, "url");
        File videoCacheDir = getVideoCacheDir(context);
        String generate = new Md5FileNameGenerator().generate(url);
        File file = new File(videoCacheDir, generate);
        if (file.exists()) {
            return file;
        }
        return new File(videoCacheDir, generate + ".download");
    }
}
