package org.jboss.netty.handler.codec.http;

import java.util.Queue;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.handler.codec.embedder.EncoderEmbedder;
import org.jboss.netty.util.internal.QueueFactory;

/* loaded from: classes7.dex */
public abstract class HttpContentEncoder extends SimpleChannelHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Queue<String> acceptEncodingQueue = QueueFactory.createQueue(String.class);
    private volatile EncoderEmbedder<ChannelBuffer> encoder;

    protected abstract String getTargetContentEncoding(String str) throws Exception;

    protected abstract EncoderEmbedder<ChannelBuffer> newContentEncoder(HttpMessage httpMessage, String str) throws Exception;

    @Override // org.jboss.netty.channel.SimpleChannelHandler
    public void messageReceived(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        Object message = messageEvent.getMessage();
        if (!(message instanceof HttpMessage)) {
            channelHandlerContext.sendUpstream(messageEvent);
            return;
        }
        String header = ((HttpMessage) message).getHeader("Accept-Encoding");
        if (header == null) {
            header = "identity";
        }
        this.acceptEncodingQueue.offer(header);
        channelHandlerContext.sendUpstream(messageEvent);
    }

    @Override // org.jboss.netty.channel.SimpleChannelHandler
    public void writeRequested(ChannelHandlerContext channelHandlerContext, MessageEvent messageEvent) throws Exception {
        Object message = messageEvent.getMessage();
        if ((message instanceof HttpResponse) && ((HttpResponse) message).getStatus().getCode() == 100) {
            channelHandlerContext.sendDownstream(messageEvent);
            return;
        }
        if (message instanceof HttpMessage) {
            HttpMessage httpMessage = (HttpMessage) message;
            this.encoder = null;
            String poll = this.acceptEncodingQueue.poll();
            if (poll == null) {
                throw new IllegalStateException("cannot send more responses than requests");
            }
            String header = httpMessage.getHeader("Content-Encoding");
            if (header != null && !"identity".equalsIgnoreCase(header)) {
                channelHandlerContext.sendDownstream(messageEvent);
                return;
            }
            if (httpMessage.isChunked() || httpMessage.getContent().readable()) {
                EncoderEmbedder<ChannelBuffer> newContentEncoder = newContentEncoder(httpMessage, poll);
                this.encoder = newContentEncoder;
                if (newContentEncoder != null) {
                    httpMessage.setHeader("Content-Encoding", getTargetContentEncoding(poll));
                    if (!httpMessage.isChunked()) {
                        ChannelBuffer wrappedBuffer = ChannelBuffers.wrappedBuffer(encode(httpMessage.getContent()), finishEncode());
                        httpMessage.setContent(wrappedBuffer);
                        if (httpMessage.containsHeader("Content-Length")) {
                            httpMessage.setHeader("Content-Length", Integer.toString(wrappedBuffer.readableBytes()));
                        }
                    }
                }
            }
            channelHandlerContext.sendDownstream(messageEvent);
            return;
        }
        if (message instanceof HttpChunk) {
            HttpChunk httpChunk = (HttpChunk) message;
            ChannelBuffer content = httpChunk.getContent();
            if (this.encoder != null) {
                if (!httpChunk.isLast()) {
                    ChannelBuffer encode = encode(content);
                    if (encode.readable()) {
                        httpChunk.setContent(encode);
                        channelHandlerContext.sendDownstream(messageEvent);
                        return;
                    }
                    return;
                }
                ChannelBuffer finishEncode = finishEncode();
                if (finishEncode.readable()) {
                    Channels.write(channelHandlerContext, Channels.succeededFuture(messageEvent.getChannel()), new DefaultHttpChunk(finishEncode), messageEvent.getRemoteAddress());
                }
                channelHandlerContext.sendDownstream(messageEvent);
                return;
            }
            channelHandlerContext.sendDownstream(messageEvent);
            return;
        }
        channelHandlerContext.sendDownstream(messageEvent);
    }

    private ChannelBuffer encode(ChannelBuffer channelBuffer) {
        this.encoder.offer(channelBuffer);
        return ChannelBuffers.wrappedBuffer((ChannelBuffer[]) this.encoder.pollAll(new ChannelBuffer[this.encoder.size()]));
    }

    private ChannelBuffer finishEncode() {
        ChannelBuffer channelBuffer;
        if (this.encoder.finish()) {
            channelBuffer = ChannelBuffers.wrappedBuffer((ChannelBuffer[]) this.encoder.pollAll(new ChannelBuffer[this.encoder.size()]));
        } else {
            channelBuffer = ChannelBuffers.EMPTY_BUFFER;
        }
        this.encoder = null;
        return channelBuffer;
    }
}
