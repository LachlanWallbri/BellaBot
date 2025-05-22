package com.liulishuo.okdownload.core.download;

import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.cause.ResumeFailedCause;
import com.liulishuo.okdownload.core.exception.FileBusyAfterRunException;
import com.liulishuo.okdownload.core.exception.InterruptException;
import com.liulishuo.okdownload.core.exception.PreAllocateException;
import com.liulishuo.okdownload.core.exception.ResumeFailedException;
import com.liulishuo.okdownload.core.exception.ServerCanceledException;
import com.liulishuo.okdownload.core.file.MultiPointOutputStream;
import java.io.IOException;
import java.net.SocketException;

/* loaded from: classes4.dex */
public class DownloadCache {
    private volatile boolean fileBusyAfterRun;
    private final MultiPointOutputStream outputStream;
    private volatile boolean preAllocateFailed;
    private volatile boolean preconditionFailed;
    private volatile IOException realCause;
    private String redirectLocation;
    private volatile boolean serverCanceled;
    private volatile boolean unknownError;
    private volatile boolean userCanceled;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DownloadCache(MultiPointOutputStream multiPointOutputStream) {
        this.outputStream = multiPointOutputStream;
    }

    private DownloadCache() {
        this.outputStream = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public MultiPointOutputStream getOutputStream() {
        MultiPointOutputStream multiPointOutputStream = this.outputStream;
        if (multiPointOutputStream != null) {
            return multiPointOutputStream;
        }
        throw new IllegalArgumentException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setRedirectLocation(String str) {
        this.redirectLocation = str;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getRedirectLocation() {
        return this.redirectLocation;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isPreconditionFailed() {
        return this.preconditionFailed;
    }

    public boolean isUserCanceled() {
        return this.userCanceled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isServerCanceled() {
        return this.serverCanceled;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isUnknownError() {
        return this.unknownError;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean isFileBusyAfterRun() {
        return this.fileBusyAfterRun;
    }

    public boolean isPreAllocateFailed() {
        return this.preAllocateFailed;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public IOException getRealCause() {
        return this.realCause;
    }

    ResumeFailedCause getResumeFailedCause() {
        return ((ResumeFailedException) this.realCause).getResumeFailedCause();
    }

    public boolean isInterrupt() {
        return this.preconditionFailed || this.userCanceled || this.serverCanceled || this.unknownError || this.fileBusyAfterRun || this.preAllocateFailed;
    }

    public void setPreconditionFailed(IOException iOException) {
        this.preconditionFailed = true;
        this.realCause = iOException;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setUserCanceled() {
        this.userCanceled = true;
    }

    public void setFileBusyAfterRun() {
        this.fileBusyAfterRun = true;
    }

    public void setServerCanceled(IOException iOException) {
        this.serverCanceled = true;
        this.realCause = iOException;
    }

    public void setUnknownError(IOException iOException) {
        this.unknownError = true;
        this.realCause = iOException;
    }

    public void setPreAllocateFailed(IOException iOException) {
        this.preAllocateFailed = true;
        this.realCause = iOException;
    }

    public void catchException(IOException iOException) {
        if (isUserCanceled()) {
            return;
        }
        if (iOException instanceof ResumeFailedException) {
            setPreconditionFailed(iOException);
            return;
        }
        if (iOException instanceof ServerCanceledException) {
            setServerCanceled(iOException);
            return;
        }
        if (iOException == FileBusyAfterRunException.SIGNAL) {
            setFileBusyAfterRun();
            return;
        }
        if (iOException instanceof PreAllocateException) {
            setPreAllocateFailed(iOException);
            return;
        }
        if (iOException != InterruptException.SIGNAL) {
            setUnknownError(iOException);
            if (iOException instanceof SocketException) {
                return;
            }
            Util.m2347d("DownloadCache", "catch unknown error " + iOException);
        }
    }

    /* loaded from: classes4.dex */
    static class PreError extends DownloadCache {
        /* JADX INFO: Access modifiers changed from: package-private */
        public PreError(IOException iOException) {
            super(null);
            setUnknownError(iOException);
        }
    }
}
