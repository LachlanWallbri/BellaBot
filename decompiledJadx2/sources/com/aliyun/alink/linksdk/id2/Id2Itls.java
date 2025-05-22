package com.aliyun.alink.linksdk.id2;

import android.text.TextUtils;
import com.aliyun.alink.linksdk.tools.ALog;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class Id2Itls {
    static final String ARM64_SO_PATH = "/system/lib64/";
    static final String ARM_SO_PATH = "/system/lib/";
    static final String TAG = "Id2Itls";
    static final String libName = "android_id2";
    private AtomicBoolean id2ClientInited = new AtomicBoolean(false);

    private native void nativeDestroyItls(long j);

    private native long nativeEstablishItls(String str, int i, String str2, String str3);

    private native int nativeGetAlertType();

    private native String nativeGetId();

    private native String nativeGetTimestampAuthCode(String str, String str2);

    private native void nativeId2Init();

    private static native void nativeInitSo(String str, String str2);

    private native int nativeRead(long j, byte[] bArr, int i, int i2);

    private native void nativeSetItlsDebugLevel(int i);

    private native void nativeSetLogLevel(int i);

    private native int nativeWrite(long j, byte[] bArr, int i, int i2);

    static {
        try {
            ALog.m479d(TAG, "id2 sdk version 1.0.2.");
            System.loadLibrary(libName);
        } catch (Exception e) {
            ALog.m480e(TAG, e.toString());
            e.printStackTrace();
        }
        initItlsSo();
    }

    public long establishItls(String str, int i, String str2, String str3) {
        long nativeEstablishItls = nativeEstablishItls(str, i, str2, str3);
        ALog.m479d(TAG, "establishItls host:" + str + " port:" + i + " productKey:" + str2 + " handle:" + nativeEstablishItls);
        return nativeEstablishItls;
    }

    public int getAlertType() {
        int nativeGetAlertType = nativeGetAlertType();
        ALog.m479d(TAG, "getAlertType alertType:" + nativeGetAlertType);
        return nativeGetAlertType;
    }

    public void destroyItls(long j) {
        ALog.m479d(TAG, "destroyItls handle:" + j);
        this.id2ClientInited.set(false);
        nativeDestroyItls(j);
    }

    public int itlsRead(long j, byte[] bArr, int i, int i2) {
        ALog.m479d(TAG, "itlsRead handle:" + j + " buf:" + bArr + " len:" + i + " timeout_ms:" + i2);
        return nativeRead(j, bArr, i, i2);
    }

    public int itlsWrite(long j, byte[] bArr, int i, int i2) {
        ALog.m479d(TAG, "itlsWrite handle:" + j + " buf:" + bArr + " len:" + i + " timeout_ms:" + i2);
        return nativeWrite(j, bArr, i, i2);
    }

    public void setItlsDebugLevel(int i) {
        ALog.m479d(TAG, "setItlsDebugLevel debugLevel:" + i);
        nativeSetItlsDebugLevel(i);
    }

    public void setJniDebugLevel(int i) {
        ALog.m479d(TAG, "setJniDebugLevel level:" + i);
        nativeSetLogLevel(i);
    }

    protected static void initItlsSo() {
        try {
            ClassLoader classLoader = Id2ItlsSdk.mContext.getClassLoader();
            Method declaredMethod = Class.forName("java.lang.ClassLoader").getDeclaredMethod("findLibrary", String.class);
            declaredMethod.setAccessible(true);
            String str = (String) declaredMethod.invoke(classLoader, libName);
            ALog.m479d(TAG, "soPath:" + str + " mSoPath:" + Id2ItlsSdk.mSoPath);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            String substring = str.substring(0, str.lastIndexOf("/") + 1);
            String str2 = Id2ItlsSdk.mSoPath;
            if (TextUtils.isEmpty(str2)) {
                str2 = substring.contains("arm64") ? ARM64_SO_PATH : ARM_SO_PATH;
            }
            nativeInitSo(substring, str2);
        } catch (Exception e) {
            e.printStackTrace();
            ALog.m479d(TAG, e.toString());
        }
    }

    public String getID2Id() {
        ALog.m479d(TAG, "getID2Id() called");
        String str = null;
        try {
            if (this.id2ClientInited.compareAndSet(false, true)) {
                ALog.m479d(TAG, "id2 client init() called.");
                nativeId2Init();
            }
            str = nativeGetId();
            ALog.m479d(TAG, "id2=" + str);
            return str;
        } catch (Throwable th) {
            ALog.m484w(TAG, "getID2Id exception=" + th);
            th.printStackTrace();
            return str;
        }
    }

    public String getTimestampAuthCode(String str, String str2) {
        String str3;
        ALog.m479d(TAG, "getTimestampAuthCode() called");
        try {
            str3 = nativeGetTimestampAuthCode(str, str2);
        } catch (Throwable th) {
            th = th;
            str3 = null;
        }
        try {
            ALog.m479d(TAG, "authCode=" + str3);
        } catch (Throwable th2) {
            th = th2;
            ALog.m484w(TAG, "getTimestampAuthCode exception=" + th);
            th.printStackTrace();
            return str3;
        }
        return str3;
    }
}
