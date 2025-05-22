package com.google.api.gax.rpc;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class PageContext<RequestT, ResponseT, ResourceT> {
    public abstract ApiCallContext getCallContext();

    public abstract UnaryCallable<RequestT, ResponseT> getCallable();

    public abstract PagedListDescriptor<RequestT, ResponseT, ResourceT> getPageDescriptor();

    public abstract RequestT getRequest();

    public PageContext<RequestT, ResponseT, ResourceT> withRequest(RequestT requestt) {
        return new AutoValue_PageContext(getCallable(), getPageDescriptor(), requestt, getCallContext());
    }

    public static <RequestT, ResponseT, ResourceT> PageContext<RequestT, ResponseT, ResourceT> create(UnaryCallable<RequestT, ResponseT> unaryCallable, PagedListDescriptor<RequestT, ResponseT, ResourceT> pagedListDescriptor, RequestT requestt, ApiCallContext apiCallContext) {
        return new AutoValue_PageContext(unaryCallable, pagedListDescriptor, requestt, apiCallContext);
    }
}
