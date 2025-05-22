package io.grpc.netty.shaded.io.netty.channel.unix;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class IovArray implements ChannelOutboundBuffer.MessageProcessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int count;
    private long maxBytes = Limits.SSIZE_MAX;
    private final ByteBuffer memory = Buffer.allocateDirectWithNativeOrder(CAPACITY);
    private final long memoryAddress = Buffer.memoryAddress(this.memory);
    private long size;
    private static final int ADDRESS_SIZE = Buffer.addressSize();
    private static final int IOV_SIZE = ADDRESS_SIZE * 2;
    private static final int CAPACITY = Limits.IOV_MAX * IOV_SIZE;

    public void clear() {
        this.count = 0;
        this.size = 0L;
    }

    @Deprecated
    public boolean add(ByteBuf byteBuf) {
        return add(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    public boolean add(ByteBuf byteBuf, int i, int i2) {
        if (this.count == Limits.IOV_MAX) {
            return false;
        }
        if (byteBuf.nioBufferCount() == 1) {
            if (i2 == 0) {
                return true;
            }
            if (byteBuf.hasMemoryAddress()) {
                return add(byteBuf.memoryAddress() + i, i2);
            }
            return add(Buffer.memoryAddress(byteBuf.internalNioBuffer(i, i2)) + r9.position(), i2);
        }
        ByteBuffer[] nioBuffers = byteBuf.nioBuffers(i, i2);
        for (ByteBuffer byteBuffer : nioBuffers) {
            int remaining = byteBuffer.remaining();
            if (remaining != 0 && (!add(Buffer.memoryAddress(byteBuffer) + byteBuffer.position(), remaining) || this.count == Limits.IOV_MAX)) {
                return false;
            }
        }
        return true;
    }

    private boolean add(long j, int i) {
        long j2 = i;
        if (this.maxBytes - j2 < this.size && this.count > 0) {
            return false;
        }
        int idx = idx(this.count);
        int i2 = ADDRESS_SIZE;
        int i3 = idx + i2;
        this.size += j2;
        this.count++;
        if (i2 == 8) {
            if (PlatformDependent.hasUnsafe()) {
                PlatformDependent.putLong(idx + this.memoryAddress, j);
                PlatformDependent.putLong(i3 + this.memoryAddress, j2);
            } else {
                this.memory.putLong(idx, j);
                this.memory.putLong(i3, j2);
            }
        } else if (PlatformDependent.hasUnsafe()) {
            PlatformDependent.putInt(idx + this.memoryAddress, (int) j);
            PlatformDependent.putInt(i3 + this.memoryAddress, i);
        } else {
            this.memory.putInt(idx, (int) j);
            this.memory.putInt(i3, i);
        }
        return true;
    }

    public int count() {
        return this.count;
    }

    public long size() {
        return this.size;
    }

    public void maxBytes(long j) {
        this.maxBytes = Math.min(Limits.SSIZE_MAX, ObjectUtil.checkPositive(j, "maxBytes"));
    }

    public long maxBytes() {
        return this.maxBytes;
    }

    public long memoryAddress(int i) {
        return this.memoryAddress + idx(i);
    }

    public void release() {
        Buffer.free(this.memory);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.ChannelOutboundBuffer.MessageProcessor
    public boolean processMessage(Object obj) throws Exception {
        if (!(obj instanceof ByteBuf)) {
            return false;
        }
        ByteBuf byteBuf = (ByteBuf) obj;
        return add(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    private static int idx(int i) {
        return IOV_SIZE * i;
    }
}
