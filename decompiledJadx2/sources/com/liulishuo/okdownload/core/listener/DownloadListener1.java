package com.liulishuo.okdownload.core.listener;

import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;
import com.liulishuo.okdownload.core.listener.assist.ListenerAssist;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class DownloadListener1 implements DownloadListener, Listener1Assist.Listener1Callback, ListenerAssist {
    final Listener1Assist assist;

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
    public void fetchEnd(DownloadTask downloadTask, int i, long j) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchStart(DownloadTask downloadTask, int i, long j) {
    }

    DownloadListener1(Listener1Assist listener1Assist) {
        this.assist = listener1Assist;
        listener1Assist.setCallback(this);
    }

    public DownloadListener1() {
        this(new Listener1Assist());
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public boolean isAlwaysRecoverAssistModel() {
        return this.assist.isAlwaysRecoverAssistModel();
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public void setAlwaysRecoverAssistModel(boolean z) {
        this.assist.setAlwaysRecoverAssistModel(z);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.ListenerAssist
    public void setAlwaysRecoverAssistModelIfNotSet(boolean z) {
        this.assist.setAlwaysRecoverAssistModelIfNotSet(z);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public final void taskStart(DownloadTask downloadTask) {
        this.assist.taskStart(downloadTask);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBeginning(DownloadTask downloadTask, BreakpointInfo breakpointInfo, ResumeFailedCause resumeFailedCause) {
        this.assist.downloadFromBeginning(downloadTask, breakpointInfo, resumeFailedCause);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBreakpoint(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        this.assist.downloadFromBreakpoint(downloadTask, breakpointInfo);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectEnd(DownloadTask downloadTask, int i, int i2, Map<String, List<String>> map) {
        this.assist.connectEnd(downloadTask);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchProgress(DownloadTask downloadTask, int i, long j) {
        this.assist.fetchProgress(downloadTask, j);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public final void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc) {
        this.assist.taskEnd(downloadTask, endCause, exc);
    }
}
