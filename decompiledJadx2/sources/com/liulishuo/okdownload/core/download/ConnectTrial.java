package com.liulishuo.okdownload.core.download;

import com.liulishuo.okdownload.DownloadListener;
import com.liulishuo.okdownload.DownloadTask;
import com.liulishuo.okdownload.OkDownload;
import com.liulishuo.okdownload.core.Util;
import com.liulishuo.okdownload.core.breakpoint.BreakpointInfo;
import com.liulishuo.okdownload.core.connection.DownloadConnection;
import com.liulishuo.okdownload.core.exception.DownloadSecurityException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public class ConnectTrial {
    private static final String TAG = "ConnectTrial";
    private boolean acceptRange;
    private final BreakpointInfo info;
    private long instanceLength;
    private int responseCode;
    private String responseEtag;
    private String responseFilename;
    private final DownloadTask task;
    private static final Pattern CONTENT_DISPOSITION_QUOTED_PATTERN = Pattern.compile("attachment;\\s*filename\\s*=\\s*\"([^\"]*)\"");
    private static final Pattern CONTENT_DISPOSITION_NON_QUOTED_PATTERN = Pattern.compile("attachment;\\s*filename\\s*=\\s*(.*)");

    public ConnectTrial(DownloadTask downloadTask, BreakpointInfo breakpointInfo) {
        this.task = downloadTask;
        this.info = breakpointInfo;
    }

    public void executeTrial() throws IOException {
        OkDownload.with().downloadStrategy().inspectNetworkOnWifi(this.task);
        OkDownload.with().downloadStrategy().inspectNetworkAvailable();
        DownloadConnection create = OkDownload.with().connectionFactory().create(this.task.getUrl());
        try {
            if (!Util.isEmpty(this.info.getEtag())) {
                create.addHeader("If-Match", this.info.getEtag());
            }
            create.addHeader("Range", "bytes=0-0");
            Map<String, List<String>> headerMapFields = this.task.getHeaderMapFields();
            if (headerMapFields != null) {
                Util.addUserRequestHeaderField(headerMapFields, create);
            }
            DownloadListener dispatch = OkDownload.with().callbackDispatcher().dispatch();
            dispatch.connectTrialStart(this.task, create.getRequestProperties());
            DownloadConnection.Connected execute = create.execute();
            this.task.setRedirectLocation(execute.getRedirectLocation());
            Util.m2347d(TAG, "task[" + this.task.getId() + "] redirect location: " + this.task.getRedirectLocation());
            this.responseCode = execute.getResponseCode();
            this.acceptRange = isAcceptRange(execute);
            this.instanceLength = findInstanceLength(execute);
            this.responseEtag = findEtag(execute);
            this.responseFilename = findFilename(execute);
            Map<String, List<String>> responseHeaderFields = execute.getResponseHeaderFields();
            if (responseHeaderFields == null) {
                responseHeaderFields = new HashMap<>();
            }
            dispatch.connectTrialEnd(this.task, this.responseCode, responseHeaderFields);
            if (isNeedTrialHeadMethodForInstanceLength(this.instanceLength, execute)) {
                trialHeadMethodForInstanceLength();
            }
        } finally {
            create.release();
        }
    }

    public long getInstanceLength() {
        return this.instanceLength;
    }

    public boolean isAcceptRange() {
        return this.acceptRange;
    }

    public boolean isChunked() {
        return this.instanceLength == -1;
    }

    public String getResponseEtag() {
        return this.responseEtag;
    }

    public String getResponseFilename() {
        return this.responseFilename;
    }

    public int getResponseCode() {
        return this.responseCode;
    }

    public boolean isEtagOverdue() {
        return (this.info.getEtag() == null || this.info.getEtag().equals(this.responseEtag)) ? false : true;
    }

    private static boolean isAcceptRange(DownloadConnection.Connected connected) throws IOException {
        if (connected.getResponseCode() == 206) {
            return true;
        }
        return "bytes".equals(connected.getResponseHeaderField("Accept-Ranges"));
    }

    private static String findFilename(DownloadConnection.Connected connected) throws IOException {
        return parseContentDisposition(connected.getResponseHeaderField("Content-Disposition"));
    }

    private static String parseContentDisposition(String str) throws IOException {
        String group;
        if (str == null) {
            return null;
        }
        try {
            Matcher matcher = CONTENT_DISPOSITION_QUOTED_PATTERN.matcher(str);
            if (matcher.find()) {
                group = matcher.group(1);
            } else {
                Matcher matcher2 = CONTENT_DISPOSITION_NON_QUOTED_PATTERN.matcher(str);
                group = matcher2.find() ? matcher2.group(1) : null;
            }
            if (group != null && group.contains("../")) {
                throw new DownloadSecurityException("The filename [" + group + "] from the response is not allowable, because it contains '../', which can raise the directory traversal vulnerability");
            }
            return group;
        } catch (IllegalStateException unused) {
            return null;
        }
    }

    private static String findEtag(DownloadConnection.Connected connected) {
        return connected.getResponseHeaderField(Util.ETAG);
    }

    private static long findInstanceLength(DownloadConnection.Connected connected) {
        long parseContentRangeFoInstanceLength = parseContentRangeFoInstanceLength(connected.getResponseHeaderField("Content-Range"));
        if (parseContentRangeFoInstanceLength != -1) {
            return parseContentRangeFoInstanceLength;
        }
        if (!parseTransferEncoding(connected.getResponseHeaderField("Transfer-Encoding"))) {
            Util.m2350w(TAG, "Transfer-Encoding isn't chunked but there is no valid instance length found either!");
        }
        return -1L;
    }

    boolean isNeedTrialHeadMethodForInstanceLength(long j, DownloadConnection.Connected connected) {
        String responseHeaderField;
        if (j != -1) {
            return false;
        }
        String responseHeaderField2 = connected.getResponseHeaderField("Content-Range");
        return (responseHeaderField2 == null || responseHeaderField2.length() <= 0) && !parseTransferEncoding(connected.getResponseHeaderField("Transfer-Encoding")) && (responseHeaderField = connected.getResponseHeaderField("Content-Length")) != null && responseHeaderField.length() > 0;
    }

    void trialHeadMethodForInstanceLength() throws IOException {
        DownloadConnection create = OkDownload.with().connectionFactory().create(this.task.getUrl());
        DownloadListener dispatch = OkDownload.with().callbackDispatcher().dispatch();
        try {
            create.setRequestMethod("HEAD");
            Map<String, List<String>> headerMapFields = this.task.getHeaderMapFields();
            if (headerMapFields != null) {
                Util.addUserRequestHeaderField(headerMapFields, create);
            }
            dispatch.connectTrialStart(this.task, create.getRequestProperties());
            DownloadConnection.Connected execute = create.execute();
            dispatch.connectTrialEnd(this.task, execute.getResponseCode(), execute.getResponseHeaderFields());
            this.instanceLength = Util.parseContentLength(execute.getResponseHeaderField("Content-Length"));
        } finally {
            create.release();
        }
    }

    private static boolean parseTransferEncoding(String str) {
        return str != null && str.equals("chunked");
    }

    private static long parseContentRangeFoInstanceLength(String str) {
        if (str == null) {
            return -1L;
        }
        String[] split = str.split("/");
        if (split.length >= 2) {
            try {
                return Long.parseLong(split[1]);
            } catch (NumberFormatException unused) {
                Util.m2350w(TAG, "parse instance length failed with " + str);
            }
        }
        return -1L;
    }
}
