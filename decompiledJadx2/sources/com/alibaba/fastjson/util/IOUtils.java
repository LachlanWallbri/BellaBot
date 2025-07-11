package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONException;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.pudu.library.loracall.SlipConfig;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.MalformedInputException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Properties;
import org.apache.commons.codec.language.Soundex;
import org.jetbrains.anko.DimensionsKt;
import org.threeten.p095bp.Year;
import org.threeten.p095bp.chrono.HijrahDate;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class IOUtils {
    public static final char[] ASCII_CHARS;

    /* renamed from: CA */
    public static final char[] f317CA;
    static final char[] DigitOnes;
    static final char[] DigitTens;
    public static final String FASTJSON_COMPATIBLEWITHFIELDNAME = "fastjson.compatibleWithFieldName";
    public static final String FASTJSON_COMPATIBLEWITHJAVABEAN = "fastjson.compatibleWithJavaBean";
    public static final String FASTJSON_PROPERTIES = "fastjson.properties";

    /* renamed from: IA */
    public static final int[] f318IA;
    static final char[] digits;
    public static final boolean[] identifierFlags;
    public static final char[] replaceChars;
    static final int[] sizeTable;
    public static final byte[] specicalFlags_doubleQuotes;
    public static final boolean[] specicalFlags_doubleQuotesFlags;
    public static final byte[] specicalFlags_singleQuotes;
    public static final boolean[] specicalFlags_singleQuotesFlags;
    public static final Properties DEFAULT_PROPERTIES = new Properties();
    public static final Charset UTF8 = Charset.forName("UTF-8");
    public static final char[] DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static final boolean[] firstIdentifierFlags = new boolean[256];

    public static int stringSize(long j) {
        long j2 = 10;
        for (int i = 1; i < 19; i++) {
            if (j < j2) {
                return i;
            }
            j2 *= 10;
        }
        return 19;
    }

    static {
        char c = 0;
        while (true) {
            boolean[] zArr = firstIdentifierFlags;
            if (c >= zArr.length) {
                break;
            }
            if (c >= 'A' && c <= 'Z') {
                zArr[c] = true;
            } else if (c >= 'a' && c <= 'z') {
                firstIdentifierFlags[c] = true;
            } else if (c == '_' || c == '$') {
                firstIdentifierFlags[c] = true;
            }
            c = (char) (c + 1);
        }
        identifierFlags = new boolean[256];
        char c2 = 0;
        while (true) {
            boolean[] zArr2 = identifierFlags;
            if (c2 < zArr2.length) {
                if (c2 >= 'A' && c2 <= 'Z') {
                    zArr2[c2] = true;
                } else if (c2 >= 'a' && c2 <= 'z') {
                    identifierFlags[c2] = true;
                } else if (c2 == '_') {
                    identifierFlags[c2] = true;
                } else if (c2 >= '0' && c2 <= '9') {
                    identifierFlags[c2] = true;
                }
                c2 = (char) (c2 + 1);
            } else {
                try {
                    break;
                } catch (Throwable unused) {
                }
            }
        }
        loadPropertiesFromFile();
        byte[] bArr = new byte[161];
        specicalFlags_doubleQuotes = bArr;
        byte[] bArr2 = new byte[161];
        specicalFlags_singleQuotes = bArr2;
        specicalFlags_doubleQuotesFlags = new boolean[161];
        specicalFlags_singleQuotesFlags = new boolean[161];
        replaceChars = new char[93];
        bArr[0] = 4;
        bArr[1] = 4;
        bArr[2] = 4;
        bArr[3] = 4;
        bArr[4] = 4;
        bArr[5] = 4;
        bArr[6] = 4;
        bArr[7] = 4;
        bArr[8] = 1;
        bArr[9] = 1;
        bArr[10] = 1;
        bArr[11] = 4;
        bArr[12] = 1;
        bArr[13] = 1;
        bArr[34] = 1;
        bArr[92] = 1;
        bArr2[0] = 4;
        bArr2[1] = 4;
        bArr2[2] = 4;
        bArr2[3] = 4;
        bArr2[4] = 4;
        bArr2[5] = 4;
        bArr2[6] = 4;
        bArr2[7] = 4;
        bArr2[8] = 1;
        bArr2[9] = 1;
        bArr2[10] = 1;
        bArr2[11] = 4;
        bArr2[12] = 1;
        bArr2[13] = 1;
        bArr2[92] = 1;
        bArr2[39] = 1;
        for (int i = 14; i <= 31; i++) {
            specicalFlags_doubleQuotes[i] = 4;
            specicalFlags_singleQuotes[i] = 4;
        }
        for (int i2 = 127; i2 < 160; i2++) {
            specicalFlags_doubleQuotes[i2] = 4;
            specicalFlags_singleQuotes[i2] = 4;
        }
        for (int i3 = 0; i3 < 161; i3++) {
            specicalFlags_doubleQuotesFlags[i3] = specicalFlags_doubleQuotes[i3] != 0;
            specicalFlags_singleQuotesFlags[i3] = specicalFlags_singleQuotes[i3] != 0;
        }
        char[] cArr = replaceChars;
        cArr[0] = '0';
        cArr[1] = '1';
        cArr[2] = '2';
        cArr[3] = '3';
        cArr[4] = '4';
        cArr[5] = '5';
        cArr[6] = '6';
        cArr[7] = '7';
        cArr[8] = 'b';
        cArr[9] = 't';
        cArr[10] = 'n';
        cArr[11] = 'v';
        cArr[12] = 'f';
        cArr[13] = 'r';
        cArr[34] = '\"';
        cArr[39] = '\'';
        cArr[47] = '/';
        cArr[92] = '\\';
        ASCII_CHARS = new char[]{'0', '0', '0', '1', '0', '2', '0', '3', '0', '4', '0', '5', '0', '6', '0', '7', '0', '8', '0', '9', '0', 'A', '0', 'B', '0', 'C', '0', 'D', '0', 'E', '0', 'F', '1', '0', '1', '1', '1', '2', '1', '3', '1', '4', '1', '5', '1', '6', '1', '7', '1', '8', '1', '9', '1', 'A', '1', 'B', '1', 'C', '1', 'D', '1', 'E', '1', 'F', '2', '0', '2', '1', '2', '2', '2', '3', '2', '4', '2', '5', '2', '6', '2', '7', '2', '8', '2', '9', '2', 'A', '2', 'B', '2', 'C', '2', 'D', '2', 'E', '2', 'F'};
        digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        DigitTens = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
        DigitOnes = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        sizeTable = new int[]{9, 99, 999, HijrahDate.MAX_VALUE_OF_ERA, 99999, 999999, 9999999, 99999999, Year.MAX_VALUE, Integer.MAX_VALUE};
        f317CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
        int[] iArr = new int[256];
        f318IA = iArr;
        Arrays.fill(iArr, -1);
        int length = f317CA.length;
        for (int i4 = 0; i4 < length; i4++) {
            f318IA[f317CA[i4]] = i4;
        }
        f318IA[61] = 0;
    }

    public static String getStringProperty(String str) {
        String str2;
        try {
            str2 = System.getProperty(str);
        } catch (SecurityException unused) {
            str2 = null;
        }
        return str2 == null ? DEFAULT_PROPERTIES.getProperty(str) : str2;
    }

    public static void loadPropertiesFromFile() {
        InputStream inputStream = (InputStream) AccessController.doPrivileged(new PrivilegedAction<InputStream>() { // from class: com.alibaba.fastjson.util.IOUtils.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public InputStream run() {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (contextClassLoader != null) {
                    return contextClassLoader.getResourceAsStream(IOUtils.FASTJSON_PROPERTIES);
                }
                return ClassLoader.getSystemResourceAsStream(IOUtils.FASTJSON_PROPERTIES);
            }
        });
        if (inputStream != null) {
            try {
                DEFAULT_PROPERTIES.load(inputStream);
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void getChars(long j, int i, char[] cArr) {
        char c;
        if (j < 0) {
            c = Soundex.SILENT_MARKER;
            j = -j;
        } else {
            c = 0;
        }
        while (j > 2147483647L) {
            long j2 = j / 100;
            int i2 = (int) (j - (((j2 << 6) + (j2 << 5)) + (j2 << 2)));
            int i3 = i - 1;
            cArr[i3] = DigitOnes[i2];
            i = i3 - 1;
            cArr[i] = DigitTens[i2];
            j = j2;
        }
        int i4 = (int) j;
        while (i4 >= 65536) {
            int i5 = i4 / 100;
            int i6 = i4 - (((i5 << 6) + (i5 << 5)) + (i5 << 2));
            int i7 = i - 1;
            cArr[i7] = DigitOnes[i6];
            i = i7 - 1;
            cArr[i] = DigitTens[i6];
            i4 = i5;
        }
        while (true) {
            int i8 = (52429 * i4) >>> 19;
            i--;
            cArr[i] = digits[i4 - ((i8 << 3) + (i8 << 1))];
            if (i8 == 0) {
                break;
            } else {
                i4 = i8;
            }
        }
        if (c != 0) {
            cArr[i - 1] = c;
        }
    }

    public static void getChars(int i, int i2, char[] cArr) {
        char c;
        if (i < 0) {
            c = Soundex.SILENT_MARKER;
            i = -i;
        } else {
            c = 0;
        }
        while (i >= 65536) {
            int i3 = i / 100;
            int i4 = i - (((i3 << 6) + (i3 << 5)) + (i3 << 2));
            int i5 = i2 - 1;
            cArr[i5] = DigitOnes[i4];
            i2 = i5 - 1;
            cArr[i2] = DigitTens[i4];
            i = i3;
        }
        while (true) {
            int i6 = (52429 * i) >>> 19;
            i2--;
            cArr[i2] = digits[i - ((i6 << 3) + (i6 << 1))];
            if (i6 == 0) {
                break;
            } else {
                i = i6;
            }
        }
        if (c != 0) {
            cArr[i2 - 1] = c;
        }
    }

    public static void getChars(byte b, int i, char[] cArr) {
        char c;
        int i2;
        if (b < 0) {
            c = Soundex.SILENT_MARKER;
            i2 = -b;
        } else {
            c = 0;
            i2 = b;
        }
        while (true) {
            int i3 = (52429 * i2) >>> 19;
            i--;
            cArr[i] = digits[i2 - ((i3 << 3) + (i3 << 1))];
            if (i3 == 0) {
                break;
            } else {
                i2 = i3;
            }
        }
        if (c != 0) {
            cArr[i - 1] = c;
        }
    }

    public static int stringSize(int i) {
        int i2 = 0;
        while (i > sizeTable[i2]) {
            i2++;
        }
        return i2 + 1;
    }

    public static void decode(CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer) {
        try {
            CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, true);
            if (!decode.isUnderflow()) {
                decode.throwException();
            }
            CoderResult flush = charsetDecoder.flush(charBuffer);
            if (flush.isUnderflow()) {
                return;
            }
            flush.throwException();
        } catch (CharacterCodingException e) {
            throw new JSONException("utf8 decode error, " + e.getMessage(), e);
        }
    }

    public static boolean firstIdentifier(char c) {
        boolean[] zArr = firstIdentifierFlags;
        return c < zArr.length && zArr[c];
    }

    public static boolean isIdent(char c) {
        boolean[] zArr = identifierFlags;
        return c < zArr.length && zArr[c];
    }

    public static byte[] decodeBase64(char[] cArr, int i, int i2) {
        int i3;
        int i4 = 0;
        if (i2 == 0) {
            return new byte[0];
        }
        int i5 = (i + i2) - 1;
        while (i < i5 && f318IA[cArr[i]] < 0) {
            i++;
        }
        while (i5 > 0 && f318IA[cArr[i5]] < 0) {
            i5--;
        }
        int i6 = cArr[i5] == '=' ? cArr[i5 + (-1)] == '=' ? 2 : 1 : 0;
        int i7 = (i5 - i) + 1;
        if (i2 > 76) {
            i3 = (cArr[76] == '\r' ? i7 / 78 : 0) << 1;
        } else {
            i3 = 0;
        }
        int i8 = (((i7 - i3) * 6) >> 3) - i6;
        byte[] bArr = new byte[i8];
        int i9 = (i8 / 3) * 3;
        int i10 = 0;
        int i11 = 0;
        while (i10 < i9) {
            int[] iArr = f318IA;
            int i12 = i + 1;
            int i13 = i12 + 1;
            int i14 = (iArr[cArr[i]] << 18) | (iArr[cArr[i12]] << 12);
            int i15 = i13 + 1;
            int i16 = i14 | (iArr[cArr[i13]] << 6);
            int i17 = i15 + 1;
            int i18 = i16 | iArr[cArr[i15]];
            int i19 = i10 + 1;
            bArr[i10] = (byte) (i18 >> 16);
            int i20 = i19 + 1;
            bArr[i19] = (byte) (i18 >> 8);
            int i21 = i20 + 1;
            bArr[i20] = (byte) i18;
            if (i3 > 0 && (i11 = i11 + 1) == 19) {
                i17 += 2;
                i11 = 0;
            }
            i = i17;
            i10 = i21;
        }
        if (i10 < i8) {
            int i22 = 0;
            while (i <= i5 - i6) {
                i4 |= f318IA[cArr[i]] << (18 - (i22 * 6));
                i22++;
                i++;
            }
            int i23 = 16;
            while (i10 < i8) {
                bArr[i10] = (byte) (i4 >> i23);
                i23 -= 8;
                i10++;
            }
        }
        return bArr;
    }

    public static byte[] decodeBase64(String str, int i, int i2) {
        int i3;
        int i4 = 0;
        if (i2 == 0) {
            return new byte[0];
        }
        int i5 = (i + i2) - 1;
        while (i < i5 && f318IA[str.charAt(i)] < 0) {
            i++;
        }
        while (i5 > 0 && f318IA[str.charAt(i5)] < 0) {
            i5--;
        }
        int i6 = str.charAt(i5) == '=' ? str.charAt(i5 + (-1)) == '=' ? 2 : 1 : 0;
        int i7 = (i5 - i) + 1;
        if (i2 > 76) {
            i3 = (str.charAt(76) == '\r' ? i7 / 78 : 0) << 1;
        } else {
            i3 = 0;
        }
        int i8 = (((i7 - i3) * 6) >> 3) - i6;
        byte[] bArr = new byte[i8];
        int i9 = (i8 / 3) * 3;
        int i10 = 0;
        int i11 = 0;
        while (i10 < i9) {
            int i12 = i + 1;
            int i13 = i12 + 1;
            int i14 = (f318IA[str.charAt(i)] << 18) | (f318IA[str.charAt(i12)] << 12);
            int i15 = i13 + 1;
            int i16 = i14 | (f318IA[str.charAt(i13)] << 6);
            int i17 = i15 + 1;
            int i18 = i16 | f318IA[str.charAt(i15)];
            int i19 = i10 + 1;
            bArr[i10] = (byte) (i18 >> 16);
            int i20 = i19 + 1;
            bArr[i19] = (byte) (i18 >> 8);
            int i21 = i20 + 1;
            bArr[i20] = (byte) i18;
            if (i3 > 0 && (i11 = i11 + 1) == 19) {
                i17 += 2;
                i11 = 0;
            }
            i = i17;
            i10 = i21;
        }
        if (i10 < i8) {
            int i22 = 0;
            while (i <= i5 - i6) {
                i4 |= f318IA[str.charAt(i)] << (18 - (i22 * 6));
                i22++;
                i++;
            }
            int i23 = 16;
            while (i10 < i8) {
                bArr[i10] = (byte) (i4 >> i23);
                i23 -= 8;
                i10++;
            }
        }
        return bArr;
    }

    public static byte[] decodeBase64(String str) {
        int i;
        int length = str.length();
        int i2 = 0;
        if (length == 0) {
            return new byte[0];
        }
        int i3 = length - 1;
        int i4 = 0;
        while (i4 < i3 && f318IA[str.charAt(i4) & 255] < 0) {
            i4++;
        }
        while (i3 > 0 && f318IA[str.charAt(i3) & 255] < 0) {
            i3--;
        }
        int i5 = str.charAt(i3) == '=' ? str.charAt(i3 + (-1)) == '=' ? 2 : 1 : 0;
        int i6 = (i3 - i4) + 1;
        if (length > 76) {
            i = (str.charAt(76) == '\r' ? i6 / 78 : 0) << 1;
        } else {
            i = 0;
        }
        int i7 = (((i6 - i) * 6) >> 3) - i5;
        byte[] bArr = new byte[i7];
        int i8 = (i7 / 3) * 3;
        int i9 = 0;
        int i10 = 0;
        while (i9 < i8) {
            int i11 = i4 + 1;
            int i12 = i11 + 1;
            int i13 = (f318IA[str.charAt(i4)] << 18) | (f318IA[str.charAt(i11)] << 12);
            int i14 = i12 + 1;
            int i15 = i13 | (f318IA[str.charAt(i12)] << 6);
            int i16 = i14 + 1;
            int i17 = i15 | f318IA[str.charAt(i14)];
            int i18 = i9 + 1;
            bArr[i9] = (byte) (i17 >> 16);
            int i19 = i18 + 1;
            bArr[i18] = (byte) (i17 >> 8);
            int i20 = i19 + 1;
            bArr[i19] = (byte) i17;
            if (i > 0 && (i10 = i10 + 1) == 19) {
                i16 += 2;
                i10 = 0;
            }
            i4 = i16;
            i9 = i20;
        }
        if (i9 < i7) {
            int i21 = 0;
            while (i4 <= i3 - i5) {
                i2 |= f318IA[str.charAt(i4)] << (18 - (i21 * 6));
                i21++;
                i4++;
            }
            int i22 = 16;
            while (i9 < i7) {
                bArr[i9] = (byte) (i2 >> i22);
                i22 -= 8;
                i9++;
            }
        }
        return bArr;
    }

    public static int encodeUTF8(char[] cArr, int i, int i2, byte[] bArr) {
        int i3;
        int i4;
        int i5;
        int i6 = i + i2;
        int i7 = 0;
        int min = Math.min(i2, bArr.length) + 0;
        while (i7 < min && cArr[i] < 128) {
            bArr[i7] = (byte) cArr[i];
            i7++;
            i++;
        }
        while (i < i6) {
            int i8 = i + 1;
            char c = cArr[i];
            if (c < 128) {
                i3 = i7 + 1;
                bArr[i7] = (byte) c;
            } else {
                if (c < 2048) {
                    int i9 = i7 + 1;
                    bArr[i7] = (byte) ((c >> 6) | 192);
                    i7 = i9 + 1;
                    bArr[i9] = (byte) ((c & '?') | 128);
                } else if (c >= 55296 && c < 57344) {
                    int i10 = i8 - 1;
                    if (!Character.isHighSurrogate(c)) {
                        boolean isLowSurrogate = Character.isLowSurrogate(c);
                        i4 = c;
                        if (isLowSurrogate) {
                            throw new JSONException("encodeUTF8 error", new MalformedInputException(1));
                        }
                    } else if (i6 - i10 < 2) {
                        i4 = -1;
                    } else {
                        char c2 = cArr[i10 + 1];
                        if (Character.isLowSurrogate(c2)) {
                            i4 = Character.toCodePoint(c, c2);
                        } else {
                            throw new JSONException("encodeUTF8 error", new MalformedInputException(1));
                        }
                    }
                    if (i4 < 0) {
                        i5 = i7 + 1;
                        bArr[i7] = 63;
                    } else {
                        int i11 = i7 + 1;
                        bArr[i7] = (byte) ((i4 >> 18) | DimensionsKt.HDPI);
                        int i12 = i11 + 1;
                        bArr[i11] = (byte) (((i4 >> 12) & 63) | 128);
                        int i13 = i12 + 1;
                        bArr[i12] = (byte) ((63 & (i4 >> 6)) | 128);
                        bArr[i13] = (byte) ((i4 & 63) | 128);
                        i8++;
                        i5 = i13 + 1;
                    }
                    i7 = i5;
                } else {
                    int i14 = i7 + 1;
                    bArr[i7] = (byte) ((c >> '\f') | 224);
                    int i15 = i14 + 1;
                    bArr[i14] = (byte) ((63 & (c >> 6)) | 128);
                    i3 = i15 + 1;
                    bArr[i15] = (byte) ((c & '?') | 128);
                }
                i = i8;
            }
            i = i8;
            i7 = i3;
        }
        return i7;
    }

    public static int decodeUTF8(byte[] bArr, int i, int i2, char[] cArr) {
        int i3 = i + i2;
        int min = Math.min(i2, cArr.length);
        int i4 = 0;
        while (i4 < min && bArr[i] >= 0) {
            cArr[i4] = (char) bArr[i];
            i4++;
            i++;
        }
        while (i < i3) {
            int i5 = i + 1;
            byte b = bArr[i];
            if (b >= 0) {
                cArr[i4] = (char) b;
                i = i5;
                i4++;
            } else {
                if ((b >> 5) != -2 || (b & 30) == 0) {
                    if ((b >> 4) == -2) {
                        int i6 = i5 + 1;
                        if (i6 < i3) {
                            byte b2 = bArr[i5];
                            int i7 = i6 + 1;
                            byte b3 = bArr[i6];
                            if ((b != -32 || (b2 & 224) != 128) && (b2 & SlipConfig.END) == 128 && (b3 & SlipConfig.END) == 128) {
                                char c = (char) (((b << 12) ^ (b2 << 6)) ^ ((-123008) ^ b3));
                                if (c >= 55296 && c < 57344) {
                                    return -1;
                                }
                                cArr[i4] = c;
                                i4++;
                                i = i7;
                            }
                        }
                        return -1;
                    }
                    if ((b >> 3) == -2 && i5 + 2 < i3) {
                        int i8 = i5 + 1;
                        byte b4 = bArr[i5];
                        int i9 = i8 + 1;
                        byte b5 = bArr[i8];
                        int i10 = i9 + 1;
                        byte b6 = bArr[i9];
                        int i11 = (((b << 18) ^ (b4 << 12)) ^ (b5 << 6)) ^ (3678080 ^ b6);
                        if ((b4 & SlipConfig.END) == 128 && (b5 & SlipConfig.END) == 128 && (b6 & SlipConfig.END) == 128 && Character.isSupplementaryCodePoint(i11)) {
                            int i12 = i4 + 1;
                            cArr[i4] = (char) ((i11 >>> 10) + 55232);
                            i4 = i12 + 1;
                            cArr[i12] = (char) ((i11 & 1023) + GeneratorBase.SURR2_FIRST);
                            i = i10;
                        }
                    }
                    return -1;
                }
                if (i5 >= i3) {
                    return -1;
                }
                int i13 = i5 + 1;
                byte b7 = bArr[i5];
                if ((b7 & SlipConfig.END) != 128) {
                    return -1;
                }
                cArr[i4] = (char) (((b << 6) ^ b7) ^ 3968);
                i = i13;
                i4++;
            }
        }
        return i4;
    }

    public static String readAll(Reader reader) {
        StringBuilder sb = new StringBuilder();
        try {
            char[] cArr = new char[2048];
            while (true) {
                int read = reader.read(cArr, 0, 2048);
                if (read >= 0) {
                    sb.append(cArr, 0, read);
                } else {
                    return sb.toString();
                }
            }
        } catch (Exception e) {
            throw new JSONException("read string from reader error", e);
        }
    }

    public static boolean isValidJsonpQueryParam(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt != '.' && !isIdent(charAt)) {
                return false;
            }
        }
        return true;
    }
}
