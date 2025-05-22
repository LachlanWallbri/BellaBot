package io.reactivex;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface ObservableConverter<T, R> {
    R apply(Observable<T> observable);
}
