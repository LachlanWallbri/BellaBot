package com.pudutech.pdmqtt.client;

import com.amazonaws.mobile.auth.userpools.CognitoUserPoolsSignInProvider;
import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import com.pudutech.pdmqtt.client.SocketFactory;
import com.pudutech.pdmqtt.service.MqttManagerImplKt;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.Security;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import org.json.JSONObject;

/* compiled from: MqttSocketFactory.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0004H\u0002J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u0004H\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u00020\u0004H\u0002J\u000e\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0004J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0014\u001a\u00020\u0001H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/pdmqtt/client/MqttSocketFactory;", "", "()V", "TAG", "", "buildCertificate", "Ljava/security/cert/X509Certificate;", "certificateString", "buildKey", "Ljava/security/Key;", "keyString", "createCaKeySocketFactory", "Ljavax/net/SocketFactory;", "certificate", TransferTable.COLUMN_KEY, "createCaSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "createSocketFactory", "jsonStr", "getPrivateKey", "possiblePrivateKey", "pdmqtt_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class MqttSocketFactory {
    public static final MqttSocketFactory INSTANCE = new MqttSocketFactory();
    private static final String TAG = TAG;
    private static final String TAG = TAG;

    private MqttSocketFactory() {
    }

    public final javax.net.SocketFactory createSocketFactory(String jsonStr) {
        Intrinsics.checkParameterIsNotNull(jsonStr, "jsonStr");
        boolean z = true;
        if (jsonStr.length() == 0) {
            throw new Exception("获取不到认证数据");
        }
        JSONObject jSONObject = new JSONObject(jsonStr);
        String optString = jSONObject.optString("certificate");
        String optString2 = jSONObject.optString(TransferTable.COLUMN_KEY);
        String str = optString;
        if (str == null || str.length() == 0) {
            throw new Exception("获取不到certificate");
        }
        MqttManagerImplKt.remoteLog(TAG, "key =  " + optString2);
        MqttManagerImplKt.remoteLog(TAG, "certificate =  " + optString);
        String str2 = optString2;
        if (str2 != null && str2.length() != 0) {
            z = false;
        }
        if (z) {
            return createCaSocketFactory(optString);
        }
        return createCaKeySocketFactory(optString, optString2);
    }

    private final SSLSocketFactory createCaSocketFactory(String certificate) {
        SocketFactory.SocketFactoryOptions socketFactoryOptions = new SocketFactory.SocketFactoryOptions();
        Charset charset = Charsets.UTF_8;
        if (certificate == null) {
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        byte[] bytes = certificate.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        socketFactoryOptions.withCaInputStream(new ByteArrayInputStream(bytes));
        return new SocketFactory(socketFactoryOptions);
    }

    private final javax.net.SocketFactory createCaKeySocketFactory(String certificate, String key) {
        X509Certificate buildCertificate = buildCertificate(certificate);
        Key buildKey = buildKey(key);
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null);
        keyStore.setCertificateEntry("cert-alias", buildCertificate);
        char[] charArray = CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(charArray, "(this as java.lang.String).toCharArray()");
        keyStore.setKeyEntry("key-alias", buildKey, charArray, new X509Certificate[]{buildCertificate});
        SSLContext sslContext2 = SSLContext.getInstance("TLS");
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        char[] charArray2 = CognitoUserPoolsSignInProvider.AttributeKeys.PASSWORD.toCharArray();
        Intrinsics.checkExpressionValueIsNotNull(charArray2, "(this as java.lang.String).toCharArray()");
        keyManagerFactory.init(keyStore, charArray2);
        KeyManager[] keyManagers = keyManagerFactory.getKeyManagers();
        Intrinsics.checkExpressionValueIsNotNull(keyManagers, "kmf.getKeyManagers()");
        sslContext2.init(keyManagers, null, new SecureRandom());
        Intrinsics.checkExpressionValueIsNotNull(sslContext2, "sslContext2");
        SSLSocketFactory socketFactory = sslContext2.getSocketFactory();
        Intrinsics.checkExpressionValueIsNotNull(socketFactory, "sslContext2.socketFactory");
        return socketFactory;
    }

    private final X509Certificate buildCertificate(String certificateString) throws CertificateException {
        try {
            Security.addProvider(new BouncyCastleProvider());
            PemObject readPemObject = new PemReader(new StringReader(certificateString)).readPemObject();
            Intrinsics.checkExpressionValueIsNotNull(readPemObject, "publicKeyCertificateReader.readPemObject()");
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            Intrinsics.checkExpressionValueIsNotNull(certificateFactory, "CertificateFactory.getInstance(\"X.509\")");
            Certificate generateCertificate = certificateFactory.generateCertificate(new ByteArrayInputStream(readPemObject.getContent()));
            if (generateCertificate != null) {
                return (X509Certificate) generateCertificate;
            }
            throw new TypeCastException("null cannot be cast to non-null type java.security.cert.X509Certificate");
        } catch (Exception e) {
            throw new CertificateException(e);
        }
    }

    private final Key buildKey(String keyString) {
        try {
            Security.addProvider(new BouncyCastleProvider());
            Object possiblePrivateKey = new PEMParser(new StringReader(keyString)).readObject();
            Intrinsics.checkExpressionValueIsNotNull(possiblePrivateKey, "possiblePrivateKey");
            return getPrivateKey(possiblePrivateKey);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private final Key getPrivateKey(Object possiblePrivateKey) throws IOException {
        if (possiblePrivateKey instanceof PEMKeyPair) {
            KeyPair keyPair = new JcaPEMKeyConverter().getKeyPair((PEMKeyPair) possiblePrivateKey);
            Intrinsics.checkExpressionValueIsNotNull(keyPair, "JcaPEMKeyConverter().get…yPair(possiblePrivateKey)");
            return keyPair.getPrivate();
        }
        if (possiblePrivateKey instanceof PrivateKeyInfo) {
            return new JcaPEMKeyConverter().getPrivateKey((PrivateKeyInfo) possiblePrivateKey);
        }
        throw new IOException("Unable to parse private key, type unknown");
    }
}
