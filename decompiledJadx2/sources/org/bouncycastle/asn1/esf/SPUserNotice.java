package org.bouncycastle.asn1.esf;

import java.util.Enumeration;
import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.ASN1String;
import org.bouncycastle.asn1.DERSequence;
import org.bouncycastle.asn1.x509.DisplayText;
import org.bouncycastle.asn1.x509.NoticeReference;

/* loaded from: classes9.dex */
public class SPUserNotice extends ASN1Object {
    private DisplayText explicitText;
    private NoticeReference noticeRef;

    private SPUserNotice(ASN1Sequence aSN1Sequence) {
        Enumeration objects = aSN1Sequence.getObjects();
        while (objects.hasMoreElements()) {
            ASN1Encodable aSN1Encodable = (ASN1Encodable) objects.nextElement();
            if ((aSN1Encodable instanceof DisplayText) || (aSN1Encodable instanceof ASN1String)) {
                this.explicitText = DisplayText.getInstance(aSN1Encodable);
            } else {
                if (!(aSN1Encodable instanceof NoticeReference) && !(aSN1Encodable instanceof ASN1Sequence)) {
                    throw new IllegalArgumentException("Invalid element in 'SPUserNotice': " + aSN1Encodable.getClass().getName());
                }
                this.noticeRef = NoticeReference.getInstance(aSN1Encodable);
            }
        }
    }

    public SPUserNotice(NoticeReference noticeReference, DisplayText displayText) {
        this.noticeRef = noticeReference;
        this.explicitText = displayText;
    }

    public static SPUserNotice getInstance(Object obj) {
        if (obj instanceof SPUserNotice) {
            return (SPUserNotice) obj;
        }
        if (obj != null) {
            return new SPUserNotice(ASN1Sequence.getInstance(obj));
        }
        return null;
    }

    public DisplayText getExplicitText() {
        return this.explicitText;
    }

    public NoticeReference getNoticeRef() {
        return this.noticeRef;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        ASN1EncodableVector aSN1EncodableVector = new ASN1EncodableVector(2);
        NoticeReference noticeReference = this.noticeRef;
        if (noticeReference != null) {
            aSN1EncodableVector.add(noticeReference);
        }
        DisplayText displayText = this.explicitText;
        if (displayText != null) {
            aSN1EncodableVector.add(displayText);
        }
        return new DERSequence(aSN1EncodableVector);
    }
}
