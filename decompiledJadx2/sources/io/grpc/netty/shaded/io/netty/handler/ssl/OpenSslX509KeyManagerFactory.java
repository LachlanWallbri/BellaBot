package io.grpc.netty.shaded.io.netty.handler.ssl;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferTable;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.buffer.UnpooledByteBufAllocator;
import io.grpc.netty.shaded.io.netty.internal.tcnative.SSL;
import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.KeyStoreSpi;
import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.UnrecoverableKeyException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.X509KeyManager;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class OpenSslX509KeyManagerFactory extends KeyManagerFactory {
    private final OpenSslKeyManagerFactorySpi spi;

    public OpenSslX509KeyManagerFactory() {
        this(newOpenSslKeyManagerFactorySpi(null));
    }

    public OpenSslX509KeyManagerFactory(Provider provider) {
        this(newOpenSslKeyManagerFactorySpi(provider));
    }

    public OpenSslX509KeyManagerFactory(String str, Provider provider) throws NoSuchAlgorithmException {
        this(newOpenSslKeyManagerFactorySpi(str, provider));
    }

    private OpenSslX509KeyManagerFactory(OpenSslKeyManagerFactorySpi openSslKeyManagerFactorySpi) {
        super(openSslKeyManagerFactorySpi, openSslKeyManagerFactorySpi.kmf.getProvider(), openSslKeyManagerFactorySpi.kmf.getAlgorithm());
        this.spi = openSslKeyManagerFactorySpi;
    }

    private static OpenSslKeyManagerFactorySpi newOpenSslKeyManagerFactorySpi(Provider provider) {
        try {
            return newOpenSslKeyManagerFactorySpi(null, provider);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    private static OpenSslKeyManagerFactorySpi newOpenSslKeyManagerFactorySpi(String str, Provider provider) throws NoSuchAlgorithmException {
        KeyManagerFactory keyManagerFactory;
        if (str == null) {
            str = KeyManagerFactory.getDefaultAlgorithm();
        }
        if (provider == null) {
            keyManagerFactory = KeyManagerFactory.getInstance(str);
        } else {
            keyManagerFactory = KeyManagerFactory.getInstance(str, provider);
        }
        return new OpenSslKeyManagerFactorySpi(keyManagerFactory);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenSslKeyMaterialProvider newProvider() {
        return this.spi.newProvider();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class OpenSslKeyManagerFactorySpi extends KeyManagerFactorySpi {
        final KeyManagerFactory kmf;
        private volatile ProviderFactory providerFactory;

        OpenSslKeyManagerFactorySpi(KeyManagerFactory keyManagerFactory) {
            this.kmf = (KeyManagerFactory) ObjectUtil.checkNotNull(keyManagerFactory, "kmf");
        }

        @Override // javax.net.ssl.KeyManagerFactorySpi
        protected synchronized void engineInit(KeyStore keyStore, char[] cArr) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
            if (this.providerFactory != null) {
                throw new KeyStoreException("Already initialized");
            }
            if (!keyStore.aliases().hasMoreElements()) {
                throw new KeyStoreException("No aliases found");
            }
            this.kmf.init(keyStore, cArr);
            this.providerFactory = new ProviderFactory(ReferenceCountedOpenSslContext.chooseX509KeyManager(this.kmf.getKeyManagers()), password(cArr), Collections.list(keyStore.aliases()));
        }

        private static String password(char[] cArr) {
            if (cArr == null || cArr.length == 0) {
                return null;
            }
            return new String(cArr);
        }

        @Override // javax.net.ssl.KeyManagerFactorySpi
        protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws InvalidAlgorithmParameterException {
            throw new InvalidAlgorithmParameterException("Not supported");
        }

        @Override // javax.net.ssl.KeyManagerFactorySpi
        protected KeyManager[] engineGetKeyManagers() {
            ProviderFactory providerFactory = this.providerFactory;
            if (providerFactory != null) {
                return new KeyManager[]{providerFactory.keyManager};
            }
            throw new IllegalStateException("engineInit(...) not called yet");
        }

        OpenSslKeyMaterialProvider newProvider() {
            ProviderFactory providerFactory = this.providerFactory;
            if (providerFactory == null) {
                throw new IllegalStateException("engineInit(...) not called yet");
            }
            return providerFactory.newProvider();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes7.dex */
        public static final class ProviderFactory {
            private final Iterable<String> aliases;
            private final X509KeyManager keyManager;
            private final String password;

            ProviderFactory(X509KeyManager x509KeyManager, String str, Iterable<String> iterable) {
                this.keyManager = x509KeyManager;
                this.password = str;
                this.aliases = iterable;
            }

            OpenSslKeyMaterialProvider newProvider() {
                return new OpenSslPopulatedKeyMaterialProvider(this.keyManager, this.password, this.aliases);
            }

            /* JADX INFO: Access modifiers changed from: private */
            /* JADX WARN: Classes with same name are omitted:
              classes6.dex
             */
            /* loaded from: classes7.dex */
            public static final class OpenSslPopulatedKeyMaterialProvider extends OpenSslKeyMaterialProvider {
                private final Map<String, Object> materialMap;

                OpenSslPopulatedKeyMaterialProvider(X509KeyManager x509KeyManager, String str, Iterable<String> iterable) {
                    super(x509KeyManager, str);
                    this.materialMap = new HashMap();
                    try {
                        for (String str2 : iterable) {
                            if (str2 != null && !this.materialMap.containsKey(str2)) {
                                try {
                                    this.materialMap.put(str2, super.chooseKeyMaterial(UnpooledByteBufAllocator.DEFAULT, str2));
                                } catch (Exception e) {
                                    this.materialMap.put(str2, e);
                                }
                            }
                        }
                        if (this.materialMap.isEmpty()) {
                            throw new IllegalArgumentException("aliases must be non-empty");
                        }
                    } catch (Throwable th) {
                        destroy();
                        throw th;
                    }
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslKeyMaterialProvider
                public OpenSslKeyMaterial chooseKeyMaterial(ByteBufAllocator byteBufAllocator, String str) throws Exception {
                    Object obj = this.materialMap.get(str);
                    if (obj == null) {
                        return null;
                    }
                    if (obj instanceof OpenSslKeyMaterial) {
                        return ((OpenSslKeyMaterial) obj).retain();
                    }
                    throw ((Exception) obj);
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslKeyMaterialProvider
                public void destroy() {
                    Iterator<Object> it = this.materialMap.values().iterator();
                    while (it.hasNext()) {
                        ReferenceCountUtil.release(it.next());
                    }
                    this.materialMap.clear();
                }
            }
        }
    }

    public static OpenSslX509KeyManagerFactory newEngineBased(File file, String str) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        return newEngineBased(SslContext.toX509Certificates(file), str);
    }

    public static OpenSslX509KeyManagerFactory newEngineBased(X509Certificate[] x509CertificateArr, String str) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        OpenSslKeyStore openSslKeyStore = new OpenSslKeyStore((X509Certificate[]) x509CertificateArr.clone(), false);
        openSslKeyStore.load(null, null);
        OpenSslX509KeyManagerFactory openSslX509KeyManagerFactory = new OpenSslX509KeyManagerFactory();
        openSslX509KeyManagerFactory.init(openSslKeyStore, str != null ? str.toCharArray() : null);
        return openSslX509KeyManagerFactory;
    }

    public static OpenSslX509KeyManagerFactory newKeyless(File file) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        return newKeyless(SslContext.toX509Certificates(file));
    }

    public static OpenSslX509KeyManagerFactory newKeyless(InputStream inputStream) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        return newKeyless(SslContext.toX509Certificates(inputStream));
    }

    public static OpenSslX509KeyManagerFactory newKeyless(X509Certificate... x509CertificateArr) throws CertificateException, IOException, KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
        OpenSslKeyStore openSslKeyStore = new OpenSslKeyStore((X509Certificate[]) x509CertificateArr.clone(), true);
        openSslKeyStore.load(null, null);
        OpenSslX509KeyManagerFactory openSslX509KeyManagerFactory = new OpenSslX509KeyManagerFactory();
        openSslX509KeyManagerFactory.init(openSslKeyStore, null);
        return openSslX509KeyManagerFactory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static final class OpenSslKeyStore extends KeyStore {
        private OpenSslKeyStore(final X509Certificate[] x509CertificateArr, final boolean z) {
            super(new KeyStoreSpi() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslX509KeyManagerFactory.OpenSslKeyStore.1
                private final Date creationDate = new Date();

                @Override // java.security.KeyStoreSpi
                public int engineSize() {
                    return 1;
                }

                @Override // java.security.KeyStoreSpi
                public Key engineGetKey(String str, char[] cArr) throws UnrecoverableKeyException {
                    long loadPrivateKeyFromEngine;
                    String str2 = null;
                    if (!engineContainsAlias(str)) {
                        return null;
                    }
                    if (z) {
                        loadPrivateKeyFromEngine = 0;
                    } else {
                        if (cArr != null) {
                            try {
                                str2 = new String(cArr);
                            } catch (Exception e) {
                                UnrecoverableKeyException unrecoverableKeyException = new UnrecoverableKeyException("Unable to load key from engine");
                                unrecoverableKeyException.initCause(e);
                                throw unrecoverableKeyException;
                            }
                        }
                        loadPrivateKeyFromEngine = SSL.loadPrivateKeyFromEngine(str, str2);
                    }
                    return new OpenSslPrivateKey(loadPrivateKeyFromEngine);
                }

                @Override // java.security.KeyStoreSpi
                public Certificate[] engineGetCertificateChain(String str) {
                    if (engineContainsAlias(str)) {
                        return (X509Certificate[]) x509CertificateArr.clone();
                    }
                    return null;
                }

                @Override // java.security.KeyStoreSpi
                public Certificate engineGetCertificate(String str) {
                    if (engineContainsAlias(str)) {
                        return x509CertificateArr[0];
                    }
                    return null;
                }

                @Override // java.security.KeyStoreSpi
                public Date engineGetCreationDate(String str) {
                    if (engineContainsAlias(str)) {
                        return this.creationDate;
                    }
                    return null;
                }

                @Override // java.security.KeyStoreSpi
                public void engineSetKeyEntry(String str, Key key, char[] cArr, Certificate[] certificateArr) throws KeyStoreException {
                    throw new KeyStoreException("Not supported");
                }

                @Override // java.security.KeyStoreSpi
                public void engineSetKeyEntry(String str, byte[] bArr, Certificate[] certificateArr) throws KeyStoreException {
                    throw new KeyStoreException("Not supported");
                }

                @Override // java.security.KeyStoreSpi
                public void engineSetCertificateEntry(String str, Certificate certificate) throws KeyStoreException {
                    throw new KeyStoreException("Not supported");
                }

                @Override // java.security.KeyStoreSpi
                public void engineDeleteEntry(String str) throws KeyStoreException {
                    throw new KeyStoreException("Not supported");
                }

                @Override // java.security.KeyStoreSpi
                public Enumeration<String> engineAliases() {
                    return Collections.enumeration(Collections.singleton(TransferTable.COLUMN_KEY));
                }

                @Override // java.security.KeyStoreSpi
                public boolean engineContainsAlias(String str) {
                    return TransferTable.COLUMN_KEY.equals(str);
                }

                @Override // java.security.KeyStoreSpi
                public boolean engineIsKeyEntry(String str) {
                    return engineContainsAlias(str);
                }

                @Override // java.security.KeyStoreSpi
                public boolean engineIsCertificateEntry(String str) {
                    return engineContainsAlias(str);
                }

                @Override // java.security.KeyStoreSpi
                public String engineGetCertificateAlias(Certificate certificate) {
                    if (!(certificate instanceof X509Certificate)) {
                        return null;
                    }
                    for (X509Certificate x509Certificate : x509CertificateArr) {
                        if (x509Certificate.equals(certificate)) {
                            return TransferTable.COLUMN_KEY;
                        }
                    }
                    return null;
                }

                @Override // java.security.KeyStoreSpi
                public void engineStore(OutputStream outputStream, char[] cArr) {
                    throw new UnsupportedOperationException();
                }

                @Override // java.security.KeyStoreSpi
                public void engineLoad(InputStream inputStream, char[] cArr) {
                    if (inputStream != null && cArr != null) {
                        throw new UnsupportedOperationException();
                    }
                }
            }, null, "native");
            OpenSsl.ensureAvailability();
        }
    }
}
