package com.amitshekhar.utils;

import java.io.UnsupportedEncodingException;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ConverterUtils {
    private static final int MAX_BLOB_LENGTH = 512;
    private static final String UNKNOWN_BLOB_LABEL = "{blob}";

    private ConverterUtils() {
    }

    public static String blobToString(byte[] bArr) {
        if (bArr.length > 512 || !fastIsAscii(bArr)) {
            return UNKNOWN_BLOB_LABEL;
        }
        try {
            return new String(bArr, "US-ASCII");
        } catch (UnsupportedEncodingException unused) {
            return UNKNOWN_BLOB_LABEL;
        }
    }

    public static boolean fastIsAscii(byte[] bArr) {
        for (byte b : bArr) {
            if ((b & Byte.MIN_VALUE) != 0) {
                return false;
            }
        }
        return true;
    }
}
