package com.pudutech.bumblebee.presenter.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import org.bouncycastle.pqc.math.linearalgebra.Matrix;
import org.jetbrains.anko.DimensionsKt;

/* loaded from: classes4.dex */
public class Base64Util {
    private static final char[] S_BASE64CHAR = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', Matrix.MATRIX_TYPE_RANDOM_LT, 'M', 'N', 'O', 'P', 'Q', Matrix.MATRIX_TYPE_RANDOM_REGULAR, 'S', 'T', Matrix.MATRIX_TYPE_RANDOM_UT, 'V', 'W', 'X', 'Y', Matrix.MATRIX_TYPE_ZERO, 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] S_DECODETABLE = new byte[128];

    static {
        int i = 0;
        int i2 = 0;
        while (true) {
            byte[] bArr = S_DECODETABLE;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = Byte.MAX_VALUE;
            i2++;
        }
        while (true) {
            char[] cArr = S_BASE64CHAR;
            if (i >= cArr.length) {
                return;
            }
            S_DECODETABLE[cArr[i]] = (byte) i;
            i++;
        }
    }

    private static int decode0(char[] cArr, byte[] bArr, int i) {
        char c = cArr[3] == '=' ? (char) 2 : (char) 3;
        if (cArr[2] == '=') {
            c = 1;
        }
        byte[] bArr2 = S_DECODETABLE;
        byte b = bArr2[cArr[0]];
        byte b2 = bArr2[cArr[1]];
        byte b3 = bArr2[cArr[2]];
        byte b4 = bArr2[cArr[3]];
        if (c == 1) {
            bArr[i] = (byte) (((b << 2) & 252) | (3 & (b2 >> 4)));
            return 1;
        }
        if (c == 2) {
            bArr[i] = (byte) ((3 & (b2 >> 4)) | ((b << 2) & 252));
            bArr[i + 1] = (byte) (((b2 << 4) & DimensionsKt.HDPI) | ((b3 >> 2) & 15));
            return 2;
        }
        if (c == 3) {
            int i2 = i + 1;
            bArr[i] = (byte) (((b << 2) & 252) | ((b2 >> 4) & 3));
            bArr[i2] = (byte) (((b2 << 4) & DimensionsKt.HDPI) | ((b3 >> 2) & 15));
            bArr[i2 + 1] = (byte) ((b4 & 63) | ((b3 << 6) & 192));
            return 3;
        }
        throw new RuntimeException("Internal error");
    }

    public static byte[] decode(char[] cArr, int i, int i2) {
        char[] cArr2 = new char[4];
        byte[] bArr = new byte[((i2 / 4) * 3) + 3];
        int i3 = 0;
        int i4 = 0;
        for (int i5 = i; i5 < i + i2; i5++) {
            char c = cArr[i5];
            if (c != '=') {
                byte[] bArr2 = S_DECODETABLE;
                if (c < bArr2.length) {
                    if (bArr2[c] == Byte.MAX_VALUE) {
                    }
                }
            }
            int i6 = i4 + 1;
            cArr2[i4] = c;
            if (i6 == cArr2.length) {
                i3 += decode0(cArr2, bArr, i3);
                i4 = 0;
            } else {
                i4 = i6;
            }
        }
        if (i3 == bArr.length) {
            return bArr;
        }
        byte[] bArr3 = new byte[i3];
        System.arraycopy(bArr, 0, bArr3, 0, i3);
        return bArr3;
    }

    public static byte[] decode(String str) {
        char[] cArr = new char[4];
        byte[] bArr = new byte[((str.length() / 4) * 3) + 3];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            char charAt = str.charAt(i3);
            if (charAt != '=') {
                byte[] bArr2 = S_DECODETABLE;
                if (charAt < bArr2.length) {
                    if (bArr2[charAt] == Byte.MAX_VALUE) {
                    }
                }
            }
            int i4 = i2 + 1;
            cArr[i2] = charAt;
            if (i4 == cArr.length) {
                i += decode0(cArr, bArr, i);
                i2 = 0;
            } else {
                i2 = i4;
            }
        }
        if (i == bArr.length) {
            return bArr;
        }
        byte[] bArr3 = new byte[i];
        System.arraycopy(bArr, 0, bArr3, 0, i);
        return bArr3;
    }

    public static void decode(char[] cArr, int i, int i2, OutputStream outputStream) throws IOException {
        char[] cArr2 = new char[4];
        byte[] bArr = new byte[3];
        int i3 = 0;
        for (int i4 = i; i4 < i + i2; i4++) {
            char c = cArr[i4];
            if (c != '=') {
                byte[] bArr2 = S_DECODETABLE;
                if (c < bArr2.length) {
                    if (bArr2[c] == Byte.MAX_VALUE) {
                    }
                }
            }
            int i5 = i3 + 1;
            cArr2[i3] = c;
            if (i5 == cArr2.length) {
                outputStream.write(bArr, 0, decode0(cArr2, bArr, 0));
                i3 = 0;
            } else {
                i3 = i5;
            }
        }
    }

    public static void decode(String str, OutputStream outputStream) throws IOException {
        char[] cArr = new char[4];
        byte[] bArr = new byte[3];
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != '=') {
                byte[] bArr2 = S_DECODETABLE;
                if (charAt < bArr2.length) {
                    if (bArr2[charAt] == Byte.MAX_VALUE) {
                    }
                }
            }
            int i3 = i + 1;
            cArr[i] = charAt;
            if (i3 == cArr.length) {
                outputStream.write(bArr, 0, decode0(cArr, bArr, 0));
                i = 0;
            } else {
                i = i3;
            }
        }
    }

    public static String encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public static String encode(byte[] bArr, int i, int i2) {
        if (i2 <= 0) {
            return "";
        }
        char[] cArr = new char[((i2 / 3) * 4) + 4];
        int i3 = i2 - i;
        int i4 = 0;
        while (i3 >= 3) {
            int i5 = ((bArr[i] & 255) << 16) + ((bArr[i + 1] & 255) << 8) + (bArr[i + 2] & 255);
            int i6 = i4 + 1;
            char[] cArr2 = S_BASE64CHAR;
            cArr[i4] = cArr2[i5 >> 18];
            int i7 = i6 + 1;
            cArr[i6] = cArr2[(i5 >> 12) & 63];
            int i8 = i7 + 1;
            cArr[i7] = cArr2[(i5 >> 6) & 63];
            i4 = i8 + 1;
            cArr[i8] = cArr2[i5 & 63];
            i += 3;
            i3 -= 3;
        }
        if (i3 == 1) {
            int i9 = bArr[i] & 255;
            int i10 = i4 + 1;
            char[] cArr3 = S_BASE64CHAR;
            cArr[i4] = cArr3[i9 >> 2];
            int i11 = i10 + 1;
            cArr[i10] = cArr3[(i9 << 4) & 63];
            int i12 = i11 + 1;
            cArr[i11] = '=';
            i4 = i12 + 1;
            cArr[i12] = '=';
        } else if (i3 == 2) {
            int i13 = ((bArr[i] & 255) << 8) + (bArr[i + 1] & 255);
            int i14 = i4 + 1;
            char[] cArr4 = S_BASE64CHAR;
            cArr[i4] = cArr4[i13 >> 10];
            int i15 = i14 + 1;
            cArr[i14] = cArr4[(i13 >> 4) & 63];
            int i16 = i15 + 1;
            cArr[i15] = cArr4[(i13 << 2) & 63];
            i4 = i16 + 1;
            cArr[i16] = '=';
        }
        return new String(cArr, 0, i4);
    }

    public static void encode(byte[] bArr, int i, int i2, OutputStream outputStream) throws IOException {
        if (i2 <= 0) {
            return;
        }
        byte[] bArr2 = new byte[4];
        int i3 = i2 - i;
        while (i3 >= 3) {
            int i4 = ((bArr[i] & 255) << 16) + ((bArr[i + 1] & 255) << 8) + (bArr[i + 2] & 255);
            char[] cArr = S_BASE64CHAR;
            bArr2[0] = (byte) cArr[i4 >> 18];
            bArr2[1] = (byte) cArr[(i4 >> 12) & 63];
            bArr2[2] = (byte) cArr[(i4 >> 6) & 63];
            bArr2[3] = (byte) cArr[i4 & 63];
            outputStream.write(bArr2, 0, 4);
            i += 3;
            i3 -= 3;
        }
        if (i3 == 1) {
            int i5 = bArr[i] & 255;
            char[] cArr2 = S_BASE64CHAR;
            bArr2[0] = (byte) cArr2[i5 >> 2];
            bArr2[1] = (byte) cArr2[(i5 << 4) & 63];
            bArr2[2] = 61;
            bArr2[3] = 61;
            outputStream.write(bArr2, 0, 4);
            return;
        }
        if (i3 == 2) {
            int i6 = ((bArr[i] & 255) << 8) + (bArr[i + 1] & 255);
            char[] cArr3 = S_BASE64CHAR;
            bArr2[0] = (byte) cArr3[i6 >> 10];
            bArr2[1] = (byte) cArr3[(i6 >> 4) & 63];
            bArr2[2] = (byte) cArr3[(i6 << 2) & 63];
            bArr2[3] = 61;
            outputStream.write(bArr2, 0, 4);
        }
    }

    public static void encode(byte[] bArr, int i, int i2, Writer writer) throws IOException {
        if (i2 <= 0) {
            return;
        }
        char[] cArr = new char[4];
        int i3 = i2 - i;
        int i4 = 0;
        while (i3 >= 3) {
            int i5 = ((bArr[i] & 255) << 16) + ((bArr[i + 1] & 255) << 8) + (bArr[i + 2] & 255);
            char[] cArr2 = S_BASE64CHAR;
            cArr[0] = cArr2[i5 >> 18];
            cArr[1] = cArr2[(i5 >> 12) & 63];
            cArr[2] = cArr2[(i5 >> 6) & 63];
            cArr[3] = cArr2[i5 & 63];
            writer.write(cArr, 0, 4);
            i += 3;
            i3 -= 3;
            i4 += 4;
            if (i4 % 76 == 0) {
                writer.write("\n");
            }
        }
        if (i3 == 1) {
            int i6 = bArr[i] & 255;
            char[] cArr3 = S_BASE64CHAR;
            cArr[0] = cArr3[i6 >> 2];
            cArr[1] = cArr3[(i6 << 4) & 63];
            cArr[2] = '=';
            cArr[3] = '=';
            writer.write(cArr, 0, 4);
            return;
        }
        if (i3 == 2) {
            int i7 = ((bArr[i] & 255) << 8) + (bArr[i + 1] & 255);
            char[] cArr4 = S_BASE64CHAR;
            cArr[0] = cArr4[i7 >> 10];
            cArr[1] = cArr4[(i7 >> 4) & 63];
            cArr[2] = cArr4[(i7 << 2) & 63];
            cArr[3] = '=';
            writer.write(cArr, 0, 4);
        }
    }
}
