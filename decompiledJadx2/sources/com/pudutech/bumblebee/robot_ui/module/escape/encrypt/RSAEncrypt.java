package com.pudutech.bumblebee.robot_ui.module.escape.encrypt;

import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RSAEncrypt.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0004J\u001b\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\fH\u0007¢\u0006\u0002\u0010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/module/escape/encrypt/RSAEncrypt;", "", "()V", "ALGO", "", "CHARSET", "pubKeyStr", "encryptByPublicKey", "data", "main", "", "args", "", "([Ljava/lang/String;)V", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes3.dex */
public final class RSAEncrypt {
    private static final String ALGO = "RSA";
    private static final String CHARSET = "UTF-8";
    public static final RSAEncrypt INSTANCE = new RSAEncrypt();
    private static final String pubKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/ADRlxj6ZAstGGNaFC12gOzddk3cqKQa7/joXEJr78gFnqoSCY+dFc+Nt5DARZqK1DgSxyYinBuonBk7Pv/tVUQwP31xqf5FKSPzI3v5dU+qIi14r4xLVPNvgP/0EvcH/x2d5PpFm+mw6Plh/rzMlkwo5IpnjZwiNBWFrjzdbKwIDAQAB";

    private RSAEncrypt() {
    }

    public final String encryptByPublicKey(String data) throws Exception {
        Intrinsics.checkParameterIsNotNull(data, "data");
        PublicKey generatePublic = KeyFactory.getInstance(ALGO).generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(pubKeyStr)));
        if (generatePublic == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.security.interfaces.RSAPublicKey");
        }
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, (RSAPublicKey) generatePublic);
        Base64.Encoder encoder = Base64.getEncoder();
        Charset forName = Charset.forName("UTF-8");
        Intrinsics.checkExpressionValueIsNotNull(forName, "Charset.forName(charsetName)");
        byte[] bytes = data.getBytes(forName);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        String encodeToString = encoder.encodeToString(cipher.doFinal(bytes));
        Intrinsics.checkExpressionValueIsNotNull(encodeToString, "Base64.getEncoder()\n    …Array(charset(CHARSET))))");
        return encodeToString;
    }

    @JvmStatic
    public static final void main(String[] args) {
        Intrinsics.checkParameterIsNotNull(args, "args");
        try {
            System.out.println((Object) ("encryDataByPublicKey: " + INSTANCE.encryptByPublicKey("12.2345")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
