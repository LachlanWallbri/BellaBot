package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public abstract class ServerStreamingCallable<RequestT, ResponseT> {
    private final FirstElementCallable<RequestT, ResponseT> firstCallable = new FirstElementCallable<>(this);
    private final SpoolingCallable<RequestT, ResponseT> spoolingCallable = new SpoolingCallable<>(this);

    public abstract void call(RequestT requestt, ResponseObserver<ResponseT> responseObserver, ApiCallContext apiCallContext);

    public UnaryCallable<RequestT, ResponseT> first() {
        return this.firstCallable;
    }

    public UnaryCallable<RequestT, List<ResponseT>> all() {
        return this.spoolingCallable;
    }

    public ServerStream<ResponseT> call(RequestT requestt) {
        return call((ServerStreamingCallable<RequestT, ResponseT>) requestt, (ApiCallContext) null);
    }

    public ServerStream<ResponseT> call(RequestT requestt, ApiCallContext apiCallContext) {
        ServerStream<ResponseT> serverStream = new ServerStream<>();
        call(requestt, serverStream.observer(), apiCallContext);
        return serverStream;
    }

    public void call(RequestT requestt, ResponseObserver<ResponseT> responseObserver) {
        call(requestt, responseObserver, null);
    }

    @Deprecated
    public void serverStreamingCall(RequestT requestt, ApiStreamObserver<ResponseT> apiStreamObserver, ApiCallContext apiCallContext) {
        call(requestt, new ApiStreamObserverAdapter(apiStreamObserver), apiCallContext);
    }

    @Deprecated
    public void serverStreamingCall(RequestT requestt, ApiStreamObserver<ResponseT> apiStreamObserver) {
        serverStreamingCall(requestt, apiStreamObserver, null);
    }

    @Deprecated
    public Iterator<ResponseT> blockingServerStreamingCall(RequestT requestt, ApiCallContext apiCallContext) {
        return call((ServerStreamingCallable<RequestT, ResponseT>) requestt, apiCallContext).iterator();
    }

    @Deprecated
    public Iterator<ResponseT> blockingServerStreamingCall(RequestT requestt) {
        return blockingServerStreamingCall(requestt, null);
    }

    public ServerStreamingCallable<RequestT, ResponseT> withDefaultCallContext(final ApiCallContext apiCallContext) {
        return new ServerStreamingCallable<RequestT, ResponseT>() { // from class: com.google.api.gax.rpc.ServerStreamingCallable.1
            @Override // com.google.api.gax.rpc.ServerStreamingCallable
            public UnaryCallable<RequestT, ResponseT> first() {
                return ServerStreamingCallable.this.first().withDefaultCallContext(apiCallContext);
            }

            @Override // com.google.api.gax.rpc.ServerStreamingCallable
            public UnaryCallable<RequestT, List<ResponseT>> all() {
                return ServerStreamingCallable.this.all().withDefaultCallContext(apiCallContext);
            }

            @Override // com.google.api.gax.rpc.ServerStreamingCallable
            public void call(RequestT requestt, ResponseObserver<ResponseT> responseObserver, ApiCallContext apiCallContext2) {
                ServerStreamingCallable.this.call(requestt, responseObserver, apiCallContext.merge(apiCallContext2));
            }
        };
    }
}
