package org.apache.http.impl.cookie;

import java.util.Locale;
import org.apache.http.cookie.CommonCookieAttributeHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieRestrictionViolationException;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SetCookie;
import org.apache.http.util.Args;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class RFC2109DomainHandler implements CommonCookieAttributeHandler {
    @Override // org.apache.http.cookie.CommonCookieAttributeHandler
    public String getAttributeName() {
        return "domain";
    }

    @Override // org.apache.http.cookie.CookieAttributeHandler
    public void parse(SetCookie setCookie, String str) throws MalformedCookieException {
        Args.notNull(setCookie, "Cookie");
        if (str == null) {
            throw new MalformedCookieException("Missing value for domain attribute");
        }
        if (str.trim().isEmpty()) {
            throw new MalformedCookieException("Blank value for domain attribute");
        }
        setCookie.setDomain(str);
    }

    @Override // org.apache.http.cookie.CookieAttributeHandler
    public void validate(Cookie cookie, CookieOrigin cookieOrigin) throws MalformedCookieException {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        String host = cookieOrigin.getHost();
        String domain = cookie.getDomain();
        if (domain == null) {
            throw new CookieRestrictionViolationException("Cookie domain may not be null");
        }
        if (domain.equals(host)) {
            return;
        }
        if (domain.indexOf(46) == -1) {
            throw new CookieRestrictionViolationException("Domain attribute \"" + domain + "\" does not match the host \"" + host + "\"");
        }
        if (!domain.startsWith(".")) {
            throw new CookieRestrictionViolationException("Domain attribute \"" + domain + "\" violates RFC 2109: domain must start with a dot");
        }
        int indexOf = domain.indexOf(46, 1);
        if (indexOf < 0 || indexOf == domain.length() - 1) {
            throw new CookieRestrictionViolationException("Domain attribute \"" + domain + "\" violates RFC 2109: domain must contain an embedded dot");
        }
        String lowerCase = host.toLowerCase(Locale.ROOT);
        if (!lowerCase.endsWith(domain)) {
            throw new CookieRestrictionViolationException("Illegal domain attribute \"" + domain + "\". Domain of origin: \"" + lowerCase + "\"");
        }
        if (lowerCase.substring(0, lowerCase.length() - domain.length()).indexOf(46) == -1) {
            return;
        }
        throw new CookieRestrictionViolationException("Domain attribute \"" + domain + "\" violates RFC 2109: host minus domain may not contain any dots");
    }

    @Override // org.apache.http.cookie.CookieAttributeHandler
    public boolean match(Cookie cookie, CookieOrigin cookieOrigin) {
        Args.notNull(cookie, "Cookie");
        Args.notNull(cookieOrigin, "Cookie origin");
        String host = cookieOrigin.getHost();
        String domain = cookie.getDomain();
        if (domain == null) {
            return false;
        }
        return host.equals(domain) || (domain.startsWith(".") && host.endsWith(domain));
    }
}
