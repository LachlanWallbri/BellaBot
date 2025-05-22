package com.aliyun.alink.dm.api;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public interface ResultCallback<T> {
    public static final int ERROR = -1;
    public static final int SUCCESS = 0;

    void onRusult(int i, T t);
}
