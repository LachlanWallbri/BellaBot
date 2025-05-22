package org.bouncycastle.crypto.tls;

import java.io.IOException;
import java.security.SecureRandom;
import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.ParametersWithIV;
import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class TlsBlockCipher implements TlsCipher {
    protected TlsContext context;
    protected BlockCipher decryptCipher;
    protected BlockCipher encryptCipher;
    protected boolean encryptThenMAC;
    protected byte[] randomData = new byte[256];
    protected TlsMac readMac;
    protected boolean useExplicitIV;
    protected TlsMac writeMac;

    public TlsBlockCipher(TlsContext tlsContext, BlockCipher blockCipher, BlockCipher blockCipher2, Digest digest, Digest digest2, int i) throws IOException {
        byte[] copyOfRange;
        byte[] copyOfRange2;
        ParametersWithIV parametersWithIV;
        ParametersWithIV parametersWithIV2;
        this.context = tlsContext;
        tlsContext.getNonceRandomGenerator().nextBytes(this.randomData);
        this.useExplicitIV = TlsUtils.isTLSv11(tlsContext);
        this.encryptThenMAC = tlsContext.getSecurityParameters().encryptThenMAC;
        int digestSize = (i * 2) + digest.getDigestSize() + digest2.getDigestSize();
        int blockSize = this.useExplicitIV ? digestSize : digestSize + blockCipher.getBlockSize() + blockCipher2.getBlockSize();
        byte[] calculateKeyBlock = TlsUtils.calculateKeyBlock(tlsContext, blockSize);
        TlsMac tlsMac = new TlsMac(tlsContext, digest, calculateKeyBlock, 0, digest.getDigestSize());
        int digestSize2 = digest.getDigestSize() + 0;
        TlsMac tlsMac2 = new TlsMac(tlsContext, digest2, calculateKeyBlock, digestSize2, digest2.getDigestSize());
        int digestSize3 = digestSize2 + digest2.getDigestSize();
        KeyParameter keyParameter = new KeyParameter(calculateKeyBlock, digestSize3, i);
        int i2 = digestSize3 + i;
        KeyParameter keyParameter2 = new KeyParameter(calculateKeyBlock, i2, i);
        int i3 = i2 + i;
        if (this.useExplicitIV) {
            copyOfRange = new byte[blockCipher.getBlockSize()];
            copyOfRange2 = new byte[blockCipher2.getBlockSize()];
        } else {
            copyOfRange = Arrays.copyOfRange(calculateKeyBlock, i3, blockCipher.getBlockSize() + i3);
            int blockSize2 = i3 + blockCipher.getBlockSize();
            copyOfRange2 = Arrays.copyOfRange(calculateKeyBlock, blockSize2, blockCipher2.getBlockSize() + blockSize2);
            i3 = blockSize2 + blockCipher2.getBlockSize();
        }
        if (i3 != blockSize) {
            throw new TlsFatalAlert((short) 80);
        }
        if (tlsContext.isServer()) {
            this.writeMac = tlsMac2;
            this.readMac = tlsMac;
            this.encryptCipher = blockCipher2;
            this.decryptCipher = blockCipher;
            parametersWithIV = new ParametersWithIV(keyParameter2, copyOfRange2);
            parametersWithIV2 = new ParametersWithIV(keyParameter, copyOfRange);
        } else {
            this.writeMac = tlsMac;
            this.readMac = tlsMac2;
            this.encryptCipher = blockCipher;
            this.decryptCipher = blockCipher2;
            parametersWithIV = new ParametersWithIV(keyParameter, copyOfRange);
            parametersWithIV2 = new ParametersWithIV(keyParameter2, copyOfRange2);
        }
        this.encryptCipher.init(true, parametersWithIV);
        this.decryptCipher.init(false, parametersWithIV2);
    }

    protected int checkPaddingConstantTime(byte[] bArr, int i, int i2, int i3, int i4) {
        byte b;
        int i5;
        int i6 = i + i2;
        byte b2 = bArr[i6 - 1];
        int i7 = (b2 & 255) + 1;
        if ((!TlsUtils.isSSL(this.context) || i7 <= i3) && i4 + i7 <= i2) {
            int i8 = i6 - i7;
            b = 0;
            while (true) {
                int i9 = i8 + 1;
                b = (byte) (b | (bArr[i8] ^ b2));
                if (i9 >= i6) {
                    break;
                }
                i8 = i9;
            }
            i5 = i7;
            if (b != 0) {
                i7 = 0;
            }
        } else {
            i5 = 0;
            b = 0;
            i7 = 0;
        }
        byte[] bArr2 = this.randomData;
        while (i5 < 256) {
            b = (byte) ((bArr2[i5] ^ b2) | b);
            i5++;
        }
        bArr2[0] = (byte) (bArr2[0] ^ b);
        return i7;
    }

    protected int chooseExtraPadBlocks(SecureRandom secureRandom, int i) {
        return Math.min(lowestBitSet(secureRandom.nextInt()), i);
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public byte[] decodeCiphertext(long j, short s, byte[] bArr, int i, int i2) throws IOException {
        int i3;
        byte[] bArr2;
        int i4 = i;
        int blockSize = this.decryptCipher.getBlockSize();
        int size = this.readMac.getSize();
        int max = this.encryptThenMAC ? blockSize + size : Math.max(blockSize, size + 1);
        if (this.useExplicitIV) {
            max += blockSize;
        }
        if (i2 < max) {
            throw new TlsFatalAlert((short) 50);
        }
        int i5 = this.encryptThenMAC ? i2 - size : i2;
        if (i5 % blockSize != 0) {
            throw new TlsFatalAlert((short) 21);
        }
        if (this.encryptThenMAC) {
            int i6 = i4 + i2;
            if (!Arrays.constantTimeAreEqual(this.readMac.calculateMac(j, s, bArr, i, i2 - size), Arrays.copyOfRange(bArr, i6 - size, i6))) {
                throw new TlsFatalAlert((short) 20);
            }
        }
        if (this.useExplicitIV) {
            this.decryptCipher.init(false, new ParametersWithIV(null, bArr, i4, blockSize));
            i4 += blockSize;
            i5 -= blockSize;
        }
        int i7 = i4;
        int i8 = i5;
        for (int i9 = 0; i9 < i8; i9 += blockSize) {
            int i10 = i7 + i9;
            this.decryptCipher.processBlock(bArr, i10, bArr, i10);
        }
        int checkPaddingConstantTime = checkPaddingConstantTime(bArr, i7, i8, blockSize, this.encryptThenMAC ? 0 : size);
        boolean z = checkPaddingConstantTime == 0;
        int i11 = i8 - checkPaddingConstantTime;
        if (this.encryptThenMAC) {
            i3 = i7;
            bArr2 = bArr;
        } else {
            i11 -= size;
            int i12 = i7 + i11;
            i3 = i7;
            bArr2 = bArr;
            z |= !Arrays.constantTimeAreEqual(this.readMac.calculateMacConstantTime(j, s, bArr, i7, i11, i8 - size, this.randomData), Arrays.copyOfRange(bArr, i12, i12 + size));
        }
        if (z) {
            throw new TlsFatalAlert((short) 20);
        }
        return Arrays.copyOfRange(bArr2, i3, i3 + i11);
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public byte[] encodePlaintext(long j, short s, byte[] bArr, int i, int i2) {
        byte[] bArr2;
        int i3;
        int i4;
        int blockSize = this.encryptCipher.getBlockSize();
        int size = this.writeMac.getSize();
        ProtocolVersion serverVersion = this.context.getServerVersion();
        int i5 = (blockSize - 1) - ((!this.encryptThenMAC ? i2 + size : i2) % blockSize);
        if ((this.encryptThenMAC || !this.context.getSecurityParameters().truncatedHMac) && !serverVersion.isDTLS() && !serverVersion.isSSL()) {
            i5 += chooseExtraPadBlocks(this.context.getSecureRandom(), (255 - i5) / blockSize) * blockSize;
        }
        int i6 = i5;
        int i7 = size + i2 + i6 + 1;
        if (this.useExplicitIV) {
            i7 += blockSize;
        }
        byte[] bArr3 = new byte[i7];
        if (this.useExplicitIV) {
            byte[] bArr4 = new byte[blockSize];
            this.context.getNonceRandomGenerator().nextBytes(bArr4);
            this.encryptCipher.init(true, new ParametersWithIV(null, bArr4));
            System.arraycopy(bArr4, 0, bArr3, 0, blockSize);
            bArr2 = bArr;
            i3 = i;
            i4 = blockSize + 0;
        } else {
            bArr2 = bArr;
            i3 = i;
            i4 = 0;
        }
        System.arraycopy(bArr2, i3, bArr3, i4, i2);
        int i8 = i4 + i2;
        if (!this.encryptThenMAC) {
            byte[] calculateMac = this.writeMac.calculateMac(j, s, bArr, i, i2);
            System.arraycopy(calculateMac, 0, bArr3, i8, calculateMac.length);
            i8 += calculateMac.length;
        }
        int i9 = i8;
        int i10 = 0;
        while (i10 <= i6) {
            bArr3[i9] = (byte) i6;
            i10++;
            i9++;
        }
        while (i4 < i9) {
            this.encryptCipher.processBlock(bArr3, i4, bArr3, i4);
            i4 += blockSize;
        }
        if (!this.encryptThenMAC) {
            return bArr3;
        }
        byte[] calculateMac2 = this.writeMac.calculateMac(j, s, bArr3, 0, i9);
        System.arraycopy(calculateMac2, 0, bArr3, i9, calculateMac2.length);
        int length = calculateMac2.length;
        return bArr3;
    }

    @Override // org.bouncycastle.crypto.tls.TlsCipher
    public int getPlaintextLimit(int i) {
        int i2;
        int blockSize = this.encryptCipher.getBlockSize();
        int size = this.writeMac.getSize();
        if (this.useExplicitIV) {
            i -= blockSize;
        }
        if (this.encryptThenMAC) {
            int i3 = i - size;
            i2 = i3 - (i3 % blockSize);
        } else {
            i2 = (i - (i % blockSize)) - size;
        }
        return i2 - 1;
    }

    public TlsMac getReadMac() {
        return this.readMac;
    }

    public TlsMac getWriteMac() {
        return this.writeMac;
    }

    protected int lowestBitSet(int i) {
        if (i == 0) {
            return 32;
        }
        int i2 = 0;
        while ((i & 1) == 0) {
            i2++;
            i >>= 1;
        }
        return i2;
    }
}
