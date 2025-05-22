package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.Decompressor;
import io.grpc.internal.ApplicationThreadDeframer;
import io.grpc.internal.MessageDeframer;
import io.grpc.internal.StreamListener;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class AbstractStream implements Stream {
    protected abstract Framer framer();

    protected abstract TransportState transportState();

    @Override // io.grpc.internal.Stream
    public void optimizeForDirectExecutor() {
        transportState().optimizeForDirectExecutor();
    }

    @Override // io.grpc.internal.Stream
    public final void setMessageCompression(boolean z) {
        framer().setMessageCompression(z);
    }

    @Override // io.grpc.internal.Stream
    public final void request(int i) {
        transportState().requestMessagesFromDeframer(i);
    }

    @Override // io.grpc.internal.Stream
    public final void writeMessage(InputStream inputStream) {
        Preconditions.checkNotNull(inputStream, "message");
        try {
            if (!framer().isClosed()) {
                framer().writePayload(inputStream);
            }
        } finally {
            GrpcUtil.closeQuietly(inputStream);
        }
    }

    @Override // io.grpc.internal.Stream
    public final void flush() {
        if (framer().isClosed()) {
            return;
        }
        framer().flush();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void endOfMessages() {
        framer().close();
    }

    @Override // io.grpc.internal.Stream
    public final void setCompressor(Compressor compressor) {
        framer().setCompressor((Compressor) Preconditions.checkNotNull(compressor, "compressor"));
    }

    @Override // io.grpc.internal.Stream
    public boolean isReady() {
        if (framer().isClosed()) {
            return false;
        }
        return transportState().isReady();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void onSendingBytes(int i) {
        transportState().onSendingBytes(i);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class TransportState implements ApplicationThreadDeframer.TransportExecutor, MessageDeframer.Listener {
        public static final int DEFAULT_ONREADY_THRESHOLD = 32768;
        private boolean allocated;
        private boolean deallocated;
        private Deframer deframer;
        private int numSentBytesQueued;
        private final Object onReadyLock = new Object();
        private final MessageDeframer rawDeframer;
        private final StatsTraceContext statsTraceCtx;
        private final TransportTracer transportTracer;

        protected abstract StreamListener listener();

        /* JADX INFO: Access modifiers changed from: protected */
        public TransportState(int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
            this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
            this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
            this.rawDeframer = new MessageDeframer(this, Codec.Identity.NONE, i, statsTraceContext, transportTracer);
            this.deframer = this.rawDeframer;
        }

        final void optimizeForDirectExecutor() {
            this.rawDeframer.setListener(this);
            this.deframer = this.rawDeframer;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
            this.rawDeframer.setFullStreamDecompressor(gzipInflatingBuffer);
            this.deframer = new ApplicationThreadDeframer(this, this, this.rawDeframer);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final void setMaxInboundMessageSize(int i) {
            this.deframer.setMaxInboundMessageSize(i);
        }

        @Override // io.grpc.internal.MessageDeframer.Listener
        public void messagesAvailable(StreamListener.MessageProducer messageProducer) {
            listener().messagesAvailable(messageProducer);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void closeDeframer(boolean z) {
            if (z) {
                this.deframer.close();
            } else {
                this.deframer.closeWhenComplete();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void deframe(ReadableBuffer readableBuffer) {
            try {
                this.deframer.deframe(readableBuffer);
            } catch (Throwable th) {
                deframeFailed(th);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void requestMessagesFromDeframer(final int i) {
            if (this.deframer instanceof ThreadOptimizedDeframer) {
                PerfMark.startTask("AbstractStream.request");
                try {
                    this.deframer.request(i);
                    return;
                } finally {
                    PerfMark.stopTask("AbstractStream.request");
                }
            }
            final Link linkOut = PerfMark.linkOut();
            runOnTransportThread(new Runnable() { // from class: io.grpc.internal.AbstractStream.TransportState.1RequestRunnable
                @Override // java.lang.Runnable
                public void run() {
                    PerfMark.startTask("AbstractStream.request");
                    PerfMark.linkIn(linkOut);
                    try {
                        TransportState.this.deframer.request(i);
                    } finally {
                        try {
                        } finally {
                        }
                    }
                }
            });
        }

        public final void requestMessagesFromDeframerForTesting(int i) {
            requestMessagesFromDeframer(i);
        }

        public final StatsTraceContext getStatsTraceContext() {
            return this.statsTraceCtx;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void setDecompressor(Decompressor decompressor) {
            this.deframer.setDecompressor(decompressor);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isReady() {
            boolean z;
            synchronized (this.onReadyLock) {
                z = this.allocated && this.numSentBytesQueued < 32768 && !this.deallocated;
            }
            return z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public void onStreamAllocated() {
            Preconditions.checkState(listener() != null);
            synchronized (this.onReadyLock) {
                Preconditions.checkState(this.allocated ? false : true, "Already allocated");
                this.allocated = true;
            }
            notifyIfReady();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final void onStreamDeallocated() {
            synchronized (this.onReadyLock) {
                this.deallocated = true;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onSendingBytes(int i) {
            synchronized (this.onReadyLock) {
                this.numSentBytesQueued += i;
            }
        }

        public final void onSentBytes(int i) {
            boolean z;
            synchronized (this.onReadyLock) {
                Preconditions.checkState(this.allocated, "onStreamAllocated was not called, but it seems the stream is active");
                z = true;
                boolean z2 = this.numSentBytesQueued < 32768;
                this.numSentBytesQueued -= i;
                boolean z3 = this.numSentBytesQueued < 32768;
                if (z2 || !z3) {
                    z = false;
                }
            }
            if (z) {
                notifyIfReady();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public TransportTracer getTransportTracer() {
            return this.transportTracer;
        }

        private void notifyIfReady() {
            boolean isReady;
            synchronized (this.onReadyLock) {
                isReady = isReady();
            }
            if (isReady) {
                listener().onReady();
            }
        }
    }
}
