package com.pudutech.remotemaintenance.listener;

import kotlin.Metadata;

/* compiled from: OnUploadFileListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005H&J \u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H&¨\u0006\r"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/listener/OnUploadFileListener;", "", "onFailure", "", "filePath", "", "fileUrl", "errMsg", "onProgress", "progress", "", "onStart", "onSuccessful", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface OnUploadFileListener {
    void onFailure(String filePath, String fileUrl, String errMsg);

    void onProgress(String filePath, String fileUrl, int progress);

    void onStart(String filePath, String fileUrl);

    void onSuccessful(String filePath, String fileUrl);
}
