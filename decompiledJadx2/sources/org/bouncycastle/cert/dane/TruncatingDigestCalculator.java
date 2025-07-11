package org.bouncycastle.cert.dane;

import java.io.OutputStream;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.operator.DigestCalculator;

/* loaded from: classes9.dex */
public class TruncatingDigestCalculator implements DigestCalculator {
    private final DigestCalculator baseCalculator;
    private final int length;

    public TruncatingDigestCalculator(DigestCalculator digestCalculator) {
        this(digestCalculator, 28);
    }

    public TruncatingDigestCalculator(DigestCalculator digestCalculator, int i) {
        this.baseCalculator = digestCalculator;
        this.length = i;
    }

    @Override // org.bouncycastle.operator.DigestCalculator
    public AlgorithmIdentifier getAlgorithmIdentifier() {
        return this.baseCalculator.getAlgorithmIdentifier();
    }

    @Override // org.bouncycastle.operator.DigestCalculator
    public byte[] getDigest() {
        byte[] bArr = new byte[this.length];
        System.arraycopy(this.baseCalculator.getDigest(), 0, bArr, 0, bArr.length);
        return bArr;
    }

    @Override // org.bouncycastle.operator.DigestCalculator
    public OutputStream getOutputStream() {
        return this.baseCalculator.getOutputStream();
    }
}
