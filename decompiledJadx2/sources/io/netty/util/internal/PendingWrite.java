package io.netty.util.internal;

import io.netty.util.Recycler;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Promise;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class PendingWrite {
    private static final Recycler<PendingWrite> RECYCLER = new Recycler<PendingWrite>() { // from class: io.netty.util.internal.PendingWrite.1
        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.netty.util.Recycler
        public PendingWrite newObject(Recycler.Handle<PendingWrite> handle) {
            return new PendingWrite(handle);
        }
    };
    private final Recycler.Handle<PendingWrite> handle;
    private Object msg;
    private Promise<Void> promise;

    public static PendingWrite newInstance(Object obj, Promise<Void> promise) {
        PendingWrite pendingWrite = RECYCLER.get();
        pendingWrite.msg = obj;
        pendingWrite.promise = promise;
        return pendingWrite;
    }

    private PendingWrite(Recycler.Handle<PendingWrite> handle) {
        this.handle = handle;
    }

    public boolean recycle() {
        this.msg = null;
        this.promise = null;
        this.handle.recycle(this);
        return true;
    }

    public boolean failAndRecycle(Throwable th) {
        ReferenceCountUtil.release(this.msg);
        Promise<Void> promise = this.promise;
        if (promise != null) {
            promise.setFailure(th);
        }
        return recycle();
    }

    public boolean successAndRecycle() {
        Promise<Void> promise = this.promise;
        if (promise != null) {
            promise.setSuccess(null);
        }
        return recycle();
    }

    public Object msg() {
        return this.msg;
    }

    public Promise<Void> promise() {
        return this.promise;
    }

    public Promise<Void> recycleAndGet() {
        Promise<Void> promise = this.promise;
        recycle();
        return promise;
    }
}
