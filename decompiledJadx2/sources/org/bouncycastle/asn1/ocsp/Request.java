package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;
import org.bouncycastle.asn1.x509.Extensions;

/* loaded from: classes9.dex */
public class Request extends ASN1Object {
    CertID reqCert;
    Extensions singleRequestExtensions;

    private Request(ASN1Sequence aSN1Sequence) {
        this.reqCert = CertID.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() == 2) {
            this.singleRequestExtensions = Extensions.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true);
        }
    }

    public Request(CertID certID, Extensions extensions) {
        this.reqCert = certID;
        this.singleRequestExtensions = extensions;
    }

    public static Request getInstance(Object obj) {
        if (obj instanceof Request) {
            return (Request) obj;
        }
        if (obj != null) {
            return new Request(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static Request getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public CertID getReqCert() {
        return this.reqCert;
    }

    public Extensions getSingleRequestExtensions() {
        return this.singleRequestExtensions;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.reqCert);
        Extensions extensions = this.singleRequestExtensions;
        if (extensions != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, extensions));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
