package io.grpc.netty.shaded.io.netty.handler.ssl;

import com.pudutech.mirsdk.SolicitService;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.buffer.CompositeByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.channel.AbstractCoalescingBufferQueue;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelException;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromiseNotifier;
import io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder;
import io.grpc.netty.shaded.io.netty.handler.codec.DecoderException;
import io.grpc.netty.shaded.io.netty.handler.codec.UnsupportedMessageTypeException;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.ReferenceCounted;
import io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise;
import io.grpc.netty.shaded.io.netty.util.concurrent.EventExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.FutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.concurrent.ImmediateExecutor;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.concurrent.PromiseNotifier;
import io.grpc.netty.shaded.io.netty.util.concurrent.ScheduledFuture;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
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
    private boolean processTask;
    private boolean readDuringHandshake;
    private boolean sentFirstMessage;
    private final ByteBuffer[] singleBuffer;
    private final LazyChannelPromise sslClosePromise;
    private final boolean startTls;
    volatile int wrapDataSize;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SslHandler.class);
    private static final Pattern IGNORABLE_CLASS_IN_STACK = Pattern.compile("^.*(?:Socket|Datagram|Sctp|Udt)Channel.*$");
    private static final Pattern IGNORABLE_ERROR_MESSAGE = Pattern.compile("^.*(?:connection.*(?:reset|closed|abort|broken)|broken.*pipe).*$", 2);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum SslEngineType {
        TCNATIVE(true, ByteToMessageDecoder.COMPOSITE_CUMULATOR) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType.1
            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
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

            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2) {
                return byteBufAllocator.directBuffer(((ReferenceCountedOpenSslEngine) sslHandler.engine).calculateMaxLengthForWrap(i, i2));
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            int calculatePendingData(SslHandler sslHandler, int i) {
                int sslPending = ((ReferenceCountedOpenSslEngine) sslHandler.engine).sslPending();
                return sslPending > 0 ? sslPending : i;
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return ((ReferenceCountedOpenSslEngine) sSLEngine).jdkCompatibilityMode;
            }
        },
        CONSCRYPT(true ? 1 : 0, ByteToMessageDecoder.COMPOSITE_CUMULATOR) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType.2
            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            int calculatePendingData(SslHandler sslHandler, int i) {
                return i;
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return true;
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
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

            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2) {
                return byteBufAllocator.directBuffer(((ConscryptAlpnSslEngine) sslHandler.engine).calculateOutNetBufSize(i, i2));
            }
        },
        JDK(0 == true ? 1 : 0, ByteToMessageDecoder.MERGE_CUMULATOR) { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType.3
            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            int calculatePendingData(SslHandler sslHandler, int i) {
                return i;
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            boolean jdkCompatibilityMode(SSLEngine sSLEngine) {
                return true;
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2) throws SSLException {
                int position;
                int writerIndex = byteBuf2.writerIndex();
                ByteBuffer byteBuffer = SslHandler.toByteBuffer(byteBuf, i, i2);
                int position2 = byteBuffer.position();
                SSLEngineResult unwrap = sslHandler.engine.unwrap(byteBuffer, SslHandler.toByteBuffer(byteBuf2, writerIndex, byteBuf2.writableBytes()));
                byteBuf2.writerIndex(writerIndex + unwrap.bytesProduced());
                return (unwrap.bytesConsumed() != 0 || (position = byteBuffer.position() - position2) == unwrap.bytesConsumed()) ? unwrap : new SSLEngineResult(unwrap.getStatus(), unwrap.getHandshakeStatus(), position, unwrap.bytesProduced());
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslEngineType
            ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2) {
                return byteBufAllocator.heapBuffer(sslHandler.engine.getSession().getPacketBufferSize());
            }
        };

        final ByteToMessageDecoder.Cumulator cumulator;
        final boolean wantsDirectBuffer;

        abstract ByteBuf allocateWrapBuffer(SslHandler sslHandler, ByteBufAllocator byteBufAllocator, int i, int i2);

        abstract int calculatePendingData(SslHandler sslHandler, int i);

        abstract boolean jdkCompatibilityMode(SSLEngine sSLEngine);

        abstract SSLEngineResult unwrap(SslHandler sslHandler, ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2) throws SSLException;

        static SslEngineType forEngine(SSLEngine sSLEngine) {
            return sSLEngine instanceof ReferenceCountedOpenSslEngine ? TCNATIVE : sSLEngine instanceof ConscryptAlpnSslEngine ? CONSCRYPT : JDK;
        }

        SslEngineType(boolean z, ByteToMessageDecoder.Cumulator cumulator) {
            this.wantsDirectBuffer = z;
            this.cumulator = cumulator;
        }
    }

    public SslHandler(SSLEngine sSLEngine) {
        this(sSLEngine, false);
    }

    public SslHandler(SSLEngine sSLEngine, boolean z) {
        this(sSLEngine, z, ImmediateExecutor.INSTANCE);
    }

    public SslHandler(SSLEngine sSLEngine, Executor executor) {
        this(sSLEngine, false, executor);
    }

    public SslHandler(SSLEngine sSLEngine, boolean z, Executor executor) {
        this.singleBuffer = new ByteBuffer[1];
        this.handshakePromise = new LazyChannelPromise();
        this.sslClosePromise = new LazyChannelPromise();
        this.handshakeTimeoutMillis = 10000L;
        this.closeNotifyFlushTimeoutMillis = SolicitService.CAMERA_OPEN_TIME_OUT;
        this.wrapDataSize = 16384;
        this.engine = (SSLEngine) ObjectUtil.checkNotNull(sSLEngine, "engine");
        this.delegatedTaskExecutor = (Executor) ObjectUtil.checkNotNull(executor, "delegatedTaskExecutor");
        this.engineType = SslEngineType.forEngine(sSLEngine);
        this.startTls = z;
        this.jdkCompatibilityMode = this.engineType.jdkCompatibilityMode(sSLEngine);
        setCumulator(this.engineType.cumulator);
    }

    public long getHandshakeTimeoutMillis() {
        return this.handshakeTimeoutMillis;
    }

    public void setHandshakeTimeout(long j, TimeUnit timeUnit) {
        ObjectUtil.checkNotNull(timeUnit, "unit");
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
        return closeOutbound();
    }

    @Deprecated
    public ChannelFuture close(ChannelPromise channelPromise) {
        return closeOutbound(channelPromise);
    }

    public ChannelFuture closeOutbound() {
        return closeOutbound(this.ctx.newPromise());
    }

    public ChannelFuture closeOutbound(final ChannelPromise channelPromise) {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext.executor().inEventLoop()) {
            closeOutbound0(channelPromise);
        } else {
            channelHandlerContext.executor().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.1
                @Override // java.lang.Runnable
                public void run() {
                    SslHandler.this.closeOutbound0(channelPromise);
                }
            });
        }
        return channelPromise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closeOutbound0(ChannelPromise channelPromise) {
        this.outboundClosed = true;
        this.engine.closeOutbound();
        try {
            flush(this.ctx, channelPromise);
        } catch (Exception e) {
            if (channelPromise.tryFailure(e)) {
                return;
            }
            logger.warn("{} flush() raised a masked exception.", this.ctx.channel(), e);
        }
    }

    public Future<Channel> sslCloseFuture() {
        return this.sslClosePromise;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    public void handlerRemoved0(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.pendingUnencryptedWrites.isEmpty()) {
            this.pendingUnencryptedWrites.releaseAndFailAll(channelHandlerContext, new ChannelException("Pending write on removal of SslHandler"));
        }
        SSLHandshakeException sSLHandshakeException = null;
        this.pendingUnencryptedWrites = null;
        if (!this.handshakePromise.isDone()) {
            sSLHandshakeException = new SSLHandshakeException("SslHandler removed before handshake completed");
            if (this.handshakePromise.tryFailure(sSLHandshakeException)) {
                channelHandlerContext.fireUserEventTriggered((Object) new SslHandshakeCompletionEvent(sSLHandshakeException));
            }
        }
        if (!this.sslClosePromise.isDone()) {
            if (sSLHandshakeException == null) {
                sSLHandshakeException = new SSLHandshakeException("SslHandler removed before handshake completed");
            }
            notifyClosePromise(sSLHandshakeException);
        }
        Object obj = this.engine;
        if (obj instanceof ReferenceCounted) {
            ((ReferenceCounted) obj).release();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.bind(socketAddress, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.connect(socketAddress, socketAddress2, channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void deregister(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        channelHandlerContext.deregister(channelPromise);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        closeOutboundAndChannel(channelHandlerContext, channelPromise, true);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        closeOutboundAndChannel(channelHandlerContext, channelPromise, false);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void read(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (!this.handshakePromise.isDone()) {
            this.readDuringHandshake = true;
        }
        channelHandlerContext.read();
    }

    private static IllegalStateException newPendingWritesNullException() {
        return new IllegalStateException("pendingUnencryptedWrites is null, handlerRemoved0 called?");
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
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

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundHandler
    public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {
        if (this.startTls && !this.sentFirstMessage) {
            this.sentFirstMessage = true;
            this.pendingUnencryptedWrites.writeAndRemoveAll(channelHandlerContext);
            forceFlush(channelHandlerContext);
            startHandshakeProcessing();
            return;
        }
        if (this.processTask) {
            return;
        }
        try {
            wrapAndFlush(channelHandlerContext);
        } catch (Throwable th) {
            setHandshakeFailure(channelHandlerContext, th);
            PlatformDependent.throwException(th);
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

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(8:36|37|(5:(2:39|(10:41|(3:63|64|(1:66)(2:69|70))|43|44|(1:46)(1:59)|47|48|49|50|51))(1:72)|48|49|50|51)|71|43|44|(0)(0)|47) */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00e7, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x00e8, code lost:
    
        r3 = r4;
        r4 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0071, code lost:
    
        finishWrap(r11, r4, r5, r12, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0079, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x004d, code lost:
    
        r3.release();
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0050, code lost:
    
        r0 = r10.handshakePromise.cause();
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0056, code lost:
    
        if (r0 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0058, code lost:
    
        r0 = r10.sslClosePromise.cause();
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x005e, code lost:
    
        if (r0 != null) goto L27;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0060, code lost:
    
        r0 = new javax.net.ssl.SSLException("SSLEngine closed already");
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x0067, code lost:
    
        r2.tryFailure(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x006a, code lost:
    
        r5 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:87:0x006b, code lost:
    
        r10.pendingUnencryptedWrites.releaseAndFailAll(r11, r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x0070, code lost:
    
        r6 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:0x007a, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x00fe, code lost:
    
        r3 = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0112, code lost:
    
        r4 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0119  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void wrap(ChannelHandlerContext channelHandlerContext, boolean z) throws SSLException {
        ByteBuf byteBuf;
        ChannelPromise channelPromise;
        ByteBuf byteBuf2;
        ChannelPromise channelPromise2;
        ByteBuf removeFirst;
        ChannelPromise channelPromise3;
        ByteBuf byteBuf3;
        ByteBuf byteBuf4;
        ByteBufAllocator alloc = channelHandlerContext.alloc();
        ByteBuf byteBuf5 = null;
        try {
            int i = this.wrapDataSize;
            ByteBuf byteBuf6 = null;
            ChannelPromise channelPromise4 = null;
            while (true) {
                try {
                    if (channelHandlerContext.isRemoved()) {
                        byteBuf2 = byteBuf6;
                        channelPromise2 = channelPromise4;
                        break;
                    }
                    channelPromise4 = channelHandlerContext.newPromise();
                    if (i > 0) {
                        removeFirst = this.pendingUnencryptedWrites.remove(alloc, i, channelPromise4);
                    } else {
                        removeFirst = this.pendingUnencryptedWrites.removeFirst(channelPromise4);
                    }
                    if (removeFirst == null) {
                        channelPromise2 = channelPromise4;
                        byteBuf5 = removeFirst;
                        byteBuf2 = byteBuf6;
                        break;
                    }
                    if (byteBuf6 == null) {
                        try {
                            byteBuf6 = allocateOutNetBuf(channelHandlerContext, removeFirst.readableBytes(), removeFirst.nioBufferCount());
                        } catch (Throwable th) {
                            th = th;
                            channelPromise = channelPromise4;
                            byteBuf5 = removeFirst;
                            byteBuf = byteBuf6;
                            if (byteBuf5 != null) {
                            }
                            finishWrap(channelHandlerContext, byteBuf, channelPromise, z, false);
                            throw th;
                        }
                    }
                    ByteBuf byteBuf7 = byteBuf6;
                    try {
                        SSLEngineResult wrap = wrap(alloc, this.engine, removeFirst, byteBuf7);
                        if (wrap.getStatus() == SSLEngineResult.Status.CLOSED) {
                            break;
                        }
                        if (removeFirst.isReadable()) {
                            this.pendingUnencryptedWrites.addFirst(removeFirst, channelPromise4);
                            channelPromise3 = null;
                        } else {
                            removeFirst.release();
                            channelPromise3 = channelPromise4;
                        }
                        try {
                            int i2 = C66609.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[wrap.getHandshakeStatus().ordinal()];
                            if (i2 == 1) {
                                if (!runDelegatedTasks(z)) {
                                    byteBuf2 = byteBuf7;
                                    channelPromise2 = channelPromise3;
                                    break;
                                } else {
                                    byteBuf6 = byteBuf7;
                                    channelPromise4 = channelPromise3;
                                }
                            } else {
                                try {
                                    if (i2 == 2) {
                                        setHandshakeSuccess();
                                    } else if (i2 != 3) {
                                        if (i2 != 4) {
                                            if (i2 != 5) {
                                                throw new IllegalStateException("Unknown handshake status: " + wrap.getHandshakeStatus());
                                            }
                                            boolean z2 = true;
                                        }
                                        if (byteBuf7.isReadable()) {
                                            byteBuf3 = byteBuf7;
                                            byteBuf4 = null;
                                        } else {
                                            byteBuf4 = byteBuf7;
                                            byteBuf3 = null;
                                        }
                                        finishWrap(channelHandlerContext, byteBuf4, channelPromise3, z, false);
                                        channelPromise4 = null;
                                        byteBuf6 = byteBuf3;
                                    }
                                    finishWrap(channelHandlerContext, byteBuf4, channelPromise3, z, false);
                                    channelPromise4 = null;
                                    byteBuf6 = byteBuf3;
                                } catch (Throwable th2) {
                                    th = th2;
                                    channelPromise = null;
                                    byteBuf = byteBuf3;
                                    if (byteBuf5 != null) {
                                        byteBuf5.release();
                                    }
                                    finishWrap(channelHandlerContext, byteBuf, channelPromise, z, false);
                                    throw th;
                                }
                                setHandshakeSuccessIfStillHandshaking();
                                if (byteBuf7.isReadable()) {
                                }
                            }
                        } catch (Throwable th3) {
                            th = th3;
                            byteBuf = byteBuf7;
                            channelPromise = channelPromise3;
                        }
                    } catch (Throwable th4) {
                        th = th4;
                        byteBuf5 = removeFirst;
                    }
                } catch (Throwable th5) {
                    th = th5;
                    byteBuf = byteBuf6;
                }
            }
            if (byteBuf5 != null) {
                byteBuf5.release();
            }
            finishWrap(channelHandlerContext, byteBuf2, channelPromise2, z, false);
        } catch (Throwable th6) {
            th = th6;
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

    /* JADX INFO: Access modifiers changed from: private */
    public boolean wrapNonAppData(final ChannelHandlerContext channelHandlerContext, boolean z) throws SSLException {
        ByteBufAllocator alloc = channelHandlerContext.alloc();
        ByteBuf byteBuf = null;
        while (!channelHandlerContext.isRemoved()) {
            try {
                if (byteBuf == null) {
                    byteBuf = allocateOutNetBuf(channelHandlerContext, 2048, 1);
                }
                SSLEngineResult wrap = wrap(alloc, this.engine, Unpooled.EMPTY_BUFFER, byteBuf);
                if (wrap.bytesProduced() > 0) {
                    channelHandlerContext.write(byteBuf).addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.2
                        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                        public void operationComplete(ChannelFuture channelFuture) {
                            Throwable cause = channelFuture.cause();
                            if (cause != null) {
                                SslHandler.this.setHandshakeFailureTransportFailure(channelHandlerContext, cause);
                            }
                        }
                    });
                    if (z) {
                        this.needsFlush = true;
                    }
                    byteBuf = null;
                }
                SSLEngineResult.HandshakeStatus handshakeStatus = wrap.getHandshakeStatus();
                int i = C66609.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[handshakeStatus.ordinal()];
                if (i == 1) {
                    if (!runDelegatedTasks(z)) {
                        break;
                    }
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
                if ((wrap.bytesProduced() == 0 && handshakeStatus != SSLEngineResult.HandshakeStatus.NEED_TASK) || (wrap.bytesConsumed() == 0 && wrap.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING)) {
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
                        if (C66609.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[wrap.getStatus().ordinal()] == 1) {
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
                if (C66609.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[wrap.getStatus().ordinal()] == 1) {
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
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler$9 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C66609 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus;
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$Status = new int[SSLEngineResult.Status.values().length];

        static {
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.BUFFER_OVERFLOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$Status[SSLEngineResult.Status.CLOSED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = new int[SSLEngineResult.HandshakeStatus.values().length];
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_TASK.ordinal()] = 1;
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

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {
        boolean z = this.handshakePromise.cause() != null;
        ClosedChannelException closedChannelException = new ClosedChannelException();
        setHandshakeFailure(channelHandlerContext, closedChannelException, !this.outboundClosed, this.handshakeStarted, false);
        notifyClosePromise(closedChannelException);
        try {
            super.channelInactive(channelHandlerContext);
        } catch (DecoderException e) {
            if (!z || !(e.getCause() instanceof SSLException)) {
                throw e;
            }
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
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
                if (!className.startsWith("io.grpc.netty.shaded.io.netty.") && "read".equals(methodName)) {
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
                        if (logger.isDebugEnabled()) {
                            logger.debug("Unexpected exception while loading class {} classname {}", getClass(), className, th2);
                        }
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

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUnwrapThrowable(ChannelHandlerContext channelHandlerContext, Throwable th) {
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

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws SSLException {
        if (this.processTask) {
            return;
        }
        if (this.jdkCompatibilityMode) {
            decodeJdkCompatible(channelHandlerContext, byteBuf);
        } else {
            decodeNonJdkCompatible(channelHandlerContext, byteBuf);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.ByteToMessageDecoder, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {
        channelReadComplete0(channelHandlerContext);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void channelReadComplete0(ChannelHandlerContext channelHandlerContext) {
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

    /* JADX INFO: Access modifiers changed from: private */
    public void unwrapNonAppData(ChannelHandlerContext channelHandlerContext) throws SSLException {
        unwrap(channelHandlerContext, Unpooled.EMPTY_BUFFER, 0, 0);
    }

    /* JADX WARN: Code restructure failed: missing block: B:102:0x009a, code lost:
    
        if (runDelegatedTasks(true) != false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x009c, code lost:
    
        r16 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:105:0x004a, code lost:
    
        r17 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0113, code lost:
    
        throw new java.lang.IllegalStateException("Two consecutive overflows but no content was consumed. " + javax.net.ssl.SSLSession.class.getSimpleName() + " getApplicationBufferSize: " + r18.engine.getSession().getApplicationBufferSize() + " maybe too small.");
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0047, code lost:
    
        if (r5 == 2) goto L12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x004c, code lost:
    
        r5 = io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.C66609.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[r3.ordinal()];
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0054, code lost:
    
        if (r5 == 1) goto L102;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0056, code lost:
    
        if (r5 == 2) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0059, code lost:
    
        if (r5 == 3) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0087, code lost:
    
        if (setHandshakeSuccessIfStillHandshaking() == false) goto L101;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x0089, code lost:
    
        r16 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x008c, code lost:
    
        if (r13 != 0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00a2, code lost:
    
        if (r2 == javax.net.ssl.SSLEngineResult.Status.BUFFER_UNDERFLOW) goto L96;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00a6, code lost:
    
        if (r3 == javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_TASK) goto L107;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00a8, code lost:
    
        if (r1 != 0) goto L108;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00aa, code lost:
    
        if (r4 != 0) goto L109;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00ae, code lost:
    
        if (r3 != javax.net.ssl.SSLEngineResult.HandshakeStatus.NEED_UNWRAP) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00b0, code lost:
    
        readIfNeeded(r19);
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x0014, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x0014, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:86:0x005c, code lost:
    
        if (r5 == 4) goto L25;
     */
    /* JADX WARN: Code restructure failed: missing block: B:88:0x005f, code lost:
    
        if (r5 != 5) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0078, code lost:
    
        throw new java.lang.IllegalStateException("unknown handshake status: " + r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:94:0x007d, code lost:
    
        if (wrapNonAppData(r19, true) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x007f, code lost:
    
        if (r13 != 0) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:99:0x0090, code lost:
    
        setHandshakeSuccess();
        r16 = true;
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
                        int i6 = C66609.$SwitchMap$javax$net$ssl$SSLEngineResult$Status[status.ordinal()];
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
        if (this.flushedBeforeHandshake && this.handshakePromise.isDone()) {
            this.flushedBeforeHandshake = false;
            z = true;
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

    private static boolean inEventLoop(Executor executor) {
        return (executor instanceof EventExecutor) && ((EventExecutor) executor).inEventLoop();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void runAllDelegatedTasks(SSLEngine sSLEngine) {
        while (true) {
            Runnable delegatedTask = sSLEngine.getDelegatedTask();
            if (delegatedTask == null) {
                return;
            } else {
                delegatedTask.run();
            }
        }
    }

    private boolean runDelegatedTasks(boolean z) {
        if (this.delegatedTaskExecutor == ImmediateExecutor.INSTANCE || inEventLoop(this.delegatedTaskExecutor)) {
            runAllDelegatedTasks(this.engine);
            return true;
        }
        executeDelegatedTasks(z);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeDelegatedTasks(boolean z) {
        this.processTask = true;
        try {
            this.delegatedTaskExecutor.execute(new SslTasksRunner(z));
        } catch (RejectedExecutionException e) {
            this.processTask = false;
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class SslTasksRunner implements Runnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final boolean inUnwrap;

        SslTasksRunner(boolean z) {
            this.inUnwrap = z;
        }

        private void taskError(Throwable th) {
            if (this.inUnwrap) {
                try {
                    SslHandler.this.handleUnwrapThrowable(SslHandler.this.ctx, th);
                    return;
                } catch (Throwable th2) {
                    safeExceptionCaught(th2);
                    return;
                }
            }
            SslHandler sslHandler = SslHandler.this;
            sslHandler.setHandshakeFailure(sslHandler.ctx, th);
            SslHandler sslHandler2 = SslHandler.this;
            sslHandler2.forceFlush(sslHandler2.ctx);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void safeExceptionCaught(Throwable th) {
            try {
                SslHandler.this.exceptionCaught(SslHandler.this.ctx, wrapIfNeeded(th));
            } catch (Throwable th2) {
                SslHandler.this.ctx.fireExceptionCaught(th2);
            }
        }

        private Throwable wrapIfNeeded(Throwable th) {
            return (this.inUnwrap && !(th instanceof DecoderException)) ? new DecoderException(th) : th;
        }

        private void tryDecodeAgain() {
            try {
                SslHandler.this.channelRead(SslHandler.this.ctx, Unpooled.EMPTY_BUFFER);
            } finally {
                try {
                } finally {
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void resumeOnEventExecutor() {
            int i;
            SslHandler.this.processTask = false;
            try {
                i = C66609.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SslHandler.this.engine.getHandshakeStatus().ordinal()];
            } catch (Throwable th) {
                safeExceptionCaught(th);
                return;
            }
            if (i != 1) {
                if (i == 2) {
                    SslHandler.this.setHandshakeSuccess();
                } else if (i != 3) {
                    if (i != 4) {
                        if (i == 5) {
                            try {
                                SslHandler.this.unwrapNonAppData(SslHandler.this.ctx);
                                tryDecodeAgain();
                                return;
                            } catch (SSLException e) {
                                SslHandler.this.handleUnwrapThrowable(SslHandler.this.ctx, e);
                                return;
                            }
                        }
                        throw new AssertionError();
                    }
                    try {
                        if (!SslHandler.this.wrapNonAppData(SslHandler.this.ctx, false) && this.inUnwrap) {
                            SslHandler.this.unwrapNonAppData(SslHandler.this.ctx);
                        }
                        SslHandler.this.forceFlush(SslHandler.this.ctx);
                        tryDecodeAgain();
                        return;
                    } catch (Throwable th2) {
                        taskError(th2);
                        return;
                    }
                    safeExceptionCaught(th);
                    return;
                }
                SslHandler.this.setHandshakeSuccessIfStillHandshaking();
                try {
                    SslHandler.this.wrap(SslHandler.this.ctx, this.inUnwrap);
                    if (this.inUnwrap) {
                        SslHandler.this.unwrapNonAppData(SslHandler.this.ctx);
                    }
                    SslHandler.this.forceFlush(SslHandler.this.ctx);
                    tryDecodeAgain();
                    return;
                } catch (Throwable th3) {
                    taskError(th3);
                    return;
                }
            }
            SslHandler.this.executeDelegatedTasks(this.inUnwrap);
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                SslHandler.runAllDelegatedTasks(SslHandler.this.engine);
                SslHandler.this.ctx.executor().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslTasksRunner.1
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
            if (SslHandler.this.ctx.executor().inEventLoop()) {
                SslHandler.this.processTask = false;
                safeExceptionCaught(th);
            } else {
                try {
                    SslHandler.this.ctx.executor().execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.SslTasksRunner.2
                        @Override // java.lang.Runnable
                        public void run() {
                            SslHandler.this.processTask = false;
                            SslTasksRunner.this.safeExceptionCaught(th);
                        }
                    });
                } catch (RejectedExecutionException unused) {
                    SslHandler.this.processTask = false;
                    SslHandler.this.ctx.fireExceptionCaught(th);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean setHandshakeSuccessIfStillHandshaking() {
        if (this.handshakePromise.isDone()) {
            return false;
        }
        setHandshakeSuccess();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHandshakeSuccess() {
        this.handshakePromise.trySuccess(this.ctx.channel());
        if (logger.isDebugEnabled()) {
            SSLSession session = this.engine.getSession();
            logger.debug("{} HANDSHAKEN: protocol:{} cipher suite:{}", this.ctx.channel(), session.getProtocol(), session.getCipherSuite());
        }
        this.ctx.fireUserEventTriggered((Object) SslHandshakeCompletionEvent.SUCCESS);
        if (!this.readDuringHandshake || this.ctx.channel().config().isAutoRead()) {
            return;
        }
        this.readDuringHandshake = false;
        this.ctx.read();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHandshakeFailure(ChannelHandlerContext channelHandlerContext, Throwable th) {
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
                    if (logger.isDebugEnabled() && ((message = e.getMessage()) == null || (!message.contains("possible truncation attack") && !message.contains("closing inbound before receiving peer's close_notify")))) {
                        logger.debug("{} SSLEngine.closeInbound() raised an exception.", channelHandlerContext.channel(), e);
                    }
                }
            }
            if (this.handshakePromise.tryFailure(th) || z3) {
                SslUtils.handleHandshakeFailure(channelHandlerContext, th, z2);
            }
        } finally {
            releaseAndFailAll(channelHandlerContext, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setHandshakeFailureTransportFailure(ChannelHandlerContext channelHandlerContext, Throwable th) {
        try {
            SSLException sSLException = new SSLException("failure when writing TLS control frames", th);
            releaseAndFailAll(channelHandlerContext, sSLException);
            if (this.handshakePromise.tryFailure(sSLException)) {
                channelHandlerContext.fireUserEventTriggered((Object) new SslHandshakeCompletionEvent(sSLException));
            }
        } finally {
            channelHandlerContext.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releaseAndFailAll(ChannelHandlerContext channelHandlerContext, Throwable th) {
        SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
        if (sslHandlerCoalescingBufferQueue != null) {
            sslHandlerCoalescingBufferQueue.releaseAndFailAll(channelHandlerContext, th);
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
    /* JADX WARN: Type inference failed for: r7v1, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
    /* JADX WARN: Type inference failed for: r7v2, types: [io.grpc.netty.shaded.io.netty.channel.ChannelPromise] */
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
                this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.3
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
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
                this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.3
                    @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
                    public void operationComplete(Future<Channel> future) {
                        channelPromise.setSuccess();
                    }
                });
            }
            throw th;
        }
    }

    private void flush(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {
        SslHandlerCoalescingBufferQueue sslHandlerCoalescingBufferQueue = this.pendingUnencryptedWrites;
        if (sslHandlerCoalescingBufferQueue != null) {
            sslHandlerCoalescingBufferQueue.add(Unpooled.EMPTY_BUFFER, channelPromise);
        } else {
            channelPromise.setFailure((Throwable) newPendingWritesNullException());
        }
        flush(channelHandlerContext);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelHandler
    public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
        this.ctx = channelHandlerContext;
        this.pendingUnencryptedWrites = new SslHandlerCoalescingBufferQueue(channelHandlerContext.channel(), 16);
        if (channelHandlerContext.channel().isActive()) {
            startHandshakeProcessing();
        }
    }

    private void startHandshakeProcessing() {
        if (this.handshakeStarted) {
            return;
        }
        this.handshakeStarted = true;
        if (this.engine.getUseClientMode()) {
            handshake();
        }
        applyHandshakeTimeout();
    }

    public Future<Channel> renegotiate() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext == null) {
            throw new IllegalStateException();
        }
        return renegotiate(channelHandlerContext.executor().newPromise());
    }

    public Future<Channel> renegotiate(final Promise<Channel> promise) {
        ObjectUtil.checkNotNull(promise, "promise");
        ChannelHandlerContext channelHandlerContext = this.ctx;
        if (channelHandlerContext == null) {
            throw new IllegalStateException();
        }
        EventExecutor executor = channelHandlerContext.executor();
        if (!executor.inEventLoop()) {
            executor.execute(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.4
                @Override // java.lang.Runnable
                public void run() {
                    SslHandler.this.renegotiateOnEventLoop(promise);
                }
            });
            return promise;
        }
        renegotiateOnEventLoop(promise);
        return promise;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renegotiateOnEventLoop(Promise<Channel> promise) {
        Promise<Channel> promise2 = this.handshakePromise;
        if (!promise2.isDone()) {
            promise2.addListener((GenericFutureListener<? extends Future<? super Channel>>) new PromiseNotifier(promise));
            return;
        }
        this.handshakePromise = promise;
        handshake();
        applyHandshakeTimeout();
    }

    private void handshake() {
        if (this.engine.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING && !this.handshakePromise.isDone()) {
            ChannelHandlerContext channelHandlerContext = this.ctx;
            try {
                this.engine.beginHandshake();
                wrapNonAppData(channelHandlerContext, false);
            } finally {
                try {
                } finally {
                }
            }
        }
    }

    private void applyHandshakeTimeout() {
        final Promise<Channel> promise = this.handshakePromise;
        final long j = this.handshakeTimeoutMillis;
        if (j <= 0 || promise.isDone()) {
            return;
        }
        final ScheduledFuture<?> schedule = this.ctx.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.5
            @Override // java.lang.Runnable
            public void run() {
                if (promise.isDone()) {
                    return;
                }
                SslHandshakeTimeoutException sslHandshakeTimeoutException = new SslHandshakeTimeoutException("handshake timed out after " + j + "ms");
                try {
                    if (promise.tryFailure(sslHandshakeTimeoutException)) {
                        SslUtils.handleHandshakeFailure(SslHandler.this.ctx, sslHandshakeTimeoutException, true);
                    }
                } finally {
                    SslHandler sslHandler = SslHandler.this;
                    sslHandler.releaseAndFailAll(sslHandler.ctx, sslHandshakeTimeoutException);
                }
            }
        }, j, TimeUnit.MILLISECONDS);
        promise.addListener((GenericFutureListener<? extends Future<? super Channel>>) new FutureListener<Channel>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.6
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(Future<Channel> future) throws Exception {
                schedule.cancel(false);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forceFlush(ChannelHandlerContext channelHandlerContext) {
        this.needsFlush = false;
        channelHandlerContext.flush();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandlerAdapter, io.grpc.netty.shaded.io.netty.channel.ChannelInboundHandler
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
                scheduledFuture = channelHandlerContext.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.7
                    @Override // java.lang.Runnable
                    public void run() {
                        if (channelFuture.isDone()) {
                            return;
                        }
                        SslHandler.logger.warn("{} Last write attempt timed out; force-closing the connection.", channelHandlerContext.channel());
                        ChannelHandlerContext channelHandlerContext2 = channelHandlerContext;
                        SslHandler.addCloseListener(channelHandlerContext2.close(channelHandlerContext2.newPromise()), channelPromise);
                    }
                }, j, TimeUnit.MILLISECONDS);
            }
        }
        channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.8
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
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
                    final ScheduledFuture<?> schedule = !SslHandler.this.sslClosePromise.isDone() ? channelHandlerContext.executor().schedule(new Runnable() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.8.1
                        @Override // java.lang.Runnable
                        public void run() {
                            if (SslHandler.this.sslClosePromise.isDone()) {
                                return;
                            }
                            SslHandler.logger.debug("{} did not receive close_notify in {}ms; force-closing the connection.", channelHandlerContext.channel(), Long.valueOf(j2));
                            SslHandler.addCloseListener(channelHandlerContext.close(channelHandlerContext.newPromise()), channelPromise);
                        }
                    }, j2, TimeUnit.MILLISECONDS) : null;
                    SslHandler.this.sslClosePromise.addListener((GenericFutureListener) new FutureListener<Channel>() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.SslHandler.8.2
                        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
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
        return this.engineType.allocateWrapBuffer(this, channelHandlerContext.alloc(), i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class SslHandlerCoalescingBufferQueue extends AbstractCoalescingBufferQueue {
        @Override // io.grpc.netty.shaded.io.netty.channel.AbstractCoalescingBufferQueue
        protected ByteBuf removeEmptyValue() {
            return null;
        }

        SslHandlerCoalescingBufferQueue(Channel channel, int i) {
            super(channel, i);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.AbstractCoalescingBufferQueue
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

        @Override // io.grpc.netty.shaded.io.netty.channel.AbstractCoalescingBufferQueue
        protected ByteBuf composeFirst(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf) {
            ByteBuf heapBuffer;
            if (!(byteBuf instanceof CompositeByteBuf)) {
                return byteBuf;
            }
            CompositeByteBuf compositeByteBuf = (CompositeByteBuf) byteBuf;
            if (SslHandler.this.engineType.wantsDirectBuffer) {
                heapBuffer = byteBufAllocator.directBuffer(compositeByteBuf.readableBytes());
            } else {
                heapBuffer = byteBufAllocator.heapBuffer(compositeByteBuf.readableBytes());
            }
            try {
                heapBuffer.writeBytes(compositeByteBuf);
            } catch (Throwable th) {
                heapBuffer.release();
                PlatformDependent.throwException(th);
            }
            compositeByteBuf.release();
            return heapBuffer;
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
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class LazyChannelPromise extends DefaultPromise<Channel> {
        private LazyChannelPromise() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise
        public EventExecutor executor() {
            if (SslHandler.this.ctx != null) {
                return SslHandler.this.ctx.executor();
            }
            throw new IllegalStateException();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.netty.shaded.io.netty.util.concurrent.DefaultPromise
        public void checkDeadLock() {
            if (SslHandler.this.ctx == null) {
                return;
            }
            super.checkDeadLock();
        }
    }
}
