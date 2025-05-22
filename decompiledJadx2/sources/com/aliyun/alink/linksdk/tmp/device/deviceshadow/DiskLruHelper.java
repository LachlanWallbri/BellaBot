package com.aliyun.alink.linksdk.tmp.device.deviceshadow;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tmp.TmpSdk;
import com.aliyun.alink.linksdk.tmp.disklrucache.DiskLruCache;
import com.aliyun.alink.linksdk.tmp.utils.DiskUtil;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class DiskLruHelper {
    public static final int DEFAULT_MAXSIZE = 33554432;
    public static final int DEFAULT_VALUECOUNT = 1;
    private static final String TAG = "[Tmp]DiskLruHelper";
    private String mCacheFile;
    private int mCacheSize;
    private DiskLruCache mDiskLruCache;
    private int mVersion;

    public DiskLruHelper(String str, int i) {
        this(str, i, 33554432);
    }

    public DiskLruHelper(String str, int i, int i2) {
        this.mCacheFile = str;
        this.mVersion = i;
        this.mCacheSize = i2;
        createDiskLruCache();
    }

    protected void createDiskLruCache() {
        try {
            if (TmpSdk.getContext() != null) {
                this.mDiskLruCache = DiskLruCache.open(DiskUtil.getDiskCacheDirWithAppend(TmpSdk.getContext(), this.mCacheFile), this.mVersion, 1, this.mCacheSize);
            }
        } catch (Exception e) {
            ALog.m480e(TAG, "DiskLruCache.open error:" + e.toString());
        }
    }

    public String getString(String str) {
        DiskLruCache diskLruCache;
        ALog.m479d(TAG, "getString key:" + str + " mDiskLruCache:" + this.mDiskLruCache);
        if (TextUtils.isEmpty(str) || (diskLruCache = this.mDiskLruCache) == null) {
            return null;
        }
        try {
            DiskLruCache.Snapshot snapshot = diskLruCache.get(str);
            if (snapshot == null || snapshot.getInputStream(0) == null) {
                return null;
            }
            return DiskUtil.readFully(snapshot.getInputStream(0));
        } catch (Exception e) {
            ALog.m480e(TAG, "mDiskLruCache get error:" + e.toString());
            return null;
        }
    }

    public boolean saveValue(String str, String str2) {
        DiskLruCache diskLruCache;
        ALog.m479d(TAG, "saveValue key:" + str + " data:" + str2 + " mDiskLruCache:" + this.mDiskLruCache);
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || (diskLruCache = this.mDiskLruCache) == null) {
            return false;
        }
        try {
            DiskLruCache.Editor edit = diskLruCache.edit(str);
            edit.set(0, str2);
            edit.commit();
            return true;
        } catch (Exception e) {
            ALog.m480e(TAG, "mDiskLruCache saveValue error: " + e.toString());
            return false;
        }
    }

    public void deleteValue(String str) {
        DiskLruCache diskLruCache;
        ALog.m479d(TAG, "deleteValue key:" + str + " mDiskLruCache:" + this.mDiskLruCache);
        if (TextUtils.isEmpty(str) || (diskLruCache = this.mDiskLruCache) == null) {
            return;
        }
        try {
            diskLruCache.remove(str);
        } catch (Exception e) {
            ALog.m480e(TAG, "deleteValue error:" + e.toString());
        }
    }

    public void clearCache() {
        ALog.m479d(TAG, "clearCache mDiskLruCache:" + this.mDiskLruCache);
        DiskLruCache diskLruCache = this.mDiskLruCache;
        if (diskLruCache == null) {
            return;
        }
        try {
            diskLruCache.delete();
        } catch (Exception e) {
            ALog.m480e(TAG, "clearCache error:" + e.toString());
        }
        createDiskLruCache();
    }
}
