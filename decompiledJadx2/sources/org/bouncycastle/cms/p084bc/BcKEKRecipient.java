package org.bouncycastle.cms.p084bc;

import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.cms.CMSException;
import org.bouncycastle.cms.KEKRecipient;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.operator.OperatorException;
import org.bouncycastle.operator.SymmetricKeyUnwrapper;
import org.bouncycastle.operator.p088bc.BcSymmetricKeyUnwrapper;

/* loaded from: classes9.dex */
public abstract class BcKEKRecipient implements KEKRecipient {
    private SymmetricKeyUnwrapper unwrapper;

    public BcKEKRecipient(BcSymmetricKeyUnwrapper bcSymmetricKeyUnwrapper) {
        this.unwrapper = bcSymmetricKeyUnwrapper;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public CipherParameters extractSecretKey(AlgorithmIdentifier algorithmIdentifier, AlgorithmIdentifier algorithmIdentifier2, byte[] bArr) throws CMSException {
        try {
            return CMSUtils.getBcKey(this.unwrapper.generateUnwrappedKey(algorithmIdentifier2, bArr));
        } catch (OperatorException e) {
            throw new CMSException("exception unwrapping key: " + e.getMessage(), e);
        }
    }
}
