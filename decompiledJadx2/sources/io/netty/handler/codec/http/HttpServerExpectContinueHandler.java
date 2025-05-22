package io.netty.handler.codec.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
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

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
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
