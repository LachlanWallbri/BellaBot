package com.aliyun.alink.linksdk.alcs.lpbs.p037b;

import com.amazonaws.services.p048s3.internal.Constants;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: SecureUtils.java */
/* renamed from: com.aliyun.alink.linksdk.alcs.lpbs.b.b */
/* loaded from: classes.dex */
public class C0941b {
    /* renamed from: a */
    public static byte[] m365a(String str, String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), Constants.HMAC_SHA1_ALGORITHM);
            Mac mac = Mac.getInstance(Constants.HMAC_SHA1_ALGORITHM);
            mac.init(secretKeySpec);
            return mac.doFinal(str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException unused) {
            return null;
        }
    }
}
