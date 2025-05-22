package io.grpc.netty.shaded.io.grpc.netty;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.TrustManagerFactorySpi;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class FixedTrustManagerFactory extends TrustManagerFactory {
    public FixedTrustManagerFactory(List<TrustManager> list) {
        super(new FixedTrustManagerFactorySpi(list), new UnhelpfulSecurityProvider(), "FakeAlgorithm");
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class FixedTrustManagerFactorySpi extends TrustManagerFactorySpi {
        private final List<TrustManager> trustManagers;

        @Override // javax.net.ssl.TrustManagerFactorySpi
        protected void engineInit(KeyStore keyStore) {
        }

        @Override // javax.net.ssl.TrustManagerFactorySpi
        protected void engineInit(ManagerFactoryParameters managerFactoryParameters) {
        }

        public FixedTrustManagerFactorySpi(List<TrustManager> list) {
            this.trustManagers = Collections.unmodifiableList(new ArrayList(list));
        }

        @Override // javax.net.ssl.TrustManagerFactorySpi
        protected TrustManager[] engineGetTrustManagers() {
            return (TrustManager[]) this.trustManagers.toArray(new TrustManager[0]);
        }
    }
}
