package org.jboss.netty.util;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.IdentityHashMap;
import java.util.Map;

/* loaded from: classes7.dex */
public final class CharsetUtil {
    public static final Charset UTF_16 = Charset.forName("UTF-16");
    public static final Charset UTF_16BE = Charset.forName("UTF-16BE");
    public static final Charset UTF_16LE = Charset.forName("UTF-16LE");
    public static final Charset UTF_8 = Charset.forName("UTF-8");
    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset US_ASCII = Charset.forName("US-ASCII");
    private static final ThreadLocal<Map<Charset, CharsetEncoder>> encoders = new ThreadLocal<Map<Charset, CharsetEncoder>>() { // from class: org.jboss.netty.util.CharsetUtil.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Map<Charset, CharsetEncoder> initialValue() {
            return new IdentityHashMap();
        }
    };
    private static final ThreadLocal<Map<Charset, CharsetDecoder>> decoders = new ThreadLocal<Map<Charset, CharsetDecoder>>() { // from class: org.jboss.netty.util.CharsetUtil.2
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Map<Charset, CharsetDecoder> initialValue() {
            return new IdentityHashMap();
        }
    };

    public static CharsetEncoder getEncoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        Map<Charset, CharsetEncoder> map = encoders.get();
        CharsetEncoder charsetEncoder = map.get(charset);
        if (charsetEncoder != null) {
            charsetEncoder.reset();
            charsetEncoder.onMalformedInput(CodingErrorAction.REPLACE);
            charsetEncoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
            return charsetEncoder;
        }
        CharsetEncoder newEncoder = charset.newEncoder();
        newEncoder.onMalformedInput(CodingErrorAction.REPLACE);
        newEncoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
        map.put(charset, newEncoder);
        return newEncoder;
    }

    public static CharsetDecoder getDecoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        Map<Charset, CharsetDecoder> map = decoders.get();
        CharsetDecoder charsetDecoder = map.get(charset);
        if (charsetDecoder != null) {
            charsetDecoder.reset();
            charsetDecoder.onMalformedInput(CodingErrorAction.REPLACE);
            charsetDecoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
            return charsetDecoder;
        }
        CharsetDecoder newDecoder = charset.newDecoder();
        newDecoder.onMalformedInput(CodingErrorAction.REPLACE);
        newDecoder.onUnmappableCharacter(CodingErrorAction.REPLACE);
        map.put(charset, newDecoder);
        return newDecoder;
    }

    private CharsetUtil() {
    }
}
