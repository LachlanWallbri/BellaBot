package org.conscrypt;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
final class EvpMdRef {
    static final String MGF1_ALGORITHM_NAME = "MGF1";
    static final String MGF1_OID = "1.2.840.113549.1.1.8";

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getJcaDigestAlgorithmStandardName(String str) {
        String upperCase = str.toUpperCase(Locale.US);
        String str2 = "SHA-256";
        if (!"SHA-256".equals(upperCase) && !"2.16.840.1.101.3.4.2.1".equals(upperCase)) {
            str2 = "SHA-512";
            if (!"SHA-512".equals(upperCase) && !"2.16.840.1.101.3.4.2.3".equals(upperCase)) {
                str2 = "SHA-1";
                if (!"SHA-1".equals(upperCase) && !"1.3.14.3.2.26".equals(upperCase)) {
                    str2 = "SHA-384";
                    if (!"SHA-384".equals(upperCase) && !"2.16.840.1.101.3.4.2.2".equals(upperCase)) {
                        str2 = "SHA-224";
                        if (!"SHA-224".equals(upperCase) && !"2.16.840.1.101.3.4.2.4".equals(upperCase)) {
                            return null;
                        }
                    }
                }
            }
        }
        return str2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getEVP_MDByJcaDigestAlgorithmStandardName(String str) throws NoSuchAlgorithmException {
        String upperCase = str.toUpperCase(Locale.US);
        if ("SHA-256".equals(upperCase)) {
            return SHA256.EVP_MD;
        }
        if ("SHA-512".equals(upperCase)) {
            return SHA512.EVP_MD;
        }
        if ("SHA-1".equals(upperCase)) {
            return SHA1.EVP_MD;
        }
        if ("SHA-384".equals(upperCase)) {
            return SHA384.EVP_MD;
        }
        if ("SHA-224".equals(upperCase)) {
            return SHA224.EVP_MD;
        }
        throw new NoSuchAlgorithmException("Unsupported algorithm: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getDigestSizeBytesByJcaDigestAlgorithmStandardName(String str) throws NoSuchAlgorithmException {
        String upperCase = str.toUpperCase(Locale.US);
        if ("SHA-256".equals(upperCase)) {
            return SHA256.SIZE_BYTES;
        }
        if ("SHA-512".equals(upperCase)) {
            return SHA512.SIZE_BYTES;
        }
        if ("SHA-1".equals(upperCase)) {
            return SHA1.SIZE_BYTES;
        }
        if ("SHA-384".equals(upperCase)) {
            return SHA384.SIZE_BYTES;
        }
        if ("SHA-224".equals(upperCase)) {
            return SHA224.SIZE_BYTES;
        }
        throw new NoSuchAlgorithmException("Unsupported algorithm: " + str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getJcaDigestAlgorithmStandardNameFromEVP_MD(long j) {
        if (j == MD5.EVP_MD) {
            return MessageDigestAlgorithms.MD5;
        }
        if (j == SHA1.EVP_MD) {
            return "SHA-1";
        }
        if (j == SHA224.EVP_MD) {
            return "SHA-224";
        }
        if (j == SHA256.EVP_MD) {
            return "SHA-256";
        }
        if (j == SHA384.EVP_MD) {
            return "SHA-384";
        }
        if (j == SHA512.EVP_MD) {
            return "SHA-512";
        }
        throw new IllegalArgumentException("Unknown EVP_MD reference");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class MD5 {
        static final String JCA_NAME = "MD5";
        static final String OID = "1.2.840.113549.2.5";
        static final long EVP_MD = NativeCrypto.EVP_get_digestbyname("md5");
        static final int SIZE_BYTES = NativeCrypto.EVP_MD_size(EVP_MD);

        private MD5() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class SHA1 {
        static final String JCA_NAME = "SHA-1";
        static final String OID = "1.3.14.3.2.26";
        static final long EVP_MD = NativeCrypto.EVP_get_digestbyname("sha1");
        static final int SIZE_BYTES = NativeCrypto.EVP_MD_size(EVP_MD);

        private SHA1() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class SHA224 {
        static final String JCA_NAME = "SHA-224";
        static final String OID = "2.16.840.1.101.3.4.2.4";
        static final long EVP_MD = NativeCrypto.EVP_get_digestbyname("sha224");
        static final int SIZE_BYTES = NativeCrypto.EVP_MD_size(EVP_MD);

        private SHA224() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class SHA256 {
        static final String JCA_NAME = "SHA-256";
        static final String OID = "2.16.840.1.101.3.4.2.1";
        static final long EVP_MD = NativeCrypto.EVP_get_digestbyname(TmpConstant.VALUE_SHA256);
        static final int SIZE_BYTES = NativeCrypto.EVP_MD_size(EVP_MD);

        private SHA256() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class SHA384 {
        static final String JCA_NAME = "SHA-384";
        static final String OID = "2.16.840.1.101.3.4.2.2";
        static final long EVP_MD = NativeCrypto.EVP_get_digestbyname("sha384");
        static final int SIZE_BYTES = NativeCrypto.EVP_MD_size(EVP_MD);

        private SHA384() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    static final class SHA512 {
        static final String JCA_NAME = "SHA-512";
        static final String OID = "2.16.840.1.101.3.4.2.3";
        static final long EVP_MD = NativeCrypto.EVP_get_digestbyname("sha512");
        static final int SIZE_BYTES = NativeCrypto.EVP_MD_size(EVP_MD);

        private SHA512() {
        }
    }

    private EvpMdRef() {
    }
}
