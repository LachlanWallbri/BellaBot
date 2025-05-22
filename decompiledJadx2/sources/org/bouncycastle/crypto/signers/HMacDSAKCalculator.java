package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.macs.HMac;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes9.dex */
public class HMacDSAKCalculator implements DSAKCalculator {
    private static final BigInteger ZERO = BigInteger.valueOf(0);

    /* renamed from: K */
    private final byte[] f9631K;

    /* renamed from: V */
    private final byte[] f9632V;
    private final HMac hMac;

    /* renamed from: n */
    private BigInteger f9633n;

    public HMacDSAKCalculator(Digest digest) {
        this.hMac = new HMac(digest);
        this.f9632V = new byte[this.hMac.getMacSize()];
        this.f9631K = new byte[this.hMac.getMacSize()];
    }

    private BigInteger bitsToInt(byte[] bArr) {
        BigInteger bigInteger = new BigInteger(1, bArr);
        return bArr.length * 8 > this.f9633n.bitLength() ? bigInteger.shiftRight((bArr.length * 8) - this.f9633n.bitLength()) : bigInteger;
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public void init(BigInteger bigInteger, BigInteger bigInteger2, byte[] bArr) {
        this.f9633n = bigInteger;
        Arrays.fill(this.f9632V, (byte) 1);
        Arrays.fill(this.f9631K, (byte) 0);
        int unsignedByteLength = BigIntegers.getUnsignedByteLength(bigInteger);
        byte[] bArr2 = new byte[unsignedByteLength];
        byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(bigInteger2);
        System.arraycopy(asUnsignedByteArray, 0, bArr2, bArr2.length - asUnsignedByteArray.length, asUnsignedByteArray.length);
        byte[] bArr3 = new byte[unsignedByteLength];
        BigInteger bitsToInt = bitsToInt(bArr);
        if (bitsToInt.compareTo(bigInteger) >= 0) {
            bitsToInt = bitsToInt.subtract(bigInteger);
        }
        byte[] asUnsignedByteArray2 = BigIntegers.asUnsignedByteArray(bitsToInt);
        System.arraycopy(asUnsignedByteArray2, 0, bArr3, bArr3.length - asUnsignedByteArray2.length, asUnsignedByteArray2.length);
        this.hMac.init(new KeyParameter(this.f9631K));
        HMac hMac = this.hMac;
        byte[] bArr4 = this.f9632V;
        hMac.update(bArr4, 0, bArr4.length);
        this.hMac.update((byte) 0);
        this.hMac.update(bArr2, 0, bArr2.length);
        this.hMac.update(bArr3, 0, bArr3.length);
        this.hMac.doFinal(this.f9631K, 0);
        this.hMac.init(new KeyParameter(this.f9631K));
        HMac hMac2 = this.hMac;
        byte[] bArr5 = this.f9632V;
        hMac2.update(bArr5, 0, bArr5.length);
        this.hMac.doFinal(this.f9632V, 0);
        HMac hMac3 = this.hMac;
        byte[] bArr6 = this.f9632V;
        hMac3.update(bArr6, 0, bArr6.length);
        this.hMac.update((byte) 1);
        this.hMac.update(bArr2, 0, bArr2.length);
        this.hMac.update(bArr3, 0, bArr3.length);
        this.hMac.doFinal(this.f9631K, 0);
        this.hMac.init(new KeyParameter(this.f9631K));
        HMac hMac4 = this.hMac;
        byte[] bArr7 = this.f9632V;
        hMac4.update(bArr7, 0, bArr7.length);
        this.hMac.doFinal(this.f9632V, 0);
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public void init(BigInteger bigInteger, SecureRandom secureRandom) {
        throw new IllegalStateException("Operation not supported");
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public boolean isDeterministic() {
        return true;
    }

    @Override // org.bouncycastle.crypto.signers.DSAKCalculator
    public BigInteger nextK() {
        byte[] bArr = new byte[BigIntegers.getUnsignedByteLength(this.f9633n)];
        while (true) {
            int i = 0;
            while (i < bArr.length) {
                HMac hMac = this.hMac;
                byte[] bArr2 = this.f9632V;
                hMac.update(bArr2, 0, bArr2.length);
                this.hMac.doFinal(this.f9632V, 0);
                int min = Math.min(bArr.length - i, this.f9632V.length);
                System.arraycopy(this.f9632V, 0, bArr, i, min);
                i += min;
            }
            BigInteger bitsToInt = bitsToInt(bArr);
            if (bitsToInt.compareTo(ZERO) > 0 && bitsToInt.compareTo(this.f9633n) < 0) {
                return bitsToInt;
            }
            HMac hMac2 = this.hMac;
            byte[] bArr3 = this.f9632V;
            hMac2.update(bArr3, 0, bArr3.length);
            this.hMac.update((byte) 0);
            this.hMac.doFinal(this.f9631K, 0);
            this.hMac.init(new KeyParameter(this.f9631K));
            HMac hMac3 = this.hMac;
            byte[] bArr4 = this.f9632V;
            hMac3.update(bArr4, 0, bArr4.length);
            this.hMac.doFinal(this.f9632V, 0);
        }
    }
}
