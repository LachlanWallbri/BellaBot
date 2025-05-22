package org.jboss.netty.handler.stream;

import java.io.InputStream;
import java.io.PushbackInputStream;
import org.jboss.netty.buffer.ChannelBuffers;

/* loaded from: classes7.dex */
public class ChunkedStream implements ChunkedInput {
    static final int DEFAULT_CHUNK_SIZE = 8192;
    private final int chunkSize;

    /* renamed from: in */
    private final PushbackInputStream f10050in;
    private long offset;

    public ChunkedStream(InputStream inputStream) {
        this(inputStream, 8192);
    }

    public ChunkedStream(InputStream inputStream, int i) {
        if (inputStream == null) {
            throw new NullPointerException("in");
        }
        if (i <= 0) {
            throw new IllegalArgumentException("chunkSize: " + i + " (expected: a positive integer)");
        }
        if (inputStream instanceof PushbackInputStream) {
            this.f10050in = (PushbackInputStream) inputStream;
        } else {
            this.f10050in = new PushbackInputStream(inputStream);
        }
        this.chunkSize = i;
    }

    public long getTransferredBytes() {
        return this.offset;
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public boolean hasNextChunk() throws Exception {
        int read = this.f10050in.read();
        if (read < 0) {
            return false;
        }
        this.f10050in.unread(read);
        return true;
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public boolean isEndOfInput() throws Exception {
        return !hasNextChunk();
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public void close() throws Exception {
        this.f10050in.close();
    }

    @Override // org.jboss.netty.handler.stream.ChunkedInput
    public Object nextChunk() throws Exception {
        int min;
        if (!hasNextChunk()) {
            return null;
        }
        if (this.f10050in.available() <= 0) {
            min = this.chunkSize;
        } else {
            min = Math.min(this.chunkSize, this.f10050in.available());
        }
        byte[] bArr = new byte[min];
        int i = 0;
        do {
            int read = this.f10050in.read(bArr, i, min - i);
            if (read < 0) {
                break;
            }
            i += read;
            this.offset += read;
        } while (i != min);
        return ChannelBuffers.wrappedBuffer(bArr, 0, i);
    }
}
