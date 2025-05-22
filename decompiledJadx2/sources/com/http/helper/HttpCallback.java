package com.http.helper;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* loaded from: classes.dex */
public interface HttpCallback<T, K> {
    void onFail(String str, T t);

    void onSuccess(String str, K k);
}
