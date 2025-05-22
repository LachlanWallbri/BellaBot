package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.CombinedChannelDuplexHandler;
import io.netty.handler.codec.PrematureChannelClosureException;
import io.netty.handler.codec.http.HttpClientUpgradeHandler;
import io.netty.util.ReferenceCountUtil;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class HttpClientCodec extends CombinedChannelDuplexHandler<HttpResponseDecoder, HttpRequestEncoder> implements HttpClientUpgradeHandler.SourceCodec {
    private boolean done;
    private final boolean failOnMissingResponse;
    private final boolean parseHttpAfterConnectRequest;
    private final Queue<HttpMethod> queue;
    private final AtomicLong requestResponseCounter;

    public HttpClientCodec() {
        this(4096, 8192, 8192, false);
    }

    public HttpClientCodec(int i, int i2, int i3) {
        this(i, i2, i3, false);
    }

    public HttpClientCodec(int i, int i2, int i3, boolean z) {
        this(i, i2, i3, z, true);
    }

    public HttpClientCodec(int i, int i2, int i3, boolean z, boolean z2) {
        this(i, i2, i3, z, z2, false);
    }

    public HttpClientCodec(int i, int i2, int i3, boolean z, boolean z2, boolean z3) {
        this.queue = new ArrayDeque();
        this.requestResponseCounter = new AtomicLong();
        init(new Decoder(i, i2, i3, z2), new Encoder());
        this.failOnMissingResponse = z;
        this.parseHttpAfterConnectRequest = z3;
    }

    public HttpClientCodec(int i, int i2, int i3, boolean z, boolean z2, int i4) {
        this(i, i2, i3, z, z2, i4, false);
    }

    public HttpClientCodec(int i, int i2, int i3, boolean z, boolean z2, int i4, boolean z3) {
        this.queue = new ArrayDeque();
        this.requestResponseCounter = new AtomicLong();
        init(new Decoder(i, i2, i3, z2, i4), new Encoder());
        this.parseHttpAfterConnectRequest = z3;
        this.failOnMissingResponse = z;
    }

    @Override // io.netty.handler.codec.http.HttpClientUpgradeHandler.SourceCodec
    public void prepareUpgradeFrom(ChannelHandlerContext channelHandlerContext) {
        ((Encoder) outboundHandler()).upgraded = true;
    }

    @Override // io.netty.handler.codec.http.HttpClientUpgradeHandler.SourceCodec
    public void upgradeFrom(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.pipeline().remove(this);
    }

    public void setSingleDecode(boolean z) {
        inboundHandler().setSingleDecode(z);
    }

    public boolean isSingleDecode() {
        return inboundHandler().isSingleDecode();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private final class Encoder extends HttpRequestEncoder {
        boolean upgraded;

        private Encoder() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.netty.handler.codec.http.HttpObjectEncoder, io.netty.handler.codec.MessageToMessageEncoder
        public void encode(ChannelHandlerContext channelHandlerContext, Object obj, List<Object> list) throws Exception {
            if (this.upgraded) {
                list.add(ReferenceCountUtil.retain(obj));
                return;
            }
            if ((obj instanceof HttpRequest) && !HttpClientCodec.this.done) {
                HttpClientCodec.this.queue.offer(((HttpRequest) obj).method());
            }
            super.encode(channelHandlerContext, obj, list);
            if (HttpClientCodec.this.failOnMissingResponse && !HttpClientCodec.this.done && (obj instanceof LastHttpContent)) {
                HttpClientCodec.this.requestResponseCounter.incrementAndGet();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    private final class Decoder extends HttpResponseDecoder {
        Decoder(int i, int i2, int i3, boolean z) {
            super(i, i2, i3, z);
        }

        Decoder(int i, int i2, int i3, boolean z, int i4) {
            super(i, i2, i3, z, i4);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.netty.handler.codec.http.HttpObjectDecoder, io.netty.handler.codec.ByteToMessageDecoder
        public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
            if (HttpClientCodec.this.done) {
                int actualReadableBytes = actualReadableBytes();
                if (actualReadableBytes == 0) {
                    return;
                }
                list.add(byteBuf.readBytes(actualReadableBytes));
                return;
            }
            super.decode(channelHandlerContext, byteBuf, list);
            if (HttpClientCodec.this.failOnMissingResponse) {
                int size = list.size();
                for (int size2 = list.size(); size2 < size; size2++) {
                    decrement(list.get(size2));
                }
            }
        }

        private void decrement(Object obj) {
            if (obj != null && (obj instanceof LastHttpContent)) {
                HttpClientCodec.this.requestResponseCounter.decrementAndGet();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.netty.handler.codec.http.HttpObjectDecoder
        public boolean isContentAlwaysEmpty(HttpMessage httpMessage) {
            int code = ((HttpResponse) httpMessage).status().code();
            if (code != 100 && code != 101) {
                HttpMethod httpMethod = (HttpMethod) HttpClientCodec.this.queue.poll();
                char charAt = httpMethod.name().charAt(0);
                if (charAt != 'C') {
                    if (charAt == 'H' && HttpMethod.HEAD.equals(httpMethod)) {
                        return true;
                    }
                } else if (code == 200 && HttpMethod.CONNECT.equals(httpMethod)) {
                    if (!HttpClientCodec.this.parseHttpAfterConnectRequest) {
                        HttpClientCodec.this.done = true;
                        HttpClientCodec.this.queue.clear();
                    }
                    return true;
                }
                return super.isContentAlwaysEmpty(httpMessage);
            }
            return super.isContentAlwaysEmpty(httpMessage);
        }

        @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
        public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
            super.channelInactive(channelHandlerContext);
            if (HttpClientCodec.this.failOnMissingResponse) {
                long j = HttpClientCodec.this.requestResponseCounter.get();
                if (j > 0) {
                    channelHandlerContext.fireExceptionCaught((Throwable) new PrematureChannelClosureException("channel gone inactive with " + j + " missing response(s)"));
                }
            }
        }
    }
}
