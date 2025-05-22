package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class CompletableFromObservable<T> extends Completable {
    final ObservableSource<T> observable;

    public CompletableFromObservable(ObservableSource<T> observableSource) {
        this.observable = observableSource;
    }

    @Override // io.reactivex.Completable
    protected void subscribeActual(CompletableObserver completableObserver) {
        this.observable.subscribe(new CompletableFromObservableObserver(completableObserver));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
      classes4.dex
      classes8.dex
     */
    /* loaded from: classes.dex */
    static final class CompletableFromObservableObserver<T> implements Observer<T> {

        /* renamed from: co */
        final CompletableObserver f8654co;

        @Override // io.reactivex.Observer
        public void onNext(T t) {
        }

        CompletableFromObservableObserver(CompletableObserver completableObserver) {
            this.f8654co = completableObserver;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
            this.f8654co.onSubscribe(disposable);
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
            this.f8654co.onError(th);
        }

        @Override // io.reactivex.Observer
        public void onComplete() {
            this.f8654co.onComplete();
        }
    }
}
