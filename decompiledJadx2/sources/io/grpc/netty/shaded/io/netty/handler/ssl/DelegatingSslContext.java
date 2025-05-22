package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.List;
import java.util.concurrent.Executor;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLSessionContext;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class DelegatingSslContext extends SslContext {
    private final SslContext ctx;

    protected abstract void initEngine(SSLEngine sSLEngine);

    protected DelegatingSslContext(SslContext sslContext) {
        this.ctx = (SslContext) ObjectUtil.checkNotNull(sslContext, "ctx");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public final boolean isClient() {
        return this.ctx.isClient();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public final List<String> cipherSuites() {
        return this.ctx.cipherSuites();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public final long sessionCacheSize() {
        return this.ctx.sessionCacheSize();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public final long sessionTimeout() {
        return this.ctx.sessionTimeout();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public final ApplicationProtocolNegotiator applicationProtocolNegotiator() {
        return this.ctx.applicationProtocolNegotiator();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public final SSLEngine newEngine(ByteBufAllocator byteBufAllocator) {
        SSLEngine newEngine = this.ctx.newEngine(byteBufAllocator);
        initEngine(newEngine);
        return newEngine;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public final SSLEngine newEngine(ByteBufAllocator byteBufAllocator, String str, int i) {
        SSLEngine newEngine = this.ctx.newEngine(byteBufAllocator, str, i);
        initEngine(newEngine);
        return newEngine;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public final SslHandler newHandler(ByteBufAllocator byteBufAllocator, boolean z) {
        SslHandler newHandler = this.ctx.newHandler(byteBufAllocator, z);
        initHandler(newHandler);
        return newHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public final SslHandler newHandler(ByteBufAllocator byteBufAllocator, String str, int i, boolean z) {
        SslHandler newHandler = this.ctx.newHandler(byteBufAllocator, str, i, z);
        initHandler(newHandler);
        return newHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public SslHandler newHandler(ByteBufAllocator byteBufAllocator, boolean z, Executor executor) {
        SslHandler newHandler = this.ctx.newHandler(byteBufAllocator, z, executor);
        initHandler(newHandler);
        return newHandler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public SslHandler newHandler(ByteBufAllocator byteBufAllocator, String str, int i, boolean z, Executor executor) {
        SslHandler newHandler = this.ctx.newHandler(byteBufAllocator, str, i, z, executor);
        initHandler(newHandler);
        return newHandler;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslContext
    public final SSLSessionContext sessionContext() {
        return this.ctx.sessionContext();
    }

    protected void initHandler(SslHandler sslHandler) {
        initEngine(sslHandler.engine());
    }
}
