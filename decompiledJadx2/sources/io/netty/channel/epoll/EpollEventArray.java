package io.netty.channel.epoll;

import io.netty.util.internal.PlatformDependent;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
final class EpollEventArray {
    private int length;
    private long memoryAddress;
    private static final int EPOLL_EVENT_SIZE = Native.sizeofEpollEvent();
    private static final int EPOLL_DATA_OFFSET = Native.offsetofEpollData();

    /* JADX INFO: Access modifiers changed from: package-private */
    public EpollEventArray(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("length must be >= 1 but was " + i);
        }
        this.length = i;
        this.memoryAddress = allocate(i);
    }

    private static long allocate(int i) {
        return PlatformDependent.allocateMemory(i * EPOLL_EVENT_SIZE);
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
        free();
        this.memoryAddress = allocate(this.length);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void free() {
        PlatformDependent.freeMemory(this.memoryAddress);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int events(int i) {
        return PlatformDependent.getInt(this.memoryAddress + (i * EPOLL_EVENT_SIZE));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: fd */
    public int m3931fd(int i) {
        return PlatformDependent.getInt(this.memoryAddress + (i * EPOLL_EVENT_SIZE) + EPOLL_DATA_OFFSET);
    }
}
