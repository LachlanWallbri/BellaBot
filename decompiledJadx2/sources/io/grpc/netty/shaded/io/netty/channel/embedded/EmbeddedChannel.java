package io.grpc.netty.shaded.io.netty.channel.embedded;

import io.grpc.netty.shaded.io.netty.channel.AbstractChannel;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelFutureListener;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandler;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.channel.ChannelId;
import io.grpc.netty.shaded.io.netty.channel.ChannelInitializer;
import io.grpc.netty.shaded.io.netty.channel.ChannelMetadata;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.channel.ChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelConfig;
import io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline;
import io.grpc.netty.shaded.io.netty.channel.EventLoop;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Future;
import io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import io.grpc.netty.shaded.io.netty.util.internal.RecyclableArrayList;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class EmbeddedChannel extends AbstractChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ChannelConfig config;
    private Queue<Object> inboundMessages;
    private Throwable lastException;
    private final EmbeddedEventLoop loop;
    private final ChannelMetadata metadata;
    private Queue<Object> outboundMessages;
    private final ChannelFutureListener recordExceptionListener;
    private State state;
    private static final SocketAddress LOCAL_ADDRESS = new EmbeddedSocketAddress();
    private static final SocketAddress REMOTE_ADDRESS = new EmbeddedSocketAddress();
    private static final ChannelHandler[] EMPTY_HANDLERS = new ChannelHandler[0];
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) EmbeddedChannel.class);
    private static final ChannelMetadata METADATA_NO_DISCONNECT = new ChannelMetadata(false);
    private static final ChannelMetadata METADATA_DISCONNECT = new ChannelMetadata(true);

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum State {
        OPEN,
        ACTIVE,
        CLOSED
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBeginRead() throws Exception {
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doBind(SocketAddress socketAddress) throws Exception {
    }

    public EmbeddedChannel() {
        this(EMPTY_HANDLERS);
    }

    public EmbeddedChannel(ChannelId channelId) {
        this(channelId, EMPTY_HANDLERS);
    }

    public EmbeddedChannel(ChannelHandler... channelHandlerArr) {
        this(EmbeddedChannelId.INSTANCE, channelHandlerArr);
    }

    public EmbeddedChannel(boolean z, ChannelHandler... channelHandlerArr) {
        this(EmbeddedChannelId.INSTANCE, z, channelHandlerArr);
    }

    public EmbeddedChannel(boolean z, boolean z2, ChannelHandler... channelHandlerArr) {
        this(EmbeddedChannelId.INSTANCE, z, z2, channelHandlerArr);
    }

    public EmbeddedChannel(ChannelId channelId, ChannelHandler... channelHandlerArr) {
        this(channelId, false, channelHandlerArr);
    }

    public EmbeddedChannel(ChannelId channelId, boolean z, ChannelHandler... channelHandlerArr) {
        this(channelId, true, z, channelHandlerArr);
    }

    public EmbeddedChannel(ChannelId channelId, boolean z, boolean z2, ChannelHandler... channelHandlerArr) {
        this(null, channelId, z, z2, channelHandlerArr);
    }

    public EmbeddedChannel(Channel channel, ChannelId channelId, boolean z, boolean z2, ChannelHandler... channelHandlerArr) {
        super(channel, channelId);
        this.loop = new EmbeddedEventLoop();
        this.recordExceptionListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.embedded.EmbeddedChannel.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                EmbeddedChannel.this.recordException(channelFuture);
            }
        };
        this.metadata = metadata(z2);
        this.config = new DefaultChannelConfig(this);
        setup(z, channelHandlerArr);
    }

    public EmbeddedChannel(ChannelId channelId, boolean z, ChannelConfig channelConfig, ChannelHandler... channelHandlerArr) {
        super(null, channelId);
        this.loop = new EmbeddedEventLoop();
        this.recordExceptionListener = new ChannelFutureListener() { // from class: io.grpc.netty.shaded.io.netty.channel.embedded.EmbeddedChannel.1
            @Override // io.grpc.netty.shaded.io.netty.util.concurrent.GenericFutureListener
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                EmbeddedChannel.this.recordException(channelFuture);
            }
        };
        this.metadata = metadata(z);
        this.config = (ChannelConfig) ObjectUtil.checkNotNull(channelConfig, "config");
        setup(true, channelHandlerArr);
    }

    private static ChannelMetadata metadata(boolean z) {
        return z ? METADATA_DISCONNECT : METADATA_NO_DISCONNECT;
    }

    private void setup(boolean z, final ChannelHandler... channelHandlerArr) {
        ObjectUtil.checkNotNull(channelHandlerArr, "handlers");
        pipeline().addLast(new ChannelInitializer<Channel>() { // from class: io.grpc.netty.shaded.io.netty.channel.embedded.EmbeddedChannel.2
            @Override // io.grpc.netty.shaded.io.netty.channel.ChannelInitializer
            protected void initChannel(Channel channel) throws Exception {
                ChannelPipeline pipeline = channel.pipeline();
                for (ChannelHandler channelHandler : channelHandlerArr) {
                    if (channelHandler == null) {
                        return;
                    }
                    pipeline.addLast(channelHandler);
                }
            }
        });
        if (z) {
            this.loop.register(this);
        }
    }

    public void register() throws Exception {
        Throwable cause = this.loop.register(this).cause();
        if (cause != null) {
            PlatformDependent.throwException(cause);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected final DefaultChannelPipeline newChannelPipeline() {
        return new EmbeddedChannelPipeline(this);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelMetadata metadata() {
        return this.metadata;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public ChannelConfig config() {
        return this.config;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isOpen() {
        return this.state != State.CLOSED;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.Channel
    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public Queue<Object> inboundMessages() {
        if (this.inboundMessages == null) {
            this.inboundMessages = new ArrayDeque();
        }
        return this.inboundMessages;
    }

    @Deprecated
    public Queue<Object> lastInboundBuffer() {
        return inboundMessages();
    }

    public Queue<Object> outboundMessages() {
        if (this.outboundMessages == null) {
            this.outboundMessages = new ArrayDeque();
        }
        return this.outboundMessages;
    }

    @Deprecated
    public Queue<Object> lastOutboundBuffer() {
        return outboundMessages();
    }

    public <T> T readInbound() {
        T t = (T) poll(this.inboundMessages);
        if (t != null) {
            ReferenceCountUtil.touch(t, "Caller of readInbound() will handle the message from this point");
        }
        return t;
    }

    public <T> T readOutbound() {
        T t = (T) poll(this.outboundMessages);
        if (t != null) {
            ReferenceCountUtil.touch(t, "Caller of readOutbound() will handle the message from this point.");
        }
        return t;
    }

    public boolean writeInbound(Object... objArr) {
        ensureOpen();
        if (objArr.length == 0) {
            return isNotEmpty(this.inboundMessages);
        }
        ChannelPipeline pipeline = pipeline();
        for (Object obj : objArr) {
            pipeline.fireChannelRead(obj);
        }
        flushInbound(false, voidPromise());
        return isNotEmpty(this.inboundMessages);
    }

    public ChannelFuture writeOneInbound(Object obj) {
        return writeOneInbound(obj, newPromise());
    }

    public ChannelFuture writeOneInbound(Object obj, ChannelPromise channelPromise) {
        if (checkOpen(true)) {
            pipeline().fireChannelRead(obj);
        }
        return checkException(channelPromise);
    }

    public EmbeddedChannel flushInbound() {
        flushInbound(true, voidPromise());
        return this;
    }

    private ChannelFuture flushInbound(boolean z, ChannelPromise channelPromise) {
        if (checkOpen(z)) {
            pipeline().fireChannelReadComplete();
            runPendingTasks();
        }
        return checkException(channelPromise);
    }

    public boolean writeOutbound(Object... objArr) {
        ensureOpen();
        if (objArr.length == 0) {
            return isNotEmpty(this.outboundMessages);
        }
        RecyclableArrayList newInstance = RecyclableArrayList.newInstance(objArr.length);
        try {
            for (Object obj : objArr) {
                if (obj == null) {
                    break;
                }
                newInstance.add(write(obj));
            }
            flushOutbound0();
            int size = newInstance.size();
            for (int i = 0; i < size; i++) {
                ChannelFuture channelFuture = (ChannelFuture) newInstance.get(i);
                if (channelFuture.isDone()) {
                    recordException(channelFuture);
                } else {
                    channelFuture.addListener((GenericFutureListener<? extends Future<? super Void>>) this.recordExceptionListener);
                }
            }
            checkException();
            return isNotEmpty(this.outboundMessages);
        } finally {
            newInstance.recycle();
        }
    }

    public ChannelFuture writeOneOutbound(Object obj) {
        return writeOneOutbound(obj, newPromise());
    }

    public ChannelFuture writeOneOutbound(Object obj, ChannelPromise channelPromise) {
        if (checkOpen(true)) {
            return write(obj, channelPromise);
        }
        return checkException(channelPromise);
    }

    public EmbeddedChannel flushOutbound() {
        if (checkOpen(true)) {
            flushOutbound0();
        }
        checkException(voidPromise());
        return this;
    }

    private void flushOutbound0() {
        runPendingTasks();
        flush();
    }

    public boolean finish() {
        return finish(false);
    }

    public boolean finishAndReleaseAll() {
        return finish(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001c A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private boolean finish(boolean z) {
        boolean z2;
        close();
        try {
            checkException();
            if (!isNotEmpty(this.inboundMessages)) {
                if (!isNotEmpty(this.outboundMessages)) {
                    z2 = false;
                    return z2;
                }
            }
            z2 = true;
            return z2;
        } finally {
            if (z) {
                releaseAll(this.inboundMessages);
                releaseAll(this.outboundMessages);
            }
        }
    }

    public boolean releaseInbound() {
        return releaseAll(this.inboundMessages);
    }

    public boolean releaseOutbound() {
        return releaseAll(this.outboundMessages);
    }

    private static boolean releaseAll(Queue<Object> queue) {
        if (!isNotEmpty(queue)) {
            return false;
        }
        while (true) {
            Object poll = queue.poll();
            if (poll == null) {
                return true;
            }
            ReferenceCountUtil.release(poll);
        }
    }

    private void finishPendingTasks(boolean z) {
        runPendingTasks();
        if (z) {
            this.loop.cancelScheduledTasks();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture close() {
        return close(newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture disconnect() {
        return disconnect(newPromise());
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture close(ChannelPromise channelPromise) {
        runPendingTasks();
        ChannelFuture close = super.close(channelPromise);
        finishPendingTasks(true);
        return close;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.ChannelOutboundInvoker
    public final ChannelFuture disconnect(ChannelPromise channelPromise) {
        ChannelFuture disconnect = super.disconnect(channelPromise);
        finishPendingTasks(!this.metadata.hasDisconnect());
        return disconnect;
    }

    private static boolean isNotEmpty(Queue<Object> queue) {
        return (queue == null || queue.isEmpty()) ? false : true;
    }

    private static Object poll(Queue<Object> queue) {
        if (queue != null) {
            return queue.poll();
        }
        return null;
    }

    public void runPendingTasks() {
        try {
            this.loop.runTasks();
        } catch (Exception e) {
            recordException(e);
        }
        try {
            this.loop.runScheduledTasks();
        } catch (Exception e2) {
            recordException(e2);
        }
    }

    public long runScheduledPendingTasks() {
        try {
            return this.loop.runScheduledTasks();
        } catch (Exception e) {
            recordException(e);
            return this.loop.nextScheduledTask();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordException(ChannelFuture channelFuture) {
        if (channelFuture.isSuccess()) {
            return;
        }
        recordException(channelFuture.cause());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void recordException(Throwable th) {
        if (this.lastException == null) {
            this.lastException = th;
        } else {
            logger.warn("More than one exception was raised. Will report only the first one and log others.", th);
        }
    }

    private ChannelFuture checkException(ChannelPromise channelPromise) {
        Throwable th = this.lastException;
        if (th != null) {
            this.lastException = null;
            if (channelPromise.isVoid()) {
                PlatformDependent.throwException(th);
            }
            return channelPromise.setFailure(th);
        }
        return channelPromise.setSuccess();
    }

    public void checkException() {
        checkException(voidPromise());
    }

    private boolean checkOpen(boolean z) {
        if (isOpen()) {
            return true;
        }
        if (!z) {
            return false;
        }
        recordException(new ClosedChannelException());
        return false;
    }

    protected final void ensureOpen() {
        if (checkOpen(true)) {
            return;
        }
        checkException();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected boolean isCompatible(EventLoop eventLoop) {
        return eventLoop instanceof EmbeddedEventLoop;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress localAddress0() {
        if (isActive()) {
            return LOCAL_ADDRESS;
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected SocketAddress remoteAddress0() {
        if (isActive()) {
            return REMOTE_ADDRESS;
        }
        return null;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doRegister() throws Exception {
        this.state = State.ACTIVE;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doDisconnect() throws Exception {
        if (this.metadata.hasDisconnect()) {
            return;
        }
        doClose();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doClose() throws Exception {
        this.state = State.CLOSED;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected AbstractChannel.AbstractUnsafe newUnsafe() {
        return new EmbeddedUnsafe();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel, io.grpc.netty.shaded.io.netty.channel.Channel
    public Channel.Unsafe unsafe() {
        return ((EmbeddedUnsafe) super.unsafe()).wrapped;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.AbstractChannel
    protected void doWrite(ChannelOutboundBuffer channelOutboundBuffer) throws Exception {
        while (true) {
            Object current = channelOutboundBuffer.current();
            if (current == null) {
                return;
            }
            ReferenceCountUtil.retain(current);
            handleOutboundMessage(current);
            channelOutboundBuffer.remove();
        }
    }

    protected void handleOutboundMessage(Object obj) {
        outboundMessages().add(obj);
    }

    protected void handleInboundMessage(Object obj) {
        inboundMessages().add(obj);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class EmbeddedUnsafe extends AbstractChannel.AbstractUnsafe {
        final Channel.Unsafe wrapped;

        private EmbeddedUnsafe() {
            super();
            this.wrapped = new Channel.Unsafe() { // from class: io.grpc.netty.shaded.io.netty.channel.embedded.EmbeddedChannel.EmbeddedUnsafe.1
                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public RecvByteBufAllocator.Handle recvBufAllocHandle() {
                    return EmbeddedUnsafe.this.recvBufAllocHandle();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public SocketAddress localAddress() {
                    return EmbeddedUnsafe.this.localAddress();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public SocketAddress remoteAddress() {
                    return EmbeddedUnsafe.this.remoteAddress();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public void register(EventLoop eventLoop, ChannelPromise channelPromise) {
                    EmbeddedUnsafe.this.register(eventLoop, channelPromise);
                    EmbeddedChannel.this.runPendingTasks();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public void bind(SocketAddress socketAddress, ChannelPromise channelPromise) {
                    EmbeddedUnsafe.this.bind(socketAddress, channelPromise);
                    EmbeddedChannel.this.runPendingTasks();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
                    EmbeddedUnsafe.this.connect(socketAddress, socketAddress2, channelPromise);
                    EmbeddedChannel.this.runPendingTasks();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public void disconnect(ChannelPromise channelPromise) {
                    EmbeddedUnsafe.this.disconnect(channelPromise);
                    EmbeddedChannel.this.runPendingTasks();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public void close(ChannelPromise channelPromise) {
                    EmbeddedUnsafe.this.close(channelPromise);
                    EmbeddedChannel.this.runPendingTasks();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public void closeForcibly() {
                    EmbeddedUnsafe.this.closeForcibly();
                    EmbeddedChannel.this.runPendingTasks();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public void deregister(ChannelPromise channelPromise) {
                    EmbeddedUnsafe.this.deregister(channelPromise);
                    EmbeddedChannel.this.runPendingTasks();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public void beginRead() {
                    EmbeddedUnsafe.this.beginRead();
                    EmbeddedChannel.this.runPendingTasks();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public void write(Object obj, ChannelPromise channelPromise) {
                    EmbeddedUnsafe.this.write(obj, channelPromise);
                    EmbeddedChannel.this.runPendingTasks();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public void flush() {
                    EmbeddedUnsafe.this.flush();
                    EmbeddedChannel.this.runPendingTasks();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public ChannelPromise voidPromise() {
                    return EmbeddedUnsafe.this.voidPromise();
                }

                @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
                public ChannelOutboundBuffer outboundBuffer() {
                    return EmbeddedUnsafe.this.outboundBuffer();
                }
            };
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.Channel.Unsafe
        public void connect(SocketAddress socketAddress, SocketAddress socketAddress2, ChannelPromise channelPromise) {
            safeSetSuccess(channelPromise);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class EmbeddedChannelPipeline extends DefaultChannelPipeline {
        EmbeddedChannelPipeline(EmbeddedChannel embeddedChannel) {
            super(embeddedChannel);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline
        protected void onUnhandledInboundException(Throwable th) {
            EmbeddedChannel.this.recordException(th);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.DefaultChannelPipeline
        protected void onUnhandledInboundMessage(ChannelHandlerContext channelHandlerContext, Object obj) {
            EmbeddedChannel.this.handleInboundMessage(obj);
        }
    }
}
