package io.grpc.netty.shaded.io.netty.handler.codec.http;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes7.dex */
public final class ClientCookieEncoder {
    @Deprecated
    public static String encode(String str, String str2) {
        return io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode(str, str2);
    }

    @Deprecated
    public static String encode(Cookie cookie) {
        return io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode(cookie);
    }

    @Deprecated
    public static String encode(Cookie... cookieArr) {
        return io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode(cookieArr);
    }

    @Deprecated
    public static String encode(Iterable<Cookie> iterable) {
        return io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.ClientCookieEncoder.LAX.encode(iterable);
    }

    private ClientCookieEncoder() {
    }
}
