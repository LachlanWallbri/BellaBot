package io.netty.handler.ssl;

import io.netty.buffer.ByteBufAllocator;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.net.ssl.X509KeyManager;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
final class OpenSslCachingKeyMaterialProvider extends OpenSslKeyMaterialProvider {
    private final ConcurrentMap<String, OpenSslKeyMaterial> cache;

    OpenSslCachingKeyMaterialProvider(X509KeyManager x509KeyManager, String str) {
        super(x509KeyManager, str);
        this.cache = new ConcurrentHashMap();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.netty.handler.ssl.OpenSslKeyMaterialProvider
    public OpenSslKeyMaterial chooseKeyMaterial(ByteBufAllocator byteBufAllocator, String str) throws Exception {
        OpenSslKeyMaterial openSslKeyMaterial = this.cache.get(str);
        if (openSslKeyMaterial == null) {
            openSslKeyMaterial = super.chooseKeyMaterial(byteBufAllocator, str);
            if (openSslKeyMaterial == null) {
                return null;
            }
            OpenSslKeyMaterial putIfAbsent = this.cache.putIfAbsent(str, openSslKeyMaterial);
            if (putIfAbsent != null) {
                openSslKeyMaterial.release();
                openSslKeyMaterial = putIfAbsent;
            }
        }
        return openSslKeyMaterial.retain();
    }

    @Override // io.netty.handler.ssl.OpenSslKeyMaterialProvider
    void destroy() {
        do {
            Iterator<OpenSslKeyMaterial> it = this.cache.values().iterator();
            while (it.hasNext()) {
                it.next().release();
                it.remove();
            }
        } while (!this.cache.isEmpty());
    }
}
