package org.bouncycastle.pkcs.jcajce;

import java.util.HashMap;
import java.util.Map;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.cryptopro.CryptoProObjectIdentifiers;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;

/* loaded from: classes9.dex */
class JceUtils {
    private static final Map PRFS = new HashMap();

    static {
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA1, "PBKDF2withHMACSHA1");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA256, "PBKDF2withHMACSHA256");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA512, "PBKDF2withHMACSHA512");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA224, "PBKDF2withHMACSHA224");
        PRFS.put(PKCSObjectIdentifiers.id_hmacWithSHA384, "PBKDF2withHMACSHA384");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_224, "PBKDF2withHMACSHA3-224");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_256, "PBKDF2withHMACSHA3-256");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_384, "PBKDF2withHMACSHA3-384");
        PRFS.put(NISTObjectIdentifiers.id_hmacWithSHA3_512, "PBKDF2withHMACSHA3-512");
        PRFS.put(CryptoProObjectIdentifiers.gostR3411Hmac, "PBKDF2withHMACGOST3411");
    }

    JceUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String getAlgorithm(ASN1ObjectIdentifier aSN1ObjectIdentifier) {
        if (PRFS.containsKey(aSN1ObjectIdentifier)) {
            return (String) PRFS.get(aSN1ObjectIdentifier);
        }
        throw new IllegalStateException("no prf for algorithm: " + aSN1ObjectIdentifier);
    }
}
