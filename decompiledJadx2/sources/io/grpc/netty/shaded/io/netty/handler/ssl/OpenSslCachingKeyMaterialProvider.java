package io.grpc.netty.shaded.io.netty.handler.ssl;

import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.net.ssl.X509KeyManager;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class OpenSslCachingKeyMaterialProvider extends OpenSslKeyMaterialProvider {
    private final ConcurrentMap<String, OpenSslKeyMaterial> cache;
    private volatile boolean full;
    private final int maxCachedEntries;

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenSslCachingKeyMaterialProvider(X509KeyManager x509KeyManager, String str, int i) {
        super(x509KeyManager, str);
        this.cache = new ConcurrentHashMap();
        this.maxCachedEntries = i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslKeyMaterialProvider
    public OpenSslKeyMaterial chooseKeyMaterial(ByteBufAllocator byteBufAllocator, String str) throws Exception {
        OpenSslKeyMaterial openSslKeyMaterial = this.cache.get(str);
        if (openSslKeyMaterial == null) {
            openSslKeyMaterial = super.chooseKeyMaterial(byteBufAllocator, str);
            if (openSslKeyMaterial == null) {
                return null;
            }
            if (this.full) {
                return openSslKeyMaterial;
            }
            if (this.cache.size() > this.maxCachedEntries) {
                this.full = true;
                return openSslKeyMaterial;
            }
            OpenSslKeyMaterial putIfAbsent = this.cache.putIfAbsent(str, openSslKeyMaterial);
            if (putIfAbsent != null) {
                openSslKeyMaterial.release();
                openSslKeyMaterial = putIfAbsent;
            }
        }
        return openSslKeyMaterial.retain();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // io.grpc.netty.shaded.io.netty.handler.ssl.OpenSslKeyMaterialProvider
    public void destroy() {
        do {
            Iterator<OpenSslKeyMaterial> it = this.cache.values().iterator();
            while (it.hasNext()) {
                it.next().release();
                it.remove();
            }
        } while (!this.cache.isEmpty());
    }
}
