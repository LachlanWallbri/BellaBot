package org.conscrypt;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public interface CertPinManager {
    void checkChainPinning(String str, List<X509Certificate> list) throws CertificateException;
}
