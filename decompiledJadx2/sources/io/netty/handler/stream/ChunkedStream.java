package io.netty.handler.stream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import java.io.InputStream;
import java.io.PushbackInputStream;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public class ChunkedStream implements ChunkedInput<ByteBuf> {
    static final int DEFAULT_CHUNK_SIZE = 8192;
    private final int chunkSize;
    private boolean closed;

    /* renamed from: in */
    private final PushbackInputStream f8566in;
    private long offset;

    @Override // io.netty.handler.stream.ChunkedInput
    public long length() {
        return -1L;
    }

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
            this.f8566in = (PushbackInputStream) inputStream;
        } else {
            this.f8566in = new PushbackInputStream(inputStream);
        }
        this.chunkSize = i;
    }

    public long transferredBytes() {
        return this.offset;
    }

    @Override // io.netty.handler.stream.ChunkedInput
    public boolean isEndOfInput() throws Exception {
        int read;
        if (this.closed || (read = this.f8566in.read()) < 0) {
            return true;
        }
        this.f8566in.unread(read);
        return false;
    }

    @Override // io.netty.handler.stream.ChunkedInput
    public void close() throws Exception {
        this.closed = true;
        this.f8566in.close();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.stream.ChunkedInput
    @Deprecated
    public ByteBuf readChunk(ChannelHandlerContext channelHandlerContext) throws Exception {
        return readChunk(channelHandlerContext.alloc());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.netty.handler.stream.ChunkedInput
    public ByteBuf readChunk(ByteBufAllocator byteBufAllocator) throws Exception {
        int min;
        if (isEndOfInput()) {
            return null;
        }
        if (this.f8566in.available() <= 0) {
            min = this.chunkSize;
        } else {
            min = Math.min(this.chunkSize, this.f8566in.available());
        }
        ByteBuf buffer = byteBufAllocator.buffer(min);
        try {
            this.offset += buffer.writeBytes(this.f8566in, min);
            return buffer;
        } catch (Throwable th) {
            buffer.release();
            throw th;
        }
    }

    @Override // io.netty.handler.stream.ChunkedInput
    public long progress() {
        return this.offset;
    }
}
