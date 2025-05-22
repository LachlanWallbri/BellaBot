package org.jboss.netty.handler.codec.http;

import org.apache.http.protocol.HTTP;
import org.jboss.netty.buffer.ChannelBuffer;

/* loaded from: classes7.dex */
public class HttpResponseEncoder extends HttpMessageEncoder {
    @Override // org.jboss.netty.handler.codec.http.HttpMessageEncoder
    protected void encodeInitialLine(ChannelBuffer channelBuffer, HttpMessage httpMessage) throws Exception {
        HttpResponse httpResponse = (HttpResponse) httpMessage;
        channelBuffer.writeBytes(httpResponse.getProtocolVersion().toString().getBytes(HTTP.ASCII));
        channelBuffer.writeByte(32);
        channelBuffer.writeBytes(String.valueOf(httpResponse.getStatus().getCode()).getBytes(HTTP.ASCII));
        channelBuffer.writeByte(32);
        channelBuffer.writeBytes(String.valueOf(httpResponse.getStatus().getReasonPhrase()).getBytes(HTTP.ASCII));
        channelBuffer.writeByte(13);
        channelBuffer.writeByte(10);
    }
}
