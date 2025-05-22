package com.google.api.client.http;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  
 */
@Deprecated
/* loaded from: classes2.dex */
public interface BackOffPolicy {
    public static final long STOP = -1;

    long getNextBackOffMillis() throws IOException;

    boolean isBackOffRequired(int i);

    void reset();
}
