package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.CertificateList;

/* loaded from: classes9.dex */
public class TimeStampAndCRL extends ASN1Object {
    private CertificateList crl;
    private ContentInfo timeStamp;

    private TimeStampAndCRL(ASN1Sequence aSN1Sequence) {
        this.timeStamp = ContentInfo.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() == 2) {
            this.crl = CertificateList.getInstance(aSN1Sequence.getObjectAt(1));
        }
    }

    public TimeStampAndCRL(ContentInfo contentInfo) {
        this.timeStamp = contentInfo;
    }

    public static TimeStampAndCRL getInstance(Object obj) {
        if (obj instanceof TimeStampAndCRL) {
            return (TimeStampAndCRL) obj;
        }
        if (obj != null) {
            return new TimeStampAndCRL(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public CertificateList getCRL() {
        return this.crl;
    }

    public CertificateList getCertificateList() {
        return this.crl;
    }

    public ContentInfo getTimeStampToken() {
        return this.timeStamp;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.timeStamp);
        CertificateList certificateList = this.crl;
        if (certificateList != null) {
            aSN1EncodableVector.add(certificateList);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
