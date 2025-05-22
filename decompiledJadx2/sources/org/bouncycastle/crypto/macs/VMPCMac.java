package org.bouncycastle.crypto.macs;

import com.google.common.base.Ascii;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;

/* loaded from: classes9.dex */
public class VMPCMac implements Mac {

    /* renamed from: T */
    private byte[] f9493T;

    /* renamed from: g */
    private byte f9494g;
    private byte[] workingIV;
    private byte[] workingKey;

    /* renamed from: x1 */
    private byte f9497x1;

    /* renamed from: x2 */
    private byte f9498x2;

    /* renamed from: x3 */
    private byte f9499x3;

    /* renamed from: x4 */
    private byte f9500x4;

    /* renamed from: n */
    private byte f9495n = 0;

    /* renamed from: P */
    private byte[] f9492P = null;

    /* renamed from: s */
    private byte f9496s = 0;

    private void initKey(byte[] bArr, byte[] bArr2) {
        this.f9496s = (byte) 0;
        this.f9492P = new byte[256];
        for (int i = 0; i < 256; i++) {
            this.f9492P[i] = (byte) i;
        }
        for (int i2 = 0; i2 < 768; i2++) {
            byte[] bArr3 = this.f9492P;
            int i3 = i2 & 255;
            this.f9496s = bArr3[(this.f9496s + bArr3[i3] + bArr[i2 % bArr.length]) & 255];
            byte b = bArr3[i3];
            byte b2 = this.f9496s;
            bArr3[i3] = bArr3[b2 & 255];
            bArr3[b2 & 255] = b;
        }
        for (int i4 = 0; i4 < 768; i4++) {
            byte[] bArr4 = this.f9492P;
            int i5 = i4 & 255;
            this.f9496s = bArr4[(this.f9496s + bArr4[i5] + bArr2[i4 % bArr2.length]) & 255];
            byte b3 = bArr4[i5];
            byte b4 = this.f9496s;
            bArr4[i5] = bArr4[b4 & 255];
            bArr4[b4 & 255] = b3;
        }
        this.f9495n = (byte) 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        for (int i2 = 1; i2 < 25; i2++) {
            byte[] bArr2 = this.f9492P;
            byte b = this.f9496s;
            byte b2 = this.f9495n;
            this.f9496s = bArr2[(b + bArr2[b2 & 255]) & 255];
            byte b3 = this.f9500x4;
            byte b4 = this.f9499x3;
            this.f9500x4 = bArr2[(b3 + b4 + i2) & 255];
            byte b5 = this.f9498x2;
            this.f9499x3 = bArr2[(b4 + b5 + i2) & 255];
            byte b6 = this.f9497x1;
            this.f9498x2 = bArr2[(b5 + b6 + i2) & 255];
            byte b7 = this.f9496s;
            this.f9497x1 = bArr2[(b6 + b7 + i2) & 255];
            byte[] bArr3 = this.f9493T;
            byte b8 = this.f9494g;
            bArr3[b8 & Ascii.f1926US] = (byte) (bArr3[b8 & Ascii.f1926US] ^ this.f9497x1);
            bArr3[(b8 + 1) & 31] = (byte) (bArr3[(b8 + 1) & 31] ^ this.f9498x2);
            bArr3[(b8 + 2) & 31] = (byte) (bArr3[(b8 + 2) & 31] ^ this.f9499x3);
            bArr3[(b8 + 3) & 31] = (byte) (bArr3[(b8 + 3) & 31] ^ this.f9500x4);
            this.f9494g = (byte) ((b8 + 4) & 31);
            byte b9 = bArr2[b2 & 255];
            bArr2[b2 & 255] = bArr2[b7 & 255];
            bArr2[b7 & 255] = b9;
            this.f9495n = (byte) ((b2 + 1) & 255);
        }
        for (int i3 = 0; i3 < 768; i3++) {
            byte[] bArr4 = this.f9492P;
            int i4 = i3 & 255;
            this.f9496s = bArr4[(this.f9496s + bArr4[i4] + this.f9493T[i3 & 31]) & 255];
            byte b10 = bArr4[i4];
            byte b11 = this.f9496s;
            bArr4[i4] = bArr4[b11 & 255];
            bArr4[b11 & 255] = b10;
        }
        byte[] bArr5 = new byte[20];
        for (int i5 = 0; i5 < 20; i5++) {
            byte[] bArr6 = this.f9492P;
            int i6 = i5 & 255;
            this.f9496s = bArr6[(this.f9496s + bArr6[i6]) & 255];
            byte b12 = this.f9496s;
            bArr5[i5] = bArr6[(bArr6[bArr6[b12 & 255] & 255] + 1) & 255];
            byte b13 = bArr6[i6];
            bArr6[i6] = bArr6[b12 & 255];
            bArr6[b12 & 255] = b13;
        }
        System.arraycopy(bArr5, 0, bArr, i, bArr5.length);
        reset();
        return bArr5.length;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "VMPC-MAC";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 20;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof ParametersWithIV)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include an IV");
        }
        ParametersWithIV parametersWithIV = (ParametersWithIV) cipherParameters;
        KeyParameter keyParameter = (KeyParameter) parametersWithIV.getParameters();
        if (!(parametersWithIV.getParameters() instanceof KeyParameter)) {
            throw new IllegalArgumentException("VMPC-MAC Init parameters must include a key");
        }
        this.workingIV = parametersWithIV.getIV();
        byte[] bArr = this.workingIV;
        if (bArr == null || bArr.length < 1 || bArr.length > 768) {
            throw new IllegalArgumentException("VMPC-MAC requires 1 to 768 bytes of IV");
        }
        this.workingKey = keyParameter.getKey();
        reset();
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        initKey(this.workingKey, this.workingIV);
        this.f9495n = (byte) 0;
        this.f9500x4 = (byte) 0;
        this.f9499x3 = (byte) 0;
        this.f9498x2 = (byte) 0;
        this.f9497x1 = (byte) 0;
        this.f9494g = (byte) 0;
        this.f9493T = new byte[32];
        for (int i = 0; i < 32; i++) {
            this.f9493T[i] = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        byte[] bArr = this.f9492P;
        byte b2 = this.f9496s;
        byte b3 = this.f9495n;
        this.f9496s = bArr[(b2 + bArr[b3 & 255]) & 255];
        byte b4 = this.f9496s;
        byte b5 = (byte) (b ^ bArr[(bArr[bArr[b4 & 255] & 255] + 1) & 255]);
        byte b6 = this.f9500x4;
        byte b7 = this.f9499x3;
        this.f9500x4 = bArr[(b6 + b7) & 255];
        byte b8 = this.f9498x2;
        this.f9499x3 = bArr[(b7 + b8) & 255];
        byte b9 = this.f9497x1;
        this.f9498x2 = bArr[(b8 + b9) & 255];
        this.f9497x1 = bArr[(b9 + b4 + b5) & 255];
        byte[] bArr2 = this.f9493T;
        byte b10 = this.f9494g;
        bArr2[b10 & Ascii.f1926US] = (byte) (bArr2[b10 & Ascii.f1926US] ^ this.f9497x1);
        bArr2[(b10 + 1) & 31] = (byte) (bArr2[(b10 + 1) & 31] ^ this.f9498x2);
        bArr2[(b10 + 2) & 31] = (byte) (bArr2[(b10 + 2) & 31] ^ this.f9499x3);
        bArr2[(b10 + 3) & 31] = (byte) (bArr2[(b10 + 3) & 31] ^ this.f9500x4);
        this.f9494g = (byte) ((b10 + 4) & 31);
        byte b11 = bArr[b3 & 255];
        bArr[b3 & 255] = bArr[b4 & 255];
        bArr[b4 & 255] = b11;
        this.f9495n = (byte) ((b3 + 1) & 255);
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        if (i + i2 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        for (int i3 = 0; i3 < i2; i3++) {
            update(bArr[i + i3]);
        }
    }
}
