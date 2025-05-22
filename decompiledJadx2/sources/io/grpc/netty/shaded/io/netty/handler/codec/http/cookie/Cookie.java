package io.grpc.netty.shaded.io.netty.handler.codec.http.cookie;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface Cookie extends Comparable<Cookie> {
    public static final long UNDEFINED_MAX_AGE = Long.MIN_VALUE;

    String domain();

    boolean isHttpOnly();

    boolean isSecure();

    long maxAge();

    String name();

    String path();

    void setDomain(String str);

    void setHttpOnly(boolean z);

    void setMaxAge(long j);

    void setPath(String str);

    void setSecure(boolean z);

    void setValue(String str);

    void setWrap(boolean z);

    String value();

    boolean wrap();
}
