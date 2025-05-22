package com.google.api.gax.grpc;

import com.google.common.base.Preconditions;
import io.grpc.CallOptions;
import io.grpc.ClientCall;
import io.grpc.ForwardingClientCall;
import io.grpc.ForwardingClientCallListener;
import io.grpc.ManagedChannel;
import io.grpc.Metadata;
import io.grpc.MethodDescriptor;
import io.grpc.Status;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
class SafeShutdownManagedChannel extends ManagedChannel {
    private final ManagedChannel delegate;
    private final AtomicInteger outstandingCalls = new AtomicInteger(0);
    private volatile boolean isShutdownSafely = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SafeShutdownManagedChannel(ManagedChannel managedChannel) {
        this.delegate = managedChannel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void shutdownSafely() {
        this.isShutdownSafely = true;
        if (this.outstandingCalls.get() == 0) {
            this.delegate.shutdown();
        }
    }

    @Override // io.grpc.ManagedChannel
    public ManagedChannel shutdown() {
        this.delegate.shutdown();
        return this;
    }

    @Override // io.grpc.ManagedChannel
    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    @Override // io.grpc.ManagedChannel
    public ManagedChannel shutdownNow() {
        this.delegate.shutdownNow();
        return this;
    }

    @Override // io.grpc.ManagedChannel
    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    @Override // io.grpc.ManagedChannel
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.delegate.awaitTermination(j, timeUnit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClientCallClose() {
        if (this.outstandingCalls.decrementAndGet() == 0 && this.isShutdownSafely) {
            shutdownSafely();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class DecrementOutstandingCalls<RespT> extends ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT> {
        DecrementOutstandingCalls(ClientCall.Listener<RespT> listener) {
            super(listener);
        }

        @Override // io.grpc.ForwardingClientCallListener.SimpleForwardingClientCallListener, io.grpc.ForwardingClientCallListener, io.grpc.PartialForwardingClientCallListener, io.grpc.ClientCall.Listener
        public void onClose(Status status, Metadata metadata) {
            try {
                super.onClose(status, metadata);
            } finally {
                SafeShutdownManagedChannel.this.onClientCallClose();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class ClientCallProxy<ReqT, RespT> extends ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT> {
        ClientCallProxy(ClientCall<ReqT, RespT> clientCall) {
            super(clientCall);
        }

        @Override // io.grpc.ForwardingClientCall, io.grpc.ClientCall
        public void start(ClientCall.Listener<RespT> listener, Metadata metadata) {
            super.start(new DecrementOutstandingCalls(listener), metadata);
        }
    }

    @Override // io.grpc.Channel
    public <RequestT, ResponseT> ClientCall<RequestT, ResponseT> newCall(MethodDescriptor<RequestT, ResponseT> methodDescriptor, CallOptions callOptions) {
        Preconditions.checkState(!this.isShutdownSafely);
        ClientCallProxy clientCallProxy = new ClientCallProxy(this.delegate.newCall(methodDescriptor, callOptions));
        this.outstandingCalls.incrementAndGet();
        return clientCallProxy;
    }

    @Override // io.grpc.Channel
    public String authority() {
        return this.delegate.authority();
    }
}
