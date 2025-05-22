package com.liulishuo.okdownload.core.listener;

import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.cause.EndCause;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class DownloadListenerBunch implements DownloadListener {
    final DownloadListener[] listenerList;

    DownloadListenerBunch(DownloadListener[] downloadListenerArr) {
        this.listenerList = downloadListenerArr;
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void taskStart(DownloadTask downloadTask) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.taskStart(downloadTask);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialStart(DownloadTask downloadTask, Map<String, List<String>> map) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.connectTrialStart(downloadTask, map);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectTrialEnd(DownloadTask downloadTask, int i, Map<String, List<String>> map) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.connectTrialEnd(downloadTask, i, map);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBeginning(DownloadTask downloadTask, BreakpointInfo breakpointInfo, ResumeFailedCause resumeFailedCause) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.downloadFromBeginning(downloadTask, breakpointInfo, resumeFailedCause);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void downloadFromBreakpoint(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.downloadFromBreakpoint(downloadTask, breakpointInfo);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectStart(DownloadTask downloadTask, int i, Map<String, List<String>> map) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.connectStart(downloadTask, i, map);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void connectEnd(DownloadTask downloadTask, int i, int i2, Map<String, List<String>> map) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.connectEnd(downloadTask, i, i2, map);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchStart(DownloadTask downloadTask, int i, long j) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.fetchStart(downloadTask, i, j);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchProgress(DownloadTask downloadTask, int i, long j) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.fetchProgress(downloadTask, i, j);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void fetchEnd(DownloadTask downloadTask, int i, long j) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.fetchEnd(downloadTask, i, j);
        }
    }

    @Override // com.liulishuo.okdownload.DownloadListener
    public void taskEnd(DownloadTask downloadTask, EndCause endCause, Exception exc) {
        for (DownloadListener downloadListener : this.listenerList) {
            downloadListener.taskEnd(downloadTask, endCause, exc);
        }
    }

    public boolean contain(DownloadListener downloadListener) {
        for (DownloadListener downloadListener2 : this.listenerList) {
            if (downloadListener2 == downloadListener) {
                return true;
            }
        }
        return false;
    }

    public int indexOf(DownloadListener downloadListener) {
        int i = 0;
        while (true) {
            DownloadListener[] downloadListenerArr = this.listenerList;
            if (i >= downloadListenerArr.length) {
                return -1;
            }
            if (downloadListenerArr[i] == downloadListener) {
                return i;
            }
            i++;
        }
    }

    /* loaded from: classes4.dex */
    public static class Builder {
        private List<DownloadListener> listenerList = new ArrayList();

        public DownloadListenerBunch build() {
            List<DownloadListener> list = this.listenerList;
            return new DownloadListenerBunch((DownloadListener[]) list.toArray(new DownloadListener[list.size()]));
        }

        public Builder append(DownloadListener downloadListener) {
            if (downloadListener != null && !this.listenerList.contains(downloadListener)) {
                this.listenerList.add(downloadListener);
            }
            return this;
        }

        public boolean remove(DownloadListener downloadListener) {
            return this.listenerList.remove(downloadListener);
        }
    }
}
