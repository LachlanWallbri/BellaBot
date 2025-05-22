package com.google.api.client.util.escape;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class CharEscapers {
    private static final Escaper APPLICATION_X_WWW_FORM_URLENCODED = new PercentEscaper("-_.*", true);
    private static final Escaper URI_ESCAPER = new PercentEscaper("-_.*", false);
    private static final Escaper URI_PATH_ESCAPER = new PercentEscaper(PercentEscaper.SAFEPATHCHARS_URLENCODER);
    private static final Escaper URI_RESERVED_ESCAPER = new PercentEscaper(PercentEscaper.SAFE_PLUS_RESERVED_CHARS_URLENCODER);
    private static final Escaper URI_USERINFO_ESCAPER = new PercentEscaper(PercentEscaper.SAFEUSERINFOCHARS_URLENCODER);
    private static final Escaper URI_QUERY_STRING_ESCAPER = new PercentEscaper("-_.!~*'()@:$,;/?:");

    @Deprecated
    public static String escapeUri(String str) {
        return APPLICATION_X_WWW_FORM_URLENCODED.escape(str);
    }

    public static String escapeUriConformant(String str) {
        return URI_ESCAPER.escape(str);
    }

    public static String decodeUri(String str) {
        try {
            return URLDecoder.decode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeUriPath(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLDecoder.decode(str.replace("+", "%2B"), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String escapeUriPath(String str) {
        return URI_PATH_ESCAPER.escape(str);
    }

    public static String escapeUriPathWithoutReserved(String str) {
        return URI_RESERVED_ESCAPER.escape(str);
    }

    public static String escapeUriUserInfo(String str) {
        return URI_USERINFO_ESCAPER.escape(str);
    }

    public static String escapeUriQuery(String str) {
        return URI_QUERY_STRING_ESCAPER.escape(str);
    }

    private CharEscapers() {
    }
}
