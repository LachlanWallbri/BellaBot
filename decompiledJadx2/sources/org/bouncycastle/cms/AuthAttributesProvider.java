package org.bouncycastle.cms;

import org.bouncycastle.asn1.ASN1Set;

/* loaded from: classes9.dex */
interface AuthAttributesProvider {
    ASN1Set getAuthAttributes();

    boolean isAead();
}
