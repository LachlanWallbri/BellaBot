package org.jboss.netty.handler.codec.http;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.embedder.DecoderEmbedder;

/* loaded from: classes7.dex */
public abstract class HttpContentDecoder extends SimpleChannelUpstreamHandler {
    private DecoderEmbedder<ChannelBuffer> decoder;

    protected String getTargetContentEncoding(String str) throws Exception {
        return "identity";
    }

    protected abstract DecoderEmbedder<ChannelBuffer> newContentDecoder(String str) throws Exception;

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        Object message = messageEvent.getMessage();
        if ((message instanceof HttpResponse) && ((HttpResponse) message).getStatus().getCode() == 100) {
            channelHandlerContext.sendUpstream(messageEvent);
            return;
        }
        if (message instanceof HttpMessage) {
            HttpMessage httpMessage = (HttpMessage) message;
            this.decoder = null;
            String header = httpMessage.getHeader("Content-Encoding");
            String trim = header != null ? header.trim() : "identity";
            if (httpMessage.isChunked() || httpMessage.getContent().readable()) {
                DecoderEmbedder<ChannelBuffer> newContentDecoder = newContentDecoder(trim);
                this.decoder = newContentDecoder;
                if (newContentDecoder != null) {
                    httpMessage.setHeader("Content-Encoding", getTargetContentEncoding(trim));
                    if (!httpMessage.isChunked()) {
                        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(decode(httpMessage.getContent()), finishDecode());
                        httpMessage.setContent(wrappedBuffer);
                        if (httpMessage.containsHeader("Content-Length")) {
                            httpMessage.setHeader("Content-Length", Integer.toString(wrappedBuffer.readableBytes()));
                        }
                    }
                }
            }
            channelHandlerContext.sendUpstream(messageEvent);
            return;
        }
        if (message instanceof HttpChunk) {
            HttpChunk httpChunk = (HttpChunk) message;
            ChannelBuffer content = httpChunk.getContent();
            if (this.decoder != null) {
                if (!httpChunk.isLast()) {
                    ChannelBuffer decode = decode(content);
                    if (decode.readable()) {
                        httpChunk.setContent(decode);
                        channelHandlerContext.sendUpstream(messageEvent);
                        return;
                    }
                    return;
                }
                ChannelBuffer finishDecode = finishDecode();
                if (finishDecode.readable()) {
                    Channels.fireMessageReceived(channelHandlerContext, new DefaultHttpChunk(finishDecode), messageEvent.getRemoteAddress());
                }
                channelHandlerContext.sendUpstream(messageEvent);
                return;
            }
            channelHandlerContext.sendUpstream(messageEvent);
            return;
        }
        channelHandlerContext.sendUpstream(messageEvent);
    }

    private ChannelBuffer decode(ChannelBuffer channelBuffer) {
        this.decoder.offer(channelBuffer);
        DecoderEmbedder<ChannelBuffer> decoderEmbedder = this.decoder;
        return ChannelBuffers.wrappedBuffer((ChannelBuffer[]) decoderEmbedder.pollAll(new ChannelBuffer[decoderEmbedder.size()]));
    }

    private ChannelBuffer finishDecode() {
        ChannelBuffer channelBuffer;
        if (this.decoder.finish()) {
            DecoderEmbedder<ChannelBuffer> decoderEmbedder = this.decoder;
            channelBuffer = ChannelBuffers.wrappedBuffer((ChannelBuffer[]) decoderEmbedder.pollAll(new ChannelBuffer[decoderEmbedder.size()]));
        } else {
            channelBuffer = ChannelBuffers.EMPTY_BUFFER;
        }
        this.decoder = null;
        return channelBuffer;
    }
}
