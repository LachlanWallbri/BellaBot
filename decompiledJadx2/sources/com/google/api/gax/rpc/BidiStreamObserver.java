package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for streaming is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public interface BidiStreamObserver<RequestT, ResponseT> extends ResponseObserver<ResponseT>, ClientStreamReadyObserver<RequestT> {
}
