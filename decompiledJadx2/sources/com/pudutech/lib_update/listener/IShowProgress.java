package com.pudutech.lib_update.listener;

import com.loc.C3898x;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: IShowProgress.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0018\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\tH&J\b\u0010\u000b\u001a\u00020\u0003H&¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/lib_update/listener/IShowProgress;", "", "onFail", "", C3898x.f4338g, "", "onFinish", "onProgress", "crtSize", "", "totalSize", "onStartProgress", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public interface IShowProgress {
    void onFail(Throwable e);

    void onFinish();

    void onProgress(long crtSize, long totalSize);

    void onStartProgress();
}
