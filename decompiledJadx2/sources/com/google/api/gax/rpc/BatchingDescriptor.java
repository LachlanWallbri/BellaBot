package com.google.api.gax.rpc;

import com.google.api.core.BetaApi;
import com.google.api.gax.batching.PartitionKey;
import com.google.api.gax.batching.RequestBuilder;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public interface BatchingDescriptor<RequestT, ResponseT> {
    long countBytes(RequestT requestt);

    long countElements(RequestT requestt);

    PartitionKey getBatchPartitionKey(RequestT requestt);

    RequestBuilder<RequestT> getRequestBuilder();

    void splitException(Throwable th, Collection<? extends BatchedRequestIssuer<ResponseT>> collection);

    void splitResponse(ResponseT responset, Collection<? extends BatchedRequestIssuer<ResponseT>> collection);
}
