package com.google.api.gax.paging;

import com.google.api.gax.paging.AbstractFixedSizeCollection;
import com.google.api.gax.paging.AbstractPage;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class AbstractPagedListResponse<RequestT, ResponseT, ResourceT, PageT extends AbstractPage<RequestT, ResponseT, ResourceT, PageT>, CollectionT extends AbstractFixedSizeCollection<RequestT, ResponseT, ResourceT, PageT, CollectionT>> implements PagedListResponse<ResourceT> {
    private final CollectionT emptyCollection;
    private final PageT page;

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    public interface Next<T> {
        T next(T t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractPagedListResponse(PageT paget, CollectionT collectiont) {
        this.page = paget;
        this.emptyCollection = collectiont;
    }

    @Override // com.google.api.gax.paging.PagedListResponse
    public Iterable<ResourceT> iterateAll() {
        return getPage().iterateAll();
    }

    @Override // com.google.api.gax.paging.PagedListResponse
    public PageT getPage() {
        return this.page;
    }

    @Override // com.google.api.gax.paging.PagedListResponse
    public Iterable<PageT> iteratePages() {
        return (Iterable<PageT>) new Iterable<PageT>() { // from class: com.google.api.gax.paging.AbstractPagedListResponse.1
            @Override // java.lang.Iterable
            public Iterator<PageT> iterator() {
                AbstractPagedListResponse abstractPagedListResponse = AbstractPagedListResponse.this;
                return new AllPagesIterator(abstractPagedListResponse.page);
            }
        };
    }

    @Override // com.google.api.gax.paging.PagedListResponse
    public String getNextPageToken() {
        return getPage().getNextPageToken();
    }

    @Override // com.google.api.gax.paging.PagedListResponse
    public CollectionT expandToFixedSizeCollection(int i) {
        return (CollectionT) this.emptyCollection.createCollection(this.emptyCollection.getPages(this.page, i), i);
    }

    @Override // com.google.api.gax.paging.PagedListResponse
    public Iterable<CollectionT> iterateFixedSizeCollections(int i) {
        final CollectionT expandToFixedSizeCollection = expandToFixedSizeCollection(i);
        return (Iterable<CollectionT>) new Iterable<CollectionT>() { // from class: com.google.api.gax.paging.AbstractPagedListResponse.2
            @Override // java.lang.Iterable
            public Iterator<CollectionT> iterator() {
                return new AllCollectionsIterator(expandToFixedSizeCollection);
            }
        };
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class AllPagesIterator extends AbstractPagedListResponse<RequestT, ResponseT, ResourceT, PageT, CollectionT>.NextIterator<PageT> {
        private AllPagesIterator(PageT paget) {
            super(paget, new Next<PageT>() { // from class: com.google.api.gax.paging.AbstractPagedListResponse.AllPagesIterator.1
                @Override // com.google.api.gax.paging.AbstractPagedListResponse.Next
                public PageT next(PageT paget2) {
                    return (PageT) paget2.getNextPage();
                }
            });
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class AllCollectionsIterator extends AbstractPagedListResponse<RequestT, ResponseT, ResourceT, PageT, CollectionT>.NextIterator<CollectionT> {
        private AllCollectionsIterator(CollectionT collectiont) {
            super(collectiont, new Next<CollectionT>() { // from class: com.google.api.gax.paging.AbstractPagedListResponse.AllCollectionsIterator.1
                @Override // com.google.api.gax.paging.AbstractPagedListResponse.Next
                public CollectionT next(CollectionT collectiont2) {
                    return (CollectionT) collectiont2.getNextCollection();
                }
            });
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class NextIterator<T> extends AbstractIterator<T> {
        private boolean computeFirst;
        private T current;
        private final Next<T> fetcher;

        private NextIterator(T t, Next<T> next) {
            this.computeFirst = true;
            this.current = (T) Preconditions.checkNotNull(t);
            this.fetcher = next;
        }

        @Override // com.google.common.collect.AbstractIterator
        protected T computeNext() {
            if (this.computeFirst) {
                this.computeFirst = false;
                return this.current;
            }
            this.current = this.fetcher.next(this.current);
            T t = this.current;
            return t == null ? endOfData() : t;
        }
    }
}
