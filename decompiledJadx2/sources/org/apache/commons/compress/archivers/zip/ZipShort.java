package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import org.apache.commons.compress.utils.ByteUtils;

/* loaded from: classes8.dex */
public final class ZipShort implements Cloneable, Serializable {
    public static final ZipShort ZERO = new ZipShort(0);
    private static final long serialVersionUID = 1;
    private final int value;

    public ZipShort(int i) {
        this.value = i;
    }

    public ZipShort(byte[] bArr) {
        this(bArr, 0);
    }

    public ZipShort(byte[] bArr, int i) {
        this.value = getValue(bArr, i);
    }

    public byte[] getBytes() {
        byte[] bArr = new byte[2];
        ByteUtils.toLittleEndian(bArr, this.value, 0, 2);
        return bArr;
    }

    public int getValue() {
        return this.value;
    }

    public static byte[] getBytes(int i) {
        byte[] bArr = new byte[2];
        putShort(i, bArr, 0);
        return bArr;
    }

    public static void putShort(int i, byte[] bArr, int i2) {
        ByteUtils.toLittleEndian(bArr, i, i2, 2);
    }

    public static int getValue(byte[] bArr, int i) {
        return (int) ByteUtils.fromLittleEndian(bArr, i, 2);
    }

    public static int getValue(byte[] bArr) {
        return getValue(bArr, 0);
    }

    public boolean equals(Object obj) {
        return (obj instanceof ZipShort) && this.value == ((ZipShort) obj).getValue();
    }

    public int hashCode() {
        return this.value;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "ZipShort value: " + this.value;
    }
}
