package com.danikula.videocache.file;

import java.io.File;

/* loaded from: classes.dex */
public class TotalSizeLruDiskUsage extends LruDiskUsage {
    private final long maxSize;

    public TotalSizeLruDiskUsage(long j) {
        if (j <= 0) {
            throw new IllegalArgumentException("Max size must be positive number!");
        }
        this.maxSize = j;
    }

    @Override // com.danikula.videocache.file.LruDiskUsage
    protected boolean accept(File file, long j, int i) {
        return j <= this.maxSize;
    }
}
