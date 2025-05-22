package com.squareup.okhttp.internal.http;

import java.io.IOException;
import okio.Sink;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface CacheRequest {
    void abort();

    Sink body() throws IOException;
}
