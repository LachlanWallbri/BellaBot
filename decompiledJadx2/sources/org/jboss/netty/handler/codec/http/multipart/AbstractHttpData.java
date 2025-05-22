package org.jboss.netty.handler.codec.http.multipart;

import java.nio.charset.Charset;
import org.jboss.netty.handler.codec.http.HttpConstants;

/* loaded from: classes7.dex */
public abstract class AbstractHttpData implements HttpData {
    protected Charset charset = HttpConstants.DEFAULT_CHARSET;
    protected boolean completed;
    protected long definedSize;
    protected final String name;
    protected long size;

    public AbstractHttpData(String str, Charset charset, long j) {
        if (str == null) {
            throw new NullPointerException("name");
        }
        String trim = str.trim();
        if (trim.length() == 0) {
            throw new IllegalArgumentException("empty name");
        }
        for (int i = 0; i < trim.length(); i++) {
            char charAt = trim.charAt(i);
            if (charAt > 127) {
                throw new IllegalArgumentException("name contains non-ascii character: " + trim);
            }
            if (charAt != ' ' && charAt != ',' && charAt != ';' && charAt != '=') {
                switch (charAt) {
                    case '\t':
                    case '\n':
                    case 11:
                    case '\f':
                    case '\r':
                        break;
                    default:
                }
            }
            throw new IllegalArgumentException("name contains one of the following prohibited characters: =,; \\t\\r\\n\\v\\f: " + trim);
        }
        this.name = trim;
        if (charset != null) {
            setCharset(charset);
        }
        this.definedSize = j;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.InterfaceHttpData
    public String getName() {
        return this.name;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public boolean isCompleted() {
        return this.completed;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public Charset getCharset() {
        return this.charset;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public void setCharset(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }

    @Override // org.jboss.netty.handler.codec.http.multipart.HttpData
    public long length() {
        return this.size;
    }
}
