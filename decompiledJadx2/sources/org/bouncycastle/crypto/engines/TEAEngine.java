package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes9.dex */
public class TEAEngine implements BlockCipher {
    private static final int block_size = 8;
    private static final int d_sum = -957401312;
    private static final int delta = -1640531527;
    private static final int rounds = 32;

    /* renamed from: _a */
    private int f9425_a;

    /* renamed from: _b */
    private int f9426_b;

    /* renamed from: _c */
    private int f9427_c;

    /* renamed from: _d */
    private int f9428_d;
    private boolean _forEncryption;
    private boolean _initialised = false;

    private int bytesToInt(byte[] bArr, int i) {
        int i2 = i + 1;
        int i3 = i2 + 1;
        int i4 = (bArr[i] << 24) | ((bArr[i2] & 255) << 16);
        int i5 = i3 + 1;
        return (bArr[i5] & 255) | i4 | ((bArr[i3] & 255) << 8);
    }

    private int decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int bytesToInt = bytesToInt(bArr, i);
        int bytesToInt2 = bytesToInt(bArr, i + 4);
        int i3 = d_sum;
        for (int i4 = 0; i4 != 32; i4++) {
            bytesToInt2 -= (((bytesToInt << 4) + this.f9427_c) ^ (bytesToInt + i3)) ^ ((bytesToInt >>> 5) + this.f9428_d);
            bytesToInt -= (((bytesToInt2 << 4) + this.f9425_a) ^ (bytesToInt2 + i3)) ^ ((bytesToInt2 >>> 5) + this.f9426_b);
            i3 += 1640531527;
        }
        unpackInt(bytesToInt, bArr2, i2);
        unpackInt(bytesToInt2, bArr2, i2 + 4);
        return 8;
    }

    private int encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        int bytesToInt = bytesToInt(bArr, i);
        int bytesToInt2 = bytesToInt(bArr, i + 4);
        int i3 = 0;
        for (int i4 = 0; i4 != 32; i4++) {
            i3 -= 1640531527;
            bytesToInt += (((bytesToInt2 << 4) + this.f9425_a) ^ (bytesToInt2 + i3)) ^ ((bytesToInt2 >>> 5) + this.f9426_b);
            bytesToInt2 += (((bytesToInt << 4) + this.f9427_c) ^ (bytesToInt + i3)) ^ ((bytesToInt >>> 5) + this.f9428_d);
        }
        unpackInt(bytesToInt, bArr2, i2);
        unpackInt(bytesToInt2, bArr2, i2 + 4);
        return 8;
    }

    private void setKey(byte[] bArr) {
        if (bArr.length != 16) {
            throw new IllegalArgumentException("Key size must be 128 bits.");
        }
        this.f9425_a = bytesToInt(bArr, 0);
        this.f9426_b = bytesToInt(bArr, 4);
        this.f9427_c = bytesToInt(bArr, 8);
        this.f9428_d = bytesToInt(bArr, 12);
    }

    private void unpackInt(int i, byte[] bArr, int i2) {
        int i3 = i2 + 1;
        bArr[i2] = (byte) (i >>> 24);
        int i4 = i3 + 1;
        bArr[i3] = (byte) (i >>> 16);
        bArr[i4] = (byte) (i >>> 8);
        bArr[i4 + 1] = (byte) i;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "TEA";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this._forEncryption = z;
            this._initialised = true;
            setKey(((KeyParameter) cipherParameters).getKey());
        } else {
            throw new IllegalArgumentException("invalid parameter passed to TEA init - " + cipherParameters.getClass().getName());
        }
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (!this._initialised) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i + 8 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 8 <= bArr2.length) {
            return this._forEncryption ? encryptBlock(bArr, i, bArr2, i2) : decryptBlock(bArr, i, bArr2, i2);
        }
        throw new OutputLengthException("output buffer too short");
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }
}
