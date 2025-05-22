package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.CombinedChannelDuplexHandler;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpServerUpgradeHandler;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class HttpServerCodec extends CombinedChannelDuplexHandler<HttpRequestDecoder, HttpResponseEncoder> implements HttpServerUpgradeHandler.SourceCodec {
    private final Queue<HttpMethod> queue;

    public HttpServerCodec() {
        this(4096, 8192, 8192);
    }

    public HttpServerCodec(int i, int i2, int i3) {
        this.queue = new ArrayDeque();
        init(new HttpServerRequestDecoder(i, i2, i3), new HttpServerResponseEncoder());
    }

    public HttpServerCodec(int i, int i2, int i3, boolean z) {
        this.queue = new ArrayDeque();
        init(new HttpServerRequestDecoder(i, i2, i3, z), new HttpServerResponseEncoder());
    }

    public HttpServerCodec(int i, int i2, int i3, boolean z, int i4) {
        this.queue = new ArrayDeque();
        init(new HttpServerRequestDecoder(i, i2, i3, z, i4), new HttpServerResponseEncoder());
    }

    public HttpServerCodec(int i, int i2, int i3, boolean z, int i4, boolean z2) {
        this.queue = new ArrayDeque();
        init(new HttpServerRequestDecoder(i, i2, i3, z, i4, z2), new HttpServerResponseEncoder());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpServerUpgradeHandler.SourceCodec
    public void upgradeFrom(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.pipeline().remove(this);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class HttpServerRequestDecoder extends HttpRequestDecoder {
        HttpServerRequestDecoder(int i, int i2, int i3) {
            super(i, i2, i3);
        }

        HttpServerRequestDecoder(int i, int i2, int i3, boolean z) {
            super(i, i2, i3, z);
        }

        HttpServerRequestDecoder(int i, int i2, int i3, boolean z, int i4) {
            super(i, i2, i3, z, i4);
        }

        HttpServerRequestDecoder(int i, int i2, int i3, boolean z, int i4, boolean z2) {
            super(i, i2, i3, z, i4, z2);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectDecoder, io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
        public void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
            super.decode(channelHandlerContext, byteBuf, list);
            int size = list.size();
            for (int size2 = list.size(); size2 < size; size2++) {
                Object obj = list.get(size2);
                if (obj instanceof HttpRequest) {
                    HttpServerCodec.this.queue.add(((HttpRequest) obj).method());
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class HttpServerResponseEncoder extends HttpResponseEncoder {
        private HttpMethod method;

        private HttpServerResponseEncoder() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseEncoder, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectEncoder
        public void sanitizeHeadersBeforeEncode(HttpResponse httpResponse, boolean z) {
            if (!z && HttpMethod.CONNECT.equals(this.method) && httpResponse.status().codeClass() == HttpStatusClass.SUCCESS) {
                httpResponse.headers().remove(HttpHeaderNames.TRANSFER_ENCODING);
            } else {
                super.sanitizeHeadersBeforeEncode(httpResponse, z);
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseEncoder, io.grpc.netty.shaded.io.netty.handler.codec.http.HttpObjectEncoder
        public boolean isContentAlwaysEmpty(HttpResponse httpResponse) {
            this.method = (HttpMethod) HttpServerCodec.this.queue.poll();
            return HttpMethod.HEAD.equals(this.method) || super.isContentAlwaysEmpty(httpResponse);
        }
    }
}
