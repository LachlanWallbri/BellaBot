package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import kotlin.text.Typography;
import org.jetbrains.anko.DimensionsKt;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class QueryStringEncoder {
    private static final char[] CHAR_MAP = "0123456789ABCDEF".toCharArray();
    private static final byte WRITE_UTF_UNKNOWN = 63;
    private final Charset charset;
    private boolean hasParams;
    private final StringBuilder uriBuilder;

    private static boolean dontNeedEncoding(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || ((c >= '0' && c <= '9') || c == '-' || c == '_' || c == '.' || c == '*');
    }

    public QueryStringEncoder(String str) {
        this(str, HttpConstants.DEFAULT_CHARSET);
    }

    public QueryStringEncoder(String str, Charset charset) {
        ObjectUtil.checkNotNull(charset, "charset");
        this.uriBuilder = new StringBuilder(str);
        this.charset = CharsetUtil.UTF_8.equals(charset) ? null : charset;
    }

    public void addParam(String str, String str2) {
        ObjectUtil.checkNotNull(str, "name");
        if (this.hasParams) {
            this.uriBuilder.append(Typography.amp);
        } else {
            this.uriBuilder.append('?');
            this.hasParams = true;
        }
        encodeComponent(str);
        if (str2 != null) {
            this.uriBuilder.append('=');
            encodeComponent(str2);
        }
    }

    private void encodeComponent(CharSequence charSequence) {
        if (this.charset == null) {
            encodeUtf8Component(charSequence);
        } else {
            encodeNonUtf8Component(charSequence);
        }
    }

    public URI toUri() throws URISyntaxException {
        return new URI(toString());
    }

    public String toString() {
        return this.uriBuilder.toString();
    }

    private void encodeNonUtf8Component(CharSequence charSequence) {
        int length = charSequence.length();
        char[] cArr = null;
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (dontNeedEncoding(charAt)) {
                this.uriBuilder.append(charAt);
                i++;
            } else {
                if (cArr == null) {
                    cArr = new char[charSequence.length() - i];
                }
                int i2 = i;
                int i3 = 0;
                do {
                    cArr[i3] = charAt;
                    i3++;
                    i2++;
                    if (i2 >= charSequence.length()) {
                        break;
                    } else {
                        charAt = charSequence.charAt(i2);
                    }
                } while (!dontNeedEncoding(charAt));
                for (byte b : new String(cArr, 0, i3).getBytes(this.charset)) {
                    appendEncoded(b);
                }
                i = i2;
            }
        }
    }

    private void encodeUtf8Component(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!dontNeedEncoding(charSequence.charAt(i))) {
                encodeUtf8Component(charSequence, i, length);
                return;
            }
        }
        this.uriBuilder.append(charSequence);
    }

    private void encodeUtf8Component(CharSequence charSequence, int i, int i2) {
        if (i > 0) {
            this.uriBuilder.append(charSequence, 0, i);
        }
        encodeUtf8ComponentSlow(charSequence, i, i2);
    }

    private void encodeUtf8ComponentSlow(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if (charAt < 128) {
                if (dontNeedEncoding(charAt)) {
                    this.uriBuilder.append(charAt);
                } else {
                    appendEncoded(charAt);
                }
            } else if (charAt < 2048) {
                appendEncoded((charAt >> 6) | 192);
                appendEncoded((charAt & '?') | 128);
            } else if (StringUtil.isSurrogate(charAt)) {
                if (!Character.isHighSurrogate(charAt)) {
                    appendEncoded(63);
                } else {
                    i++;
                    if (i == charSequence.length()) {
                        appendEncoded(63);
                        return;
                    }
                    writeUtf8Surrogate(charAt, charSequence.charAt(i));
                }
            } else {
                appendEncoded((charAt >> '\f') | 224);
                appendEncoded(((charAt >> 6) & 63) | 128);
                appendEncoded((charAt & '?') | 128);
            }
            i++;
        }
    }

    private void writeUtf8Surrogate(char c, char c2) {
        if (!Character.isLowSurrogate(c2)) {
            appendEncoded(63);
            if (Character.isHighSurrogate(c2)) {
                c2 = '?';
            }
            appendEncoded(c2);
            return;
        }
        int codePoint = Character.toCodePoint(c, c2);
        appendEncoded((codePoint >> 18) | DimensionsKt.HDPI);
        appendEncoded(((codePoint >> 12) & 63) | 128);
        appendEncoded(((codePoint >> 6) & 63) | 128);
        appendEncoded((codePoint & 63) | 128);
    }

    private void appendEncoded(int i) {
        StringBuilder sb = this.uriBuilder;
        sb.append('%');
        sb.append(forDigit(i >> 4));
        sb.append(forDigit(i));
    }

    private static char forDigit(int i) {
        return CHAR_MAP[i & 15];
    }
}
