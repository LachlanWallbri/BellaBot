package com.slamtec.slamware.utils;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class RLEUtil {
    public static native byte[] decode(byte[] bArr);

    public static native byte[] encode(byte[] bArr);

    static {
        System.loadLibrary("rpsdk");
    }
}
