package com.iflytek.alsa.jni;

/* loaded from: classes4.dex */
public class RefJni {
    private static boolean JNI_LOG = true;
    private static boolean LIB_LOADED = false;

    public static native int pcmOpen(int i, int i2, int i3, int i4, int i5, int i6, int i7, Object obj);

    public static native int pcm_buffer_size(long j);

    public static native int pcm_close(long j);

    public static native int pcm_read(byte[] bArr, int i);

    public static native int pcm_start_record(int i, int i2);

    public static native void setJniLog(boolean z);

    public static void loadLib() {
        if (LIB_LOADED) {
            return;
        }
        try {
            System.loadLibrary("alsa-jni");
            setJniLog(JNI_LOG);
            LIB_LOADED = true;
        } catch (Exception unused) {
            LIB_LOADED = false;
        }
    }

    public static boolean isLibLoaded() {
        return LIB_LOADED;
    }

    public static void showJniLog(boolean z) {
        JNI_LOG = z;
        if (LIB_LOADED) {
            setJniLog(z);
        }
    }
}
