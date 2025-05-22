package org.bouncycastle.crypto.macs;

import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.Mac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public class SipHash implements Mac {

    /* renamed from: c */
    protected final int f9483c;

    /* renamed from: d */
    protected final int f9484d;

    /* renamed from: k0 */
    protected long f9485k0;

    /* renamed from: k1 */
    protected long f9486k1;

    /* renamed from: m */
    protected long f9487m;

    /* renamed from: v0 */
    protected long f9488v0;

    /* renamed from: v1 */
    protected long f9489v1;

    /* renamed from: v2 */
    protected long f9490v2;

    /* renamed from: v3 */
    protected long f9491v3;
    protected int wordCount;
    protected int wordPos;

    public SipHash() {
        this.f9487m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f9483c = 2;
        this.f9484d = 4;
    }

    public SipHash(int i, int i2) {
        this.f9487m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
        this.f9483c = i;
        this.f9484d = i2;
    }

    protected static long rotateLeft(long j, int i) {
        return (j >>> (-i)) | (j << i);
    }

    protected void applySipRounds(int i) {
        long j = this.f9488v0;
        long j2 = this.f9489v1;
        long j3 = this.f9490v2;
        long j4 = this.f9491v3;
        for (int i2 = 0; i2 < i; i2++) {
            long j5 = j + j2;
            long j6 = j3 + j4;
            long rotateLeft = rotateLeft(j2, 13) ^ j5;
            long rotateLeft2 = rotateLeft(j4, 16) ^ j6;
            long j7 = j6 + rotateLeft;
            j = rotateLeft(j5, 32) + rotateLeft2;
            j2 = rotateLeft(rotateLeft, 17) ^ j7;
            j4 = rotateLeft(rotateLeft2, 21) ^ j;
            j3 = rotateLeft(j7, 32);
        }
        this.f9488v0 = j;
        this.f9489v1 = j2;
        this.f9490v2 = j3;
        this.f9491v3 = j4;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int doFinal(byte[] bArr, int i) throws DataLengthException, IllegalStateException {
        Pack.longToLittleEndian(doFinal(), bArr, i);
        return 8;
    }

    public long doFinal() throws DataLengthException, IllegalStateException {
        this.f9487m = this.f9487m >>> ((7 - this.wordPos) << 3);
        this.f9487m >>>= 8;
        this.f9487m |= (((this.wordCount << 3) + r2) & 255) << 56;
        processMessageWord();
        this.f9490v2 ^= 255;
        applySipRounds(this.f9484d);
        long j = ((this.f9488v0 ^ this.f9489v1) ^ this.f9490v2) ^ this.f9491v3;
        reset();
        return j;
    }

    @Override // org.bouncycastle.crypto.Mac
    public String getAlgorithmName() {
        return "SipHash-" + this.f9483c + "-" + this.f9484d;
    }

    @Override // org.bouncycastle.crypto.Mac
    public int getMacSize() {
        return 8;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void init(CipherParameters cipherParameters) throws IllegalArgumentException {
        if (!(cipherParameters instanceof KeyParameter)) {
            throw new IllegalArgumentException("'params' must be an instance of KeyParameter");
        }
        byte[] key = ((KeyParameter) cipherParameters).getKey();
        if (key.length != 16) {
            throw new IllegalArgumentException("'params' must be a 128-bit key");
        }
        this.f9485k0 = Pack.littleEndianToLong(key, 0);
        this.f9486k1 = Pack.littleEndianToLong(key, 8);
        reset();
    }

    protected void processMessageWord() {
        this.wordCount++;
        this.f9491v3 ^= this.f9487m;
        applySipRounds(this.f9483c);
        this.f9488v0 ^= this.f9487m;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void reset() {
        long j = this.f9485k0;
        this.f9488v0 = 8317987319222330741L ^ j;
        long j2 = this.f9486k1;
        this.f9489v1 = 7237128888997146477L ^ j2;
        this.f9490v2 = j ^ 7816392313619706465L;
        this.f9491v3 = 8387220255154660723L ^ j2;
        this.f9487m = 0L;
        this.wordPos = 0;
        this.wordCount = 0;
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte b) throws IllegalStateException {
        this.f9487m >>>= 8;
        this.f9487m |= (b & 255) << 56;
        int i = this.wordPos + 1;
        this.wordPos = i;
        if (i == 8) {
            processMessageWord();
            this.wordPos = 0;
        }
    }

    @Override // org.bouncycastle.crypto.Mac
    public void update(byte[] bArr, int i, int i2) throws DataLengthException, IllegalStateException {
        int i3 = i2 & (-8);
        int i4 = this.wordPos;
        int i5 = 0;
        if (i4 == 0) {
            while (i5 < i3) {
                this.f9487m = Pack.littleEndianToLong(bArr, i + i5);
                processMessageWord();
                i5 += 8;
            }
            while (i5 < i2) {
                this.f9487m >>>= 8;
                this.f9487m |= (bArr[i + i5] & 255) << 56;
                i5++;
            }
            this.wordPos = i2 - i3;
            return;
        }
        int i6 = i4 << 3;
        int i7 = 0;
        while (i7 < i3) {
            long littleEndianToLong = Pack.littleEndianToLong(bArr, i + i7);
            this.f9487m = (this.f9487m >>> (-i6)) | (littleEndianToLong << i6);
            processMessageWord();
            this.f9487m = littleEndianToLong;
            i7 += 8;
        }
        while (i7 < i2) {
            this.f9487m >>>= 8;
            this.f9487m |= (bArr[i + i7] & 255) << 56;
            int i8 = this.wordPos + 1;
            this.wordPos = i8;
            if (i8 == 8) {
                processMessageWord();
                this.wordPos = 0;
            }
            i7++;
        }
    }
}
