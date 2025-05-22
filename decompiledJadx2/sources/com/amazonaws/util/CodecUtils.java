package com.amazonaws.util;

/* loaded from: classes.dex */
public enum CodecUtils {
    ;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int sanitize(String str, byte[] bArr) {
        int length = bArr.length;
        char[] charArray = str.toCharArray();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char c = charArray[i2];
            if (c != '\r' && c != '\n' && c != ' ') {
                if (c > 127) {
                    throw new IllegalArgumentException("Invalid character found at position " + i2 + " for " + str);
                }
                bArr[i] = (byte) c;
                i++;
            }
        }
        return i;
    }

    public static byte[] toBytesDirect(String str) {
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[charArray.length];
        for (int i = 0; i < bArr.length; i++) {
            char c = charArray[i];
            if (c > 127) {
                throw new IllegalArgumentException("Invalid character found at position " + i + " for " + str);
            }
            bArr[i] = (byte) c;
        }
        return bArr;
    }

    public static String toStringDirect(byte[] bArr) {
        char[] cArr = new char[bArr.length];
        int length = bArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            cArr[i2] = (char) bArr[i];
            i++;
            i2++;
        }
        return new String(cArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void sanityCheckLastPos(int i, int i2) {
        if ((i & i2) != 0) {
            throw new IllegalArgumentException("Invalid last non-pad character detected");
        }
    }
}
