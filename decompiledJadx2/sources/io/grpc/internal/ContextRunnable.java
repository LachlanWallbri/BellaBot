package io.grpc.internal;

import io.grpc.Context;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
abstract class ContextRunnable implements Runnable {
    private final Context context;

    public abstract void runInContext();

    /* JADX INFO: Access modifiers changed from: protected */
    public ContextRunnable(Context context) {
        this.context = context;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context attach = this.context.attach();
        try {
            runInContext();
        } finally {
            this.context.detach(attach);
        }
    }
}
