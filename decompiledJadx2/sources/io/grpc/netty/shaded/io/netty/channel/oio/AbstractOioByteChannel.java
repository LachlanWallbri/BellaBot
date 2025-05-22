package io.grpc.netty.shaded.io.netty.channel.oio;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOption;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.FileRegion;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.socket.ChannelInputShutdownEvent;
import io.grpc.netty.shaded.io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractOioByteChannel extends AbstractOioChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata(false);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) FileRegion.class) + ')';

    protected abstract int available();

    protected abstract int doReadBytes(ByteBuf byteBuf) throws Exception;

    protected abstract void doWriteBytes(ByteBuf byteBuf) throws Exception;

    protected abstract void doWriteFileRegion(FileRegion fileRegion) throws Exception;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract boolean isInputShutdown();

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract ChannelFuture shutdownInput();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractOioByteChannel(Channel channel) {
        super(channel);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    private void closeOnRead(ChannelPipeline channelPipeline) {
        if (isOpen()) {
            if (Boolean.TRUE.equals(config().getOption(ChannelOption.ALLOW_HALF_CLOSURE))) {
                shutdownInput();
                channelPipeline.fireUserEventTriggered((Object) ChannelInputShutdownEvent.INSTANCE);
            } else {
                unsafe().close(unsafe().voidPromise());
            }
            channelPipeline.fireUserEventTriggered((Object) ChannelInputShutdownReadComplete.INSTANCE);
        }
    }

    private void handleReadException(ChannelPipeline channelPipeline, ByteBuf byteBuf, Throwable th, boolean z, RecvByteBufAllocator.Handle handle) {
        if (byteBuf != null) {
            if (byteBuf.isReadable()) {
                this.readPending = false;
                channelPipeline.fireChannelRead((Object) byteBuf);
            } else {
                byteBuf.release();
            }
        }
        handle.readComplete();
        channelPipeline.fireChannelReadComplete();
        channelPipeline.fireExceptionCaught(th);
        if (z || (th instanceof OutOfMemoryError) || (th instanceof IOException)) {
            closeOnRead(channelPipeline);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x0051, code lost:
    
        r2 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0052, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x00df, code lost:
    
        r4 = null;
        r5 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b3, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b4, code lost:
    
        r6 = r4;
        r4 = r5;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x003e, code lost:
    
        if (r6.isReadable() != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0040, code lost:
    
        r6.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x0047, code lost:
    
        if (r7.lastBytesRead() >= 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x004a, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x004b, code lost:
    
        if (r4 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x004f, code lost:
    
        r6 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x004d, code lost:
    
        r12.readPending = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00e9  */
    @Override // io.grpc.netty.shaded.io.netty.channel.oio.AbstractOioChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void doRead() {
        boolean z;
        boolean z2;
        ByteBuf byteBuf;
        Throwable th;
        ChannelConfig config = config();
        if (isInputShutdown() || !this.readPending) {
            return;
        }
        this.readPending = false;
        ChannelPipeline pipeline = pipeline();
        ByteBufAllocator allocator = config.getAllocator();
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        recvBufAllocHandle.reset(config);
        boolean z3 = true;
        ByteBuf byteBuf2 = null;
        try {
            ByteBuf allocate = recvBufAllocHandle.allocate(allocator);
            z2 = false;
            while (true) {
                try {
                    recvBufAllocHandle.lastBytesRead(doReadBytes(allocate));
                    if (recvBufAllocHandle.lastBytesRead() <= 0) {
                        break;
                    }
                    try {
                        int available = available();
                        if (available <= 0) {
                            break;
                        }
                        if (!allocate.isWritable()) {
                            int capacity = allocate.capacity();
                            int maxCapacity = allocate.maxCapacity();
                            if (capacity == maxCapacity) {
                                recvBufAllocHandle.incMessagesRead(1);
                                this.readPending = false;
                                pipeline.fireChannelRead((Object) allocate);
                                allocate = recvBufAllocHandle.allocate(allocator);
                            } else if (allocate.writerIndex() + available > maxCapacity) {
                                allocate.capacity(maxCapacity);
                            } else {
                                allocate.ensureWritable(available);
                            }
                        }
                        if (!recvBufAllocHandle.continueReading()) {
                            break;
                        } else {
                            z2 = true;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        z2 = true;
                        byteBuf = allocate;
                        z = false;
                        try {
                            handleReadException(pipeline, byteBuf, th, z, recvBufAllocHandle);
                            if (!this.readPending) {
                                return;
                            }
                        } finally {
                            if (this.readPending || config.isAutoRead() || (!z2 && isActive())) {
                                read();
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
            z2 = true;
            z3 = false;
            if (allocate != null) {
                try {
                    if (allocate.isReadable()) {
                        this.readPending = false;
                        pipeline.fireChannelRead((Object) allocate);
                    } else {
                        allocate.release();
                    }
                } catch (Throwable th4) {
                    th = th4;
                    ByteBuf byteBuf3 = allocate;
                    z = z3;
                    byteBuf = byteBuf3;
                    handleReadException(pipeline, byteBuf, th, z, recvBufAllocHandle);
                    if (!this.readPending) {
                    }
                }
            } else {
                byteBuf2 = allocate;
            }
            if (z2) {
                recvBufAllocHandle.readComplete();
                pipeline.fireChannelReadComplete();
            }
            if (z3) {
                closeOnRead(pipeline);
            }
        } catch (Throwable th5) {
            th = th5;
            z = false;
            z2 = false;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        while (true) {
            Object current = channelOutboundBuffer.current();
            if (current == null) {
                return;
            }
            if (current instanceof ByteBuf) {
                ByteBuf byteBuf = (ByteBuf) current;
                int readableBytes = byteBuf.readableBytes();
                while (readableBytes > 0) {
                    doWriteBytes(byteBuf);
                    int readableBytes2 = byteBuf.readableBytes();
                    channelOutboundBuffer.progress(readableBytes - readableBytes2);
                    readableBytes = readableBytes2;
                }
                channelOutboundBuffer.remove();
            } else if (current instanceof FileRegion) {
                FileRegion fileRegion = (FileRegion) current;
                long transferred = fileRegion.transferred();
                doWriteFileRegion(fileRegion);
                channelOutboundBuffer.progress(fileRegion.transferred() - transferred);
                channelOutboundBuffer.remove();
            } else {
                channelOutboundBuffer.remove(new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(current)));
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected final Object filterOutboundMessage(Object obj) throws Exception {
        if ((obj instanceof ByteBuf) || (obj instanceof FileRegion)) {
            return obj;
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
    }
}
