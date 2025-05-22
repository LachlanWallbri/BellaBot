package io.netty.channel.kqueue;

import io.netty.util.internal.PlatformDependent;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public final class KQueueEventArray {
    private int capacity;
    private long memoryAddress;
    private int size;
    private static final int KQUEUE_EVENT_SIZE = Native.sizeofKEvent();
    private static final int KQUEUE_IDENT_OFFSET = Native.offsetofKEventIdent();
    private static final int KQUEUE_FILTER_OFFSET = Native.offsetofKEventFilter();
    private static final int KQUEUE_FFLAGS_OFFSET = Native.offsetofKEventFFlags();
    private static final int KQUEUE_FLAGS_OFFSET = Native.offsetofKEventFlags();
    private static final int KQUEUE_DATA_OFFSET = Native.offsetofKeventData();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static native void deleteGlobalRefs(long j, long j2);

    private static native void evSet(long j, AbstractKQueueChannel abstractKQueueChannel, int i, short s, short s2, int i2);

    private static native AbstractKQueueChannel getChannel(long j);

    /* JADX INFO: Access modifiers changed from: package-private */
    public KQueueEventArray(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("capacity must be >= 1 but was " + i);
        }
        this.memoryAddress = PlatformDependent.allocateMemory(KQUEUE_EVENT_SIZE * i);
        this.capacity = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long memoryAddress() {
        return this.memoryAddress;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int capacity() {
        return this.capacity;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int size() {
        return this.size;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void clear() {
        this.size = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void evSet(AbstractKQueueChannel abstractKQueueChannel, short s, short s2, int i) {
        checkSize();
        int i2 = this.size;
        this.size = i2 + 1;
        evSet(getKEventOffset(i2), abstractKQueueChannel, abstractKQueueChannel.socket.intValue(), s, s2, i);
    }

    private void checkSize() {
        if (this.size == this.capacity) {
            realloc(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void realloc(boolean z) {
        int i = this.capacity;
        int i2 = i <= 65536 ? i << 1 : (i + i) >> 1;
        long reallocateMemory = PlatformDependent.reallocateMemory(this.memoryAddress, KQUEUE_EVENT_SIZE * i2);
        if (reallocateMemory != 0) {
            this.memoryAddress = reallocateMemory;
            this.capacity = i2;
        } else if (z) {
            throw new OutOfMemoryError("unable to allocate " + i2 + " new bytes! Existing capacity is: " + this.capacity);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void free() {
        PlatformDependent.freeMemory(this.memoryAddress);
        this.capacity = 0;
        this.size = 0;
        this.memoryAddress = 0;
    }

    long getKEventOffset(int i) {
        return this.memoryAddress + (i * KQUEUE_EVENT_SIZE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public short flags(int i) {
        return PlatformDependent.getShort(getKEventOffset(i) + KQUEUE_FLAGS_OFFSET);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public short filter(int i) {
        return PlatformDependent.getShort(getKEventOffset(i) + KQUEUE_FILTER_OFFSET);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public short fflags(int i) {
        return PlatformDependent.getShort(getKEventOffset(i) + KQUEUE_FFLAGS_OFFSET);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: fd */
    public int m3933fd(int i) {
        return PlatformDependent.getInt(getKEventOffset(i) + KQUEUE_IDENT_OFFSET);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long data(int i) {
        return PlatformDependent.getLong(getKEventOffset(i) + KQUEUE_DATA_OFFSET);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public AbstractKQueueChannel channel(int i) {
        return getChannel(getKEventOffset(i));
    }
}
