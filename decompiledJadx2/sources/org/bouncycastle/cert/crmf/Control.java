package org.bouncycastle.cert.crmf;

import org.bouncycastle.asn1.ASN1Encodable;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes9.dex */
public interface Control {
    ASN1ObjectIdentifier getType();

    ASN1Encodable getValue();
}
