package org.bouncycastle.crypto.generators;

import androidx.core.view.InputDeviceCompat;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.DerivationParameters;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.DigestDerivationFunction;
import org.bouncycastle.crypto.OutputLengthException;
import org.bouncycastle.crypto.params.ISO18033KDFParameters;
import org.bouncycastle.crypto.params.KDFParameters;
import org.bouncycastle.util.Pack;

/* loaded from: classes9.dex */
public class BaseKDFBytesGenerator implements DigestDerivationFunction {
    private int counterStart;
    private Digest digest;

    /* renamed from: iv */
    private byte[] f9448iv;
    private byte[] shared;

    /* JADX INFO: Access modifiers changed from: protected */
    public BaseKDFBytesGenerator(int i, Digest digest) {
        this.counterStart = i;
        this.digest = digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public int generateBytes(byte[] bArr, int i, int i2) throws DataLengthException, IllegalArgumentException {
        if (bArr.length - i2 < i) {
            throw new OutputLengthException("output buffer too small");
        }
        long j = i2;
        int digestSize = this.digest.getDigestSize();
        if (j > TarConstants.MAXSIZE) {
            throw new IllegalArgumentException("Output length too large");
        }
        long j2 = digestSize;
        int i3 = (int) (((j + j2) - 1) / j2);
        byte[] bArr2 = new byte[this.digest.getDigestSize()];
        byte[] bArr3 = new byte[4];
        Pack.intToBigEndian(this.counterStart, bArr3, 0);
        int i4 = this.counterStart & InputDeviceCompat.SOURCE_ANY;
        int i5 = i;
        for (int i6 = 0; i6 < i3; i6++) {
            Digest digest = this.digest;
            byte[] bArr4 = this.shared;
            digest.update(bArr4, 0, bArr4.length);
            this.digest.update(bArr3, 0, bArr3.length);
            byte[] bArr5 = this.f9448iv;
            if (bArr5 != null) {
                this.digest.update(bArr5, 0, bArr5.length);
            }
            this.digest.doFinal(bArr2, 0);
            if (i2 > digestSize) {
                System.arraycopy(bArr2, 0, bArr, i5, digestSize);
                i5 += digestSize;
                i2 -= digestSize;
            } else {
                System.arraycopy(bArr2, 0, bArr, i5, i2);
            }
            byte b = (byte) (bArr3[3] + 1);
            bArr3[3] = b;
            if (b == 0) {
                i4 += 256;
                Pack.intToBigEndian(i4, bArr3, 0);
            }
        }
        this.digest.reset();
        return (int) j;
    }

    @Override // org.bouncycastle.crypto.DigestDerivationFunction
    public Digest getDigest() {
        return this.digest;
    }

    @Override // org.bouncycastle.crypto.DerivationFunction
    public void init(DerivationParameters derivationParameters) {
        if (derivationParameters instanceof KDFParameters) {
            KDFParameters kDFParameters = (KDFParameters) derivationParameters;
            this.shared = kDFParameters.getSharedSecret();
            this.f9448iv = kDFParameters.getIV();
        } else {
            if (!(derivationParameters instanceof ISO18033KDFParameters)) {
                throw new IllegalArgumentException("KDF parameters required for generator");
            }
            this.shared = ((ISO18033KDFParameters) derivationParameters).getSeed();
            this.f9448iv = null;
        }
    }
}
