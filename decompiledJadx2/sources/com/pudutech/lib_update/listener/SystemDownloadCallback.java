package com.pudutech.lib_update.listener;

import com.loc.C3898x;
import java.io.File;
import kotlin.Metadata;

/* compiled from: IShowProgress.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH&J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u000bH&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/lib_update/listener/SystemDownloadCallback;", "", "onFail", "", C3898x.f4338g, "", "onFinish", "outputFile", "Ljava/io/File;", "onProgress", "crtSize", "", "totalSize", "onStartProgress", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface SystemDownloadCallback {
    void onFail(Throwable e);

    void onFinish(File outputFile);

    void onProgress(long crtSize, long totalSize);

    void onStartProgress();
}
