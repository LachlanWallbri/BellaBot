package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public abstract class BidiStreamingCallable<RequestT, ResponseT> {
    public abstract ClientStream<RequestT> internalCall(ResponseObserver<ResponseT> responseObserver, ClientStreamReadyObserver<RequestT> clientStreamReadyObserver, ApiCallContext apiCallContext);

    public void call(BidiStreamObserver<RequestT, ResponseT> bidiStreamObserver) {
        call(bidiStreamObserver, null);
    }

    public void call(final BidiStreamObserver<RequestT, ResponseT> bidiStreamObserver, ApiCallContext apiCallContext) {
        internalCall(bidiStreamObserver, new ClientStreamReadyObserver<RequestT>() { // from class: com.google.api.gax.rpc.BidiStreamingCallable.1
            @Override // com.google.api.gax.rpc.ClientStreamReadyObserver
            public void onReady(ClientStream<RequestT> clientStream) {
                bidiStreamObserver.onReady(clientStream);
            }
        }, apiCallContext);
    }

    public BidiStream<RequestT, ResponseT> call() {
        return call((ApiCallContext) null);
    }

    public BidiStream<RequestT, ResponseT> call(ApiCallContext apiCallContext) {
        BidiStream<RequestT, ResponseT> bidiStream = new BidiStream<>();
        bidiStream.setClientStream(splitCall(bidiStream.observer(), apiCallContext));
        return bidiStream;
    }

    public ClientStream<RequestT> splitCall(ResponseObserver<ResponseT> responseObserver) {
        return splitCall(responseObserver, null);
    }

    public ClientStream<RequestT> splitCall(ResponseObserver<ResponseT> responseObserver, ApiCallContext apiCallContext) {
        return internalCall(responseObserver, new ClientStreamReadyObserver<RequestT>() { // from class: com.google.api.gax.rpc.BidiStreamingCallable.2
            @Override // com.google.api.gax.rpc.ClientStreamReadyObserver
            public void onReady(ClientStream<RequestT> clientStream) {
            }
        }, apiCallContext);
    }

    @Deprecated
    public ApiStreamObserver<RequestT> bidiStreamingCall(ApiStreamObserver<ResponseT> apiStreamObserver, ApiCallContext apiCallContext) {
        final ClientStream<RequestT> splitCall = splitCall(new ApiStreamObserverAdapter(apiStreamObserver), apiCallContext);
        return new ApiStreamObserver<RequestT>() { // from class: com.google.api.gax.rpc.BidiStreamingCallable.3
            @Override // com.google.api.gax.rpc.ApiStreamObserver
            public void onNext(RequestT requestt) {
                splitCall.send(requestt);
            }

            @Override // com.google.api.gax.rpc.ApiStreamObserver
            public void onError(Throwable th) {
                splitCall.closeSendWithError(th);
            }

            @Override // com.google.api.gax.rpc.ApiStreamObserver
            public void onCompleted() {
                splitCall.closeSend();
            }
        };
    }

    @Deprecated
    public ApiStreamObserver<RequestT> bidiStreamingCall(ApiStreamObserver<ResponseT> apiStreamObserver) {
        return bidiStreamingCall(apiStreamObserver, null);
    }

    public BidiStreamingCallable<RequestT, ResponseT> withDefaultCallContext(final ApiCallContext apiCallContext) {
        return new BidiStreamingCallable<RequestT, ResponseT>() { // from class: com.google.api.gax.rpc.BidiStreamingCallable.4
            @Override // com.google.api.gax.rpc.BidiStreamingCallable
            public ClientStream<RequestT> internalCall(ResponseObserver<ResponseT> responseObserver, ClientStreamReadyObserver<RequestT> clientStreamReadyObserver, ApiCallContext apiCallContext2) {
                return BidiStreamingCallable.this.internalCall(responseObserver, clientStreamReadyObserver, apiCallContext.merge(apiCallContext2));
            }
        };
    }
}
