package org.bouncycastle.asn1.cms;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

/* loaded from: classes9.dex */
public interface CMSObjectIdentifiers {
    public static final ASN1ObjectIdentifier data = PKCSObjectIdentifiers.data;
    public static final ASN1ObjectIdentifier signedData = PKCSObjectIdentifiers.signedData;
    public static final ASN1ObjectIdentifier envelopedData = PKCSObjectIdentifiers.envelopedData;
    public static final ASN1ObjectIdentifier signedAndEnvelopedData = PKCSObjectIdentifiers.signedAndEnvelopedData;
    public static final ASN1ObjectIdentifier digestedData = PKCSObjectIdentifiers.digestedData;
    public static final ASN1ObjectIdentifier encryptedData = PKCSObjectIdentifiers.encryptedData;
    public static final ASN1ObjectIdentifier authenticatedData = PKCSObjectIdentifiers.id_ct_authData;
    public static final ASN1ObjectIdentifier compressedData = PKCSObjectIdentifiers.id_ct_compressedData;
    public static final ASN1ObjectIdentifier authEnvelopedData = PKCSObjectIdentifiers.id_ct_authEnvelopedData;
    public static final ASN1ObjectIdentifier timestampedData = PKCSObjectIdentifiers.id_ct_timestampedData;
    public static final ASN1ObjectIdentifier id_ri = new ASN1ObjectIdentifier("1.3.6.1.5.5.7.16");
    public static final ASN1ObjectIdentifier id_ri_ocsp_response = id_ri.branch("2");
    public static final ASN1ObjectIdentifier id_ri_scvp = id_ri.branch(TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD);
}
