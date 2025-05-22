package io.grpc.netty.shaded.io.netty.util.internal;

import io.grpc.netty.shaded.io.netty.util.ReferenceCountUtil;
import io.grpc.netty.shaded.io.netty.util.concurrent.Promise;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectPool;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PendingWrite {
    private static final ObjectPool<PendingWrite> RECYCLER = ObjectPool.newPool(new ObjectPool.ObjectCreator<PendingWrite>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.PendingWrite.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // io.grpc.netty.shaded.io.netty.util.internal.ObjectPool.ObjectCreator
        public PendingWrite newObject(ObjectPool.Handle<PendingWrite> handle) {
            return new PendingWrite(handle);
        }
    });
    private final ObjectPool.Handle<PendingWrite> handle;
    private Object msg;
    private Promise<Void> promise;

    public static PendingWrite newInstance(Object obj, Promise<Void> promise) {
        PendingWrite pendingWrite = RECYCLER.get();
        pendingWrite.msg = obj;
        pendingWrite.promise = promise;
        return pendingWrite;
    }

    private PendingWrite(ObjectPool.Handle<PendingWrite> handle) {
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
