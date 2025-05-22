package com.google.api.core;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface ApiAsyncFunction<I, O> {
    ApiFuture<O> apply(I i) throws Exception;
}
