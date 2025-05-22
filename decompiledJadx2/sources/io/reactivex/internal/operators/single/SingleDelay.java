package io.reactivex.internal.operators.single;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class SingleDelay<T> extends Single<T> {
    final boolean delayError;
    final Scheduler scheduler;
    final SingleSource<? extends T> source;
    final long time;
    final TimeUnit unit;

    public SingleDelay(SingleSource<? extends T> singleSource, long j, TimeUnit timeUnit, Scheduler scheduler, boolean z) {
        this.source = singleSource;
        this.time = j;
        this.unit = timeUnit;
        this.scheduler = scheduler;
        this.delayError = z;
    }

    @Override // io.reactivex.Single
    protected void subscribeActual(SingleObserver<? super T> singleObserver) {
        SequentialDisposable sequentialDisposable = new SequentialDisposable();
        singleObserver.onSubscribe(sequentialDisposable);
        this.source.subscribe(new Delay(sequentialDisposable, singleObserver));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    final class Delay implements SingleObserver<T> {
        final SingleObserver<? super T> downstream;

        /* renamed from: sd */
        private final SequentialDisposable f8714sd;

        Delay(SequentialDisposable sequentialDisposable, SingleObserver<? super T> singleObserver) {
            this.f8714sd = sequentialDisposable;
            this.downstream = singleObserver;
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            this.f8714sd.replace(disposable);
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            this.f8714sd.replace(SingleDelay.this.scheduler.scheduleDirect(new OnSuccess(t), SingleDelay.this.time, SingleDelay.this.unit));
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.f8714sd.replace(SingleDelay.this.scheduler.scheduleDirect(new OnError(th), SingleDelay.this.delayError ? SingleDelay.this.time : 0L, SingleDelay.this.unit));
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* loaded from: classes2.dex */
        final class OnSuccess implements Runnable {
            private final T value;

            OnSuccess(T t) {
                this.value = t;
            }

            @Override // java.lang.Runnable
            public void run() {
                Delay.this.downstream.onSuccess(this.value);
            }
        }

        /* JADX WARN: Classes with same name are omitted:
          classes7.dex
          classes8.dex
         */
        /* loaded from: classes2.dex */
        final class OnError implements Runnable {

            /* renamed from: e */
            private final Throwable f8715e;

            OnError(Throwable th) {
                this.f8715e = th;
            }

            @Override // java.lang.Runnable
            public void run() {
                Delay.this.downstream.onError(this.f8715e);
            }
        }
    }
}
