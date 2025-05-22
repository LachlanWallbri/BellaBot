package io.grpc.netty.shaded.io.netty.handler.codec.http;

import java.util.Collection;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public final class ServerCookieEncoder {
    @Deprecated
    public static String encode(String str, String str2) {
        return io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(str, str2);
    }

    @Deprecated
    public static String encode(Cookie cookie) {
        return io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(cookie);
    }

    @Deprecated
    public static List<String> encode(Cookie... cookieArr) {
        return io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(cookieArr);
    }

    @Deprecated
    public static List<String> encode(Collection<Cookie> collection) {
        return io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode((Collection<? extends io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.Cookie>) collection);
    }

    @Deprecated
    public static List<String> encode(Iterable<Cookie> iterable) {
        return io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(iterable);
    }

    private ServerCookieEncoder() {
    }
}
