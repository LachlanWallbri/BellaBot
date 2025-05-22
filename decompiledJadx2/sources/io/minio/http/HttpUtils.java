package io.minio.http;

import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import io.minio.routines.InetAddressValidator;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.apache.http.HttpHost;

/* loaded from: classes7.dex */
public class HttpUtils {
    public static final byte[] EMPTY_BODY = new byte[0];

    public static void validateNotNull(Object obj, String str) {
        if (obj != null) {
            return;
        }
        throw new IllegalArgumentException(str + " must not be null.");
    }

    public static void validateNotEmptyString(String str, String str2) {
        validateNotNull(str, str2);
        if (str.isEmpty()) {
            throw new IllegalArgumentException(str2 + " must be a non-empty string.");
        }
    }

    public static void validateNullOrNotEmptyString(String str, String str2) {
        if (str == null || !str.isEmpty()) {
            return;
        }
        throw new IllegalArgumentException(str2 + " must be a non-empty string.");
    }

    public static void validateHostnameOrIPAddress(String str) {
        if (InetAddressValidator.getInstance().isValid(str)) {
            return;
        }
        if (str.length() < 1 || str.length() > 253) {
            throw new IllegalArgumentException("invalid hostname");
        }
        for (String str2 : str.split("\\.")) {
            if (str2.length() < 1 || str2.length() > 63) {
                throw new IllegalArgumentException("invalid hostname");
            }
            if (!str2.matches("^[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?$")) {
                throw new IllegalArgumentException("invalid hostname");
            }
        }
    }

    public static void validateUrl(HttpUrl httpUrl) {
        if (httpUrl.encodedPath().equals("/")) {
            return;
        }
        throw new IllegalArgumentException("no path allowed in endpoint " + httpUrl);
    }

    public static HttpUrl getBaseUrl(String str) {
        validateNotEmptyString(str, "endpoint");
        HttpUrl parse = HttpUrl.parse(str);
        if (parse == null) {
            validateHostnameOrIPAddress(str);
            return new HttpUrl.Builder().scheme("https").host(str).build();
        }
        validateUrl(parse);
        return parse;
    }

    public static String getHostHeader(HttpUrl httpUrl) {
        if ((httpUrl.scheme().equals(HttpHost.DEFAULT_SCHEME_NAME) && httpUrl.port() == 80) || (httpUrl.scheme().equals("https") && httpUrl.port() == 443)) {
            return httpUrl.host();
        }
        return httpUrl.host() + ":" + httpUrl.port();
    }

    public static OkHttpClient enableExternalCertificates(OkHttpClient okHttpClient, String str) throws GeneralSecurityException, IOException {
        FileInputStream fileInputStream = new FileInputStream(str);
        try {
            Collection<? extends Certificate> generateCertificates = CertificateFactory.getInstance("X.509").generateCertificates(fileInputStream);
            fileInputStream.close();
            if (generateCertificates == null || generateCertificates.isEmpty()) {
                throw new IllegalArgumentException("expected non-empty set of trusted certificates");
            }
            char[] charArray = CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD.toCharArray();
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, charArray);
            Iterator<? extends Certificate> it = generateCertificates.iterator();
            int i = 0;
            while (it.hasNext()) {
                keyStore.setCertificateEntry(Integer.toString(i), it.next());
                i++;
            }
            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keyStore, charArray);
            TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            trustManagerFactory.init(keyStore);
            KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
            TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
            SSLContext sSLContext = SSLContext.getInstance("TLS");
            sSLContext.init(keyManagers, trustManagers, null);
            return okHttpClient.newBuilder().sslSocketFactory(sSLContext.getSocketFactory(), (X509TrustManager) trustManagers[0]).build();
        } catch (Throwable th) {
            try {
                fileInputStream.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static OkHttpClient newDefaultHttpClient(long j, long j2, long j3) {
        OkHttpClient build = new OkHttpClient().newBuilder().connectTimeout(j, TimeUnit.MILLISECONDS).writeTimeout(j2, TimeUnit.MILLISECONDS).readTimeout(j3, TimeUnit.MILLISECONDS).protocols(Arrays.asList(Protocol.HTTP_1_1)).build();
        String str = System.getenv("SSL_CERT_FILE");
        if (str == null || str.isEmpty()) {
            return build;
        }
        try {
            return enableExternalCertificates(build, str);
        } catch (IOException | GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public static OkHttpClient disableCertCheck(OkHttpClient okHttpClient) throws KeyManagementException, NoSuchAlgorithmException {
        TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: io.minio.http.HttpUtils.1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) throws CertificateException {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};
        SSLContext sSLContext = SSLContext.getInstance("SSL");
        sSLContext.init(null, trustManagerArr, new SecureRandom());
        return okHttpClient.newBuilder().sslSocketFactory(sSLContext.getSocketFactory(), (X509TrustManager) trustManagerArr[0]).hostnameVerifier(new HostnameVerifier() { // from class: io.minio.http.HttpUtils.2
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str, SSLSession sSLSession) {
                return true;
            }
        }).build();
    }

    public static OkHttpClient setTimeout(OkHttpClient okHttpClient, long j, long j2, long j3) {
        return okHttpClient.newBuilder().connectTimeout(j, TimeUnit.MILLISECONDS).writeTimeout(j2, TimeUnit.MILLISECONDS).readTimeout(j3, TimeUnit.MILLISECONDS).build();
    }
}
