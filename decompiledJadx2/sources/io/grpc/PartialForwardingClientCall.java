package io.grpc;

import com.google.common.base.MoreObjects;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
abstract class PartialForwardingClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    protected abstract ClientCall<?, ?> delegate();

    @Override // io.grpc.ClientCall
    public void request(int i) {
        delegate().request(i);
    }

    @Override // io.grpc.ClientCall
    public void cancel(@Nullable String str, @Nullable Throwable th) {
        delegate().cancel(str, th);
    }

    @Override // io.grpc.ClientCall
    public void halfClose() {
        delegate().halfClose();
    }

    @Override // io.grpc.ClientCall
    public void setMessageCompression(boolean z) {
        delegate().setMessageCompression(z);
    }

    @Override // io.grpc.ClientCall
    public boolean isReady() {
        return delegate().isReady();
    }

    @Override // io.grpc.ClientCall
    public Attributes getAttributes() {
        return delegate().getAttributes();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
