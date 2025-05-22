package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Attributes;
import io.grpc.Decompressor;
import io.grpc.InternalStatus;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractStream;
import io.grpc.internal.MessageFramer;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractServerStream extends AbstractStream implements ServerStream, MessageFramer.Sink {
    private final MessageFramer framer;
    private boolean headersSent;
    private boolean outboundClosed;
    private final StatsTraceContext statsTraceCtx;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    protected interface Sink {
        void cancel(Status status);

        void writeFrame(@Nullable WritableBuffer writableBuffer, boolean z, int i);

        void writeHeaders(Metadata metadata);

        void writeTrailers(Metadata metadata, boolean z, Status status);
    }

    protected abstract Sink abstractServerStreamSink();

    @Override // io.grpc.internal.ServerStream
    public String getAuthority() {
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractStream
    public abstract TransportState transportState();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractServerStream(WritableBufferAllocator writableBufferAllocator, StatsTraceContext statsTraceContext) {
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        this.framer = new MessageFramer(this, writableBufferAllocator, statsTraceContext);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractStream
    public final MessageFramer framer() {
        return this.framer;
    }

    @Override // io.grpc.internal.ServerStream
    public final void writeHeaders(Metadata metadata) {
        Preconditions.checkNotNull(metadata, "headers");
        this.headersSent = true;
        abstractServerStreamSink().writeHeaders(metadata);
    }

    @Override // io.grpc.internal.MessageFramer.Sink
    public final void deliverFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i) {
        Sink abstractServerStreamSink = abstractServerStreamSink();
        if (z) {
            z2 = false;
        }
        abstractServerStreamSink.writeFrame(writableBuffer, z2, i);
    }

    @Override // io.grpc.internal.ServerStream
    public final void close(Status status, Metadata metadata) {
        Preconditions.checkNotNull(status, "status");
        Preconditions.checkNotNull(metadata, "trailers");
        if (this.outboundClosed) {
            return;
        }
        this.outboundClosed = true;
        endOfMessages();
        addStatusToTrailers(metadata, status);
        transportState().setClosedStatus(status);
        abstractServerStreamSink().writeTrailers(metadata, this.headersSent, status);
    }

    private void addStatusToTrailers(Metadata metadata, Status status) {
        metadata.discardAll(InternalStatus.CODE_KEY);
        metadata.discardAll(InternalStatus.MESSAGE_KEY);
        metadata.put(InternalStatus.CODE_KEY, status);
        if (status.getDescription() != null) {
            metadata.put(InternalStatus.MESSAGE_KEY, status.getDescription());
        }
    }

    @Override // io.grpc.internal.ServerStream
    public final void cancel(Status status) {
        abstractServerStreamSink().cancel(status);
    }

    @Override // io.grpc.internal.AbstractStream, io.grpc.internal.Stream
    public final boolean isReady() {
        return super.isReady();
    }

    @Override // io.grpc.internal.ServerStream
    public final void setDecompressor(Decompressor decompressor) {
        transportState().setDecompressor((Decompressor) Preconditions.checkNotNull(decompressor, "decompressor"));
    }

    @Override // io.grpc.internal.ServerStream
    public Attributes getAttributes() {
        return Attributes.EMPTY;
    }

    @Override // io.grpc.internal.ServerStream
    public final void setListener(ServerStreamListener serverStreamListener) {
        transportState().setListener(serverStreamListener);
    }

    @Override // io.grpc.internal.ServerStream
    public StatsTraceContext statsTraceContext() {
        return this.statsTraceCtx;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    protected static abstract class TransportState extends AbstractStream.TransportState {

        @Nullable
        private Status closedStatus;
        private boolean deframerClosed;
        private Runnable deframerClosedTask;
        private boolean endOfStream;
        private boolean immediateCloseRequested;
        private ServerStreamListener listener;
        private boolean listenerClosed;
        private final StatsTraceContext statsTraceCtx;

        /* JADX INFO: Access modifiers changed from: protected */
        public TransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
            super(i, statsTraceContext, (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer"));
            this.endOfStream = false;
            this.deframerClosed = false;
            this.immediateCloseRequested = false;
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        }

        public final void setListener(ServerStreamListener serverStreamListener) {
            Preconditions.checkState(this.listener == null, "setListener should be called only once");
            this.listener = (ServerStreamListener) Preconditions.checkNotNull(serverStreamListener, "listener");
        }

        @Override // io.grpc.internal.AbstractStream.TransportState
        public final void onStreamAllocated() {
            super.onStreamAllocated();
            getTransportTracer().reportRemoteStreamStarted();
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void deframerClosed(boolean z) {
            this.deframerClosed = true;
            if (this.endOfStream) {
                if (!this.immediateCloseRequested && z) {
                    deframeFailed(Status.INTERNAL.withDescription("Encountered end-of-stream mid-frame").asRuntimeException());
                    this.deframerClosedTask = null;
                    return;
                }
                this.listener.halfClosed();
            }
            Runnable runnable = this.deframerClosedTask;
            if (runnable != null) {
                runnable.run();
                this.deframerClosedTask = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.internal.AbstractStream.TransportState
        public ServerStreamListener listener() {
            return this.listener;
        }

        public void inboundDataReceived(ReadableBuffer readableBuffer, boolean z) {
            Preconditions.checkState(!this.endOfStream, "Past end of stream");
            deframe(readableBuffer);
            if (z) {
                this.endOfStream = true;
                closeDeframer(false);
            }
        }

        public final void transportReportStatus(final Status status) {
            Preconditions.checkArgument(!status.isOk(), "status must not be OK");
            if (this.deframerClosed) {
                this.deframerClosedTask = null;
                closeListener(status);
            } else {
                this.deframerClosedTask = new Runnable() { // from class: io.grpc.internal.AbstractServerStream.TransportState.1
                    @Override // java.lang.Runnable
                    public void run() {
                        TransportState.this.closeListener(status);
                    }
                };
                this.immediateCloseRequested = true;
                closeDeframer(true);
            }
        }

        public void complete() {
            if (this.deframerClosed) {
                this.deframerClosedTask = null;
                closeListener(Status.f8305OK);
            } else {
                this.deframerClosedTask = new Runnable() { // from class: io.grpc.internal.AbstractServerStream.TransportState.2
                    @Override // java.lang.Runnable
                    public void run() {
                        TransportState.this.closeListener(Status.f8305OK);
                    }
                };
                this.immediateCloseRequested = true;
                closeDeframer(true);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void closeListener(Status status) {
            Preconditions.checkState((status.isOk() && this.closedStatus == null) ? false : true);
            if (this.listenerClosed) {
                return;
            }
            if (!status.isOk()) {
                this.statsTraceCtx.streamClosed(status);
                getTransportTracer().reportStreamClosed(false);
            } else {
                this.statsTraceCtx.streamClosed(this.closedStatus);
                getTransportTracer().reportStreamClosed(this.closedStatus.isOk());
            }
            this.listenerClosed = true;
            onStreamDeallocated();
            listener().closed(status);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setClosedStatus(Status status) {
            Preconditions.checkState(this.closedStatus == null, "closedStatus can only be set once");
            this.closedStatus = status;
        }
    }
}
