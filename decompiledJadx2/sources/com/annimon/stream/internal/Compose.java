package com.annimon.stream.internal;

import java.io.Closeable;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes.dex */
public final class Compose {
    private Compose() {
    }

    public static Runnable runnables(final Runnable runnable, final Runnable runnable2) {
        return new Runnable() { // from class: com.annimon.stream.internal.Compose.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    runnable.run();
                    runnable2.run();
                } catch (Throwable th) {
                    try {
                        runnable2.run();
                    } catch (Throwable unused) {
                    }
                    if (th instanceof RuntimeException) {
                        throw ((RuntimeException) th);
                    }
                    throw ((Error) th);
                }
            }
        };
    }

    public static Runnable closeables(final Closeable closeable, final Closeable closeable2) {
        return new Runnable() { // from class: com.annimon.stream.internal.Compose.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    closeable.close();
                    try {
                        closeable2.close();
                    } catch (Throwable th) {
                        if (th instanceof RuntimeException) {
                            throw ((RuntimeException) th);
                        }
                        if (th instanceof Error) {
                            throw ((Error) th);
                        }
                        throw new RuntimeException(th);
                    }
                } catch (Throwable th2) {
                    try {
                        closeable2.close();
                    } catch (Throwable unused) {
                    }
                    if (th2 instanceof RuntimeException) {
                        throw ((RuntimeException) th2);
                    }
                    throw ((Error) th2);
                }
            }
        };
    }
}
