package org.bouncycastle.operator.p088bc;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.ntt.NTTObjectIdentifiers;
import org.bouncycastle.asn1.x509.AlgorithmIdentifier;
import org.bouncycastle.crypto.params.KeyParameter;

/* loaded from: classes9.dex */
class CamelliaUtil {
    CamelliaUtil() {
    }

    static AlgorithmIdentifier determineKeyEncAlg(KeyParameter keyParameter) {
        ASN1ObjectIdentifier aSN1ObjectIdentifier;
        int length = keyParameter.getKey().length * 8;
        if (length == 128) {
            aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia128_wrap;
        } else if (length == 192) {
            aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia192_wrap;
        } else {
            if (length != 256) {
                throw new IllegalArgumentException("illegal keysize in Camellia");
            }
            aSN1ObjectIdentifier = NTTObjectIdentifiers.id_camellia256_wrap;
        }
        return new AlgorithmIdentifier(aSN1ObjectIdentifier);
    }
}
