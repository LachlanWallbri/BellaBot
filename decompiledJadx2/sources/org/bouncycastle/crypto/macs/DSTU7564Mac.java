package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.digests.DSTU7564Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public class DSTU7564Mac implements Mac {
    private static final int BITS_IN_BYTE = 8;
    private DSTU7564Digest engine;
    private long inputLength;
    private int macSize;
    private byte[] paddedKey = null;
    private byte[] invertedKey = null;

    public DSTU7564Mac(int i) {
        this.engine = new DSTU7564Digest(i);
        this.macSize = i / 8;
    }

    private void pad() {
        int byteLength = this.engine.getByteLength() - ((int) (this.inputLength % this.engine.getByteLength()));
        if (byteLength < 13) {
            byteLength += this.engine.getByteLength();
        }
        byte[] bArr = new byte[byteLength];
        bArr[0] = Byte.MIN_VALUE;
        Pack.longToLittleEndian(this.inputLength * 8, bArr, bArr.length - 12);
        this.engine.update(bArr, 0, bArr.length);
    }

    private byte[] padKey(byte[] bArr) {
        int length = (((bArr.length + this.engine.getByteLength()) - 1) / this.engine.getByteLength()) * this.engine.getByteLength();
        if (length - bArr.length < 13) {
            length += this.engine.getByteLength();
        }
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        bArr2[bArr.length] = Byte.MIN_VALUE;
        Pack.intToLittleEndian(bArr.length * 8, bArr2, bArr2.length - 12);
        return bArr2;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        if (this.paddedKey == null) {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
        if (bArr.length - i < this.macSize) {
            throw new OutputLengthException("Output buffer too short");
        }
        pad();
        DSTU7564Digest dSTU7564Digest = this.engine;
        byte[] bArr2 = this.invertedKey;
        dSTU7564Digest.update(bArr2, 0, bArr2.length);
        this.inputLength = 0L;
        int doFinal = this.engine.doFinal(bArr, i);
        reset();
        return doFinal;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "DSTU7564Mac";
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return this.macSize;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        this.paddedKey = null;
        reset();
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("Bad parameter passed");
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        this.invertedKey = new byte[key.length];
        this.paddedKey = padKey(key);
        int i = 0;
        while (true) {
            byte[] bArr = this.invertedKey;
            if (i >= bArr.length) {
                DSTU7564Digest dSTU7564Digest = this.engine;
                byte[] bArr2 = this.paddedKey;
                dSTU7564Digest.update(bArr2, 0, bArr2.length);
                return;
            }
            bArr[i] = (byte) (~key[i]);
            i++;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        this.inputLength = 0L;
        this.engine.reset();
        byte[] bArr = this.paddedKey;
        if (bArr != null) {
            this.engine.update(bArr, 0, bArr.length);
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        this.engine.update(b);
        this.inputLength++;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        if (bArr.length - i < i2) {
            throw new DataLengthException("Input buffer too short");
        }
        if (this.paddedKey != null) {
            this.engine.update(bArr, i, i2);
            this.inputLength += i2;
        } else {
            throw new IllegalStateException(getAlgorithmName() + " not initialised");
        }
    }
}
