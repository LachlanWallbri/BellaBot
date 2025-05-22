package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.util.AsciiString;
import org.apache.http.HttpHost;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HttpScheme {
    public static final HttpScheme HTTP = new HttpScheme(80, HttpHost.DEFAULT_SCHEME_NAME);
    public static final HttpScheme HTTPS = new HttpScheme(443, "https");
    private final AsciiString name;
    private final int port;

    private HttpScheme(int i, String str) {
        this.port = i;
        this.name = AsciiString.cached(str);
    }

    public AsciiString name() {
        return this.name;
    }

    public int port() {
        return this.port;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof HttpScheme)) {
            return false;
        }
        HttpScheme httpScheme = (HttpScheme) obj;
        return httpScheme.port() == this.port && httpScheme.name().equals(this.name);
    }

    public int hashCode() {
        return (this.port * 31) + this.name.hashCode();
    }

    public String toString() {
        return this.name.toString();
    }
}
