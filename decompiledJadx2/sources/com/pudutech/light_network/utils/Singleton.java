package com.pudutech.light_network.utils;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
public abstract class Singleton<T, P> {
    private volatile T mInstance;

    protected abstract T create(Context context, P p);

    public final T get(Context context, P p) {
        if (this.mInstance == null) {
            synchronized (this) {
                if (this.mInstance == null) {
                    this.mInstance = create(context, p);
                }
            }
        }
        return this.mInstance;
    }

    public final void clear() {
        this.mInstance = null;
    }
}
