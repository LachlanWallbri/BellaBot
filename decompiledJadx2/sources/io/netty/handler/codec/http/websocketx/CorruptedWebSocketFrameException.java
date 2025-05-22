package io.netty.handler.codec.http.websocketx;

import io.netty.handler.codec.CorruptedFrameException;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes8.dex */
public final class CorruptedWebSocketFrameException extends CorruptedFrameException {
    private static final long serialVersionUID = 3918055132492988338L;
    private final WebSocketCloseStatus closeStatus;

    public CorruptedWebSocketFrameException() {
        this(WebSocketCloseStatus.PROTOCOL_ERROR, null, null);
    }

    public CorruptedWebSocketFrameException(WebSocketCloseStatus webSocketCloseStatus, String str, Throwable th) {
        super(str == null ? webSocketCloseStatus.reasonText() : str, th);
        this.closeStatus = webSocketCloseStatus;
    }

    public CorruptedWebSocketFrameException(WebSocketCloseStatus webSocketCloseStatus, String str) {
        this(webSocketCloseStatus, str, null);
    }

    public CorruptedWebSocketFrameException(WebSocketCloseStatus webSocketCloseStatus, Throwable th) {
        this(webSocketCloseStatus, null, th);
    }

    public WebSocketCloseStatus closeStatus() {
        return this.closeStatus;
    }
}
