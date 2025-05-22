package com.aliyun.alink.linksdk.tmp.device.deviceshadow;

import android.util.LruCache;
import com.aliyun.alink.linksdk.tools.ALog;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class MemoryLruHelper {
    public static final int MAX_LRU_SIZE = 5242880;
    public static final String TAG = "[Tmp]MemoryLruHelper";
    protected LruCache<String, String> mMemoryLruCahce = new LruCache<>(5242880);

    public String getString(String str) {
        ALog.m479d(TAG, "getString key:" + str);
        try {
            return this.mMemoryLruCahce.get(str);
        } catch (Exception e) {
            ALog.m480e(TAG, "mDiskLruCache get error:" + e.toString());
            return null;
        }
    }

    public boolean saveValue(String str, String str2) {
        ALog.m479d(TAG, "saveValue key:" + str + " data:" + str2);
        try {
            this.mMemoryLruCahce.put(str, str2);
            return true;
        } catch (Exception e) {
            ALog.m480e(TAG, "mDiskLruCache saveValue error: " + e.toString());
            return false;
        }
    }

    public void deleteValue(String str) {
        ALog.m479d(TAG, "deleteValue key:" + str);
        try {
            this.mMemoryLruCahce.remove(str);
        } catch (Exception e) {
            ALog.m480e(TAG, "deleteValue error:" + e.toString());
        }
    }
}
