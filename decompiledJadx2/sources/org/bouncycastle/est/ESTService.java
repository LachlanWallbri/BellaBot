package org.bouncycastle.est;

import com.amazonaws.util.DateUtils;
import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.bouncycastle.asn1.ASN1InputStream;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERPrintableString;
import org.bouncycastle.asn1.cms.ContentInfo;
import org.bouncycastle.asn1.est.CsrAttrs;
import org.bouncycastle.asn1.pkcs.PKCSObjectIdentifiers;
import org.bouncycastle.cert.X509CRLHolder;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cmc.CMCException;
import org.bouncycastle.cmc.SimplePKIResponse;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.pkcs.PKCS10CertificationRequest;
import org.bouncycastle.pkcs.PKCS10CertificationRequestBuilder;
import org.bouncycastle.util.Selector;
import org.bouncycastle.util.Store;
import org.bouncycastle.util.encoders.Base64;

/* loaded from: classes9.dex */
public class ESTService {
    protected static final String CACERTS = "/cacerts";
    protected static final String CSRATTRS = "/csrattrs";
    protected static final String FULLCMC = "/fullcmc";
    protected static final String SERVERGEN = "/serverkeygen";
    protected static final String SIMPLE_ENROLL = "/simpleenroll";
    protected static final String SIMPLE_REENROLL = "/simplereenroll";
    protected static final Set<String> illegalParts = new HashSet();
    private static final Pattern pathInValid;
    private final ESTClientProvider clientProvider;
    private final String server;

    static {
        illegalParts.add("cacerts");
        illegalParts.add("simpleenroll");
        illegalParts.add("simplereenroll");
        illegalParts.add("fullcmc");
        illegalParts.add("serverkeygen");
        illegalParts.add("csrattrs");
        pathInValid = Pattern.compile("^[0-9a-zA-Z_\\-.~!$&'()*+,;:=]+");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ESTService(String str, String str2, ESTClientProvider eSTClientProvider) {
        String str3;
        String verifyServer = verifyServer(str);
        if (str2 != null) {
            str3 = OSSConfig.PREFIX_HTTPS + verifyServer + "/.well-known/est/" + verifyLabel(str2);
        } else {
            str3 = OSSConfig.PREFIX_HTTPS + verifyServer + "/.well-known/est";
        }
        this.server = str3;
        this.clientProvider = eSTClientProvider;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String annotateRequest(byte[] bArr) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        int i = 0;
        do {
            int i2 = i + 48;
            if (i2 < bArr.length) {
                printWriter.print(Base64.toBase64String(bArr, i, 48));
                i = i2;
            } else {
                printWriter.print(Base64.toBase64String(bArr, i, bArr.length - i));
                i = bArr.length;
            }
            printWriter.print('\n');
        } while (i < bArr.length);
        printWriter.flush();
        return stringWriter.toString();
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store) {
        return storeToArray(store, null);
    }

    public static X509CertificateHolder[] storeToArray(Store<X509CertificateHolder> store, Selector<X509CertificateHolder> selector) {
        Collection<X509CertificateHolder> matches = store.getMatches(selector);
        return (X509CertificateHolder[]) matches.toArray(new X509CertificateHolder[matches.size()]);
    }

    private String verifyLabel(String str) {
        while (str.endsWith("/") && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        while (str.startsWith("/") && str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() == 0) {
            throw new IllegalArgumentException("Label set but after trimming '/' is not zero length string.");
        }
        if (!pathInValid.matcher(str).matches()) {
            throw new IllegalArgumentException("Server path " + str + " contains invalid characters");
        }
        if (!illegalParts.contains(str)) {
            return str;
        }
        throw new IllegalArgumentException("Label " + str + " is a reserved path segment.");
    }

    private String verifyServer(String str) {
        while (str.endsWith("/") && str.length() > 0) {
            try {
                str = str.substring(0, str.length() - 1);
            } catch (Exception e) {
                if (e instanceof IllegalArgumentException) {
                    throw ((IllegalArgumentException) e);
                }
                throw new IllegalArgumentException("Scheme and host is invalid: " + e.getMessage(), e);
            }
        }
        if (str.contains("://")) {
            throw new IllegalArgumentException("Server contains scheme, must only be <dnsname/ipaddress>:port, https:// will be added arbitrarily.");
        }
        URL url = new URL(OSSConfig.PREFIX_HTTPS + str);
        if (url.getPath().length() != 0 && !url.getPath().equals("/")) {
            throw new IllegalArgumentException("Server contains path, must only be <dnsname/ipaddress>:port, a path of '/.well-known/est/<label>' will be added arbitrarily.");
        }
        return str;
    }

    public CACertsResponse getCACerts() throws Exception {
        ESTResponse eSTResponse;
        URL url;
        ESTRequest build;
        Store<X509CertificateHolder> store;
        Store<X509CRLHolder> store2;
        Store<X509CertificateHolder> store3;
        Store<X509CRLHolder> store4;
        String str;
        try {
            url = new URL(this.server + CACERTS);
            ESTClient makeClient = this.clientProvider.makeClient();
            build = new ESTRequestBuilder("GET", url).withClient(makeClient).build();
            eSTResponse = makeClient.doRequest(build);
        } catch (Throwable th) {
            th = th;
            eSTResponse = null;
        }
        try {
            if (eSTResponse.getStatusCode() == 200) {
                if (!"application/pkcs7-mime".equals(eSTResponse.getHeaders().getFirstValue("Content-Type"))) {
                    if (eSTResponse.getHeaders().getFirstValue("Content-Type") != null) {
                        str = " got " + eSTResponse.getHeaders().getFirstValue("Content-Type");
                    } else {
                        str = " but was not present.";
                    }
                    throw new ESTException("Response : " + url.toString() + "Expecting application/pkcs7-mime " + str, null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
                }
                try {
                    if (eSTResponse.getContentLength() == null || eSTResponse.getContentLength().longValue() <= 0) {
                        store3 = null;
                        store4 = null;
                    } else {
                        SimplePKIResponse simplePKIResponse = new SimplePKIResponse(ContentInfo.getInstance((ASN1Sequence) new ASN1InputStream(eSTResponse.getInputStream()).readObject()));
                        store3 = simplePKIResponse.getCertificates();
                        store4 = simplePKIResponse.getCRLs();
                    }
                    store = store3;
                    store2 = store4;
                } catch (Throwable th2) {
                    throw new ESTException("Decoding CACerts: " + url.toString() + " " + th2.getMessage(), th2, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
                }
            } else {
                if (eSTResponse.getStatusCode() != 204) {
                    throw new ESTException("Get CACerts: " + url.toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
                }
                store = null;
                store2 = null;
            }
            CACertsResponse cACertsResponse = new CACertsResponse(store, store2, build, eSTResponse.getSource(), this.clientProvider.isTrusted());
            if (eSTResponse != null) {
                try {
                    eSTResponse.close();
                } catch (Exception e) {
                    e = e;
                }
            }
            e = null;
            if (e == null) {
                return cACertsResponse;
            }
            if (e instanceof ESTException) {
                throw e;
            }
            throw new ESTException("Get CACerts: " + url.toString(), e, eSTResponse.getStatusCode(), null);
        } catch (Throwable th3) {
            th = th3;
            try {
                if (th instanceof ESTException) {
                    throw th;
                }
                throw new ESTException(th.getMessage(), th);
            } catch (Throwable th4) {
                if (eSTResponse != null) {
                    try {
                        eSTResponse.close();
                    } catch (Exception unused) {
                    }
                }
                throw th4;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00c2  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00a4 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public CSRRequestResponse getCSRAttributes() throws ESTException {
        ESTResponse eSTResponse;
        ESTException th;
        URL url;
        ESTRequest build;
        CSRAttributesResponse cSRAttributesResponse;
        if (!this.clientProvider.isTrusted()) {
            throw new IllegalStateException("No trust anchors.");
        }
        try {
            url = new URL(this.server + CSRATTRS);
            ESTClient makeClient = this.clientProvider.makeClient();
            build = new ESTRequestBuilder("GET", url).withClient(makeClient).build();
            eSTResponse = makeClient.doRequest(build);
        } catch (Throwable th2) {
            eSTResponse = null;
            th = th2;
        }
        try {
            int statusCode = eSTResponse.getStatusCode();
            if (statusCode == 200) {
                try {
                    if (eSTResponse.getContentLength() != null && eSTResponse.getContentLength().longValue() > 0) {
                        cSRAttributesResponse = new CSRAttributesResponse(CsrAttrs.getInstance(ASN1Sequence.getInstance(new ASN1InputStream(eSTResponse.getInputStream()).readObject())));
                        if (eSTResponse != null) {
                            try {
                                eSTResponse.close();
                            } catch (Exception e) {
                                e = e;
                            }
                        }
                        e = null;
                        if (e != null) {
                            return new CSRRequestResponse(cSRAttributesResponse, eSTResponse.getSource());
                        }
                        if (e instanceof ESTException) {
                            throw ((ESTException) e);
                        }
                        throw new ESTException(e.getMessage(), e, eSTResponse.getStatusCode(), null);
                    }
                } catch (Throwable th3) {
                    throw new ESTException("Decoding CACerts: " + url.toString() + " " + th3.getMessage(), th3, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
                }
            } else if (statusCode != 204 && statusCode != 404) {
                throw new ESTException("CSR Attribute request: " + build.getURL().toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
            }
            cSRAttributesResponse = null;
            if (eSTResponse != null) {
            }
            e = null;
            if (e != null) {
            }
        } catch (Throwable th4) {
            th = th4;
            try {
                if (th instanceof ESTException) {
                    throw th;
                }
                throw new ESTException(th.getMessage(), th);
            } catch (Throwable th5) {
                if (eSTResponse != null) {
                    try {
                        eSTResponse.close();
                    } catch (Exception unused) {
                    }
                }
                throw th5;
            }
        }
    }

    protected EnrollmentResponse handleEnrollResponse(ESTResponse eSTResponse) throws IOException {
        long time;
        ESTRequest originalRequest = eSTResponse.getOriginalRequest();
        if (eSTResponse.getStatusCode() != 202) {
            if (eSTResponse.getStatusCode() == 200) {
                try {
                    return new EnrollmentResponse(new SimplePKIResponse(ContentInfo.getInstance(new ASN1InputStream(eSTResponse.getInputStream()).readObject())).getCertificates(), -1L, null, eSTResponse.getSource());
                } catch (CMCException e) {
                    throw new ESTException(e.getMessage(), e.getCause());
                }
            }
            throw new ESTException("Simple Enroll: " + originalRequest.getURL().toString(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
        }
        String header = eSTResponse.getHeader("Retry-After");
        if (header == null) {
            throw new ESTException("Got Status 202 but not Retry-After header from: " + originalRequest.getURL().toString());
        }
        try {
            try {
                time = System.currentTimeMillis() + (Long.parseLong(header) * 1000);
            } catch (Exception e2) {
                throw new ESTException("Unable to parse Retry-After header:" + originalRequest.getURL().toString() + " " + e2.getMessage(), null, eSTResponse.getStatusCode(), eSTResponse.getInputStream());
            }
        } catch (NumberFormatException unused) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.RFC822_DATE_PATTERN, Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            time = simpleDateFormat.parse(header).getTime();
        }
        return new EnrollmentResponse(null, time, originalRequest, eSTResponse.getSource());
    }

    public EnrollmentResponse simpleEnroll(EnrollmentResponse enrollmentResponse) throws Exception {
        if (!this.clientProvider.isTrusted()) {
            throw new IllegalStateException("No trust anchors.");
        }
        ESTResponse eSTResponse = null;
        try {
            ESTClient makeClient = this.clientProvider.makeClient();
            eSTResponse = makeClient.doRequest(new ESTRequestBuilder(enrollmentResponse.getRequestToRetry()).withClient(makeClient).build());
            return handleEnrollResponse(eSTResponse);
        } catch (Throwable th) {
            try {
                if (th instanceof ESTException) {
                    throw th;
                }
                throw new ESTException(th.getMessage(), th);
            } finally {
                if (eSTResponse != null) {
                    eSTResponse.close();
                }
            }
        }
    }

    public EnrollmentResponse simpleEnroll(boolean z, PKCS10CertificationRequest pKCS10CertificationRequest, ESTAuth eSTAuth) throws IOException {
        if (!this.clientProvider.isTrusted()) {
            throw new IllegalStateException("No trust anchors.");
        }
        ESTResponse eSTResponse = null;
        try {
            byte[] bytes = annotateRequest(pKCS10CertificationRequest.getEncoded()).getBytes();
            StringBuilder sb = new StringBuilder();
            sb.append(this.server);
            sb.append(z ? SIMPLE_REENROLL : SIMPLE_ENROLL);
            URL url = new URL(sb.toString());
            ESTClient makeClient = this.clientProvider.makeClient();
            ESTRequestBuilder withClient = new ESTRequestBuilder("POST", url).withData(bytes).withClient(makeClient);
            withClient.addHeader("Content-Type", "application/pkcs10");
            withClient.addHeader("Content-Length", "" + bytes.length);
            withClient.addHeader("Content-Transfer-Encoding", "base64");
            if (eSTAuth != null) {
                eSTAuth.applyAuth(withClient);
            }
            eSTResponse = makeClient.doRequest(withClient.build());
            return handleEnrollResponse(eSTResponse);
        } catch (Throwable th) {
            try {
                if (th instanceof ESTException) {
                    throw th;
                }
                throw new ESTException(th.getMessage(), th);
            } finally {
                if (eSTResponse != null) {
                    eSTResponse.close();
                }
            }
        }
    }

    public EnrollmentResponse simpleEnrollPoP(boolean z, final PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder, final ContentSigner contentSigner, ESTAuth eSTAuth) throws IOException {
        if (!this.clientProvider.isTrusted()) {
            throw new IllegalStateException("No trust anchors.");
        }
        ESTResponse eSTResponse = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(this.server);
            sb.append(z ? SIMPLE_REENROLL : SIMPLE_ENROLL);
            URL url = new URL(sb.toString());
            ESTClient makeClient = this.clientProvider.makeClient();
            ESTRequestBuilder withConnectionListener = new ESTRequestBuilder("POST", url).withClient(makeClient).withConnectionListener(new ESTSourceConnectionListener() { // from class: org.bouncycastle.est.ESTService.1
                @Override // org.bouncycastle.est.ESTSourceConnectionListener
                public ESTRequest onConnection(Source source, ESTRequest eSTRequest) throws IOException {
                    if (source instanceof TLSUniqueProvider) {
                        TLSUniqueProvider tLSUniqueProvider = (TLSUniqueProvider) source;
                        if (tLSUniqueProvider.isTLSUniqueAvailable()) {
                            PKCS10CertificationRequestBuilder pKCS10CertificationRequestBuilder2 = new PKCS10CertificationRequestBuilder(pKCS10CertificationRequestBuilder);
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            pKCS10CertificationRequestBuilder2.setAttribute(PKCSObjectIdentifiers.pkcs_9_at_challengePassword, new DERPrintableString(Base64.toBase64String(tLSUniqueProvider.getTLSUnique())));
                            byteArrayOutputStream.write(ESTService.this.annotateRequest(pKCS10CertificationRequestBuilder2.build(contentSigner).getEncoded()).getBytes());
                            byteArrayOutputStream.flush();
                            ESTRequestBuilder withData = new ESTRequestBuilder(eSTRequest).withData(byteArrayOutputStream.toByteArray());
                            withData.setHeader("Content-Type", "application/pkcs10");
                            withData.setHeader("Content-Transfer-Encoding", "base64");
                            withData.setHeader("Content-Length", Long.toString(byteArrayOutputStream.size()));
                            return withData.build();
                        }
                    }
                    throw new IOException("Source does not supply TLS unique.");
                }
            });
            if (eSTAuth != null) {
                eSTAuth.applyAuth(withConnectionListener);
            }
            eSTResponse = makeClient.doRequest(withConnectionListener.build());
            return handleEnrollResponse(eSTResponse);
        } catch (Throwable th) {
            try {
                if (th instanceof ESTException) {
                    throw th;
                }
                throw new ESTException(th.getMessage(), th);
            } finally {
                if (eSTResponse != null) {
                    eSTResponse.close();
                }
            }
        }
    }
}
