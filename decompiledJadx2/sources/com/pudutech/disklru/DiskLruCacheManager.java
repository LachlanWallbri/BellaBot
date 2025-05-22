package com.pudutech.disklru;

import android.content.Context;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.jakewharton.disklrucache.DiskLruCache;
import com.pudutech.base.MD5Util;
import com.pudutech.base.Pdlog;
import com.pudutech.disklru.utils.Utils;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: DiskLruCacheManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u000f\u001a\u00020\u0010J\u0006\u0010\u0011\u001a\u00020\u0010J\u001f\u0010\u0012\u001a\u00020\u00062\u0012\u0010\u0013\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\u0014\"\u00020\u0006¢\u0006\u0002\u0010\u0015J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u0016\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006J \u0010\u001a\u001a\u00020\u00102\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\u0004J\u000e\u0010\u001f\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0006J\u0016\u0010 \u001a\u00020\u00102\u0006\u0010!\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0006J\u0016\u0010 \u001a\u00020\u00102\u0006\u0010\"\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006#"}, m3961d2 = {"Lcom/pudutech/disklru/DiskLruCacheManager;", "", "()V", "DISK_CACHE_SIZE", "", "TAG", "", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "mDiskCache", "Lcom/jakewharton/disklrucache/DiskLruCache;", "getMDiskCache", "()Lcom/jakewharton/disklrucache/DiskLruCache;", "setMDiskCache", "(Lcom/jakewharton/disklrucache/DiskLruCache;)V", "clear", "", "flush", "genDiskLruKey", "keys", "", "([Ljava/lang/String;)Ljava/lang/String;", "getDiskCache", "Ljava/io/InputStream;", TransferTable.COLUMN_KEY, "path", "init", "context", "Landroid/content/Context;", MapElement.Key.DIR, "cacheSize", "remove", "saveDiskCache", "inputStream", "filePath", "disklru_cache_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DiskLruCacheManager {
    private static final long DISK_CACHE_SIZE = 52428800;
    private static final String TAG = "TtsDiskLruCacheManager";
    private static DiskLruCache mDiskCache;
    public static final DiskLruCacheManager INSTANCE = new DiskLruCacheManager();
    private static final CoroutineScope coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    private DiskLruCacheManager() {
    }

    public final DiskLruCache getMDiskCache() {
        return mDiskCache;
    }

    public final void setMDiskCache(DiskLruCache diskLruCache) {
        mDiskCache = diskLruCache;
    }

    public static /* synthetic */ void init$default(DiskLruCacheManager diskLruCacheManager, Context context, String str, long j, int i, Object obj) {
        if ((i & 4) != 0) {
            j = DISK_CACHE_SIZE;
        }
        diskLruCacheManager.init(context, str, j);
    }

    public final void init(Context context, String dir, long cacheSize) {
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(dir, "dir");
        String diskCacheDir = Utils.INSTANCE.getDiskCacheDir(context);
        Pdlog.m3273d(TAG, "initCache--" + diskCacheDir);
        File file = new File(diskCacheDir, dir);
        if (!file.exists()) {
            file.mkdirs();
        }
        mDiskCache = DiskLruCache.open(file, Utils.INSTANCE.getAppVersion(context), 1, cacheSize);
    }

    private final InputStream getDiskCache(String key) {
        DiskLruCache.Snapshot snapshot;
        Pdlog.m3273d(TAG, "getDiskCache -- key:" + key);
        try {
            DiskLruCache diskLruCache = mDiskCache;
            if (diskLruCache == null || (snapshot = diskLruCache.get(key)) == null) {
                return null;
            }
            Pdlog.m3273d(TAG, "getDiskCache");
            return snapshot.getInputStream(0);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8, types: [kotlin.jvm.internal.Ref$IntRef] */
    public final String getDiskCache(String path, String key) {
        DiskLruCache diskLruCache;
        DiskLruCache.Snapshot snapshot;
        Intrinsics.checkParameterIsNotNull(path, "path");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Pdlog.m3273d(TAG, "getDiskCache--path:" + path + " -- key:" + key);
        ?? r4 = (OutputStream) 0;
        InputStream inputStream = (InputStream) null;
        try {
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            try {
                diskLruCache = mDiskCache;
            } catch (IOException e2) {
                e = e2;
            }
            if (diskLruCache == null || (snapshot = diskLruCache.get(key)) == null) {
                return "";
            }
            Pdlog.m3273d(TAG, "getDiskCache");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(path)));
            try {
                inputStream = snapshot.getInputStream(0);
                byte[] bArr = new byte[8192];
                r4 = new Ref.IntRef();
                while (true) {
                    int read = inputStream.read(bArr, 0, 8192);
                    r4.element = read;
                    if (read == -1) {
                        break;
                    }
                    bufferedOutputStream.write(bArr, 0, r4.element);
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                bufferedOutputStream.close();
            } catch (IOException e3) {
                e = e3;
                r4 = bufferedOutputStream;
                e.printStackTrace();
                if (inputStream != null) {
                    inputStream.close();
                }
                if (r4 != 0) {
                    r4.close();
                }
                return path;
            } catch (Throwable th) {
                th = th;
                r4 = bufferedOutputStream;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                        throw th;
                    }
                }
                if (r4 != 0) {
                    r4.close();
                }
                throw th;
            }
            return path;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public final void saveDiskCache(InputStream inputStream, String key) {
        Intrinsics.checkParameterIsNotNull(inputStream, "inputStream");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Pdlog.m3273d(TAG, "saveDiskCache--inputStream:" + inputStream + " -- key:" + key);
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new DiskLruCacheManager$saveDiskCache$1(key, inputStream, null), 3, null);
    }

    public final void saveDiskCache(String filePath, String key) {
        Intrinsics.checkParameterIsNotNull(filePath, "filePath");
        Intrinsics.checkParameterIsNotNull(key, "key");
        Pdlog.m3273d(TAG, "saveDiskCache--filePath:" + filePath + " -- key:" + key);
        File file = new File(filePath);
        if (file.exists()) {
            BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new DiskLruCacheManager$saveDiskCache$2(key, file, null), 3, null);
        } else {
            Pdlog.m3273d(TAG, "saveDiskCache--file not exist");
        }
    }

    public final void remove(String key) {
        Intrinsics.checkParameterIsNotNull(key, "key");
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new DiskLruCacheManager$remove$1(key, null), 3, null);
    }

    public final void clear() {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new DiskLruCacheManager$clear$1(null), 3, null);
    }

    public final void flush() {
        BuildersKt__Builders_commonKt.launch$default(coroutineScope, null, null, new DiskLruCacheManager$flush$1(null), 3, null);
    }

    public final String genDiskLruKey(String... keys) {
        Intrinsics.checkParameterIsNotNull(keys, "keys");
        String str = "";
        for (String str2 : keys) {
            str = str + str2;
        }
        String md5 = MD5Util.toMD5(str);
        Intrinsics.checkExpressionValueIsNotNull(md5, "MD5Util.toMD5(key)");
        if (md5 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        String lowerCase = md5.toLowerCase();
        Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
        return lowerCase;
    }
}
