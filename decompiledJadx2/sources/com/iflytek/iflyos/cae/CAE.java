package com.iflytek.iflyos.cae;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
  classes4.dex
 */
/* loaded from: classes.dex */
public class CAE {
    private static boolean LIB_LOADED = false;

    public static native int CAEAudioWrite(byte[] bArr, int i);

    public static native int CAEAuth(String str);

    public static native int CAEDestory();

    public static native String CAEGetVersion();

    public static native int CAENew(String str, ICAEListener iCAEListener);

    public static native int CAEReloadResource(String str);

    public static native int CAESetRealBeam(int i);

    public static native int CAESetShowLog(int i);

    public static void loadLib() {
        if (LIB_LOADED) {
            return;
        }
        try {
            System.loadLibrary("cae-jni");
            LIB_LOADED = true;
        } catch (Exception unused) {
            LIB_LOADED = false;
        }
    }

    public static boolean isLibLoaded() {
        return LIB_LOADED;
    }
}
