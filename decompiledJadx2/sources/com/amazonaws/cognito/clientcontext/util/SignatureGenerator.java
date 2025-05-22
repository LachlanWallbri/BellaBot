package com.amazonaws.cognito.clientcontext.util;

import android.util.Base64;
import android.util.Log;
import com.amazonaws.cognito.clientcontext.data.ConfigurationConstant;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: classes.dex */
public class SignatureGenerator {
    private static final String ALGORITHM = "HmacSHA256";
    private static final String TAG = "HMAC_SHA256_Signature";

    public String getSignature(String str, String str2, String str3) {
        try {
            Mac mac = Mac.getInstance(ALGORITHM);
            mac.init(new SecretKeySpec(str2.getBytes(ConfigurationConstant.DEFAULT_CHARSET), ALGORITHM));
            mac.update(str3.getBytes(ConfigurationConstant.DEFAULT_CHARSET));
            return Base64.encodeToString(mac.doFinal(str.getBytes(ConfigurationConstant.DEFAULT_CHARSET)), 0);
        } catch (Exception e) {
            logWarning(e);
            return "";
        }
    }

    private void logWarning(Exception exc) {
        Log.w(TAG, "Exception while completing context data signature", exc);
    }
}
