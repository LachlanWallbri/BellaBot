package io.reactivex.internal.util;

import io.reactivex.Observer;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public interface ObservableQueueDrain<T, U> {
    void accept(Observer<? super U> observer, T t);

    boolean cancelled();

    boolean done();

    boolean enter();

    Throwable error();

    int leave(int i);
}
