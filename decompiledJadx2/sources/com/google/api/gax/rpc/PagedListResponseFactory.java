package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> {
    ApiFuture<PagedListResponseT> getFuturePagedResponse(UnaryCallable<RequestT, ResponseT> unaryCallable, RequestT requestt, ApiCallContext apiCallContext, ApiFuture<ResponseT> apiFuture);
}
