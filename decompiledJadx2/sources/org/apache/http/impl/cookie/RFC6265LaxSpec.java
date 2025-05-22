package org.apache.http.impl.cookie;

import org.apache.http.cookie.CommonCookieAttributeHandler;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class RFC6265LaxSpec extends RFC6265CookieSpecBase {
    public String toString() {
        return "rfc6265-lax";
    }

    public RFC6265LaxSpec() {
        super(new BasicPathHandler(), new BasicDomainHandler(), new LaxMaxAgeHandler(), new BasicSecureHandler(), new LaxExpiresHandler());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RFC6265LaxSpec(CommonCookieAttributeHandler... commonCookieAttributeHandlerArr) {
        super(commonCookieAttributeHandlerArr);
    }
}
