package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

/* loaded from: classes9.dex */
public class SRP6VerifierGenerator {

    /* renamed from: N */
    protected BigInteger f9168N;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f9169g;

    public BigInteger generateVerifier(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        return this.f9169g.modPow(SRP6Util.calculateX(this.digest, this.f9168N, bArr, bArr2, bArr3), this.f9168N);
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest) {
        this.f9168N = bigInteger;
        this.f9169g = bigInteger2;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest) {
        this.f9168N = sRP6GroupParameters.getN();
        this.f9169g = sRP6GroupParameters.getG();
        this.digest = digest;
    }
}
