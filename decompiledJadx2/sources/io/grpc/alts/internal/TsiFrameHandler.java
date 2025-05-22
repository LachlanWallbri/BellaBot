package io.grpc.alts.internal;

import com.google.common.base.Preconditions;
import io.grpc.alts.internal.TsiFrameProtector;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.PendingWriteQueue;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import java.net.SocketAddress;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class TsiFrameHandler extends ByteToMessageDecoder implements ChannelOutboundHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger logger = Logger.getLogger(TsiFrameHandler.class.getName());
    private boolean closeInitiated;
    private PendingWriteQueue pendingUnprotectedWrites;
    private TsiFrameProtector protector;

    public TsiFrameHandler(TsiFrameProtector tsiFrameProtector) {
        this.protector = (TsiFrameProtector) Preconditions.checkNotNull(tsiFrameProtector, "protector");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.handlerAdded(channelHandlerContext);
        this.pendingUnprotectedWrites = new PendingWriteQueue((ChannelHandlerContext) Preconditions.checkNotNull(channelHandlerContext));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        Preconditions.checkState(this.protector != null, "decode() called after close()");
        this.protector.unprotect(byteBuf, list, channelHandlerContext.alloc());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) {
        if (this.protector == null) {
            channelPromise.setFailure((Throwable) new IllegalStateException("write() called after close()"));
            return;
        }
        ByteBuf byteBuf = (ByteBuf) obj;
        if (!byteBuf.isReadable()) {
            channelPromise.setSuccess();
        } else {
            this.pendingUnprotectedWrites.add(byteBuf, channelPromise);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    public void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        destroyProtectorAndWrites();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        doClose(channelHandlerContext);
        channelHandlerContext.disconnect(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        doClose(channelHandlerContext);
        channelHandlerContext.close(channelPromise);
    }

    private void doClose(ChannelHandlerContext channelHandlerContext) {
        if (this.closeInitiated) {
            return;
        }
        this.closeInitiated = true;
        try {
            try {
                if (!this.pendingUnprotectedWrites.isEmpty()) {
                    flush(channelHandlerContext);
                }
            } catch (GeneralSecurityException e) {
                logger.log(Level.FINE, "Ignored error on flush before close", (Throwable) e);
            }
        } finally {
            destroyProtectorAndWrites();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void flush(final ChannelHandlerContext channelHandlerContext) throws GeneralSecurityException {
        PendingWriteQueue pendingWriteQueue = this.pendingUnprotectedWrites;
        if (pendingWriteQueue == null || pendingWriteQueue.isEmpty()) {
            return;
        }
        Preconditions.checkState(this.protector != null, "flush() called after close()");
        final ProtectedPromise protectedPromise = new ProtectedPromise(channelHandlerContext.channel(), channelHandlerContext.executor(), this.pendingUnprotectedWrites.size());
        ArrayList arrayList = new ArrayList(this.pendingUnprotectedWrites.size());
        while (!this.pendingUnprotectedWrites.isEmpty()) {
            arrayList.add(((ByteBuf) this.pendingUnprotectedWrites.current()).retain());
            protectedPromise.addUnprotectedPromise(this.pendingUnprotectedWrites.remove());
        }
        this.protector.protectFlush(arrayList, new TsiFrameProtector.Consumer<ByteBuf>() { // from class: io.grpc.alts.internal.TsiFrameHandler.1ProtectedFrameWriteFlusher
            @Override // io.grpc.alts.internal.TsiFrameProtector.Consumer
            public void accept(ByteBuf byteBuf) {
                channelHandlerContext.writeAndFlush(byteBuf, protectedPromise.newPromise());
            }
        }, channelHandlerContext.alloc());
        protectedPromise.doneAllocatingPromises();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) {
        channelHandlerContext.bind(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
        channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) {
        channelHandlerContext.deregister(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext channelHandlerContext) {
        channelHandlerContext.read();
    }

    private void destroyProtectorAndWrites() {
        try {
            if (this.pendingUnprotectedWrites != null && !this.pendingUnprotectedWrites.isEmpty()) {
                this.pendingUnprotectedWrites.removeAndFailAll(new ChannelException("Pending write on teardown of TSI handler"));
            }
            this.pendingUnprotectedWrites = null;
            TsiFrameProtector tsiFrameProtector = this.protector;
            if (tsiFrameProtector != null) {
                try {
                    tsiFrameProtector.destroy();
                } finally {
                    this.protector = null;
                }
            }
        } catch (Throwable th) {
            this.pendingUnprotectedWrites = null;
            throw th;
        }
    }
}
