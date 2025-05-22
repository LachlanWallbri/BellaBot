package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleKeyManagerFactory;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.security.KeyStore;
import javax.net.ssl.KeyManager;
import javax.net.ssl.ManagerFactoryParameters;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class KeyManagerFactoryWrapper extends SimpleKeyManagerFactory {

    /* renamed from: km */
    private final KeyManager f8414km;

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleKeyManagerFactory
    protected void engineInit(KeyStore keyStore, char[] cArr) throws Exception {
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleKeyManagerFactory
    protected void engineInit(ManagerFactoryParameters managerFactoryParameters) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public KeyManagerFactoryWrapper(KeyManager keyManager) {
        this.f8414km = (KeyManager) ObjectUtil.checkNotNull(keyManager, "km");
    }

    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.util.SimpleKeyManagerFactory
    protected KeyManager[] engineGetKeyManagers() {
        return new KeyManager[]{this.f8414km};
    }
}
