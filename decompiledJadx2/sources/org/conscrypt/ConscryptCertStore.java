package org.conscrypt;

import java.security.cert.X509Certificate;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface ConscryptCertStore {
    Set<X509Certificate> findAllIssuers(X509Certificate x509Certificate);

    X509Certificate getTrustAnchor(X509Certificate x509Certificate);
}
