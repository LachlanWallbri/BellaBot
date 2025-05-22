package com.aliyun.alink.p022h2.api;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface CompletableListener<T> {
    void complete(T t);

    void completeExceptionally(Throwable th);
}
