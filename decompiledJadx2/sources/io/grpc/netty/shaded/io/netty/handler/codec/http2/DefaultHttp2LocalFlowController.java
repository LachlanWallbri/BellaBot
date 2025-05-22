package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Exception;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Stream;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultHttp2LocalFlowController implements Http2LocalFlowController {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final float DEFAULT_WINDOW_UPDATE_RATIO = 0.5f;
    private static final FlowState REDUCED_FLOW_STATE = new FlowState() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.2
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public boolean consumeBytes(int i) throws Http2Exception {
            return false;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void incrementFlowControlWindows(int i) throws Http2Exception {
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void incrementInitialStreamWindow(int i) {
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public int initialWindowSize() {
            return 0;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public int unconsumedBytes() {
            return 0;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public int windowSize() {
            return 0;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void window(int i) {
            throw new UnsupportedOperationException();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public boolean writeWindowUpdateIfNeeded() throws Http2Exception {
            throw new UnsupportedOperationException();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public float windowUpdateRatio() {
            throw new UnsupportedOperationException();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void windowUpdateRatio(float f) {
            throw new UnsupportedOperationException();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void receiveFlowControlledFrame(int i) throws Http2Exception {
            throw new UnsupportedOperationException();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void endOfStream(boolean z) {
            throw new UnsupportedOperationException();
        }
    };
    private final Http2Connection connection;
    private ChannelHandlerContext ctx;
    private Http2FrameWriter frameWriter;
    private int initialWindowSize;
    private final Http2Connection.PropertyKey stateKey;
    private float windowUpdateRatio;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface FlowState {
        boolean consumeBytes(int i) throws Http2Exception;

        void endOfStream(boolean z);

        void incrementFlowControlWindows(int i) throws Http2Exception;

        void incrementInitialStreamWindow(int i);

        int initialWindowSize();

        void receiveFlowControlledFrame(int i) throws Http2Exception;

        int unconsumedBytes();

        void window(int i);

        int windowSize();

        float windowUpdateRatio();

        void windowUpdateRatio(float f);

        boolean writeWindowUpdateIfNeeded() throws Http2Exception;
    }

    public DefaultHttp2LocalFlowController(Http2Connection http2Connection) {
        this(http2Connection, 0.5f, false);
    }

    public DefaultHttp2LocalFlowController(Http2Connection http2Connection, float f, boolean z) {
        FlowState defaultState;
        this.initialWindowSize = 65535;
        this.connection = (Http2Connection) ObjectUtil.checkNotNull(http2Connection, "connection");
        windowUpdateRatio(f);
        this.stateKey = http2Connection.newKey();
        if (z) {
            defaultState = new AutoRefillState(http2Connection.connectionStream(), this.initialWindowSize);
        } else {
            defaultState = new DefaultState(http2Connection.connectionStream(), this.initialWindowSize);
        }
        http2Connection.connectionStream().setProperty(this.stateKey, defaultState);
        http2Connection.addListener(new Http2ConnectionAdapter() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.1
            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamAdded(Http2Stream http2Stream) {
                http2Stream.setProperty(DefaultHttp2LocalFlowController.this.stateKey, DefaultHttp2LocalFlowController.REDUCED_FLOW_STATE);
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamActive(Http2Stream http2Stream) {
                Http2Connection.PropertyKey propertyKey = DefaultHttp2LocalFlowController.this.stateKey;
                DefaultHttp2LocalFlowController defaultHttp2LocalFlowController = DefaultHttp2LocalFlowController.this;
                http2Stream.setProperty(propertyKey, new DefaultState(http2Stream, defaultHttp2LocalFlowController.initialWindowSize));
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamClosed(Http2Stream http2Stream) {
                try {
                    try {
                        FlowState state = DefaultHttp2LocalFlowController.this.state(http2Stream);
                        int unconsumedBytes = state.unconsumedBytes();
                        if (DefaultHttp2LocalFlowController.this.ctx != null && unconsumedBytes > 0 && DefaultHttp2LocalFlowController.this.consumeAllBytes(state, unconsumedBytes)) {
                            DefaultHttp2LocalFlowController.this.ctx.flush();
                        }
                    } catch (Http2Exception e) {
                        PlatformDependent.throwException(e);
                    }
                } finally {
                    http2Stream.setProperty(DefaultHttp2LocalFlowController.this.stateKey, DefaultHttp2LocalFlowController.REDUCED_FLOW_STATE);
                }
            }
        });
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController
    public DefaultHttp2LocalFlowController frameWriter(Http2FrameWriter http2FrameWriter) {
        this.frameWriter = (Http2FrameWriter) ObjectUtil.checkNotNull(http2FrameWriter, "frameWriter");
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
    public void channelHandlerContext(ChannelHandlerContext channelHandlerContext) {
        this.ctx = (ChannelHandlerContext) ObjectUtil.checkNotNull(channelHandlerContext, "ctx");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
    public void initialWindowSize(int i) throws Http2Exception {
        int i2 = i - this.initialWindowSize;
        this.initialWindowSize = i;
        WindowUpdateVisitor windowUpdateVisitor = new WindowUpdateVisitor(i2);
        this.connection.forEachActiveStream(windowUpdateVisitor);
        windowUpdateVisitor.throwIfError();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
    public int initialWindowSize() {
        return this.initialWindowSize;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
    public int windowSize(Http2Stream http2Stream) {
        return state(http2Stream).windowSize();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController
    public int initialWindowSize(Http2Stream http2Stream) {
        return state(http2Stream).initialWindowSize();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2FlowController
    public void incrementWindowSize(Http2Stream http2Stream, int i) throws Http2Exception {
        FlowState state = state(http2Stream);
        state.incrementInitialStreamWindow(i);
        state.writeWindowUpdateIfNeeded();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController
    public boolean consumeBytes(Http2Stream http2Stream, int i) throws Http2Exception {
        ObjectUtil.checkPositiveOrZero(i, "numBytes");
        if (i == 0 || http2Stream == null || isClosed(http2Stream)) {
            return false;
        }
        if (http2Stream.mo3916id() == 0) {
            throw new UnsupportedOperationException("Returning bytes for the connection window is not supported");
        }
        return consumeAllBytes(state(http2Stream), i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean consumeAllBytes(FlowState flowState, int i) throws Http2Exception {
        return flowState.consumeBytes(i) | connectionState().consumeBytes(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController
    public int unconsumedBytes(Http2Stream http2Stream) {
        return state(http2Stream).unconsumedBytes();
    }

    private static void checkValidRatio(float f) {
        double d = f;
        if (Double.compare(d, 0.0d) <= 0 || Double.compare(d, 1.0d) >= 0) {
            throw new IllegalArgumentException("Invalid ratio: " + f);
        }
    }

    public void windowUpdateRatio(float f) {
        checkValidRatio(f);
        this.windowUpdateRatio = f;
    }

    public float windowUpdateRatio() {
        return this.windowUpdateRatio;
    }

    public void windowUpdateRatio(Http2Stream http2Stream, float f) throws Http2Exception {
        checkValidRatio(f);
        FlowState state = state(http2Stream);
        state.windowUpdateRatio(f);
        state.writeWindowUpdateIfNeeded();
    }

    public float windowUpdateRatio(Http2Stream http2Stream) throws Http2Exception {
        return state(http2Stream).windowUpdateRatio();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2LocalFlowController
    public void receiveFlowControlledFrame(Http2Stream http2Stream, ByteBuf byteBuf, int i, boolean z) throws Http2Exception {
        int readableBytes = byteBuf.readableBytes() + i;
        FlowState connectionState = connectionState();
        connectionState.receiveFlowControlledFrame(readableBytes);
        if (http2Stream == null || isClosed(http2Stream)) {
            if (readableBytes > 0) {
                connectionState.consumeBytes(readableBytes);
            }
        } else {
            FlowState state = state(http2Stream);
            state.endOfStream(z);
            state.receiveFlowControlledFrame(readableBytes);
        }
    }

    private FlowState connectionState() {
        return (FlowState) this.connection.connectionStream().getProperty(this.stateKey);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public FlowState state(Http2Stream http2Stream) {
        return (FlowState) http2Stream.getProperty(this.stateKey);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isClosed(Http2Stream http2Stream) {
        return http2Stream.state() == Http2Stream.State.CLOSED;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class AutoRefillState extends DefaultState {
        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.DefaultState, io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public boolean consumeBytes(int i) throws Http2Exception {
            return false;
        }

        AutoRefillState(Http2Stream http2Stream, int i) {
            super(http2Stream, i);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.DefaultState, io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void receiveFlowControlledFrame(int i) throws Http2Exception {
            super.receiveFlowControlledFrame(i);
            super.consumeBytes(i);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private class DefaultState implements FlowState {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private boolean endOfStream;
        private int initialStreamWindowSize;
        private int lowerBound;
        private int processedWindow;
        private final Http2Stream stream;
        private float streamWindowUpdateRatio;
        private int window;

        DefaultState(Http2Stream http2Stream, int i) {
            this.stream = http2Stream;
            window(i);
            this.streamWindowUpdateRatio = DefaultHttp2LocalFlowController.this.windowUpdateRatio;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void window(int i) {
            this.initialStreamWindowSize = i;
            this.processedWindow = i;
            this.window = i;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public int windowSize() {
            return this.window;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public int initialWindowSize() {
            return this.initialStreamWindowSize;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void endOfStream(boolean z) {
            this.endOfStream = z;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public float windowUpdateRatio() {
            return this.streamWindowUpdateRatio;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void windowUpdateRatio(float f) {
            this.streamWindowUpdateRatio = f;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void incrementInitialStreamWindow(int i) {
            int min = (int) Math.min(2147483647L, Math.max(0L, this.initialStreamWindowSize + i));
            int i2 = this.initialStreamWindowSize;
            this.initialStreamWindowSize = i2 + (min - i2);
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void incrementFlowControlWindows(int i) throws Http2Exception {
            if (i > 0 && this.window > Integer.MAX_VALUE - i) {
                throw Http2Exception.streamError(this.stream.mo3916id(), Http2Error.FLOW_CONTROL_ERROR, "Flow control window overflowed for stream: %d", Integer.valueOf(this.stream.mo3916id()));
            }
            this.window += i;
            this.processedWindow += i;
            if (i >= 0) {
                i = 0;
            }
            this.lowerBound = i;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public void receiveFlowControlledFrame(int i) throws Http2Exception {
            this.window -= i;
            if (this.window < this.lowerBound) {
                throw Http2Exception.streamError(this.stream.mo3916id(), Http2Error.FLOW_CONTROL_ERROR, "Flow control window exceeded for stream: %d", Integer.valueOf(this.stream.mo3916id()));
            }
        }

        private void returnProcessedBytes(int i) throws Http2Exception {
            int i2 = this.processedWindow;
            if (i2 - i < this.window) {
                throw Http2Exception.streamError(this.stream.mo3916id(), Http2Error.INTERNAL_ERROR, "Attempting to return too many bytes for stream %d", Integer.valueOf(this.stream.mo3916id()));
            }
            this.processedWindow = i2 - i;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public boolean consumeBytes(int i) throws Http2Exception {
            returnProcessedBytes(i);
            return writeWindowUpdateIfNeeded();
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public int unconsumedBytes() {
            return this.processedWindow - this.window;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.DefaultHttp2LocalFlowController.FlowState
        public boolean writeWindowUpdateIfNeeded() throws Http2Exception {
            if (!this.endOfStream && this.initialStreamWindowSize > 0 && !DefaultHttp2LocalFlowController.isClosed(this.stream)) {
                if (this.processedWindow <= ((int) (this.initialStreamWindowSize * this.streamWindowUpdateRatio))) {
                    writeWindowUpdate();
                    return true;
                }
            }
            return false;
        }

        private void writeWindowUpdate() throws Http2Exception {
            int i = this.initialStreamWindowSize - this.processedWindow;
            try {
                incrementFlowControlWindows(i);
                DefaultHttp2LocalFlowController.this.frameWriter.writeWindowUpdate(DefaultHttp2LocalFlowController.this.ctx, this.stream.mo3916id(), i, DefaultHttp2LocalFlowController.this.ctx.newPromise());
            } catch (Throwable th) {
                throw Http2Exception.connectionError(Http2Error.INTERNAL_ERROR, th, "Attempting to return too many bytes for stream %d", Integer.valueOf(this.stream.mo3916id()));
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class WindowUpdateVisitor implements Http2StreamVisitor {
        private Http2Exception.CompositeStreamException compositeException;
        private final int delta;

        WindowUpdateVisitor(int i) {
            this.delta = i;
        }

        @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2StreamVisitor
        public boolean visit(Http2Stream http2Stream) throws Http2Exception {
            try {
                FlowState state = DefaultHttp2LocalFlowController.this.state(http2Stream);
                state.incrementFlowControlWindows(this.delta);
                state.incrementInitialStreamWindow(this.delta);
                return true;
            } catch (Http2Exception.StreamException e) {
                if (this.compositeException == null) {
                    this.compositeException = new Http2Exception.CompositeStreamException(e.error(), 4);
                }
                this.compositeException.add(e);
                return true;
            }
        }

        public void throwIfError() throws Http2Exception.CompositeStreamException {
            Http2Exception.CompositeStreamException compositeStreamException = this.compositeException;
            if (compositeStreamException != null) {
                throw compositeStreamException;
            }
        }
    }
}
