package io.netty.handler.codec.http;

import java.util.Collection;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
@Deprecated
/* loaded from: classes.dex */
public final class ServerCookieEncoder {
    @Deprecated
    public static String encode(String str, String str2) {
        return io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(str, str2);
    }

    @Deprecated
    public static String encode(Cookie cookie) {
        return io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(cookie);
    }

    @Deprecated
    public static List<String> encode(Cookie... cookieArr) {
        return io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(cookieArr);
    }

    @Deprecated
    public static List<String> encode(Collection<Cookie> collection) {
        return io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode((Collection<? extends io.netty.handler.codec.http.cookie.Cookie>) collection);
    }

    @Deprecated
    public static List<String> encode(Iterable<Cookie> iterable) {
        return io.netty.handler.codec.http.cookie.ServerCookieEncoder.LAX.encode(iterable);
    }

    private ServerCookieEncoder() {
    }
}
