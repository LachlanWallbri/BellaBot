package io.netty.channel.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.FileRegion;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.nio.AbstractNioChannel;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.netty.channel.socket.SocketChannelConfig;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractNioByteChannel extends AbstractNioChannel {
    private final Runnable flushTask;
    private boolean inputClosedSeenErrorOnRead;
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) FileRegion.class) + ')';

    protected abstract int doReadBytes(ByteBuf byteBuf) throws Exception;

    protected abstract int doWriteBytes(ByteBuf byteBuf) throws Exception;

    protected abstract long doWriteFileRegion(FileRegion fileRegion) throws Exception;

    protected boolean isInputShutdown0() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ChannelFuture shutdownInput();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractNioByteChannel(Channel channel, SelectableChannel selectableChannel) {
        super(channel, selectableChannel, 1);
        this.flushTask = new Runnable() { // from class: io.netty.channel.nio.AbstractNioByteChannel.1
            @Override // java.lang.Runnable
            public void run() {
                ((AbstractNioChannel.AbstractNioUnsafe) AbstractNioByteChannel.this.unsafe()).flush0();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.channel.AbstractChannel
    public AbstractNioChannel.AbstractNioUnsafe newUnsafe() {
        return new NioByteUnsafe();
    }

    @Override // io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    final boolean shouldBreakReadReady(ChannelConfig channelConfig) {
        return isInputShutdown0() && (this.inputClosedSeenErrorOnRead || !isAllowHalfClosure(channelConfig));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isAllowHalfClosure(ChannelConfig channelConfig) {
        return (channelConfig instanceof SocketChannelConfig) && ((SocketChannelConfig) channelConfig).isAllowHalfClosure();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* loaded from: classes.dex */
    public class NioByteUnsafe extends AbstractNioChannel.AbstractNioUnsafe {
        /* JADX INFO: Access modifiers changed from: protected */
        public NioByteUnsafe() {
            super();
        }

        private void closeOnRead(ChannelPipeline channelPipeline) {
            if (!AbstractNioByteChannel.this.isInputShutdown0()) {
                if (AbstractNioByteChannel.isAllowHalfClosure(AbstractNioByteChannel.this.config())) {
                    AbstractNioByteChannel.this.shutdownInput();
                    channelPipeline.fireUserEventTriggered((Object) ChannelInputShutdownEvent.INSTANCE);
                    return;
                } else {
                    close(voidPromise());
                    return;
                }
            }
            AbstractNioByteChannel.this.inputClosedSeenErrorOnRead = true;
            channelPipeline.fireUserEventTriggered((Object) ChannelInputShutdownReadComplete.INSTANCE);
        }

        private void handleReadException(ChannelPipeline channelPipeline, ByteBuf byteBuf, Throwable th, boolean z, RecvByteBufAllocator.Handle handle) {
            if (byteBuf != null) {
                if (byteBuf.isReadable()) {
                    AbstractNioByteChannel.this.readPending = false;
                    channelPipeline.fireChannelRead((Object) byteBuf);
                } else {
                    byteBuf.release();
                }
            }
            handle.readComplete();
            channelPipeline.fireChannelReadComplete();
            channelPipeline.fireExceptionCaught(th);
            if (z || (th instanceof IOException)) {
                closeOnRead(channelPipeline);
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:31:0x003b, code lost:
        
            r5.release();
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x0042, code lost:
        
            if (r7.lastBytesRead() >= 0) goto L15;
         */
        /* JADX WARN: Code restructure failed: missing block: B:34:0x0045, code lost:
        
            r8 = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x0046, code lost:
        
            if (r8 == false) goto L21;
         */
        /* JADX WARN: Code restructure failed: missing block: B:36:0x0052, code lost:
        
            r4 = r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x0048, code lost:
        
            r9.this$0.readPending = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:40:0x004d, code lost:
        
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x004e, code lost:
        
            r5 = r1;
            r4 = null;
            r6 = r8;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x0086, code lost:
        
            handleReadException(r3, r4, r5, r6, r7);
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:?, code lost:
        
            return;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x0099, code lost:
        
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x009e, code lost:
        
            if (r9.this$0.readPending == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:57:0x00a6, code lost:
        
            removeReadOp();
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x00a9, code lost:
        
            throw r1;
         */
        @Override // io.netty.channel.nio.AbstractNioChannel.NioUnsafe
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final void read() {
            boolean z;
            Throwable th;
            boolean z2;
            ByteBuf byteBuf;
            ChannelConfig config = AbstractNioByteChannel.this.config();
            if (AbstractNioByteChannel.this.shouldBreakReadReady(config)) {
                AbstractNioByteChannel.this.clearReadPending();
                return;
            }
            ChannelPipeline pipeline = AbstractNioByteChannel.this.pipeline();
            ByteBufAllocator allocator = config.getAllocator();
            RecvByteBufAllocator.Handle recvBufAllocHandle = recvBufAllocHandle();
            recvBufAllocHandle.reset(config);
            while (true) {
                z = false;
                try {
                    ByteBuf allocate = recvBufAllocHandle.allocate(allocator);
                    try {
                        recvBufAllocHandle.lastBytesRead(AbstractNioByteChannel.this.doReadBytes(allocate));
                        boolean z3 = true;
                        if (recvBufAllocHandle.lastBytesRead() <= 0) {
                            break;
                        }
                        recvBufAllocHandle.incMessagesRead(1);
                        AbstractNioByteChannel.this.readPending = false;
                        pipeline.fireChannelRead((Object) allocate);
                        if (!recvBufAllocHandle.continueReading()) {
                            break;
                        }
                    } catch (Throwable th2) {
                        z2 = false;
                        byteBuf = allocate;
                        th = th2;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    z2 = z;
                    byteBuf = null;
                }
            }
            recvBufAllocHandle.readComplete();
            pipeline.fireChannelReadComplete();
            if (z) {
                closeOnRead(pipeline);
            }
            if (AbstractNioByteChannel.this.readPending || config.isAutoRead()) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int doWrite0(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        if (channelOutboundBuffer.current() == null) {
            return 0;
        }
        return doWriteInternal(channelOutboundBuffer, channelOutboundBuffer.current());
    }

    private int doWriteInternal(ChannelOutboundBuffer channelOutboundBuffer, Object obj) throws Exception {
        if (obj instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) obj;
            if (!byteBuf.isReadable()) {
                channelOutboundBuffer.remove();
                return 0;
            }
            int doWriteBytes = doWriteBytes(byteBuf);
            if (doWriteBytes <= 0) {
                return Integer.MAX_VALUE;
            }
            channelOutboundBuffer.progress(doWriteBytes);
            if (!byteBuf.isReadable()) {
                channelOutboundBuffer.remove();
            }
            return 1;
        }
        if (obj instanceof FileRegion) {
            FileRegion fileRegion = (FileRegion) obj;
            if (fileRegion.transferred() >= fileRegion.count()) {
                channelOutboundBuffer.remove();
                return 0;
            }
            long doWriteFileRegion = doWriteFileRegion(fileRegion);
            if (doWriteFileRegion <= 0) {
                return Integer.MAX_VALUE;
            }
            channelOutboundBuffer.progress(doWriteFileRegion);
            if (fileRegion.transferred() >= fileRegion.count()) {
                channelOutboundBuffer.remove();
            }
            return 1;
        }
        throw new Error();
    }

    @Override // io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        int writeSpinCount = config().getWriteSpinCount();
        do {
            Object current = channelOutboundBuffer.current();
            if (current == null) {
                clearOpWrite();
                return;
            }
            writeSpinCount -= doWriteInternal(channelOutboundBuffer, current);
        } while (writeSpinCount > 0);
        incompleteWrite(writeSpinCount < 0);
    }

    @Override // io.netty.channel.AbstractChannel
    protected final Object filterOutboundMessage(Object obj) {
        if (obj instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) obj;
            return byteBuf.isDirect() ? obj : newDirectBuffer(byteBuf);
        }
        if (obj instanceof FileRegion) {
            return obj;
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void incompleteWrite(boolean z) {
        if (z) {
            setOpWrite();
        } else {
            clearOpWrite();
            eventLoop().execute(this.flushTask);
        }
    }

    protected final void setOpWrite() {
        SelectionKey selectionKey = selectionKey();
        if (selectionKey.isValid()) {
            int interestOps = selectionKey.interestOps();
            if ((interestOps & 4) == 0) {
                selectionKey.interestOps(interestOps | 4);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void clearOpWrite() {
        SelectionKey selectionKey = selectionKey();
        if (selectionKey.isValid()) {
            int interestOps = selectionKey.interestOps();
            if ((interestOps & 4) != 0) {
                selectionKey.interestOps(interestOps & (-5));
            }
        }
    }
}
