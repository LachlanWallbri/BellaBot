package io.netty.channel.oio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.FileRegion;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.socket.ChannelInputShutdownEvent;
import io.netty.channel.socket.ChannelInputShutdownReadComplete;
import io.netty.util.internal.StringUtil;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class AbstractOioByteChannel extends AbstractOioChannel {
    private static final ChannelMetadata METADATA = new ChannelMetadata(false);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) FileRegion.class) + ')';

    protected abstract int available();

    protected abstract int doReadBytes(ByteBuf byteBuf) throws Exception;

    protected abstract void doWriteBytes(ByteBuf byteBuf) throws Exception;

    protected abstract void doWriteFileRegion(FileRegion fileRegion) throws Exception;

    protected abstract boolean isInputShutdown();

    protected abstract ChannelFuture shutdownInput();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractOioByteChannel(Channel channel) {
        super(channel);
    }

    @Override // io.netty.channel.Channel
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
        if (z || (th instanceof IOException)) {
            closeOnRead(channelPipeline);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x004d, code lost:
    
        r12.readPending = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x0051, code lost:
    
        r2 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:103:0x0052, code lost:
    
        r6 = false;
        r4 = null;
        r1 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x00e5, code lost:
    
        r5 = r2;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00b5, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00b6, code lost:
    
        r6 = r4;
        r4 = r5;
        r5 = r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00e0, code lost:
    
        r1 = r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x003e, code lost:
    
        if (r6.isReadable() != false) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0040, code lost:
    
        r6.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0047, code lost:
    
        if (r7.lastBytesRead() >= 0) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x004a, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:97:0x004b, code lost:
    
        if (r4 == false) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:98:0x004f, code lost:
    
        r6 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00ee A[DONT_GENERATE] */
    @Override // io.netty.channel.oio.AbstractOioChannel
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected void doRead() {
        boolean z;
        ByteBuf byteBuf;
        Throwable th;
        ByteBuf byteBuf2;
        ChannelConfig config = config();
        if (isInputShutdown() || !this.readPending) {
            return;
        }
        boolean z2 = false;
        this.readPending = false;
        ChannelPipeline pipeline = pipeline();
        ByteBufAllocator allocator = config.getAllocator();
        RecvByteBufAllocator.Handle recvBufAllocHandle = unsafe().recvBufAllocHandle();
        recvBufAllocHandle.reset(config);
        boolean z3 = true;
        ByteBuf byteBuf3 = null;
        try {
            ByteBuf allocate = recvBufAllocHandle.allocate(allocator);
            boolean z4 = false;
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
                            z4 = true;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        byteBuf2 = allocate;
                        z = false;
                        z2 = true;
                        byteBuf = byteBuf2;
                        try {
                            handleReadException(pipeline, byteBuf, th, z, recvBufAllocHandle);
                        } finally {
                            if (this.readPending || config.isAutoRead() || (!z2 && isActive())) {
                                read();
                            }
                        }
                    }
                } catch (Throwable th3) {
                    th = th3;
                    byteBuf = allocate;
                    z = false;
                }
            }
            z4 = true;
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
                    z2 = z4;
                    byteBuf2 = allocate;
                    z = z3;
                    byteBuf = byteBuf2;
                    handleReadException(pipeline, byteBuf, th, z, recvBufAllocHandle);
                }
            } else {
                byteBuf3 = allocate;
            }
            if (z4) {
                recvBufAllocHandle.readComplete();
                pipeline.fireChannelReadComplete();
            }
            if (z3) {
                closeOnRead(pipeline);
            }
            if (!this.readPending && !config.isAutoRead() && (z4 || !isActive())) {
            }
        } catch (Throwable th5) {
            th = th5;
            z = false;
            byteBuf = null;
        }
    }

    @Override // io.netty.channel.AbstractChannel
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

    @Override // io.netty.channel.AbstractChannel
    protected final Object filterOutboundMessage(Object obj) throws Exception {
        if ((obj instanceof ByteBuf) || (obj instanceof FileRegion)) {
            return obj;
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
    }
}
