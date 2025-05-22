package io.netty.handler.codec.http;

import io.netty.util.CharsetUtil;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class HttpConstants {
    public static final byte COLON = 58;
    public static final byte COMMA = 44;

    /* renamed from: CR */
    public static final byte f8494CR = 13;
    public static final Charset DEFAULT_CHARSET = CharsetUtil.UTF_8;
    public static final byte DOUBLE_QUOTE = 34;
    public static final byte EQUALS = 61;

    /* renamed from: HT */
    public static final byte f8495HT = 9;

    /* renamed from: LF */
    public static final byte f8496LF = 10;
    public static final byte SEMICOLON = 59;

    /* renamed from: SP */
    public static final byte f8497SP = 32;
    public static final char SP_CHAR = ' ';

    private HttpConstants() {
    }
}
