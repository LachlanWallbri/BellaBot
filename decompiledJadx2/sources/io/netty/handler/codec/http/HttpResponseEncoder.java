package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class HttpResponseEncoder extends HttpObjectEncoder<HttpResponse> {
    @Override // io.netty.handler.codec.http.HttpObjectEncoder, io.netty.handler.codec.MessageToMessageEncoder
    public boolean acceptOutboundMessage(Object obj) throws Exception {
        return super.acceptOutboundMessage(obj) && !(obj instanceof HttpRequest);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http.HttpObjectEncoder
    public void encodeInitialLine(ByteBuf byteBuf, HttpResponse httpResponse) throws Exception {
        httpResponse.protocolVersion().encode(byteBuf);
        byteBuf.writeByte(32);
        httpResponse.status().encode(byteBuf);
        ByteBufUtil.writeShortBE(byteBuf, 3338);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http.HttpObjectEncoder
    public void sanitizeHeadersBeforeEncode(HttpResponse httpResponse, boolean z) {
        if (z) {
            HttpResponseStatus status = httpResponse.status();
            if (status.codeClass() == HttpStatusClass.INFORMATIONAL || status.code() == HttpResponseStatus.NO_CONTENT.code()) {
                httpResponse.headers().remove(HttpHeaderNames.CONTENT_LENGTH);
                httpResponse.headers().remove(HttpHeaderNames.TRANSFER_ENCODING);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.handler.codec.http.HttpObjectEncoder
    public boolean isContentAlwaysEmpty(HttpResponse httpResponse) {
        HttpResponseStatus status = httpResponse.status();
        if (status.codeClass() != HttpStatusClass.INFORMATIONAL) {
            return status.code() == HttpResponseStatus.NO_CONTENT.code() || status.code() == HttpResponseStatus.NOT_MODIFIED.code();
        }
        if (status.code() == HttpResponseStatus.SWITCHING_PROTOCOLS.code()) {
            return httpResponse.headers().contains(HttpHeaderNames.SEC_WEBSOCKET_VERSION);
        }
        return true;
    }
}
