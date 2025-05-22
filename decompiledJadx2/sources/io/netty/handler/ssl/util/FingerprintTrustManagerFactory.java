package io.netty.handler.ssl.util;

import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.StringUtil;
import java.security.KeyStore;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class FingerprintTrustManagerFactory extends SimpleTrustManagerFactory {
    private static final int SHA1_BYTE_LEN = 20;
    private static final int SHA1_HEX_LEN = 40;
    private final byte[][] fingerprints;

    /* renamed from: tm */
    private final TrustManager f8562tm;
    private static final Pattern FINGERPRINT_PATTERN = Pattern.compile("^[0-9a-fA-F:]+$");
    private static final Pattern FINGERPRINT_STRIP_PATTERN = Pattern.compile(":");
    private static final FastThreadLocal<MessageDigest> tlmd = new FastThreadLocal<MessageDigest>() { // from class: io.netty.handler.ssl.util.FingerprintTrustManagerFactory.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // io.netty.util.concurrent.FastThreadLocal
        public MessageDigest initialValue() {
            try {
                return MessageDigest.getInstance("SHA1");
            } catch (NoSuchAlgorithmException e) {
                throw new Error(e);
            }
        }
    };

    @Override // io.netty.handler.ssl.util.SimpleTrustManagerFactory
    protected void engineInit(KeyStore keyStore) throws Exception {
    }

    @Override // io.netty.handler.ssl.util.SimpleTrustManagerFactory
    protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws Exception {
    }

    public FingerprintTrustManagerFactory(Iterable<String> iterable) {
        this(toFingerprintArray(iterable));
    }

    public FingerprintTrustManagerFactory(String... strArr) {
        this(toFingerprintArray(Arrays.asList(strArr)));
    }

    public FingerprintTrustManagerFactory(byte[]... bArr) {
        this.f8562tm = new X509TrustManager() { // from class: io.netty.handler.ssl.util.FingerprintTrustManagerFactory.2
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                checkTrusted("client", x509CertificateArr);
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
                checkTrusted("server", x509CertificateArr);
            }

            private void checkTrusted(String str, X509Certificate[] x509CertificateArr) throws CertificateException {
                boolean z = false;
                X509Certificate x509Certificate = x509CertificateArr[0];
                byte[] fingerprint = fingerprint(x509Certificate);
                byte[][] bArr2 = FingerprintTrustManagerFactory.this.fingerprints;
                int length = bArr2.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (Arrays.equals(fingerprint, bArr2[i])) {
                        z = true;
                        break;
                    }
                    i++;
                }
                if (z) {
                    return;
                }
                throw new CertificateException(str + " certificate with unknown fingerprint: " + x509Certificate.getSubjectDN());
            }

            private byte[] fingerprint(X509Certificate x509Certificate) throws CertificateEncodingException {
                MessageDigest messageDigest = (MessageDigest) FingerprintTrustManagerFactory.tlmd.get();
                messageDigest.reset();
                return messageDigest.digest(x509Certificate.getEncoded());
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return EmptyArrays.EMPTY_X509_CERTIFICATES;
            }
        };
        if (bArr == null) {
            throw new NullPointerException("fingerprints");
        }
        ArrayList arrayList = new ArrayList(bArr.length);
        for (byte[] bArr2 : bArr) {
            if (bArr2 == null) {
                break;
            }
            if (bArr2.length != 20) {
                throw new IllegalArgumentException("malformed fingerprint: " + ByteBufUtil.hexDump(Unpooled.wrappedBuffer(bArr2)) + " (expected: SHA1)");
            }
            arrayList.add(bArr2.clone());
        }
        this.fingerprints = (byte[][]) arrayList.toArray(new byte[arrayList.size()]);
    }

    private static byte[][] toFingerprintArray(Iterable<String> iterable) {
        String next;
        if (iterable == null) {
            throw new NullPointerException("fingerprints");
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = iterable.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            if (!FINGERPRINT_PATTERN.matcher(next).matches()) {
                throw new IllegalArgumentException("malformed fingerprint: " + next);
            }
            String replaceAll = FINGERPRINT_STRIP_PATTERN.matcher(next).replaceAll("");
            if (replaceAll.length() != 40) {
                throw new IllegalArgumentException("malformed fingerprint: " + replaceAll + " (expected: SHA1)");
            }
            arrayList.add(StringUtil.decodeHexDump(replaceAll));
        }
        return (byte[][]) arrayList.toArray(new byte[arrayList.size()]);
    }

    @Override // io.netty.handler.ssl.util.SimpleTrustManagerFactory
    protected TrustManager[] engineGetTrustManagers() {
        return new TrustManager[]{this.f8562tm};
    }
}
