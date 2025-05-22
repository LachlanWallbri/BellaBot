package org.jboss.netty.handler.codec.http;

import java.util.List;
import java.util.Map;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.buffer.CompositeChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.LifeCycleAwareChannelHandler;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.frame.TooLongFrameException;
import org.jboss.netty.util.CharsetUtil;

/* loaded from: classes7.dex */
public class HttpChunkAggregator extends SimpleChannelUpstreamHandler implements LifeCycleAwareChannelHandler {
    private static final ChannelBuffer CONTINUE = ChannelBuffers.copiedBuffer("HTTP/1.1 100 Continue\r\n\r\n", CharsetUtil.US_ASCII);
    public static final int DEFAULT_MAX_COMPOSITEBUFFER_COMPONENTS = 1024;
    private ChannelHandlerContext ctx;
    private HttpMessage currentMessage;
    private final int maxContentLength;
    private int maxCumulationBufferComponents = 1024;

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
    }

    public HttpChunkAggregator(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxContentLength must be a positive integer: " + i);
        }
        this.maxContentLength = i;
    }

    public final int getMaxCumulationBufferComponents() {
        return this.maxCumulationBufferComponents;
    }

    public final void setMaxCumulationBufferComponents(int i) {
        if (i < 2) {
            throw new IllegalArgumentException("maxCumulationBufferComponents: " + i + " (expected: >= 2)");
        }
        if (this.ctx == null) {
            this.maxCumulationBufferComponents = i;
            return;
        }
        throw new IllegalStateException("decoder properties cannot be changed once the decoder is added to a pipeline.");
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        Object message = messageEvent.getMessage();
        HttpMessage httpMessage = this.currentMessage;
        if (message instanceof HttpMessage) {
            HttpMessage httpMessage2 = (HttpMessage) message;
            if (HttpHeaders.is100ContinueExpected(httpMessage2)) {
                Channels.write(channelHandlerContext, Channels.succeededFuture(channelHandlerContext.getChannel()), CONTINUE.duplicate());
            }
            if (httpMessage2.isChunked()) {
                List<String> headers = httpMessage2.getHeaders("Transfer-Encoding");
                headers.remove("chunked");
                if (headers.isEmpty()) {
                    httpMessage2.removeHeader("Transfer-Encoding");
                }
                httpMessage2.setChunked(false);
                this.currentMessage = httpMessage2;
                return;
            }
            this.currentMessage = null;
            channelHandlerContext.sendUpstream(messageEvent);
            return;
        }
        if (!(message instanceof HttpChunk)) {
            channelHandlerContext.sendUpstream(messageEvent);
            return;
        }
        if (httpMessage == null) {
            throw new IllegalStateException("received " + HttpChunk.class.getSimpleName() + " without " + HttpMessage.class.getSimpleName());
        }
        HttpChunk httpChunk = (HttpChunk) message;
        ChannelBuffer content = httpMessage.getContent();
        if (content.readableBytes() > this.maxContentLength - httpChunk.getContent().readableBytes()) {
            throw new TooLongFrameException("HTTP content length exceeded " + this.maxContentLength + " bytes.");
        }
        appendToCumulation(httpChunk.getContent());
        if (httpChunk.isLast()) {
            this.currentMessage = null;
            if (httpChunk instanceof HttpChunkTrailer) {
                for (Map.Entry<String, String> entry : ((HttpChunkTrailer) httpChunk).getHeaders()) {
                    httpMessage.setHeader(entry.getKey(), entry.getValue());
                }
            }
            httpMessage.setHeader("Content-Length", String.valueOf(content.readableBytes()));
            Channels.fireMessageReceived(channelHandlerContext, httpMessage, messageEvent.getRemoteAddress());
        }
    }

    protected void appendToCumulation(ChannelBuffer channelBuffer) {
        ChannelBuffer content = this.currentMessage.getContent();
        if (content instanceof CompositeChannelBuffer) {
            CompositeChannelBuffer compositeChannelBuffer = (CompositeChannelBuffer) content;
            if (compositeChannelBuffer.numComponents() >= this.maxCumulationBufferComponents) {
                this.currentMessage.setContent(ChannelBuffers.wrappedBuffer(compositeChannelBuffer.copy(), channelBuffer));
                return;
            }
            List<ChannelBuffer> decompose = compositeChannelBuffer.decompose(0, compositeChannelBuffer.readableBytes());
            ChannelBuffer[] channelBufferArr = (ChannelBuffer[]) decompose.toArray(new ChannelBuffer[decompose.size() + 1]);
            channelBufferArr[channelBufferArr.length - 1] = channelBuffer;
            this.currentMessage.setContent(ChannelBuffers.wrappedBuffer(channelBufferArr));
            return;
        }
        this.currentMessage.setContent(ChannelBuffers.wrappedBuffer(content, channelBuffer));
    }

    @Override // org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
    }
}
