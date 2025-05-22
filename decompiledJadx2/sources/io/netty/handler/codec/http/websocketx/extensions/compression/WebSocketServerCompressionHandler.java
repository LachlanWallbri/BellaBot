package io.netty.handler.codec.http.websocketx.extensions.compression;

import io.netty.handler.codec.http.websocketx.extensions.WebSocketServerExtensionHandler;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class WebSocketServerCompressionHandler extends WebSocketServerExtensionHandler {
    public WebSocketServerCompressionHandler() {
        super(new PerMessageDeflateServerExtensionHandshaker(), new DeflateFrameServerExtensionHandshaker());
    }
}
