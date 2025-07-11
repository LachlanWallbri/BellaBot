package com.squareup.okhttp.internal;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class NamedRunnable implements Runnable {
    protected final String name;

    protected abstract void execute();

    public NamedRunnable(String str, Object... objArr) {
        this.name = String.format(str, objArr);
    }

    @Override // java.lang.Runnable
    public final void run() {
        String name = Thread.currentThread().getName();
        Thread.currentThread().setName(this.name);
        try {
            execute();
        } finally {
            Thread.currentThread().setName(name);
        }
    }
}
