package org.bouncycastle.asn1.x509.qualified;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;

/* loaded from: classes9.dex */
public class QCStatement extends ASN1Object implements ETSIQCObjectIdentifiers, RFC3739QCObjectIdentifiers {
    ASN1ObjectIdentifier qcStatementId;
    ASN1Encodable qcStatementInfo;

    public QCStatement(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        this.qcStatementId = aSN1ObjectIdentifier;
        this.qcStatementInfo = null;
    }

    public QCStatement(ASN1ObjectIdentifier aSN1ObjectIdentifier, ASN1Encodable aSN1Encodable) {
        this.qcStatementId = aSN1ObjectIdentifier;
        this.qcStatementInfo = aSN1Encodable;
    }

    private QCStatement(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        this.qcStatementId = ASN1ObjectIdentifier.getInstance(objects.nextElement());
        if (objects.hasMoreElements()) {
            this.qcStatementInfo = (ASN1Encodable) objects.nextElement();
        }
    }

    public static QCStatement getInstance(Object obj) {
        if (obj instanceof QCStatement) {
            return (QCStatement) obj;
        }
        if (obj != null) {
            return new QCStatement(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public ASN1ObjectIdentifier getStatementId() {
        return this.qcStatementId;
    }

    public ASN1Encodable getStatementInfo() {
        return this.qcStatementInfo;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        aSN1EncodableVector.add(this.qcStatementId);
        ASN1Encodable aSN1Encodable = this.qcStatementInfo;
        if (aSN1Encodable != null) {
            aSN1EncodableVector.add(aSN1Encodable);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
