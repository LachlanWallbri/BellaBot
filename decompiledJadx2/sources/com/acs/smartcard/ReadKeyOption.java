package com.acs.smartcard;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public class ReadKeyOption {

    /* renamed from: a */
    private byte[] f155a;

    public ReadKeyOption() {
        this.f155a = new byte[6];
    }

    public ReadKeyOption(int i, int i2, int i3, int i4, int i5) {
        this.f155a = new byte[6];
        setTimeOut(i);
        setPinMaxExtraDigit(i2);
        setKeyReturnCondition(i3);
        setEchoLcdStartPosition(i4);
        setEchoLcdMode(i5);
    }

    public int getTimeOut() {
        return this.f155a[0] & 255;
    }

    public void setTimeOut(int i) {
        this.f155a[0] = (byte) i;
    }

    public int getPinMaxExtraDigit() {
        byte[] bArr = this.f155a;
        return ((bArr[2] & 255) << 8) | (bArr[1] & 255);
    }

    public void setPinMaxExtraDigit(int i) {
        byte[] bArr = this.f155a;
        bArr[1] = (byte) i;
        bArr[2] = (byte) (i >> 8);
    }

    public int getKeyReturnCondition() {
        return this.f155a[3] & 255;
    }

    public void setKeyReturnCondition(int i) {
        this.f155a[3] = (byte) i;
    }

    public int getEchoLcdStartPosition() {
        return this.f155a[4] & 255;
    }

    public void setEchoLcdStartPosition(int i) {
        this.f155a[4] = (byte) i;
    }

    public int getEchoLcdMode() {
        return this.f155a[5] & 255;
    }

    public void setEchoLcdMode(int i) {
        this.f155a[5] = (byte) i;
    }

    public byte[] toByteArray() {
        return this.f155a;
    }
}
