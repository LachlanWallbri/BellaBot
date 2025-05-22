package com.amazonaws.async;

/* loaded from: classes.dex */
public interface Callback<R> {
    void onError(Exception exc);

    void onResult(R r);
}
