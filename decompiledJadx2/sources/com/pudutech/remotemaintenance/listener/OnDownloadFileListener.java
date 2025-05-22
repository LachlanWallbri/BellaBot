package com.pudutech.remotemaintenance.listener;

import kotlin.Metadata;

/* compiled from: OnDownloadFileListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005H&J\u001a\u0010\u0007\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\tH&J\u0012\u0010\n\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u001c\u0010\u000b\u001a\u00020\u00032\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/listener/OnDownloadFileListener;", "", "onFailure", "", "fileUrl", "", "errMsg", "onProgress", "progress", "", "onStart", "onSuccessful", "filePath", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface OnDownloadFileListener {
    void onFailure(String fileUrl, String errMsg);

    void onProgress(String fileUrl, int progress);

    void onStart(String fileUrl);

    void onSuccessful(String filePath, String fileUrl);
}
