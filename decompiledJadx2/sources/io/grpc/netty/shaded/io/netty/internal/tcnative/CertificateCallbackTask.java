package io.grpc.netty.shaded.io.netty.internal.tcnative;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class CertificateCallbackTask extends SSLTask {
    private final byte[][] asn1DerEncodedPrincipals;
    private final CertificateCallback callback;
    private final byte[] keyTypeBytes;

    CertificateCallbackTask(long j, byte[] bArr, byte[][] bArr2, CertificateCallback certificateCallback) {
        super(j);
        this.keyTypeBytes = bArr;
        this.asn1DerEncodedPrincipals = bArr2;
        this.callback = certificateCallback;
    }

    @Override // io.grpc.netty.shaded.io.netty.internal.tcnative.SSLTask
    protected int runTask(long j) {
        try {
            this.callback.handle(j, this.keyTypeBytes, this.asn1DerEncodedPrincipals);
            return 1;
        } catch (Exception unused) {
            return 0;
        }
    }
}
