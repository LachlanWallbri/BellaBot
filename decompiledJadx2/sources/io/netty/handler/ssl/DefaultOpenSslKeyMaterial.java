package io.netty.handler.ssl;

import io.netty.internal.tcnative.SSL;
import io.netty.util.AbstractReferenceCounted;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.ResourceLeakDetectorFactory;
import io.netty.util.ResourceLeakTracker;
import java.security.cert.X509Certificate;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes8.dex */
final class DefaultOpenSslKeyMaterial extends AbstractReferenceCounted implements OpenSslKeyMaterial {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final ResourceLeakDetector<DefaultOpenSslKeyMaterial> leakDetector = ResourceLeakDetectorFactory.instance().newResourceLeakDetector(DefaultOpenSslKeyMaterial.class);
    private long chain;
    private final ResourceLeakTracker<DefaultOpenSslKeyMaterial> leak = leakDetector.track(this);
    private long privateKey;
    private final X509Certificate[] x509CertificateChain;

    /* JADX INFO: Access modifiers changed from: package-private */
    public DefaultOpenSslKeyMaterial(long j, long j2, X509Certificate[] x509CertificateArr) {
        this.chain = j;
        this.privateKey = j2;
        this.x509CertificateChain = x509CertificateArr;
    }

    @Override // io.netty.handler.ssl.OpenSslKeyMaterial
    public X509Certificate[] certificateChain() {
        return (X509Certificate[]) this.x509CertificateChain.clone();
    }

    @Override // io.netty.handler.ssl.OpenSslKeyMaterial
    public long certificateChainAddress() {
        if (refCnt() <= 0) {
            throw new IllegalReferenceCountException();
        }
        return this.chain;
    }

    @Override // io.netty.handler.ssl.OpenSslKeyMaterial
    public long privateKeyAddress() {
        if (refCnt() <= 0) {
            throw new IllegalReferenceCountException();
        }
        return this.privateKey;
    }

    @Override // io.netty.util.AbstractReferenceCounted
    protected void deallocate() {
        SSL.freeX509Chain(this.chain);
        this.chain = 0L;
        SSL.freePrivateKey(this.privateKey);
        this.privateKey = 0L;
        ResourceLeakTracker<DefaultOpenSslKeyMaterial> resourceLeakTracker = this.leak;
        if (resourceLeakTracker != null) {
            resourceLeakTracker.close(this);
        }
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public DefaultOpenSslKeyMaterial retain() {
        ResourceLeakTracker<DefaultOpenSslKeyMaterial> resourceLeakTracker = this.leak;
        if (resourceLeakTracker != null) {
            resourceLeakTracker.record();
        }
        super.retain();
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public DefaultOpenSslKeyMaterial retain(int i) {
        ResourceLeakTracker<DefaultOpenSslKeyMaterial> resourceLeakTracker = this.leak;
        if (resourceLeakTracker != null) {
            resourceLeakTracker.record();
        }
        super.retain(i);
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public DefaultOpenSslKeyMaterial touch() {
        ResourceLeakTracker<DefaultOpenSslKeyMaterial> resourceLeakTracker = this.leak;
        if (resourceLeakTracker != null) {
            resourceLeakTracker.record();
        }
        super.touch();
        return this;
    }

    @Override // io.netty.util.ReferenceCounted
    public DefaultOpenSslKeyMaterial touch(Object obj) {
        ResourceLeakTracker<DefaultOpenSslKeyMaterial> resourceLeakTracker = this.leak;
        if (resourceLeakTracker != null) {
            resourceLeakTracker.record(obj);
        }
        return this;
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public boolean release() {
        ResourceLeakTracker<DefaultOpenSslKeyMaterial> resourceLeakTracker = this.leak;
        if (resourceLeakTracker != null) {
            resourceLeakTracker.record();
        }
        return super.release();
    }

    @Override // io.netty.util.AbstractReferenceCounted, io.netty.util.ReferenceCounted
    public boolean release(int i) {
        ResourceLeakTracker<DefaultOpenSslKeyMaterial> resourceLeakTracker = this.leak;
        if (resourceLeakTracker != null) {
            resourceLeakTracker.record();
        }
        return super.release(i);
    }
}
