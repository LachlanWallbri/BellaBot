package io.netty.handler.codec.http.websocketx;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class WebSocketHandshakeException extends RuntimeException {
    private static final long serialVersionUID = 1;

    public WebSocketHandshakeException(String str) {
        super(str);
    }

    public WebSocketHandshakeException(String str, Throwable th) {
        super(str, th);
    }
}
