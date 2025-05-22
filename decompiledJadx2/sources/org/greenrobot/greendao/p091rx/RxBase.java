package org.greenrobot.greendao.p091rx;

import java.util.concurrent.Callable;
import rx.Observable;
import rx.Scheduler;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
class RxBase {
    protected final Scheduler scheduler;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RxBase() {
        this.scheduler = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RxBase(Scheduler scheduler) {
        this.scheduler = scheduler;
    }

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <R> Observable<R> wrap(Callable<R> callable) {
        return wrap(RxUtils.fromCallable(callable));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public <R> Observable<R> wrap(Observable<R> observable) {
        Scheduler scheduler = this.scheduler;
        return scheduler != null ? observable.subscribeOn(scheduler) : observable;
    }
}
