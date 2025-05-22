package com.pudutech.light_network.download;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class DownLoadObserver implements Observer<DownloadInfo> {

    /* renamed from: d */
    protected Disposable f5479d;
    protected DownloadInfo downloadInfo;

    @Override // io.reactivex.Observer
    public void onComplete() {
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        this.f5479d = disposable;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // io.reactivex.Observer
    public void onNext(DownloadInfo downloadInfo) {
        this.downloadInfo = downloadInfo;
    }
}
