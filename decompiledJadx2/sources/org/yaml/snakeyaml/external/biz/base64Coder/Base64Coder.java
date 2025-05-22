package org.yaml.snakeyaml.external.biz.base64Coder;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
 */
/* loaded from: classes9.dex */
public class Base64Coder {
    private static byte[] map2;
    private static final String systemLineSeparator = System.getProperty("line.separator");
    private static char[] map1 = new char[64];

    static {
        char c = 'A';
        int i = 0;
        while (c <= 'Z') {
            map1[i] = c;
            c = (char) (c + 1);
            i++;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            map1[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            map1[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        char[] cArr = map1;
        cArr[i] = '+';
        cArr[i + 1] = '/';
        map2 = new byte[128];
        int i2 = 0;
        while (true) {
            byte[] bArr = map2;
            if (i2 >= bArr.length) {
                break;
            }
            bArr[i2] = -1;
            i2++;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            map2[map1[i3]] = (byte) i3;
        }
    }

    public static String encodeString(String str) {
        return new String(encode(str.getBytes()));
    }

    public static String encodeLines(byte[] bArr) {
        return encodeLines(bArr, 0, bArr.length, 76, systemLineSeparator);
    }

    public static String encodeLines(byte[] bArr, int i, int i2, int i3, String str) {
        int i4 = (i3 * 3) / 4;
        if (i4 <= 0) {
            throw new IllegalArgumentException();
        }
        StringBuilder sb = new StringBuilder((((i2 + 2) / 3) * 4) + ((((i2 + i4) - 1) / i4) * str.length()));
        int i5 = 0;
        while (i5 < i2) {
            int min = Math.min(i2 - i5, i4);
            sb.append(encode(bArr, i + i5, min));
            sb.append(str);
            i5 += min;
        }
        return sb.toString();
    }

    public static char[] encode(byte[] bArr) {
        return encode(bArr, 0, bArr.length);
    }

    public static char[] encode(byte[] bArr, int i) {
        return encode(bArr, 0, i);
    }

    public static char[] encode(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = ((i2 * 4) + 2) / 3;
        char[] cArr = new char[((i2 + 2) / 3) * 4];
        int i8 = i2 + i;
        int i9 = 0;
        while (i < i8) {
            int i10 = i + 1;
            int i11 = bArr[i] & 255;
            if (i10 < i8) {
                i3 = i10 + 1;
                i4 = bArr[i10] & 255;
            } else {
                i3 = i10;
                i4 = 0;
            }
            if (i3 < i8) {
                i5 = i3 + 1;
                i6 = bArr[i3] & 255;
            } else {
                i5 = i3;
                i6 = 0;
            }
            int i12 = i11 >>> 2;
            int i13 = ((i11 & 3) << 4) | (i4 >>> 4);
            int i14 = ((i4 & 15) << 2) | (i6 >>> 6);
            int i15 = i6 & 63;
            int i16 = i9 + 1;
            char[] cArr2 = map1;
            cArr[i9] = cArr2[i12];
            int i17 = i16 + 1;
            cArr[i16] = cArr2[i13];
            char c = '=';
            cArr[i17] = i17 < i7 ? cArr2[i14] : '=';
            int i18 = i17 + 1;
            if (i18 < i7) {
                c = map1[i15];
            }
            cArr[i18] = c;
            i9 = i18 + 1;
            i = i5;
        }
        return cArr;
    }

    public static String decodeString(String str) {
        return new String(decode(str));
    }

    public static byte[] decodeLines(String str) {
        char[] cArr = new char[str.length()];
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt != ' ' && charAt != '\r' && charAt != '\n' && charAt != '\t') {
                cArr[i] = charAt;
                i++;
            }
        }
        return decode(cArr, 0, i);
    }

    public static byte[] decode(String str) {
        return decode(str.toCharArray());
    }

    public static byte[] decode(char[] cArr) {
        return decode(cArr, 0, cArr.length);
    }

    public static byte[] decode(char[] cArr, int i, int i2) {
        int i3;
        char c;
        int i4;
        if (i2 % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (i2 > 0 && cArr[(i + i2) - 1] == '=') {
            i2--;
        }
        int i5 = (i2 * 3) / 4;
        byte[] bArr = new byte[i5];
        int i6 = i2 + i;
        int i7 = 0;
        while (i < i6) {
            int i8 = i + 1;
            char c2 = cArr[i];
            int i9 = i8 + 1;
            char c3 = cArr[i8];
            char c4 = 'A';
            if (i9 < i6) {
                i3 = i9 + 1;
                c = cArr[i9];
            } else {
                i3 = i9;
                c = 'A';
            }
            if (i3 < i6) {
                int i10 = i3 + 1;
                char c5 = cArr[i3];
                i3 = i10;
                c4 = c5;
            }
            if (c2 > 127 || c3 > 127 || c > 127 || c4 > 127) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            byte[] bArr2 = map2;
            byte b = bArr2[c2];
            byte b2 = bArr2[c3];
            byte b3 = bArr2[c];
            byte b4 = bArr2[c4];
            if (b < 0 || b2 < 0 || b3 < 0 || b4 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int i11 = (b << 2) | (b2 >>> 4);
            int i12 = ((b2 & 15) << 4) | (b3 >>> 2);
            int i13 = ((b3 & 3) << 6) | b4;
            int i14 = i7 + 1;
            bArr[i7] = (byte) i11;
            if (i14 < i5) {
                i4 = i14 + 1;
                bArr[i14] = (byte) i12;
            } else {
                i4 = i14;
            }
            if (i4 < i5) {
                i7 = i4 + 1;
                bArr[i4] = (byte) i13;
            } else {
                i7 = i4;
            }
            i = i3;
        }
        return bArr;
    }

    private Base64Coder() {
    }
}
