package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import com.iflytek.cloud.SpeechEvent;
import io.grpc.internal.AbstractReadableBuffer;
import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class NettyReadableBuffer extends AbstractReadableBuffer {
    private final ByteBuf buffer;
    private boolean closed;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyReadableBuffer(ByteBuf byteBuf) {
        this.buffer = (ByteBuf) Preconditions.checkNotNull(byteBuf, SpeechEvent.KEY_EVENT_TTS_BUFFER);
    }

    ByteBuf buffer() {
        return this.buffer;
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readableBytes() {
        return this.buffer.readableBytes();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void skipBytes(int i) {
        this.buffer.skipBytes(i);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public int readUnsignedByte() {
        return this.buffer.readUnsignedByte();
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(byte[] bArr, int i, int i2) {
        this.buffer.readBytes(bArr, i, i2);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(ByteBuffer byteBuffer) {
        this.buffer.readBytes(byteBuffer);
    }

    @Override // io.grpc.internal.ReadableBuffer
    public void readBytes(OutputStream outputStream, int i) {
        try {
            this.buffer.readBytes(outputStream, i);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override // io.grpc.internal.ReadableBuffer
    public NettyReadableBuffer readBytes(int i) {
        return new NettyReadableBuffer(this.buffer.readRetainedSlice(i));
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    public boolean hasArray() {
        return this.buffer.hasArray();
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    public byte[] array() {
        return this.buffer.array();
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer
    public int arrayOffset() {
        return this.buffer.arrayOffset() + this.buffer.readerIndex();
    }

    @Override // io.grpc.internal.AbstractReadableBuffer, io.grpc.internal.ReadableBuffer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.buffer.release();
    }
}
