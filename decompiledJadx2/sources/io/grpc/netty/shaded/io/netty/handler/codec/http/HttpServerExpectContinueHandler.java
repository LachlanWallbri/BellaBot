package io.grpc.netty.shaded.io.netty.handler.codec.http;

import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class HttpServerExpectContinueHandler extends ChannelInboundHandlerAdapter {
    private static final FullHttpResponse EXPECTATION_FAILED = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.EXPECTATION_FAILED, Unpooled.EMPTY_BUFFER);
    private static final FullHttpResponse ACCEPT = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE, Unpooled.EMPTY_BUFFER);

    static {
        EXPECTATION_FAILED.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) 0);
        ACCEPT.headers().set((CharSequence) HttpHeaderNames.CONTENT_LENGTH, (Object) 0);
    }

    protected HttpResponse acceptMessage(HttpRequest httpRequest) {
        return ACCEPT.retainedDuplicate();
    }

    protected HttpResponse rejectResponse(HttpRequest httpRequest) {
        return EXPECTATION_FAILED.retainedDuplicate();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof HttpRequest) {
            HttpRequest httpRequest = (HttpRequest) obj;
            if (HttpUtil.is100ContinueExpected(httpRequest)) {
                HttpResponse acceptMessage = acceptMessage(httpRequest);
                if (acceptMessage == null) {
                    HttpResponse rejectResponse = rejectResponse(httpRequest);
                    ReferenceCountUtil.release(obj);
                    channelHandlerContext.writeAndFlush(rejectResponse).addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE_ON_FAILURE);
                    return;
                }
                channelHandlerContext.writeAndFlush(acceptMessage).addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE_ON_FAILURE);
                httpRequest.headers().remove(HttpHeaderNames.EXPECT);
            }
        }
        super.channelRead(channelHandlerContext, obj);
    }
}
