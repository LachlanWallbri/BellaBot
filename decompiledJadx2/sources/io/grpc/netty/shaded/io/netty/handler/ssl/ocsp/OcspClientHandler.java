package io.grpc.netty.shaded.io.netty.handler.ssl.ocsp;

import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter;
import io.grpc.netty.shaded.io.netty.handler.ssl.ReferenceCountedOpenSslEngine;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslHandshakeCompletionEvent;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import javax.net.ssl.SSLHandshakeException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class OcspClientHandler extends ChannelInboundHandlerAdapter {
    private final ReferenceCountedOpenSslEngine engine;

    protected abstract boolean verify(ChannelHandlerContext channelHandlerContext, ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine) throws Exception;

    protected OcspClientHandler(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine) {
        this.engine = (ReferenceCountedOpenSslEngine) ObjectUtil.checkNotNull(referenceCountedOpenSslEngine, "engine");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof SslHandshakeCompletionEvent) {
            channelHandlerContext.pipeline().remove(this);
            if (((SslHandshakeCompletionEvent) obj).isSuccess() && !verify(channelHandlerContext, this.engine)) {
                throw new SSLHandshakeException("Bad OCSP response");
            }
        }
        channelHandlerContext.fireUserEventTriggered(obj);
    }
}
