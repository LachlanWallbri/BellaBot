package org.bouncycastle.asn1.p081x9;

import java.math.BigInteger;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERBitString;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.bouncycastle.math.ec.ECCurve;
import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class X9Curve extends ASN1Object implements X9ObjectIdentifiers {
    private ECCurve curve;
    private ASN1ObjectIdentifier fieldIdentifier;
    private byte[] seed;

    public X9Curve(X9FieldID x9FieldID, BigInteger bigInteger, BigInteger bigInteger2, ASN1Sequence aSN1Sequence) {
        int intValueExact;
        int i;
        int i2;
        ECCurve f2m;
        this.fieldIdentifier = null;
        this.fieldIdentifier = x9FieldID.getIdentifier();
        if (this.fieldIdentifier.equals((ASN1Primitive) prime_field)) {
            f2m = new ECCurve.C8419Fp(((ASN1Integer) x9FieldID.getParameters()).getValue(), new BigInteger(1, ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets()), new BigInteger(1, ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets()), bigInteger, bigInteger2);
        } else {
            if (!this.fieldIdentifier.equals((ASN1Primitive) characteristic_two_field)) {
                throw new IllegalArgumentException("This type of ECCurve is not implemented");
            }
            ASN1Sequence aSN1Sequence2 = ASN1Sequence.getInstance(x9FieldID.getParameters());
            int intValueExact2 = ((ASN1Integer) aSN1Sequence2.getObjectAt(0)).intValueExact();
            ASN1ObjectIdentifier aSN1ObjectIdentifier = (ASN1ObjectIdentifier) aSN1Sequence2.getObjectAt(1);
            if (aSN1ObjectIdentifier.equals((ASN1Primitive) tpBasis)) {
                i = ASN1Integer.getInstance(aSN1Sequence2.getObjectAt(2)).intValueExact();
                i2 = 0;
                intValueExact = 0;
            } else {
                if (!aSN1ObjectIdentifier.equals((ASN1Primitive) ppBasis)) {
                    throw new IllegalArgumentException("This type of EC basis is not implemented");
                }
                ASN1Sequence aSN1Sequence3 = ASN1Sequence.getInstance(aSN1Sequence2.getObjectAt(2));
                int intValueExact3 = ASN1Integer.getInstance(aSN1Sequence3.getObjectAt(0)).intValueExact();
                int intValueExact4 = ASN1Integer.getInstance(aSN1Sequence3.getObjectAt(1)).intValueExact();
                intValueExact = ASN1Integer.getInstance(aSN1Sequence3.getObjectAt(2)).intValueExact();
                i = intValueExact3;
                i2 = intValueExact4;
            }
            f2m = new ECCurve.F2m(intValueExact2, i, i2, intValueExact, new BigInteger(1, ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(0)).getOctets()), new BigInteger(1, ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(1)).getOctets()), bigInteger, bigInteger2);
        }
        this.curve = f2m;
        if (aSN1Sequence.size() == 3) {
            this.seed = ((DERBitString) aSN1Sequence.getObjectAt(2)).getBytes();
        }
    }

    public X9Curve(ECCurve eCCurve) {
        this(eCCurve, null);
    }

    public X9Curve(ECCurve eCCurve, byte[] bArr) {
        this.fieldIdentifier = null;
        this.curve = eCCurve;
        this.seed = Arrays.clone(bArr);
        setFieldIdentifier();
    }

    private void setFieldIdentifier() {
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        if (ECAlgorithms.isFpCurve(this.curve)) {
            aSN1ObjectIdentifier = prime_field;
        } else {
            if (!ECAlgorithms.isF2mCurve(this.curve)) {
                throw new IllegalArgumentException("This type of ECCurve is not implemented");
            }
            aSN1ObjectIdentifier = characteristic_two_field;
        }
        this.fieldIdentifier = aSN1ObjectIdentifier;
    }

    public ECCurve getCurve() {
        return this.curve;
    }

    public byte[] getSeed() {
        return Arrays.clone(this.seed);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0061  */
    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ASN1Primitive toASN1Primitive() {
        X9FieldElement x9FieldElement;
        byte[] bArr;
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(3);
        if (!this.fieldIdentifier.equals((ASN1Primitive) prime_field)) {
            if (this.fieldIdentifier.equals((ASN1Primitive) characteristic_two_field)) {
                aSN1EncodableVector.add(new X9FieldElement(this.curve.getA()).toASN1Primitive());
                x9FieldElement = new X9FieldElement(this.curve.getB());
            }
            bArr = this.seed;
            if (bArr != null) {
                aSN1EncodableVector.add(new DERBitString(bArr));
            }
            return new DERSequence(aSN1EncodableVector);
        }
        aSN1EncodableVector.add(new X9FieldElement(this.curve.getA()).toASN1Primitive());
        x9FieldElement = new X9FieldElement(this.curve.getB());
        aSN1EncodableVector.add(x9FieldElement.toASN1Primitive());
        bArr = this.seed;
        if (bArr != null) {
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
