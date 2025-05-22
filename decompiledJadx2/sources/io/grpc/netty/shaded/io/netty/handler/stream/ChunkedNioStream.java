package io.grpc.netty.shaded.io.netty.handler.stream;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ChunkedNioStream implements ChunkedInput<ByteBuf> {
    private final ByteBuffer byteBuffer;
    private final int chunkSize;

    /* renamed from: in */
    private final ReadableByteChannel f8427in;
    private long offset;

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public long length() {
        return -1L;
    }

    public ChunkedNioStream(ReadableByteChannel readableByteChannel) {
        this(readableByteChannel, 8192);
    }

    public ChunkedNioStream(ReadableByteChannel readableByteChannel, int i) {
        ObjectUtil.checkNotNull(readableByteChannel, "in");
        if (i <= 0) {
            throw new IllegalArgumentException("chunkSize: " + i + " (expected: a positive integer)");
        }
        this.f8427in = readableByteChannel;
        this.offset = 0L;
        this.chunkSize = i;
        this.byteBuffer = ByteBuffer.allocate(i);
    }

    public long transferredBytes() {
        return this.offset;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public boolean isEndOfInput() throws Exception {
        int read;
        if (this.byteBuffer.position() > 0) {
            return false;
        }
        if (!this.f8427in.isOpen() || (read = this.f8427in.read(this.byteBuffer)) < 0) {
            return true;
        }
        this.offset += read;
        return false;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public void close() throws Exception {
        this.f8427in.close();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    @Deprecated
    public ByteBuf readChunk(ChannelHandlerContext channelHandlerContext) throws Exception {
        return readChunk(channelHandlerContext.alloc());
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public ByteBuf readChunk(ByteBufAllocator byteBufAllocator) throws Exception {
        if (isEndOfInput()) {
            return null;
        }
        int position = this.byteBuffer.position();
        do {
            int read = this.f8427in.read(this.byteBuffer);
            if (read < 0) {
                break;
            }
            position += read;
            this.offset += read;
        } while (position != this.chunkSize);
        this.byteBuffer.flip();
        ByteBuf buffer = byteBufAllocator.buffer(this.byteBuffer.remaining());
        try {
            buffer.writeBytes(this.byteBuffer);
            this.byteBuffer.clear();
            return buffer;
        } catch (Throwable th) {
            buffer.release();
            throw th;
        }
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public long progress() {
        return this.offset;
    }
}
