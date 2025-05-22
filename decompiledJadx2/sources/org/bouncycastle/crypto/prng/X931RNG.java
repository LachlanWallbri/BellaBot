package org.bouncycastle.crypto.prng;

import org.bouncycastle.crypto.BlockCipher;

/* loaded from: classes9.dex */
public class X931RNG {
    private static final int BLOCK128_MAX_BITS_REQUEST = 262144;
    private static final long BLOCK128_RESEED_MAX = 8388608;
    private static final int BLOCK64_MAX_BITS_REQUEST = 4096;
    private static final long BLOCK64_RESEED_MAX = 32768;

    /* renamed from: DT */
    private final byte[] f9617DT;

    /* renamed from: I */
    private final byte[] f9618I;

    /* renamed from: R */
    private final byte[] f9619R;

    /* renamed from: V */
    private byte[] f9620V;
    private final BlockCipher engine;
    private final EntropySource entropySource;
    private long reseedCounter = 1;

    public X931RNG(BlockCipher blockCipher, byte[] bArr, EntropySource entropySource) {
        this.engine = blockCipher;
        this.entropySource = entropySource;
        this.f9617DT = new byte[blockCipher.getBlockSize()];
        byte[] bArr2 = this.f9617DT;
        System.arraycopy(bArr, 0, bArr2, 0, bArr2.length);
        this.f9618I = new byte[blockCipher.getBlockSize()];
        this.f9619R = new byte[blockCipher.getBlockSize()];
    }

    private void increment(byte[] bArr) {
        for (int length = bArr.length - 1; length >= 0; length--) {
            byte b = (byte) (bArr[length] + 1);
            bArr[length] = b;
            if (b != 0) {
                return;
            }
        }
    }

    private static boolean isTooLarge(byte[] bArr, int i) {
        return bArr != null && bArr.length > i;
    }

    private void process(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = (byte) (bArr2[i] ^ bArr3[i]);
        }
        this.engine.processBlock(bArr, 0, bArr, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int generate(byte[] bArr, boolean z) {
        if (this.f9619R.length == 8) {
            if (this.reseedCounter > 32768) {
                return -1;
            }
            if (isTooLarge(bArr, 512)) {
                throw new IllegalArgumentException("Number of bits per request limited to 4096");
            }
        } else {
            if (this.reseedCounter > BLOCK128_RESEED_MAX) {
                return -1;
            }
            if (isTooLarge(bArr, 32768)) {
                throw new IllegalArgumentException("Number of bits per request limited to 262144");
            }
        }
        if (z || this.f9620V == null) {
            this.f9620V = this.entropySource.getEntropy();
            if (this.f9620V.length != this.engine.getBlockSize()) {
                throw new IllegalStateException("Insufficient entropy returned");
            }
        }
        int length = bArr.length / this.f9619R.length;
        for (int i = 0; i < length; i++) {
            this.engine.processBlock(this.f9617DT, 0, this.f9618I, 0);
            process(this.f9619R, this.f9618I, this.f9620V);
            process(this.f9620V, this.f9619R, this.f9618I);
            byte[] bArr2 = this.f9619R;
            System.arraycopy(bArr2, 0, bArr, bArr2.length * i, bArr2.length);
            increment(this.f9617DT);
        }
        int length2 = bArr.length - (this.f9619R.length * length);
        if (length2 > 0) {
            this.engine.processBlock(this.f9617DT, 0, this.f9618I, 0);
            process(this.f9619R, this.f9618I, this.f9620V);
            process(this.f9620V, this.f9619R, this.f9618I);
            byte[] bArr3 = this.f9619R;
            System.arraycopy(bArr3, 0, bArr, length * bArr3.length, length2);
            increment(this.f9617DT);
        }
        this.reseedCounter++;
        return bArr.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public EntropySource getEntropySource() {
        return this.entropySource;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reseed() {
        this.f9620V = this.entropySource.getEntropy();
        if (this.f9620V.length != this.engine.getBlockSize()) {
            throw new IllegalStateException("Insufficient entropy returned");
        }
        this.reseedCounter = 1L;
    }
}
