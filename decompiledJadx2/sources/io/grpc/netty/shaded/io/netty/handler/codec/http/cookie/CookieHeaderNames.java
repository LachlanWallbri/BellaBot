package io.grpc.netty.shaded.io.netty.handler.codec.http.cookie;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class CookieHeaderNames {
    public static final String DOMAIN = "Domain";
    public static final String EXPIRES = "Expires";
    public static final String HTTPONLY = "HTTPOnly";
    public static final String MAX_AGE = "Max-Age";
    public static final String PATH = "Path";
    public static final String SAMESITE = "SameSite";
    public static final String SECURE = "Secure";

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public enum SameSite {
        Lax,
        Strict,
        None;

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: of */
        public static SameSite m3915of(String str) {
            if (str == null) {
                return null;
            }
            for (SameSite sameSite : (SameSite[]) SameSite.class.getEnumConstants()) {
                if (sameSite.name().equalsIgnoreCase(str)) {
                    return sameSite;
                }
            }
            return null;
        }
    }

    private CookieHeaderNames() {
    }
}
