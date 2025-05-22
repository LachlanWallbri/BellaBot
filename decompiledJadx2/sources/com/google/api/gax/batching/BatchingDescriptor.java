package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For google-cloud-java client use only.")
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public interface BatchingDescriptor<ElementT, ElementResultT, RequestT, ResponseT> {
    long countBytes(ElementT elementt);

    BatchingRequestBuilder<ElementT, RequestT> newRequestBuilder(RequestT requestt);

    void splitException(Throwable th, List<BatchEntry<ElementT, ElementResultT>> list);

    void splitResponse(ResponseT responset, List<BatchEntry<ElementT, ElementResultT>> list);
}
