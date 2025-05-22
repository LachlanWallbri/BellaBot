package com.liulishuo.okdownload;

import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface DownloadListener {
    void connectEnd(DownloadTask downloadTask, int i, int i2, Map<String, List<String>> map);

    void connectStart(DownloadTask downloadTask, int i, Map<String, List<String>> map);

    void connectTrialEnd(DownloadTask downloadTask, int i, Map<String, List<String>> map);

    void connectTrialStart(DownloadTask downloadTask, Map<String, List<String>> map);

    void downloadFromBeginning(DownloadTask downloadTask, BreakpointInfo breakpointInfo, ResumeFailedCause resumeFailedCause);

    void downloadFromBreakpoint(DownloadTask downloadTask, BreakpointInfo breakpointInfo);

    void fetchEnd(DownloadTask downloadTask, int i, long j);

    void fetchProgress(DownloadTask downloadTask, int i, long j);

    void fetchStart(DownloadTask downloadTask, int i, long j);

    void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc);

    void taskStart(DownloadTask downloadTask);
}
