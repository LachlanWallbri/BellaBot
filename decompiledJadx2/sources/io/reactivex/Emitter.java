package io.reactivex;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface Emitter<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);
}
