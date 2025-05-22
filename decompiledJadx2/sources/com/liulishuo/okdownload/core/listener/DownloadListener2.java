package com.liulishuo.okdownload.core.listener;

import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class DownloadListener2 implements DownloadListener {
    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectEnd(DownloadTask downloadTask, int i, int i2, Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectStart(DownloadTask downloadTask, int i, Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialEnd(DownloadTask downloadTask, int i, Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialStart(DownloadTask downloadTask, Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBeginning(DownloadTask downloadTask, BreakpointInfo breakpointInfo, ResumeFailedCause resumeFailedCause) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBreakpoint(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchEnd(DownloadTask downloadTask, int i, long j) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchProgress(DownloadTask downloadTask, int i, long j) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchStart(DownloadTask downloadTask, int i, long j) {
    }
}
