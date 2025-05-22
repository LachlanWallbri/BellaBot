package io.grpc.netty.shaded.io.netty.internal.tcnative;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
abstract class SSLTask implements Runnable {
    private boolean complete;
    private int returnValue;
    private final long ssl;

    protected abstract int runTask(long j);

    /* JADX INFO: Access modifiers changed from: protected */
    public SSLTask(long j) {
        this.ssl = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        if (this.complete) {
            return;
        }
        this.complete = true;
        this.returnValue = runTask(this.ssl);
    }
}
