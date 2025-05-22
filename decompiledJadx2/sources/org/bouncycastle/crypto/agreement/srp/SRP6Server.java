package org.bouncycastle.crypto.agreement.srp;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.params.SRP6GroupParameters;

/* loaded from: classes9.dex */
public class SRP6Server {

    /* renamed from: A */
    protected BigInteger f9158A;

    /* renamed from: B */
    protected BigInteger f9159B;
    protected BigInteger Key;

    /* renamed from: M1 */
    protected BigInteger f9160M1;

    /* renamed from: M2 */
    protected BigInteger f9161M2;

    /* renamed from: N */
    protected BigInteger f9162N;

    /* renamed from: S */
    protected BigInteger f9163S;

    /* renamed from: b */
    protected BigInteger f9164b;
    protected Digest digest;

    /* renamed from: g */
    protected BigInteger f9165g;
    protected SecureRandom random;

    /* renamed from: u */
    protected BigInteger f9166u;

    /* renamed from: v */
    protected BigInteger f9167v;

    private BigInteger calculateS() {
        return this.f9167v.modPow(this.f9166u, this.f9162N).multiply(this.f9158A).mod(this.f9162N).modPow(this.f9164b, this.f9162N);
    }

    public BigInteger calculateSecret(BigInteger bigInteger) throws CryptoException {
        this.f9158A = SRP6Util.validatePublicValue(this.f9162N, bigInteger);
        this.f9166u = SRP6Util.calculateU(this.digest, this.f9162N, this.f9158A, this.f9159B);
        this.f9163S = calculateS();
        return this.f9163S;
    }

    public BigInteger calculateServerEvidenceMessage() throws CryptoException {
        BigInteger bigInteger;
        BigInteger bigInteger2;
        BigInteger bigInteger3 = this.f9158A;
        if (bigInteger3 == null || (bigInteger = this.f9160M1) == null || (bigInteger2 = this.f9163S) == null) {
            throw new CryptoException("Impossible to compute M2: some data are missing from the previous operations (A,M1,S)");
        }
        this.f9161M2 = SRP6Util.calculateM2(this.digest, this.f9162N, bigInteger3, bigInteger, bigInteger2);
        return this.f9161M2;
    }

    public BigInteger calculateSessionKey() throws CryptoException {
        BigInteger bigInteger = this.f9163S;
        if (bigInteger == null || this.f9160M1 == null || this.f9161M2 == null) {
            throw new CryptoException("Impossible to compute Key: some data are missing from the previous operations (S,M1,M2)");
        }
        this.Key = SRP6Util.calculateKey(this.digest, this.f9162N, bigInteger);
        return this.Key;
    }

    public BigInteger generateServerCredentials() {
        BigInteger calculateK = SRP6Util.calculateK(this.digest, this.f9162N, this.f9165g);
        this.f9164b = selectPrivateValue();
        this.f9159B = calculateK.multiply(this.f9167v).mod(this.f9162N).add(this.f9165g.modPow(this.f9164b, this.f9162N)).mod(this.f9162N);
        return this.f9159B;
    }

    public void init(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3, Digest digest, SecureRandom secureRandom) {
        this.f9162N = bigInteger;
        this.f9165g = bigInteger2;
        this.f9167v = bigInteger3;
        this.random = secureRandom;
        this.digest = digest;
    }

    public void init(SRP6GroupParameters sRP6GroupParameters, BigInteger bigInteger, Digest digest, SecureRandom secureRandom) {
        init(sRP6GroupParameters.getN(), sRP6GroupParameters.getG(), bigInteger, digest, secureRandom);
    }

    protected BigInteger selectPrivateValue() {
        return SRP6Util.generatePrivateValue(this.digest, this.f9162N, this.f9165g, this.random);
    }

    public boolean verifyClientEvidenceMessage(BigInteger bigInteger) throws CryptoException {
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4 = this.f9158A;
        if (bigInteger4 == null || (bigInteger2 = this.f9159B) == null || (bigInteger3 = this.f9163S) == null) {
            throw new CryptoException("Impossible to compute and verify M1: some data are missing from the previous operations (A,B,S)");
        }
        if (!SRP6Util.calculateM1(this.digest, this.f9162N, bigInteger4, bigInteger2, bigInteger3).equals(bigInteger)) {
            return false;
        }
        this.f9160M1 = bigInteger;
        return true;
    }
}
