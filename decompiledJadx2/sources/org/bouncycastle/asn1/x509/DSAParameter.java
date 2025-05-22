package org.bouncycastle.asn1.x509;

import java.math.BigInteger;
import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1TaggedObject;
import org.bouncycastle.asn1.DERSequence;

/* loaded from: classes9.dex */
public class DSAParameter extends ASN1Object {

    /* renamed from: g */
    ASN1Integer f9083g;

    /* renamed from: p */
    ASN1Integer f9084p;

    /* renamed from: q */
    ASN1Integer f9085q;

    public DSAParameter(BigInteger bigInteger, BigInteger bigInteger2, BigInteger bigInteger3) {
        this.f9084p = new ASN1Integer(bigInteger);
        this.f9085q = new ASN1Integer(bigInteger2);
        this.f9083g = new ASN1Integer(bigInteger3);
    }

    private DSAParameter(ASN1Sequence aSN1Sequence) {
        if (aSN1Sequence.size() != 3) {
            throw new IllegalArgumentException("Bad sequence size: " + aSN1Sequence.size());
        }
        Enumeration objects = aSN1Sequence.getObjects();
        this.f9084p = ASN1Integer.getInstance(objects.nextElement());
        this.f9085q = ASN1Integer.getInstance(objects.nextElement());
        this.f9083g = ASN1Integer.getInstance(objects.nextElement());
    }

    public static DSAParameter getInstance(Object obj) {
        if (obj instanceof DSAParameter) {
            return (DSAParameter) obj;
        }
        if (obj != null) {
            return new DSAParameter(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public static DSAParameter getInstance(ASN1TaggedObject aSN1TaggedObject, boolean z) {
        return getInstance(ASN1Sequence.getInstance(aSN1TaggedObject, z));
    }

    public BigInteger getG() {
        return this.f9083g.getPositiveValue();
    }

    public BigInteger getP() {
        return this.f9084p.getPositiveValue();
    }

    public BigInteger getQ() {
        return this.f9085q.getPositiveValue();
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        aSN1EncodableVector.add(this.f9084p);
        aSN1EncodableVector.add(this.f9085q);
        aSN1EncodableVector.add(this.f9083g);
        return new DERSequence(aSN1EncodableVector);
    }
}
