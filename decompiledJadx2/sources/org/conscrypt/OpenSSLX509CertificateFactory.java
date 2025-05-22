package org.conscrypt;

import com.pudutech.peanut.robot_ui.bluetooth.printutil.PrinterUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.security.cert.CRL;
import java.security.cert.CRLException;
import java.security.cert.CertPath;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactorySpi;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.compress.archivers.tar.TarConstants;

/* JADX WARN: Classes with same name are omitted:
  classes7.dex
 */
/* loaded from: classes9.dex */
public class OpenSSLX509CertificateFactory extends CertificateFactorySpi {
    private static final byte[] PKCS7_MARKER = {45, 45, 45, 45, 45, 66, 69, 71, PrinterUtils.BarCode.CODE128, 78, 32, 80, TarConstants.LF_GNUTYPE_LONGLINK, 67, TarConstants.LF_GNUTYPE_SPARSE, TarConstants.LF_CONTIG};
    private static final int PUSHBACK_SIZE = 64;
    private Parser<OpenSSLX509Certificate> certificateParser = new Parser<OpenSSLX509Certificate>() { // from class: org.conscrypt.OpenSSLX509CertificateFactory.1
        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public OpenSSLX509Certificate fromX509PemInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509Certificate.fromX509PemInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public OpenSSLX509Certificate fromX509DerInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509Certificate.fromX509DerInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public List<? extends OpenSSLX509Certificate> fromPkcs7PemInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509Certificate.fromPkcs7PemInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public List<? extends OpenSSLX509Certificate> fromPkcs7DerInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509Certificate.fromPkcs7DerInputStream(inputStream);
        }
    };
    private Parser<OpenSSLX509CRL> crlParser = new Parser<OpenSSLX509CRL>() { // from class: org.conscrypt.OpenSSLX509CertificateFactory.2
        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public OpenSSLX509CRL fromX509PemInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509CRL.fromX509PemInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public OpenSSLX509CRL fromX509DerInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509CRL.fromX509DerInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public List<? extends OpenSSLX509CRL> fromPkcs7PemInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509CRL.fromPkcs7PemInputStream(inputStream);
        }

        @Override // org.conscrypt.OpenSSLX509CertificateFactory.Parser
        public List<? extends OpenSSLX509CRL> fromPkcs7DerInputStream(InputStream inputStream) throws ParsingException {
            return OpenSSLX509CRL.fromPkcs7DerInputStream(inputStream);
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    public static class ParsingException extends Exception {
        private static final long serialVersionUID = 8390802697728301325L;

        /* JADX INFO: Access modifiers changed from: package-private */
        public ParsingException(String str) {
            super(str);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public ParsingException(Exception exc) {
            super(exc);
        }

        ParsingException(String str, Exception exc) {
            super(str, exc);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes7.dex
     */
    /* loaded from: classes9.dex */
    private static abstract class Parser<T> {
        protected abstract List<? extends T> fromPkcs7DerInputStream(InputStream inputStream) throws ParsingException;

        protected abstract List<? extends T> fromPkcs7PemInputStream(InputStream inputStream) throws ParsingException;

        protected abstract T fromX509DerInputStream(InputStream inputStream) throws ParsingException;

        protected abstract T fromX509PemInputStream(InputStream inputStream) throws ParsingException;

        private Parser() {
        }

        T generateItem(InputStream inputStream) throws ParsingException {
            if (inputStream == null) {
                throw new ParsingException("inStream == null");
            }
            boolean markSupported = inputStream.markSupported();
            if (markSupported) {
                inputStream.mark(OpenSSLX509CertificateFactory.PKCS7_MARKER.length);
            }
            PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 64);
            try {
                byte[] bArr = new byte[OpenSSLX509CertificateFactory.PKCS7_MARKER.length];
                int read = pushbackInputStream.read(bArr);
                if (read < 0) {
                    throw new ParsingException("inStream is empty");
                }
                pushbackInputStream.unread(bArr, 0, read);
                if (bArr[0] == 45) {
                    if (read == OpenSSLX509CertificateFactory.PKCS7_MARKER.length && Arrays.equals(OpenSSLX509CertificateFactory.PKCS7_MARKER, bArr)) {
                        List<? extends T> fromPkcs7PemInputStream = fromPkcs7PemInputStream(pushbackInputStream);
                        if (fromPkcs7PemInputStream.size() == 0) {
                            return null;
                        }
                        fromPkcs7PemInputStream.get(0);
                    } else {
                        return fromX509PemInputStream(pushbackInputStream);
                    }
                }
                if (bArr[4] == 6) {
                    List<? extends T> fromPkcs7DerInputStream = fromPkcs7DerInputStream(pushbackInputStream);
                    if (fromPkcs7DerInputStream.size() == 0) {
                        return null;
                    }
                    return fromPkcs7DerInputStream.get(0);
                }
                return fromX509DerInputStream(pushbackInputStream);
            } catch (Exception e) {
                if (markSupported) {
                    try {
                        inputStream.reset();
                    } catch (IOException unused) {
                    }
                }
                throw new ParsingException(e);
            }
        }

        Collection<? extends T> generateItems(InputStream inputStream) throws ParsingException {
            T t;
            if (inputStream == null) {
                throw new ParsingException("inStream == null");
            }
            try {
                if (inputStream.available() == 0) {
                    return new ArrayList();
                }
                boolean markSupported = inputStream.markSupported();
                if (markSupported) {
                    inputStream.mark(64);
                }
                PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream, 64);
                try {
                    byte[] bArr = new byte[OpenSSLX509CertificateFactory.PKCS7_MARKER.length];
                    int read = pushbackInputStream.read(bArr);
                    if (read < 0) {
                        throw new ParsingException("inStream is empty");
                    }
                    pushbackInputStream.unread(bArr, 0, read);
                    if (read == OpenSSLX509CertificateFactory.PKCS7_MARKER.length && Arrays.equals(OpenSSLX509CertificateFactory.PKCS7_MARKER, bArr)) {
                        return fromPkcs7PemInputStream(pushbackInputStream);
                    }
                    if (bArr[4] == 6) {
                        return fromPkcs7DerInputStream(pushbackInputStream);
                    }
                    ArrayList arrayList = new ArrayList();
                    do {
                        if (markSupported) {
                            inputStream.mark(64);
                        }
                        try {
                            t = generateItem(pushbackInputStream);
                            arrayList.add(t);
                        } catch (ParsingException unused) {
                            if (markSupported) {
                                try {
                                    inputStream.reset();
                                } catch (IOException unused2) {
                                }
                            }
                            t = null;
                        }
                    } while (t != null);
                    return arrayList;
                } catch (Exception e) {
                    if (markSupported) {
                        try {
                            inputStream.reset();
                        } catch (IOException unused3) {
                        }
                    }
                    throw new ParsingException(e);
                }
            } catch (IOException e2) {
                throw new ParsingException("Problem reading input stream", e2);
            }
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Certificate engineGenerateCertificate(InputStream inputStream) throws CertificateException {
        try {
            return this.certificateParser.generateItem(inputStream);
        } catch (ParsingException e) {
            throw new CertificateException(e);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Collection<? extends Certificate> engineGenerateCertificates(InputStream inputStream) throws CertificateException {
        try {
            return this.certificateParser.generateItems(inputStream);
        } catch (ParsingException e) {
            throw new CertificateException(e);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CRL engineGenerateCRL(InputStream inputStream) throws CRLException {
        try {
            return this.crlParser.generateItem(inputStream);
        } catch (ParsingException e) {
            throw new CRLException(e);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Collection<? extends CRL> engineGenerateCRLs(InputStream inputStream) throws CRLException {
        if (inputStream == null) {
            return Collections.emptyList();
        }
        try {
            return this.crlParser.generateItems(inputStream);
        } catch (ParsingException e) {
            throw new CRLException(e);
        }
    }

    @Override // java.security.cert.CertificateFactorySpi
    public Iterator<String> engineGetCertPathEncodings() {
        return OpenSSLX509CertPath.getEncodingsIterator();
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(InputStream inputStream) throws CertificateException {
        return OpenSSLX509CertPath.fromEncoding(inputStream);
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(InputStream inputStream, String str) throws CertificateException {
        return OpenSSLX509CertPath.fromEncoding(inputStream, str);
    }

    @Override // java.security.cert.CertificateFactorySpi
    public CertPath engineGenerateCertPath(List<? extends Certificate> list) throws CertificateException {
        ArrayList arrayList = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            Certificate certificate = list.get(i);
            if (!(certificate instanceof X509Certificate)) {
                throw new CertificateException("Certificate not X.509 type at index " + i);
            }
            arrayList.add((X509Certificate) certificate);
        }
        return new OpenSSLX509CertPath(arrayList);
    }
}
