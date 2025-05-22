package com.aliyun.alink.linksdk.channel.core.persistent.mqtt.p042a;

import com.aliyun.alink.linksdk.channel.core.p041b.C0969a;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertPath;
import java.security.cert.CertPathValidator;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.PKIXParameters;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* compiled from: MqttTrustManager.java */
/* renamed from: com.aliyun.alink.linksdk.channel.core.persistent.mqtt.a.c */
/* loaded from: classes.dex */
public class C0976c implements X509TrustManager {

    /* renamed from: a */
    private final X509TrustManager f914a;

    /* renamed from: b */
    private final KeyStore f915b;

    @Override // javax.net.ssl.X509TrustManager
    public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
    }

    @Override // javax.net.ssl.X509TrustManager
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }

    public C0976c(InputStream inputStream) {
        this.f915b = m413a(inputStream);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance("X509");
        trustManagerFactory.init((KeyStore) null);
        this.f914a = (X509TrustManager) trustManagerFactory.getTrustManagers()[0];
    }

    /* renamed from: a */
    private KeyStore m413a(InputStream inputStream) {
        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        keyStore.load(null);
        X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(inputStream);
        keyStore.setCertificateEntry(x509Certificate.getSubjectX500Principal().getName(), x509Certificate);
        return keyStore;
    }

    @Override // javax.net.ssl.X509TrustManager
    public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        try {
            this.f914a.checkServerTrusted(x509CertificateArr, str);
        } catch (CertificateException e) {
            try {
                X509Certificate[] m416a = m416a(x509CertificateArr);
                CertPathValidator certPathValidator = CertPathValidator.getInstance("PKIX");
                CertPath generateCertPath = CertificateFactory.getInstance("X509").generateCertPath(Arrays.asList(m416a));
                PKIXParameters pKIXParameters = new PKIXParameters(this.f915b);
                pKIXParameters.setRevocationEnabled(false);
                certPathValidator.validate(generateCertPath, pKIXParameters);
            } catch (CertificateNotYetValidException e2) {
                C0969a.m393a("MqttTrustManager", "CertificateNotYetValidException " + e2);
            } catch (Exception e3) {
                if (e3.getCause() instanceof CertificateNotYetValidException) {
                    C0969a.m393a("MqttTrustManager", "validate cert failed.because system is early than cert valid . wsf will ignore this exception," + e3);
                    return;
                }
                C0969a.m393a("MqttTrustManager", "checkServerTrusted faied." + e);
                C0969a.m393a("MqttTrustManager", "validate cert failed." + e3);
                throw e;
            }
        }
    }

    /* renamed from: a */
    private X509Certificate[] m416a(X509Certificate[] x509CertificateArr) {
        X509Certificate[] x509CertificateArr2 = new X509Certificate[x509CertificateArr.length];
        List<X509Certificate> asList = Arrays.asList(x509CertificateArr);
        int length = x509CertificateArr.length - 1;
        X509Certificate m415a = m415a(asList);
        x509CertificateArr2[length] = m415a;
        while (true) {
            m415a = m414a(m415a, asList);
            if (m415a == null || length <= 0) {
                break;
            }
            length--;
            x509CertificateArr2[length] = m415a;
        }
        return x509CertificateArr2;
    }

    /* renamed from: a */
    private X509Certificate m415a(List<X509Certificate> list) {
        Iterator<X509Certificate> it = list.iterator();
        while (it.hasNext()) {
            X509Certificate next = it.next();
            X509Certificate m417b = m417b(next, list);
            if (m417b == null || m417b.equals(next)) {
                return next;
            }
        }
        return null;
    }

    /* renamed from: a */
    private X509Certificate m414a(X509Certificate x509Certificate, List<X509Certificate> list) {
        for (X509Certificate x509Certificate2 : list) {
            if (x509Certificate2.getIssuerDN().equals(x509Certificate.getSubjectDN()) && !x509Certificate2.equals(x509Certificate)) {
                return x509Certificate2;
            }
        }
        return null;
    }

    /* renamed from: b */
    private X509Certificate m417b(X509Certificate x509Certificate, List<X509Certificate> list) {
        for (X509Certificate x509Certificate2 : list) {
            if (x509Certificate2.getSubjectDN().equals(x509Certificate.getIssuerDN())) {
                return x509Certificate2;
            }
        }
        return null;
    }
}
