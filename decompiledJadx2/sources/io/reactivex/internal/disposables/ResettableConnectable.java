package io.reactivex.internal.disposables;

import io.reactivex.disposables.Disposable;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public interface ResettableConnectable {
    void resetIf(Disposable disposable);
}
