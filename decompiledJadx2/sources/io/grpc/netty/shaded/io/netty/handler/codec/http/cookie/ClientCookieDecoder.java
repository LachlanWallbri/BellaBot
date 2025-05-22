package io.grpc.netty.shaded.io.netty.handler.codec.http.cookie;

import io.grpc.netty.shaded.io.netty.handler.codec.DateFormatter;
import io.grpc.netty.shaded.io.netty.handler.codec.http.cookie.CookieHeaderNames;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ClientCookieDecoder extends CookieDecoder {
    public static final ClientCookieDecoder STRICT = new ClientCookieDecoder(true);
    public static final ClientCookieDecoder LAX = new ClientCookieDecoder(false);

    private ClientCookieDecoder(boolean z) {
        super(z);
    }

    public Cookie decode(String str) {
        int i;
        int i2;
        int i3;
        int i4;
        int length = ((String) ObjectUtil.checkNotNull(str, "header")).length();
        if (length == 0) {
            return null;
        }
        CookieBuilder cookieBuilder = null;
        int i5 = 0;
        while (i5 != length) {
            char charAt = str.charAt(i5);
            if (charAt == ',') {
                break;
            }
            if (charAt == '\t' || charAt == '\n' || charAt == 11 || charAt == '\f' || charAt == '\r' || charAt == ' ' || charAt == ';') {
                i5++;
            } else {
                int i6 = i5;
                while (true) {
                    char charAt2 = str.charAt(i6);
                    i = -1;
                    if (charAt2 == ';') {
                        i2 = i6;
                        i3 = i2;
                        break;
                    }
                    if (charAt2 == '=') {
                        i4 = i6 + 1;
                        if (i4 == length) {
                            i = 0;
                            i2 = i6;
                            i3 = i4;
                        } else {
                            int indexOf = str.indexOf(59, i4);
                            if (indexOf <= 0) {
                                indexOf = length;
                            }
                            i = indexOf;
                            i3 = i;
                            i2 = i6;
                        }
                    } else {
                        i6++;
                        if (i6 == length) {
                            i2 = length;
                            i3 = i6;
                            break;
                        }
                    }
                }
                i4 = i;
                if (i > 0 && str.charAt(i - 1) == ',') {
                    i--;
                }
                if (cookieBuilder == null) {
                    DefaultCookie initCookie = initCookie(str, i5, i2, i4, i);
                    if (initCookie == null) {
                        return null;
                    }
                    cookieBuilder = new CookieBuilder(initCookie, str);
                } else {
                    cookieBuilder.appendAttribute(i5, i2, i4, i);
                }
                i5 = i3;
            }
        }
        if (cookieBuilder != null) {
            return cookieBuilder.cookie();
        }
        return null;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class CookieBuilder {
        private final DefaultCookie cookie;
        private String domain;
        private int expiresEnd;
        private int expiresStart;
        private final String header;
        private boolean httpOnly;
        private long maxAge = Long.MIN_VALUE;
        private String path;
        private CookieHeaderNames.SameSite sameSite;
        private boolean secure;

        private static boolean isValueDefined(int i, int i2) {
            return (i == -1 || i == i2) ? false : true;
        }

        CookieBuilder(DefaultCookie defaultCookie, String str) {
            this.cookie = defaultCookie;
            this.header = str;
        }

        private long mergeMaxAgeAndExpires() {
            Date parseHttpDate;
            long j = this.maxAge;
            if (j != Long.MIN_VALUE) {
                return j;
            }
            if (!isValueDefined(this.expiresStart, this.expiresEnd) || (parseHttpDate = DateFormatter.parseHttpDate(this.header, this.expiresStart, this.expiresEnd)) == null) {
                return Long.MIN_VALUE;
            }
            long time = parseHttpDate.getTime() - System.currentTimeMillis();
            return (time / 1000) + (time % 1000 != 0 ? 1 : 0);
        }

        Cookie cookie() {
            this.cookie.setDomain(this.domain);
            this.cookie.setPath(this.path);
            this.cookie.setMaxAge(mergeMaxAgeAndExpires());
            this.cookie.setSecure(this.secure);
            this.cookie.setHttpOnly(this.httpOnly);
            this.cookie.setSameSite(this.sameSite);
            return this.cookie;
        }

        void appendAttribute(int i, int i2, int i3, int i4) {
            int i5 = i2 - i;
            if (i5 == 4) {
                parse4(i, i3, i4);
                return;
            }
            if (i5 == 6) {
                parse6(i, i3, i4);
            } else if (i5 == 7) {
                parse7(i, i3, i4);
            } else if (i5 == 8) {
                parse8(i, i3, i4);
            }
        }

        private void parse4(int i, int i2, int i3) {
            if (this.header.regionMatches(true, i, "Path", 0, 4)) {
                this.path = computeValue(i2, i3);
            }
        }

        private void parse6(int i, int i2, int i3) {
            if (this.header.regionMatches(true, i, "Domain", 0, 5)) {
                this.domain = computeValue(i2, i3);
            } else if (this.header.regionMatches(true, i, "Secure", 0, 5)) {
                this.secure = true;
            }
        }

        private void setMaxAge(String str) {
            try {
                this.maxAge = Math.max(Long.parseLong(str), 0L);
            } catch (NumberFormatException unused) {
            }
        }

        private void parse7(int i, int i2, int i3) {
            if (this.header.regionMatches(true, i, "Expires", 0, 7)) {
                this.expiresStart = i2;
                this.expiresEnd = i3;
            } else if (this.header.regionMatches(true, i, "Max-Age", 0, 7)) {
                setMaxAge(computeValue(i2, i3));
            }
        }

        private void parse8(int i, int i2, int i3) {
            if (this.header.regionMatches(true, i, "HTTPOnly", 0, 8)) {
                this.httpOnly = true;
            } else if (this.header.regionMatches(true, i, CookieHeaderNames.SAMESITE, 0, 8)) {
                this.sameSite = CookieHeaderNames.SameSite.m3915of(computeValue(i2, i3));
            }
        }

        private String computeValue(int i, int i2) {
            if (isValueDefined(i, i2)) {
                return this.header.substring(i, i2);
            }
            return null;
        }
    }
}
