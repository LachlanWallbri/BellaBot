package org.bouncycastle.crypto.agreement;

import java.math.BigInteger;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.params.DHUPrivateParameters;
import org.bouncycastle.crypto.params.DHUPublicParameters;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes9.dex */
public class DHUnifiedAgreement {
    private DHUPrivateParameters privParams;

    public byte[] calculateAgreement(CipherParameters cipherParameters) {
        DHUPublicParameters dHUPublicParameters = (DHUPublicParameters) cipherParameters;
        DHBasicAgreement dHBasicAgreement = new DHBasicAgreement();
        DHBasicAgreement dHBasicAgreement2 = new DHBasicAgreement();
        dHBasicAgreement.init(this.privParams.getStaticPrivateKey());
        BigInteger calculateAgreement = dHBasicAgreement.calculateAgreement(dHUPublicParameters.getStaticPublicKey());
        dHBasicAgreement2.init(this.privParams.getEphemeralPrivateKey());
        return Arrays.concatenate(BigIntegers.asUnsignedByteArray(getFieldSize(), dHBasicAgreement2.calculateAgreement(dHUPublicParameters.getEphemeralPublicKey())), BigIntegers.asUnsignedByteArray(getFieldSize(), calculateAgreement));
    }

    public int getFieldSize() {
        return (this.privParams.getStaticPrivateKey().getParameters().getP().bitLength() + 7) / 8;
    }

    public void init(CipherParameters cipherParameters) {
        this.privParams = (DHUPrivateParameters) cipherParameters;
    }
}
