package io.grpc.netty.shaded.io.netty.internal.tcnative;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class CertificateVerifierTask extends SSLTask {
    private final String authAlgorithm;
    private final CertificateVerifier verifier;
    private final byte[][] x509;

    CertificateVerifierTask(long j, byte[][] bArr, String str, CertificateVerifier certificateVerifier) {
        super(j);
        this.x509 = bArr;
        this.authAlgorithm = str;
        this.verifier = certificateVerifier;
    }

    @Override // io.grpc.netty.shaded.io.netty.internal.tcnative.SSLTask
    protected int runTask(long j) {
        return this.verifier.verify(j, this.x509, this.authAlgorithm);
    }
}
