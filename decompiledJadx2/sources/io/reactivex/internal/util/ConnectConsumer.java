package io.reactivex.internal.util;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
  classes8.dex
 */
/* loaded from: classes2.dex */
public final class ConnectConsumer implements Consumer<Disposable> {
    public Disposable disposable;

    @Override // io.reactivex.functions.Consumer
    public void accept(Disposable disposable) throws Exception {
        this.disposable = disposable;
    }
}
