package org.bouncycastle.crypto.signers;

import java.math.BigInteger;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.Signer;
import org.bouncycastle.crypto.params.RSAKeyParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes9.dex */
public class X931Signer implements Signer {
    public static final int TRAILER_IMPLICIT = 188;
    public static final int TRAILER_RIPEMD128 = 13004;
    public static final int TRAILER_RIPEMD160 = 12748;
    public static final int TRAILER_SHA1 = 13260;
    public static final int TRAILER_SHA224 = 14540;
    public static final int TRAILER_SHA256 = 13516;
    public static final int TRAILER_SHA384 = 14028;
    public static final int TRAILER_SHA512 = 13772;
    public static final int TRAILER_WHIRLPOOL = 14284;
    private byte[] block;
    private AsymmetricBlockCipher cipher;
    private Digest digest;
    private RSAKeyParameters kParam;
    private int keyBits;
    private int trailer;

    public X931Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest) {
        this(asymmetricBlockCipher, digest, false);
    }

    public X931Signer(AsymmetricBlockCipher asymmetricBlockCipher, Digest digest, boolean z) {
        int intValue;
        this.cipher = asymmetricBlockCipher;
        this.digest = digest;
        if (z) {
            intValue = 188;
        } else {
            Integer trailer = ISOTrailers.getTrailer(digest);
            if (trailer == null) {
                throw new IllegalArgumentException("no valid trailer for digest: " + digest.getAlgorithmName());
            }
            intValue = trailer.intValue();
        }
        this.trailer = intValue;
    }

    private void clearBlock(byte[] bArr) {
        for (int i = 0; i != bArr.length; i++) {
            bArr[i] = 0;
        }
    }

    private void createSignatureBlock(int i) {
        int length;
        int digestSize = this.digest.getDigestSize();
        if (i == 188) {
            byte[] bArr = this.block;
            int length2 = (bArr.length - digestSize) - 1;
            this.digest.doFinal(bArr, length2);
            this.block[r5.length - 1] = PSSSigner.TRAILER_IMPLICIT;
            length = length2;
        } else {
            byte[] bArr2 = this.block;
            length = (bArr2.length - digestSize) - 2;
            this.digest.doFinal(bArr2, length);
            byte[] bArr3 = this.block;
            bArr3[bArr3.length - 2] = (byte) (i >>> 8);
            bArr3[bArr3.length - 1] = (byte) i;
        }
        this.block[0] = 107;
        for (int i2 = length - 2; i2 != 0; i2--) {
            this.block[i2] = -69;
        }
        this.block[length - 1] = -70;
    }

    @Override // org.bouncycastle.crypto.Signer
    public byte[] generateSignature() throws CryptoException {
        createSignatureBlock(this.trailer);
        AsymmetricBlockCipher asymmetricBlockCipher = this.cipher;
        byte[] bArr = this.block;
        BigInteger bigInteger = new BigInteger(1, asymmetricBlockCipher.processBlock(bArr, 0, bArr.length));
        clearBlock(this.block);
        return BigIntegers.asUnsignedByteArray(BigIntegers.getUnsignedByteLength(this.kParam.getModulus()), bigInteger.min(this.kParam.getModulus().subtract(bigInteger)));
    }

    @Override // org.bouncycastle.crypto.Signer
    public void init(boolean z, CipherParameters cipherParameters) {
        this.kParam = (RSAKeyParameters) cipherParameters;
        this.cipher.init(z, this.kParam);
        this.keyBits = this.kParam.getModulus().bitLength();
        this.block = new byte[(this.keyBits + 7) / 8];
        reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void reset() {
        this.digest.reset();
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte b) {
        this.digest.update(b);
    }

    @Override // org.bouncycastle.crypto.Signer
    public void update(byte[] bArr, int i, int i2) {
        this.digest.update(bArr, i, i2);
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x002d, code lost:
    
        if ((r4.intValue() & 15) == 12) goto L9;
     */
    @Override // org.bouncycastle.crypto.Signer
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public boolean verifySignature(byte[] bArr) {
        boolean z = false;
        try {
            this.block = this.cipher.processBlock(bArr, 0, bArr.length);
            BigInteger bigInteger = new BigInteger(1, this.block);
            if ((bigInteger.intValue() & 15) != 12) {
                bigInteger = this.kParam.getModulus().subtract(bigInteger);
            }
            createSignatureBlock(this.trailer);
            byte[] asUnsignedByteArray = BigIntegers.asUnsignedByteArray(this.block.length, bigInteger);
            z = Arrays.constantTimeAreEqual(this.block, asUnsignedByteArray);
            if (this.trailer == 15052 && !z) {
                byte[] bArr2 = this.block;
                bArr2[bArr2.length - 2] = 64;
                z = Arrays.constantTimeAreEqual(bArr2, asUnsignedByteArray);
            }
            clearBlock(this.block);
            clearBlock(asUnsignedByteArray);
        } catch (Exception unused) {
        }
        return z;
    }
}
