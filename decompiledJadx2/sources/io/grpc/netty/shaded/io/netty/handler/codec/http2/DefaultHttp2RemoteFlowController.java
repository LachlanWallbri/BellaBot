package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.ArrayDeque;
import java.util.Deque;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultHttp2RemoteFlowController implements Http2RemoteFlowController {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int MIN_WRITABLE_CHUNK = 32768;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) DefaultHttp2RemoteFlowController.class);
    private final Http2Connection connection;
    private final FlowState connectionState;
    private ChannelHandlerContext ctx;
    private int initialWindowSize;
    private WritabilityMonitor monitor;
    private final Http2Connection.PropertyKey stateKey;
    private final StreamByteDistributor streamByteDistributor;

    public DefaultHttp2RemoteFlowController(Http2Connection http2Connection) {
        this(http2Connection, (Http2RemoteFlowController.Listener) null);
    }

    public DefaultHttp2RemoteFlowController(Http2Connection http2Connection, StreamByteDistributor streamByteDistributor) {
        this(http2Connection, streamByteDistributor, null);
    }

    public DefaultHttp2RemoteFlowController(Http2Connection http2Connection, Http2RemoteFlowController.Listener listener) {
        this(http2Connection, new WeightedFairQueueByteDistributor(http2Connection), listener);
    }

    public DefaultHttp2RemoteFlowController(Http2Connection http2Connection, StreamByteDistributor streamByteDistributor, Http2RemoteFlowController.Listener listener) {
        this.initialWindowSize = 65535;
        this.connection = (Http2Connection) ObjectUtil.checkNotNull(http2Connection, "connection");
        this.streamByteDistributor = (StreamByteDistributor) ObjectUtil.checkNotNull(streamByteDistributor, "streamWriteDistributor");
        this.stateKey = http2Connection.newKey();
        this.connectionState = new FlowState(http2Connection.connectionStream());
        http2Connection.connectionStream().setProperty(this.stateKey, this.connectionState);
        listener(listener);
        this.monitor.windowSize(this.connectionState, this.initialWindowSize);
        http2Connection.addListener(new Http2ConnectionAdapter() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.1
            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamAdded(Http2Stream http2Stream) {
                http2Stream.setProperty(DefaultHttp2RemoteFlowController.this.stateKey, new FlowState(http2Stream));
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamActive(Http2Stream http2Stream) {
                DefaultHttp2RemoteFlowController.this.monitor.windowSize(DefaultHttp2RemoteFlowController.this.state(http2Stream), DefaultHttp2RemoteFlowController.this.initialWindowSize);
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamClosed(Http2Stream http2Stream) {
                DefaultHttp2RemoteFlowController.this.state(http2Stream).cancel(Http2Error.STREAM_CLOSED, null);
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamHalfClosed(Http2Stream http2Stream) {
                if (Http2Stream.State.HALF_CLOSED_LOCAL == http2Stream.state()) {
                    DefaultHttp2RemoteFlowController.this.state(http2Stream).cancel(Http2Error.STREAM_CLOSED, null);
                }
            }
        });
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
    public void channelHandlerContext(ChannelHandlerContext channelHandlerContext) throws Http2Exception {
        this.ctx = (ChannelHandlerContext) ObjectUtil.checkNotNull(channelHandlerContext, "ctx");
        channelWritabilityChanged();
        if (isChannelWritable()) {
            writePendingBytes();
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController
    public ChannelHandlerContext channelHandlerContext() {
        return this.ctx;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
    public void initialWindowSize(int i) throws Http2Exception {
        this.monitor.initialWindowSize(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
    public int initialWindowSize() {
        return this.initialWindowSize;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
    public int windowSize(Http2Stream http2Stream) {
        return state(http2Stream).windowSize();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController
    public boolean isWritable(Http2Stream http2Stream) {
        return this.monitor.isWritable(state(http2Stream));
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController
    public void channelWritabilityChanged() throws Http2Exception {
        this.monitor.channelWritabilityChange();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController
    public void updateDependencyTree(int i, int i2, short s, boolean z) {
        this.streamByteDistributor.updateDependencyTree(i, i2, s, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isChannelWritable() {
        return this.ctx != null && isChannelWritable0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isChannelWritable0() {
        return this.ctx.channel().isWritable();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController
    public void listener(Http2RemoteFlowController.Listener listener) {
        this.monitor = listener == null ? new WritabilityMonitor() : new ListenerWritabilityMonitor(listener);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
    public void incrementWindowSize(Http2Stream http2Stream, int i) throws Http2Exception {
        this.monitor.incrementWindowSize(state(http2Stream), i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController
    public void addFlowControlled(Http2Stream http2Stream, Http2RemoteFlowController.FlowControlled flowControlled) {
        ObjectUtil.checkNotNull(flowControlled, TypedValues.Attributes.S_FRAME);
        try {
            this.monitor.enqueueFrame(state(http2Stream), flowControlled);
        } catch (Throwable th) {
            flowControlled.error(this.ctx, th);
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController
    public boolean hasFlowControlled(Http2Stream http2Stream) {
        return state(http2Stream).hasFrame();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FlowState state(Http2Stream http2Stream) {
        return (FlowState) http2Stream.getProperty(this.stateKey);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int connectionWindowSize() {
        return this.connectionState.windowSize();
    }

    private int minUsableChannelBytes() {
        return Math.max(this.ctx.channel().config().getWriteBufferLowWaterMark(), 32768);
    }

    private int maxUsableChannelBytes() {
        int min = (int) Math.min(2147483647L, this.ctx.channel().bytesBeforeUnwritable());
        return Math.min(this.connectionState.windowSize(), min > 0 ? Math.max(min, minUsableChannelBytes()) : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int writableBytes() {
        return Math.min(connectionWindowSize(), maxUsableChannelBytes());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2RemoteFlowController
    public void writePendingBytes() throws Http2Exception {
        this.monitor.writePendingBytes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class FlowState implements StreamByteDistributor.StreamState {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean cancelled;
        private boolean markedWritable;
        private long pendingBytes;
        private final Deque<Http2RemoteFlowController.FlowControlled> pendingWriteQueue = new ArrayDeque(2);
        private final Http2Stream stream;
        private int window;
        private boolean writing;

        FlowState(Http2Stream http2Stream) {
            this.stream = http2Stream;
        }

        boolean isWritable() {
            return ((long) windowSize()) > pendingBytes() && !this.cancelled;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor.StreamState
        public Http2Stream stream() {
            return this.stream;
        }

        boolean markedWritability() {
            return this.markedWritable;
        }

        void markedWritability(boolean z) {
            this.markedWritable = z;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor.StreamState
        public int windowSize() {
            return this.window;
        }

        void windowSize(int i) {
            this.window = i;
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x0053, code lost:
        
            r8.writing = r1;
            r9 = r9 - r3;
            decrementPendingBytes(r9, r1);
            decrementFlowControlWindow(r9);
            r9 = r8.cancelled;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x0066, code lost:
        
            return -1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        int writeAllocatedBytes(int i) {
            int i2;
            int i3;
            Http2RemoteFlowController.FlowControlled peek;
            int min;
            try {
                this.writing = true;
                i2 = i;
                boolean z = false;
                while (!this.cancelled && (peek = peek()) != null && ((min = Math.min(i2, writableWindow())) > 0 || peek.size() <= 0)) {
                    try {
                        int size = peek.size();
                        try {
                            peek.write(DefaultHttp2RemoteFlowController.this.ctx, Math.max(0, min));
                            if (peek.size() == 0) {
                                this.pendingWriteQueue.remove();
                                peek.writeComplete();
                            }
                            i2 -= size - peek.size();
                            z = true;
                        } finally {
                        }
                    } catch (Throwable th) {
                        th = th;
                        try {
                            this.cancelled = true;
                            this.writing = false;
                            i3 = i - i2;
                            decrementPendingBytes(i3, false);
                            decrementFlowControlWindow(i3);
                            if (this.cancelled) {
                                cancel(Http2Error.INTERNAL_ERROR, th);
                            }
                            return i3;
                        } finally {
                            this.writing = false;
                            int i4 = i - i2;
                            decrementPendingBytes(i4, false);
                            decrementFlowControlWindow(i4);
                            if (this.cancelled) {
                                cancel(Http2Error.INTERNAL_ERROR, null);
                            }
                        }
                    }
                }
                this.writing = false;
                i3 = i - i2;
                decrementPendingBytes(i3, false);
                decrementFlowControlWindow(i3);
                if (this.cancelled) {
                    cancel(Http2Error.INTERNAL_ERROR, null);
                }
            } catch (Throwable th2) {
                th = th2;
                i2 = i;
            }
            return i3;
        }

        int incrementStreamWindow(int i) throws Http2Exception {
            if (i > 0 && Integer.MAX_VALUE - i < this.window) {
                throw Http2Exception.streamError(this.stream.mo3916id(), Http2Error.FLOW_CONTROL_ERROR, "Window size overflow for stream: %d", Integer.valueOf(this.stream.mo3916id()));
            }
            this.window += i;
            DefaultHttp2RemoteFlowController.this.streamByteDistributor.updateStreamableBytes(this);
            return this.window;
        }

        private int writableWindow() {
            return Math.min(this.window, DefaultHttp2RemoteFlowController.this.connectionWindowSize());
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor.StreamState
        public long pendingBytes() {
            return this.pendingBytes;
        }

        void enqueueFrame(Http2RemoteFlowController.FlowControlled flowControlled) {
            Http2RemoteFlowController.FlowControlled peekLast = this.pendingWriteQueue.peekLast();
            if (peekLast == null) {
                enqueueFrameWithoutMerge(flowControlled);
                return;
            }
            int size = peekLast.size();
            if (peekLast.merge(DefaultHttp2RemoteFlowController.this.ctx, flowControlled)) {
                incrementPendingBytes(peekLast.size() - size, true);
            } else {
                enqueueFrameWithoutMerge(flowControlled);
            }
        }

        private void enqueueFrameWithoutMerge(Http2RemoteFlowController.FlowControlled flowControlled) {
            this.pendingWriteQueue.offer(flowControlled);
            incrementPendingBytes(flowControlled.size(), true);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor.StreamState
        public boolean hasFrame() {
            return !this.pendingWriteQueue.isEmpty();
        }

        private Http2RemoteFlowController.FlowControlled peek() {
            return this.pendingWriteQueue.peek();
        }

        void cancel(Http2Error http2Error, Throwable th) {
            this.cancelled = true;
            if (this.writing) {
                return;
            }
            Http2RemoteFlowController.FlowControlled poll = this.pendingWriteQueue.poll();
            if (poll != null) {
                Http2Exception streamError = Http2Exception.streamError(this.stream.mo3916id(), http2Error, th, "Stream closed before write could take place", new Object[0]);
                do {
                    writeError(poll, streamError);
                    poll = this.pendingWriteQueue.poll();
                } while (poll != null);
            }
            DefaultHttp2RemoteFlowController.this.streamByteDistributor.updateStreamableBytes(this);
            DefaultHttp2RemoteFlowController.this.monitor.stateCancelled(this);
        }

        private void incrementPendingBytes(int i, boolean z) {
            this.pendingBytes += i;
            DefaultHttp2RemoteFlowController.this.monitor.incrementPendingBytes(i);
            if (z) {
                DefaultHttp2RemoteFlowController.this.streamByteDistributor.updateStreamableBytes(this);
            }
        }

        private void decrementPendingBytes(int i, boolean z) {
            incrementPendingBytes(-i, z);
        }

        private void decrementFlowControlWindow(int i) {
            int i2 = -i;
            try {
                DefaultHttp2RemoteFlowController.this.connectionState.incrementStreamWindow(i2);
                incrementStreamWindow(i2);
            } catch (Http2Exception e) {
                throw new IllegalStateException("Invalid window state when writing frame: " + e.getMessage(), e);
            }
        }

        private void writeError(Http2RemoteFlowController.FlowControlled flowControlled, Http2Exception http2Exception) {
            decrementPendingBytes(flowControlled.size(), true);
            flowControlled.error(DefaultHttp2RemoteFlowController.this.ctx, http2Exception);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class WritabilityMonitor implements StreamByteDistributor.Writer {
        private boolean inWritePendingBytes;
        private long totalPendingBytes;

        void channelWritabilityChange() throws Http2Exception {
        }

        void stateCancelled(FlowState flowState) {
        }

        private WritabilityMonitor() {
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor.Writer
        public final void write(Http2Stream http2Stream, int i) {
            DefaultHttp2RemoteFlowController.this.state(http2Stream).writeAllocatedBytes(i);
        }

        void windowSize(FlowState flowState, int i) {
            flowState.windowSize(i);
        }

        void incrementWindowSize(FlowState flowState, int i) throws Http2Exception {
            flowState.incrementStreamWindow(i);
        }

        void enqueueFrame(FlowState flowState, Http2RemoteFlowController.FlowControlled flowControlled) throws Http2Exception {
            flowState.enqueueFrame(flowControlled);
        }

        final void incrementPendingBytes(int i) {
            this.totalPendingBytes += i;
        }

        final boolean isWritable(FlowState flowState) {
            return isWritableConnection() && flowState.isWritable();
        }

        final void writePendingBytes() throws Http2Exception {
            if (this.inWritePendingBytes) {
                return;
            }
            this.inWritePendingBytes = true;
            try {
                int writableBytes = DefaultHttp2RemoteFlowController.this.writableBytes();
                while (DefaultHttp2RemoteFlowController.this.streamByteDistributor.distribute(writableBytes, this) && (writableBytes = DefaultHttp2RemoteFlowController.this.writableBytes()) > 0 && DefaultHttp2RemoteFlowController.this.isChannelWritable0()) {
                }
            } finally {
                this.inWritePendingBytes = false;
            }
        }

        void initialWindowSize(int i) throws Http2Exception {
            ObjectUtil.checkPositiveOrZero(i, "newWindowSize");
            final int i2 = i - DefaultHttp2RemoteFlowController.this.initialWindowSize;
            DefaultHttp2RemoteFlowController.this.initialWindowSize = i;
            DefaultHttp2RemoteFlowController.this.connection.forEachActiveStream(new Http2StreamVisitor() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.WritabilityMonitor.1
                @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor
                public boolean visit(Http2Stream http2Stream) throws Http2Exception {
                    DefaultHttp2RemoteFlowController.this.state(http2Stream).incrementStreamWindow(i2);
                    return true;
                }
            });
            if (i2 <= 0 || !DefaultHttp2RemoteFlowController.this.isChannelWritable()) {
                return;
            }
            writePendingBytes();
        }

        final boolean isWritableConnection() {
            return ((long) DefaultHttp2RemoteFlowController.this.connectionState.windowSize()) - this.totalPendingBytes > 0 && DefaultHttp2RemoteFlowController.this.isChannelWritable();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class ListenerWritabilityMonitor extends WritabilityMonitor implements Http2StreamVisitor {
        private final Http2RemoteFlowController.Listener listener;

        ListenerWritabilityMonitor(Http2RemoteFlowController.Listener listener) {
            super();
            this.listener = listener;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor
        public boolean visit(Http2Stream http2Stream) throws Http2Exception {
            FlowState state = DefaultHttp2RemoteFlowController.this.state(http2Stream);
            if (isWritable(state) == state.markedWritability()) {
                return true;
            }
            notifyWritabilityChanged(state);
            return true;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.WritabilityMonitor
        void windowSize(FlowState flowState, int i) {
            super.windowSize(flowState, i);
            try {
                checkStateWritability(flowState);
            } catch (Http2Exception e) {
                throw new RuntimeException("Caught unexpected exception from window", e);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.WritabilityMonitor
        void incrementWindowSize(FlowState flowState, int i) throws Http2Exception {
            super.incrementWindowSize(flowState, i);
            checkStateWritability(flowState);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.WritabilityMonitor
        void initialWindowSize(int i) throws Http2Exception {
            super.initialWindowSize(i);
            if (isWritableConnection()) {
                checkAllWritabilityChanged();
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.WritabilityMonitor
        void enqueueFrame(FlowState flowState, Http2RemoteFlowController.FlowControlled flowControlled) throws Http2Exception {
            super.enqueueFrame(flowState, flowControlled);
            checkConnectionThenStreamWritabilityChanged(flowState);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.WritabilityMonitor
        void stateCancelled(FlowState flowState) {
            try {
                checkConnectionThenStreamWritabilityChanged(flowState);
            } catch (Http2Exception e) {
                throw new RuntimeException("Caught unexpected exception from checkAllWritabilityChanged", e);
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2RemoteFlowController.WritabilityMonitor
        void channelWritabilityChange() throws Http2Exception {
            if (DefaultHttp2RemoteFlowController.this.connectionState.markedWritability() != DefaultHttp2RemoteFlowController.this.isChannelWritable()) {
                checkAllWritabilityChanged();
            }
        }

        private void checkStateWritability(FlowState flowState) throws Http2Exception {
            if (isWritable(flowState) != flowState.markedWritability()) {
                if (flowState == DefaultHttp2RemoteFlowController.this.connectionState) {
                    checkAllWritabilityChanged();
                } else {
                    notifyWritabilityChanged(flowState);
                }
            }
        }

        private void notifyWritabilityChanged(FlowState flowState) {
            flowState.markedWritability(!flowState.markedWritability());
            try {
                this.listener.writabilityChanged(flowState.stream);
            } catch (Throwable th) {
                DefaultHttp2RemoteFlowController.logger.error("Caught Throwable from listener.writabilityChanged", th);
            }
        }

        private void checkConnectionThenStreamWritabilityChanged(FlowState flowState) throws Http2Exception {
            if (isWritableConnection() != DefaultHttp2RemoteFlowController.this.connectionState.markedWritability()) {
                checkAllWritabilityChanged();
            } else if (isWritable(flowState) != flowState.markedWritability()) {
                notifyWritabilityChanged(flowState);
            }
        }

        private void checkAllWritabilityChanged() throws Http2Exception {
            DefaultHttp2RemoteFlowController.this.connectionState.markedWritability(isWritableConnection());
            DefaultHttp2RemoteFlowController.this.connection.forEachActiveStream(this);
        }
    }
}
