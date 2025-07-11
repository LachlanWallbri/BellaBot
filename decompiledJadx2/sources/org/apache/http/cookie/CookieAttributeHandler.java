package org.apache.http.cookie;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface CookieAttributeHandler {
    boolean match(Cookie cookie, CookieOrigin cookieOrigin);

    void parse(SetCookie setCookie, String str) throws MalformedCookieException;

    void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException;
}
