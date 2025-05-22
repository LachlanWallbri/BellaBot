package io.grpc.netty.shaded.io.netty.handler.codec.http.websocketx;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.handler.codec.CorruptedFrameException;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class Utf8FrameValidator extends ChannelInboundHandlerAdapter {
    private int fragmentedFramesCount;
    private Utf8Validator utf8Validator;

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof WebSocketFrame) {
            WebSocketFrame webSocketFrame = (WebSocketFrame) obj;
            try {
                if (((WebSocketFrame) obj).isFinalFragment()) {
                    if (!(webSocketFrame instanceof PingWebSocketFrame)) {
                        this.fragmentedFramesCount = 0;
                        if ((webSocketFrame instanceof TextWebSocketFrame) || (this.utf8Validator != null && this.utf8Validator.isChecking())) {
                            checkUTF8String(webSocketFrame.content());
                            this.utf8Validator.finish();
                        }
                    }
                } else {
                    if (this.fragmentedFramesCount == 0) {
                        if (webSocketFrame instanceof TextWebSocketFrame) {
                            checkUTF8String(webSocketFrame.content());
                        }
                    } else if (this.utf8Validator != null && this.utf8Validator.isChecking()) {
                        checkUTF8String(webSocketFrame.content());
                    }
                    this.fragmentedFramesCount++;
                }
            } catch (CorruptedWebSocketFrameException e) {
                webSocketFrame.release();
                throw e;
            }
        }
        super.channelRead(channelHandlerContext, obj);
    }

    private void checkUTF8String(ByteBuf byteBuf) {
        if (this.utf8Validator == null) {
            this.utf8Validator = new Utf8Validator();
        }
        this.utf8Validator.check(byteBuf);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if ((th instanceof CorruptedFrameException) && channelHandlerContext.channel().isOpen()) {
            channelHandlerContext.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener((GenericFutureListener<? extends Future<? super Void>>) ChannelFutureListener.CLOSE);
        }
        super.exceptionCaught(channelHandlerContext, th);
    }
}
