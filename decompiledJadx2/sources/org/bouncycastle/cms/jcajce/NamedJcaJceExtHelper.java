package org.bouncycastle.cms.jcajce;

import java.security.PrivateKey;
import javax.crypto.SecretKey;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.jcajce.util.NamedJcaJceHelper;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceAsymmetricKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceKTSKeyUnwrapper;
import org.bouncycastle.operator.jcajce.JceSymmetricKeyUnwrapper;

/* loaded from: classes9.dex */
class NamedJcaJceExtHelper extends NamedJcaJceHelper implements JcaJceExtHelper {
    public NamedJcaJceExtHelper(String str) {
        super(str);
    }

    @Override // org.bouncycastle.cms.jcajce.JcaJceExtHelper
    public JceAsymmetricKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey) {
        return new JceAsymmetricKeyUnwrapper(algorithmIdentifier, CMSUtils.cleanPrivateKey(privateKey)).setProvider(this.providerName);
    }

    @Override // org.bouncycastle.cms.jcajce.JcaJceExtHelper
    public JceKTSKeyUnwrapper createAsymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, PrivateKey privateKey, byte[] bArr, byte[] bArr2) {
        return new JceKTSKeyUnwrapper(algorithmIdentifier, CMSUtils.cleanPrivateKey(privateKey), bArr, bArr2).setProvider(this.providerName);
    }

    @Override // org.bouncycastle.cms.jcajce.JcaJceExtHelper
    public SymmetricKeyUnwrapper createSymmetricUnwrapper(AlgorithmIdentifier algorithmIdentifier, SecretKey secretKey) {
        return new JceSymmetricKeyUnwrapper(algorithmIdentifier, secretKey).setProvider(this.providerName);
    }
}
