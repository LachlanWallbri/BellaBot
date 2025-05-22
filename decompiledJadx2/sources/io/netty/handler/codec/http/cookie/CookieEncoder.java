package io.netty.handler.codec.http.cookie;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public abstract class CookieEncoder {
    protected final boolean strict;

    /* JADX INFO: Access modifiers changed from: protected */
    public CookieEncoder(boolean z) {
        this.strict = z;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void validateCookie(String str, String str2) {
        if (this.strict) {
            int firstInvalidCookieNameOctet = CookieUtil.firstInvalidCookieNameOctet(str);
            if (firstInvalidCookieNameOctet >= 0) {
                throw new IllegalArgumentException("Cookie name contains an invalid char: " + str.charAt(firstInvalidCookieNameOctet));
            }
            CharSequence unwrapValue = CookieUtil.unwrapValue(str2);
            if (unwrapValue == null) {
                throw new IllegalArgumentException("Cookie value wrapping quotes are not balanced: " + str2);
            }
            int firstInvalidCookieValueOctet = CookieUtil.firstInvalidCookieValueOctet(unwrapValue);
            if (firstInvalidCookieValueOctet < 0) {
                return;
            }
            throw new IllegalArgumentException("Cookie value contains an invalid char: " + str2.charAt(firstInvalidCookieValueOctet));
        }
    }
}
