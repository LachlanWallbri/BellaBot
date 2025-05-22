package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class PinModify {

    /* renamed from: a */
    private byte[] f150a;

    /* renamed from: b */
    private byte[] f151b;

    public PinModify() {
        this.f150a = new byte[24];
    }

    public PinModify(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, byte[] bArr, int i19) {
        this.f150a = new byte[24];
        setTimeOut(i);
        setTimeOut2(i2);
        setFormatString(i3);
        setPinBlockString(i4);
        setPinLengthFormat(i5);
        setInsertionOffsetOld(i6);
        setInsertionOffsetNew(i7);
        setPinMaxExtraDigit(i8);
        setConfirmPin(i9);
        setEntryValidationCondition(i10);
        setNumberMessage(i11);
        setLangId(i12);
        setMsgIndex1(i13);
        setMsgIndex2(i14);
        setMsgIndex3(i15);
        setTeoPrologue(0, i16);
        setTeoPrologue(1, i17);
        setTeoPrologue(2, i18);
        setData(bArr, i19);
    }

    public int getTimeOut() {
        return this.f150a[0] & 255;
    }

    public void setTimeOut(int i) {
        this.f150a[0] = (byte) i;
    }

    public int getTimeOut2() {
        return this.f150a[1] & 255;
    }

    public void setTimeOut2(int i) {
        this.f150a[1] = (byte) i;
    }

    public int getFormatString() {
        return this.f150a[2] & 255;
    }

    public void setFormatString(int i) {
        this.f150a[2] = (byte) i;
    }

    public int getPinBlockString() {
        return this.f150a[3] & 255;
    }

    public void setPinBlockString(int i) {
        this.f150a[3] = (byte) i;
    }

    public int getPinLengthFormat() {
        return this.f150a[4] & 255;
    }

    public void setPinLengthFormat(int i) {
        this.f150a[4] = (byte) i;
    }

    public int getInsertionOffsetOld() {
        return this.f150a[5] & 255;
    }

    public void setInsertionOffsetOld(int i) {
        this.f150a[5] = (byte) i;
    }

    public int getInsertionOffsetNew() {
        return this.f150a[6] & 255;
    }

    public void setInsertionOffsetNew(int i) {
        this.f150a[6] = (byte) i;
    }

    public int getPinMaxExtraDigit() {
        byte[] bArr = this.f150a;
        return ((bArr[8] & 255) << 8) | (bArr[7] & 255);
    }

    public void setPinMaxExtraDigit(int i) {
        byte[] bArr = this.f150a;
        bArr[7] = (byte) i;
        bArr[8] = (byte) (i >> 8);
    }

    public int getConfirmPin() {
        return this.f150a[9] & 255;
    }

    public void setConfirmPin(int i) {
        this.f150a[9] = (byte) i;
    }

    public int getEntryValidationCondition() {
        return this.f150a[10] & 255;
    }

    public void setEntryValidationCondition(int i) {
        this.f150a[10] = (byte) i;
    }

    public int getNumberMessage() {
        return this.f150a[11] & 255;
    }

    public void setNumberMessage(int i) {
        this.f150a[11] = (byte) i;
    }

    public int getLangId() {
        byte[] bArr = this.f150a;
        return ((bArr[13] & 255) << 8) | (bArr[12] & 255);
    }

    public void setLangId(int i) {
        byte[] bArr = this.f150a;
        bArr[12] = (byte) i;
        bArr[13] = (byte) (i >> 8);
    }

    public int getMsgIndex1() {
        return this.f150a[14] & 255;
    }

    public void setMsgIndex1(int i) {
        this.f150a[14] = (byte) i;
    }

    public int getMsgIndex2() {
        return this.f150a[15] & 255;
    }

    public void setMsgIndex2(int i) {
        this.f150a[15] = (byte) i;
    }

    public int getMsgIndex3() {
        return this.f150a[16] & 255;
    }

    public void setMsgIndex3(int i) {
        this.f150a[16] = (byte) i;
    }

    public int getTeoPrologue(int i) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException("The index is invalid.");
        }
        return this.f150a[i + 17] & 255;
    }

    public void setTeoPrologue(int i, int i2) {
        if (i < 0 || i >= 3) {
            throw new IllegalArgumentException("The index is invalid.");
        }
        this.f150a[i + 17] = (byte) i2;
    }

    public byte[] getData() {
        return this.f151b;
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
        this.f151b = bArr2;
        System.arraycopy(bArr, 0, bArr2, 0, i);
        byte[] bArr3 = this.f150a;
        byte[] bArr4 = this.f151b;
        bArr3[20] = (byte) bArr4.length;
        bArr3[21] = (byte) (bArr4.length >> 8);
        bArr3[22] = (byte) (bArr4.length >> 16);
        bArr3[23] = (byte) (bArr4.length >>> 24);
    }

    public byte[] toByteArray() {
        byte[] bArr = this.f151b;
        if (bArr != null) {
            byte[] bArr2 = this.f150a;
            byte[] bArr3 = new byte[bArr2.length + bArr.length];
            System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
            byte[] bArr4 = this.f151b;
            System.arraycopy(bArr4, 0, bArr3, this.f150a.length, bArr4.length);
            return bArr3;
        }
        byte[] bArr5 = this.f150a;
        byte[] bArr6 = new byte[bArr5.length];
        System.arraycopy(bArr5, 0, bArr6, 0, bArr5.length);
        return bArr6;
    }
}
