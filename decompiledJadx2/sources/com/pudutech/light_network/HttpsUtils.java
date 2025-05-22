package com.pudutech.light_network;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.content.pm.SigningInfo;
import android.os.Build;
import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.amazonaws.services.p048s3.internal.crypto.JceEncryptionConstants;
import com.iflytek.aiui.AIUIConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.light_network.utils.PuduTrustManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: HttpsUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00032\u00020\u0001:\u0005\u0003\u0004\u0005\u0006\u0007B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\b"}, m3961d2 = {"Lcom/pudutech/light_network/HttpsUtils;", "", "()V", "Companion", "MyTrustManager", "SSLParams", "UnSafeHostnameVerifier", "UnSafeTrustManager", "lib_network_release"}, m3962k = 1, m3963mv = {1, 1, 15})
/* loaded from: classes.dex */
public final class HttpsUtils {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: HttpsUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J#\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\nJ#\u0010\u000b\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\nJ\u0013\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0016¢\u0006\u0002\u0010\r¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/light_network/HttpsUtils$UnSafeTrustManager;", "Ljavax/net/ssl/X509TrustManager;", "()V", "checkClientTrusted", "", "chain", "", "Ljava/security/cert/X509Certificate;", "authType", "", "([Ljava/security/cert/X509Certificate;Ljava/lang/String;)V", "checkServerTrusted", "getAcceptedIssuers", "()[Ljava/security/cert/X509Certificate;", "lib_network_release"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes.dex */
    private static final class UnSafeTrustManager implements X509TrustManager {
        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            Intrinsics.checkParameterIsNotNull(chain, "chain");
            Intrinsics.checkParameterIsNotNull(authType, "authType");
        }

        @Override // javax.net.ssl.X509TrustManager
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            Intrinsics.checkParameterIsNotNull(chain, "chain");
            Intrinsics.checkParameterIsNotNull(authType, "authType");
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: HttpsUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/light_network/HttpsUtils$SSLParams;", "", "()V", "sSLSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "getSSLSocketFactory", "()Ljavax/net/ssl/SSLSocketFactory;", "setSSLSocketFactory", "(Ljavax/net/ssl/SSLSocketFactory;)V", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "getTrustManager", "()Ljavax/net/ssl/X509TrustManager;", "setTrustManager", "(Ljavax/net/ssl/X509TrustManager;)V", "lib_network_release"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes.dex */
    public static final class SSLParams {
        private SSLSocketFactory sSLSocketFactory;
        private X509TrustManager trustManager;

        public final SSLSocketFactory getSSLSocketFactory() {
            return this.sSLSocketFactory;
        }

        public final void setSSLSocketFactory(SSLSocketFactory sSLSocketFactory) {
            this.sSLSocketFactory = sSLSocketFactory;
        }

        public final X509TrustManager getTrustManager() {
            return this.trustManager;
        }

        public final void setTrustManager(X509TrustManager x509TrustManager) {
            this.trustManager = x509TrustManager;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: HttpsUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/light_network/HttpsUtils$UnSafeHostnameVerifier;", "Ljavax/net/ssl/HostnameVerifier;", "(Lcom/pudutech/light_network/HttpsUtils;)V", "verify", "", "hostname", "", "session", "Ljavax/net/ssl/SSLSession;", "lib_network_release"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes.dex */
    private final class UnSafeHostnameVerifier implements HostnameVerifier {
        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String hostname, SSLSession session) {
            Intrinsics.checkParameterIsNotNull(hostname, "hostname");
            Intrinsics.checkParameterIsNotNull(session, "session");
            return true;
        }

        public UnSafeHostnameVerifier() {
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: HttpsUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003J#\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ#\u0010\f\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000bJ\u0015\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007H\u0016¢\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/light_network/HttpsUtils$MyTrustManager;", "Ljavax/net/ssl/X509TrustManager;", "localTrustManager", "(Ljavax/net/ssl/X509TrustManager;)V", "checkClientTrusted", "", "chain", "", "Ljava/security/cert/X509Certificate;", "authType", "", "([Ljava/security/cert/X509Certificate;Ljava/lang/String;)V", "checkServerTrusted", "getAcceptedIssuers", "()[Ljava/security/cert/X509Certificate;", "lib_network_release"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes.dex */
    private static final class MyTrustManager implements X509TrustManager {
        private final X509TrustManager localTrustManager;

        @Override // javax.net.ssl.X509TrustManager
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            Intrinsics.checkParameterIsNotNull(chain, "chain");
            Intrinsics.checkParameterIsNotNull(authType, "authType");
        }

        @Override // javax.net.ssl.X509TrustManager
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }

        public MyTrustManager(X509TrustManager localTrustManager) throws NoSuchAlgorithmException, KeyStoreException {
            Intrinsics.checkParameterIsNotNull(localTrustManager, "localTrustManager");
            this.localTrustManager = localTrustManager;
        }

        /* JADX WARN: Code restructure failed: missing block: B:21:0x003e, code lost:
        
            if (r6 != null) goto L23;
         */
        @Override // javax.net.ssl.X509TrustManager
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            String str;
            Intrinsics.checkParameterIsNotNull(chain, "chain");
            Intrinsics.checkParameterIsNotNull(authType, "authType");
            try {
                this.localTrustManager.checkServerTrusted(chain, authType);
            } catch (CertificateException e) {
                if (e.getCause() != null) {
                    Throwable cause = e.getCause();
                    if ((cause != null ? cause.getCause() : null) != null) {
                        Throwable cause2 = e.getCause();
                        if (cause2 != null) {
                            Throwable cause3 = cause2.getCause();
                            if (cause3 != null) {
                                Class<?> cls = cause3.getClass();
                                if (cls != null) {
                                    str = cls.getName();
                                }
                            }
                        }
                    }
                }
                str = "";
                if (!Intrinsics.areEqual(str, "")) {
                    String str2 = str;
                    if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "CertificateExpiredException", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) str2, (CharSequence) "CertificateNotYetValidException", false, 2, (Object) null)) {
                        return;
                    }
                }
                throw new CertificateException(e);
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* compiled from: HttpsUtils.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001d\u0010\u0007\u001a\u0004\u0018\u00010\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0002¢\u0006\u0002\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0018\u0010\u0013\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016J\u0018\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u000eH\u0002J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u000eH\u0002J\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001c\u001a\u00020\u001dH\u0004J!\u0010\u001e\u001a\u0004\u0018\u00010\u00042\u0012\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\n\"\u00020\u0012¢\u0006\u0002\u0010\u001fJ\u0012\u0010 \u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J)\u0010\u0005\u001a\u00020!2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00120\n2\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020$¢\u0006\u0002\u0010%J)\u0010&\u001a\n\u0012\u0004\u0012\u00020'\u0018\u00010\n2\b\u0010\"\u001a\u0004\u0018\u00010\u00122\b\u0010#\u001a\u0004\u0018\u00010$H\u0002¢\u0006\u0002\u0010(J)\u0010)\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\n2\u0012\u0010\u001c\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00120\n\"\u00020\u0012H\u0002¢\u0006\u0002\u0010*J*\u0010+\u001a\u00020,2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010-\u001a\u00020,2\b\u0010.\u001a\u0004\u0018\u00010\u00122\u0006\u0010/\u001a\u000200R\u0011\u0010\u0003\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u00061"}, m3961d2 = {"Lcom/pudutech/light_network/HttpsUtils$Companion;", "", "()V", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "getSslSocketFactory", "()Ljavax/net/ssl/SSLSocketFactory;", "chooseTrustManager", "Ljavax/net/ssl/X509TrustManager;", "trustManagers", "", "Ljavax/net/ssl/TrustManager;", "([Ljavax/net/ssl/TrustManager;)Ljavax/net/ssl/X509TrustManager;", "decodeByteArray", "", "context", "Landroid/content/Context;", "instream", "Ljava/io/InputStream;", "decrypt", AIUIConstant.KEY_CONTENT, TransferTable.COLUMN_KEY, "Ljava/security/interfaces/RSAPublicKey;", "getPrivateRawKey", "getPublicKey", "Ljava/security/PublicKey;", "signature", "getSSLSocketFactory", "certificates", "", "getSSLSocketFactoryForOneWay", "([Ljava/io/InputStream;)Ljavax/net/ssl/SSLSocketFactory;", "getSign", "Lcom/pudutech/light_network/HttpsUtils$SSLParams;", "bksFile", CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD, "", "([Ljava/io/InputStream;Ljava/io/InputStream;Ljava/lang/String;)Lcom/pudutech/light_network/HttpsUtils$SSLParams;", "prepareKeyManager", "Ljavax/net/ssl/KeyManager;", "(Ljava/io/InputStream;Ljava/lang/String;)[Ljavax/net/ssl/KeyManager;", "prepareTrustManager", "([Ljava/io/InputStream;)[Ljavax/net/ssl/TrustManager;", "setSuportHttpsParams", "Lokhttp3/OkHttpClient$Builder;", "builder", "certificateInput", "parseStream", "", "lib_network_release"}, m3962k = 1, m3963mv = {1, 1, 15})
    /* loaded from: classes.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SSLParams getSslSocketFactory(InputStream[] certificates, InputStream bksFile, String password) {
            UnSafeTrustManager unSafeTrustManager;
            Intrinsics.checkParameterIsNotNull(certificates, "certificates");
            Intrinsics.checkParameterIsNotNull(bksFile, "bksFile");
            Intrinsics.checkParameterIsNotNull(password, "password");
            SSLParams sSLParams = new SSLParams();
            try {
                TrustManager[] prepareTrustManager = prepareTrustManager((InputStream[]) Arrays.copyOf(certificates, certificates.length));
                KeyManager[] prepareKeyManager = prepareKeyManager(bksFile, password);
                SSLContext sslContext = SSLContext.getInstance("TLS");
                if (prepareTrustManager != null) {
                    X509TrustManager chooseTrustManager = chooseTrustManager(prepareTrustManager);
                    if (chooseTrustManager == null) {
                        Intrinsics.throwNpe();
                    }
                    unSafeTrustManager = new MyTrustManager(chooseTrustManager);
                } else {
                    unSafeTrustManager = new UnSafeTrustManager();
                }
                sslContext.init(prepareKeyManager, new TrustManager[]{unSafeTrustManager}, null);
                Intrinsics.checkExpressionValueIsNotNull(sslContext, "sslContext");
                sSLParams.setSSLSocketFactory(sslContext.getSocketFactory());
                sSLParams.setTrustManager(unSafeTrustManager);
                return sSLParams;
            } catch (KeyManagementException e) {
                throw new AssertionError(e);
            } catch (KeyStoreException e2) {
                throw new AssertionError(e2);
            } catch (NoSuchAlgorithmException e3) {
                throw new AssertionError(e3);
            }
        }

        private final TrustManager[] prepareTrustManager(InputStream... certificates) {
            if (certificates != null && certificates.length > 0) {
                try {
                    CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                    KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                    keyStore.load(null, null);
                    int length = certificates.length;
                    int i = 0;
                    int i2 = 0;
                    while (i < length) {
                        InputStream inputStream = certificates[i];
                        int i3 = i2 + 1;
                        keyStore.setCertificateEntry(Integer.toString(i2), certificateFactory.generateCertificate(inputStream));
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException unused) {
                            }
                        }
                        i++;
                        i2 = i3;
                    }
                    TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                    if (trustManagerFactory == null) {
                        Intrinsics.throwNpe();
                    }
                    trustManagerFactory.init(keyStore);
                    return trustManagerFactory.getTrustManagers();
                } catch (KeyStoreException e) {
                    e.printStackTrace();
                } catch (NoSuchAlgorithmException e2) {
                    e2.printStackTrace();
                } catch (CertificateException e3) {
                    e3.printStackTrace();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
            }
            return null;
        }

        private final KeyManager[] prepareKeyManager(InputStream bksFile, String password) {
            if (bksFile != null && password != null) {
                try {
                    KeyStore keyStore = KeyStore.getInstance("PKCS12");
                    char[] charArray = password.toCharArray();
                    Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
                    keyStore.load(bksFile, charArray);
                    KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
                    char[] charArray2 = password.toCharArray();
                    Intrinsics.checkExpressionValueIsNotNull(charArray2, "(this as java.lang.String).toCharArray()");
                    keyManagerFactory.init(keyStore, charArray2);
                    Intrinsics.checkExpressionValueIsNotNull(keyManagerFactory, "keyManagerFactory");
                    return keyManagerFactory.getKeyManagers();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (KeyStoreException e2) {
                    e2.printStackTrace();
                } catch (NoSuchAlgorithmException e3) {
                    e3.printStackTrace();
                } catch (UnrecoverableKeyException e4) {
                    e4.printStackTrace();
                } catch (CertificateException e5) {
                    e5.printStackTrace();
                } catch (Exception e6) {
                    e6.printStackTrace();
                }
            }
            return null;
        }

        private final X509TrustManager chooseTrustManager(TrustManager[] trustManagers) {
            for (TrustManager trustManager : trustManagers) {
                if (trustManager instanceof X509TrustManager) {
                    return (X509TrustManager) trustManager;
                }
            }
            return null;
        }

        public final SSLSocketFactory getSslSocketFactory() throws Exception {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.pudutech.light_network.HttpsUtils$Companion$sslSocketFactory$trustAllCerts$1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    Intrinsics.checkParameterIsNotNull(chain, "chain");
                    Intrinsics.checkParameterIsNotNull(authType, "authType");
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    Intrinsics.checkParameterIsNotNull(chain, "chain");
                    Intrinsics.checkParameterIsNotNull(authType, "authType");
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustManagerArr, new SecureRandom());
            Intrinsics.checkExpressionValueIsNotNull(sslContext, "sslContext");
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            Intrinsics.checkExpressionValueIsNotNull(socketFactory, "sslContext\n                    .socketFactory");
            return socketFactory;
        }

        protected final SSLSocketFactory getSSLSocketFactory(Context context, int[] certificates) {
            Intrinsics.checkParameterIsNotNull(certificates, "certificates");
            if (context == null) {
                throw new NullPointerException("context == null");
            }
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                Intrinsics.checkExpressionValueIsNotNull(certificateFactory, "CertificateFactory.getInstance(\"X.509\")");
                KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                keyStore.load(null, null);
                int length = certificates.length;
                for (int i = 0; i < length; i++) {
                    InputStream openRawResource = context.getResources().openRawResource(certificates[i]);
                    keyStore.setCertificateEntry(String.valueOf(i), certificateFactory.generateCertificate(openRawResource));
                    if (openRawResource != null) {
                        openRawResource.close();
                    }
                }
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                SSLContext sslContext = SSLContext.getInstance("TLS");
                Intrinsics.checkExpressionValueIsNotNull(trustManagerFactory, "trustManagerFactory");
                sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
                Intrinsics.checkExpressionValueIsNotNull(sslContext, "sslContext");
                return sslContext.getSocketFactory();
            } catch (Exception unused) {
                return null;
            }
        }

        public final SSLSocketFactory getSSLSocketFactoryForOneWay(InputStream... certificates) {
            Intrinsics.checkParameterIsNotNull(certificates, "certificates");
            try {
                CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
                KeyStore keyStore = KeyStore.getInstance("BKS");
                keyStore.load(null);
                int length = certificates.length;
                int i = 0;
                int i2 = 0;
                while (i < length) {
                    InputStream inputStream = certificates[i];
                    int i3 = i2 + 1;
                    keyStore.setCertificateEntry(Integer.toString(i2), certificateFactory.generateCertificate(inputStream));
                    if (inputStream != null) {
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    i++;
                    i2 = i3;
                }
                SSLContext sslContext = SSLContext.getInstance("TLS");
                TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                trustManagerFactory.init(keyStore);
                Intrinsics.checkExpressionValueIsNotNull(trustManagerFactory, "trustManagerFactory");
                sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());
                Intrinsics.checkExpressionValueIsNotNull(sslContext, "sslContext");
                return sslContext.getSocketFactory();
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public final OkHttpClient.Builder setSuportHttpsParams(Context context, OkHttpClient.Builder builder, InputStream certificateInput, boolean parseStream) throws CertificateException, NoSuchAlgorithmException, IOException {
            X509TrustManager x509TrustManager;
            Intrinsics.checkParameterIsNotNull(builder, "builder");
            if (certificateInput != null && context != null) {
                CertificateFactory certificateFactory = (CertificateFactory) null;
                try {
                    certificateFactory = CertificateFactory.getInstance("X.509");
                } catch (CertificateException e) {
                    e.printStackTrace();
                }
                if (certificateFactory == null) {
                    Intrinsics.throwNpe();
                }
                if (parseStream) {
                    certificateInput = new ByteArrayInputStream(decodeByteArray(context, certificateInput));
                }
                Certificate generateCertificate = certificateFactory.generateCertificate(certificateInput);
                KeyStore keyStore = (KeyStore) null;
                try {
                    keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
                } catch (KeyStoreException e2) {
                    e2.printStackTrace();
                }
                if (keyStore == null) {
                    Intrinsics.throwNpe();
                }
                keyStore.load(null, null);
                try {
                    keyStore.setCertificateEntry("certificate", generateCertificate);
                } catch (KeyStoreException e3) {
                    e3.printStackTrace();
                }
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
                try {
                    tmf.init(keyStore);
                } catch (KeyStoreException e4) {
                    e4.printStackTrace();
                }
                x509TrustManager = (X509TrustManager) null;
                Intrinsics.checkExpressionValueIsNotNull(tmf, "tmf");
                TrustManager[] trustManagers = tmf.getTrustManagers();
                int length = trustManagers.length;
                int i = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    TrustManager trustManager = trustManagers[i];
                    if (trustManager instanceof X509TrustManager) {
                        x509TrustManager = (X509TrustManager) trustManager;
                        break;
                    }
                    i++;
                }
            } else {
                x509TrustManager = null;
            }
            TrustManager[] trustManagerArr = {new PuduTrustManager(x509TrustManager)};
            SSLContext sslContext = SSLContext.getInstance("TLS");
            try {
                sslContext.init(null, trustManagerArr, new SecureRandom());
            } catch (KeyManagementException e5) {
                e5.printStackTrace();
            }
            Intrinsics.checkExpressionValueIsNotNull(sslContext, "sslContext");
            SSLSocketFactory socketFactory = sslContext.getSocketFactory();
            TrustManager trustManager2 = trustManagerArr[0];
            if (trustManager2 == null) {
                throw new TypeCastException("null cannot be cast to non-null type javax.net.ssl.X509TrustManager");
            }
            builder.sslSocketFactory(socketFactory, (X509TrustManager) trustManager2);
            return builder;
        }

        private final byte[] getSign(Context context) {
            PackageManager packageManager = context.getPackageManager();
            try {
                if (Build.VERSION.SDK_INT >= 28) {
                    SigningInfo signingInfo = packageManager.getPackageInfo(context.getPackageName(), 134217728).signingInfo;
                    if (signingInfo != null) {
                        return signingInfo.getSigningCertificateHistory()[0].toByteArray();
                    }
                    return null;
                }
                Signature[] signatureArr = packageManager.getPackageInfo(context.getPackageName(), 64).signatures;
                if (signatureArr != null) {
                    return signatureArr[0].toByteArray();
                }
                return null;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                return null;
            }
        }

        private final PublicKey getPublicKey(byte[] signature) {
            try {
                Certificate generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signature));
                if (generateCertificate == null) {
                    throw new TypeCastException("null cannot be cast to non-null type java.security.cert.X509Certificate");
                }
                return ((X509Certificate) generateCertificate).getPublicKey();
            } catch (CertificateException e) {
                e.printStackTrace();
                return null;
            }
        }

        public final byte[] decrypt(byte[] content, RSAPublicKey key) {
            Intrinsics.checkParameterIsNotNull(content, "content");
            Intrinsics.checkParameterIsNotNull(key, "key");
            try {
                Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
                cipher.init(2, key);
                int length = content.length;
                int bitLength = key.getModulus().bitLength() / 8;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream((((length + bitLength) - 12) / (bitLength - 11)) * bitLength);
                int i = 0;
                while (i < length) {
                    int i2 = length - i;
                    if (i2 > bitLength) {
                        cipher.update(content, i, bitLength);
                        i += bitLength;
                    } else {
                        cipher.update(content, i, i2);
                        i += i2;
                    }
                    byteArrayOutputStream.write(cipher.doFinal());
                }
                return byteArrayOutputStream.toByteArray();
            } catch (Exception e) {
                Pdlog.m3274e("Test", "rsa decrypt failed. " + e);
                return null;
            }
        }

        private final byte[] getPrivateRawKey(Context context, byte[] key) throws Exception {
            Companion companion = this;
            byte[] sign = companion.getSign(context);
            if (sign == null) {
                Intrinsics.throwNpe();
            }
            PublicKey publicKey = companion.getPublicKey(sign);
            if (publicKey == null) {
                throw new TypeCastException("null cannot be cast to non-null type java.security.interfaces.RSAPublicKey");
            }
            byte[] decrypt = companion.decrypt(key, (RSAPublicKey) publicKey);
            if (decrypt == null) {
                Intrinsics.throwNpe();
            }
            return decrypt;
        }

        private final byte[] decodeByteArray(Context context, InputStream instream) {
            byte[] bArr = new byte[1024];
            InputStream open = context.getAssets().open("pdtech.scr");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = open.read(bArr);
                if (read < 0) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            open.close();
            byte[] rsaKey = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            Intrinsics.checkExpressionValueIsNotNull(rsaKey, "rsaKey");
            SecretKeySpec secretKeySpec = new SecretKeySpec(getPrivateRawKey(context, rsaKey), JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(JceEncryptionConstants.SYMMETRIC_KEY_ALGORITHM);
            cipher.init(2, secretKeySpec);
            CipherInputStream cipherInputStream = new CipherInputStream(instream, cipher);
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            while (true) {
                int read2 = cipherInputStream.read(bArr);
                if (read2 >= 0) {
                    byteArrayOutputStream2.write(bArr, 0, read2);
                } else {
                    byte[] bytes = byteArrayOutputStream2.toByteArray();
                    byteArrayOutputStream2.close();
                    Intrinsics.checkExpressionValueIsNotNull(bytes, "bytes");
                    return bytes;
                }
            }
        }
    }
}
