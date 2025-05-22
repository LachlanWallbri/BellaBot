package com.pudutech.pdmqtt.client;

import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import javax.security.cert.CertificateException;

/* loaded from: classes6.dex */
public class SocketFactory extends SSLSocketFactory {
    private SSLSocketFactory factory;
    private TrustManagerFactory tmf;

    /* loaded from: classes6.dex */
    public static class SocketFactoryOptions {
        private InputStream caClientP12InputStream;
        private String caClientP12Password;
        private InputStream caCrtInputStream;

        public SocketFactoryOptions withCaInputStream(InputStream inputStream) {
            this.caCrtInputStream = inputStream;
            return this;
        }

        public SocketFactoryOptions withClientP12InputStream(InputStream inputStream) {
            this.caClientP12InputStream = inputStream;
            return this;
        }

        public SocketFactoryOptions withClientP12Password(String str) {
            this.caClientP12Password = str;
            return this;
        }

        public boolean hasCaCrt() {
            return this.caCrtInputStream != null;
        }

        public boolean hasClientP12Crt() {
            return this.caClientP12Password != null;
        }

        public InputStream getCaCrtInputStream() {
            return this.caCrtInputStream;
        }

        public InputStream getCaClientP12InputStream() {
            return this.caClientP12InputStream;
        }

        public String getCaClientP12Password() {
            return this.caClientP12Password;
        }

        public boolean hasClientP12Password() {
            String str = this.caClientP12Password;
            return (str == null || str.equals("")) ? false : true;
        }
    }

    public SocketFactory() throws CertificateException, KeyStoreException, NoSuchAlgorithmException, IOException, KeyManagementException, java.security.cert.CertificateException, UnrecoverableKeyException {
        this(new SocketFactoryOptions());
    }

    public SocketFactory(SocketFactoryOptions socketFactoryOptions) throws KeyStoreException, NoSuchAlgorithmException, IOException, KeyManagementException, java.security.cert.CertificateException, UnrecoverableKeyException {
        Log.v(toString(), "initializing CustomSocketFactory");
        this.tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("X509");
        if (socketFactoryOptions.hasCaCrt()) {
            Log.v(toString(), "MQTT_CONNECTION_OPTIONS.hasCaCrt(): true");
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(null, null);
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(socketFactoryOptions.getCaCrtInputStream());
            keyStore.setCertificateEntry(x509Certificate.getSubjectX500Principal().getName(), x509Certificate);
            this.tmf.init(keyStore);
            Log.v("MQTT", "Certificate Owner: " + x509Certificate.getSubjectDN().toString());
            Log.v("MQTT", "Certificate Issuer: " + x509Certificate.getIssuerDN().toString());
            Log.v("MQTT", "Certificate Serial Number:  " + x509Certificate.getSerialNumber().toString());
            Log.v("MQTT", "Certificate Algorithm:  " + x509Certificate.getSigAlgName());
            Log.v("MQTT", "Certificate Version:  " + x509Certificate.getVersion());
            Log.v("MQTT", "Certificate OID:  " + x509Certificate.getSigAlgOID());
            Enumeration<String> aliases = keyStore.aliases();
            while (aliases.hasMoreElements()) {
                String nextElement = aliases.nextElement();
                Log.v("MQTT", "Alias: " + nextElement + " isKeyEntry:" + keyStore.isKeyEntry(nextElement) + "  isCertificateEntry:" + keyStore.isCertificateEntry(nextElement));
            }
        } else {
            Log.v("MQTT", "CA sideload: false, using system keystore");
            KeyStore keyStore2 = KeyStore.getInstance("AndroidCAStore");
            keyStore2.load(null);
            this.tmf.init(keyStore2);
        }
        if (socketFactoryOptions.hasClientP12Crt()) {
            Log.v(toString(), "MQTT_CONNECTION_OPTIONS.hasClientP12Crt(): true");
            KeyStore keyStore3 = KeyStore.getInstance("PKCS12");
            keyStore3.load(socketFactoryOptions.getCaClientP12InputStream(), socketFactoryOptions.hasClientP12Password() ? socketFactoryOptions.getCaClientP12Password().toCharArray() : new char[0]);
            keyManagerFactory.init(keyStore3, socketFactoryOptions.hasClientP12Password() ? socketFactoryOptions.getCaClientP12Password().toCharArray() : new char[0]);
            Log.v(toString(), "Client .p12 Keystore content: ");
            Enumeration<String> aliases2 = keyStore3.aliases();
            while (aliases2.hasMoreElements()) {
                Log.v("Alias: %s", aliases2.nextElement());
            }
        } else {
            Log.v(toString(), "Client .p12 sideload: false, using null CLIENT cert");
            keyManagerFactory.init(null, null);
        }
        SSLContext sSLContext = SSLContext.getInstance("TLSv1.2");
        sSLContext.init(keyManagerFactory.getKeyManagers(), new TrustManager[]{new X509TrustManager() { // from class: com.pudutech.pdmqtt.client.SocketFactory.1
            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        }}, null);
        this.factory = sSLContext.getSocketFactory();
    }

    public TrustManager[] getTrustManagers() {
        return this.tmf.getTrustManagers();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getDefaultCipherSuites() {
        return this.factory.getDefaultCipherSuites();
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public String[] getSupportedCipherSuites() {
        return this.factory.getSupportedCipherSuites();
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket() throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.factory.createSocket();
        sSLSocket.setEnabledProtocols(new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"});
        return sSLSocket;
    }

    @Override // javax.net.ssl.SSLSocketFactory
    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.factory.createSocket(socket, str, i, z);
        sSLSocket.setEnabledProtocols(new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"});
        return sSLSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.factory.createSocket(str, i);
        sSLSocket.setEnabledProtocols(new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"});
        return sSLSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.factory.createSocket(str, i, inetAddress, i2);
        sSLSocket.setEnabledProtocols(new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"});
        return sSLSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.factory.createSocket(inetAddress, i);
        sSLSocket.setEnabledProtocols(new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"});
        return sSLSocket;
    }

    @Override // javax.net.SocketFactory
    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        SSLSocket sSLSocket = (SSLSocket) this.factory.createSocket(inetAddress, i, inetAddress2, i2);
        sSLSocket.setEnabledProtocols(new String[]{"TLSv1", "TLSv1.1", "TLSv1.2"});
        return sSLSocket;
    }
}
