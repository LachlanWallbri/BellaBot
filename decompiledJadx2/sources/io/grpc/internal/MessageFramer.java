package io.grpc.internal;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import io.grpc.Codec;
import io.grpc.Compressor;
import io.grpc.Drainable;
import io.grpc.KnownLength;
import io.grpc.Status;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class MessageFramer implements Framer {
    private static final byte COMPRESSED = 1;
    private static final int HEADER_LENGTH = 5;
    private static final int NO_MAX_OUTBOUND_MESSAGE_SIZE = -1;
    private static final byte UNCOMPRESSED = 0;
    private WritableBuffer buffer;
    private final WritableBufferAllocator bufferAllocator;
    private boolean closed;
    private long currentMessageWireSize;
    private int messagesBuffered;
    private final Sink sink;
    private final StatsTraceContext statsTraceCtx;
    private int maxOutboundMessageSize = -1;
    private Compressor compressor = Codec.Identity.NONE;
    private boolean messageCompression = true;
    private final OutputStreamAdapter outputStreamAdapter = new OutputStreamAdapter();
    private final ByteBuffer headerScratch = ByteBuffer.allocate(5);
    private int currentMessageSeqNo = -1;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface Sink {
        void deliverFrame(@Nullable WritableBuffer writableBuffer, boolean z, boolean z2, int i);
    }

    public MessageFramer(Sink sink, WritableBufferAllocator writableBufferAllocator, StatsTraceContext statsTraceContext) {
        this.sink = (Sink) Preconditions.checkNotNull(sink, "sink");
        this.bufferAllocator = (WritableBufferAllocator) Preconditions.checkNotNull(writableBufferAllocator, "bufferAllocator");
        this.statsTraceCtx = (StatsTraceContext) Preconditions.checkNotNull(statsTraceContext, "statsTraceCtx");
    }

    @Override // io.grpc.internal.Framer
    public MessageFramer setCompressor(Compressor compressor) {
        this.compressor = (Compressor) Preconditions.checkNotNull(compressor, "Can't pass an empty compressor");
        return this;
    }

    @Override // io.grpc.internal.Framer
    public MessageFramer setMessageCompression(boolean z) {
        this.messageCompression = z;
        return this;
    }

    @Override // io.grpc.internal.Framer
    public void setMaxOutboundMessageSize(int i) {
        Preconditions.checkState(this.maxOutboundMessageSize == -1, "max size already set");
        this.maxOutboundMessageSize = i;
    }

    @Override // io.grpc.internal.Framer
    public void writePayload(InputStream inputStream) {
        int writeUncompressed;
        verifyNotClosed();
        this.messagesBuffered++;
        this.currentMessageSeqNo++;
        this.currentMessageWireSize = 0L;
        this.statsTraceCtx.outboundMessage(this.currentMessageSeqNo);
        boolean z = this.messageCompression && this.compressor != Codec.Identity.NONE;
        try {
            int knownLength = getKnownLength(inputStream);
            if (knownLength != 0 && z) {
                writeUncompressed = writeCompressed(inputStream, knownLength);
            } else {
                writeUncompressed = writeUncompressed(inputStream, knownLength);
            }
            if (knownLength != -1 && writeUncompressed != knownLength) {
                throw Status.INTERNAL.withDescription(String.format("Message length inaccurate %s != %s", Integer.valueOf(writeUncompressed), Integer.valueOf(knownLength))).asRuntimeException();
            }
            long j = writeUncompressed;
            this.statsTraceCtx.outboundUncompressedSize(j);
            this.statsTraceCtx.outboundWireSize(this.currentMessageWireSize);
            this.statsTraceCtx.outboundMessageSent(this.currentMessageSeqNo, this.currentMessageWireSize, j);
        } catch (IOException e) {
            throw Status.INTERNAL.withDescription("Failed to frame message").withCause(e).asRuntimeException();
        } catch (RuntimeException e2) {
            throw Status.INTERNAL.withDescription("Failed to frame message").withCause(e2).asRuntimeException();
        }
    }

    private int writeUncompressed(InputStream inputStream, int i) throws IOException {
        if (i != -1) {
            this.currentMessageWireSize = i;
            return writeKnownLengthUncompressed(inputStream, i);
        }
        BufferChainOutputStream bufferChainOutputStream = new BufferChainOutputStream();
        int writeToOutputStream = writeToOutputStream(inputStream, bufferChainOutputStream);
        int i2 = this.maxOutboundMessageSize;
        if (i2 >= 0 && writeToOutputStream > i2) {
            throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("message too large %d > %d", Integer.valueOf(writeToOutputStream), Integer.valueOf(this.maxOutboundMessageSize))).asRuntimeException();
        }
        writeBufferChain(bufferChainOutputStream, false);
        return writeToOutputStream;
    }

    private int writeCompressed(InputStream inputStream, int i) throws IOException {
        BufferChainOutputStream bufferChainOutputStream = new BufferChainOutputStream();
        OutputStream compress = this.compressor.compress(bufferChainOutputStream);
        try {
            int writeToOutputStream = writeToOutputStream(inputStream, compress);
            compress.close();
            int i2 = this.maxOutboundMessageSize;
            if (i2 >= 0 && writeToOutputStream > i2) {
                throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("message too large %d > %d", Integer.valueOf(writeToOutputStream), Integer.valueOf(this.maxOutboundMessageSize))).asRuntimeException();
            }
            writeBufferChain(bufferChainOutputStream, true);
            return writeToOutputStream;
        } catch (Throwable th) {
            compress.close();
            throw th;
        }
    }

    private int getKnownLength(InputStream inputStream) throws IOException {
        if ((inputStream instanceof KnownLength) || (inputStream instanceof ByteArrayInputStream)) {
            return inputStream.available();
        }
        return -1;
    }

    private int writeKnownLengthUncompressed(InputStream inputStream, int i) throws IOException {
        int i2 = this.maxOutboundMessageSize;
        if (i2 >= 0 && i > i2) {
            throw Status.RESOURCE_EXHAUSTED.withDescription(String.format("message too large %d > %d", Integer.valueOf(i), Integer.valueOf(this.maxOutboundMessageSize))).asRuntimeException();
        }
        this.headerScratch.clear();
        this.headerScratch.put((byte) 0).putInt(i);
        if (this.buffer == null) {
            this.buffer = this.bufferAllocator.allocate(this.headerScratch.position() + i);
        }
        writeRaw(this.headerScratch.array(), 0, this.headerScratch.position());
        return writeToOutputStream(inputStream, this.outputStreamAdapter);
    }

    private void writeBufferChain(BufferChainOutputStream bufferChainOutputStream, boolean z) {
        int readableBytes = bufferChainOutputStream.readableBytes();
        this.headerScratch.clear();
        this.headerScratch.put(z ? (byte) 1 : (byte) 0).putInt(readableBytes);
        WritableBuffer allocate = this.bufferAllocator.allocate(5);
        allocate.write(this.headerScratch.array(), 0, this.headerScratch.position());
        if (readableBytes == 0) {
            this.buffer = allocate;
            return;
        }
        this.sink.deliverFrame(allocate, false, false, this.messagesBuffered - 1);
        this.messagesBuffered = 1;
        List list = bufferChainOutputStream.bufferList;
        for (int i = 0; i < list.size() - 1; i++) {
            this.sink.deliverFrame((WritableBuffer) list.get(i), false, false, 0);
        }
        this.buffer = (WritableBuffer) list.get(list.size() - 1);
        this.currentMessageWireSize = readableBytes;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static int writeToOutputStream(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream instanceof Drainable) {
            return ((Drainable) inputStream).drainTo(outputStream);
        }
        long copy = ByteStreams.copy(inputStream, outputStream);
        Preconditions.checkArgument(copy <= 2147483647L, "Message size overflow: %s", copy);
        return (int) copy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void writeRaw(byte[] bArr, int i, int i2) {
        while (i2 > 0) {
            WritableBuffer writableBuffer = this.buffer;
            if (writableBuffer != null && writableBuffer.writableBytes() == 0) {
                commitToSink(false, false);
            }
            if (this.buffer == null) {
                this.buffer = this.bufferAllocator.allocate(i2);
            }
            int min = Math.min(i2, this.buffer.writableBytes());
            this.buffer.write(bArr, i, min);
            i += min;
            i2 -= min;
        }
    }

    @Override // io.grpc.internal.Framer
    public void flush() {
        WritableBuffer writableBuffer = this.buffer;
        if (writableBuffer == null || writableBuffer.readableBytes() <= 0) {
            return;
        }
        commitToSink(false, true);
    }

    @Override // io.grpc.internal.Framer
    public boolean isClosed() {
        return this.closed;
    }

    @Override // io.grpc.internal.Framer
    public void close() {
        if (isClosed()) {
            return;
        }
        this.closed = true;
        WritableBuffer writableBuffer = this.buffer;
        if (writableBuffer != null && writableBuffer.readableBytes() == 0) {
            releaseBuffer();
        }
        commitToSink(true, true);
    }

    @Override // io.grpc.internal.Framer
    public void dispose() {
        this.closed = true;
        releaseBuffer();
    }

    private void releaseBuffer() {
        WritableBuffer writableBuffer = this.buffer;
        if (writableBuffer != null) {
            writableBuffer.release();
            this.buffer = null;
        }
    }

    private void commitToSink(boolean z, boolean z2) {
        WritableBuffer writableBuffer = this.buffer;
        this.buffer = null;
        this.sink.deliverFrame(writableBuffer, z, z2, this.messagesBuffered);
        this.messagesBuffered = 0;
    }

    private void verifyNotClosed() {
        if (isClosed()) {
            throw new IllegalStateException("Framer already closed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public class OutputStreamAdapter extends OutputStream {
        private OutputStreamAdapter() {
        }

        @Override // java.io.OutputStream
        public void write(int i) {
            write(new byte[]{(byte) i}, 0, 1);
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            MessageFramer.this.writeRaw(bArr, i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public final class BufferChainOutputStream extends OutputStream {
        private final List<WritableBuffer> bufferList;
        private WritableBuffer current;

        private BufferChainOutputStream() {
            this.bufferList = new ArrayList();
        }

        @Override // java.io.OutputStream
        public void write(int i) throws IOException {
            WritableBuffer writableBuffer = this.current;
            if (writableBuffer != null && writableBuffer.writableBytes() > 0) {
                this.current.write((byte) i);
            } else {
                write(new byte[]{(byte) i}, 0, 1);
            }
        }

        @Override // java.io.OutputStream
        public void write(byte[] bArr, int i, int i2) {
            if (this.current == null) {
                this.current = MessageFramer.this.bufferAllocator.allocate(i2);
                this.bufferList.add(this.current);
            }
            while (i2 > 0) {
                int min = Math.min(i2, this.current.writableBytes());
                if (min == 0) {
                    this.current = MessageFramer.this.bufferAllocator.allocate(Math.max(i2, this.current.readableBytes() * 2));
                    this.bufferList.add(this.current);
                } else {
                    this.current.write(bArr, i, min);
                    i += min;
                    i2 -= min;
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int readableBytes() {
            Iterator<WritableBuffer> it = this.bufferList.iterator();
            int i = 0;
            while (it.hasNext()) {
                i += it.next().readableBytes();
            }
            return i;
        }
    }
}
