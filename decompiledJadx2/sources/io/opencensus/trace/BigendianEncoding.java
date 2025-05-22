package io.opencensus.trace;

import io.opencensus.internal.Utils;
import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class BigendianEncoding {
    private static final String ALPHABET = "0123456789abcdef";
    private static final int ASCII_CHARACTERS = 128;
    static final int BYTE_BASE16 = 2;
    static final int LONG_BASE16 = 16;
    static final int LONG_BYTES = 8;
    private static final char[] ENCODING = buildEncodingArray();
    private static final byte[] DECODING = buildDecodingArray();

    private static char[] buildEncodingArray() {
        char[] cArr = new char[512];
        for (int i = 0; i < 256; i++) {
            cArr[i] = ALPHABET.charAt(i >>> 4);
            cArr[i | 256] = ALPHABET.charAt(i & 15);
        }
        return cArr;
    }

    private static byte[] buildDecodingArray() {
        byte[] bArr = new byte[128];
        Arrays.fill(bArr, (byte) -1);
        for (int i = 0; i < 16; i++) {
            bArr[ALPHABET.charAt(i)] = (byte) i;
        }
        return bArr;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long longFromByteArray(byte[] bArr, int i) {
        Utils.checkArgument(bArr.length >= i + 8, "array too small");
        return (bArr[i + 7] & 255) | ((bArr[i] & 255) << 56) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << 40) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void longToByteArray(long j, byte[] bArr, int i) {
        Utils.checkArgument(bArr.length >= i + 8, "array too small");
        bArr[i + 7] = (byte) (j & 255);
        bArr[i + 6] = (byte) ((j >> 8) & 255);
        bArr[i + 5] = (byte) ((j >> 16) & 255);
        bArr[i + 4] = (byte) ((j >> 24) & 255);
        bArr[i + 3] = (byte) ((j >> 32) & 255);
        bArr[i + 2] = (byte) ((j >> 40) & 255);
        bArr[i + 1] = (byte) ((j >> 48) & 255);
        bArr[i] = (byte) ((j >> 56) & 255);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long longFromBase16String(CharSequence charSequence, int i) {
        Utils.checkArgument(charSequence.length() >= i + 16, "chars too small");
        return (decodeByte(charSequence.charAt(i + 14), charSequence.charAt(i + 15)) & 255) | ((decodeByte(charSequence.charAt(i), charSequence.charAt(i + 1)) & 255) << 56) | ((decodeByte(charSequence.charAt(i + 2), charSequence.charAt(i + 3)) & 255) << 48) | ((decodeByte(charSequence.charAt(i + 4), charSequence.charAt(i + 5)) & 255) << 40) | ((decodeByte(charSequence.charAt(i + 6), charSequence.charAt(i + 7)) & 255) << 32) | ((decodeByte(charSequence.charAt(i + 8), charSequence.charAt(i + 9)) & 255) << 24) | ((decodeByte(charSequence.charAt(i + 10), charSequence.charAt(i + 11)) & 255) << 16) | ((decodeByte(charSequence.charAt(i + 12), charSequence.charAt(i + 13)) & 255) << 8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void longToBase16String(long j, char[] cArr, int i) {
        byteToBase16((byte) ((j >> 56) & 255), cArr, i);
        byteToBase16((byte) ((j >> 48) & 255), cArr, i + 2);
        byteToBase16((byte) ((j >> 40) & 255), cArr, i + 4);
        byteToBase16((byte) ((j >> 32) & 255), cArr, i + 6);
        byteToBase16((byte) ((j >> 24) & 255), cArr, i + 8);
        byteToBase16((byte) ((j >> 16) & 255), cArr, i + 10);
        byteToBase16((byte) ((j >> 8) & 255), cArr, i + 12);
        byteToBase16((byte) (j & 255), cArr, i + 14);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void byteToBase16String(byte b, char[] cArr, int i) {
        byteToBase16(b, cArr, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte byteFromBase16String(CharSequence charSequence, int i) {
        Utils.checkArgument(charSequence.length() >= i + 2, "chars too small");
        return decodeByte(charSequence.charAt(i), charSequence.charAt(i + 1));
    }

    private static byte decodeByte(char c, char c2) {
        Utils.checkArgument(c2 < 128 && DECODING[c2] != -1, "invalid character " + c2);
        Utils.checkArgument(c < 128 && DECODING[c] != -1, "invalid character " + c);
        byte[] bArr = DECODING;
        return (byte) ((bArr[c] << 4) | bArr[c2]);
    }

    private static void byteToBase16(byte b, char[] cArr, int i) {
        int i2 = b & 255;
        char[] cArr2 = ENCODING;
        cArr[i] = cArr2[i2];
        cArr[i + 1] = cArr2[i2 | 256];
    }

    private BigendianEncoding() {
    }
}
