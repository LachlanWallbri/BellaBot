package com.pudutech.pd_network.sn;

import android.util.Base64;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import java.nio.charset.Charset;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SNUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\b\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0002\"\u0016\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, m3961d2 = {"CHARSET", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "CIPHER_ALGORITHM", "", "KEY_ALGORITHM", "aesDecrypt", TransferTable.COLUMN_KEY, "aesEncrypt", "pd_network_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class SNUtilsKt {
    private static final Charset CHARSET = Charset.forName("UTF-8");
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS7Padding";
    private static final String KEY_ALGORITHM = "AES";

    /* JADX INFO: Access modifiers changed from: private */
    public static final String aesEncrypt(String str, String str2) {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        Charset CHARSET2 = CHARSET;
        Intrinsics.checkExpressionValueIsNotNull(CHARSET2, "CHARSET");
        if (str2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = str2.getBytes(CHARSET2);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        cipher.init(1, new SecretKeySpec(bytes, "AES"), new IvParameterSpec(bytes));
        Charset CHARSET3 = CHARSET;
        Intrinsics.checkExpressionValueIsNotNull(CHARSET3, "CHARSET");
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes2 = str.getBytes(CHARSET3);
        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
        String encodeToString = Base64.encodeToString(cipher.doFinal(bytes2), 2);
        Intrinsics.checkExpressionValueIsNotNull(encodeToString, "Base64.encodeToString(encrypted, Base64.NO_WRAP)");
        return encodeToString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String aesDecrypt(String str, String str2) {
        Charset CHARSET2 = CHARSET;
        Intrinsics.checkExpressionValueIsNotNull(CHARSET2, "CHARSET");
        if (str == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = str.getBytes(CHARSET2);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] decode = Base64.decode(bytes, 2);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        Charset CHARSET3 = CHARSET;
        Intrinsics.checkExpressionValueIsNotNull(CHARSET3, "CHARSET");
        if (str2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes2 = str2.getBytes(CHARSET3);
        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
        cipher.init(2, new SecretKeySpec(bytes2, "AES"), new IvParameterSpec(bytes2));
        byte[] original = cipher.doFinal(decode);
        Intrinsics.checkExpressionValueIsNotNull(original, "original");
        Charset CHARSET4 = CHARSET;
        Intrinsics.checkExpressionValueIsNotNull(CHARSET4, "CHARSET");
        return new String(original, CHARSET4);
    }
}
