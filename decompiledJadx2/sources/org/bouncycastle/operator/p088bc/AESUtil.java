package org.bouncycastle.operator.p088bc;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.nist.NISTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes9.dex */
class AESUtil {
    AESUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static AlgorithmIdentifier determineKeyEncAlg(KeyParameter keyParameter) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        int length = keyParameter.getKey().length * 8;
        if (length == 128) {
            aSN1ObjectIdentifier = NISTObjectIdentifiers.id_aes128_wrap;
        } else if (length == 192) {
            aSN1ObjectIdentifier = NISTObjectIdentifiers.id_aes192_wrap;
        } else {
            if (length != 256) {
                throw new IllegalArgumentException("illegal keysize in AES");
            }
            aSN1ObjectIdentifier = NISTObjectIdentifiers.id_aes256_wrap;
        }
        return new AlgorithmIdentifier(aSN1ObjectIdentifier);
    }
}
