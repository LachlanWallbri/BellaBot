package com.google.api.core;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ApiFutureCallback<V> {
    void onFailure(Throwable th);

    void onSuccess(V v);
}
