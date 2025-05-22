package io.grpc.netty.shaded.io.netty.handler.stream;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.ChannelHandlerContext;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import org.simpleframework.xml.strategy.Name;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class ChunkedNioFile implements ChunkedInput<ByteBuf> {
    private final int chunkSize;
    private final long endOffset;

    /* renamed from: in */
    private final FileChannel f8426in;
    private long offset;
    private final long startOffset;

    public ChunkedNioFile(File file) throws IOException {
        this(new RandomAccessFile(file, "r").getChannel());
    }

    public ChunkedNioFile(File file, int i) throws IOException {
        this(new RandomAccessFile(file, "r").getChannel(), i);
    }

    public ChunkedNioFile(FileChannel fileChannel) throws IOException {
        this(fileChannel, 8192);
    }

    public ChunkedNioFile(FileChannel fileChannel, int i) throws IOException {
        this(fileChannel, 0L, fileChannel.size(), i);
    }

    public ChunkedNioFile(FileChannel fileChannel, long j, long j2, int i) throws IOException {
        ObjectUtil.checkNotNull(fileChannel, "in");
        ObjectUtil.checkPositiveOrZero(j, TypedValues.Cycle.S_WAVE_OFFSET);
        ObjectUtil.checkPositiveOrZero(j2, Name.LENGTH);
        ObjectUtil.checkPositive(i, "chunkSize");
        if (!fileChannel.isOpen()) {
            throw new ClosedChannelException();
        }
        this.f8426in = fileChannel;
        this.chunkSize = i;
        this.startOffset = j;
        this.offset = j;
        this.endOffset = j + j2;
    }

    public long startOffset() {
        return this.startOffset;
    }

    public long endOffset() {
        return this.endOffset;
    }

    public long currentOffset() {
        return this.offset;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public boolean isEndOfInput() throws Exception {
        return this.offset >= this.endOffset || !this.f8426in.isOpen();
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public void close() throws Exception {
        this.f8426in.close();
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
        long j = this.offset;
        long j2 = this.endOffset;
        if (j >= j2) {
            return null;
        }
        int min = (int) Math.min(this.chunkSize, j2 - j);
        ByteBuf buffer = byteBufAllocator.buffer(min);
        int i = 0;
        do {
            try {
                int writeBytes = buffer.writeBytes(this.f8426in, i + j, min - i);
                if (writeBytes < 0) {
                    break;
                }
                i += writeBytes;
            } catch (Throwable th) {
                buffer.release();
                throw th;
            }
        } while (i != min);
        this.offset += i;
        return buffer;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public long length() {
        return this.endOffset - this.startOffset;
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.stream.ChunkedInput
    public long progress() {
        return this.offset - this.startOffset;
    }
}
