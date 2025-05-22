package com.amazonaws.services.p048s3.model;

import com.alibaba.sdk.android.oss.model.ObjectMetadata;

/* loaded from: classes.dex */
public enum SSEAlgorithm {
    AES256(ObjectMetadata.AES_256_SERVER_SIDE_ENCRYPTION),
    KMS("aws:kms");

    private final String algorithm;

    public String getAlgorithm() {
        return this.algorithm;
    }

    SSEAlgorithm(String str) {
        this.algorithm = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.algorithm;
    }

    public static SSEAlgorithm fromString(String str) {
        if (str == null) {
            return null;
        }
        for (SSEAlgorithm sSEAlgorithm : values()) {
            if (sSEAlgorithm.getAlgorithm().equals(str)) {
                return sSEAlgorithm;
            }
        }
        throw new IllegalArgumentException("Unsupported algorithm " + str);
    }

    public static SSEAlgorithm getDefault() {
        return AES256;
    }
}
