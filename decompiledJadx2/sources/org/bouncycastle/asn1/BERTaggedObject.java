package org.bouncycastle.asn1;

import java.io.IOException;
import java.util.Enumeration;

/* loaded from: classes9.dex */
public class BERTaggedObject extends ASN1TaggedObject {
    public BERTaggedObject(int i) {
        super(false, i, new BERSequence());
    }

    public BERTaggedObject(int i, ASN1Encodable aSN1Encodable) {
        super(true, i, aSN1Encodable);
    }

    public BERTaggedObject(boolean z, int i, ASN1Encodable aSN1Encodable) {
        super(z, i, aSN1Encodable);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1TaggedObject, org.bouncycastle.asn1.ASN1Primitive
    public void encode(ASN1OutputStream aSN1OutputStream, boolean z) throws IOException {
        Enumeration objects;
        aSN1OutputStream.writeTag(z, 160, this.tagNo);
        aSN1OutputStream.write(128);
        if (this.explicit) {
            aSN1OutputStream.writePrimitive(this.obj.toASN1Primitive(), true);
        } else {
            if (this.obj instanceof ASN1OctetString) {
                objects = this.obj instanceof BEROctetString ? ((BEROctetString) this.obj).getObjects() : new BEROctetString(((ASN1OctetString) this.obj).getOctets()).getObjects();
            } else if (this.obj instanceof ASN1Sequence) {
                objects = ((ASN1Sequence) this.obj).getObjects();
            } else {
                if (!(this.obj instanceof ASN1Set)) {
                    throw new ASN1Exception("not implemented: " + this.obj.getClass().getName());
                }
                objects = ((ASN1Set) this.obj).getObjects();
            }
            aSN1OutputStream.writeElements(objects);
        }
        aSN1OutputStream.write(0);
        aSN1OutputStream.write(0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public int encodedLength() throws IOException {
        int calculateTagLength;
        int encodedLength = this.obj.toASN1Primitive().encodedLength();
        if (this.explicit) {
            calculateTagLength = StreamUtil.calculateTagLength(this.tagNo) + StreamUtil.calculateBodyLength(encodedLength);
        } else {
            encodedLength--;
            calculateTagLength = StreamUtil.calculateTagLength(this.tagNo);
        }
        return calculateTagLength + encodedLength;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // org.bouncycastle.asn1.ASN1Primitive
    public boolean isConstructed() {
        return this.explicit || this.obj.toASN1Primitive().isConstructed();
    }
}
