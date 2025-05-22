package com.amazonaws.services.kms.model;

import java.util.HashMap;
import java.util.Map;

/* loaded from: classes.dex */
public enum SigningAlgorithmSpec {
    RSASSA_PSS_SHA_256("RSASSA_PSS_SHA_256"),
    RSASSA_PSS_SHA_384("RSASSA_PSS_SHA_384"),
    RSASSA_PSS_SHA_512("RSASSA_PSS_SHA_512"),
    RSASSA_PKCS1_V1_5_SHA_256("RSASSA_PKCS1_V1_5_SHA_256"),
    RSASSA_PKCS1_V1_5_SHA_384("RSASSA_PKCS1_V1_5_SHA_384"),
    RSASSA_PKCS1_V1_5_SHA_512("RSASSA_PKCS1_V1_5_SHA_512"),
    ECDSA_SHA_256("ECDSA_SHA_256"),
    ECDSA_SHA_384("ECDSA_SHA_384"),
    ECDSA_SHA_512("ECDSA_SHA_512");

    private static final Map<String, SigningAlgorithmSpec> enumMap = new HashMap();
    private String value;

    static {
        enumMap.put("RSASSA_PSS_SHA_256", RSASSA_PSS_SHA_256);
        enumMap.put("RSASSA_PSS_SHA_384", RSASSA_PSS_SHA_384);
        enumMap.put("RSASSA_PSS_SHA_512", RSASSA_PSS_SHA_512);
        enumMap.put("RSASSA_PKCS1_V1_5_SHA_256", RSASSA_PKCS1_V1_5_SHA_256);
        enumMap.put("RSASSA_PKCS1_V1_5_SHA_384", RSASSA_PKCS1_V1_5_SHA_384);
        enumMap.put("RSASSA_PKCS1_V1_5_SHA_512", RSASSA_PKCS1_V1_5_SHA_512);
        enumMap.put("ECDSA_SHA_256", ECDSA_SHA_256);
        enumMap.put("ECDSA_SHA_384", ECDSA_SHA_384);
        enumMap.put("ECDSA_SHA_512", ECDSA_SHA_512);
    }

    SigningAlgorithmSpec(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    public static SigningAlgorithmSpec fromValue(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        }
        if (enumMap.containsKey(str)) {
            return enumMap.get(str);
        }
        throw new IllegalArgumentException("Cannot create enum from " + str + " value!");
    }
}
