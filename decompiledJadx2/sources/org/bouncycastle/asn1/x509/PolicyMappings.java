package org.bouncycastle.asn1.x509;

import java.util.Enumeration;
import java.util.Hashtable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

/* loaded from: classes9.dex */
public class PolicyMappings extends ASN1Object {
    ASN1Sequence seq;

    public PolicyMappings(Hashtable hashtable) {
        this.seq = null;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(hashtable.size());
        Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            String str = (String) keys.nextElement();
            String str2 = (String) hashtable.get(str);
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(2);
            aSN1EncodableVector2.add(new ASN1ObjectIdentifier(str));
            aSN1EncodableVector2.add(new ASN1ObjectIdentifier(str2));
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        this.seq = new DERSequence(aSN1EncodableVector);
    }

    private PolicyMappings(ASN1Sequence aSN1Sequence) {
        this.seq = null;
        this.seq = aSN1Sequence;
    }

    public PolicyMappings(CertPolicyId certPolicyId, CertPolicyId certPolicyId2) {
        this.seq = null;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(certPolicyId);
        aSN1EncodableVector.add(certPolicyId2);
        this.seq = new DERSequence(new DERSequence(aSN1EncodableVector));
    }

    public PolicyMappings(CertPolicyId[] certPolicyIdArr, CertPolicyId[] certPolicyIdArr2) {
        this.seq = null;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(certPolicyIdArr.length);
        for (int i = 0; i != certPolicyIdArr.length; i++) {
            ASN1EncodableVector aSN1EncodableVector2 = new ASN1EncodableVector(2);
            aSN1EncodableVector2.add(certPolicyIdArr[i]);
            aSN1EncodableVector2.add(certPolicyIdArr2[i]);
            aSN1EncodableVector.add(new DERSequence(aSN1EncodableVector2));
        }
        this.seq = new DERSequence(aSN1EncodableVector);
    }

    public static PolicyMappings getInstance(Object obj) {
        if (obj instanceof PolicyMappings) {
            return (PolicyMappings) obj;
        }
        if (obj != null) {
            return new PolicyMappings(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.seq;
    }
}
