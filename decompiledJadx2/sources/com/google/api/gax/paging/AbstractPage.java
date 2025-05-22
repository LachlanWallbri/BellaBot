package com.google.api.gax.paging;

import com.google.api.core.ApiFunction;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.api.core.InternalApi;
import com.google.api.gax.paging.AbstractPage;
import com.google.api.gax.rpc.ApiExceptions;
import com.google.api.gax.rpc.PageContext;
import com.google.common.base.Strings;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.MoreExecutors;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class AbstractPage<RequestT, ResponseT, ResourceT, PageT extends AbstractPage<RequestT, ResponseT, ResourceT, PageT>> implements AsyncPage<ResourceT> {
    private final PageContext<RequestT, ResponseT, ResourceT> context;
    private final ResponseT response;

    protected abstract PageT createPage(PageContext<RequestT, ResponseT, ResourceT> pageContext, ResponseT responset);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPage(PageContext<RequestT, ResponseT, ResourceT> pageContext, ResponseT responset) {
        this.context = pageContext;
        this.response = responset;
    }

    @InternalApi("Visible for testing")
    public ApiFuture<PageT> createPageAsync(final PageContext<RequestT, ResponseT, ResourceT> pageContext, ApiFuture<ResponseT> apiFuture) {
        return ApiFutures.transform(apiFuture, new ApiFunction<ResponseT, PageT>() { // from class: com.google.api.gax.paging.AbstractPage.1
            @Override // com.google.api.core.ApiFunction
            public /* bridge */ /* synthetic */ Object apply(Object obj) {
                return apply((C20861) obj);
            }

            @Override // com.google.api.core.ApiFunction
            public PageT apply(ResponseT responset) {
                return (PageT) AbstractPage.this.createPage(pageContext, responset);
            }
        }, MoreExecutors.directExecutor());
    }

    @Override // com.google.api.gax.paging.Page
    public boolean hasNextPage() {
        return !getNextPageToken().equals(this.context.getPageDescriptor().emptyToken());
    }

    @Override // com.google.api.gax.paging.Page
    public String getNextPageToken() {
        return Strings.nullToEmpty(this.context.getPageDescriptor().extractNextToken(this.response));
    }

    @Override // com.google.api.gax.paging.Page
    public PageT getNextPage() {
        return getNextPageImpl(null);
    }

    public PageT getNextPage(int i) {
        return getNextPageImpl(Integer.valueOf(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.api.gax.paging.AsyncPage
    public ApiFuture<PageT> getNextPageAsync() {
        if (hasNextPage()) {
            PageContext<RequestT, ResponseT, ResourceT> withRequest = this.context.withRequest(this.context.getPageDescriptor().injectToken(this.context.getRequest(), getNextPageToken()));
            return createPageAsync(withRequest, callApi(withRequest));
        }
        return ApiFutures.immediateFuture(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private PageT getNextPageImpl(Integer num) {
        if (!hasNextPage()) {
            return null;
        }
        Object injectToken = this.context.getPageDescriptor().injectToken(this.context.getRequest(), getNextPageToken());
        if (num != null) {
            injectToken = this.context.getPageDescriptor().injectPageSize(injectToken, num.intValue());
        }
        PageContext withRequest = this.context.withRequest(injectToken);
        return (PageT) createPage(withRequest, ApiExceptions.callAndTranslateApiException(callApi(withRequest)));
    }

    private ApiFuture<ResponseT> callApi(PageContext<RequestT, ResponseT, ResourceT> pageContext) {
        return pageContext.getCallable().futureCall(pageContext.getRequest(), pageContext.getCallContext());
    }

    @Override // com.google.api.gax.paging.Page
    public Iterable<ResourceT> iterateAll() {
        return new Iterable<ResourceT>() { // from class: com.google.api.gax.paging.AbstractPage.2
            @Override // java.lang.Iterable
            public Iterator<ResourceT> iterator() {
                return new AllResourcesIterator();
            }
        };
    }

    @Override // com.google.api.gax.paging.Page
    public Iterable<ResourceT> getValues() {
        return this.context.getPageDescriptor().extractResources(this.response);
    }

    public ResponseT getResponse() {
        return this.response;
    }

    public RequestT getRequest() {
        return this.context.getRequest();
    }

    public int getPageElementCount() {
        return Iterables.size(this.context.getPageDescriptor().extractResources(this.response));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public PageContext<RequestT, ResponseT, ResourceT> getContext() {
        return this.context;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class AllResourcesIterator extends AbstractIterator<ResourceT> {
        private Iterator<ResourceT> currentIterator;
        private AbstractPage<RequestT, ResponseT, ResourceT, PageT> currentPage;

        private AllResourcesIterator() {
            this.currentPage = AbstractPage.this;
            this.currentIterator = this.currentPage.getValues().iterator();
        }

        @Override // com.google.common.collect.AbstractIterator
        protected ResourceT computeNext() {
            while (!this.currentIterator.hasNext()) {
                this.currentPage = this.currentPage.getNextPage();
                AbstractPage<RequestT, ResponseT, ResourceT, PageT> abstractPage = this.currentPage;
                if (abstractPage == null) {
                    return endOfData();
                }
                this.currentIterator = abstractPage.getValues().iterator();
            }
            return this.currentIterator.next();
        }
    }
}
