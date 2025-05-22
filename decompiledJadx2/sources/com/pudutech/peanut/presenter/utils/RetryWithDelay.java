package com.pudutech.peanut.presenter.utils;

import com.pudutech.base.Pdlog;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class RetryWithDelay implements Function<Observable<? extends Throwable>, Observable<?>> {
    private int maxRetries;
    private int retryCount;
    private int retryDelayMillis;

    static /* synthetic */ int access$004(RetryWithDelay retryWithDelay) {
        int i = retryWithDelay.retryCount + 1;
        retryWithDelay.retryCount = i;
        return i;
    }

    public RetryWithDelay() {
        this.maxRetries = 10;
        this.retryDelayMillis = 1000;
        this.retryCount = 0;
    }

    public RetryWithDelay(int i, int i2) {
        this.maxRetries = 10;
        this.retryDelayMillis = 1000;
        this.retryCount = 0;
        this.maxRetries = i;
        this.retryDelayMillis = i2;
    }

    @Override // io.reactivex.functions.Function
    public Observable<?> apply(Observable<? extends Throwable> observable) {
        return observable.flatMap(new Function<Throwable, ObservableSource<?>>() { // from class: com.pudutech.peanut.presenter.utils.RetryWithDelay.1
            @Override // io.reactivex.functions.Function
            public ObservableSource<?> apply(Throwable th) {
                if (RetryWithDelay.access$004(RetryWithDelay.this) <= RetryWithDelay.this.maxRetries) {
                    Pdlog.m3273d("RetryWithDelay", "get error, it will try after " + (RetryWithDelay.this.retryDelayMillis * RetryWithDelay.this.retryCount) + " millisecond, retry count " + RetryWithDelay.this.retryCount);
                    return Observable.timer(RetryWithDelay.this.retryDelayMillis * RetryWithDelay.this.retryCount, TimeUnit.MILLISECONDS, Schedulers.trampoline());
                }
                return Observable.error(th);
            }
        });
    }
}
