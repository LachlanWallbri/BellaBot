package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PinVerify {

    /* renamed from: a */
    private byte[] f153a;

    /* renamed from: b */
    private byte[] f154b;

    public PinVerify() {
        this.f153a = new byte[19];
    }

    public PinVerify(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, byte[] bArr, int i14) {
        this.f153a = new byte[19];
        setTimeOut(i);
        setTimeOut2(i2);
        setFormatString(i3);
        setPinBlockString(i4);
        setPinLengthFormat(i5);
        setPinMaxExtraDigit(i6);
        setEntryValidationCondition(i7);
        setNumberMessage(i8);
        setLangId(i9);
        setMsgIndex(i10);
        setTeoPrologue(0, i11);
        setTeoPrologue(1, i12);
        setTeoPrologue(2, i13);
        setData(bArr, i14);
    }

    public int getTimeOut() {
        return this.f153a[0] & 255;
    }

    public void setTimeOut(int i) {
        this.f153a[0] = (byte) i;
    }

    public int getTimeOut2() {
        return this.f153a[1] & 255;
    }

    public void setTimeOut2(int i) {
        this.f153a[1] = (byte) i;
    }

    public int getFormatString() {
        return this.f153a[2] & 255;
    }

    public void setFormatString(int i) {
        this.f153a[2] = (byte) i;
    }

    public int getPinBlockString() {
        return this.f153a[3] & 255;
    }

    public void setPinBlockString(int i) {
        this.f153a[3] = (byte) i;
    }

    public int getPinLengthFormat() {
        return this.f153a[4] & 255;
    }

    public void setPinLengthFormat(int i) {
        this.f153a[4] = (byte) i;
    }

    public int getPinMaxExtraDigit() {
        byte[] bArr = this.f153a;
        return ((bArr[6] & 255) << 8) | (bArr[5] & 255);
    }

    public void setPinMaxExtraDigit(int i) {
        byte[] bArr = this.f153a;
        bArr[5] = (byte) i;
        bArr[6] = (byte) (i >> 8);
    }

    public int getEntryValidationCondition() {
        return this.f153a[7] & 255;
    }

    public void setEntryValidationCondition(int i) {
        this.f153a[7] = (byte) i;
    }

    public int getNumberMessage() {
        return this.f153a[8] & 255;
    }

    public void setNumberMessage(int i) {
        this.f153a[8] = (byte) i;
    }

    public int getLangId() {
        byte[] bArr = this.f153a;
        return ((bArr[10] & 255) << 8) | (bArr[9] & 255);
    }

    public void setLangId(int i) {
        byte[] bArr = this.f153a;
        bArr[9] = (byte) i;
        bArr[10] = (byte) (i >> 8);
    }

    public int getMsgIndex() {
        return this.f153a[11] & 255;
    }

    public void setMsgIndex(int i) {
        this.f153a[11] = (byte) i;
    }

    public int getTeoPrologue(int i) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException("The index is invalid.");
        }
        return this.f153a[i + 12] & 255;
    }

    public void setTeoPrologue(int i, int i2) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException("The index is invalid.");
        }
        this.f153a[i + 12] = (byte) i2;
    }

    public byte[] getData() {
        return this.f154b;
    }

    public void setData(byte[] bArr, int i) {
        if (bArr == null) {
            throw new IllegalArgumentException("The data is null.");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("The data length is less than or equal to zero.");
        }
        if (i > bArr.length) {
            throw new IllegalArgumentException("The data length is greater than the data size.");
        }
        byte[] bArr2 = new byte[i];
        this.f154b = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i);
        byte[] bArr3 = this.f153a;
        byte[] bArr4 = this.f154b;
        bArr3[15] = (byte) bArr4.length;
        bArr3[16] = (byte) (bArr4.length >> 8);
        bArr3[17] = (byte) (bArr4.length >> 16);
        bArr3[18] = (byte) (bArr4.length >>> 24);
    }

    public byte[] toByteArray() {
        byte[] bArr = this.f154b;
        if (bArr != null) {
            byte[] bArr2 = this.f153a;
            byte[] bArr3 = new byte[bArr2.length + bArr.length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            byte[] bArr4 = this.f154b;
            System.arraycopy(bArr4, 0, bArr3, this.f153a.length, bArr4.length);
            return bArr3;
        }
        byte[] bArr5 = this.f153a;
        byte[] bArr6 = new byte[bArr5.length];
        System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
        return bArr6;
    }
}
