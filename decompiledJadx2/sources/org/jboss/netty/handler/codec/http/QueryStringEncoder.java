package org.jboss.netty.handler.codec.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import org.mozilla.javascript.ES6Iterator;

/* loaded from: classes7.dex */
public class QueryStringEncoder {
    private final Charset charset;
    private final List<Param> params;
    private final String uri;

    public QueryStringEncoder(String str) {
        this(str, HttpConstants.DEFAULT_CHARSET);
    }

    public QueryStringEncoder(String str, Charset charset) {
        this.params = new ArrayList();
        if (str == null) {
            throw new NullPointerException("uri");
        }
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.uri = str;
        this.charset = charset;
    }

    @Deprecated
    public QueryStringEncoder(String str, String str2) {
        this(str, Charset.forName(str2));
    }

    public void addParam(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        if (str2 == null) {
            throw new NullPointerException(ES6Iterator.VALUE_PROPERTY);
        }
        this.params.add(new Param(str, str2));
    }

    public URI toUri() throws URISyntaxException {
        return new URI(toString());
    }

    public String toString() {
        if (this.params.isEmpty()) {
            return this.uri;
        }
        StringBuilder sb = new StringBuilder(this.uri);
        sb.append("?");
        for (int i = 0; i < this.params.size(); i++) {
            Param param = this.params.get(i);
            sb.append(encodeComponent(param.name, this.charset));
            sb.append("=");
            sb.append(encodeComponent(param.value, this.charset));
            if (i != this.params.size() - 1) {
                sb.append("&");
            }
        }
        return sb.toString();
    }

    private static String encodeComponent(String str, Charset charset) {
        try {
            return URLEncoder.encode(str, charset.name()).replaceAll("\\+", "%20");
        } catch (UnsupportedEncodingException unused) {
            throw new UnsupportedCharsetException(charset.name());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class Param {
        final String name;
        final String value;

        Param(String str, String str2) {
            this.value = str2;
            this.name = str;
        }
    }
}
