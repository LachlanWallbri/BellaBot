package org.bouncycastle.cms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.BEROctetString;
import org.bouncycastle.asn1.DERSet;
import org.bouncycastle.asn1.cms.CMSObjectIdentifiers;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.cms.SignedData;
import org.bouncycastle.asn1.cms.SignerInfo;

/* loaded from: classes9.dex */
public class CMSSignedDataGenerator extends CMSSignedGenerator {
    private List signerInfs = new ArrayList();

    public CMSSignedData generate(CMSTypedData cMSTypedData) throws CMSException {
        return generate(cMSTypedData, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00dc  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x00d3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CMSSignedData generate(CMSTypedData cMSTypedData, boolean z) throws CMSException {
        BEROctetString bEROctetString;
        if (!this.signerInfs.isEmpty()) {
            throw new IllegalStateException("this method can only be used with SignerInfoGenerator");
        }
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector();
        ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector();
        this.digests.clear();
        for (SignerInformation signerInformation : this._signers) {
            aSN1EncodableVector.add(CMSSignedHelper.INSTANCE.fixAlgID(signerInformation.getDigestAlgorithmID()));
            aSN1EncodableVector2.add(signerInformation.toASN1Structure());
        }
        ASN1ObjectIdentifier contentType = cMSTypedData.getContentType();
        if (cMSTypedData.getContent() != null) {
            ByteArrayOutputStream byteArrayOutputStream = z ? new ByteArrayOutputStream() : null;
            OutputStream safeOutputStream = CMSUtils.getSafeOutputStream(CMSUtils.attachSignersToOutputStream(this.signerGens, byteArrayOutputStream));
            try {
                cMSTypedData.write(safeOutputStream);
                safeOutputStream.close();
                if (z) {
                    bEROctetString = new BEROctetString(byteArrayOutputStream.toByteArray());
                    for (SignerInfoGenerator signerInfoGenerator : this.signerGens) {
                        SignerInfo generate = signerInfoGenerator.generate(contentType);
                        aSN1EncodableVector.add(generate.getDigestAlgorithm());
                        aSN1EncodableVector2.add(generate);
                        byte[] calculatedDigest = signerInfoGenerator.getCalculatedDigest();
                        if (calculatedDigest != null) {
                            this.digests.put(generate.getDigestAlgorithm().getAlgorithm().getId(), calculatedDigest);
                        }
                    }
                    return new CMSSignedData(cMSTypedData, new ContentInfo(CMSObjectIdentifiers.signedData, new SignedData(new DERSet(aSN1EncodableVector), new ContentInfo(contentType, bEROctetString), this.certs.size() == 0 ? CMSUtils.createBerSetFromList(this.certs) : null, this.crls.size() != 0 ? CMSUtils.createBerSetFromList(this.crls) : null, new DERSet(aSN1EncodableVector2))));
                }
            } catch (IOException e) {
                throw new CMSException("data processing exception: " + e.getMessage(), e);
            }
        }
        bEROctetString = null;
        while (r3.hasNext()) {
        }
        if (this.certs.size() == 0) {
        }
        if (this.crls.size() != 0) {
        }
        return new CMSSignedData(cMSTypedData, new ContentInfo(CMSObjectIdentifiers.signedData, new SignedData(new DERSet(aSN1EncodableVector), new ContentInfo(contentType, bEROctetString), this.certs.size() == 0 ? CMSUtils.createBerSetFromList(this.certs) : null, this.crls.size() != 0 ? CMSUtils.createBerSetFromList(this.crls) : null, new DERSet(aSN1EncodableVector2))));
    }

    public SignerInformationStore generateCounterSigners(SignerInformation signerInformation) throws CMSException {
        return generate(new CMSProcessableByteArray(null, signerInformation.getSignature()), false).getSignerInfos();
    }
}
