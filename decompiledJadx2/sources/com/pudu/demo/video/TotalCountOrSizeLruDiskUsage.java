package com.pudu.demo.video;

import com.danikula.videocache.file.LruDiskUsage;
import java.io.File;
import kotlin.Metadata;

/* compiled from: TotalCountOrSizeLruDiskUsage.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0005H\u0014R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudu/demo/video/TotalCountOrSizeLruDiskUsage;", "Lcom/danikula/videocache/file/LruDiskUsage;", "maxSize", "", "maxCount", "", "(JI)V", "mMaxCount", "mMaxSize", "accept", "", "file", "Ljava/io/File;", "totalSize", "totalCount", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TotalCountOrSizeLruDiskUsage extends LruDiskUsage {
    private int mMaxCount;
    private long mMaxSize;

    public TotalCountOrSizeLruDiskUsage(long j, int i) {
        this.mMaxSize = j;
        this.mMaxCount = i;
    }

    @Override // com.danikula.videocache.file.LruDiskUsage
    protected boolean accept(File file, long totalSize, int totalCount) {
        return totalSize <= this.mMaxSize && totalCount <= this.mMaxCount;
    }
}
