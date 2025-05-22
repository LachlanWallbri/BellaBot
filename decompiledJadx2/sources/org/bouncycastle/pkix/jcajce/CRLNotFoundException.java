package org.bouncycastle.pkix.jcajce;

import java.security.cert.CertPathValidatorException;

/* loaded from: classes9.dex */
class CRLNotFoundException extends CertPathValidatorException {
    /* JADX INFO: Access modifiers changed from: package-private */
    public CRLNotFoundException(String str) {
        super(str);
    }

    public CRLNotFoundException(String str, Throwable th) {
        super(str, th);
    }
}
