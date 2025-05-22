package io.grpc.netty.shaded.io.netty.channel.epoll;

import android.support.v4.media.session.PlaybackStateCompat;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.AbstractChannel;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.DefaultFileRegion;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.FileRegion;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel;
import io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel;
import io.grpc.netty.shaded.io.netty.channel.unix.FileDescriptor;
import io.grpc.netty.shaded.io.netty.channel.unix.IovArray;
import io.grpc.netty.shaded.io.netty.channel.unix.SocketWritableByteChannel;
import io.grpc.netty.shaded.io.netty.channel.unix.UnixChannelUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.util.Queue;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractEpollStreamChannel extends AbstractEpollChannel implements DuplexChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private WritableByteChannel byteChannel;
    private final Runnable flushTask;
    private FileDescriptor pipeIn;
    private FileDescriptor pipeOut;
    private volatile Queue<SpliceInTask> spliceQueue;
    private static final ChannelMetadata METADATA = new ChannelMetadata(false, 16);
    private static final String EXPECTED_TYPES = " (expected: " + StringUtil.simpleClassName((Class<?>) ByteBuf.class) + ", " + StringUtil.simpleClassName((Class<?>) DefaultFileRegion.class) + ')';
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) AbstractEpollStreamChannel.class);

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public /* bridge */ /* synthetic */ boolean isActive() {
        return super.isActive();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public /* bridge */ /* synthetic */ boolean isOpen() {
        return super.isOpen();
    }

    protected AbstractEpollStreamChannel(Channel channel, int i) {
        this(channel, new LinuxSocket(i));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractEpollStreamChannel(int i) {
        this(new LinuxSocket(i));
    }

    AbstractEpollStreamChannel(LinuxSocket linuxSocket) {
        this(linuxSocket, isSoErrorZero(linuxSocket));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractEpollStreamChannel(Channel channel, LinuxSocket linuxSocket) {
        super(channel, linuxSocket, true);
        this.flushTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.1
            @Override // java.lang.Runnable
            public void run() {
                ((AbstractEpollChannel.AbstractEpollUnsafe) AbstractEpollStreamChannel.this.unsafe()).flush0();
            }
        };
        this.flags |= Native.EPOLLRDHUP;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractEpollStreamChannel(Channel channel, LinuxSocket linuxSocket, SocketAddress socketAddress) {
        super(channel, linuxSocket, socketAddress);
        this.flushTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.1
            @Override // java.lang.Runnable
            public void run() {
                ((AbstractEpollChannel.AbstractEpollUnsafe) AbstractEpollStreamChannel.this.unsafe()).flush0();
            }
        };
        this.flags |= Native.EPOLLRDHUP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractEpollStreamChannel(LinuxSocket linuxSocket, boolean z) {
        super((Channel) null, linuxSocket, z);
        this.flushTask = new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.1
            @Override // java.lang.Runnable
            public void run() {
                ((AbstractEpollChannel.AbstractEpollUnsafe) AbstractEpollStreamChannel.this.unsafe()).flush0();
            }
        };
        this.flags |= Native.EPOLLRDHUP;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public AbstractEpollChannel.AbstractEpollUnsafe newUnsafe() {
        return new EpollStreamUnsafe();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return METADATA;
    }

    public final ChannelFuture spliceTo(AbstractEpollStreamChannel abstractEpollStreamChannel, int i) {
        return spliceTo(abstractEpollStreamChannel, i, newPromise());
    }

    public final ChannelFuture spliceTo(AbstractEpollStreamChannel abstractEpollStreamChannel, int i, ChannelPromise channelPromise) {
        if (abstractEpollStreamChannel.eventLoop() != eventLoop()) {
            throw new IllegalArgumentException("EventLoops are not the same.");
        }
        ObjectUtil.checkPositiveOrZero(i, "len");
        if (abstractEpollStreamChannel.config().getEpollMode() != EpollMode.LEVEL_TRIGGERED || config().getEpollMode() != EpollMode.LEVEL_TRIGGERED) {
            throw new IllegalStateException("spliceTo() supported only when using " + EpollMode.LEVEL_TRIGGERED);
        }
        ObjectUtil.checkNotNull(channelPromise, "promise");
        if (!isOpen()) {
            channelPromise.tryFailure(new ClosedChannelException());
        } else {
            addToSpliceQueue(new SpliceInChannelTask(abstractEpollStreamChannel, i, channelPromise));
            failSpliceIfClosed(channelPromise);
        }
        return channelPromise;
    }

    public final ChannelFuture spliceTo(FileDescriptor fileDescriptor, int i, int i2) {
        return spliceTo(fileDescriptor, i, i2, newPromise());
    }

    public final ChannelFuture spliceTo(FileDescriptor fileDescriptor, int i, int i2, ChannelPromise channelPromise) {
        ObjectUtil.checkPositiveOrZero(i2, "len");
        ObjectUtil.checkPositiveOrZero(i, TypedValues.Cycle.S_WAVE_OFFSET);
        if (config().getEpollMode() != EpollMode.LEVEL_TRIGGERED) {
            throw new IllegalStateException("spliceTo() supported only when using " + EpollMode.LEVEL_TRIGGERED);
        }
        ObjectUtil.checkNotNull(channelPromise, "promise");
        if (!isOpen()) {
            channelPromise.tryFailure(new ClosedChannelException());
        } else {
            addToSpliceQueue(new SpliceFdTask(fileDescriptor, i, i2, channelPromise));
            failSpliceIfClosed(channelPromise);
        }
        return channelPromise;
    }

    private void failSpliceIfClosed(ChannelPromise channelPromise) {
        if (isOpen() || !channelPromise.tryFailure(new ClosedChannelException())) {
            return;
        }
        eventLoop().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.2
            @Override // java.lang.Runnable
            public void run() {
                AbstractEpollStreamChannel.this.clearSpliceQueue();
            }
        });
    }

    private int writeBytes(ChannelOutboundBuffer channelOutboundBuffer, ByteBuf byteBuf) throws Exception {
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes == 0) {
            channelOutboundBuffer.remove();
            return 0;
        }
        if (byteBuf.hasMemoryAddress() || byteBuf.nioBufferCount() == 1) {
            return doWriteBytes(channelOutboundBuffer, byteBuf);
        }
        ByteBuffer[] nioBuffers = byteBuf.nioBuffers();
        return writeBytesMultiple(channelOutboundBuffer, nioBuffers, nioBuffers.length, readableBytes, config().getMaxBytesPerGatheringWrite());
    }

    private void adjustMaxBytesPerGatheringWrite(long j, long j2, long j3) {
        if (j == j2) {
            long j4 = j << 1;
            if (j4 > j3) {
                config().setMaxBytesPerGatheringWrite(j4);
                return;
            }
            return;
        }
        if (j > PlaybackStateCompat.ACTION_SKIP_TO_QUEUE_ITEM) {
            long j5 = j >>> 1;
            if (j2 < j5) {
                config().setMaxBytesPerGatheringWrite(j5);
            }
        }
    }

    private int writeBytesMultiple(ChannelOutboundBuffer channelOutboundBuffer, IovArray iovArray) throws IOException {
        long size = iovArray.size();
        long writevAddresses = this.socket.writevAddresses(iovArray.memoryAddress(0), iovArray.count());
        if (writevAddresses <= 0) {
            return Integer.MAX_VALUE;
        }
        adjustMaxBytesPerGatheringWrite(size, writevAddresses, iovArray.maxBytes());
        channelOutboundBuffer.removeBytes(writevAddresses);
        return 1;
    }

    private int writeBytesMultiple(ChannelOutboundBuffer channelOutboundBuffer, ByteBuffer[] byteBufferArr, int i, long j, long j2) throws IOException {
        if (j > j2) {
            j = j2;
        }
        long writev = this.socket.writev(byteBufferArr, 0, i, j);
        if (writev <= 0) {
            return Integer.MAX_VALUE;
        }
        adjustMaxBytesPerGatheringWrite(j, writev, j2);
        channelOutboundBuffer.removeBytes(writev);
        return 1;
    }

    private int writeDefaultFileRegion(ChannelOutboundBuffer channelOutboundBuffer, DefaultFileRegion defaultFileRegion) throws Exception {
        long transferred = defaultFileRegion.transferred();
        long count = defaultFileRegion.count();
        if (transferred >= count) {
            channelOutboundBuffer.remove();
            return 0;
        }
        long sendFile = this.socket.sendFile(defaultFileRegion, defaultFileRegion.position(), transferred, count - transferred);
        if (sendFile <= 0) {
            if (sendFile != 0) {
                return Integer.MAX_VALUE;
            }
            validateFileRegion(defaultFileRegion, transferred);
            return Integer.MAX_VALUE;
        }
        channelOutboundBuffer.progress(sendFile);
        if (defaultFileRegion.transferred() < count) {
            return 1;
        }
        channelOutboundBuffer.remove();
        return 1;
    }

    private int writeFileRegion(ChannelOutboundBuffer channelOutboundBuffer, FileRegion fileRegion) throws Exception {
        if (fileRegion.transferred() >= fileRegion.count()) {
            channelOutboundBuffer.remove();
            return 0;
        }
        if (this.byteChannel == null) {
            this.byteChannel = new EpollSocketWritableByteChannel();
        }
        long transferTo = fileRegion.transferTo(this.byteChannel, fileRegion.transferred());
        if (transferTo <= 0) {
            return Integer.MAX_VALUE;
        }
        channelOutboundBuffer.progress(transferTo);
        if (fileRegion.transferred() < fileRegion.count()) {
            return 1;
        }
        channelOutboundBuffer.remove();
        return 1;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        int doWriteSingle;
        int writeSpinCount = config().getWriteSpinCount();
        do {
            int size = channelOutboundBuffer.size();
            if (size > 1 && (channelOutboundBuffer.current() instanceof ByteBuf)) {
                doWriteSingle = doWriteMultiple(channelOutboundBuffer);
            } else {
                if (size == 0) {
                    clearFlag(Native.EPOLLOUT);
                    return;
                }
                doWriteSingle = doWriteSingle(channelOutboundBuffer);
            }
            writeSpinCount -= doWriteSingle;
        } while (writeSpinCount > 0);
        if (writeSpinCount == 0) {
            clearFlag(Native.EPOLLOUT);
            eventLoop().execute(this.flushTask);
        } else {
            setFlag(Native.EPOLLOUT);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int doWriteSingle(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        Object current = channelOutboundBuffer.current();
        if (current instanceof ByteBuf) {
            return writeBytes(channelOutboundBuffer, (ByteBuf) current);
        }
        if (current instanceof DefaultFileRegion) {
            return writeDefaultFileRegion(channelOutboundBuffer, (DefaultFileRegion) current);
        }
        if (current instanceof FileRegion) {
            return writeFileRegion(channelOutboundBuffer, (FileRegion) current);
        }
        if (current instanceof SpliceOutTask) {
            if (!((SpliceOutTask) current).spliceOut()) {
                return Integer.MAX_VALUE;
            }
            channelOutboundBuffer.remove();
            return 1;
        }
        throw new Error();
    }

    private int doWriteMultiple(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        long maxBytesPerGatheringWrite = config().getMaxBytesPerGatheringWrite();
        IovArray cleanIovArray = ((EpollEventLoop) eventLoop()).cleanIovArray();
        cleanIovArray.maxBytes(maxBytesPerGatheringWrite);
        channelOutboundBuffer.forEachFlushedMessage(cleanIovArray);
        if (cleanIovArray.count() >= 1) {
            return writeBytesMultiple(channelOutboundBuffer, cleanIovArray);
        }
        channelOutboundBuffer.removeBytes(0L);
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public Object filterOutboundMessage(Object obj) {
        if (obj instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) obj;
            return UnixChannelUtil.isBufferCopyNeededForWrite(byteBuf) ? newDirectBuffer(byteBuf) : byteBuf;
        }
        if ((obj instanceof FileRegion) || (obj instanceof SpliceOutTask)) {
            return obj;
        }
        throw new UnsupportedOperationException("unsupported message type: " + StringUtil.simpleClassName(obj) + EXPECTED_TYPES);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected final void doShutdownOutput() throws Exception {
        this.socket.shutdown(false, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutdownInput0(ChannelPromise channelPromise) {
        try {
            this.socket.shutdown(true, false);
            channelPromise.setSuccess();
        } catch (Throwable th) {
            channelPromise.setFailure(th);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public boolean isOutputShutdown() {
        return this.socket.isOutputShutdown();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public boolean isInputShutdown() {
        return this.socket.isInputShutdown();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public boolean isShutdown() {
        return this.socket.isShutdown();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdownOutput() {
        return shutdownOutput(newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdownOutput(final ChannelPromise channelPromise) {
        EventLoop eventLoop = eventLoop();
        if (eventLoop.inEventLoop()) {
            ((AbstractChannel.AbstractUnsafe) unsafe()).shutdownOutput(channelPromise);
        } else {
            eventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.3
                @Override // java.lang.Runnable
                public void run() {
                    ((AbstractChannel.AbstractUnsafe) AbstractEpollStreamChannel.this.unsafe()).shutdownOutput(channelPromise);
                }
            });
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdownInput() {
        return shutdownInput(newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdownInput(final ChannelPromise channelPromise) {
        Executor prepareToClose = ((EpollStreamUnsafe) unsafe()).prepareToClose();
        if (prepareToClose != null) {
            prepareToClose.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.4
                @Override // java.lang.Runnable
                public void run() {
                    AbstractEpollStreamChannel.this.shutdownInput0(channelPromise);
                }
            });
        } else {
            EventLoop eventLoop = eventLoop();
            if (eventLoop.inEventLoop()) {
                shutdownInput0(channelPromise);
            } else {
                eventLoop.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.5
                    @Override // java.lang.Runnable
                    public void run() {
                        AbstractEpollStreamChannel.this.shutdownInput0(channelPromise);
                    }
                });
            }
        }
        return channelPromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdown() {
        return shutdown(newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.socket.DuplexChannel
    public ChannelFuture shutdown(final ChannelPromise channelPromise) {
        ChannelFuture shutdownOutput = shutdownOutput();
        if (shutdownOutput.isDone()) {
            shutdownOutputDone(shutdownOutput, channelPromise);
        } else {
            shutdownOutput.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.6
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    AbstractEpollStreamChannel.this.shutdownOutputDone(channelFuture, channelPromise);
                }
            });
        }
        return channelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void shutdownOutputDone(final ChannelFuture channelFuture, final ChannelPromise channelPromise) {
        ChannelFuture shutdownInput = shutdownInput();
        if (shutdownInput.isDone()) {
            shutdownDone(channelFuture, shutdownInput, channelPromise);
        } else {
            shutdownInput.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.7
                @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                    AbstractEpollStreamChannel.shutdownDone(channelFuture, channelFuture2, channelPromise);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void shutdownDone(ChannelFuture channelFuture, ChannelFuture channelFuture2, ChannelPromise channelPromise) {
        Throwable cause = channelFuture.cause();
        Throwable cause2 = channelFuture2.cause();
        if (cause != null) {
            if (cause2 != null) {
                logger.debug("Exception suppressed because a previous exception occurred.", cause2);
            }
            channelPromise.setFailure(cause);
        } else if (cause2 != null) {
            channelPromise.setFailure(cause2);
        } else {
            channelPromise.setSuccess();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel, io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    public void doClose() throws Exception {
        try {
            super.doClose();
        } finally {
            safeClosePipe(this.pipeIn);
            safeClosePipe(this.pipeOut);
            clearSpliceQueue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearSpliceQueue() {
        Queue<SpliceInTask> queue = this.spliceQueue;
        if (queue == null) {
            return;
        }
        ClosedChannelException closedChannelException = null;
        while (true) {
            SpliceInTask poll = queue.poll();
            if (poll == null) {
                return;
            }
            if (closedChannelException == null) {
                closedChannelException = new ClosedChannelException();
            }
            poll.promise.tryFailure(closedChannelException);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void safeClosePipe(FileDescriptor fileDescriptor) {
        if (fileDescriptor != null) {
            try {
                fileDescriptor.close();
            } catch (IOException e) {
                logger.warn("Error while closing a pipe", (Throwable) e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class EpollStreamUnsafe extends AbstractEpollChannel.AbstractEpollUnsafe {
        /* JADX INFO: Access modifiers changed from: package-private */
        public EpollStreamUnsafe() {
            super();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel.AbstractUnsafe
        public Executor prepareToClose() {
            return super.prepareToClose();
        }

        private void handleReadException(ChannelPipeline channelPipeline, ByteBuf byteBuf, Throwable th, boolean z, EpollRecvByteAllocatorHandle epollRecvByteAllocatorHandle) {
            if (byteBuf != null) {
                if (byteBuf.isReadable()) {
                    this.readPending = false;
                    channelPipeline.fireChannelRead((Object) byteBuf);
                } else {
                    byteBuf.release();
                }
            }
            epollRecvByteAllocatorHandle.readComplete();
            channelPipeline.fireChannelReadComplete();
            channelPipeline.fireExceptionCaught(th);
            if (z || (th instanceof OutOfMemoryError) || (th instanceof IOException)) {
                shutdownInput(false);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel.AbstractEpollUnsafe
        EpollRecvByteAllocatorHandle newEpollHandle(RecvByteBufAllocator.ExtendedHandle extendedHandle) {
            return new EpollRecvByteAllocatorStreamingHandle(extendedHandle);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* JADX WARN: Code restructure failed: missing block: B:63:0x003c, code lost:
        
            if (r4 != null) goto L11;
         */
        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:22:0x009e A[Catch: all -> 0x00a2, TRY_LEAVE, TryCatch #0 {all -> 0x00a2, blocks: (B:53:0x007b, B:20:0x0096, B:22:0x009e), top: B:52:0x007b }] */
        @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollChannel.AbstractEpollUnsafe
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void epollInReady() {
            ByteBuf byteBuf;
            Throwable th;
            boolean z;
            Throwable th2;
            ByteBuf allocate;
            boolean z2;
            EpollChannelConfig config = AbstractEpollStreamChannel.this.config();
            if (AbstractEpollStreamChannel.this.shouldBreakEpollInReady(config)) {
                clearEpollIn0();
                return;
            }
            EpollRecvByteAllocatorHandle recvBufAllocHandle = recvBufAllocHandle();
            recvBufAllocHandle.edgeTriggered(AbstractEpollStreamChannel.this.isFlagSet(Native.EPOLLET));
            ChannelPipeline pipeline = AbstractEpollStreamChannel.this.pipeline();
            ByteBufAllocator allocator = config.getAllocator();
            recvBufAllocHandle.reset(config);
            epollInBefore();
            Queue queue = null;
            do {
                if (queue == null) {
                    try {
                        queue = AbstractEpollStreamChannel.this.spliceQueue;
                    } catch (Throwable th3) {
                        th = th3;
                        byteBuf = null;
                        z = false;
                        th2 = th;
                        try {
                            handleReadException(pipeline, byteBuf, th2, z, recvBufAllocHandle);
                        } finally {
                            epollInFinally(config);
                        }
                    }
                }
                SpliceInTask spliceInTask = (SpliceInTask) queue.peek();
                try {
                    if (spliceInTask != null) {
                        if (!spliceInTask.spliceIn(recvBufAllocHandle)) {
                            break;
                        } else if (AbstractEpollStreamChannel.this.isActive()) {
                            queue.remove();
                        }
                    }
                    recvBufAllocHandle.lastBytesRead(AbstractEpollStreamChannel.this.doReadBytes(allocate));
                } catch (Throwable th4) {
                    th = th4;
                    byteBuf = allocate;
                    z = false;
                    th2 = th;
                    handleReadException(pipeline, byteBuf, th2, z, recvBufAllocHandle);
                }
                allocate = recvBufAllocHandle.allocate(allocator);
                if (recvBufAllocHandle.lastBytesRead() <= 0) {
                    allocate.release();
                    z2 = recvBufAllocHandle.lastBytesRead() < 0;
                    if (z2) {
                        try {
                            this.readPending = false;
                        } catch (Throwable th5) {
                            th2 = th5;
                            byteBuf = null;
                            z = z2;
                            handleReadException(pipeline, byteBuf, th2, z, recvBufAllocHandle);
                        }
                    }
                    recvBufAllocHandle.readComplete();
                    pipeline.fireChannelReadComplete();
                    if (z2) {
                        shutdownInput(false);
                    }
                }
                recvBufAllocHandle.incMessagesRead(1);
                this.readPending = false;
                pipeline.fireChannelRead((Object) allocate);
                if (AbstractEpollStreamChannel.this.shouldBreakEpollInReady(config)) {
                    break;
                }
            } while (recvBufAllocHandle.continueReading());
            z2 = false;
            recvBufAllocHandle.readComplete();
            pipeline.fireChannelReadComplete();
            if (z2) {
            }
        }
    }

    private void addToSpliceQueue(SpliceInTask spliceInTask) {
        Queue<SpliceInTask> queue = this.spliceQueue;
        if (queue == null) {
            synchronized (this) {
                queue = this.spliceQueue;
                if (queue == null) {
                    queue = PlatformDependent.newMpscQueue();
                    this.spliceQueue = queue;
                }
            }
        }
        queue.add(spliceInTask);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public abstract class SpliceInTask {
        int len;
        final ChannelPromise promise;

        abstract boolean spliceIn(RecvByteBufAllocator.Handle handle);

        protected SpliceInTask(int i, ChannelPromise channelPromise) {
            this.promise = channelPromise;
            this.len = i;
        }

        protected final int spliceIn(FileDescriptor fileDescriptor, RecvByteBufAllocator.Handle handle) throws IOException {
            int min = Math.min(handle.guess(), this.len);
            int i = 0;
            while (true) {
                int splice = Native.splice(AbstractEpollStreamChannel.this.socket.intValue(), -1L, fileDescriptor.intValue(), -1L, min);
                if (splice == 0) {
                    return i;
                }
                i += splice;
                min -= splice;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class SpliceInChannelTask extends SpliceInTask implements ChannelFutureListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        /* renamed from: ch */
        private final AbstractEpollStreamChannel f8334ch;

        SpliceInChannelTask(AbstractEpollStreamChannel abstractEpollStreamChannel, int i, ChannelPromise channelPromise) {
            super(i, channelPromise);
            this.f8334ch = abstractEpollStreamChannel;
        }

        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (channelFuture.isSuccess()) {
                return;
            }
            this.promise.setFailure(channelFuture.cause());
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v10, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
        /* JADX WARN: Type inference failed for: r0v17 */
        /* JADX WARN: Type inference failed for: r0v18 */
        /* JADX WARN: Type inference failed for: r4v1, types: [io.grpc.netty.shaded.io.netty.channel.Channel$Unsafe] */
        @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.SpliceInTask
        public boolean spliceIn(RecvByteBufAllocator.Handle handle) {
            ?? r0;
            if (this.len != 0) {
                try {
                    FileDescriptor fileDescriptor = this.f8334ch.pipeOut;
                    if (fileDescriptor == null) {
                        FileDescriptor[] pipe = FileDescriptor.pipe();
                        this.f8334ch.pipeIn = pipe[0];
                        fileDescriptor = this.f8334ch.pipeOut = pipe[1];
                    }
                    int spliceIn = spliceIn(fileDescriptor, handle);
                    if (spliceIn > 0) {
                        if (this.len != Integer.MAX_VALUE) {
                            this.len -= spliceIn;
                        }
                        if (this.len == 0) {
                            r0 = this.promise;
                        } else {
                            r0 = this.f8334ch.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) this);
                        }
                        boolean isAutoRead = AbstractEpollStreamChannel.this.config().isAutoRead();
                        this.f8334ch.unsafe().write(new SpliceOutTask(this.f8334ch, spliceIn, isAutoRead), r0);
                        this.f8334ch.unsafe().flush();
                        if (isAutoRead && !r0.isDone()) {
                            AbstractEpollStreamChannel.this.config().setAutoRead(false);
                        }
                    }
                    return this.len == 0;
                } catch (Throwable th) {
                    this.promise.setFailure(th);
                    return true;
                }
            }
            this.promise.setSuccess();
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class SpliceOutTask {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean autoRead;

        /* renamed from: ch */
        private final AbstractEpollStreamChannel f8335ch;
        private int len;

        SpliceOutTask(AbstractEpollStreamChannel abstractEpollStreamChannel, int i, boolean z) {
            this.f8335ch = abstractEpollStreamChannel;
            this.len = i;
            this.autoRead = z;
        }

        public boolean spliceOut() throws Exception {
            try {
                this.len -= Native.splice(this.f8335ch.pipeIn.intValue(), -1L, this.f8335ch.socket.intValue(), -1L, this.len);
                if (this.len != 0) {
                    return false;
                }
                if (this.autoRead) {
                    AbstractEpollStreamChannel.this.config().setAutoRead(true);
                }
                return true;
            } catch (IOException e) {
                if (this.autoRead) {
                    AbstractEpollStreamChannel.this.config().setAutoRead(true);
                }
                throw e;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class SpliceFdTask extends SpliceInTask {
        static final /* synthetic */ boolean $assertionsDisabled = false;

        /* renamed from: fd */
        private final FileDescriptor f8333fd;
        private int offset;
        private final ChannelPromise promise;

        SpliceFdTask(FileDescriptor fileDescriptor, int i, int i2, ChannelPromise channelPromise) {
            super(i2, channelPromise);
            this.f8333fd = fileDescriptor;
            this.promise = channelPromise;
            this.offset = i;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.epoll.AbstractEpollStreamChannel.SpliceInTask
        public boolean spliceIn(RecvByteBufAllocator.Handle handle) {
            if (this.len == 0) {
                this.promise.setSuccess();
                return true;
            }
            try {
                FileDescriptor[] pipe = FileDescriptor.pipe();
                FileDescriptor fileDescriptor = pipe[0];
                FileDescriptor fileDescriptor2 = pipe[1];
                try {
                    int spliceIn = spliceIn(fileDescriptor2, handle);
                    if (spliceIn > 0) {
                        if (this.len != Integer.MAX_VALUE) {
                            this.len -= spliceIn;
                        }
                        do {
                            int splice = Native.splice(fileDescriptor.intValue(), -1L, this.f8333fd.intValue(), this.offset, spliceIn);
                            this.offset += splice;
                            spliceIn -= splice;
                        } while (spliceIn > 0);
                        if (this.len == 0) {
                            this.promise.setSuccess();
                            return true;
                        }
                    }
                    return false;
                } finally {
                    AbstractEpollStreamChannel.safeClosePipe(fileDescriptor);
                    AbstractEpollStreamChannel.safeClosePipe(fileDescriptor2);
                }
            } catch (Throwable th) {
                this.promise.setFailure(th);
                return true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class EpollSocketWritableByteChannel extends SocketWritableByteChannel {
        EpollSocketWritableByteChannel() {
            super(AbstractEpollStreamChannel.this.socket);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.unix.SocketWritableByteChannel
        protected ByteBufAllocator alloc() {
            return AbstractEpollStreamChannel.this.alloc();
        }
    }
}
