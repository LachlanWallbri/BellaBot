package io.opencensus.common;

import java.io.Closeable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes8.dex */
public interface NonThrowingCloseable extends Closeable {
    @Override // java.io.Closeable, java.lang.AutoCloseable
    void close();
}
