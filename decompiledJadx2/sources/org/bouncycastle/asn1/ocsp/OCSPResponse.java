package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.DERTaggedObject;

/* loaded from: classes9.dex */
public class OCSPResponse extends ASN1Object {
    ResponseBytes responseBytes;
    OCSPResponseStatus responseStatus;

    private OCSPResponse(ASN1Sequence aSN1Sequence) {
        this.responseStatus = OCSPResponseStatus.getInstance(aSN1Sequence.getObjectAt(0));
        if (aSN1Sequence.size() == 2) {
            this.responseBytes = ResponseBytes.getInstance((ASN1TaggedObject) aSN1Sequence.getObjectAt(1), true);
        }
    }

    public OCSPResponse(OCSPResponseStatus oCSPResponseStatus, ResponseBytes responseBytes) {
        this.responseStatus = oCSPResponseStatus;
        this.responseBytes = responseBytes;
    }

    public static OCSPResponse getInstance(Object obj) {
        if (obj instanceof OCSPResponse) {
            return (OCSPResponse) obj;
        }
        if (obj != null) {
            return new OCSPResponse(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static OCSPResponse getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public ResponseBytes getResponseBytes() {
        return this.responseBytes;
    }

    public OCSPResponseStatus getResponseStatus() {
        return this.responseStatus;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.responseStatus);
        ResponseBytes responseBytes = this.responseBytes;
        if (responseBytes != null) {
            aSN1EncodableVector.add(new DERTaggedObject(true, 0, responseBytes));
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
