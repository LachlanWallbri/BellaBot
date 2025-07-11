package org.bouncycastle.asn1.isismtt.x509;

import org.bouncycastle.asn1.ASN1Object;
import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.x500.DirectoryString;

/* loaded from: classes9.dex */
public class Restriction extends ASN1Object {
    private DirectoryString restriction;

    public Restriction(String str) {
        this.restriction = new DirectoryString(str);
    }

    private Restriction(DirectoryString directoryString) {
        this.restriction = directoryString;
    }

    public static Restriction getInstance(Object obj) {
        if (obj instanceof Restriction) {
            return (Restriction) obj;
        }
        if (obj != null) {
            return new Restriction(DirectoryString.getInstance(obj));
        }
        return null;
    }

    public DirectoryString getRestriction() {
        return this.restriction;
    }

    @Override // org.bouncycastle.asn1.ASN1Object, org.bouncycastle.asn1.ASN1Encodable
    public ASN1Primitive toASN1Primitive() {
        return this.restriction.toASN1Primitive();
    }
}
