package com.google.api.gax.rpc;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface PagedListDescriptor<RequestT, ResponseT, ResourceT> {
    String emptyToken();

    String extractNextToken(ResponseT responset);

    Integer extractPageSize(RequestT requestt);

    Iterable<ResourceT> extractResources(ResponseT responset);

    RequestT injectPageSize(RequestT requestt, int i);

    RequestT injectToken(RequestT requestt, String str);
}
