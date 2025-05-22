package com.liulishuo.okdownload.core.interceptor.connect;

import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BlockInfo;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.connection.DownloadConnection;
import com.liulishuo.okdownload.core.download.DownloadChain;
import com.liulishuo.okdownload.core.exception.InterruptException;
import com.liulishuo.okdownload.core.interceptor.Interceptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class HeaderInterceptor implements Interceptor.Connect {
    private static final String TAG = "HeaderInterceptor";

    @Override // com.liulishuo.okdownload.core.interceptor.Interceptor.Connect
    public DownloadConnection.Connected interceptConnect(DownloadChain downloadChain) throws IOException {
        long parseContentLengthFromContentRange;
        BreakpointInfo info = downloadChain.getInfo();
        DownloadConnection connectionOrCreate = downloadChain.getConnectionOrCreate();
        DownloadTask task = downloadChain.getTask();
        Map<String, List<String>> headerMapFields = task.getHeaderMapFields();
        if (headerMapFields != null) {
            Util.addUserRequestHeaderField(headerMapFields, connectionOrCreate);
        }
        if (headerMapFields == null || !headerMapFields.containsKey("User-Agent")) {
            Util.addDefaultUserAgent(connectionOrCreate);
        }
        int blockIndex = downloadChain.getBlockIndex();
        BlockInfo block = info.getBlock(blockIndex);
        if (block == null) {
            throw new IOException("No block-info found on " + blockIndex);
        }
        connectionOrCreate.addHeader("Range", ("bytes=" + block.getRangeLeft() + "-") + block.getRangeRight());
        Util.m2347d(TAG, "AssembleHeaderRange (" + task.getId() + ") block(" + blockIndex + ") downloadFrom(" + block.getRangeLeft() + ") currentOffset(" + block.getCurrentOffset() + ")");
        String etag = info.getEtag();
        if (!Util.isEmpty(etag)) {
            connectionOrCreate.addHeader("If-Match", etag);
        }
        if (downloadChain.getCache().isInterrupt()) {
            throw InterruptException.SIGNAL;
        }
        OkDownload.with().callbackDispatcher().dispatch().connectStart(task, blockIndex, connectionOrCreate.getRequestProperties());
        DownloadConnection.Connected processConnect = downloadChain.processConnect();
        if (downloadChain.getCache().isInterrupt()) {
            throw InterruptException.SIGNAL;
        }
        Map<String, List<String>> responseHeaderFields = processConnect.getResponseHeaderFields();
        if (responseHeaderFields == null) {
            responseHeaderFields = new HashMap<>();
        }
        OkDownload.with().callbackDispatcher().dispatch().connectEnd(task, blockIndex, processConnect.getResponseCode(), responseHeaderFields);
        OkDownload.with().downloadStrategy().resumeAvailableResponseCheck(processConnect, blockIndex, info).inspect();
        String responseHeaderField = processConnect.getResponseHeaderField("Content-Length");
        if (responseHeaderField == null || responseHeaderField.length() == 0) {
            parseContentLengthFromContentRange = Util.parseContentLengthFromContentRange(processConnect.getResponseHeaderField("Content-Range"));
        } else {
            parseContentLengthFromContentRange = Util.parseContentLength(responseHeaderField);
        }
        downloadChain.setResponseContentLength(parseContentLengthFromContentRange);
        return processConnect;
    }
}
