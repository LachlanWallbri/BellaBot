package com.google.api.core;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public final class SettableApiFuture<V> extends AbstractApiFuture<V> {
    private SettableApiFuture() {
    }

    public static <V> SettableApiFuture<V> create() {
        return new SettableApiFuture<>();
    }

    @Override // com.google.api.core.AbstractApiFuture
    public boolean set(V v) {
        return super.set(v);
    }

    @Override // com.google.api.core.AbstractApiFuture
    public boolean setException(Throwable th) {
        return super.setException(th);
    }
}
