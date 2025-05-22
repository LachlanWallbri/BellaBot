package io.netty.handler.codec.http.websocketx.extensions;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* loaded from: classes8.dex */
public interface WebSocketExtensionFilterProvider {
    public static final WebSocketExtensionFilterProvider DEFAULT = new WebSocketExtensionFilterProvider() { // from class: io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilterProvider.1
        @Override // io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilterProvider
        public WebSocketExtensionFilter encoderFilter() {
            return WebSocketExtensionFilter.NEVER_SKIP;
        }

        @Override // io.netty.handler.codec.http.websocketx.extensions.WebSocketExtensionFilterProvider
        public WebSocketExtensionFilter decoderFilter() {
            return WebSocketExtensionFilter.NEVER_SKIP;
        }
    };

    WebSocketExtensionFilter decoderFilter();

    WebSocketExtensionFilter encoderFilter();
}
