package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.X509KeyManager;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class OpenSslCachingX509KeyManagerFactory extends KeyManagerFactory {
    private final int maxCachedEntries;

    public OpenSslCachingX509KeyManagerFactory(KeyManagerFactory keyManagerFactory) {
        this(keyManagerFactory, 1024);
    }

    public OpenSslCachingX509KeyManagerFactory(final KeyManagerFactory keyManagerFactory, int i) {
        super(new KeyManagerFactorySpi() { // from class: io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslCachingX509KeyManagerFactory.1
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
        this.maxCachedEntries = ObjectUtil.checkPositive(i, "maxCachedEntries");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenSslKeyMaterialProvider newProvider(String str) {
        X509KeyManager chooseX509KeyManager = ReferenceCountedOpenSslContext.chooseX509KeyManager(getKeyManagers());
        if ("sun.security.ssl.X509KeyManagerImpl".equals(chooseX509KeyManager.getClass().getName())) {
            return new OpenSslKeyMaterialProvider(chooseX509KeyManager, str);
        }
        return new OpenSslCachingKeyMaterialProvider(ReferenceCountedOpenSslContext.chooseX509KeyManager(getKeyManagers()), str, this.maxCachedEntries);
    }
}
