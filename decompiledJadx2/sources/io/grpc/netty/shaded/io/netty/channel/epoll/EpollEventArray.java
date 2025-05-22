package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.channel.unix.Buffer;
import io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent;
import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class EpollEventArray {
    private int length;
    private ByteBuffer memory;
    private long memoryAddress;
    private static final int EPOLL_EVENT_SIZE = Native.sizeofEpollEvent();
    private static final int EPOLL_DATA_OFFSET = Native.offsetofEpollData();

    /* JADX INFO: Access modifiers changed from: package-private */
    public EpollEventArray(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("length must be >= 1 but was " + i);
        }
        this.length = i;
        this.memory = Buffer.allocateDirectWithNativeOrder(calculateBufferCapacity(i));
        this.memoryAddress = Buffer.memoryAddress(this.memory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long memoryAddress() {
        return this.memoryAddress;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int length() {
        return this.length;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void increase() {
        this.length <<= 1;
        ByteBuffer allocateDirectWithNativeOrder = Buffer.allocateDirectWithNativeOrder(calculateBufferCapacity(this.length));
        Buffer.free(this.memory);
        this.memory = allocateDirectWithNativeOrder;
        this.memoryAddress = Buffer.memoryAddress(allocateDirectWithNativeOrder);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void free() {
        Buffer.free(this.memory);
        this.memoryAddress = 0L;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int events(int i) {
        return getInt(i, 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: fd */
    public int m3909fd(int i) {
        return getInt(i, EPOLL_DATA_OFFSET);
    }

    private int getInt(int i, int i2) {
        if (PlatformDependent.hasUnsafe()) {
            return PlatformDependent.getInt(this.memoryAddress + (i * EPOLL_EVENT_SIZE) + i2);
        }
        return this.memory.getInt((i * EPOLL_EVENT_SIZE) + i2);
    }

    private static int calculateBufferCapacity(int i) {
        return i * EPOLL_EVENT_SIZE;
    }
}
