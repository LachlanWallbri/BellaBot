package com.pudutech.mpmodule.permission;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public abstract class CPermissionObserver implements Observer<Boolean> {
    private Disposable mDisposable;

    protected abstract void onCNext(Boolean bool);

    @Override // io.reactivex.Observer
    public void onComplete() {
    }

    @Override // io.reactivex.Observer
    public void onSubscribe(Disposable disposable) {
        this.mDisposable = disposable;
    }

    @Override // io.reactivex.Observer
    public void onNext(Boolean bool) {
        onCNext(bool);
        onComplete();
    }

    @Override // io.reactivex.Observer
    public void onError(Throwable th) {
        onCError(th);
        onComplete();
    }

    protected void onCError(Throwable th) {
        Disposable disposable = this.mDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }
}
