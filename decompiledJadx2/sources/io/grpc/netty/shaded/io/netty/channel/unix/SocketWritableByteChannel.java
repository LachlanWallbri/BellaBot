package io.grpc.netty.shaded.io.netty.channel.unix;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufUtil;
import io.grpc.netty.shaded.io.netty.buffer.Unpooled;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class SocketWritableByteChannel implements WritableByteChannel {

    /* renamed from: fd */
    private final FileDescriptor f8344fd;

    protected abstract ByteBufAllocator alloc();

    /* JADX INFO: Access modifiers changed from: protected */
    public SocketWritableByteChannel(FileDescriptor fileDescriptor) {
        this.f8344fd = (FileDescriptor) ObjectUtil.checkNotNull(fileDescriptor, "fd");
    }

    @Override // java.nio.channels.WritableByteChannel
    public final int write(ByteBuffer byteBuffer) throws IOException {
        int write;
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        if (byteBuffer.isDirect()) {
            write = this.f8344fd.write(byteBuffer, position, byteBuffer.limit());
        } else {
            int i = limit - position;
            ByteBuf byteBuf = null;
            try {
                if (i == 0) {
                    byteBuf = Unpooled.EMPTY_BUFFER;
                } else {
                    ByteBufAllocator alloc = alloc();
                    if (alloc.isDirectBufferPooled()) {
                        byteBuf = alloc.directBuffer(i);
                    } else {
                        byteBuf = ByteBufUtil.threadLocalDirectBuffer();
                        if (byteBuf == null) {
                            byteBuf = Unpooled.directBuffer(i);
                        }
                    }
                }
                byteBuf.writeBytes(byteBuffer.duplicate());
                ByteBuffer internalNioBuffer = byteBuf.internalNioBuffer(byteBuf.readerIndex(), i);
                write = this.f8344fd.write(internalNioBuffer, internalNioBuffer.position(), internalNioBuffer.limit());
            } finally {
                if (byteBuf != null) {
                    byteBuf.release();
                }
            }
        }
        if (write > 0) {
            byteBuffer.position(position + write);
        }
        return write;
    }

    @Override // java.nio.channels.Channel
    public final boolean isOpen() {
        return this.f8344fd.isOpen();
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.f8344fd.close();
    }
}
