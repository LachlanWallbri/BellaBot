package io.reactivex.internal.operators.single;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Notification;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class SingleDematerialize<T, R> extends Maybe<R> {
    final Function<? super T, Notification<R>> selector;
    final Single<T> source;

    public SingleDematerialize(Single<T> single, Function<? super T, Notification<R>> function) {
        this.source = single;
        this.selector = function;
    }

    @Override // io.reactivex.Maybe
    protected void subscribeActual(MaybeObserver<? super R> maybeObserver) {
        this.source.subscribe(new DematerializeObserver(maybeObserver, this.selector));
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
      classes8.dex
     */
    /* loaded from: classes2.dex */
    static final class DematerializeObserver<T, R> implements SingleObserver<T>, Disposable {
        final MaybeObserver<? super R> downstream;
        final Function<? super T, Notification<R>> selector;
        Disposable upstream;

        DematerializeObserver(MaybeObserver<? super R> maybeObserver, Function<? super T, Notification<R>> function) {
            this.downstream = maybeObserver;
            this.selector = function;
        }

        @Override // io.reactivex.disposables.Disposable
        public void dispose() {
            this.upstream.dispose();
        }

        @Override // io.reactivex.disposables.Disposable
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        @Override // io.reactivex.SingleObserver
        public void onSubscribe(Disposable disposable) {
            if (DisposableHelper.validate(this.upstream, disposable)) {
                this.upstream = disposable;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.SingleObserver
        public void onSuccess(T t) {
            try {
                Notification notification = (Notification) ObjectHelper.requireNonNull(this.selector.apply(t), "The selector returned a null Notification");
                if (notification.isOnNext()) {
                    this.downstream.onSuccess((Object) notification.getValue());
                } else if (notification.isOnComplete()) {
                    this.downstream.onComplete();
                } else {
                    this.downstream.onError(notification.getError());
                }
            } catch (Throwable th) {
                Exceptions.throwIfFatal(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.SingleObserver
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }
    }
}
