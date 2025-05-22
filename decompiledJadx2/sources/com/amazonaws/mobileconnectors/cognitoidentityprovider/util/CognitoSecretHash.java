package com.amazonaws.mobileconnectors.cognitoidentityprovider.util;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.exceptions.CognitoInternalErrorException;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.exceptions.CognitoParameterInvalidException;
import com.amazonaws.util.Base64;
import com.amazonaws.util.StringUtils;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public final class CognitoSecretHash {
    private static final String HMAC_SHA_256 = "HmacSHA256";

    public static String getSecretHash(String str, String str2, String str3) {
        if (str == null) {
            throw new CognitoParameterInvalidException("user ID cannot be null");
        }
        if (str2 == null) {
            throw new CognitoParameterInvalidException("client ID cannot be null");
        }
        if (StringUtils.isBlank(str3)) {
            return null;
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(str3.getBytes(StringUtils.UTF8), HMAC_SHA_256);
        try {
            Mac mac = Mac.getInstance(HMAC_SHA_256);
            mac.init(secretKeySpec);
            mac.update(str.getBytes(StringUtils.UTF8));
            return new String(Base64.encode(mac.doFinal(str2.getBytes(StringUtils.UTF8))));
        } catch (Exception unused) {
            throw new CognitoInternalErrorException("errors in HMAC calculation");
        }
    }
}
