package io.grpc;

import com.google.common.base.MoreObjects;
import io.grpc.ClientCall;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
abstract class PartialForwardingClientCallListener<RespT> extends ClientCall.Listener<RespT> {
    protected abstract ClientCall.Listener<?> delegate();

    @Override // io.grpc.ClientCall.Listener
    public void onHeaders(Metadata metadata) {
        delegate().onHeaders(metadata);
    }

    @Override // io.grpc.ClientCall.Listener
    public void onClose(Status status, Metadata metadata) {
        delegate().onClose(status, metadata);
    }

    @Override // io.grpc.ClientCall.Listener
    public void onReady() {
        delegate().onReady();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
