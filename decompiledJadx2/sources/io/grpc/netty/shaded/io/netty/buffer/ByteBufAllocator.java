package io.grpc.netty.shaded.io.netty.buffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface ByteBufAllocator {
    public static final ByteBufAllocator DEFAULT = ByteBufUtil.DEFAULT_ALLOCATOR;

    ByteBuf buffer();

    ByteBuf buffer(int i);

    ByteBuf buffer(int i, int i2);

    int calculateNewCapacity(int i, int i2);

    CompositeByteBuf compositeBuffer();

    CompositeByteBuf compositeBuffer(int i);

    CompositeByteBuf compositeDirectBuffer();

    CompositeByteBuf compositeDirectBuffer(int i);

    CompositeByteBuf compositeHeapBuffer();

    CompositeByteBuf compositeHeapBuffer(int i);

    ByteBuf directBuffer();

    ByteBuf directBuffer(int i);

    ByteBuf directBuffer(int i, int i2);

    ByteBuf heapBuffer();

    ByteBuf heapBuffer(int i);

    ByteBuf heapBuffer(int i, int i2);

    ByteBuf ioBuffer();

    ByteBuf ioBuffer(int i);

    ByteBuf ioBuffer(int i, int i2);

    boolean isDirectBufferPooled();
}
