package com.acs.smartcard;

import java.util.Arrays;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PinProperties {

    /* renamed from: a */
    private byte[] f152a;

    public PinProperties() {
        this.f152a = new byte[4];
    }

    public PinProperties(byte[] bArr, int i) {
        this.f152a = new byte[4];
        fromByteArray(bArr, i);
    }

    public void clear() {
        Arrays.fill(this.f152a, (byte) 0);
    }

    public int getLcdLayout() {
        byte[] bArr = this.f152a;
        return ((bArr[1] & 255) << 8) | (bArr[0] & 255);
    }

    public int getEntryValidationCondition() {
        return this.f152a[2] & 255;
    }

    public int getTimeOut2() {
        return this.f152a[3] & 255;
    }

    public void fromByteArray(byte[] bArr, int i) {
        if (bArr == null) {
            throw new IllegalArgumentException("The buffer is null.");
        }
        byte[] bArr2 = this.f152a;
        if (i < bArr2.length) {
            throw new IllegalArgumentException("The buffer length is less than " + this.f152a.length + ".");
        }
        if (i > bArr.length) {
            throw new IllegalArgumentException("The buffer length is greater than the buffer size.");
        }
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
    }
}
