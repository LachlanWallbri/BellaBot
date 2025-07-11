package org.jboss.netty.handler.codec.http.websocketx;

/* loaded from: classes7.dex */
public enum WebSocketVersion {
    UNKNOWN,
    V00,
    V08,
    V13;

    public String toHttpHeaderValue() {
        if (this == V00) {
            return "0";
        }
        if (this == V08) {
            return "8";
        }
        if (this == V13) {
            return "13";
        }
        throw new IllegalStateException("Unknown web socket version: " + this);
    }
}
