package com.iflytek.msc;

import android.text.TextUtils;

/* loaded from: classes3.dex */
public class AIMIC {
    public static final String DEF_LIBNAME_C = "aimic";
    public static final long INVALID_HANDLER = 0;
    private static final String VAL_SEP = ";";
    private static boolean sIsLoaded = false;
    private static long sHandler = 0;
    private static Object sSync = Long.valueOf(sHandler);

    /* loaded from: classes3.dex */
    public interface Listener {
        void onRecogAudio(byte[] bArr, int i, int i2, Object obj);

        void onWakeupAudio(byte[] bArr, int i, int i2, Object obj);

        void onWakeupMsg(int i, int i2, int i3, byte[] bArr, int i4, byte[] bArr2, int i5, byte[] bArr3, int i6);
    }

    public static native int AIMICAudioWrite(long j, byte[] bArr, int i, int i2) throws Throwable;

    public static native void AIMICDebugLog(boolean z, int i) throws Throwable;

    public static native int AIMICDestroy(long j) throws Throwable;

    public static native int AIMICGetChannel() throws Throwable;

    public static native int AIMICGetParam(long j, byte[] bArr, byte[] bArr2) throws Throwable;

    public static native byte[] AIMICGetVersion() throws Throwable;

    public static native int AIMICNew(byte[] bArr, Listener listener) throws Throwable;

    public static native int AIMICResetEng(long j) throws Throwable;

    public static native int AIMICSetParam(long j, byte[] bArr, byte[] bArr2) throws Throwable;

    public static void loadLibrary(String str) throws Throwable {
        String[] split;
        synchronized (sSync) {
            sIsLoaded = false;
            if (TextUtils.isEmpty(str)) {
                split = new String[]{DEF_LIBNAME_C};
            } else {
                split = str.split(";");
            }
            for (String str2 : split) {
                System.loadLibrary(str2);
            }
            sIsLoaded = true;
        }
    }

    public static boolean isLoaded() {
        boolean z;
        synchronized (sSync) {
            z = sIsLoaded;
        }
        return z;
    }

    public static boolean isValid() {
        boolean z;
        synchronized (sSync) {
            z = 0 != sHandler;
        }
        return z;
    }

    public static long getHandler() {
        return sHandler;
    }
}
