package io.netty.handler.ssl;

import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
public final class OpenSslCachingX509KeyManagerFactory extends KeyManagerFactory {
    public OpenSslCachingX509KeyManagerFactory(final KeyManagerFactory keyManagerFactory) {
        super(new KeyManagerFactorySpi() { // from class: io.netty.handler.ssl.OpenSslCachingX509KeyManagerFactory.1
            @Override // javax.net.ssl.KeyManagerFactorySpi
            protected void engineInit(KeyStore keyStore, char[] cArr) throws KeyStoreException, NoSuchAlgorithmException, UnrecoverableKeyException {
                keyManagerFactory.init(keyStore, cArr);
            }

            @Override // javax.net.ssl.KeyManagerFactorySpi
            protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws InvalidAlgorithmParameterException {
                keyManagerFactory.init(managerFactoryParameters);
            }

            @Override // javax.net.ssl.KeyManagerFactorySpi
            protected KeyManager[] engineGetKeyManagers() {
                return keyManagerFactory.getKeyManagers();
            }
        }, keyManagerFactory.getProvider(), keyManagerFactory.getAlgorithm());
    }
}
