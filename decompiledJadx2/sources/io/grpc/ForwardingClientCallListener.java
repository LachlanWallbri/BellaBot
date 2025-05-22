package io.grpc;

import io.grpc.ClientCall;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class ForwardingClientCallListener<RespT> extends PartialForwardingClientCallListener<RespT> {
    @Override // io.grpc.PartialForwardingClientCallListener
    protected abstract ClientCall.Listener<RespT> delegate();

    @Override // io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
    public /* bridge */ /* synthetic */ void onClose(Status status, Metadata metadata) {
        super.onClose(status, metadata);
    }

    @Override // io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
    public /* bridge */ /* synthetic */ void onHeaders(Metadata metadata) {
        super.onHeaders(metadata);
    }

    @Override // io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
    public /* bridge */ /* synthetic */ void onReady() {
        super.onReady();
    }

    @Override // io.grpc.PartialForwardingClientCallListener
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    @Override // io.grpc.ClientCall.Listener
    public void onMessage(RespT respt) {
        delegate().onMessage(respt);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static abstract class SimpleForwardingClientCallListener<RespT> extends ForwardingClientCallListener<RespT> {
        private final ClientCall.Listener<RespT> delegate;

        @Override // io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
        public /* bridge */ /* synthetic */ void onClose(Status status, Metadata metadata) {
            super.onClose(status, metadata);
        }

        @Override // io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
        public /* bridge */ /* synthetic */ void onHeaders(Metadata metadata) {
            super.onHeaders(metadata);
        }

        @Override // io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
        public /* bridge */ /* synthetic */ void onReady() {
            super.onReady();
        }

        @Override // io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener
        public /* bridge */ /* synthetic */ String toString() {
            return super.toString();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public SimpleForwardingClientCallListener(ClientCall.Listener<RespT> listener) {
            this.delegate = listener;
        }

        @Override // io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener
        protected ClientCall.Listener<RespT> delegate() {
            return this.delegate;
        }
    }
}
