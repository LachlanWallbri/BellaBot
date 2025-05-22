package com.liulishuo.okdownload.core.breakpoint;

import com.liulishuo.okdownload.DownloadTask;
import java.io.IOException;

/* loaded from: classes4.dex */
public interface BreakpointStore {
    BreakpointInfo createAndInsert(DownloadTask downloadTask) throws IOException;

    BreakpointInfo findAnotherInfoFromCompare(DownloadTask downloadTask, BreakpointInfo breakpointInfo);

    int findOrCreateId(DownloadTask downloadTask);

    BreakpointInfo get(int i);

    String getResponseFilename(String str);

    boolean isFileDirty(int i);

    boolean isOnlyMemoryCache();

    void remove(int i);

    boolean update(BreakpointInfo breakpointInfo) throws IOException;
}
