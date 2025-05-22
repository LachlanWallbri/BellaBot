package com.liulishuo.okdownload.core.listener;

import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.listener.assist.Listener4Assist;
import com.liulishuo.okdownload.core.listener.assist.ListenerAssist;
import com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class DownloadListener4 implements DownloadListener, Listener4Assist.Listener4Callback, ListenerAssist {
    final Listener4Assist assist;

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialEnd(DownloadTask downloadTask, int i, Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialStart(DownloadTask downloadTask, Map<String, List<String>> map) {
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchStart(DownloadTask downloadTask, int i, long j) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadListener4(Listener4Assist listener4Assist) {
        this.assist = listener4Assist;
        listener4Assist.setCallback(this);
    }

    public DownloadListener4() {
        this(new Listener4Assist(new Listener4ModelCreator()));
    }

    public void setAssistExtend(Listener4Assist.AssistExtend assistExtend) {
        this.assist.setAssistExtend(assistExtend);
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
    public final void downloadFromBeginning(DownloadTask downloadTask, BreakpointInfo breakpointInfo, ResumeFailedCause resumeFailedCause) {
        this.assist.infoReady(downloadTask, breakpointInfo, false);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public final void downloadFromBreakpoint(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        this.assist.infoReady(downloadTask, breakpointInfo, true);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public final void fetchProgress(DownloadTask downloadTask, int i, long j) {
        this.assist.fetchProgress(downloadTask, i, j);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchEnd(DownloadTask downloadTask, int i, long j) {
        this.assist.fetchEnd(downloadTask, i);
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public final void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc) {
        this.assist.taskEnd(downloadTask, endCause, exc);
    }

    /* loaded from: classes4.dex */
    static class Listener4ModelCreator implements ListenerModelHandler.ModelCreator<Listener4Assist.Listener4Model> {
        Listener4ModelCreator() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.liulishuo.okdownload.core.listener.assist.ListenerModelHandler.ModelCreator
        public Listener4Assist.Listener4Model create(int i) {
            return new Listener4Assist.Listener4Model(i);
        }
    }
}
