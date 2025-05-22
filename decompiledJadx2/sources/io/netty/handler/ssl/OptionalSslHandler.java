package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.ObjectUtil;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class OptionalSslHandler extends ByteToMessageDecoder {
    private final SslContext sslContext;

    protected ChannelHandler newNonSslHandler(ChannelHandlerContext channelHandlerContext) {
        return null;
    }

    protected String newNonSslHandlerName() {
        return null;
    }

    protected String newSslHandlerName() {
        return null;
    }

    public OptionalSslHandler(SslContext sslContext) {
        this.sslContext = (SslContext) ObjectUtil.checkNotNull(sslContext, "sslContext");
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 5) {
            return;
        }
        if (SslHandler.isEncrypted(byteBuf)) {
            handleSsl(channelHandlerContext);
        } else {
            handleNonSsl(channelHandlerContext);
        }
    }

    private void handleSsl(ChannelHandlerContext channelHandlerContext) {
        SslHandler sslHandler = null;
        try {
            sslHandler = newSslHandler(channelHandlerContext, this.sslContext);
            channelHandlerContext.pipeline().replace(this, newSslHandlerName(), sslHandler);
        } catch (Throwable th) {
            if (sslHandler != null) {
                ReferenceCountUtil.safeRelease(sslHandler.engine());
            }
            throw th;
        }
    }

    private void handleNonSsl(ChannelHandlerContext channelHandlerContext) {
        ChannelHandler newNonSslHandler = newNonSslHandler(channelHandlerContext);
        if (newNonSslHandler != null) {
            channelHandlerContext.pipeline().replace(this, newNonSslHandlerName(), newNonSslHandler);
        } else {
            channelHandlerContext.pipeline().remove(this);
        }
    }

    protected SslHandler newSslHandler(ChannelHandlerContext channelHandlerContext, SslContext sslContext) {
        return sslContext.newHandler(channelHandlerContext.alloc());
    }
}
