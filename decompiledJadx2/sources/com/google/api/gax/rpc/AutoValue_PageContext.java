package com.google.api.gax.rpc;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
final class AutoValue_PageContext<RequestT, ResponseT, ResourceT> extends PageContext<RequestT, ResponseT, ResourceT> {
    private final ApiCallContext callContext;
    private final UnaryCallable<RequestT, ResponseT> callable;
    private final PagedListDescriptor<RequestT, ResponseT, ResourceT> pageDescriptor;
    private final RequestT request;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_PageContext(UnaryCallable<RequestT, ResponseT> unaryCallable, PagedListDescriptor<RequestT, ResponseT, ResourceT> pagedListDescriptor, RequestT requestt, ApiCallContext apiCallContext) {
        if (unaryCallable == null) {
            throw new NullPointerException("Null callable");
        }
        this.callable = unaryCallable;
        if (pagedListDescriptor == null) {
            throw new NullPointerException("Null pageDescriptor");
        }
        this.pageDescriptor = pagedListDescriptor;
        if (requestt == null) {
            throw new NullPointerException("Null request");
        }
        this.request = requestt;
        if (apiCallContext == null) {
            throw new NullPointerException("Null callContext");
        }
        this.callContext = apiCallContext;
    }

    @Override // com.google.api.gax.rpc.PageContext
    public UnaryCallable<RequestT, ResponseT> getCallable() {
        return this.callable;
    }

    @Override // com.google.api.gax.rpc.PageContext
    public PagedListDescriptor<RequestT, ResponseT, ResourceT> getPageDescriptor() {
        return this.pageDescriptor;
    }

    @Override // com.google.api.gax.rpc.PageContext
    public RequestT getRequest() {
        return this.request;
    }

    @Override // com.google.api.gax.rpc.PageContext
    public ApiCallContext getCallContext() {
        return this.callContext;
    }

    public String toString() {
        return "PageContext{callable=" + this.callable + ", pageDescriptor=" + this.pageDescriptor + ", request=" + this.request + ", callContext=" + this.callContext + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PageContext)) {
            return false;
        }
        PageContext pageContext = (PageContext) obj;
        return this.callable.equals(pageContext.getCallable()) && this.pageDescriptor.equals(pageContext.getPageDescriptor()) && this.request.equals(pageContext.getRequest()) && this.callContext.equals(pageContext.getCallContext());
    }

    public int hashCode() {
        return ((((((this.callable.hashCode() ^ 1000003) * 1000003) ^ this.pageDescriptor.hashCode()) * 1000003) ^ this.request.hashCode()) * 1000003) ^ this.callContext.hashCode();
    }
}
