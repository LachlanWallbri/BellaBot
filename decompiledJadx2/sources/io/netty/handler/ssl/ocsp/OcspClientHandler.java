package io.netty.handler.ssl.ocsp;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.ssl.ReferenceCountedOpenSslEngine;
import io.netty.handler.ssl.SslHandshakeCompletionEvent;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.ThrowableUtil;
import javax.net.ssl.SSLHandshakeException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class OcspClientHandler extends ChannelInboundHandlerAdapter {
    private static final SSLHandshakeException OCSP_VERIFICATION_EXCEPTION = (SSLHandshakeException) ThrowableUtil.unknownStackTrace(new SSLHandshakeException("Bad OCSP response"), OcspClientHandler.class, "verify(...)");
    private final ReferenceCountedOpenSslEngine engine;

    protected abstract boolean verify(ChannelHandlerContext channelHandlerContext, ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine) throws Exception;

    protected OcspClientHandler(ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine) {
        this.engine = (ReferenceCountedOpenSslEngine) ObjectUtil.checkNotNull(referenceCountedOpenSslEngine, "engine");
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object obj) throws Exception {
        if (obj instanceof SslHandshakeCompletionEvent) {
            channelHandlerContext.pipeline().remove(this);
            if (((SslHandshakeCompletionEvent) obj).isSuccess() && !verify(channelHandlerContext, this.engine)) {
                throw OCSP_VERIFICATION_EXCEPTION;
            }
        }
        channelHandlerContext.fireUserEventTriggered(obj);
    }
}
