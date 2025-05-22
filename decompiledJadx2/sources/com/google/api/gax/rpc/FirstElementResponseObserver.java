package com.google.api.gax.rpc;

import com.google.api.core.AbstractApiFuture;
import com.google.api.core.ApiFuture;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public class FirstElementResponseObserver<ResponseT> extends StateCheckingResponseObserver<ResponseT> {
    private StreamController controller;
    private final FirstElementResponseObserver<ResponseT>.MyFuture future = new MyFuture();

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onStartImpl(StreamController streamController) {
        this.controller = streamController;
        streamController.disableAutoInboundFlowControl();
        streamController.request(1);
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onResponseImpl(ResponseT responset) {
        this.future.set(responset);
        this.controller.cancel();
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onErrorImpl(Throwable th) {
        this.future.setException(th);
    }

    @Override // com.google.api.gax.rpc.StateCheckingResponseObserver
    protected void onCompleteImpl() {
        this.future.set(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ApiFuture<ResponseT> getFuture() {
        return this.future;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class MyFuture extends AbstractApiFuture<ResponseT> {
        private MyFuture() {
        }

        @Override // com.google.api.core.AbstractApiFuture
        protected void interruptTask() {
            FirstElementResponseObserver.this.controller.cancel();
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.core.AbstractApiFuture
        public boolean set(ResponseT responset) {
            return super.set(responset);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.google.api.core.AbstractApiFuture
        public boolean setException(Throwable th) {
            return super.setException(th);
        }
    }
}
