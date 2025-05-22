package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.ServerCall;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
abstract class PartialForwardingServerCallListener<ReqT> extends ServerCall.Listener<ReqT> {
    protected abstract ServerCall.Listener<?> delegate();

    @Override // io.grpc.ServerCall.Listener
    public void onHalfClose() {
        delegate().onHalfClose();
    }

    @Override // io.grpc.ServerCall.Listener
    public void onCancel() {
        delegate().onCancel();
    }

    @Override // io.grpc.ServerCall.Listener
    public void onComplete() {
        delegate().onComplete();
    }

    @Override // io.grpc.ServerCall.Listener
    public void onReady() {
        delegate().onReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
