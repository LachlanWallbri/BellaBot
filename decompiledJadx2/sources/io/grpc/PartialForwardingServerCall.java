package io.grpc;

import com.google.common.base.MoreObjects;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class PartialForwardingServerCall<ReqT, RespT> extends ServerCall<ReqT, RespT> {
    protected abstract ServerCall<?, ?> delegate();

    @Override // io.grpc.ServerCall
    public void request(int i) {
        delegate().request(i);
    }

    @Override // io.grpc.ServerCall
    public void sendHeaders(Metadata metadata) {
        delegate().sendHeaders(metadata);
    }

    @Override // io.grpc.ServerCall
    public boolean isReady() {
        return delegate().isReady();
    }

    @Override // io.grpc.ServerCall
    public void close(Status status, Metadata metadata) {
        delegate().close(status, metadata);
    }

    @Override // io.grpc.ServerCall
    public boolean isCancelled() {
        return delegate().isCancelled();
    }

    @Override // io.grpc.ServerCall
    public void setMessageCompression(boolean z) {
        delegate().setMessageCompression(z);
    }

    @Override // io.grpc.ServerCall
    public void setCompression(String str) {
        delegate().setCompression(str);
    }

    @Override // io.grpc.ServerCall
    public Attributes getAttributes() {
        return delegate().getAttributes();
    }

    @Override // io.grpc.ServerCall
    public String getAuthority() {
        return delegate().getAuthority();
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("delegate", delegate()).toString();
    }
}
