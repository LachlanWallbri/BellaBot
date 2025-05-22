package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.crmf.CertRequest;
import org.bouncycastle.asn1.crmf.PKMACValue;
import org.bouncycastle.asn1.crmf.POPOSigningKey;
import org.bouncycastle.asn1.crmf.POPOSigningKeyInput;
import org.bouncycastle.asn1.x509.GeneralName;
import org.bouncycastle.asn1.x509.SubjectPublicKeyInfo;
import org.bouncycastle.operator.ContentSigner;

/* loaded from: classes9.dex */
public class ProofOfPossessionSigningKeyBuilder {
    private CertRequest certRequest;
    private GeneralName name;
    private SubjectPublicKeyInfo pubKeyInfo;
    private PKMACValue publicKeyMAC;

    public ProofOfPossessionSigningKeyBuilder(CertRequest certRequest) {
        this.certRequest = certRequest;
    }

    public ProofOfPossessionSigningKeyBuilder(SubjectPublicKeyInfo subjectPublicKeyInfo) {
        this.pubKeyInfo = subjectPublicKeyInfo;
    }

    public POPOSigningKey build(ContentSigner contentSigner) {
        POPOSigningKeyInput pOPOSigningKeyInput;
        if (this.name != null && this.publicKeyMAC != null) {
            throw new IllegalStateException("name and publicKeyMAC cannot both be set.");
        }
        CertRequest certRequest = this.certRequest;
        if (certRequest != null) {
            pOPOSigningKeyInput = null;
            CRMFUtil.derEncodeToStream(certRequest, contentSigner.getOutputStream());
        } else {
            GeneralName generalName = this.name;
            pOPOSigningKeyInput = generalName != null ? new POPOSigningKeyInput(generalName, this.pubKeyInfo) : new POPOSigningKeyInput(this.publicKeyMAC, this.pubKeyInfo);
            CRMFUtil.derEncodeToStream(pOPOSigningKeyInput, contentSigner.getOutputStream());
        }
        return new POPOSigningKey(pOPOSigningKeyInput, contentSigner.getAlgorithmIdentifier(), new DERBitString(contentSigner.getSignature()));
    }

    public ProofOfPossessionSigningKeyBuilder setPublicKeyMac(PKMACValueGenerator pKMACValueGenerator, char[] cArr) throws CRMFException {
        this.publicKeyMAC = pKMACValueGenerator.generate(cArr, this.pubKeyInfo);
        return this;
    }

    public ProofOfPossessionSigningKeyBuilder setSender(GeneralName generalName) {
        this.name = generalName;
        return this;
    }
}
