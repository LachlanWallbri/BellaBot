package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderException;
import io.grpc.netty.shaded.io.netty.util.AsyncMapping;
import io.grpc.netty.shaded.io.netty.util.DomainNameMapping;
import io.grpc.netty.shaded.io.netty.util.Mapping;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class SniHandler extends AbstractSniHandler<SslContext> {
    private static final Selection EMPTY_SELECTION = new Selection(null, null);
    protected final AsyncMapping<String, SslContext> mapping;
    private volatile Selection selection;

    public SniHandler(Mapping<? super String, ? extends SslContext> mapping) {
        this(new AsyncMappingAdapter(mapping));
    }

    public SniHandler(DomainNameMapping<? extends SslContext> domainNameMapping) {
        this((Mapping<? super String, ? extends SslContext>) domainNameMapping);
    }

    public SniHandler(AsyncMapping<? super String, ? extends SslContext> asyncMapping) {
        this.selection = EMPTY_SELECTION;
        this.mapping = (AsyncMapping) ObjectUtil.checkNotNull(asyncMapping, "mapping");
    }

    public String hostname() {
        return this.selection.hostname;
    }

    public SslContext sslContext() {
        return this.selection.context;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.AbstractSniHandler
    protected Future<SslContext> lookup(ChannelHandlerContext channelHandlerContext, String str) throws Exception {
        return this.mapping.map(str, channelHandlerContext.executor().newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.AbstractSniHandler
    protected final void onLookupComplete(ChannelHandlerContext channelHandlerContext, String str, Future<SslContext> future) throws Exception {
        if (!future.isSuccess()) {
            Throwable cause = future.cause();
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new DecoderException("failed to get the SslContext for " + str, cause);
        }
        SslContext now = future.getNow();
        this.selection = new Selection(now, str);
        try {
            replaceHandler(channelHandlerContext, str, now);
        } catch (Throwable th) {
            this.selection = EMPTY_SELECTION;
            PlatformDependent.throwException(th);
        }
    }

    protected void replaceHandler(ChannelHandlerContext channelHandlerContext, String str, SslContext sslContext) throws Exception {
        SslHandler sslHandler = null;
        try {
            sslHandler = newSslHandler(sslContext, channelHandlerContext.alloc());
            channelHandlerContext.pipeline().replace(this, SslHandler.class.getName(), sslHandler);
        } catch (Throwable th) {
            if (sslHandler != null) {
                ReferenceCountUtil.safeRelease(sslHandler.engine());
            }
            throw th;
        }
    }

    protected SslHandler newSslHandler(SslContext sslContext, ByteBufAllocator byteBufAllocator) {
        return sslContext.newHandler(byteBufAllocator);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class AsyncMappingAdapter implements AsyncMapping<String, SslContext> {
        private final Mapping<? super String, ? extends SslContext> mapping;

        private AsyncMappingAdapter(Mapping<? super String, ? extends SslContext> mapping) {
            this.mapping = (Mapping) ObjectUtil.checkNotNull(mapping, "mapping");
        }

        @Override // io.grpc.netty.shaded.io.netty.util.AsyncMapping
        public Future<SslContext> map(String str, Promise<SslContext> promise) {
            try {
                return promise.setSuccess(this.mapping.map(str));
            } catch (Throwable th) {
                return promise.setFailure(th);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class Selection {
        final SslContext context;
        final String hostname;

        Selection(SslContext sslContext, String str) {
            this.context = sslContext;
            this.hostname = str;
        }
    }
}
