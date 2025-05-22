package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Codec;
import io.grpc.Decompressor;
import io.grpc.Status;
import io.grpc.internal.StreamListener;
import java.io.Closeable;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class MessageDeframer implements Closeable, Deframer {
    private static final int COMPRESSED_FLAG_MASK = 1;
    private static final int HEADER_LENGTH = 5;
    private static final int MAX_BUFFER_SIZE = 2097152;
    private static final int RESERVED_MASK = 254;
    private boolean compressedFlag;
    private Decompressor decompressor;
    private GzipInflatingBuffer fullStreamDecompressor;
    private int inboundBodyWireSize;
    private byte[] inflatedBuffer;
    private int inflatedIndex;
    private Listener listener;
    private int maxInboundMessageSize;
    private CompositeReadableBuffer nextFrame;
    private long pendingDeliveries;
    private final StatsTraceContext statsTraceCtx;
    private final TransportTracer transportTracer;
    private State state = State.HEADER;
    private int requiredLength = 5;
    private CompositeReadableBuffer unprocessed = new CompositeReadableBuffer();
    private boolean inDelivery = false;
    private int currentMessageSeqNo = -1;
    private boolean closeWhenComplete = false;
    private volatile boolean stopDelivery = false;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Listener {
        void bytesRead(int i);

        void deframeFailed(Throwable th);

        void deframerClosed(boolean z);

        void messagesAvailable(StreamListener.MessageProducer messageProducer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum State {
        HEADER,
        BODY
    }

    public MessageDeframer(Listener listener, Decompressor decompressor, int i, StatsTraceContext statsTraceContext, TransportTracer transportTracer) {
        this.listener = (Listener) Preconditions.checkNotNull(listener, "sink");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor, "decompressor");
        this.maxInboundMessageSize = i;
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
        this.transportTracer = (TransportTracer) Preconditions.checkNotNull(transportTracer, "transportTracer");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setListener(Listener listener) {
        this.listener = listener;
    }

    @Override // io.grpc.internal.Deframer
    public void setMaxInboundMessageSize(int i) {
        this.maxInboundMessageSize = i;
    }

    @Override // io.grpc.internal.Deframer
    public void setDecompressor(Decompressor decompressor) {
        Preconditions.checkState(this.fullStreamDecompressor == null, "Already set full stream decompressor");
        this.decompressor = (Decompressor) Preconditions.checkNotNull(decompressor, "Can't pass an empty decompressor");
    }

    @Override // io.grpc.internal.Deframer
    public void setFullStreamDecompressor(GzipInflatingBuffer gzipInflatingBuffer) {
        Preconditions.checkState(this.decompressor == Codec.Identity.NONE, "per-message decompressor already set");
        Preconditions.checkState(this.fullStreamDecompressor == null, "full stream decompressor already set");
        this.fullStreamDecompressor = (GzipInflatingBuffer) Preconditions.checkNotNull(gzipInflatingBuffer, "Can't pass a null full stream decompressor");
        this.unprocessed = null;
    }

    @Override // io.grpc.internal.Deframer
    public void request(int i) {
        Preconditions.checkArgument(i > 0, "numMessages must be > 0");
        if (isClosed()) {
            return;
        }
        this.pendingDeliveries += i;
        deliver();
    }

    @Override // io.grpc.internal.Deframer
    public void deframe(ReadableBuffer readableBuffer) {
        Preconditions.checkNotNull(readableBuffer, "data");
        boolean z = true;
        try {
            if (!isClosedOrScheduledToClose()) {
                if (this.fullStreamDecompressor != null) {
                    this.fullStreamDecompressor.addGzippedBytes(readableBuffer);
                } else {
                    this.unprocessed.addBuffer(readableBuffer);
                }
                z = false;
                deliver();
            }
        } finally {
            if (z) {
                readableBuffer.close();
            }
        }
    }

    @Override // io.grpc.internal.Deframer
    public void closeWhenComplete() {
        if (isClosed()) {
            return;
        }
        if (isStalled()) {
            close();
        } else {
            this.closeWhenComplete = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void stopDelivery() {
        this.stopDelivery = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean hasPendingDeliveries() {
        return this.pendingDeliveries != 0;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable, io.grpc.internal.Deframer
    public void close() {
        if (isClosed()) {
            return;
        }
        CompositeReadableBuffer compositeReadableBuffer = this.nextFrame;
        boolean z = compositeReadableBuffer != null && compositeReadableBuffer.readableBytes() > 0;
        try {
            if (this.fullStreamDecompressor != null) {
                if (!z && !this.fullStreamDecompressor.hasPartialData()) {
                    z = false;
                    this.fullStreamDecompressor.close();
                }
                z = true;
                this.fullStreamDecompressor.close();
            }
            if (this.unprocessed != null) {
                this.unprocessed.close();
            }
            if (this.nextFrame != null) {
                this.nextFrame.close();
            }
            this.fullStreamDecompressor = null;
            this.unprocessed = null;
            this.nextFrame = null;
            this.listener.deframerClosed(z);
        } catch (Throwable th) {
            this.fullStreamDecompressor = null;
            this.unprocessed = null;
            this.nextFrame = null;
            throw th;
        }
    }

    public boolean isClosed() {
        return this.unprocessed == null && this.fullStreamDecompressor == null;
    }

    private boolean isClosedOrScheduledToClose() {
        return isClosed() || this.closeWhenComplete;
    }

    private boolean isStalled() {
        GzipInflatingBuffer gzipInflatingBuffer = this.fullStreamDecompressor;
        if (gzipInflatingBuffer != null) {
            return gzipInflatingBuffer.isStalled();
        }
        return this.unprocessed.readableBytes() == 0;
    }

    private void deliver() {
        if (this.inDelivery) {
            return;
        }
        this.inDelivery = true;
        while (true) {
            try {
                if (this.stopDelivery || this.pendingDeliveries <= 0 || !readRequiredBytes()) {
                    break;
                }
                int i = C61541.$SwitchMap$io$grpc$internal$MessageDeframer$State[this.state.ordinal()];
                if (i == 1) {
                    processHeader();
                } else if (i == 2) {
                    processBody();
                    this.pendingDeliveries--;
                } else {
                    throw new AssertionError("Invalid state: " + this.state);
                }
            } finally {
                this.inDelivery = false;
            }
        }
        if (this.stopDelivery) {
            close();
            return;
        }
        if (this.closeWhenComplete && isStalled()) {
            close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* renamed from: io.grpc.internal.MessageDeframer$1 */
    /* loaded from: classes7.dex */
    public static /* synthetic */ class C61541 {
        static final /* synthetic */ int[] $SwitchMap$io$grpc$internal$MessageDeframer$State = new int[State.values().length];

        static {
            try {
                $SwitchMap$io$grpc$internal$MessageDeframer$State[State.HEADER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$io$grpc$internal$MessageDeframer$State[State.BODY.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private boolean readRequiredBytes() {
        int i;
        Throwable th;
        int i2;
        try {
            if (this.nextFrame == null) {
                this.nextFrame = new CompositeReadableBuffer();
            }
            i2 = 0;
            i = 0;
            while (true) {
                try {
                    int readableBytes = this.requiredLength - this.nextFrame.readableBytes();
                    if (readableBytes <= 0) {
                        if (i2 > 0) {
                            this.listener.bytesRead(i2);
                            if (this.state == State.BODY) {
                                if (this.fullStreamDecompressor != null) {
                                    this.statsTraceCtx.inboundWireSize(i);
                                    this.inboundBodyWireSize += i;
                                } else {
                                    this.statsTraceCtx.inboundWireSize(i2);
                                    this.inboundBodyWireSize += i2;
                                }
                            }
                        }
                        return true;
                    }
                    if (this.fullStreamDecompressor != null) {
                        try {
                            try {
                                if (this.inflatedBuffer == null || this.inflatedIndex == this.inflatedBuffer.length) {
                                    this.inflatedBuffer = new byte[Math.min(readableBytes, 2097152)];
                                    this.inflatedIndex = 0;
                                }
                                int inflateBytes = this.fullStreamDecompressor.inflateBytes(this.inflatedBuffer, this.inflatedIndex, Math.min(readableBytes, this.inflatedBuffer.length - this.inflatedIndex));
                                i2 += this.fullStreamDecompressor.getAndResetBytesConsumed();
                                i += this.fullStreamDecompressor.getAndResetDeflatedBytesConsumed();
                                if (inflateBytes == 0) {
                                    if (i2 > 0) {
                                        this.listener.bytesRead(i2);
                                        if (this.state == State.BODY) {
                                            if (this.fullStreamDecompressor != null) {
                                                this.statsTraceCtx.inboundWireSize(i);
                                                this.inboundBodyWireSize += i;
                                            } else {
                                                this.statsTraceCtx.inboundWireSize(i2);
                                                this.inboundBodyWireSize += i2;
                                            }
                                        }
                                    }
                                    return false;
                                }
                                this.nextFrame.addBuffer(ReadableBuffers.wrap(this.inflatedBuffer, this.inflatedIndex, inflateBytes));
                                this.inflatedIndex += inflateBytes;
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } catch (DataFormatException e2) {
                            throw new RuntimeException(e2);
                        }
                    } else {
                        if (this.unprocessed.readableBytes() == 0) {
                            if (i2 > 0) {
                                this.listener.bytesRead(i2);
                                if (this.state == State.BODY) {
                                    if (this.fullStreamDecompressor != null) {
                                        this.statsTraceCtx.inboundWireSize(i);
                                        this.inboundBodyWireSize += i;
                                    } else {
                                        this.statsTraceCtx.inboundWireSize(i2);
                                        this.inboundBodyWireSize += i2;
                                    }
                                }
                            }
                            return false;
                        }
                        int min = Math.min(readableBytes, this.unprocessed.readableBytes());
                        i2 += min;
                        this.nextFrame.addBuffer(this.unprocessed.readBytes(min));
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (i2 > 0) {
                        this.listener.bytesRead(i2);
                        if (this.state == State.BODY) {
                            if (this.fullStreamDecompressor != null) {
                                this.statsTraceCtx.inboundWireSize(i);
                                this.inboundBodyWireSize += i;
                            } else {
                                this.statsTraceCtx.inboundWireSize(i2);
                                this.inboundBodyWireSize += i2;
                            }
                        }
                    }
                    throw th;
                }
            }
        } catch (Throwable th3) {
            i = 0;
            th = th3;
            i2 = 0;
        }
    }

    private void processHeader() {
        int readUnsignedByte = this.nextFrame.readUnsignedByte();
        if ((readUnsignedByte & 254) != 0) {
            throw Status.INTERNAL.withDescription("gRPC frame header malformed: reserved bits not zero").asRuntimeException();
        }
        this.compressedFlag = (readUnsignedByte & 1) != 0;
        this.requiredLength = this.nextFrame.readInt();
        int i = this.requiredLength;
        if (i < 0 || i > this.maxInboundMessageSize) {
            throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("gRPC message exceeds maximum size %d: %d", Integer.valueOf(this.maxInboundMessageSize), Integer.valueOf(this.requiredLength))).asRuntimeException();
        }
        this.currentMessageSeqNo++;
        this.statsTraceCtx.inboundMessage(this.currentMessageSeqNo);
        this.transportTracer.reportMessageReceived();
        this.state = State.BODY;
    }

    private void processBody() {
        this.statsTraceCtx.inboundMessageRead(this.currentMessageSeqNo, this.inboundBodyWireSize, -1L);
        this.inboundBodyWireSize = 0;
        InputStream compressedBody = this.compressedFlag ? getCompressedBody() : getUncompressedBody();
        this.nextFrame = null;
        this.listener.messagesAvailable(new SingleMessageProducer(compressedBody, null));
        this.state = State.HEADER;
        this.requiredLength = 5;
    }

    private InputStream getUncompressedBody() {
        this.statsTraceCtx.inboundUncompressedSize(this.nextFrame.readableBytes());
        return ReadableBuffers.openStream(this.nextFrame, true);
    }

    private InputStream getCompressedBody() {
        if (this.decompressor == Codec.Identity.NONE) {
            throw Status.INTERNAL.withDescription("Can't decode compressed gRPC message as compression not configured").asRuntimeException();
        }
        try {
            return new SizeEnforcingInputStream(this.decompressor.decompress(ReadableBuffers.openStream(this.nextFrame, true)), this.maxInboundMessageSize, this.statsTraceCtx);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class SizeEnforcingInputStream extends FilterInputStream {
        private long count;
        private long mark;
        private long maxCount;
        private final int maxMessageSize;
        private final StatsTraceContext statsTraceCtx;

        SizeEnforcingInputStream(InputStream inputStream, int i, StatsTraceContext statsTraceContext) {
            super(inputStream);
            this.mark = -1L;
            this.maxMessageSize = i;
            this.statsTraceCtx = statsTraceContext;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read() throws IOException {
            int read = this.in.read();
            if (read != -1) {
                this.count++;
            }
            verifySize();
            reportCount();
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public int read(byte[] bArr, int i, int i2) throws IOException {
            int read = this.in.read(bArr, i, i2);
            if (read != -1) {
                this.count += read;
            }
            verifySize();
            reportCount();
            return read;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) throws IOException {
            long skip = this.in.skip(j);
            this.count += skip;
            verifySize();
            reportCount();
            return skip;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void mark(int i) {
            this.in.mark(i);
            this.mark = this.count;
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public synchronized void reset() throws IOException {
            if (!this.in.markSupported()) {
                throw new IOException("Mark not supported");
            }
            if (this.mark == -1) {
                throw new IOException("Mark not set");
            }
            this.in.reset();
            this.count = this.mark;
        }

        private void reportCount() {
            long j = this.count;
            long j2 = this.maxCount;
            if (j > j2) {
                this.statsTraceCtx.inboundUncompressedSize(j - j2);
                this.maxCount = this.count;
            }
        }

        private void verifySize() {
            if (this.count > this.maxMessageSize) {
                throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("Compressed gRPC message exceeds maximum size %d: %d bytes read", Integer.valueOf(this.maxMessageSize), Long.valueOf(this.count))).asRuntimeException();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class SingleMessageProducer implements StreamListener.MessageProducer {
        private InputStream message;

        /* synthetic */ SingleMessageProducer(InputStream inputStream, C61541 c61541) {
            this(inputStream);
        }

        private SingleMessageProducer(InputStream inputStream) {
            this.message = inputStream;
        }

        @Override // io.grpc.internal.StreamListener.MessageProducer
        @Nullable
        public InputStream next() {
            InputStream inputStream = this.message;
            this.message = null;
            return inputStream;
        }
    }
}
