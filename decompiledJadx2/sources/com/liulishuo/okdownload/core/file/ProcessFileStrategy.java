package com.liulishuo.okdownload.core.file;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.breakpoint.DownloadStore;
import java.io.File;
import java.io.IOException;

/* loaded from: classes4.dex */
public class ProcessFileStrategy {
    private final FileLock fileLock = new FileLock();

    public void completeProcessStream(MultiPointOutputStream multiPointOutputStream, DownloadTask downloadTask) {
    }

    public MultiPointOutputStream createProcessStream(DownloadTask downloadTask, BreakpointInfo breakpointInfo, DownloadStore downloadStore) {
        return new MultiPointOutputStream(downloadTask, breakpointInfo, downloadStore);
    }

    public void discardProcess(DownloadTask downloadTask) throws IOException {
        File file = downloadTask.getFile();
        if (file != null && file.exists() && !file.delete()) {
            throw new IOException("Delete file failed!");
        }
    }

    public FileLock getFileLock() {
        return this.fileLock;
    }

    public boolean isPreAllocateLength(DownloadTask downloadTask) {
        if (!OkDownload.with().outputStreamFactory().supportSeek()) {
            return false;
        }
        if (downloadTask.getSetPreAllocateLength() != null) {
            return downloadTask.getSetPreAllocateLength().booleanValue();
        }
        return true;
    }
}
