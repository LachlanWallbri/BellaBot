package com.liulishuo.okdownload.core.download;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.exception.FileBusyAfterRunException;
import com.liulishuo.okdownload.core.exception.ServerCanceledException;
import java.io.IOException;

/* loaded from: classes4.dex */
public class BreakpointRemoteCheck {
    private boolean acceptRange;
    ResumeFailedCause failedCause;
    private final BreakpointInfo info;
    private long instanceLength;
    private boolean resumable;
    private final DownloadTask task;

    boolean isTrialSpecialPass(int i, long j, boolean z) {
        return i == 416 && j >= 0 && z;
    }

    public BreakpointRemoteCheck(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        this.task = downloadTask;
        this.info = breakpointInfo;
    }

    public String toString() {
        return "acceptRange[" + this.acceptRange + "] resumable[" + this.resumable + "] failedCause[" + this.failedCause + "] instanceLength[" + this.instanceLength + "] " + super.toString();
    }

    public ResumeFailedCause getCause() {
        return this.failedCause;
    }

    public ResumeFailedCause getCauseOrThrow() {
        ResumeFailedCause resumeFailedCause = this.failedCause;
        if (resumeFailedCause != null) {
            return resumeFailedCause;
        }
        throw new IllegalStateException("No cause find with resumable: " + this.resumable);
    }

    public boolean isResumable() {
        return this.resumable;
    }

    public boolean isAcceptRange() {
        return this.acceptRange;
    }

    public long getInstanceLength() {
        return this.instanceLength;
    }

    public void check() throws IOException {
        DownloadStrategy downloadStrategy = OkDownload.with().downloadStrategy();
        ConnectTrial createConnectTrial = createConnectTrial();
        createConnectTrial.executeTrial();
        boolean isAcceptRange = createConnectTrial.isAcceptRange();
        boolean isChunked = createConnectTrial.isChunked();
        long instanceLength = createConnectTrial.getInstanceLength();
        String responseEtag = createConnectTrial.getResponseEtag();
        String responseFilename = createConnectTrial.getResponseFilename();
        int responseCode = createConnectTrial.getResponseCode();
        downloadStrategy.validFilenameFromResponse(responseFilename, this.task, this.info);
        this.info.setChunked(isChunked);
        this.info.setEtag(responseEtag);
        if (OkDownload.with().downloadDispatcher().isFileConflictAfterRun(this.task)) {
            throw FileBusyAfterRunException.SIGNAL;
        }
        ResumeFailedCause preconditionFailedCause = downloadStrategy.getPreconditionFailedCause(responseCode, this.info.getTotalOffset() != 0, this.info, responseEtag);
        this.resumable = preconditionFailedCause == null;
        this.failedCause = preconditionFailedCause;
        this.instanceLength = instanceLength;
        this.acceptRange = isAcceptRange;
        if (isTrialSpecialPass(responseCode, instanceLength, this.resumable)) {
            return;
        }
        if (downloadStrategy.isServerCanceled(responseCode, this.info.getTotalOffset() != 0)) {
            throw new ServerCanceledException(responseCode, this.info.getTotalOffset());
        }
    }

    ConnectTrial createConnectTrial() {
        return new ConnectTrial(this.task, this.info);
    }
}
