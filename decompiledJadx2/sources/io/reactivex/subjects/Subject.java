package io.reactivex.subjects;

import io.reactivex.Observable;
import io.reactivex.Observer;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public abstract class Subject<T> extends Observable<T> implements Observer<T> {
    public abstract Throwable getThrowable();

    public abstract boolean hasComplete();

    public abstract boolean hasObservers();

    public abstract boolean hasThrowable();

    public final Subject<T> toSerialized() {
        return this instanceof SerializedSubject ? this : new SerializedSubject(this);
    }
}
