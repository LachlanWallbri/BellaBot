package io.netty.handler.codec.http;

import io.netty.util.internal.ObjectUtil;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import kotlin.text.Typography;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class QueryStringEncoder {
    private final String charsetName;
    private boolean hasParams;
    private final StringBuilder uriBuilder;

    public QueryStringEncoder(String str) {
        this(str, HttpConstants.DEFAULT_CHARSET);
    }

    public QueryStringEncoder(String str, Charset charset) {
        this.uriBuilder = new StringBuilder(str);
        this.charsetName = charset.name();
    }

    public void addParam(String str, String str2) {
        ObjectUtil.checkNotNull(str, "name");
        if (this.hasParams) {
            this.uriBuilder.append(Typography.amp);
        } else {
            this.uriBuilder.append('?');
            this.hasParams = true;
        }
        appendComponent(str, this.charsetName, this.uriBuilder);
        if (str2 != null) {
            this.uriBuilder.append('=');
            appendComponent(str2, this.charsetName, this.uriBuilder);
        }
    }

    public URI toUri() throws URISyntaxException {
        return new URI(toString());
    }

    public String toString() {
        return this.uriBuilder.toString();
    }

    private static void appendComponent(String str, String str2, StringBuilder sb) {
        try {
            String encode = URLEncoder.encode(str, str2);
            int indexOf = encode.indexOf(43);
            if (indexOf == -1) {
                sb.append(encode);
                return;
            }
            sb.append((CharSequence) encode, 0, indexOf);
            sb.append("%20");
            int length = encode.length();
            while (true) {
                indexOf++;
                if (indexOf >= length) {
                    return;
                }
                char charAt = encode.charAt(indexOf);
                if (charAt != '+') {
                    sb.append(charAt);
                } else {
                    sb.append("%20");
                }
            }
        } catch (UnsupportedEncodingException unused) {
            throw new UnsupportedCharsetException(str2);
        }
    }
}
