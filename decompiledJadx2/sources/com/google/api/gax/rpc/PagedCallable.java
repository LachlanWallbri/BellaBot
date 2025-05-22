package com.google.api.gax.rpc;

import com.google.api.core.ApiFuture;
import com.google.api.core.InternalApi;
import com.google.common.base.Preconditions;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For use by transport-specific implementations")
/* loaded from: classes2.dex */
public class PagedCallable<RequestT, ResponseT, PagedListResponseT> extends UnaryCallable<RequestT, PagedListResponseT> {
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory;

    public PagedCallable(UnaryCallable<RequestT, ResponseT> unaryCallable, PagedListResponseFactory<RequestT, ResponseT, PagedListResponseT> pagedListResponseFactory) {
        this.callable = (UnaryCallable) Preconditions.checkNotNull(unaryCallable);
        this.pagedListResponseFactory = pagedListResponseFactory;
    }

    public String toString() {
        return String.format("paged(%s)", this.callable);
    }

    @Override // com.google.api.gax.rpc.UnaryCallable
    public ApiFuture<PagedListResponseT> futureCall(RequestT requestt, ApiCallContext apiCallContext) {
        return this.pagedListResponseFactory.getFuturePagedResponse(this.callable.withDefaultCallContext(apiCallContext), requestt, apiCallContext, this.callable.futureCall(requestt, apiCallContext));
    }
}
