package org.bouncycastle.asn1;

/* loaded from: classes9.dex */
public class DERObjectIdentifier extends ASN1ObjectIdentifier {
    public DERObjectIdentifier(String str) {
        super(str);
    }

    DERObjectIdentifier(ASN1ObjectIdentifier aSN1ObjectIdentifier, String str) {
        super(aSN1ObjectIdentifier, str);
    }

    DERObjectIdentifier(byte[] bArr) {
        super(bArr);
    }
}
