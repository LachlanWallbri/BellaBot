package io.grpc.netty.shaded.io.netty.handler.codec.marshalling;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import java.io.IOException;
import org.jboss.marshalling.ByteInput;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class ChannelBufferByteInput implements ByteInput {
    private final ByteBuf buffer;

    public void close() throws IOException {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelBufferByteInput(ByteBuf byteBuf) {
        this.buffer = byteBuf;
    }

    public int available() throws IOException {
        return this.buffer.readableBytes();
    }

    public int read() throws IOException {
        if (this.buffer.isReadable()) {
            return this.buffer.readByte() & 255;
        }
        return -1;
    }

    public int read(byte[] bArr) throws IOException {
        return read(bArr, 0, bArr.length);
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        int available = available();
        if (available == 0) {
            return -1;
        }
        int min = Math.min(available, i2);
        this.buffer.readBytes(bArr, i, min);
        return min;
    }

    public long skip(long j) throws IOException {
        long readableBytes = this.buffer.readableBytes();
        if (readableBytes < j) {
            j = readableBytes;
        }
        this.buffer.readerIndex((int) (r0.readerIndex() + j));
        return j;
    }
}
