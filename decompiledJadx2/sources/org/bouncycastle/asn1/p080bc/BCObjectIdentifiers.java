package org.bouncycastle.asn1.p080bc;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;

/* loaded from: classes9.dex */
public interface BCObjectIdentifiers {

    /* renamed from: bc */
    public static final ASN1ObjectIdentifier f9018bc = new ASN1ObjectIdentifier("1.3.6.1.4.1.22554");
    public static final ASN1ObjectIdentifier bc_pbe = f9018bc.branch("1");
    public static final ASN1ObjectIdentifier bc_pbe_sha1 = bc_pbe.branch("1");
    public static final ASN1ObjectIdentifier bc_pbe_sha256 = bc_pbe.branch("2.1");
    public static final ASN1ObjectIdentifier bc_pbe_sha384 = bc_pbe.branch("2.2");
    public static final ASN1ObjectIdentifier bc_pbe_sha512 = bc_pbe.branch("2.3");
    public static final ASN1ObjectIdentifier bc_pbe_sha224 = bc_pbe.branch("2.4");
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs5 = bc_pbe_sha1.branch("1");
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12 = bc_pbe_sha1.branch("2");
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs5 = bc_pbe_sha256.branch("1");
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12 = bc_pbe_sha256.branch("2");
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12_aes128_cbc = bc_pbe_sha1_pkcs12.branch("1.2");
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12_aes192_cbc = bc_pbe_sha1_pkcs12.branch("1.22");
    public static final ASN1ObjectIdentifier bc_pbe_sha1_pkcs12_aes256_cbc = bc_pbe_sha1_pkcs12.branch("1.42");
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12_aes128_cbc = bc_pbe_sha256_pkcs12.branch("1.2");
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12_aes192_cbc = bc_pbe_sha256_pkcs12.branch("1.22");
    public static final ASN1ObjectIdentifier bc_pbe_sha256_pkcs12_aes256_cbc = bc_pbe_sha256_pkcs12.branch("1.42");
    public static final ASN1ObjectIdentifier bc_sig = f9018bc.branch("2");
    public static final ASN1ObjectIdentifier sphincs256 = bc_sig.branch("1");
    public static final ASN1ObjectIdentifier sphincs256_with_BLAKE512 = sphincs256.branch("1");
    public static final ASN1ObjectIdentifier sphincs256_with_SHA512 = sphincs256.branch("2");
    public static final ASN1ObjectIdentifier sphincs256_with_SHA3_512 = sphincs256.branch("3");
    public static final ASN1ObjectIdentifier xmss = bc_sig.branch("2");
    public static final ASN1ObjectIdentifier xmss_SHA256ph = xmss.branch("1");
    public static final ASN1ObjectIdentifier xmss_SHA512ph = xmss.branch("2");
    public static final ASN1ObjectIdentifier xmss_SHAKE128ph = xmss.branch("3");
    public static final ASN1ObjectIdentifier xmss_SHAKE256ph = xmss.branch(TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD);
    public static final ASN1ObjectIdentifier xmss_SHA256 = xmss.branch("5");
    public static final ASN1ObjectIdentifier xmss_SHA512 = xmss.branch("6");
    public static final ASN1ObjectIdentifier xmss_SHAKE128 = xmss.branch("7");
    public static final ASN1ObjectIdentifier xmss_SHAKE256 = xmss.branch("8");
    public static final ASN1ObjectIdentifier xmss_mt = bc_sig.branch("3");
    public static final ASN1ObjectIdentifier xmss_mt_SHA256ph = xmss_mt.branch("1");
    public static final ASN1ObjectIdentifier xmss_mt_SHA512ph = xmss_mt.branch("2");
    public static final ASN1ObjectIdentifier xmss_mt_SHAKE128ph = xmss_mt.branch("3");
    public static final ASN1ObjectIdentifier xmss_mt_SHAKE256ph = xmss_mt.branch(TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD);
    public static final ASN1ObjectIdentifier xmss_mt_SHA256 = xmss_mt.branch("5");
    public static final ASN1ObjectIdentifier xmss_mt_SHA512 = xmss_mt.branch("6");
    public static final ASN1ObjectIdentifier xmss_mt_SHAKE128 = xmss_mt.branch("7");
    public static final ASN1ObjectIdentifier xmss_mt_SHAKE256 = xmss_mt.branch("8");
    public static final ASN1ObjectIdentifier xmss_with_SHA256 = xmss_SHA256ph;
    public static final ASN1ObjectIdentifier xmss_with_SHA512 = xmss_SHA512ph;
    public static final ASN1ObjectIdentifier xmss_with_SHAKE128 = xmss_SHAKE128ph;
    public static final ASN1ObjectIdentifier xmss_with_SHAKE256 = xmss_SHAKE256ph;
    public static final ASN1ObjectIdentifier xmss_mt_with_SHA256 = xmss_mt_SHA256ph;
    public static final ASN1ObjectIdentifier xmss_mt_with_SHA512 = xmss_mt_SHA512ph;
    public static final ASN1ObjectIdentifier xmss_mt_with_SHAKE128 = xmss_mt_SHAKE128;
    public static final ASN1ObjectIdentifier xmss_mt_with_SHAKE256 = xmss_mt_SHAKE256;
    public static final ASN1ObjectIdentifier qTESLA = bc_sig.branch(TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD);
    public static final ASN1ObjectIdentifier qTESLA_Rnd1_I = qTESLA.branch("1");
    public static final ASN1ObjectIdentifier qTESLA_Rnd1_III_size = qTESLA.branch("2");
    public static final ASN1ObjectIdentifier qTESLA_Rnd1_III_speed = qTESLA.branch("3");
    public static final ASN1ObjectIdentifier qTESLA_Rnd1_p_I = qTESLA.branch(TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD);
    public static final ASN1ObjectIdentifier qTESLA_Rnd1_p_III = qTESLA.branch("5");
    public static final ASN1ObjectIdentifier qTESLA_p_I = qTESLA.branch("11");
    public static final ASN1ObjectIdentifier qTESLA_p_III = qTESLA.branch("12");
    public static final ASN1ObjectIdentifier bc_exch = f9018bc.branch("3");
    public static final ASN1ObjectIdentifier newHope = bc_exch.branch("1");
    public static final ASN1ObjectIdentifier bc_ext = f9018bc.branch(TmpConstant.MODEL_TYPE_ALI_LCA_CLOUD);
    public static final ASN1ObjectIdentifier linkedCertificate = bc_ext.branch("1");
}
