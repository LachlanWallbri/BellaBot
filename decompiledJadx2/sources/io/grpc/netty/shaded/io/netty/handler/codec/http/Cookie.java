package io.grpc.netty.shaded.io.netty.handler.codec.http;

import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public interface Cookie extends io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie {
    @Deprecated
    String comment();

    @Deprecated
    String commentUrl();

    @Deprecated
    String getComment();

    @Deprecated
    String getCommentUrl();

    @Deprecated
    String getDomain();

    @Deprecated
    long getMaxAge();

    @Deprecated
    String getName();

    @Deprecated
    String getPath();

    @Deprecated
    Set<Integer> getPorts();

    @Deprecated
    String getValue();

    @Deprecated
    int getVersion();

    @Deprecated
    boolean isDiscard();

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie
    @Deprecated
    long maxAge();

    @Deprecated
    Set<Integer> ports();

    @Deprecated
    void setComment(String str);

    @Deprecated
    void setCommentUrl(String str);

    @Deprecated
    void setDiscard(boolean z);

    @Override // io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie
    @Deprecated
    void setMaxAge(long j);

    @Deprecated
    void setPorts(Iterable<Integer> iterable);

    @Deprecated
    void setPorts(int... iArr);

    @Deprecated
    void setVersion(int i);

    @Deprecated
    int version();
}
