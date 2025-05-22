package com.wzc.vad;

/* loaded from: classes7.dex */
public class VadUtils {
    public native void create();

    public native void free();

    public native int init();

    public native int process(int i, short[] sArr, int i2);

    public native int setMode(int i);

    public native int validRateAndFrameLength(int i, int i2);

    static {
        System.loadLibrary("wzc_webrtc_vad");
    }
}
