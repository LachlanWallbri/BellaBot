package org.apache.http.pool;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface ConnFactory<T, C> {
    C create(T t) throws IOException;
}
