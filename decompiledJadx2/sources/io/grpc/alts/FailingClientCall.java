package io.grpc.alts;

import io.grpc.ClientCall;
import io.grpc.Metadata;
import io.grpc.Status;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class FailingClientCall<ReqT, RespT> extends ClientCall<ReqT, RespT> {
    private final Status error;

    @Override // io.grpc.ClientCall
    public void cancel(String str, Throwable th) {
    }

    @Override // io.grpc.ClientCall
    public void halfClose() {
    }

    @Override // io.grpc.ClientCall
    public void request(int i) {
    }

    @Override // io.grpc.ClientCall
    public void sendMessage(ReqT reqt) {
    }

    public FailingClientCall(Status status) {
        this.error = status;
    }

    @Override // io.grpc.ClientCall
    public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
        listener.onClose(this.error, new Metadata());
    }
}
