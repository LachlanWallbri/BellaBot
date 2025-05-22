package io.grpc.netty.shaded.io.netty.internal.tcnative;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
abstract class SSLPrivateKeyMethodTask extends SSLTask {
    private static final byte[] EMPTY = new byte[0];
    private final SSLPrivateKeyMethod method;
    private byte[] resultBytes;

    protected abstract byte[] runTask(long j, SSLPrivateKeyMethod sSLPrivateKeyMethod) throws Exception;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SSLPrivateKeyMethodTask(long j, SSLPrivateKeyMethod sSLPrivateKeyMethod) {
        super(j);
        this.method = sSLPrivateKeyMethod;
    }

    @Override // io.grpc.netty.shaded.io.netty.internal.tcnative.SSLTask
    protected final int runTask(long j) {
        try {
            this.resultBytes = runTask(j, this.method);
            return 1;
        } catch (Exception unused) {
            this.resultBytes = EMPTY;
            return 0;
        }
    }
}
