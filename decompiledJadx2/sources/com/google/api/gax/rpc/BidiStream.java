package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public class BidiStream<RequestT, ResponseT> extends ServerStream<ResponseT> implements ClientStream<RequestT> {
    private ClientStream<RequestT> clientStream;

    /* JADX INFO: Access modifiers changed from: package-private */
    @InternalApi("For use by BidiStreamingCallable only.")
    public BidiStream() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @InternalApi("For use by BidiStreamingCallable only.")
    public void setClientStream(ClientStream<RequestT> clientStream) {
        this.clientStream = clientStream;
    }

    @Override // com.google.api.gax.rpc.ClientStream
    public void send(RequestT requestt) {
        this.clientStream.send(requestt);
    }

    @Override // com.google.api.gax.rpc.ClientStream
    public boolean isSendReady() {
        return this.clientStream.isSendReady();
    }

    @Override // com.google.api.gax.rpc.ClientStream
    public void closeSend() {
        this.clientStream.closeSend();
    }

    @Override // com.google.api.gax.rpc.ClientStream
    public void closeSendWithError(Throwable th) {
        this.clientStream.closeSendWithError(th);
    }
}
