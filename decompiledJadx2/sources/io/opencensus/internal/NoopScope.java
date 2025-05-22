package io.opencensus.internal;

import io.opencensus.common.Scope;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class NoopScope implements Scope {
    private static final Scope INSTANCE = new NoopScope();

    @Override // io.opencensus.common.Scope, io.opencensus.common.NonThrowingCloseable, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    private NoopScope() {
    }

    public static Scope getInstance() {
        return INSTANCE;
    }
}
