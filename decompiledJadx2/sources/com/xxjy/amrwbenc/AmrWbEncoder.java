package com.xxjy.amrwbenc;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class AmrWbEncoder {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum Mode {
        MD66,
        MD885,
        MD1265,
        MD1425,
        MD1585,
        MD1825,
        MD1985,
        MD2305,
        MD2385,
        N_MODES
    }

    public static native int encode(int i, short[] sArr, byte[] bArr, int i2);

    public static native void exit();

    public static native void init();

    static {
        System.loadLibrary("amr-wb-enc");
    }
}
