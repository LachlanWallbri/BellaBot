package org.bouncycastle.pqc.crypto.qteslarnd1;

import java.security.SecureRandom;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoServicesRegistrar;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.pqc.crypto.MessageSigner;

/* loaded from: classes9.dex */
public class QTESLASigner implements MessageSigner {
    private QTESLAPrivateKeyParameters privateKey;
    private QTESLAPublicKeyParameters publicKey;
    private SecureRandom secureRandom;

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public byte[] generateSignature(byte[] bArr) {
        byte[] bArr2 = new byte[QTESLASecurityCategory.getSignatureSize(this.privateKey.getSecurityCategory())];
        int securityCategory = this.privateKey.getSecurityCategory();
        if (securityCategory == 0) {
            QTESLA.signingI(bArr2, bArr, 0, bArr.length, this.privateKey.getSecret(), this.secureRandom);
        } else if (securityCategory == 1) {
            QTESLA.signingIIISize(bArr2, bArr, 0, bArr.length, this.privateKey.getSecret(), this.secureRandom);
        } else if (securityCategory == 2) {
            QTESLA.signingIIISpeed(bArr2, bArr, 0, bArr.length, this.privateKey.getSecret(), this.secureRandom);
        } else if (securityCategory == 3) {
            QTESLA.signingIP(bArr2, bArr, 0, bArr.length, this.privateKey.getSecret(), this.secureRandom);
        } else {
            if (securityCategory != 4) {
                throw new IllegalArgumentException("unknown security category: " + this.privateKey.getSecurityCategory());
            }
            QTESLA.signingIIIP(bArr2, bArr, 0, bArr.length, this.privateKey.getSecret(), this.secureRandom);
        }
        return bArr2;
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public void init(boolean z, CipherParameters cipherParameters) {
        int securityCategory;
        if (z) {
            if (cipherParameters instanceof ParametersWithRandom) {
                ParametersWithRandom parametersWithRandom = (ParametersWithRandom) cipherParameters;
                this.secureRandom = parametersWithRandom.getRandom();
                this.privateKey = (QTESLAPrivateKeyParameters) parametersWithRandom.getParameters();
            } else {
                this.secureRandom = CryptoServicesRegistrar.getSecureRandom();
                this.privateKey = (QTESLAPrivateKeyParameters) cipherParameters;
            }
            this.publicKey = null;
            securityCategory = this.privateKey.getSecurityCategory();
        } else {
            this.privateKey = null;
            this.publicKey = (QTESLAPublicKeyParameters) cipherParameters;
            securityCategory = this.publicKey.getSecurityCategory();
        }
        QTESLASecurityCategory.validate(securityCategory);
    }

    @Override // org.bouncycastle.pqc.crypto.MessageSigner
    public boolean verifySignature(byte[] bArr, byte[] bArr2) {
        int verifyingI;
        int securityCategory = this.publicKey.getSecurityCategory();
        if (securityCategory == 0) {
            verifyingI = QTESLA.verifyingI(bArr, bArr2, 0, bArr2.length, this.publicKey.getPublicData());
        } else if (securityCategory == 1) {
            verifyingI = QTESLA.verifyingIIISize(bArr, bArr2, 0, bArr2.length, this.publicKey.getPublicData());
        } else if (securityCategory == 2) {
            verifyingI = QTESLA.verifyingIIISpeed(bArr, bArr2, 0, bArr2.length, this.publicKey.getPublicData());
        } else if (securityCategory == 3) {
            verifyingI = QTESLA.verifyingPI(bArr, bArr2, 0, bArr2.length, this.publicKey.getPublicData());
        } else {
            if (securityCategory != 4) {
                throw new IllegalArgumentException("unknown security category: " + this.publicKey.getSecurityCategory());
            }
            verifyingI = QTESLA.verifyingPIII(bArr, bArr2, 0, bArr2.length, this.publicKey.getPublicData());
        }
        return verifyingI == 0;
    }
}
