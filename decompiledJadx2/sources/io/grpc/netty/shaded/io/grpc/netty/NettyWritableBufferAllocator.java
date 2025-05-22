package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.internal.WritableBuffer;
import io.grpc.internal.WritableBufferAllocator;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class NettyWritableBufferAllocator implements WritableBufferAllocator {
    private static final int MAX_BUFFER = 1048576;
    private static final int MIN_BUFFER = 4096;
    private final ByteBufAllocator allocator;

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyWritableBufferAllocator(ByteBufAllocator byteBufAllocator) {
        this.allocator = byteBufAllocator;
    }

    @Override // io.grpc.internal.WritableBufferAllocator
    public WritableBuffer allocate(int i) {
        int min = Math.min(1048576, Math.max(4096, i));
        return new NettyWritableBuffer(this.allocator.buffer(min, min));
    }
}
