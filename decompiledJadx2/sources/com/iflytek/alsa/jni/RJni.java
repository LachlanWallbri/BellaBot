package com.iflytek.alsa.jni;

/* loaded from: classes4.dex */
public class RJni {
    private static boolean JNI_LOG = true;
    private static boolean LIB_LOADED = false;

    public static native int destory();

    public static native int init(int i);

    public static native int read(byte[] bArr, int i);

    public static native int showLog(boolean z);

    public static native int startRecord();

    public static native int stopRecord();

    public static void loadLib() {
        if (LIB_LOADED) {
            return;
        }
        try {
            System.loadLibrary("alsa-jni");
            LIB_LOADED = true;
        } catch (Exception e) {
            e.printStackTrace();
            LIB_LOADED = false;
        }
    }
}
