package io.grpc.netty.shaded.io.netty.handler.codec.http2;

import io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection;
import io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.ArrayDeque;
import java.util.Deque;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class UniformStreamByteDistributor implements StreamByteDistributor {
    private final Http2Connection.PropertyKey stateKey;
    private long totalStreamableBytes;
    private final Deque<State> queue = new ArrayDeque(4);
    private int minAllocationChunk = 1024;

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor
    public void updateDependencyTree(int i, int i2, short s, boolean z) {
    }

    public UniformStreamByteDistributor(Http2Connection http2Connection) {
        this.stateKey = http2Connection.newKey();
        Http2Stream connectionStream = http2Connection.connectionStream();
        connectionStream.setProperty(this.stateKey, new State(connectionStream));
        http2Connection.addListener(new Http2ConnectionAdapter() { // from class: io.grpc.netty.shaded.io.netty.handler.codec.http2.UniformStreamByteDistributor.1
            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamAdded(Http2Stream http2Stream) {
                http2Stream.setProperty(UniformStreamByteDistributor.this.stateKey, new State(http2Stream));
            }

            @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2ConnectionAdapter, io.grpc.netty.shaded.io.netty.handler.codec.http2.Http2Connection.Listener
            public void onStreamClosed(Http2Stream http2Stream) {
                UniformStreamByteDistributor.this.state(http2Stream).close();
            }
        });
    }

    public void minAllocationChunk(int i) {
        ObjectUtil.checkPositive(i, "minAllocationChunk");
        this.minAllocationChunk = i;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor
    public void updateStreamableBytes(StreamByteDistributor.StreamState streamState) {
        state(streamState.stream()).updateStreamableBytes(Http2CodecUtil.streamableBytes(streamState), streamState.hasFrame(), streamState.windowSize());
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http2.StreamByteDistributor
    public boolean distribute(int i, StreamByteDistributor.Writer writer) throws Http2Exception {
        int size = this.queue.size();
        if (size == 0) {
            return this.totalStreamableBytes > 0;
        }
        int max = Math.max(this.minAllocationChunk, i / size);
        State pollFirst = this.queue.pollFirst();
        while (true) {
            pollFirst.enqueued = false;
            if (!pollFirst.windowNegative) {
                if (i == 0 && pollFirst.streamableBytes > 0) {
                    this.queue.addFirst(pollFirst);
                    pollFirst.enqueued = true;
                    break;
                }
                int min = Math.min(max, Math.min(i, pollFirst.streamableBytes));
                i -= min;
                pollFirst.write(min, writer);
            }
            pollFirst = this.queue.pollFirst();
            if (pollFirst == null) {
                break;
            }
        }
        return this.totalStreamableBytes > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public State state(Http2Stream http2Stream) {
        return (State) ((Http2Stream) ObjectUtil.checkNotNull(http2Stream, "stream")).getProperty(this.stateKey);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class State {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        boolean enqueued;
        final Http2Stream stream;
        int streamableBytes;
        boolean windowNegative;
        boolean writing;

        State(Http2Stream http2Stream) {
            this.stream = http2Stream;
        }

        void updateStreamableBytes(int i, boolean z, int i2) {
            int i3 = i - this.streamableBytes;
            if (i3 != 0) {
                this.streamableBytes = i;
                UniformStreamByteDistributor.this.totalStreamableBytes += i3;
            }
            this.windowNegative = i2 < 0;
            if (z) {
                if (i2 > 0 || (i2 == 0 && !this.writing)) {
                    addToQueue();
                }
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        void write(int i, StreamByteDistributor.Writer writer) throws Http2Exception {
            this.writing = true;
            try {
                writer.write(this.stream, i);
            } finally {
            }
        }

        void addToQueue() {
            if (this.enqueued) {
                return;
            }
            this.enqueued = true;
            UniformStreamByteDistributor.this.queue.addLast(this);
        }

        void removeFromQueue() {
            if (this.enqueued) {
                this.enqueued = false;
                UniformStreamByteDistributor.this.queue.remove(this);
            }
        }

        void close() {
            removeFromQueue();
            updateStreamableBytes(0, false, 0);
        }
    }
}
