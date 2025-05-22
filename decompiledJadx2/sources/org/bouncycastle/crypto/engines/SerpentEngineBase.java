package org.bouncycastle.crypto.engines;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes9.dex */
public abstract class SerpentEngineBase implements BlockCipher {
    protected static final int BLOCK_SIZE = 16;
    static final int PHI = -1640531527;
    static final int ROUNDS = 32;

    /* renamed from: X0 */
    protected int f9420X0;

    /* renamed from: X1 */
    protected int f9421X1;

    /* renamed from: X2 */
    protected int f9422X2;

    /* renamed from: X3 */
    protected int f9423X3;
    protected boolean encrypting;
    protected int[] wKey;

    /* JADX INFO: Access modifiers changed from: protected */
    public static int rotateLeft(int i, int i2) {
        return (i >>> (-i2)) | (i << i2);
    }

    protected static int rotateRight(int i, int i2) {
        return (i << (-i2)) | (i >>> i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: LT */
    public final void m4095LT() {
        int rotateLeft = rotateLeft(this.f9420X0, 13);
        int rotateLeft2 = rotateLeft(this.f9422X2, 3);
        int i = (this.f9421X1 ^ rotateLeft) ^ rotateLeft2;
        int i2 = (this.f9423X3 ^ rotateLeft2) ^ (rotateLeft << 3);
        this.f9421X1 = rotateLeft(i, 1);
        this.f9423X3 = rotateLeft(i2, 7);
        this.f9420X0 = rotateLeft((rotateLeft ^ this.f9421X1) ^ this.f9423X3, 5);
        this.f9422X2 = rotateLeft((this.f9423X3 ^ rotateLeft2) ^ (this.f9421X1 << 7), 22);
    }

    protected abstract void decryptBlock(byte[] bArr, int i, byte[] bArr2, int i2);

    protected abstract void encryptBlock(byte[] bArr, int i, byte[] bArr2, int i2);

    @Override // org.bouncycastle.crypto.BlockCipher
    public String getAlgorithmName() {
        return "Serpent";
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public int getBlockSize() {
        return 16;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib0(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i2 ^ i;
        int i7 = (i5 | i6) ^ i4;
        int i8 = i3 ^ i7;
        this.f9422X2 = i6 ^ i8;
        int i9 = (i6 & i4) ^ i5;
        this.f9421X1 = (this.f9422X2 & i9) ^ i7;
        this.f9423X3 = (i & i7) ^ (this.f9421X1 | i8);
        this.f9420X0 = this.f9423X3 ^ (i9 ^ i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib1(int i, int i2, int i3, int i4) {
        int i5 = i4 ^ i2;
        int i6 = i ^ (i2 & i5);
        int i7 = i5 ^ i6;
        this.f9423X3 = i3 ^ i7;
        int i8 = i2 ^ (i5 & i6);
        int i9 = this.f9423X3;
        this.f9421X1 = i6 ^ (i9 | i8);
        int i10 = ~this.f9421X1;
        int i11 = i8 ^ i9;
        this.f9420X0 = i10 ^ i11;
        this.f9422X2 = (i10 | i11) ^ i7;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib2(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i4;
        int i6 = ~i5;
        int i7 = i ^ i3;
        int i8 = i3 ^ i5;
        this.f9420X0 = (i2 & i8) ^ i7;
        this.f9423X3 = (((i | i6) ^ i4) | i7) ^ i5;
        int i9 = ~i8;
        int i10 = this.f9420X0 | this.f9423X3;
        this.f9421X1 = i9 ^ i10;
        this.f9422X2 = (i9 & i4) ^ (i10 ^ i7);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib3(int i, int i2, int i3, int i4) {
        int i5 = i | i2;
        int i6 = i2 ^ i3;
        int i7 = i ^ (i2 & i6);
        int i8 = i3 ^ i7;
        int i9 = i4 | i7;
        this.f9420X0 = i6 ^ i9;
        int i10 = (i9 | i6) ^ i4;
        this.f9422X2 = i8 ^ i10;
        int i11 = i5 ^ i10;
        int i12 = this.f9420X0;
        this.f9423X3 = i7 ^ (i12 & i11);
        this.f9421X1 = this.f9423X3 ^ (i11 ^ i12);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib4(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ ((i3 | i4) & i);
        int i6 = i3 ^ (i & i5);
        this.f9421X1 = i4 ^ i6;
        int i7 = ~i;
        int i8 = this.f9421X1;
        this.f9423X3 = (i6 & i8) ^ i5;
        int i9 = (i8 | i7) ^ i4;
        this.f9420X0 = this.f9423X3 ^ i9;
        this.f9422X2 = (i7 ^ i8) ^ (i5 & i9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib5(int i, int i2, int i3, int i4) {
        int i5 = ~i3;
        int i6 = (i2 & i5) ^ i4;
        int i7 = i & i6;
        this.f9423X3 = (i2 ^ i5) ^ i7;
        int i8 = this.f9423X3 | i2;
        this.f9421X1 = i6 ^ (i & i8);
        int i9 = i4 | i;
        this.f9420X0 = (i5 ^ i8) ^ i9;
        this.f9422X2 = ((i ^ i3) | i7) ^ (i2 & i9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib6(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i2;
        int i7 = i3 ^ i6;
        int i8 = (i3 | i5) ^ i4;
        this.f9421X1 = i7 ^ i8;
        int i9 = i6 ^ (i7 & i8);
        this.f9423X3 = i8 ^ (i2 | i9);
        int i10 = i2 | this.f9423X3;
        this.f9420X0 = i9 ^ i10;
        this.f9422X2 = (i4 & i5) ^ (i10 ^ i7);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void ib7(int i, int i2, int i3, int i4) {
        int i5 = (i & i2) | i3;
        int i6 = (i | i2) & i4;
        this.f9423X3 = i5 ^ i6;
        int i7 = ~i4;
        int i8 = i2 ^ i6;
        int i9 = this.f9423X3;
        this.f9421X1 = ((i7 ^ i9) | i8) ^ i;
        int i10 = i8 ^ i3;
        int i11 = this.f9421X1;
        this.f9420X0 = i10 ^ (i4 | i11);
        this.f9422X2 = ((i & i9) ^ this.f9420X0) ^ (i5 ^ i11);
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void init(boolean z, CipherParameters cipherParameters) {
        if (cipherParameters instanceof KeyParameter) {
            this.encrypting = z;
            this.wKey = makeWorkingKey(((KeyParameter) cipherParameters).getKey());
            return;
        }
        throw new IllegalArgumentException("invalid parameter passed to " + getAlgorithmName() + " init - " + cipherParameters.getClass().getName());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void inverseLT() {
        int rotateRight = (rotateRight(this.f9422X2, 22) ^ this.f9423X3) ^ (this.f9421X1 << 7);
        int rotateRight2 = rotateRight(this.f9420X0, 5) ^ this.f9421X1;
        int i = this.f9423X3;
        int i2 = rotateRight2 ^ i;
        int rotateRight3 = rotateRight(i, 7);
        int rotateRight4 = rotateRight(this.f9421X1, 1);
        this.f9423X3 = (rotateRight3 ^ rotateRight) ^ (i2 << 3);
        this.f9421X1 = (rotateRight4 ^ i2) ^ rotateRight;
        this.f9422X2 = rotateRight(rotateRight, 3);
        this.f9420X0 = rotateRight(i2, 13);
    }

    protected abstract int[] makeWorkingKey(byte[] bArr);

    @Override // org.bouncycastle.crypto.BlockCipher
    public final int processBlock(byte[] bArr, int i, byte[] bArr2, int i2) {
        if (this.wKey == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (i + 16 > bArr.length) {
            throw new DataLengthException("input buffer too short");
        }
        if (i2 + 16 > bArr2.length) {
            throw new OutputLengthException("output buffer too short");
        }
        if (this.encrypting) {
            encryptBlock(bArr, i, bArr2, i2);
            return 16;
        }
        decryptBlock(bArr, i, bArr2, i2);
        return 16;
    }

    @Override // org.bouncycastle.crypto.BlockCipher
    public void reset() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb0(int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ i5;
        int i7 = i2 ^ i6;
        this.f9423X3 = (i4 & i) ^ i7;
        int i8 = i ^ (i2 & i5);
        this.f9422X2 = (i3 | i8) ^ i7;
        int i9 = this.f9423X3 & (i6 ^ i8);
        this.f9421X1 = (~i6) ^ i9;
        this.f9420X0 = (~i8) ^ i9;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb1(int i, int i2, int i3, int i4) {
        int i5 = (~i) ^ i2;
        int i6 = (i | i5) ^ i3;
        this.f9422X2 = i4 ^ i6;
        int i7 = i2 ^ (i4 | i5);
        int i8 = this.f9422X2 ^ i5;
        this.f9423X3 = (i6 & i7) ^ i8;
        int i9 = i7 ^ i6;
        this.f9421X1 = this.f9423X3 ^ i9;
        this.f9420X0 = i6 ^ (i9 & i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb2(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i2 ^ i4;
        this.f9420X0 = (i3 & i5) ^ i6;
        int i7 = i3 ^ i5;
        int i8 = this.f9420X0;
        int i9 = i2 & (i3 ^ i8);
        this.f9423X3 = i7 ^ i9;
        this.f9422X2 = i ^ ((i9 | i4) & (i8 | i7));
        this.f9421X1 = (this.f9423X3 ^ i6) ^ (this.f9422X2 ^ (i4 | i5));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb3(int i, int i2, int i3, int i4) {
        int i5 = i ^ i2;
        int i6 = i & i3;
        int i7 = i | i4;
        int i8 = i3 ^ i4;
        int i9 = i6 | (i5 & i7);
        this.f9422X2 = i8 ^ i9;
        int i10 = (i7 ^ i2) ^ i9;
        this.f9420X0 = i5 ^ (i8 & i10);
        int i11 = this.f9422X2 & this.f9420X0;
        this.f9421X1 = i10 ^ i11;
        this.f9423X3 = (i2 | i4) ^ (i8 ^ i11);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb4(int i, int i2, int i3, int i4) {
        int i5 = i ^ i4;
        int i6 = i3 ^ (i4 & i5);
        int i7 = i2 | i6;
        this.f9423X3 = i5 ^ i7;
        int i8 = ~i2;
        this.f9420X0 = (i5 | i8) ^ i6;
        int i9 = i8 ^ i5;
        this.f9422X2 = (i7 & i9) ^ (this.f9420X0 & i);
        this.f9421X1 = (i ^ i6) ^ (i9 & this.f9422X2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb5(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i2;
        int i7 = i ^ i4;
        this.f9420X0 = (i3 ^ i5) ^ (i6 | i7);
        int i8 = this.f9420X0;
        int i9 = i4 & i8;
        this.f9421X1 = (i6 ^ i8) ^ i9;
        int i10 = i7 ^ (i8 | i5);
        this.f9422X2 = (i6 | i9) ^ i10;
        this.f9423X3 = (i10 & this.f9421X1) ^ (i2 ^ i9);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb6(int i, int i2, int i3, int i4) {
        int i5 = ~i;
        int i6 = i ^ i4;
        int i7 = i2 ^ i6;
        int i8 = i3 ^ (i5 | i6);
        this.f9421X1 = i2 ^ i8;
        int i9 = (i6 | this.f9421X1) ^ i4;
        this.f9422X2 = (i8 & i9) ^ i7;
        int i10 = i9 ^ i8;
        this.f9420X0 = this.f9422X2 ^ i10;
        this.f9423X3 = (i10 & i7) ^ (~i8);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void sb7(int i, int i2, int i3, int i4) {
        int i5 = i2 ^ i3;
        int i6 = (i3 & i5) ^ i4;
        int i7 = i ^ i6;
        this.f9421X1 = i2 ^ ((i4 | i5) & i7);
        int i8 = this.f9421X1 | i6;
        this.f9423X3 = (i & i7) ^ i5;
        int i9 = i7 ^ i8;
        int i10 = this.f9423X3;
        this.f9422X2 = i6 ^ (i10 & i9);
        this.f9420X0 = (~i9) ^ (i10 & this.f9422X2);
    }
}
