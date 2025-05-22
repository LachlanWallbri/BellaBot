package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleTrustManagerFactory;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.security.KeyStore;
import javax.net.ssl.ManagerFactoryParameters;
import javax.net.ssl.TrustManager;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class TrustManagerFactoryWrapper extends SimpleTrustManagerFactory {

    /* renamed from: tm */
    private final TrustManager f8423tm;

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleTrustManagerFactory
    protected void engineInit(KeyStore keyStore) throws Exception {
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleTrustManagerFactory
    protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public TrustManagerFactoryWrapper(TrustManager trustManager) {
        this.f8423tm = (TrustManager) ObjectUtil.checkNotNull(trustManager, "tm");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleTrustManagerFactory
    protected TrustManager[] engineGetTrustManagers() {
        return new TrustManager[]{this.f8423tm};
    }
}
