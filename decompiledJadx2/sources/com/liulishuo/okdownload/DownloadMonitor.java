package com.liulishuo.okdownload;

import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;

/* loaded from: classes4.dex */
public interface DownloadMonitor {
    void taskDownloadFromBeginning(DownloadTask downloadTask, BreakpointInfo breakpointInfo, ResumeFailedCause resumeFailedCause);

    void taskDownloadFromBreakpoint(DownloadTask downloadTask, BreakpointInfo breakpointInfo);

    void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc);

    void taskStart(DownloadTask downloadTask);
}
