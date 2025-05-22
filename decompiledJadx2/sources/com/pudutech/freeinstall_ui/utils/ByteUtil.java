package com.pudutech.freeinstall_ui.utils;

import androidx.core.view.ViewCompat;
import java.nio.charset.Charset;

/* loaded from: classes2.dex */
public class ByteUtil {
    public static byte[] getBytes(char c) {
        return new byte[]{(byte) c, (byte) (c >> '\b')};
    }

    public static byte[] getBytes(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((65280 & i) >> 8), (byte) ((16711680 & i) >> 16), (byte) ((i & ViewCompat.MEASURED_STATE_MASK) >> 24)};
    }

    public static byte[] getBytes(long j) {
        return new byte[]{(byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255), (byte) ((j >> 32) & 255), (byte) ((j >> 40) & 255), (byte) ((j >> 48) & 255), (byte) ((j >> 56) & 255)};
    }

    public static byte[] getBytes(short s) {
        return new byte[]{(byte) (s & 255), (byte) ((s & 65280) >> 8)};
    }

    public static byte[] getBytes(float f) {
        return getBytes(Float.floatToIntBits(f));
    }

    public static byte[] getBytes(double d) {
        return getBytes(Double.doubleToLongBits(d));
    }

    public static byte[] getBytes(String str, String str2) {
        return str.getBytes(Charset.forName(str2));
    }

    public static byte[] getBytes(String str) {
        return getBytes(str, "GBK");
    }

    public static short getShort(byte[] bArr) {
        return (short) (((bArr[1] << 8) & 65280) | (bArr[0] & 255));
    }

    public static char getChar(byte[] bArr) {
        return (char) (((bArr[1] << 8) & 65280) | (bArr[0] & 255));
    }

    public static int getInt(byte[] bArr) {
        return ((bArr[3] << 24) & ViewCompat.MEASURED_STATE_MASK) | (bArr[0] & 255) | ((bArr[1] << 8) & 65280) | ((bArr[2] << 16) & 16711680);
    }

    public static long getLong(byte[] bArr) {
        return (bArr[0] & 255) | ((bArr[1] << 8) & 65280) | ((bArr[2] << 16) & 16711680) | ((bArr[3] << 24) & 4278190080L) | ((bArr[4] << 32) & 1095216660480L) | ((bArr[5] << 40) & 280375465082880L) | ((bArr[6] << 48) & 71776119061217280L) | ((bArr[7] << 56) & (-72057594037927936L));
    }

    public static float getFloat(byte[] bArr) {
        return Float.intBitsToFloat(getInt(bArr));
    }

    public static double getDouble(byte[] bArr) {
        return Double.longBitsToDouble(getLong(bArr));
    }

    public static String getString(byte[] bArr, String str) {
        return new String(bArr, Charset.forName(str));
    }

    public static String getString(byte[] bArr) {
        return getString(bArr, "GBK");
    }
}
