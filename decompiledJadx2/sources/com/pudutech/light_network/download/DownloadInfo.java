package com.pudutech.light_network.download;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public class DownloadInfo {
    public static final long TOTAL_ERROR = -1;
    private String fileName;
    private long progress;
    private long total;
    private String url;

    public DownloadInfo(String str) {
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public long getTotal() {
        return this.total;
    }

    public void setTotal(long j) {
        this.total = j;
    }

    public long getProgress() {
        return this.progress;
    }

    public void setProgress(long j) {
        this.progress = j;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String str) {
        this.fileName = str;
    }

    public String toString() {
        return "DownloadInfo{url='" + this.url + "', total=" + this.total + ", progress=" + this.progress + ", fileName='" + this.fileName + "'}";
    }
}
