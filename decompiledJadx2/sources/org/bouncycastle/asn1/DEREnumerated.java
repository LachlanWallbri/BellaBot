package org.bouncycastle.asn1;

import java.math.BigInteger;

/* loaded from: classes9.dex */
public class DEREnumerated extends ASN1Enumerated {
    public DEREnumerated(int i) {
        super(i);
    }

    public DEREnumerated(BigInteger bigInteger) {
        super(bigInteger);
    }

    DEREnumerated(byte[] bArr) {
        super(bArr);
    }
}
