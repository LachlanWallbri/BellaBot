package org.bouncycastle.asn1.ocsp;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.AuthorityInformationAccess;

/* loaded from: classes9.dex */
public class ServiceLocator extends ASN1Object {
    private final X500Name issuer;
    private final AuthorityInformationAccess locator;

    private ServiceLocator(ASN1Sequence aSN1Sequence) {
        this.issuer = X500Name.getInstance(aSN1Sequence.getObjectAt(0));
        this.locator = aSN1Sequence.size() == 2 ? AuthorityInformationAccess.getInstance(aSN1Sequence.getObjectAt(1)) : null;
    }

    public static ServiceLocator getInstance(Object obj) {
        if (obj instanceof ServiceLocator) {
            return (ServiceLocator) obj;
        }
        if (obj != null) {
            return new ServiceLocator(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public X500Name getIssuer() {
        return this.issuer;
    }

    public AuthorityInformationAccess getLocator() {
        return this.locator;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.issuer);
        AuthorityInformationAccess authorityInformationAccess = this.locator;
        if (authorityInformationAccess != null) {
            aSN1EncodableVector.add(authorityInformationAccess);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
