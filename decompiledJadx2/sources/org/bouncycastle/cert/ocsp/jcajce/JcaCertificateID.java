package org.bouncycastle.cert.ocsp.jcajce;

import java.math.BigInteger;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import org.bouncycastle.cert.jcajce.JcaX509CertificateHolder;
import org.bouncycastle.cert.ocsp.CertificateID;
import org.bouncycastle.cert.ocsp.OCSPException;
import org.bouncycastle.operator.DigestCalculator;

/* loaded from: classes9.dex */
public class JcaCertificateID extends CertificateID {
    public JcaCertificateID(DigestCalculator digestCalculator, X509Certificate x509Certificate, BigInteger bigInteger) throws OCSPException, CertificateEncodingException {
        super(digestCalculator, new JcaX509CertificateHolder(x509Certificate), bigInteger);
    }
}
