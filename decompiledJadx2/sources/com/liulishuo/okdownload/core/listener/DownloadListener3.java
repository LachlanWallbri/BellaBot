package com.liulishuo.okdownload.core.listener;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.listener.assist.Listener1Assist;

/* loaded from: classes4.dex */
public abstract class DownloadListener3 extends DownloadListener1 {
    protected abstract void canceled(DownloadTask downloadTask);

    protected abstract void completed(DownloadTask downloadTask);

    protected abstract void error(DownloadTask downloadTask, Exception exc);

    protected abstract void started(DownloadTask downloadTask);

    protected abstract void warn(DownloadTask downloadTask);

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public final void taskStart(DownloadTask downloadTask, Listener1Assist.Listener1Model listener1Model) {
        started(downloadTask);
    }

    @Override // com.liulishuo.okdownload.core.listener.assist.Listener1Assist.Listener1Callback
    public void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc, Listener1Assist.Listener1Model listener1Model) {
        switch (endCause) {
            case COMPLETED:
                completed(downloadTask);
                return;
            case CANCELED:
                canceled(downloadTask);
                return;
            case ERROR:
            case PRE_ALLOCATE_FAILED:
                error(downloadTask, exc);
                return;
            case FILE_BUSY:
            case SAME_TASK_BUSY:
                warn(downloadTask);
                return;
            default:
                Util.m2350w("DownloadListener3", "Don't support " + endCause);
                return;
        }
    }
}
