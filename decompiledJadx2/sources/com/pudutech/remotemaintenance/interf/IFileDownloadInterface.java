package com.pudutech.remotemaintenance.interf;

import com.pudutech.remotemaintenance.listener.OnDownloadFileListener;
import kotlin.Metadata;

/* compiled from: IFileDownloadInterface.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\bH&¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/interf/IFileDownloadInterface;", "", "downloadFile", "", "fileUrl", "", "fileName", "listener", "Lcom/pudutech/remotemaintenance/listener/OnDownloadFileListener;", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public interface IFileDownloadInterface {
    void downloadFile(String fileUrl, String fileName, OnDownloadFileListener listener);
}
