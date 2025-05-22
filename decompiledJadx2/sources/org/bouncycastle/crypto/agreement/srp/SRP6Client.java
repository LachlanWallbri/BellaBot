package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

/* loaded from: classes9.dex */
public class SRP6Client {

    /* renamed from: A */
    protected BigInteger f9148A;

    /* renamed from: B */
    protected BigInteger f9149B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f9150M1;

    /* renamed from: M2 */
    protected BigInteger f9151M2;

    /* renamed from: N */
    protected BigInteger f9152N;

    /* renamed from: S */
    protected BigInteger f9153S;

    /* renamed from: a */
    protected BigInteger f9154a;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f9155g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f9156u;

    /* renamed from: x */
    protected BigInteger f9157x;

    private BigInteger calculateS() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.f9152N, this.f9155g);
        return this.f9149B.subtract(this.f9155g.modPow(this.f9157x, this.f9152N).multiply(calculateK).mod(this.f9152N)).mod(this.f9152N).modPow(this.f9156u.multiply(this.f9157x).add(this.f9154a), this.f9152N);
    }

    public BigInteger calculateClientEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f9148A;
        if (bigInteger3 == null || (bigInteger = this.f9149B) == null || (bigInteger2 = this.f9153S) == null) {
            throw new CryptoException("Impossible to compute M1: some data are missing from the previous operations (A,B,S)");
        }
        this.f9150M1 = SRP6Util.calculateM1(this.digest, this.f9152N, bigInteger3, bigInteger, bigInteger2);
        return this.f9150M1;
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        this.f9149B = SRP6Util.validatePublicValue(this.f9152N, bigInteger);
        this.f9156u = SRP6Util.calculateU(this.digest, this.f9152N, this.f9148A, this.f9149B);
        this.f9153S = calculateS();
        return this.f9153S;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f9153S;
        if (bigInteger == null || this.f9150M1 == null || this.f9151M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        this.Key = SRP6Util.calculateKey(this.digest, this.f9152N, bigInteger);
        return this.Key;
    }

    public BigInteger generateClientCredentials(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        this.f9157x = SRP6Util.calculateX(this.digest, this.f9152N, bArr, bArr2, bArr3);
        this.f9154a = selectPrivateValue();
        this.f9148A = this.f9155g.modPow(this.f9154a, this.f9152N);
        return this.f9148A;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, Digest digest, SecureRandom secureRandom) {
        this.f9152N = bigInteger;
        this.f9155g = bigInteger2;
        this.digest = digest;
        this.random = secureRandom;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), digest, secureRandom);
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f9152N, this.f9155g, this.random);
    }

    public boolean verifyServerEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f9148A;
        if (bigInteger4 == null || (bigInteger2 = this.f9150M1) == null || (bigInteger3 = this.f9153S) == null) {
            throw new CryptoException("Impossible to compute and verify M2: some data are missing from the previous operations (A,M1,S)");
        }
        if (!SRP6Util.calculateM2(this.digest, this.f9152N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            return false;
        }
        this.f9151M2 = bigInteger;
        return true;
    }
}
