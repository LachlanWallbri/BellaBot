package io.netty.handler.ssl;

import com.pudutech.mirsdk.SolicitService;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.AbstractCoalescingBufferQueue;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPromise;
import io.netty.channel.ChannelPromiseNotifier;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.UnsupportedMessageTypeException;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.ReferenceCounted;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.FutureListener;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.ImmediateExecutor;
import io.netty.util.concurrent.Promise;
import io.netty.util.concurrent.ScheduledFuture;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class SslHandler extends ByteToMessageDecoder implements ChannelOutboundHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MAX_PLAINTEXT_LENGTH = 16384;
    private boolean closeNotify;
    private volatile long closeNotifyFlushTimeoutMillis;
    private volatile long closeNotifyReadTimeoutMillis;
    private volatile ChannelHandlerContext ctx;
    private final Executor delegatedTaskExecutor;
    private final SSLEngine engine;
    private final SslEngineType engineType;
    private boolean firedChannelRead;
    private boolean flushedBeforeHandshake;
    private Promise<Channel> handshakePromise;
    private boolean handshakeStarted;
    private volatile long handshakeTimeoutMillis;
    private final boolean jdkCompatibilityMode;
    private boolean needsFlush;
    private boolean outboundClosed;
    private int packetLength;
    private SslHandlerCoalescingBufferQueue pendingUnencryptedWrites;
    private boolean readDuringHandshake;
    private boolean sentFirstMessage;
    private final ByteBuffer[] singleBuffer;
    private final LazyChannelPromise sslClosePromise;
    private final boolean startTls;
    volatile int wrapDataSize;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SslHandler.class);
    private static final Pattern IGNORABLE_CLASS_IN_STACK = Pattern.compile("^.*(?:Socket|Datagram|Sctp|Udt)Channel.*$");
    private static final Pattern IGNORABLE_ERROR_MESSAGE = Pattern.compile("^.*(?:connection.*(?:reset|closed|abort|broken)|broken.*pipe).*$", 2);
    private static final SSLException SSLENGINE_CLOSED = (SSLException) ThrowableUtil.unknownStackTrace(new SSLException("SSLEngine closed already"), SslHandler.class, "wrap(...)");
    private static final SSLException HANDSHAKE_TIMED_OUT = (SSLException) ThrowableUtil.unknownStackTrace(new SSLException("handshake timed out"), SslHandler.class, "handshake(...)");
    private static final ClosedChannelException CHANNEL_CLOSED = (ClosedChannelException) ThrowableUtil.unknownStackTrace(new ClosedChannelException(), SslHandler.class, "channelInactive(...)");

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public enum SslEngineType {
        TCNATIVE(true, ByteToMessageDecoder.COMPOSITE_CUMULATOR) { // from class: io.netty.handler.ssl.SslHandler.SslEngineType.1
            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2) throws SSLException {
                SSLEngineResult unwrap;
                int nioBufferCount = byteBuf.nioBufferCount();
                int writerIndex = byteBuf2.writerIndex();
                if (nioBufferCount > 1) {
                    ReferenceCountedOpenSslEngine referenceCountedOpenSslEngine = (ReferenceCountedOpenSslEngine) sslHandler.engine;
                    try {
                        sslHandler.singleBuffer[0] = SslHandler.toByteBuffer(byteBuf2, writerIndex, byteBuf2.writableBytes());
                        unwrap = referenceCountedOpenSslEngine.unwrap(byteBuf.nioBuffers(i, i2), sslHandler.singleBuffer);
                    } finally {
                        sslHandler.singleBuffer[0] = null;
                    }
                } else {
                    unwrap = sslHandler.engine.unwrap(SslHandler.toByteBuffer(byteBuf, i, i2), SslHandler.toByteBuffer(byteBuf2, writerIndex, byteBuf2.writableBytes()));
                }
                byteBuf2.writerIndex(writerIndex + unwrap.bytesProduced());
                return unwrap;
            }

            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            int getPacketBufferSize(SslHandler sslHandler) {
                return ((ReferenceCountedOpenSslEngine) sslHandler.engine).maxEncryptedPacketLength0();
            }

            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            int calculateWrapBufferCapacity(SslHandler sslHandler, int i, int i2) {
                return ((ReferenceCountedOpenSslEngine) sslHandler.engine).calculateMaxLengthForWrap(i, i2);
            }

            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            int calculatePendingData(SslHandler sslHandler, int i) {
                int sslPending = ((ReferenceCountedOpenSslEngine) sslHandler.engine).sslPending();
                return sslPending > 0 ? sslPending : i;
            }

            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return ((ReferenceCountedOpenSslEngine) sSLEngine).jdkCompatibilityMode;
            }
        },
        CONSCRYPT(true ? 1 : 0, ByteToMessageDecoder.COMPOSITE_CUMULATOR) { // from class: io.netty.handler.ssl.SslHandler.SslEngineType.2
            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            int calculatePendingData(SslHandler sslHandler, int i) {
                return i;
            }

            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return true;
            }

            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2) throws SSLException {
                SSLEngineResult unwrap;
                int nioBufferCount = byteBuf.nioBufferCount();
                int writerIndex = byteBuf2.writerIndex();
                if (nioBufferCount > 1) {
                    try {
                        sslHandler.singleBuffer[0] = SslHandler.toByteBuffer(byteBuf2, writerIndex, byteBuf2.writableBytes());
                        unwrap = ((ConscryptAlpnSslEngine) sslHandler.engine).unwrap(byteBuf.nioBuffers(i, i2), sslHandler.singleBuffer);
                    } finally {
                        sslHandler.singleBuffer[0] = null;
                    }
                } else {
                    unwrap = sslHandler.engine.unwrap(SslHandler.toByteBuffer(byteBuf, i, i2), SslHandler.toByteBuffer(byteBuf2, writerIndex, byteBuf2.writableBytes()));
                }
                byteBuf2.writerIndex(writerIndex + unwrap.bytesProduced());
                return unwrap;
            }

            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            int calculateWrapBufferCapacity(SslHandler sslHandler, int i, int i2) {
                return ((ConscryptAlpnSslEngine) sslHandler.engine).calculateOutNetBufSize(i, i2);
            }
        },
        JDK(0 == true ? 1 : 0, ByteToMessageDecoder.MERGE_CUMULATOR) { // from class: io.netty.handler.ssl.SslHandler.SslEngineType.3
            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            int calculatePendingData(SslHandler sslHandler, int i) {
                return i;
            }

            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return true;
            }

            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2) throws SSLException {
                int position;
                int writerIndex = byteBuf2.writerIndex();
                ByteBuffer byteBuffer = SslHandler.toByteBuffer(byteBuf, i, i2);
                int position2 = byteBuffer.position();
                SSLEngineResult unwrap = sslHandler.engine.unwrap(byteBuffer, SslHandler.toByteBuffer(byteBuf2, writerIndex, byteBuf2.writableBytes()));
                byteBuf2.writerIndex(writerIndex + unwrap.bytesProduced());
                return (unwrap.bytesConsumed() != 0 || (position = byteBuffer.position() - position2) == unwrap.bytesConsumed()) ? unwrap : new SSLEngineResult(unwrap.getStatus(), unwrap.getHandshakeStatus(), position, unwrap.bytesProduced());
            }

            @Override // io.netty.handler.ssl.SslHandler.SslEngineType
            int calculateWrapBufferCapacity(SslHandler sslHandler, int i, int i2) {
                return sslHandler.engine.getSession().getPacketBufferSize();
            }
        };

        final ByteToMessageDecoder.Cumulator cumulator;
        final boolean wantsDirectBuffer;

        abstract int calculatePendingData(SslHandler sslHandler, int i);

        abstract int calculateWrapBufferCapacity(SslHandler sslHandler, int i, int i2);

        abstract boolean jdkCompatibilityMode(SSLEngine sSLEngine);

        abstract SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2) throws SSLException;

        static SslEngineType forEngine(SSLEngine sSLEngine) {
            return sSLEngine instanceof ReferenceCountedOpenSslEngine ? TCNATIVE : sSLEngine instanceof ConscryptAlpnSslEngine ? CONSCRYPT : JDK;
        }

        SslEngineType(boolean z, ByteToMessageDecoder.Cumulator cumulator) {
            this.wantsDirectBuffer = z;
            this.cumulator = cumulator;
        }

        int getPacketBufferSize(SslHandler sslHandler) {
            return sslHandler.engine.getSession().getPacketBufferSize();
        }
    }

    public SslHandler(SSLEngine sSLEngine) {
        this(sSLEngine, false);
    }

    public SslHandler(SSLEngine sSLEngine, boolean z) {
        this(sSLEngine, z, ImmediateExecutor.INSTANCE);
    }

    @Deprecated
    public SslHandler(SSLEngine sSLEngine, Executor executor) {
        this(sSLEngine, false, executor);
    }

    @Deprecated
    public SslHandler(SSLEngine sSLEngine, boolean z, Executor executor) {
        this.singleBuffer = new ByteBuffer[1];
        this.handshakePromise = new LazyChannelPromise();
        this.sslClosePromise = new LazyChannelPromise();
        this.handshakeTimeoutMillis = 10000L;
        this.closeNotifyFlushTimeoutMillis = SolicitService.CAMERA_OPEN_TIME_OUT;
        this.wrapDataSize = 16384;
        if (sSLEngine == null) {
            throw new NullPointerException("engine");
        }
        if (executor == null) {
            throw new NullPointerException("delegatedTaskExecutor");
        }
        this.engine = sSLEngine;
        SslEngineType forEngine = SslEngineType.forEngine(sSLEngine);
        this.engineType = forEngine;
        this.delegatedTaskExecutor = executor;
        this.startTls = z;
        this.jdkCompatibilityMode = forEngine.jdkCompatibilityMode(sSLEngine);
        setCumulator(this.engineType.cumulator);
    }

    public long getHandshakeTimeoutMillis() {
        return this.handshakeTimeoutMillis;
    }

    public void setHandshakeTimeout(long j, TimeUnit timeUnit) {
        if (timeUnit == null) {
            throw new NullPointerException("unit");
        }
        setHandshakeTimeoutMillis(timeUnit.toMillis(j));
    }

    public void setHandshakeTimeoutMillis(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("handshakeTimeoutMillis: " + j + " (expected: >= 0)");
        }
        this.handshakeTimeoutMillis = j;
    }

    public final void setWrapDataSize(int i) {
        this.wrapDataSize = i;
    }

    @Deprecated
    public long getCloseNotifyTimeoutMillis() {
        return getCloseNotifyFlushTimeoutMillis();
    }

    @Deprecated
    public void setCloseNotifyTimeout(long j, TimeUnit timeUnit) {
        setCloseNotifyFlushTimeout(j, timeUnit);
    }

    @Deprecated
    public void setCloseNotifyTimeoutMillis(long j) {
        setCloseNotifyFlushTimeoutMillis(j);
    }

    public final long getCloseNotifyFlushTimeoutMillis() {
        return this.closeNotifyFlushTimeoutMillis;
    }

    public final void setCloseNotifyFlushTimeout(long j, TimeUnit timeUnit) {
        setCloseNotifyFlushTimeoutMillis(timeUnit.toMillis(j));
    }

    public final void setCloseNotifyFlushTimeoutMillis(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("closeNotifyFlushTimeoutMillis: " + j + " (expected: >= 0)");
        }
        this.closeNotifyFlushTimeoutMillis = j;
    }

    public final long getCloseNotifyReadTimeoutMillis() {
        return this.closeNotifyReadTimeoutMillis;
    }

    public final void setCloseNotifyReadTimeout(long j, TimeUnit timeUnit) {
        setCloseNotifyReadTimeoutMillis(timeUnit.toMillis(j));
    }

    public final void setCloseNotifyReadTimeoutMillis(long j) {
        if (j < 0) {
            throw new IllegalArgumentException("closeNotifyReadTimeoutMillis: " + j + " (expected: >= 0)");
        }
        this.closeNotifyReadTimeoutMillis = j;
    }

    public SSLEngine engine() {
        return this.engine;
    }

    public String applicationProtocol() {
        Object engine = engine();
        if (engine instanceof ApplicationProtocolAccessor) {
            return ((ApplicationProtocolAccessor) engine).getNegotiatedApplicationProtocol();
        }
        return null;
    }

    public Future<Channel> handshakeFuture() {
        return this.handshakePromise;
    }

    @Deprecated
    public ChannelFuture close() {
        return close(this.ctx.newPromise());
    }

    @Deprecated
    public ChannelFuture close(final ChannelPromise channelPromise) {
        final ChannelHandlerContext channelHandlerContext = this.ctx;
        channelHandlerContext.executor().execute(new Runnable() { // from class: io.netty.handler.ssl.SslHandler.1
            @Override // java.lang.Runnable
            public void run() {
                SslHandler.this.outboundClosed = true;
                SslHandler.this.engine.closeOutbound();
                try {
                    SslHandler.this.flush(channelHandlerContext, channelPromise);
                } catch (Exception e) {
                    if (channelPromise.tryFailure(e)) {
                        return;
                    }
                    SslHandler.logger.warn("{} flush() raised a masked exception.", channelHandlerContext.channel(), e);
                }
            }
        });
        return channelPromise;
    }

    public Future<Channel> sslCloseFuture() {
        return this.sslClosePromise;
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    public void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.pendingUnencryptedWrites.isEmpty()) {
            this.pendingUnencryptedWrites.releaseAndFailAll(channelHandlerContext, new ChannelException("Pending write on removal of SslHandler"));
        }
        this.pendingUnencryptedWrites = null;
        Object obj = this.engine;
        if (obj instanceof ReferenceCounted) {
            ((ReferenceCounted) obj).release();
        }
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.bind(socketAddress, channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.deregister(channelPromise);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        closeOutboundAndChannel(channelHandlerContext, channelPromise, true);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        closeOutboundAndChannel(channelHandlerContext, channelPromise, false);
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.handshakePromise.isDone()) {
            this.readDuringHandshake = true;
        }
        channelHandlerContext.read();
    }

    private static IllegalStateException newPendingWritesNullException() {
        return new IllegalStateException("pendingUnencryptedWrites is null, handlerRemoved0 called?");
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void write(ChannelHandlerContext channelHandlerContext, Object obj, ChannelPromise channelPromise) throws Exception {
        if (!(obj instanceof ByteBuf)) {
            UnsupportedMessageTypeException unsupportedMessageTypeException = new UnsupportedMessageTypeException(obj, (Class<?>[]) new Class[]{ByteBuf.class});
            ReferenceCountUtil.safeRelease(obj);
            channelPromise.setFailure((Throwable) unsupportedMessageTypeException);
        } else {
            SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
            if (sslHandlerCoalescingBufferQueue == null) {
                ReferenceCountUtil.safeRelease(obj);
                channelPromise.setFailure((Throwable) newPendingWritesNullException());
            } else {
                sslHandlerCoalescingBufferQueue.add((ByteBuf) obj, channelPromise);
            }
        }
    }

    @Override // io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.startTls && !this.sentFirstMessage) {
            this.sentFirstMessage = true;
            this.pendingUnencryptedWrites.writeAndRemoveAll(channelHandlerContext);
            forceFlush(channelHandlerContext);
        } else {
            try {
                wrapAndFlush(channelHandlerContext);
            } catch (Throwable th) {
                setHandshakeFailure(channelHandlerContext, th);
                PlatformDependent.throwException(th);
            }
        }
    }

    private void wrapAndFlush(ChannelHandlerContext channelHandlerContext) throws SSLException {
        if (this.pendingUnencryptedWrites.isEmpty()) {
            this.pendingUnencryptedWrites.add(Unpooled.EMPTY_BUFFER, channelHandlerContext.newPromise());
        }
        if (!this.handshakePromise.isDone()) {
            this.flushedBeforeHandshake = true;
        }
        try {
            wrap(channelHandlerContext, false);
        } finally {
            forceFlush(channelHandlerContext);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0082, code lost:
    
        if (r4 == 2) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0085, code lost:
    
        if (r4 == 3) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0088, code lost:
    
        if (r4 == 4) goto L75;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008b, code lost:
    
        if (r4 != 5) goto L41;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x008d, code lost:
    
        r11 = true;
        r6 = r12;
        r7 = r13;
        r8 = r5;
        r9 = r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0058, code lost:
    
        r6.finishWrap(r7, r8, r9, r14, r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005c, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ad, code lost:
    
        throw new java.lang.IllegalStateException("Unknown handshake status: " + r6.getHandshakeStatus());
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00b1, code lost:
    
        setHandshakeSuccessIfStillHandshaking();
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00ae, code lost:
    
        setHandshakeSuccess();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0044, code lost:
    
        r4.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0047, code lost:
    
        r3.tryFailure(io.netty.handler.ssl.SslHandler.SSLENGINE_CLOSED);
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x004c, code lost:
    
        r9 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x004d, code lost:
    
        r12.pendingUnencryptedWrites.releaseAndFailAll(r13, io.netty.handler.ssl.SslHandler.SSLENGINE_CLOSED);
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0054, code lost:
    
        r11 = false;
        r6 = r12;
        r7 = r13;
        r8 = r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x005d, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x005e, code lost:
    
        r4 = r5;
        r5 = null;
     */
    /* JADX WARN: Removed duplicated region for block: B:54:0x00de  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void wrap(ChannelHandlerContext channelHandlerContext, boolean z) throws SSLException {
        ByteBuf byteBuf;
        ChannelPromise channelPromise;
        ByteBuf byteBuf2;
        ChannelPromise channelPromise2;
        ByteBuf removeFirst;
        ByteBufAllocator alloc = channelHandlerContext.alloc();
        ByteBuf byteBuf3 = null;
        try {
            int i = this.wrapDataSize;
            loop0: while (true) {
                ChannelPromise channelPromise3 = null;
                byteBuf2 = null;
                while (true) {
                    try {
                        if (channelHandlerContext.isRemoved()) {
                            channelPromise2 = channelPromise3;
                            break loop0;
                        }
                        channelPromise3 = channelHandlerContext.newPromise();
                        if (i > 0) {
                            removeFirst = this.pendingUnencryptedWrites.remove(alloc, i, channelPromise3);
                        } else {
                            removeFirst = this.pendingUnencryptedWrites.removeFirst(channelPromise3);
                        }
                        if (removeFirst == null) {
                            channelPromise2 = channelPromise3;
                            byteBuf3 = removeFirst;
                            break loop0;
                        }
                        if (byteBuf2 == null) {
                            try {
                                byteBuf2 = allocateOutNetBuf(channelHandlerContext, removeFirst.readableBytes(), removeFirst.nioBufferCount());
                            } catch (Throwable th) {
                                th = th;
                                byteBuf3 = removeFirst;
                                byteBuf = byteBuf2;
                                channelPromise = channelPromise3;
                                if (byteBuf3 != null) {
                                    byteBuf3.release();
                                }
                                finishWrap(channelHandlerContext, byteBuf, channelPromise, z, false);
                                throw th;
                            }
                        }
                        SSLEngineResult wrap = wrap(alloc, this.engine, removeFirst, byteBuf2);
                        if (wrap.getStatus() == SSLEngineResult.Status.CLOSED) {
                            break loop0;
                        }
                        if (removeFirst.isReadable()) {
                            this.pendingUnencryptedWrites.addFirst(removeFirst, channelPromise3);
                            channelPromise3 = null;
                        } else {
                            removeFirst.release();
                        }
                        int i2 = C734110.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[wrap.getHandshakeStatus().ordinal()];
                        if (i2 != 1) {
                            break;
                        } else {
                            runDelegatedTasks();
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                finishWrap(channelHandlerContext, byteBuf2, channelPromise3, z, false);
            }
            if (byteBuf3 != null) {
                byteBuf3.release();
            }
            finishWrap(channelHandlerContext, byteBuf2, channelPromise2, z, false);
        } catch (Throwable th3) {
            th = th3;
            byteBuf = null;
            channelPromise = null;
        }
    }

    private void finishWrap(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, ChannelPromise channelPromise, boolean z, boolean z2) {
        if (byteBuf == null) {
            byteBuf = Unpooled.EMPTY_BUFFER;
        } else if (!byteBuf.isReadable()) {
            byteBuf.release();
            byteBuf = Unpooled.EMPTY_BUFFER;
        }
        if (channelPromise != null) {
            channelHandlerContext.write(byteBuf, channelPromise);
        } else {
            channelHandlerContext.write(byteBuf);
        }
        if (z) {
            this.needsFlush = true;
        }
        if (z2) {
            readIfNeeded(channelHandlerContext);
        }
    }

    private boolean wrapNonAppData(ChannelHandlerContext channelHandlerContext, boolean z) throws SSLException {
        ByteBufAllocator alloc = channelHandlerContext.alloc();
        ByteBuf byteBuf = null;
        while (!channelHandlerContext.isRemoved()) {
            try {
                if (byteBuf == null) {
                    byteBuf = allocateOutNetBuf(channelHandlerContext, 2048, 1);
                }
                SSLEngineResult wrap = wrap(alloc, this.engine, Unpooled.EMPTY_BUFFER, byteBuf);
                if (wrap.bytesProduced() > 0) {
                    channelHandlerContext.write(byteBuf);
                    if (z) {
                        this.needsFlush = true;
                    }
                    byteBuf = null;
                }
                int i = C734110.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[wrap.getHandshakeStatus().ordinal()];
                if (i == 1) {
                    runDelegatedTasks();
                } else {
                    if (i == 2) {
                        setHandshakeSuccess();
                        if (byteBuf != null) {
                            byteBuf.release();
                        }
                        return false;
                    }
                    if (i == 3) {
                        setHandshakeSuccessIfStillHandshaking();
                        if (!z) {
                            unwrapNonAppData(channelHandlerContext);
                        }
                        if (byteBuf != null) {
                            byteBuf.release();
                        }
                        return true;
                    }
                    if (i != 4) {
                        if (i != 5) {
                            throw new IllegalStateException("Unknown handshake status: " + wrap.getHandshakeStatus());
                        }
                        if (z) {
                            return false;
                        }
                        unwrapNonAppData(channelHandlerContext);
                    }
                }
                if (wrap.bytesProduced() == 0 || (wrap.bytesConsumed() == 0 && wrap.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING)) {
                    break;
                }
            } finally {
                if (byteBuf != null) {
                    byteBuf.release();
                }
            }
        }
        if (byteBuf != null) {
            byteBuf.release();
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0080 A[Catch: all -> 0x008c, LOOP:0: B:12:0x0045->B:14:0x0080, LOOP_END, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x008c, blocks: (B:10:0x001c, B:12:0x0045, B:14:0x0080), top: B:9:0x001c }] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0076 A[EDGE_INSN: B:15:0x0076->B:16:0x0076 BREAK  A[LOOP:0: B:12:0x0045->B:14:0x0080], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private SSLEngineResult wrap(ByteBufAllocator byteBufAllocator, SSLEngine sSLEngine, ByteBuf byteBuf, ByteBuf byteBuf2) throws SSLException {
        ByteBuf byteBuf3;
        ByteBuffer[] nioBuffers;
        SSLEngineResult wrap;
        try {
            int readerIndex = byteBuf.readerIndex();
            int readableBytes = byteBuf.readableBytes();
            if (!byteBuf.isDirect() && this.engineType.wantsDirectBuffer) {
                byteBuf3 = byteBufAllocator.directBuffer(readableBytes);
                try {
                    byteBuf3.writeBytes(byteBuf, readerIndex, readableBytes);
                    nioBuffers = this.singleBuffer;
                    nioBuffers[0] = byteBuf3.internalNioBuffer(byteBuf3.readerIndex(), readableBytes);
                    while (true) {
                        wrap = sSLEngine.wrap(nioBuffers, byteBuf2.nioBuffer(byteBuf2.writerIndex(), byteBuf2.writableBytes()));
                        byteBuf.skipBytes(wrap.bytesConsumed());
                        byteBuf2.writerIndex(byteBuf2.writerIndex() + wrap.bytesProduced());
                        if (C734110.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[wrap.getStatus().ordinal()] == 1) {
                            break;
                        }
                        byteBuf2.ensureWritable(sSLEngine.getSession().getPacketBufferSize());
                    }
                    this.singleBuffer[0] = null;
                    if (byteBuf3 != null) {
                        byteBuf3.release();
                    }
                    return wrap;
                } catch (Throwable th) {
                    th = th;
                    this.singleBuffer[0] = null;
                    if (byteBuf3 != null) {
                        byteBuf3.release();
                    }
                    throw th;
                }
            }
            if (!(byteBuf instanceof CompositeByteBuf) && byteBuf.nioBufferCount() == 1) {
                ByteBuffer[] byteBufferArr = this.singleBuffer;
                byteBufferArr[0] = byteBuf.internalNioBuffer(readerIndex, readableBytes);
                nioBuffers = byteBufferArr;
            } else {
                nioBuffers = byteBuf.nioBuffers();
            }
            byteBuf3 = null;
            while (true) {
                wrap = sSLEngine.wrap(nioBuffers, byteBuf2.nioBuffer(byteBuf2.writerIndex(), byteBuf2.writableBytes()));
                byteBuf.skipBytes(wrap.bytesConsumed());
                byteBuf2.writerIndex(byteBuf2.writerIndex() + wrap.bytesProduced());
                if (C734110.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[wrap.getStatus().ordinal()] == 1) {
                }
                byteBuf2.ensureWritable(sSLEngine.getSession().getPacketBufferSize());
            }
            this.singleBuffer[0] = null;
            if (byteBuf3 != null) {
            }
            return wrap;
        } catch (Throwable th2) {
            th = th2;
            byteBuf3 = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.netty.handler.ssl.SslHandler$10 */
    /* loaded from: classes.dex */
    public static /* synthetic */ class C734110 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus;
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$Status;

        static {
            int[] iArr = new int[SSLEngineResult.Status.values().length];
            $SwitchMap$javax$net$ssl$SSLEngineResult$Status = iArr;
            try {
                iArr[SSLEngineResult.Status.BUFFER_OVERFLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[SSLEngineResult.HandshakeStatus.values().length];
            $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = iArr2;
            try {
                iArr2[SSLEngineResult.HandshakeStatus.NEED_TASK.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.FINISHED.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_WRAP.ordinal()] = 4;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_UNWRAP.ordinal()] = 5;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        setHandshakeFailure(channelHandlerContext, CHANNEL_CLOSED, !this.outboundClosed, this.handshakeStarted, false);
        notifyClosePromise(CHANNEL_CLOSED);
        super.channelInactive(channelHandlerContext);
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler, io.netty.channel.ChannelInboundHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) throws Exception {
        if (ignoreException(th)) {
            if (logger.isDebugEnabled()) {
                logger.debug("{} Swallowing a harmless 'connection reset by peer / broken pipe' error that occurred while writing close_notify in response to the peer's close_notify", channelHandlerContext.channel(), th);
            }
            if (channelHandlerContext.channel().isActive()) {
                channelHandlerContext.close();
                return;
            }
            return;
        }
        channelHandlerContext.fireExceptionCaught(th);
    }

    private boolean ignoreException(Throwable th) {
        if (!(th instanceof SSLException) && (th instanceof IOException) && this.sslClosePromise.isDone()) {
            String message = th.getMessage();
            if (message != null && IGNORABLE_ERROR_MESSAGE.matcher(message).matches()) {
                return true;
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                String className = stackTraceElement.getClassName();
                String methodName = stackTraceElement.getMethodName();
                if (!className.startsWith("io.netty.") && "read".equals(methodName)) {
                    if (IGNORABLE_CLASS_IN_STACK.matcher(className).matches()) {
                        return true;
                    }
                    try {
                        Class<?> loadClass = PlatformDependent.getClassLoader(getClass()).loadClass(className);
                        if (!SocketChannel.class.isAssignableFrom(loadClass)) {
                            if (!DatagramChannel.class.isAssignableFrom(loadClass)) {
                                if (PlatformDependent.javaVersion() >= 7 && "com.sun.nio.sctp.SctpChannel".equals(loadClass.getSuperclass().getName())) {
                                }
                            }
                        }
                        return true;
                    } catch (Throwable th2) {
                        logger.debug("Unexpected exception while loading class {} classname {}", getClass(), className, th2);
                    }
                }
            }
        }
        return false;
    }

    public static boolean isEncrypted(ByteBuf byteBuf) {
        if (byteBuf.readableBytes() >= 5) {
            return SslUtils.getEncryptedPacketLength(byteBuf, byteBuf.readerIndex()) != -2;
        }
        throw new IllegalArgumentException("buffer must have at least 5 readable bytes");
    }

    private void decodeJdkCompatible(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) throws NotSslRecordException {
        int i = this.packetLength;
        if (i > 0) {
            if (byteBuf.readableBytes() < i) {
                return;
            }
        } else {
            int readableBytes = byteBuf.readableBytes();
            if (readableBytes < 5) {
                return;
            }
            int encryptedPacketLength = SslUtils.getEncryptedPacketLength(byteBuf, byteBuf.readerIndex());
            if (encryptedPacketLength == -2) {
                NotSslRecordException notSslRecordException = new NotSslRecordException("not an SSL/TLS record: " + ByteBufUtil.hexDump(byteBuf));
                byteBuf.skipBytes(byteBuf.readableBytes());
                setHandshakeFailure(channelHandlerContext, notSslRecordException);
                throw notSslRecordException;
            }
            if (encryptedPacketLength > readableBytes) {
                this.packetLength = encryptedPacketLength;
                return;
            }
            i = encryptedPacketLength;
        }
        this.packetLength = 0;
        try {
            byteBuf.skipBytes(unwrap(channelHandlerContext, byteBuf, byteBuf.readerIndex(), i));
        } catch (Throwable th) {
            handleUnwrapThrowable(channelHandlerContext, th);
        }
    }

    private void decodeNonJdkCompatible(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf) {
        try {
            byteBuf.skipBytes(unwrap(channelHandlerContext, byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes()));
        } catch (Throwable th) {
            handleUnwrapThrowable(channelHandlerContext, th);
        }
    }

    private void handleUnwrapThrowable(ChannelHandlerContext channelHandlerContext, Throwable th) {
        try {
            if (this.handshakePromise.tryFailure(th)) {
                channelHandlerContext.fireUserEventTriggered((Object) new SslHandshakeCompletionEvent(th));
            }
            wrapAndFlush(channelHandlerContext);
        } catch (SSLException e) {
            logger.debug("SSLException during trying to call SSLEngine.wrap(...) because of an previous SSLException, ignoring...", (Throwable) e);
        } finally {
            setHandshakeFailure(channelHandlerContext, th, true, false, true);
        }
        PlatformDependent.throwException(th);
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws SSLException {
        if (this.jdkCompatibilityMode) {
            decodeJdkCompatible(channelHandlerContext, byteBuf);
        } else {
            decodeNonJdkCompatible(channelHandlerContext, byteBuf);
        }
    }

    @Override // io.netty.handler.codec.ByteToMessageDecoder, io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        discardSomeReadBytes();
        flushIfNeeded(channelHandlerContext);
        readIfNeeded(channelHandlerContext);
        this.firedChannelRead = false;
        channelHandlerContext.fireChannelReadComplete();
    }

    private void readIfNeeded(ChannelHandlerContext channelHandlerContext) {
        if (channelHandlerContext.channel().config().isAutoRead()) {
            return;
        }
        if (this.firedChannelRead && this.handshakePromise.isDone()) {
            return;
        }
        channelHandlerContext.read();
    }

    private void flushIfNeeded(ChannelHandlerContext channelHandlerContext) {
        if (this.needsFlush) {
            forceFlush(channelHandlerContext);
        }
    }

    private void unwrapNonAppData(ChannelHandlerContext channelHandlerContext) throws SSLException {
        unwrap(channelHandlerContext, Unpooled.EMPTY_BUFFER, 0, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:17:0x0110, code lost:
    
        throw new java.lang.IllegalStateException("Two consecutive overflows but no content was consumed. " + javax.net.ssl.SSLSession.class.getSimpleName() + " getApplicationBufferSize: " + r18.engine.getSession().getApplicationBufferSize() + " maybe too small.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0047, code lost:
    
        if (r5 == 2) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x004c, code lost:
    
        r5 = io.netty.handler.ssl.SslHandler.C734110.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[r3.ordinal()];
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0054, code lost:
    
        if (r5 == 1) goto L94;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0056, code lost:
    
        if (r5 == 2) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0059, code lost:
    
        if (r5 == 3) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0087, code lost:
    
        if (setHandshakeSuccessIfStillHandshaking() == false) goto L93;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0089, code lost:
    
        r16 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x008e, code lost:
    
        if (r18.flushedBeforeHandshake == false) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x0090, code lost:
    
        r18.flushedBeforeHandshake = false;
        r16 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x0094, code lost:
    
        if (r14 != 0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00a3, code lost:
    
        if (r2 == javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW) goto L89;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a5, code lost:
    
        if (r1 != 0) goto L99;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00a7, code lost:
    
        if (r4 != 0) goto L100;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00ab, code lost:
    
        if (r3 != javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ad, code lost:
    
        readIfNeeded(r19);
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x0014, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x005c, code lost:
    
        if (r5 == 4) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x005f, code lost:
    
        if (r5 != 5) goto L88;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x0078, code lost:
    
        throw new java.lang.IllegalStateException("unknown handshake status: " + r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x007d, code lost:
    
        if (wrapNonAppData(r19, true) == false) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:89:0x007f, code lost:
    
        if (r14 != 0) goto L39;
     */
    /* JADX WARN: Code restructure failed: missing block: B:93:0x0098, code lost:
    
        setHandshakeSuccess();
        r16 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x009e, code lost:
    
        runDelegatedTasks();
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x004a, code lost:
    
        r17 = true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private int unwrap(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, int i, int i2) throws SSLException {
        Throwable th;
        int i3 = i;
        int i4 = i2;
        ByteBuf allocate = allocate(channelHandlerContext, i2);
        boolean z = false;
        boolean z2 = false;
        loop0: while (true) {
            int i5 = -1;
            while (true) {
                try {
                    if (!channelHandlerContext.isRemoved()) {
                        th = null;
                        SSLEngineResult unwrap = this.engineType.unwrap(this, byteBuf, i3, i4, allocate);
                        SSLEngineResult.Status status = unwrap.getStatus();
                        SSLEngineResult.HandshakeStatus handshakeStatus = unwrap.getHandshakeStatus();
                        int bytesProduced = unwrap.bytesProduced();
                        int bytesConsumed = unwrap.bytesConsumed();
                        i3 += bytesConsumed;
                        i4 -= bytesConsumed;
                        int i6 = C734110.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[status.ordinal()];
                        if (i6 == 1) {
                            int readableBytes = allocate.readableBytes();
                            int applicationBufferSize = this.engine.getSession().getApplicationBufferSize() - readableBytes;
                            if (readableBytes > 0) {
                                this.firedChannelRead = true;
                                channelHandlerContext.fireChannelRead((Object) allocate);
                                if (applicationBufferSize <= 0) {
                                    try {
                                        applicationBufferSize = this.engine.getSession().getApplicationBufferSize();
                                    } catch (Throwable th2) {
                                        th = th2;
                                        allocate = null;
                                        if (allocate != null) {
                                            if (allocate.isReadable()) {
                                                this.firedChannelRead = true;
                                                channelHandlerContext.fireChannelRead((Object) allocate);
                                            } else {
                                                allocate.release();
                                            }
                                        }
                                        throw th;
                                    }
                                }
                            } else {
                                allocate.release();
                            }
                            if (readableBytes == 0 && i5 == 0) {
                                break loop0;
                            }
                            allocate = allocate(channelHandlerContext, this.engineType.calculatePendingData(this, applicationBufferSize));
                            i5 = readableBytes;
                        } else {
                            break;
                        }
                    } else {
                        th = null;
                        break loop0;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            }
        }
        if (z) {
            wrap(channelHandlerContext, true);
        }
        if (z2) {
            notifyClosePromise(th);
        }
        if (allocate != null) {
            if (allocate.isReadable()) {
                this.firedChannelRead = true;
                channelHandlerContext.fireChannelRead((Object) allocate);
            } else {
                allocate.release();
            }
        }
        return i2 - i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ByteBuffer toByteBuffer(ByteBuf byteBuf, int i, int i2) {
        return byteBuf.nioBufferCount() == 1 ? byteBuf.internalNioBuffer(i, i2) : byteBuf.nioBuffer(i, i2);
    }

    private void runDelegatedTasks() {
        if (this.delegatedTaskExecutor != ImmediateExecutor.INSTANCE) {
            final ArrayList arrayList = new ArrayList(2);
            while (true) {
                Runnable delegatedTask = this.engine.getDelegatedTask();
                if (delegatedTask == null) {
                    break;
                } else {
                    arrayList.add(delegatedTask);
                }
            }
            if (arrayList.isEmpty()) {
                return;
            }
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            this.delegatedTaskExecutor.execute(new Runnable() { // from class: io.netty.handler.ssl.SslHandler.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        try {
                            Iterator it = arrayList.iterator();
                            while (it.hasNext()) {
                                ((Runnable) it.next()).run();
                            }
                        } catch (Exception e) {
                            SslHandler.this.ctx.fireExceptionCaught((Throwable) e);
                        }
                    } finally {
                        countDownLatch.countDown();
                    }
                }
            });
            boolean z = false;
            while (countDownLatch.getCount() != 0) {
                try {
                    countDownLatch.await();
                } catch (InterruptedException unused) {
                    z = true;
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
                return;
            }
            return;
        }
        while (true) {
            Runnable delegatedTask2 = this.engine.getDelegatedTask();
            if (delegatedTask2 == null) {
                return;
            } else {
                delegatedTask2.run();
            }
        }
    }

    private boolean setHandshakeSuccessIfStillHandshaking() {
        if (this.handshakePromise.isDone()) {
            return false;
        }
        setHandshakeSuccess();
        return true;
    }

    private void setHandshakeSuccess() {
        this.handshakePromise.trySuccess(this.ctx.channel());
        if (logger.isDebugEnabled()) {
            logger.debug("{} HANDSHAKEN: {}", this.ctx.channel(), this.engine.getSession().getCipherSuite());
        }
        this.ctx.fireUserEventTriggered((Object) SslHandshakeCompletionEvent.SUCCESS);
        if (!this.readDuringHandshake || this.ctx.channel().config().isAutoRead()) {
            return;
        }
        this.readDuringHandshake = false;
        this.ctx.read();
    }

    private void setHandshakeFailure(ChannelHandlerContext channelHandlerContext, Throwable th) {
        setHandshakeFailure(channelHandlerContext, th, true, true, false);
    }

    private void setHandshakeFailure(ChannelHandlerContext channelHandlerContext, Throwable th, boolean z, boolean z2, boolean z3) {
        String message;
        try {
            this.outboundClosed = true;
            this.engine.closeOutbound();
            if (z) {
                try {
                    this.engine.closeInbound();
                } catch (SSLException e) {
                    if (logger.isDebugEnabled() && ((message = e.getMessage()) == null || !message.contains("possible truncation attack"))) {
                        logger.debug("{} SSLEngine.closeInbound() raised an exception.", channelHandlerContext.channel(), e);
                    }
                }
            }
            if (this.handshakePromise.tryFailure(th) || z3) {
                SslUtils.handleHandshakeFailure(channelHandlerContext, th, z2);
            }
        } finally {
            releaseAndFailAll(th);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
     */
    /* loaded from: classes8.dex */
    private final class SslTasksRunner implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean inUnwrap;

        SslTasksRunner(boolean z) {
            this.inUnwrap = z;
        }

        private void taskError(Throwable th) {
            if (this.inUnwrap) {
                try {
                    SslHandler.access$800(SslHandler.this, SslHandler.access$700(SslHandler.this), th);
                    return;
                } catch (Throwable th2) {
                    safeExceptionCaught(th2);
                    return;
                }
            }
            SslHandler sslHandler = SslHandler.this;
            SslHandler.access$900(sslHandler, SslHandler.access$700(sslHandler), th);
            SslHandler sslHandler2 = SslHandler.this;
            SslHandler.access$1000(sslHandler2, SslHandler.access$700(sslHandler2));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void safeExceptionCaught(Throwable th) {
            try {
                SslHandler.this.exceptionCaught(SslHandler.access$700(SslHandler.this), wrapIfNeeded(th));
            } catch (Throwable th2) {
                SslHandler.access$700(SslHandler.this).fireExceptionCaught(th2);
            }
        }

        private Throwable wrapIfNeeded(Throwable th) {
            return (this.inUnwrap && !(th instanceof DecoderException)) ? new DecoderException(th) : th;
        }

        private void tryDecodeAgain() {
            try {
                SslHandler.this.channelRead(SslHandler.access$700(SslHandler.this), Unpooled.EMPTY_BUFFER);
            } finally {
                try {
                } finally {
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void resumeOnEventExecutor() {
            int i;
            SslHandler.access$1202(SslHandler.this, false);
            try {
                i = C73499.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SslHandler.this.engine.getHandshakeStatus().ordinal()];
            } catch (Throwable th) {
                safeExceptionCaught(th);
                return;
            }
            if (i != 1) {
                if (i == 2) {
                    long unused = SslHandler.this.closeNotifyReadTimeoutMillis;
                } else if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            try {
                                SslHandler.access$1700(SslHandler.this, SslHandler.access$700(SslHandler.this));
                                tryDecodeAgain();
                                return;
                            } catch (SSLException e) {
                                SslHandler.access$800(SslHandler.this, SslHandler.access$700(SslHandler.this), e);
                                return;
                            }
                        }
                        throw new AssertionError();
                    }
                    try {
                        if (!SslHandler.access$1800(SslHandler.this, SslHandler.access$700(SslHandler.this), false) && this.inUnwrap) {
                            SslHandler.access$1700(SslHandler.this, SslHandler.access$700(SslHandler.this));
                        }
                        SslHandler.access$1000(SslHandler.this, SslHandler.access$700(SslHandler.this));
                        tryDecodeAgain();
                        return;
                    } catch (Throwable th2) {
                        taskError(th2);
                        return;
                    }
                    safeExceptionCaught(th);
                    return;
                }
                LazyChannelPromise unused2 = SslHandler.this.sslClosePromise;
                try {
                    SslHandler.access$1600(SslHandler.this, SslHandler.access$700(SslHandler.this), this.inUnwrap);
                    if (this.inUnwrap) {
                        SslHandler.access$1700(SslHandler.this, SslHandler.access$700(SslHandler.this));
                    }
                    SslHandler.access$1000(SslHandler.this, SslHandler.access$700(SslHandler.this));
                    tryDecodeAgain();
                    return;
                } catch (Throwable th3) {
                    taskError(th3);
                    return;
                }
            }
            SslHandler.access$1300(SslHandler.this, this.inUnwrap);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                SslHandler.access$1900(SslHandler.this.engine);
                SslHandler.access$700(SslHandler.this).executor().execute(new Runnable() { // from class: io.netty.handler.ssl.SslHandler.SslTasksRunner.1
                    @Override // java.lang.Runnable
                    public void run() {
                        SslTasksRunner.this.resumeOnEventExecutor();
                    }
                });
            } catch (Throwable th) {
                handleException(th);
            }
        }

        private void handleException(final Throwable th) {
            if (SslHandler.access$700(SslHandler.this).executor().inEventLoop()) {
                SslHandler.access$1202(SslHandler.this, false);
                safeExceptionCaught(th);
            } else {
                try {
                    SslHandler.access$700(SslHandler.this).executor().execute(new Runnable() { // from class: io.netty.handler.ssl.SslHandler.SslTasksRunner.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SslHandler.access$1202(SslHandler.this, false);
                            SslTasksRunner.this.safeExceptionCaught(th);
                        }
                    });
                } catch (RejectedExecutionException unused) {
                    SslHandler.access$1202(SslHandler.this, false);
                    SslHandler.access$700(SslHandler.this).fireExceptionCaught(th);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAndFailAll(Throwable th) {
        SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
        if (sslHandlerCoalescingBufferQueue != null) {
            sslHandlerCoalescingBufferQueue.releaseAndFailAll(this.ctx, th);
        }
    }

    private void notifyClosePromise(Throwable th) {
        if (th == null) {
            if (this.sslClosePromise.trySuccess(this.ctx.channel())) {
                this.ctx.fireUserEventTriggered((Object) SslCloseCompletionEvent.SUCCESS);
            }
        } else if (this.sslClosePromise.tryFailure(th)) {
            this.ctx.fireUserEventTriggered((Object) new SslCloseCompletionEvent(th));
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1, types: [io.netty.channel.ChannelPromise] */
    /* JADX WARN: Type inference failed for: r7v2, types: [io.netty.channel.ChannelPromise] */
    private void closeOutboundAndChannel(ChannelHandlerContext channelHandlerContext, final ChannelPromise channelPromise, boolean z) throws Exception {
        this.outboundClosed = true;
        this.engine.closeOutbound();
        if (!channelHandlerContext.channel().isActive()) {
            if (z) {
                channelHandlerContext.disconnect(channelPromise);
                return;
            } else {
                channelHandlerContext.close(channelPromise);
                return;
            }
        }
        ChannelPromise newPromise = channelHandlerContext.newPromise();
        try {
            flush(channelHandlerContext, newPromise);
            if (!this.closeNotify) {
                this.closeNotify = true;
                safeClose(channelHandlerContext, newPromise, channelHandlerContext.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelPromiseNotifier(false, channelPromise)));
            } else {
                this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() { // from class: io.netty.handler.ssl.SslHandler.3
                    @Override // io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(Future<Channel> future) {
                        channelPromise.setSuccess();
                    }
                });
            }
        } catch (Throwable th) {
            if (!this.closeNotify) {
                this.closeNotify = true;
                safeClose(channelHandlerContext, newPromise, channelHandlerContext.newPromise().addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelPromiseNotifier(false, channelPromise)));
            } else {
                this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() { // from class: io.netty.handler.ssl.SslHandler.3
                    @Override // io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(Future<Channel> future) {
                        channelPromise.setSuccess();
                    }
                });
            }
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flush(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
        if (sslHandlerCoalescingBufferQueue != null) {
            sslHandlerCoalescingBufferQueue.add(Unpooled.EMPTY_BUFFER, channelPromise);
        } else {
            channelPromise.setFailure((Throwable) newPendingWritesNullException());
        }
        flush(channelHandlerContext);
    }

    @Override // io.netty.channel.ChannelHandlerAdapter, io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        this.pendingUnencryptedWrites = new SslHandlerCoalescingBufferQueue(channelHandlerContext.channel(), 16);
        if (channelHandlerContext.channel().isActive()) {
            startHandshakeProcessing();
        }
    }

    private void startHandshakeProcessing() {
        this.handshakeStarted = true;
        if (this.engine.getUseClientMode()) {
            handshake(null);
        } else {
            applyHandshakeTimeout(null);
        }
    }

    public Future<Channel> renegotiate() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext == null) {
            throw new IllegalStateException();
        }
        return renegotiate(channelHandlerContext.executor().newPromise());
    }

    public Future<Channel> renegotiate(final Promise<Channel> promise) {
        if (promise == null) {
            throw new NullPointerException("promise");
        }
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext == null) {
            throw new IllegalStateException();
        }
        EventExecutor executor = channelHandlerContext.executor();
        if (!executor.inEventLoop()) {
            executor.execute(new Runnable() { // from class: io.netty.handler.ssl.SslHandler.4
                @Override // java.lang.Runnable
                public void run() {
                    SslHandler.this.handshake(promise);
                }
            });
            return promise;
        }
        handshake(promise);
        return promise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handshake(final Promise<Channel> promise) {
        if (promise != null) {
            Promise<Channel> promise2 = this.handshakePromise;
            if (!promise2.isDone()) {
                promise2.addListener((GenericFutureListener<? extends Future<? super Channel>>) new FutureListener<Channel>() { // from class: io.netty.handler.ssl.SslHandler.5
                    @Override // io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(Future<Channel> future) throws Exception {
                        if (future.isSuccess()) {
                            promise.setSuccess(future.getNow());
                        } else {
                            promise.setFailure(future.cause());
                        }
                    }
                });
                return;
            }
            this.handshakePromise = promise;
        } else if (this.engine.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            return;
        } else {
            promise = this.handshakePromise;
        }
        ChannelHandlerContext channelHandlerContext = this.ctx;
        try {
            this.engine.beginHandshake();
            wrapNonAppData(channelHandlerContext, false);
        } finally {
            try {
                forceFlush(channelHandlerContext);
                applyHandshakeTimeout(promise);
            } catch (Throwable th) {
            }
        }
        forceFlush(channelHandlerContext);
        applyHandshakeTimeout(promise);
    }

    private void applyHandshakeTimeout(final Promise<Channel> promise) {
        if (promise == null) {
            promise = this.handshakePromise;
        }
        long j = this.handshakeTimeoutMillis;
        if (j <= 0 || promise.isDone()) {
            return;
        }
        final ScheduledFuture<?> schedule = this.ctx.executor().schedule(new Runnable() { // from class: io.netty.handler.ssl.SslHandler.6
            @Override // java.lang.Runnable
            public void run() {
                if (promise.isDone()) {
                    return;
                }
                try {
                    if (SslHandler.this.handshakePromise.tryFailure(SslHandler.HANDSHAKE_TIMED_OUT)) {
                        SslUtils.handleHandshakeFailure(SslHandler.this.ctx, SslHandler.HANDSHAKE_TIMED_OUT, true);
                    }
                } finally {
                    SslHandler.this.releaseAndFailAll(SslHandler.HANDSHAKE_TIMED_OUT);
                }
            }
        }, j, TimeUnit.MILLISECONDS);
        promise.addListener((GenericFutureListener<? extends Future<? super Channel>>) new FutureListener<Channel>() { // from class: io.netty.handler.ssl.SslHandler.7
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<Channel> future) throws Exception {
                schedule.cancel(false);
            }
        });
    }

    private void forceFlush(ChannelHandlerContext channelHandlerContext) {
        this.needsFlush = false;
        channelHandlerContext.flush();
    }

    @Override // io.netty.channel.ChannelInboundHandlerAdapter, io.netty.channel.ChannelInboundHandler
    public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.startTls) {
            startHandshakeProcessing();
        }
        channelHandlerContext.fireChannelActive();
    }

    private void safeClose(final ChannelHandlerContext channelHandlerContext, final ChannelFuture channelFuture, final ChannelPromise channelPromise) {
        if (!channelHandlerContext.channel().isActive()) {
            channelHandlerContext.close(channelPromise);
            return;
        }
        final ScheduledFuture<?> scheduledFuture = null;
        if (!channelFuture.isDone()) {
            long j = this.closeNotifyFlushTimeoutMillis;
            if (j > 0) {
                scheduledFuture = channelHandlerContext.executor().schedule(new Runnable() { // from class: io.netty.handler.ssl.SslHandler.8
                    @Override // java.lang.Runnable
                    public void run() {
                        if (channelFuture.isDone()) {
                            return;
                        }
                        SslHandler.logger.warn("{} Last write attempt timed out; force-closing the connection.", channelHandlerContext.channel());
                        ChannelHandlerContext channelHandlerContext2 = channelHandlerContext;
                        SslHandler.addCloseListener(channelHandlerContext2.close(channelHandlerContext2.newPromise()), channelPromise);
                    }

                    /* JADX WARN: Classes with same name are omitted:
                      classes5.dex
                     */
                    /* renamed from: io.netty.handler.ssl.SslHandler$8$1, reason: invalid class name */
                    /* loaded from: classes8.dex */
                    class AnonymousClass1 implements Runnable {
                        final /* synthetic */ long val$closeNotifyReadTimeout;

                        AnonymousClass1(long j) {
                            this.val$closeNotifyReadTimeout = j;
                        }

                        @Override // java.lang.Runnable
                        public void run() {
                            if (SslHandler.access$2700(SslHandler.this).isDone()) {
                                return;
                            }
                            SslHandler.access$2400().debug("{} did not receive close_notify in {}ms; force-closing the connection.", channelHandlerContext.channel(), Long.valueOf(this.val$closeNotifyReadTimeout));
                            SslHandler.access$2500(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                        }
                    }

                    /* JADX WARN: Classes with same name are omitted:
                      classes5.dex
                     */
                    /* renamed from: io.netty.handler.ssl.SslHandler$8$2, reason: invalid class name */
                    /* loaded from: classes8.dex */
                    class AnonymousClass2 implements FutureListener<Channel> {
                        final /* synthetic */ java.util.concurrent.ScheduledFuture val$closeNotifyReadTimeoutFuture;

                        AnonymousClass2(java.util.concurrent.ScheduledFuture scheduledFuture) {
                            this.val$closeNotifyReadTimeoutFuture = scheduledFuture;
                        }

                        @Override // io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(Future<Channel> future) throws Exception {
                            java.util.concurrent.ScheduledFuture scheduledFuture = this.val$closeNotifyReadTimeoutFuture;
                            if (scheduledFuture != null) {
                                scheduledFuture.cancel(false);
                            }
                            SslHandler.access$2500(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                        }
                    }
                }, j, TimeUnit.MILLISECONDS);
            }
        }
        channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.netty.handler.ssl.SslHandler.9
            @Override // io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                java.util.concurrent.ScheduledFuture scheduledFuture2 = scheduledFuture;
                if (scheduledFuture2 != null) {
                    scheduledFuture2.cancel(false);
                }
                final long j2 = SslHandler.this.closeNotifyReadTimeoutMillis;
                if (j2 <= 0) {
                    ChannelHandlerContext channelHandlerContext2 = channelHandlerContext;
                    SslHandler.addCloseListener(channelHandlerContext2.close(channelHandlerContext2.newPromise()), channelPromise);
                } else {
                    final ScheduledFuture<?> schedule = !SslHandler.this.sslClosePromise.isDone() ? channelHandlerContext.executor().schedule(new Runnable() { // from class: io.netty.handler.ssl.SslHandler.9.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (SslHandler.this.sslClosePromise.isDone()) {
                                return;
                            }
                            SslHandler.logger.debug("{} did not receive close_notify in {}ms; force-closing the connection.", channelHandlerContext.channel(), Long.valueOf(j2));
                            SslHandler.addCloseListener(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                        }
                    }, j2, TimeUnit.MILLISECONDS) : null;
                    SslHandler.this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() { // from class: io.netty.handler.ssl.SslHandler.9.2
                        @Override // io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(Future<Channel> future) throws Exception {
                            java.util.concurrent.ScheduledFuture scheduledFuture3 = schedule;
                            if (scheduledFuture3 != null) {
                                scheduledFuture3.cancel(false);
                            }
                            SslHandler.addCloseListener(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addCloseListener(ChannelFuture channelFuture, ChannelPromise channelPromise) {
        channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelPromiseNotifier(false, channelPromise));
    }

    private ByteBuf allocate(ChannelHandlerContext channelHandlerContext, int i) {
        ByteBufAllocator alloc = channelHandlerContext.alloc();
        if (this.engineType.wantsDirectBuffer) {
            return alloc.directBuffer(i);
        }
        return alloc.buffer(i);
    }

    private ByteBuf allocateOutNetBuf(ChannelHandlerContext channelHandlerContext, int i, int i2) {
        return allocate(channelHandlerContext, this.engineType.calculateWrapBufferCapacity(this, i, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public final class SslHandlerCoalescingBufferQueue extends AbstractCoalescingBufferQueue {
        @Override // io.netty.channel.AbstractCoalescingBufferQueue
        protected ByteBuf removeEmptyValue() {
            return null;
        }

        SslHandlerCoalescingBufferQueue(Channel channel, int i) {
            super(channel, i);
        }

        @Override // io.netty.channel.AbstractCoalescingBufferQueue
        protected ByteBuf compose(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf, ByteBuf byteBuf2) {
            int i = SslHandler.this.wrapDataSize;
            if (!(byteBuf instanceof CompositeByteBuf)) {
                return SslHandler.attemptCopyToCumulation(byteBuf, byteBuf2, i) ? byteBuf : copyAndCompose(byteBufAllocator, byteBuf, byteBuf2);
            }
            CompositeByteBuf compositeByteBuf = (CompositeByteBuf) byteBuf;
            int numComponents = compositeByteBuf.numComponents();
            if (numComponents == 0 || !SslHandler.attemptCopyToCumulation(compositeByteBuf.internalComponent(numComponents - 1), byteBuf2, i)) {
                compositeByteBuf.addComponent(true, byteBuf2);
            }
            return compositeByteBuf;
        }

        @Override // io.netty.channel.AbstractCoalescingBufferQueue
        protected ByteBuf composeFirst(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf) {
            if (!(byteBuf instanceof CompositeByteBuf)) {
                return byteBuf;
            }
            CompositeByteBuf compositeByteBuf = (CompositeByteBuf) byteBuf;
            ByteBuf directBuffer = byteBufAllocator.directBuffer(compositeByteBuf.readableBytes());
            try {
                directBuffer.writeBytes(compositeByteBuf);
            } catch (Throwable th) {
                directBuffer.release();
                PlatformDependent.throwException(th);
            }
            compositeByteBuf.release();
            return directBuffer;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean attemptCopyToCumulation(ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        int readableBytes = byteBuf2.readableBytes();
        int capacity = byteBuf.capacity();
        if (i - byteBuf.readableBytes() < readableBytes || ((!byteBuf.isWritable(readableBytes) || capacity < i) && (capacity >= i || !ByteBufUtil.ensureWritableSuccess(byteBuf.ensureWritable(readableBytes, false))))) {
            return false;
        }
        byteBuf.writeBytes(byteBuf2);
        byteBuf2.release();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes5.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    public final class LazyChannelPromise extends DefaultPromise<Channel> {
        private LazyChannelPromise() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.netty.util.concurrent.DefaultPromise
        public EventExecutor executor() {
            if (SslHandler.this.ctx != null) {
                return SslHandler.this.ctx.executor();
            }
            throw new IllegalStateException();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.netty.util.concurrent.DefaultPromise
        public void checkDeadLock() {
            if (SslHandler.this.ctx == null) {
                return;
            }
            super.checkDeadLock();
        }
    }
}
