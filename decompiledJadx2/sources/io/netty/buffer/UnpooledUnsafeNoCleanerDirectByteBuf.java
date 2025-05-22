package io.netty.buffer;

import io.netty.util.internal.PlatformDependent;
import java.nio.ByteBuffer;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public class UnpooledUnsafeNoCleanerDirectByteBuf extends UnpooledUnsafeDirectByteBuf {
    /* JADX INFO: Access modifiers changed from: package-private */
    public UnpooledUnsafeNoCleanerDirectByteBuf(ByteBufAllocator byteBufAllocator, int i, int i2) {
        super(byteBufAllocator, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.UnpooledUnsafeDirectByteBuf
    public ByteBuffer allocateDirect(int i) {
        return PlatformDependent.allocateDirectNoCleaner(i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteBuffer reallocateDirect(ByteBuffer byteBuffer, int i) {
        return PlatformDependent.reallocateDirectNoCleaner(byteBuffer, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // io.netty.buffer.UnpooledUnsafeDirectByteBuf
    public void freeDirect(ByteBuffer byteBuffer) {
        PlatformDependent.freeDirectNoCleaner(byteBuffer);
    }

    @Override // io.netty.buffer.UnpooledUnsafeDirectByteBuf, io.netty.buffer.ByteBuf
    public ByteBuf capacity(int i) {
        checkNewCapacity(i);
        int capacity = capacity();
        if (i == capacity) {
            return this;
        }
        ByteBuffer reallocateDirect = reallocateDirect(this.buffer, i);
        if (i < capacity) {
            if (readerIndex() < i) {
                if (writerIndex() > i) {
                    writerIndex(i);
                }
            } else {
                setIndex(i, i);
            }
        }
        setByteBuffer(reallocateDirect, false);
        return this;
    }
}
