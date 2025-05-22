package io.opencensus.contrib.http;

import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class HttpExtractor<Q, P> {
    @Nullable
    public abstract String getHost(Q q);

    @Nullable
    public abstract String getMethod(Q q);

    @Nullable
    public abstract String getPath(Q q);

    @Nullable
    public abstract String getRoute(Q q);

    public abstract int getStatusCode(@Nullable P p);

    @Nullable
    public abstract String getUrl(Q q);

    @Nullable
    public abstract String getUserAgent(Q q);
}
