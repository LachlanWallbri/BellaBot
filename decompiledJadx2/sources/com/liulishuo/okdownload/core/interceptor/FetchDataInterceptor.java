package com.liulishuo.okdownload.core.interceptor;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.dispatcher.CallbackDispatcher;
import com.liulishuo.okdownload.core.download.DownloadChain;
import com.liulishuo.okdownload.core.exception.InterruptException;
import com.liulishuo.okdownload.core.file.MultiPointOutputStream;
import com.liulishuo.okdownload.core.interceptor.Interceptor;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: classes4.dex */
public class FetchDataInterceptor implements Interceptor.Fetch {
    private final int blockIndex;
    private final CallbackDispatcher dispatcher = OkDownload.with().callbackDispatcher();
    private final InputStream inputStream;
    private final MultiPointOutputStream outputStream;
    private final byte[] readBuffer;
    private final DownloadTask task;

    public FetchDataInterceptor(int i, InputStream inputStream, MultiPointOutputStream multiPointOutputStream, DownloadTask downloadTask) {
        this.blockIndex = i;
        this.inputStream = inputStream;
        this.readBuffer = new byte[downloadTask.getReadBufferSize()];
        this.outputStream = multiPointOutputStream;
        this.task = downloadTask;
    }

    @Override // com.liulishuo.okdownload.core.interceptor.Interceptor.Fetch
    public long interceptFetch(DownloadChain downloadChain) throws IOException {
        if (downloadChain.getCache().isInterrupt()) {
            throw InterruptException.SIGNAL;
        }
        OkDownload.with().downloadStrategy().inspectNetworkOnWifi(downloadChain.getTask());
        int read = this.inputStream.read(this.readBuffer);
        if (read == -1) {
            return read;
        }
        this.outputStream.write(this.blockIndex, this.readBuffer, read);
        long j = read;
        downloadChain.increaseCallbackBytes(j);
        if (this.dispatcher.isFetchProcessMoment(this.task)) {
            downloadChain.flushNoCallbackIncreaseBytes();
        }
        return j;
    }
}
