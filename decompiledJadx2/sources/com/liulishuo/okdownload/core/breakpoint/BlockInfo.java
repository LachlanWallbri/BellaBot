package com.liulishuo.okdownload.core.breakpoint;

import java.util.concurrent.atomic.AtomicLong;

/* loaded from: classes4.dex */
public class BlockInfo {
    private final long contentLength;
    private final AtomicLong currentOffset;
    private final long startOffset;

    public BlockInfo(long j, long j2) {
        this(j, j2, 0L);
    }

    public BlockInfo(long j, long j2, long j3) {
        if (j < 0 || ((j2 < 0 && j2 != -1) || j3 < 0)) {
            throw new IllegalArgumentException();
        }
        this.startOffset = j;
        this.contentLength = j2;
        this.currentOffset = new AtomicLong(j3);
    }

    public long getCurrentOffset() {
        return this.currentOffset.get();
    }

    public long getStartOffset() {
        return this.startOffset;
    }

    public long getRangeLeft() {
        return this.startOffset + this.currentOffset.get();
    }

    public long getContentLength() {
        return this.contentLength;
    }

    public long getRangeRight() {
        return (this.startOffset + this.contentLength) - 1;
    }

    public void increaseCurrentOffset(long j) {
        this.currentOffset.addAndGet(j);
    }

    public void resetBlock() {
        this.currentOffset.set(0L);
    }

    public BlockInfo copy() {
        return new BlockInfo(this.startOffset, this.contentLength, this.currentOffset.get());
    }

    public String toString() {
        return "[" + this.startOffset + ", " + getRangeRight() + ")-current:" + this.currentOffset;
    }
}
