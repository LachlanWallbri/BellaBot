package org.apache.commons.compress.utils;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* loaded from: classes9.dex */
public class Charsets {

    @Deprecated
    public static final Charset ISO_8859_1 = StandardCharsets.ISO_8859_1;

    @Deprecated
    public static final Charset US_ASCII = StandardCharsets.US_ASCII;

    @Deprecated
    public static final Charset UTF_16 = StandardCharsets.UTF_16;

    @Deprecated
    public static final Charset UTF_16BE = StandardCharsets.UTF_16BE;

    @Deprecated
    public static final Charset UTF_16LE = StandardCharsets.UTF_16LE;

    @Deprecated
    public static final Charset UTF_8 = StandardCharsets.UTF_8;

    public static Charset toCharset(Charset charset) {
        return charset == null ? Charset.defaultCharset() : charset;
    }

    public static Charset toCharset(String str) {
        return str == null ? Charset.defaultCharset() : Charset.forName(str);
    }
}
