package org.bouncycastle.asn1.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;

/* loaded from: classes9.dex */
public class CertPolicyId extends ASN1Object {

    /* renamed from: id */
    private ASN1ObjectIdentifier f9082id;

    private CertPolicyId(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.f9082id = aSN1ObjectIdentifier;
    }

    public static CertPolicyId getInstance(Object obj) {
        if (obj instanceof CertPolicyId) {
            return (CertPolicyId) obj;
        }
        if (obj != null) {
            return new CertPolicyId(ASN1ObjectIdentifier.getInstance(obj));
        }
        return null;
    }

    public String getId() {
        return this.f9082id.getId();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.f9082id;
    }
}
