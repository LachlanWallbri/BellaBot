package org.jboss.netty.handler.codec.http.websocketx;

/* loaded from: classes7.dex */
public class WebSocketHandshakeException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public WebSocketHandshakeException(String str) {
        super(str);
    }

    public WebSocketHandshakeException(String str, Throwable th) {
        super(str, th);
    }
}
