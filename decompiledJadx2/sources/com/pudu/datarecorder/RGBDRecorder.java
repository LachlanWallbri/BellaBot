package com.pudu.datarecorder;

/* loaded from: classes.dex */
public class RGBDRecorder {
    public static native void add_images(byte[] bArr, byte[] bArr2, int i);

    public static native void clean();

    public static native void init_module();

    public static native void set_downsample_ratio(int i, int i2);

    public static native void set_volume_mb(int i);

    public static native void stop();

    static {
        System.loadLibrary("rgbd_recoder_lib");
    }
}
