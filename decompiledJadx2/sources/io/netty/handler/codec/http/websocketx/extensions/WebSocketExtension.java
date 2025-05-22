package io.netty.handler.codec.http.websocketx.extensions;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface WebSocketExtension {
    public static final int RSV1 = 4;
    public static final int RSV2 = 2;
    public static final int RSV3 = 1;

    WebSocketExtensionDecoder newExtensionDecoder();

    WebSocketExtensionEncoder newExtensionEncoder();

    int rsv();
}
