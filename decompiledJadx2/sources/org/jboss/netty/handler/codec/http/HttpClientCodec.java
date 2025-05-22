package org.jboss.netty.handler.codec.http;

import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ChannelUpstreamHandler;
import org.jboss.netty.handler.codec.PrematureChannelClosureException;
import org.jboss.netty.handler.codec.http.HttpMessageDecoder;
import org.jboss.netty.util.internal.QueueFactory;

/* loaded from: classes7.dex */
public class HttpClientCodec implements ChannelUpstreamHandler, ChannelDownstreamHandler {
    private final HttpResponseDecoder decoder;
    volatile boolean done;
    private final HttpRequestEncoder encoder;
    private final boolean failOnMissingResponse;
    final Queue<HttpMethod> queue;
    private final AtomicLong requestResponseCounter;

    public HttpClientCodec() {
        this(4096, 8192, 8192, false);
    }

    public HttpClientCodec(int i, int i2, int i3) {
        this(i, i2, i3, false);
    }

    public HttpClientCodec(int i, int i2, int i3, boolean z) {
        this.queue = QueueFactory.createQueue(HttpMethod.class);
        this.encoder = new Encoder();
        this.requestResponseCounter = new AtomicLong(0L);
        this.decoder = new Decoder(i, i2, i3);
        this.failOnMissingResponse = z;
    }

    @Override // org.jboss.netty.channel.ChannelUpstreamHandler
    public void handleUpstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        this.decoder.handleUpstream(channelHandlerContext, channelEvent);
    }

    @Override // org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        this.encoder.handleDownstream(channelHandlerContext, channelEvent);
    }

    /* loaded from: classes7.dex */
    private final class Encoder extends HttpRequestEncoder {
        Encoder() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jboss.netty.handler.codec.http.HttpMessageEncoder, org.jboss.netty.handler.codec.oneone.OneToOneEncoder
        public Object encode(ChannelHandlerContext channelHandlerContext, Channel channel, Object obj) throws Exception {
            boolean z = obj instanceof HttpRequest;
            if (z && !HttpClientCodec.this.done) {
                HttpClientCodec.this.queue.offer(((HttpRequest) obj).getMethod());
            }
            Object encode = super.encode(channelHandlerContext, channel, obj);
            if (HttpClientCodec.this.failOnMissingResponse) {
                if (z && !((HttpRequest) obj).isChunked()) {
                    HttpClientCodec.this.requestResponseCounter.incrementAndGet();
                } else if ((obj instanceof HttpChunk) && ((HttpChunk) obj).isLast()) {
                    HttpClientCodec.this.requestResponseCounter.incrementAndGet();
                }
            }
            return encode;
        }
    }

    /* loaded from: classes7.dex */
    private final class Decoder extends HttpResponseDecoder {
        Decoder(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jboss.netty.handler.codec.http.HttpMessageDecoder, org.jboss.netty.handler.codec.replay.ReplayingDecoder
        public Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, HttpMessageDecoder.State state) throws Exception {
            if (HttpClientCodec.this.done) {
                return channelBuffer.readBytes(actualReadableBytes());
            }
            Object decode = super.decode(channelHandlerContext, channel, channelBuffer, state);
            if (HttpClientCodec.this.failOnMissingResponse) {
                decrement(decode);
            }
            return decode;
        }

        private void decrement(Object obj) {
            if (obj == null) {
                return;
            }
            if ((obj instanceof HttpMessage) && !((HttpMessage) obj).isChunked()) {
                HttpClientCodec.this.requestResponseCounter.decrementAndGet();
                return;
            }
            if ((obj instanceof HttpChunk) && ((HttpChunk) obj).isLast()) {
                HttpClientCodec.this.requestResponseCounter.decrementAndGet();
            } else if (obj instanceof Object[]) {
                HttpClientCodec.this.requestResponseCounter.decrementAndGet();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // org.jboss.netty.handler.codec.http.HttpMessageDecoder
        public boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
            int code = ((HttpResponse) httpMessage).getStatus().getCode();
            if (code == 100) {
                return true;
            }
            HttpMethod poll = HttpClientCodec.this.queue.poll();
            char charAt = poll.getName().charAt(0);
            if (charAt != 'C') {
                if (charAt == 'H' && HttpMethod.HEAD.equals(poll)) {
                    return true;
                }
            } else if (code == 200 && HttpMethod.CONNECT.equals(poll)) {
                HttpClientCodec httpClientCodec = HttpClientCodec.this;
                httpClientCodec.done = true;
                httpClientCodec.queue.clear();
                return true;
            }
            return super.isContentAlwaysEmpty(httpMessage);
        }

        @Override // org.jboss.netty.handler.codec.frame.FrameDecoder, org.jboss.netty.channel.SimpleChannelUpstreamHandler
        public void channelClosed(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
            super.channelClosed(channelHandlerContext, channelStateEvent);
            if (HttpClientCodec.this.failOnMissingResponse) {
                long j = HttpClientCodec.this.requestResponseCounter.get();
                if (j <= 0) {
                    return;
                }
                throw new PrematureChannelClosureException("Channel closed but still missing " + j + " response(s)");
            }
        }
    }
}
