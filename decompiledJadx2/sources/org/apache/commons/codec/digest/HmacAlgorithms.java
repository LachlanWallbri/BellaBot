package org.apache.commons.codec.digest;

import com.amazonaws.services.p048s3.internal.Constants;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public enum HmacAlgorithms {
    HMAC_MD5("HmacMD5"),
    HMAC_SHA_1(Constants.HMAC_SHA1_ALGORITHM),
    HMAC_SHA_224("HmacSHA224"),
    HMAC_SHA_256("HmacSHA256"),
    HMAC_SHA_384("HmacSHA384"),
    HMAC_SHA_512("HmacSHA512");

    private final String name;

    HmacAlgorithms(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.name;
    }
}
