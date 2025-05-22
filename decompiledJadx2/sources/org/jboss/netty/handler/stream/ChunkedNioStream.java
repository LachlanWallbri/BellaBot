package org.jboss.netty.handler.stream;

import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

/* loaded from: classes7.dex */
public class ChunkedNioStream implements ChunkedInput {
    private final ByteBuffer byteBuffer;
    private final int chunkSize;

    /* renamed from: in */
    private final ReadableByteChannel f10049in;
    private long offset;

    public ChunkedNioStream(ReadableByteChannel readableByteChannel) {
        this(readableByteChannel, 8192);
    }

    public ChunkedNioStream(ReadableByteChannel readableByteChannel, int i) {
        if (readableByteChannel == null) {
            throw new NullPointerException("in");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("chunkSize: " + i + " (expected: a positive integer)");
        }
        this.f10049in = readableByteChannel;
        this.offset = 0L;
        this.chunkSize = i;
        this.byteBuffer = ByteBuffer.allocate(i);
    }

    public long getTransferredBytes() {
        return this.offset;
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public boolean hasNextChunk() throws Exception {
        int read;
        if (this.byteBuffer.position() > 0) {
            return true;
        }
        if (!this.f10049in.isOpen() || (read = this.f10049in.read(this.byteBuffer)) < 0) {
            return false;
        }
        this.offset += read;
        return true;
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public boolean isEndOfInput() throws Exception {
        return !hasNextChunk();
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public void close() throws Exception {
        this.f10049in.close();
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public Object nextChunk() throws Exception {
        if (!hasNextChunk()) {
            return null;
        }
        int position = this.byteBuffer.position();
        do {
            int read = this.f10049in.read(this.byteBuffer);
            if (read < 0) {
                break;
            }
            position += read;
            this.offset += read;
        } while (position != this.chunkSize);
        this.byteBuffer.flip();
        ChannelBuffer copiedBuffer = ChannelBuffers.copiedBuffer(this.byteBuffer);
        this.byteBuffer.clear();
        return copiedBuffer;
    }
}
