package org.bouncycastle.asn1.cms;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

/* loaded from: classes9.dex */
public interface CMSAttributes {
    public static final ASN1ObjectIdentifier contentType = PKCSObjectIdentifiers.pkcs_9_at_contentType;
    public static final ASN1ObjectIdentifier messageDigest = PKCSObjectIdentifiers.pkcs_9_at_messageDigest;
    public static final ASN1ObjectIdentifier signingTime = PKCSObjectIdentifiers.pkcs_9_at_signingTime;
    public static final ASN1ObjectIdentifier counterSignature = PKCSObjectIdentifiers.pkcs_9_at_counterSignature;
    public static final ASN1ObjectIdentifier contentHint = PKCSObjectIdentifiers.id_aa_contentHint;
    public static final ASN1ObjectIdentifier cmsAlgorithmProtect = PKCSObjectIdentifiers.id_aa_cmsAlgorithmProtect;
}
