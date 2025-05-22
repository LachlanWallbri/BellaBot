package com.google.api.client.util;

import com.google.common.io.BaseEncoding;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes2.dex */
public class Base64 {
    public static byte[] encodeBase64(byte[] bArr) {
        return StringUtils.getBytesUtf8(encodeBase64String(bArr));
    }

    public static String encodeBase64String(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return BaseEncoding.base64().encode(bArr);
    }

    public static byte[] encodeBase64URLSafe(byte[] bArr) {
        return StringUtils.getBytesUtf8(encodeBase64URLSafeString(bArr));
    }

    public static String encodeBase64URLSafeString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return BaseEncoding.base64Url().omitPadding().encode(bArr);
    }

    public static byte[] decodeBase64(byte[] bArr) {
        return decodeBase64(StringUtils.newStringUtf8(bArr));
    }

    public static byte[] decodeBase64(String str) {
        if (str == null) {
            return null;
        }
        try {
            return BaseEncoding.base64().decode(str);
        } catch (IllegalArgumentException e) {
            if (e.getCause() instanceof BaseEncoding.DecodingException) {
                return BaseEncoding.base64Url().decode(str.trim());
            }
            throw e;
        }
    }

    private Base64() {
    }
}
