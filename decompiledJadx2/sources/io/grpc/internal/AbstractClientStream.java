package io.grpc.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import io.grpc.CallOptions;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.Deadline;
import io.grpc.Decompressor;
import io.grpc.DecompressorRegistry;
import io.grpc.Grpc;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.AbstractStream;
import io.grpc.internal.ClientStreamListener;
import io.grpc.internal.MessageFramer;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractClientStream extends AbstractStream implements ClientStream, MessageFramer.Sink {
    private static final Logger log = Logger.getLogger(AbstractClientStream.class.getName());
    private volatile boolean cancelled;
    private final Framer framer;
    private Metadata headers;
    private boolean shouldBeCountedForInUse;
    private final TransportTracer transportTracer;
    private boolean useGet;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    protected interface Sink {
        void cancel(Status status);

        void writeFrame(@Nullable WritableBuffer writableBuffer, boolean z, boolean z2, int i);

        void writeHeaders(Metadata metadata, @Nullable byte[] bArr);
    }

    protected abstract Sink abstractClientStreamSink();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.grpc.internal.AbstractStream
    public abstract TransportState transportState();

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractClientStream(WritableBufferAllocator writableBufferAllocator, StatsTraceContext statsTraceContext, TransportTracer transportTracer, Metadata metadata, CallOptions callOptions, boolean z) {
        Preconditions.checkNotNull(metadata, "headers");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
        this.shouldBeCountedForInUse = GrpcUtil.shouldBeCountedForInUse(callOptions);
        this.useGet = z;
        if (!z) {
            this.framer = new MessageFramer(this, writableBufferAllocator, statsTraceContext);
            this.headers = metadata;
        } else {
            this.framer = new GetFramer(metadata, statsTraceContext);
        }
    }

    @Override // io.grpc.internal.ClientStream
    public void setDeadline(Deadline deadline) {
        this.headers.discardAll(GrpcUtil.TIMEOUT_KEY);
        this.headers.put(GrpcUtil.TIMEOUT_KEY, Long.valueOf(Math.max(0L, deadline.timeRemaining(TimeUnit.NANOSECONDS))));
    }

    @Override // io.grpc.internal.ClientStream
    public void setMaxOutboundMessageSize(int i) {
        this.framer.setMaxOutboundMessageSize(i);
    }

    @Override // io.grpc.internal.ClientStream
    public void setMaxInboundMessageSize(int i) {
        transportState().setMaxInboundMessageSize(i);
    }

    @Override // io.grpc.internal.ClientStream
    public final void setFullStreamDecompression(boolean z) {
        transportState().setFullStreamDecompression(z);
    }

    @Override // io.grpc.internal.ClientStream
    public final void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
        transportState().setDecompressorRegistry(decompressorRegistry);
    }

    @Override // io.grpc.internal.ClientStream
    public final void start(ClientStreamListener clientStreamListener) {
        transportState().setListener(clientStreamListener);
        if (this.useGet) {
            return;
        }
        abstractClientStreamSink().writeHeaders(this.headers, null);
        this.headers = null;
    }

    @Override // io.grpc.internal.AbstractStream
    protected final Framer framer() {
        return this.framer;
    }

    public final boolean shouldBeCountedForInUse() {
        return this.shouldBeCountedForInUse;
    }

    @Override // io.grpc.internal.MessageFramer.Sink
    public final void deliverFrame(WritableBuffer writableBuffer, boolean z, boolean z2, int i) {
        Preconditions.checkArgument(writableBuffer != null || z, "null frame before EOS");
        abstractClientStreamSink().writeFrame(writableBuffer, z, z2, i);
    }

    @Override // io.grpc.internal.ClientStream
    public final void halfClose() {
        if (transportState().isOutboundClosed()) {
            return;
        }
        transportState().setOutboundClosed();
        endOfMessages();
    }

    @Override // io.grpc.internal.ClientStream
    public final void cancel(Status status) {
        Preconditions.checkArgument(!status.isOk(), "Should not cancel with OK status");
        this.cancelled = true;
        abstractClientStreamSink().cancel(status);
    }

    @Override // io.grpc.internal.AbstractStream, io.grpc.internal.Stream
    public final boolean isReady() {
        return super.isReady() && !this.cancelled;
    }

    @Override // io.grpc.internal.ClientStream
    public final void appendTimeoutInsight(InsightBuilder insightBuilder) {
        insightBuilder.appendKeyValue("remote_addr", getAttributes().get(Grpc.TRANSPORT_ATTR_REMOTE_ADDR));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public TransportTracer getTransportTracer() {
        return this.transportTracer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class TransportState extends AbstractStream.TransportState {
        private DecompressorRegistry decompressorRegistry;
        private boolean deframerClosed;
        private Runnable deframerClosedTask;
        private boolean fullStreamDecompression;
        private ClientStreamListener listener;
        private boolean listenerClosed;
        private volatile boolean outboundClosed;
        private final StatsTraceContext statsTraceCtx;
        private boolean statusReported;
        private boolean statusReportedIsOk;

        /* JADX INFO: Access modifiers changed from: protected */
        public TransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
            super(i, statsTraceContext, transportTracer);
            this.decompressorRegistry = DecompressorRegistry.getDefaultInstance();
            this.deframerClosed = false;
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setFullStreamDecompression(boolean z) {
            this.fullStreamDecompression = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setDecompressorRegistry(DecompressorRegistry decompressorRegistry) {
            Preconditions.checkState(this.listener == null, "Already called start");
            this.decompressorRegistry = (DecompressorRegistry) Preconditions.checkNotNull(decompressorRegistry, "decompressorRegistry");
        }

        public final void setListener(ClientStreamListener clientStreamListener) {
            Preconditions.checkState(this.listener == null, "Already called setListener");
            this.listener = (ClientStreamListener) Preconditions.checkNotNull(clientStreamListener, "listener");
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void deframerClosed(boolean z) {
            Preconditions.checkState(this.statusReported, "status should have been reported on deframer closed");
            this.deframerClosed = true;
            if (this.statusReportedIsOk && z) {
                transportReportStatus(Status.INTERNAL.withDescription("Encountered end-of-stream mid-frame"), true, new Metadata());
            }
            Runnable runnable = this.deframerClosedTask;
            if (runnable != null) {
                runnable.run();
                this.deframerClosedTask = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.grpc.internal.AbstractStream.TransportState
        public final ClientStreamListener listener() {
            return this.listener;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void setOutboundClosed() {
            this.outboundClosed = true;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final boolean isOutboundClosed() {
            return this.outboundClosed;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Removed duplicated region for block: B:9:0x005a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void inboundHeadersReceived(Metadata metadata) {
            boolean z;
            String str;
            Preconditions.checkState(!this.statusReported, "Received headers on closed stream");
            this.statsTraceCtx.clientInboundHeaders();
            String str2 = (String) metadata.get(GrpcUtil.CONTENT_ENCODING_KEY);
            if (this.fullStreamDecompression && str2 != null) {
                if (str2.equalsIgnoreCase("gzip")) {
                    setFullStreamDecompressor(new GzipInflatingBuffer());
                    z = true;
                    str = (String) metadata.get(GrpcUtil.MESSAGE_ENCODING_KEY);
                    if (str != null) {
                        Decompressor lookupDecompressor = this.decompressorRegistry.lookupDecompressor(str);
                        if (lookupDecompressor == null) {
                            deframeFailed(Status.INTERNAL.withDescription(String.format("Can't find decompressor for %s", str)).asRuntimeException());
                            return;
                        } else if (lookupDecompressor != Codec.Identity.NONE) {
                            if (z) {
                                deframeFailed(Status.INTERNAL.withDescription(String.format("Full stream and gRPC message encoding cannot both be set", new Object[0])).asRuntimeException());
                                return;
                            }
                            setDecompressor(lookupDecompressor);
                        }
                    }
                    listener().headersRead(metadata);
                }
                if (!str2.equalsIgnoreCase("identity")) {
                    deframeFailed(Status.INTERNAL.withDescription(String.format("Can't find full stream decompressor for %s", str2)).asRuntimeException());
                    return;
                }
            }
            z = false;
            str = (String) metadata.get(GrpcUtil.MESSAGE_ENCODING_KEY);
            if (str != null) {
            }
            listener().headersRead(metadata);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void inboundDataReceived(ReadableBuffer readableBuffer) {
            Preconditions.checkNotNull(readableBuffer, TypedValues.Attributes.S_FRAME);
            try {
                if (this.statusReported) {
                    AbstractClientStream.log.log(Level.INFO, "Received data on closed stream");
                    readableBuffer.close();
                } else {
                    deframe(readableBuffer);
                }
            } catch (Throwable th) {
                if (1 != 0) {
                    readableBuffer.close();
                }
                throw th;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void inboundTrailersReceived(Metadata metadata, Status status) {
            Preconditions.checkNotNull(status, "status");
            Preconditions.checkNotNull(metadata, "trailers");
            if (this.statusReported) {
                AbstractClientStream.log.log(Level.INFO, "Received trailers on closed stream:\n {1}\n {2}", new Object[]{status, metadata});
            } else {
                this.statsTraceCtx.clientInboundTrailers(metadata);
                transportReportStatus(status, false, metadata);
            }
        }

        public final void transportReportStatus(Status status, boolean z, Metadata metadata) {
            transportReportStatus(status, ClientStreamListener.RpcProgress.PROCESSED, z, metadata);
        }

        public final void transportReportStatus(final Status status, final ClientStreamListener.RpcProgress rpcProgress, boolean z, final Metadata metadata) {
            Preconditions.checkNotNull(status, "status");
            Preconditions.checkNotNull(metadata, "trailers");
            if (!this.statusReported || z) {
                this.statusReported = true;
                this.statusReportedIsOk = status.isOk();
                onStreamDeallocated();
                if (this.deframerClosed) {
                    this.deframerClosedTask = null;
                    closeListener(status, rpcProgress, metadata);
                } else {
                    this.deframerClosedTask = new Runnable() { // from class: io.grpc.internal.AbstractClientStream.TransportState.1
                        @Override // java.lang.Runnable
                        public void run() {
                            TransportState.this.closeListener(status, rpcProgress, metadata);
                        }
                    };
                    closeDeframer(z);
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void closeListener(Status status, ClientStreamListener.RpcProgress rpcProgress, Metadata metadata) {
            if (this.listenerClosed) {
                return;
            }
            this.listenerClosed = true;
            this.statsTraceCtx.streamClosed(status);
            listener().closed(status, rpcProgress, metadata);
            if (getTransportTracer() != null) {
                getTransportTracer().reportStreamClosed(status.isOk());
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private class GetFramer implements Framer {
        private boolean closed;
        private Metadata headers;
        private byte[] payload;
        private final StatsTraceContext statsTraceCtx;

        @Override // io.grpc.internal.Framer
        public void flush() {
        }

        @Override // io.grpc.internal.Framer
        public Framer setCompressor(Compressor compressor) {
            return this;
        }

        @Override // io.grpc.internal.Framer
        public void setMaxOutboundMessageSize(int i) {
        }

        @Override // io.grpc.internal.Framer
        public Framer setMessageCompression(boolean z) {
            return this;
        }

        public GetFramer(Metadata metadata, StatsTraceContext statsTraceContext) {
            this.headers = (Metadata) Preconditions.checkNotNull(metadata, "headers");
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        }

        @Override // io.grpc.internal.Framer
        public void writePayload(InputStream inputStream) {
            Preconditions.checkState(this.payload == null, "writePayload should not be called multiple times");
            try {
                this.payload = ByteStreams.toByteArray(inputStream);
                this.statsTraceCtx.outboundMessage(0);
                StatsTraceContext statsTraceContext = this.statsTraceCtx;
                byte[] bArr = this.payload;
                statsTraceContext.outboundMessageSent(0, bArr.length, bArr.length);
                this.statsTraceCtx.outboundUncompressedSize(this.payload.length);
                this.statsTraceCtx.outboundWireSize(this.payload.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override // io.grpc.internal.Framer
        public boolean isClosed() {
            return this.closed;
        }

        @Override // io.grpc.internal.Framer
        public void close() {
            this.closed = true;
            Preconditions.checkState(this.payload != null, "Lack of request message. GET request is only supported for unary requests");
            AbstractClientStream.this.abstractClientStreamSink().writeHeaders(this.headers, this.payload);
            this.payload = null;
            this.headers = null;
        }

        @Override // io.grpc.internal.Framer
        public void dispose() {
            this.closed = true;
            this.payload = null;
            this.headers = null;
        }
    }
}
