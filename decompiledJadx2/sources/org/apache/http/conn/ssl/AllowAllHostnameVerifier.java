package org.apache.http.conn.ssl;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
@Deprecated
/* loaded from: classes9.dex */
public class AllowAllHostnameVerifier extends AbstractVerifier {
    public static final AllowAllHostnameVerifier INSTANCE = new AllowAllHostnameVerifier();

    public final String toString() {
        return "ALLOW_ALL";
    }

    @Override // org.apache.http.conn.ssl.X509HostnameVerifier
    public final void verify(String str, String[] strArr, String[] strArr2) {
    }
}
