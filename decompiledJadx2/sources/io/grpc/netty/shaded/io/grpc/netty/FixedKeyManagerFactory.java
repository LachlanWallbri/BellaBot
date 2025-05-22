package io.grpc.netty.shaded.io.grpc.netty;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.KeyManagerFactorySpi;
import javax.net.ssl.ManagerFactoryParameters;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class FixedKeyManagerFactory extends KeyManagerFactory {
    public FixedKeyManagerFactory(List<KeyManager> list) {
        super(new FixedKeyManagerFactorySpi(list), new UnhelpfulSecurityProvider(), "FakeAlgorithm");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class FixedKeyManagerFactorySpi extends KeyManagerFactorySpi {
        private final List<KeyManager> keyManagers;

        @Override // javax.net.ssl.KeyManagerFactorySpi
        protected void engineInit(KeyStore keyStore, char[] cArr) {
        }

        @Override // javax.net.ssl.KeyManagerFactorySpi
        protected void engineInit(ManagerFactoryParameters managerFactoryParameters) {
        }

        public FixedKeyManagerFactorySpi(List<KeyManager> list) {
            this.keyManagers = Collections.unmodifiableList(new ArrayList(list));
        }

        @Override // javax.net.ssl.KeyManagerFactorySpi
        protected KeyManager[] engineGetKeyManagers() {
            return (KeyManager[]) this.keyManagers.toArray(new KeyManager[0]);
        }
    }
}
