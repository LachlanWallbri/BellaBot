package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.util.AsciiString;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class WebSocketScheme {

    /* renamed from: WS */
    public static final WebSocketScheme f8374WS = new WebSocketScheme(80, "ws");
    public static final WebSocketScheme WSS = new WebSocketScheme(443, "wss");
    private final AsciiString name;
    private final int port;

    private WebSocketScheme(int i, String str) {
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
        if (!(obj instanceof WebSocketScheme)) {
            return false;
        }
        WebSocketScheme webSocketScheme = (WebSocketScheme) obj;
        return webSocketScheme.port() == this.port && webSocketScheme.name().equals(this.name);
    }

    public int hashCode() {
        return (this.port * 31) + this.name.hashCode();
    }

    public String toString() {
        return this.name.toString();
    }
}
