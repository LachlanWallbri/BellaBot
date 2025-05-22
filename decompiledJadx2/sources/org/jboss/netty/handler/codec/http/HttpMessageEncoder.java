package org.jboss.netty.handler.codec.http;

import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.http.protocol.HTTP;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.jboss.netty.util.CharsetUtil;

/* loaded from: classes7.dex */
public abstract class HttpMessageEncoder extends OneToOneEncoder {
    private static final byte[] CRLF = {13, 10};
    private static final ChannelBuffer LAST_CHUNK = ChannelBuffers.copiedBuffer("0\r\n\r\n", CharsetUtil.US_ASCII);
    private volatile boolean chunked;

    protected abstract void encodeInitialLine(ChannelBuffer channelBuffer, HttpMessage httpMessage) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.jboss.netty.handler.codec.oneone.OneToOneEncoder
    public Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
        boolean isTransferEncodingChunked;
        if (obj instanceof HttpMessage) {
            HttpMessage httpMessage = (HttpMessage) obj;
            if (httpMessage.isChunked()) {
                if (!HttpCodecUtil.isTransferEncodingChunked(httpMessage)) {
                    httpMessage.addHeader("Transfer-Encoding", "chunked");
                }
                this.chunked = true;
                isTransferEncodingChunked = true;
            } else {
                isTransferEncodingChunked = HttpCodecUtil.isTransferEncodingChunked(httpMessage);
                this.chunked = isTransferEncodingChunked;
            }
            ChannelBuffer dynamicBuffer = ChannelBuffers.dynamicBuffer(channel.getConfig().getBufferFactory());
            encodeInitialLine(dynamicBuffer, httpMessage);
            encodeHeaders(dynamicBuffer, httpMessage);
            dynamicBuffer.writeByte(13);
            dynamicBuffer.writeByte(10);
            ChannelBuffer content = httpMessage.getContent();
            if (!content.readable()) {
                return dynamicBuffer;
            }
            if (isTransferEncodingChunked) {
                throw new IllegalArgumentException("HttpMessage.content must be empty if Transfer-Encoding is chunked.");
            }
            return ChannelBuffers.wrappedBuffer(dynamicBuffer, content);
        }
        if (!(obj instanceof HttpChunk)) {
            return obj;
        }
        HttpChunk httpChunk = (HttpChunk) obj;
        if (this.chunked) {
            if (httpChunk.isLast()) {
                this.chunked = false;
                if (httpChunk instanceof HttpChunkTrailer) {
                    ChannelBuffer dynamicBuffer2 = ChannelBuffers.dynamicBuffer(channel.getConfig().getBufferFactory());
                    dynamicBuffer2.writeByte(48);
                    dynamicBuffer2.writeByte(13);
                    dynamicBuffer2.writeByte(10);
                    encodeTrailingHeaders(dynamicBuffer2, (HttpChunkTrailer) httpChunk);
                    dynamicBuffer2.writeByte(13);
                    dynamicBuffer2.writeByte(10);
                    return dynamicBuffer2;
                }
                return LAST_CHUNK.duplicate();
            }
            ChannelBuffer content2 = httpChunk.getContent();
            int readableBytes = content2.readableBytes();
            return ChannelBuffers.wrappedBuffer(ChannelBuffers.copiedBuffer(Integer.toHexString(readableBytes), CharsetUtil.US_ASCII), ChannelBuffers.wrappedBuffer(CRLF), content2.slice(content2.readerIndex(), readableBytes), ChannelBuffers.wrappedBuffer(CRLF));
        }
        if (httpChunk.isLast()) {
            return null;
        }
        return httpChunk.getContent();
    }

    private static void encodeHeaders(ChannelBuffer channelBuffer, HttpMessage httpMessage) {
        try {
            for (Map.Entry<String, String> entry : httpMessage.getHeaders()) {
                encodeHeader(channelBuffer, entry.getKey(), entry.getValue());
            }
        } catch (UnsupportedEncodingException e) {
            throw ((Error) new Error().initCause(e));
        }
    }

    private static void encodeTrailingHeaders(ChannelBuffer channelBuffer, HttpChunkTrailer httpChunkTrailer) {
        try {
            for (Map.Entry<String, String> entry : httpChunkTrailer.getHeaders()) {
                encodeHeader(channelBuffer, entry.getKey(), entry.getValue());
            }
        } catch (UnsupportedEncodingException e) {
            throw ((Error) new Error().initCause(e));
        }
    }

    private static void encodeHeader(ChannelBuffer channelBuffer, String str, String str2) throws UnsupportedEncodingException {
        channelBuffer.writeBytes(str.getBytes(HTTP.ASCII));
        channelBuffer.writeByte(58);
        channelBuffer.writeByte(32);
        channelBuffer.writeBytes(str2.getBytes(HTTP.ASCII));
        channelBuffer.writeByte(13);
        channelBuffer.writeByte(10);
    }
}
