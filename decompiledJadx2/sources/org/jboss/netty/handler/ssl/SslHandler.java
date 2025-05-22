package org.jboss.netty.handler.ssl;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import kotlin.UShort;
import kotlin.jvm.internal.ShortCompanionObject;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelState;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.DefaultChannelFuture;
import org.jboss.netty.channel.DownstreamMessageEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.internal.DetectionUtil;
import org.jboss.netty.util.internal.NonReentrantLock;
import org.jboss.netty.util.internal.QueueFactory;

/* loaded from: classes7.dex */
public class SslHandler extends FrameDecoder implements ChannelDownstreamHandler {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static SslBufferPool defaultBufferPool;
    private final SslBufferPool bufferPool;
    private volatile ChannelHandlerContext ctx;
    private final Executor delegatedTaskExecutor;
    private volatile boolean enableRenegotiation;
    private final SSLEngine engine;
    private volatile ChannelFuture handshakeFuture;
    final Object handshakeLock;
    private volatile boolean handshaken;
    private boolean handshaking;
    int ignoreClosedChannelException;
    final Object ignoreClosedChannelExceptionLock;
    private volatile boolean issueHandshake;
    private int packetLength;
    private final Queue<MessageEvent> pendingEncryptedWrites;
    private final NonReentrantLock pendingEncryptedWritesLock;
    private final Queue<PendingWrite> pendingUnencryptedWrites;
    private final AtomicBoolean sentCloseNotify;
    private final AtomicBoolean sentFirstMessage;
    private final SSLEngineInboundCloseFuture sslEngineCloseFuture;
    private final boolean startTls;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SslHandler.class);
    private static final ByteBuffer EMPTY_BUFFER = ByteBuffer.allocate(0);
    private static final Pattern IGNORABLE_CLASS_IN_STACK = Pattern.compile("^.*(Socket|DatagramChannel|SctpChannel).*$");
    private static final Pattern IGNORABLE_ERROR_MESSAGE = Pattern.compile("^.*(?:connection.*reset|connection.*closed|broken.*pipe).*$", 2);

    public static synchronized SslBufferPool getDefaultBufferPool() {
        SslBufferPool sslBufferPool;
        synchronized (SslHandler.class) {
            if (defaultBufferPool == null) {
                defaultBufferPool = new SslBufferPool();
            }
            sslBufferPool = defaultBufferPool;
        }
        return sslBufferPool;
    }

    public SslHandler(SSLEngine sSLEngine) {
        this(sSLEngine, getDefaultBufferPool(), ImmediateExecutor.INSTANCE);
    }

    public SslHandler(SSLEngine sSLEngine, SslBufferPool sslBufferPool) {
        this(sSLEngine, sslBufferPool, ImmediateExecutor.INSTANCE);
    }

    public SslHandler(SSLEngine sSLEngine, boolean z) {
        this(sSLEngine, getDefaultBufferPool(), z);
    }

    public SslHandler(SSLEngine sSLEngine, SslBufferPool sslBufferPool, boolean z) {
        this(sSLEngine, sslBufferPool, z, ImmediateExecutor.INSTANCE);
    }

    public SslHandler(SSLEngine sSLEngine, Executor executor) {
        this(sSLEngine, getDefaultBufferPool(), executor);
    }

    public SslHandler(SSLEngine sSLEngine, SslBufferPool sslBufferPool, Executor executor) {
        this(sSLEngine, sslBufferPool, false, executor);
    }

    public SslHandler(SSLEngine sSLEngine, boolean z, Executor executor) {
        this(sSLEngine, getDefaultBufferPool(), z, executor);
    }

    public SslHandler(SSLEngine sSLEngine, SslBufferPool sslBufferPool, boolean z, Executor executor) {
        this.enableRenegotiation = true;
        this.handshakeLock = new Object();
        this.sentFirstMessage = new AtomicBoolean();
        this.sentCloseNotify = new AtomicBoolean();
        this.ignoreClosedChannelExceptionLock = new Object();
        this.pendingUnencryptedWrites = new LinkedList();
        this.pendingEncryptedWrites = QueueFactory.createQueue(MessageEvent.class);
        this.pendingEncryptedWritesLock = new NonReentrantLock();
        this.sslEngineCloseFuture = new SSLEngineInboundCloseFuture();
        this.packetLength = -1;
        if (sSLEngine == null) {
            throw new NullPointerException("engine");
        }
        if (sslBufferPool == null) {
            throw new NullPointerException("bufferPool");
        }
        if (executor == null) {
            throw new NullPointerException("delegatedTaskExecutor");
        }
        this.engine = sSLEngine;
        this.bufferPool = sslBufferPool;
        this.delegatedTaskExecutor = executor;
        this.startTls = z;
    }

    public SSLEngine getEngine() {
        return this.engine;
    }

    public ChannelFuture handshake() {
        ChannelFuture failedFuture;
        if (this.handshaken && !isEnableRenegotiation()) {
            throw new IllegalStateException("renegotiation disabled");
        }
        ChannelHandlerContext channelHandlerContext = this.ctx;
        Channel channel = channelHandlerContext.getChannel();
        Exception e = null;
        synchronized (this.handshakeLock) {
            if (this.handshaking) {
                return this.handshakeFuture;
            }
            this.handshaking = true;
            try {
                this.engine.beginHandshake();
                runDelegatedTasks();
                failedFuture = Channels.future(channel);
                this.handshakeFuture = failedFuture;
            } catch (Exception e2) {
                e = e2;
                failedFuture = Channels.failedFuture(channel, e);
                this.handshakeFuture = failedFuture;
            }
            if (e == null) {
                try {
                    wrapNonAppData(channelHandlerContext, channel);
                } catch (SSLException e3) {
                    failedFuture.setFailure(e3);
                    Channels.fireExceptionCaught(channelHandlerContext, e3);
                }
            } else {
                Channels.fireExceptionCaught(channelHandlerContext, e);
            }
            return failedFuture;
        }
    }

    @Deprecated
    public ChannelFuture handshake(Channel channel) {
        return handshake();
    }

    public ChannelFuture close() {
        ChannelHandlerContext channelHandlerContext = this.ctx;
        Channel channel = channelHandlerContext.getChannel();
        try {
            this.engine.closeOutbound();
            return wrapNonAppData(channelHandlerContext, channel);
        } catch (SSLException e) {
            Channels.fireExceptionCaught(channelHandlerContext, e);
            return Channels.failedFuture(channel, e);
        }
    }

    @Deprecated
    public ChannelFuture close(Channel channel) {
        return close();
    }

    public boolean isEnableRenegotiation() {
        return this.enableRenegotiation;
    }

    public void setEnableRenegotiation(boolean z) {
        this.enableRenegotiation = z;
    }

    public void setIssueHandshake(boolean z) {
        this.issueHandshake = z;
    }

    public boolean isIssueHandshake() {
        return this.issueHandshake;
    }

    public ChannelFuture getSSLEngineInboundCloseFuture() {
        return this.sslEngineCloseFuture;
    }

    @Override // org.jboss.netty.channel.ChannelDownstreamHandler
    public void handleDownstream(ChannelHandlerContext channelHandlerContext, ChannelEvent channelEvent) throws Exception {
        PendingWrite pendingWrite;
        if (channelEvent instanceof ChannelStateEvent) {
            ChannelStateEvent channelStateEvent = (ChannelStateEvent) channelEvent;
            int i = C87294.$SwitchMap$org$jboss$netty$channel$ChannelState[channelStateEvent.getState().ordinal()];
            if ((i == 1 || i == 2 || i == 3) && (Boolean.FALSE.equals(channelStateEvent.getValue()) || channelStateEvent.getValue() == null)) {
                closeOutboundAndChannel(channelHandlerContext, channelStateEvent);
                return;
            }
        }
        if (!(channelEvent instanceof MessageEvent)) {
            channelHandlerContext.sendDownstream(channelEvent);
            return;
        }
        MessageEvent messageEvent = (MessageEvent) channelEvent;
        if (!(messageEvent.getMessage() instanceof ChannelBuffer)) {
            channelHandlerContext.sendDownstream(channelEvent);
            return;
        }
        if (this.startTls && this.sentFirstMessage.compareAndSet(false, true)) {
            channelHandlerContext.sendDownstream(channelEvent);
            return;
        }
        ChannelBuffer channelBuffer = (ChannelBuffer) messageEvent.getMessage();
        if (channelBuffer.readable()) {
            pendingWrite = new PendingWrite(channelEvent.getFuture(), channelBuffer.toByteBuffer(channelBuffer.readerIndex(), channelBuffer.readableBytes()));
        } else {
            pendingWrite = new PendingWrite(channelEvent.getFuture(), null);
        }
        synchronized (this.pendingUnencryptedWrites) {
            this.pendingUnencryptedWrites.offer(pendingWrite);
        }
        wrap(channelHandlerContext, channelEvent.getChannel());
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder, org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void channelDisconnected(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        synchronized (this.handshakeLock) {
            if (this.handshaking) {
                this.handshakeFuture.setFailure(new ClosedChannelException());
            }
        }
        try {
            super.channelDisconnected(channelHandlerContext, channelStateEvent);
            unwrap(channelHandlerContext, channelStateEvent.getChannel(), ChannelBuffers.EMPTY_BUFFER, 0, 0);
            this.engine.closeOutbound();
            if (this.sentCloseNotify.get() || !this.handshaken) {
                return;
            }
            try {
                this.engine.closeInbound();
            } catch (SSLException e) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Failed to clean up SSLEngine.", e);
                }
            }
        } catch (Throwable th) {
            unwrap(channelHandlerContext, channelStateEvent.getChannel(), ChannelBuffers.EMPTY_BUFFER, 0, 0);
            this.engine.closeOutbound();
            if (!this.sentCloseNotify.get() && this.handshaken) {
                try {
                    this.engine.closeInbound();
                } catch (SSLException e2) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Failed to clean up SSLEngine.", e2);
                    }
                }
            }
            throw th;
        }
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder, org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, ExceptionEvent exceptionEvent) throws Exception {
        Throwable cause = exceptionEvent.getCause();
        if (cause instanceof IOException) {
            if (cause instanceof ClosedChannelException) {
                synchronized (this.ignoreClosedChannelExceptionLock) {
                    if (this.ignoreClosedChannelException > 0) {
                        this.ignoreClosedChannelException--;
                        if (logger.isDebugEnabled()) {
                            logger.debug("Swallowing an exception raised while writing non-app data", cause);
                        }
                        return;
                    }
                }
            } else if (ignoreException(cause)) {
                return;
            }
        }
        channelHandlerContext.sendUpstream(exceptionEvent);
    }

    private boolean ignoreException(Throwable th) {
        if (!(th instanceof SSLException) && (th instanceof IOException) && this.engine.isOutboundDone()) {
            if (IGNORABLE_ERROR_MESSAGE.matcher(String.valueOf(th.getMessage()).toLowerCase()).matches()) {
                return true;
            }
            for (StackTraceElement stackTraceElement : th.getStackTrace()) {
                String className = stackTraceElement.getClassName();
                String methodName = stackTraceElement.getMethodName();
                if (!className.startsWith("org.jboss.netty.") && methodName.equals("read")) {
                    if (IGNORABLE_CLASS_IN_STACK.matcher(className).matches()) {
                        return true;
                    }
                    try {
                        Class<?> loadClass = getClass().getClassLoader().loadClass(className);
                        if (!SocketChannel.class.isAssignableFrom(loadClass)) {
                            if (!DatagramChannel.class.isAssignableFrom(loadClass)) {
                                if (DetectionUtil.javaVersion() >= 7 && "com.sun.nio.sctp.SctpChannel".equals(loadClass.getSuperclass().getName())) {
                                }
                            }
                        }
                        return true;
                    } catch (ClassNotFoundException unused) {
                        continue;
                    }
                }
            }
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003e, code lost:
    
        if (r10.packetLength <= 5) goto L17;
     */
    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    protected Object decode(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer) throws Exception {
        boolean z;
        if (this.packetLength == -1) {
            if (channelBuffer.readableBytes() < 5) {
                return null;
            }
            boolean z2 = false;
            switch (channelBuffer.getUnsignedByte(channelBuffer.readerIndex())) {
                case 20:
                case 21:
                case 22:
                case 23:
                    z = true;
                    break;
                default:
                    z = false;
                    break;
            }
            if (z) {
                if (channelBuffer.getUnsignedByte(channelBuffer.readerIndex() + 1) == 3) {
                    this.packetLength = (getShort(channelBuffer, channelBuffer.readerIndex() + 3) & UShort.MAX_VALUE) + 5;
                }
                z = false;
            }
            if (!z) {
                int i = (channelBuffer.getUnsignedByte(channelBuffer.readerIndex()) & 128) != 0 ? 2 : 3;
                short unsignedByte = channelBuffer.getUnsignedByte(channelBuffer.readerIndex() + i + 1);
                if (unsignedByte == 2 || unsignedByte == 3) {
                    if (i == 2) {
                        this.packetLength = (getShort(channelBuffer, channelBuffer.readerIndex()) & ShortCompanionObject.MAX_VALUE) + 2;
                    } else {
                        this.packetLength = (getShort(channelBuffer, channelBuffer.readerIndex()) & 16383) + 3;
                    }
                    if (this.packetLength > i) {
                        z2 = true;
                    }
                }
                if (!z2) {
                    NotSslRecordException notSslRecordException = new NotSslRecordException("not an SSL/TLS record: " + ChannelBuffers.hexDump(channelBuffer));
                    channelBuffer.skipBytes(channelBuffer.readableBytes());
                    throw notSslRecordException;
                }
            }
        }
        if (channelBuffer.readableBytes() < this.packetLength) {
            return null;
        }
        int readerIndex = channelBuffer.readerIndex();
        channelBuffer.skipBytes(this.packetLength);
        try {
            return unwrap(channelHandlerContext, channel, channelBuffer, readerIndex, this.packetLength);
        } finally {
            this.packetLength = -1;
        }
    }

    private static short getShort(ChannelBuffer channelBuffer, int i) {
        return (short) ((channelBuffer.getByte(i + 1) & 255) | (channelBuffer.getByte(i) << 8));
    }

    /* JADX WARN: Code restructure failed: missing block: B:84:0x00eb, code lost:
    
        r1 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:91:0x0099, code lost:
    
        r4 = false;
     */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x015e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ChannelFuture wrap(ChannelHandlerContext channelHandlerContext, Channel channel) throws SSLException {
        boolean z;
        PendingWrite poll;
        PendingWrite poll2;
        SSLEngineResult wrap;
        ByteBuffer acquireBuffer = this.bufferPool.acquireBuffer();
        boolean z2 = false;
        ChannelFuture channelFuture = null;
        boolean z3 = false;
        while (true) {
            try {
                synchronized (this.pendingUnencryptedWrites) {
                    try {
                        PendingWrite peek = this.pendingUnencryptedWrites.peek();
                        if (peek != null) {
                            ByteBuffer byteBuffer = peek.outAppBuf;
                            if (byteBuffer == null) {
                                this.pendingUnencryptedWrites.remove();
                                offerEncryptedWriteRequest(new DownstreamMessageEvent(channel, peek.future, ChannelBuffers.EMPTY_BUFFER, channel.getRemoteAddress()));
                                z3 = true;
                            } else {
                                try {
                                    synchronized (this.handshakeLock) {
                                        wrap = this.engine.wrap(byteBuffer, acquireBuffer);
                                    }
                                    if (wrap.bytesProduced() > 0) {
                                        acquireBuffer.flip();
                                        ChannelBuffer buffer = this.ctx.getChannel().getConfig().getBufferFactory().getBuffer(acquireBuffer.remaining());
                                        buffer.writeBytes(acquireBuffer);
                                        acquireBuffer.clear();
                                        ChannelFuture succeededFuture = peek.outAppBuf.hasRemaining() ? Channels.succeededFuture(channel) : peek.future;
                                        offerEncryptedWriteRequest(new DownstreamMessageEvent(channel, succeededFuture, buffer, channel.getRemoteAddress()));
                                        z3 = true;
                                        channelFuture = succeededFuture;
                                    } else {
                                        if (wrap.getStatus() == SSLEngineResult.Status.CLOSED) {
                                            try {
                                                break;
                                            } catch (Throwable th) {
                                                th = th;
                                                z = false;
                                                throw th;
                                            }
                                        }
                                        SSLEngineResult.HandshakeStatus handshakeStatus = wrap.getHandshakeStatus();
                                        handleRenegotiation(handshakeStatus);
                                        int i = C87294.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[handshakeStatus.ordinal()];
                                        if (i != 1) {
                                            if (i != 2) {
                                                if (i != 3) {
                                                    if (i != 4 && i != 5) {
                                                        throw new IllegalStateException("Unknown handshake status: " + handshakeStatus);
                                                    }
                                                    if (handshakeStatus == SSLEngineResult.HandshakeStatus.FINISHED) {
                                                        setHandshakeSuccess(channel);
                                                    }
                                                    z = wrap.getStatus() != SSLEngineResult.Status.CLOSED;
                                                } else {
                                                    runDelegatedTasks();
                                                }
                                            }
                                        } else if (!byteBuffer.hasRemaining()) {
                                        }
                                    }
                                } finally {
                                    if (!byteBuffer.hasRemaining()) {
                                        this.pendingUnencryptedWrites.remove();
                                    }
                                }
                            }
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
            } catch (SSLException e) {
                try {
                    setHandshakeFailure(channel, e);
                    throw e;
                } catch (Throwable th3) {
                    th = th3;
                    z = false;
                    this.bufferPool.releaseBuffer(acquireBuffer);
                    if (z3) {
                        flushPendingEncryptedWrites(channelHandlerContext);
                    }
                    if (!z) {
                        IllegalStateException illegalStateException = new IllegalStateException("SSLEngine already closed");
                        while (true) {
                            synchronized (this.pendingUnencryptedWrites) {
                                poll = this.pendingUnencryptedWrites.poll();
                                if (poll == null) {
                                    break;
                                }
                            }
                            poll.future.setFailure(illegalStateException);
                        }
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
                this.bufferPool.releaseBuffer(acquireBuffer);
                if (z3) {
                }
                if (!z) {
                }
                throw th;
            }
        }
        this.bufferPool.releaseBuffer(acquireBuffer);
        if (z3) {
            flushPendingEncryptedWrites(channelHandlerContext);
        }
        if (!z) {
            IllegalStateException illegalStateException2 = new IllegalStateException("SSLEngine already closed");
            while (true) {
                synchronized (this.pendingUnencryptedWrites) {
                    poll2 = this.pendingUnencryptedWrites.poll();
                    if (poll2 == null) {
                        break;
                    }
                }
                poll2.future.setFailure(illegalStateException2);
            }
        }
        if (z2) {
            unwrap(channelHandlerContext, channel, ChannelBuffers.EMPTY_BUFFER, 0, 0);
        }
        return channelFuture == null ? Channels.succeededFuture(channel) : channelFuture;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: org.jboss.netty.handler.ssl.SslHandler$4 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C87294 {
        static final /* synthetic */ int[] $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = new int[SSLEngineResult.HandshakeStatus.values().length];
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$channel$ChannelState;

        static {
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_WRAP.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_UNWRAP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NEED_TASK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.FINISHED.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $SwitchMap$org$jboss$netty$channel$ChannelState = new int[ChannelState.values().length];
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.OPEN.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.CONNECTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$org$jboss$netty$channel$ChannelState[ChannelState.BOUND.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }

    private void offerEncryptedWriteRequest(MessageEvent messageEvent) {
        boolean tryLock = this.pendingEncryptedWritesLock.tryLock();
        try {
            this.pendingEncryptedWrites.offer(messageEvent);
        } finally {
            if (tryLock) {
                this.pendingEncryptedWritesLock.unlock();
            }
        }
    }

    private void flushPendingEncryptedWrites(ChannelHandlerContext channelHandlerContext) {
        if (!this.pendingEncryptedWritesLock.tryLock()) {
            return;
        }
        while (true) {
            try {
                MessageEvent poll = this.pendingEncryptedWrites.poll();
                if (poll == null) {
                    return;
                } else {
                    channelHandlerContext.sendDownstream(poll);
                }
            } finally {
                this.pendingEncryptedWritesLock.unlock();
            }
        }
    }

    private ChannelFuture wrapNonAppData(ChannelHandlerContext channelHandlerContext, Channel channel) throws SSLException {
        SSLEngineResult wrap;
        ByteBuffer acquireBuffer = this.bufferPool.acquireBuffer();
        ChannelFuture channelFuture = null;
        do {
            try {
                try {
                    synchronized (this.handshakeLock) {
                        wrap = this.engine.wrap(EMPTY_BUFFER, acquireBuffer);
                    }
                    if (wrap.bytesProduced() > 0) {
                        acquireBuffer.flip();
                        ChannelBuffer buffer = channelHandlerContext.getChannel().getConfig().getBufferFactory().getBuffer(acquireBuffer.remaining());
                        buffer.writeBytes(acquireBuffer);
                        acquireBuffer.clear();
                        ChannelFuture future = Channels.future(channel);
                        future.addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.ssl.SslHandler.1
                            @Override // org.jboss.netty.channel.ChannelFutureListener
                            public void operationComplete(ChannelFuture channelFuture2) throws Exception {
                                if (channelFuture2.getCause() instanceof ClosedChannelException) {
                                    synchronized (SslHandler.this.ignoreClosedChannelExceptionLock) {
                                        SslHandler.this.ignoreClosedChannelException++;
                                    }
                                }
                            }
                        });
                        Channels.write(channelHandlerContext, future, buffer);
                        channelFuture = future;
                    }
                    SSLEngineResult.HandshakeStatus handshakeStatus = wrap.getHandshakeStatus();
                    handleRenegotiation(handshakeStatus);
                    int i = C87294.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[handshakeStatus.ordinal()];
                    if (i != 1) {
                        if (i != 2) {
                            if (i == 3) {
                                runDelegatedTasks();
                            } else if (i == 4) {
                                setHandshakeSuccess(channel);
                                runDelegatedTasks();
                            } else if (i != 5) {
                                throw new IllegalStateException("Unexpected handshake status: " + handshakeStatus);
                            }
                        } else if (!Thread.holdsLock(this.handshakeLock)) {
                            unwrap(channelHandlerContext, channel, ChannelBuffers.EMPTY_BUFFER, 0, 0);
                        }
                    }
                } catch (SSLException e) {
                    setHandshakeFailure(channel, e);
                    throw e;
                }
            } finally {
                this.bufferPool.releaseBuffer(acquireBuffer);
            }
        } while (wrap.bytesProduced() != 0);
        return channelFuture == null ? Channels.succeededFuture(channel) : channelFuture;
    }

    /* JADX WARN: Code restructure failed: missing block: B:45:0x009d, code lost:
    
        r2 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x009e, code lost:
    
        if (r2 == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00a6, code lost:
    
        if (java.lang.Thread.holdsLock(r4.handshakeLock) != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00ae, code lost:
    
        if (r4.pendingEncryptedWritesLock.isHeldByCurrentThread() != false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00b0, code lost:
    
        wrap(r5, r6);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00b3, code lost:
    
        r8.flip();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00ba, code lost:
    
        if (r8.hasRemaining() == false) goto L59;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00bc, code lost:
    
        r5 = r5.getChannel().getConfig().getBufferFactory().getBuffer(r8.remaining());
        r5.writeBytes(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00d8, code lost:
    
        return r5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00d9, code lost:
    
        r6 = r4.bufferPool;
        r6.releaseBuffer(r8);
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00df, code lost:
    
        return null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private ChannelBuffer unwrap(ChannelHandlerContext channelHandlerContext, Channel channel, ChannelBuffer channelBuffer, int i, int i2) throws SSLException {
        boolean z;
        SSLEngineResult unwrap;
        ByteBuffer byteBuffer = channelBuffer.toByteBuffer(i, i2);
        ByteBuffer acquireBuffer = this.bufferPool.acquireBuffer();
        while (true) {
            try {
                try {
                    synchronized (this.handshakeLock) {
                        boolean z2 = true;
                        z = (this.handshaken || this.handshaking || this.engine.getUseClientMode() || this.engine.isInboundDone() || this.engine.isOutboundDone()) ? false : true;
                    }
                    if (z) {
                        handshake();
                    }
                    synchronized (this.handshakeLock) {
                        unwrap = this.engine.unwrap(byteBuffer, acquireBuffer);
                    }
                    if (unwrap.getStatus() == SSLEngineResult.Status.CLOSED) {
                        this.sslEngineCloseFuture.setClosed();
                    }
                    SSLEngineResult.HandshakeStatus handshakeStatus = unwrap.getHandshakeStatus();
                    handleRenegotiation(handshakeStatus);
                    int i3 = C87294.$SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[handshakeStatus.ordinal()];
                    if (i3 == 1) {
                        wrapNonAppData(channelHandlerContext, channel);
                    } else if (i3 != 2) {
                        if (i3 == 3) {
                            runDelegatedTasks();
                        } else if (i3 == 4) {
                            setHandshakeSuccess(channel);
                        } else if (i3 != 5) {
                            throw new IllegalStateException("Unknown handshake status: " + handshakeStatus);
                        }
                    } else if (!byteBuffer.hasRemaining() || this.engine.isInboundDone()) {
                        break;
                    }
                } catch (SSLException e) {
                    setHandshakeFailure(channel, e);
                    throw e;
                }
            } finally {
                this.bufferPool.releaseBuffer(acquireBuffer);
            }
        }
    }

    private void handleRenegotiation(SSLEngineResult.HandshakeStatus handshakeStatus) {
        boolean z;
        if (handshakeStatus == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING || handshakeStatus == SSLEngineResult.HandshakeStatus.FINISHED || !this.handshaken) {
            return;
        }
        synchronized (this.handshakeLock) {
            if (this.handshaking) {
                return;
            }
            if (!this.engine.isInboundDone() && !this.engine.isOutboundDone()) {
                if (isEnableRenegotiation()) {
                    z = true;
                } else {
                    z = false;
                    this.handshaking = true;
                }
                if (z) {
                    handshake();
                } else {
                    Channels.fireExceptionCaught(this.ctx, new SSLException("renegotiation attempted by peer; closing the connection"));
                    Channels.close(this.ctx, Channels.succeededFuture(this.ctx.getChannel()));
                }
            }
        }
    }

    private void runDelegatedTasks() {
        final Runnable delegatedTask;
        while (true) {
            synchronized (this.handshakeLock) {
                delegatedTask = this.engine.getDelegatedTask();
            }
            if (delegatedTask == null) {
                return;
            } else {
                this.delegatedTaskExecutor.execute(new Runnable() { // from class: org.jboss.netty.handler.ssl.SslHandler.2
                    @Override // java.lang.Runnable
                    public void run() {
                        synchronized (SslHandler.this.handshakeLock) {
                            delegatedTask.run();
                        }
                    }
                });
            }
        }
    }

    private void setHandshakeSuccess(Channel channel) {
        synchronized (this.handshakeLock) {
            this.handshaking = false;
            this.handshaken = true;
            if (this.handshakeFuture == null) {
                this.handshakeFuture = Channels.future(channel);
            }
        }
        this.handshakeFuture.setSuccess();
    }

    private void setHandshakeFailure(Channel channel, SSLException sSLException) {
        synchronized (this.handshakeLock) {
            if (this.handshaking) {
                this.handshaking = false;
                this.handshaken = false;
                if (this.handshakeFuture == null) {
                    this.handshakeFuture = Channels.future(channel);
                }
                this.engine.closeOutbound();
                try {
                    this.engine.closeInbound();
                } catch (SSLException e) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("SSLEngine.closeInbound() raised an exception after a handshake failure.", e);
                    }
                }
                this.handshakeFuture.setFailure(sSLException);
            }
        }
    }

    private void closeOutboundAndChannel(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) {
        if (channelStateEvent.getChannel().isConnected()) {
            try {
                try {
                    unwrap(channelHandlerContext, channelStateEvent.getChannel(), ChannelBuffers.EMPTY_BUFFER, 0, 0);
                } catch (SSLException e) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("Failed to unwrap before sending a close_notify message", e);
                    }
                }
                boolean z = true;
                if (!this.engine.isInboundDone()) {
                    if (this.sentCloseNotify.compareAndSet(false, true)) {
                        this.engine.closeOutbound();
                        try {
                            wrapNonAppData(channelHandlerContext, channelStateEvent.getChannel()).addListener(new ClosingChannelFutureListener(channelHandlerContext, channelStateEvent));
                        } catch (SSLException e2) {
                            if (logger.isDebugEnabled()) {
                                logger.debug("Failed to encode a close_notify message", e2);
                            }
                        }
                    }
                    z = false;
                }
                if (z) {
                }
            } finally {
                channelHandlerContext.sendDownstream(channelStateEvent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class PendingWrite {
        final ChannelFuture future;
        final ByteBuffer outAppBuf;

        PendingWrite(ChannelFuture channelFuture, ByteBuffer byteBuffer) {
            this.future = channelFuture;
            this.outAppBuf = byteBuffer;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class ClosingChannelFutureListener implements ChannelFutureListener {
        private final ChannelHandlerContext context;

        /* renamed from: e */
        private final ChannelStateEvent f10047e;

        ClosingChannelFutureListener(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) {
            this.context = channelHandlerContext;
            this.f10047e = channelStateEvent;
        }

        @Override // org.jboss.netty.channel.ChannelFutureListener
        public void operationComplete(ChannelFuture channelFuture) throws Exception {
            if (!(channelFuture.getCause() instanceof ClosedChannelException)) {
                Channels.close(this.context, this.f10047e.getFuture());
            } else {
                this.f10047e.getFuture().setSuccess();
            }
        }
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder, org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void beforeAdd(ChannelHandlerContext channelHandlerContext) throws Exception {
        super.beforeAdd(channelHandlerContext);
        this.ctx = channelHandlerContext;
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder, org.jboss.netty.channel.LifeCycleAwareChannelHandler
    public void afterRemove(ChannelHandlerContext channelHandlerContext) throws Exception {
        IOException iOException = null;
        while (true) {
            PendingWrite poll = this.pendingUnencryptedWrites.poll();
            if (poll == null) {
                break;
            }
            if (iOException == null) {
                iOException = new IOException("Unable to write data");
            }
            poll.future.setFailure(iOException);
        }
        while (true) {
            MessageEvent poll2 = this.pendingEncryptedWrites.poll();
            if (poll2 == null) {
                break;
            }
            if (iOException == null) {
                iOException = new IOException("Unable to write data");
            }
            poll2.getFuture().setFailure(iOException);
        }
        if (iOException != null) {
            Channels.fireExceptionCaughtLater(channelHandlerContext, iOException);
        }
    }

    @Override // org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void channelConnected(final ChannelHandlerContext channelHandlerContext, final ChannelStateEvent channelStateEvent) throws Exception {
        if (this.issueHandshake) {
            handshake().addListener(new ChannelFutureListener() { // from class: org.jboss.netty.handler.ssl.SslHandler.3
                @Override // org.jboss.netty.channel.ChannelFutureListener
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (!channelFuture.isSuccess()) {
                        Channels.fireExceptionCaught(channelFuture.getChannel(), channelFuture.getCause());
                    } else {
                        channelHandlerContext.sendUpstream(channelStateEvent);
                    }
                }
            });
        } else {
            super.channelConnected(channelHandlerContext, channelStateEvent);
        }
    }

    @Override // org.jboss.netty.handler.codec.frame.FrameDecoder, org.jboss.netty.channel.SimpleChannelUpstreamHandler
    public void channelClosed(ChannelHandlerContext channelHandlerContext, ChannelStateEvent channelStateEvent) throws Exception {
        ClosedChannelException closedChannelException;
        synchronized (this.pendingUnencryptedWrites) {
            closedChannelException = null;
            while (true) {
                PendingWrite poll = this.pendingUnencryptedWrites.poll();
                if (poll == null) {
                    break;
                }
                if (closedChannelException == null) {
                    closedChannelException = new ClosedChannelException();
                }
                poll.future.setFailure(closedChannelException);
            }
            while (true) {
                MessageEvent poll2 = this.pendingEncryptedWrites.poll();
                if (poll2 == null) {
                    break;
                }
                if (closedChannelException == null) {
                    closedChannelException = new ClosedChannelException();
                }
                poll2.getFuture().setFailure(closedChannelException);
            }
        }
        if (closedChannelException != null) {
            Channels.fireExceptionCaught(channelHandlerContext, closedChannelException);
        }
        super.channelClosed(channelHandlerContext, channelStateEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public final class SSLEngineInboundCloseFuture extends DefaultChannelFuture {
        @Override // org.jboss.netty.channel.DefaultChannelFuture, org.jboss.netty.channel.ChannelFuture
        public boolean setFailure(Throwable th) {
            return false;
        }

        @Override // org.jboss.netty.channel.DefaultChannelFuture, org.jboss.netty.channel.ChannelFuture
        public boolean setSuccess() {
            return false;
        }

        public SSLEngineInboundCloseFuture() {
            super(null, true);
        }

        void setClosed() {
            super.setSuccess();
        }

        @Override // org.jboss.netty.channel.DefaultChannelFuture, org.jboss.netty.channel.ChannelFuture
        public Channel getChannel() {
            if (SslHandler.this.ctx == null) {
                return null;
            }
            return SslHandler.this.ctx.getChannel();
        }
    }
}
