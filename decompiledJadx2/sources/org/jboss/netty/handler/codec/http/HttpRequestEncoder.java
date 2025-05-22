package org.jboss.netty.handler.codec.http;

import org.apache.http.protocol.HTTP;
import org.jboss.netty.buffer.ChannelBuffer;

/* loaded from: classes7.dex */
public class HttpRequestEncoder extends HttpMessageEncoder {
    @Override // org.jboss.netty.handler.codec.http.HttpMessageEncoder
    protected void encodeInitialLine(ChannelBuffer channelBuffer, HttpMessage httpMessage) throws Exception {
        HttpRequest httpRequest = (HttpRequest) httpMessage;
        channelBuffer.writeBytes(httpRequest.getMethod().toString().getBytes(HTTP.ASCII));
        channelBuffer.writeByte(32);
        channelBuffer.writeBytes(httpRequest.getUri().getBytes(HTTP.ASCII));
        channelBuffer.writeByte(32);
        channelBuffer.writeBytes(httpRequest.getProtocolVersion().toString().getBytes(HTTP.ASCII));
        channelBuffer.writeByte(13);
        channelBuffer.writeByte(10);
    }
}
