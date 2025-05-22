package com.google.api.gax.paging;

import com.google.api.client.util.Lists;
import com.google.api.gax.paging.AbstractFixedSizeCollection;
import com.google.api.gax.paging.AbstractPage;
import com.google.api.pathtemplate.ValidationException;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public abstract class AbstractFixedSizeCollection<RequestT, ResponseT, ResourceT, PageT extends AbstractPage<RequestT, ResponseT, ResourceT, PageT>, CollectionT extends AbstractFixedSizeCollection<RequestT, ResponseT, ResourceT, PageT, CollectionT>> implements FixedSizeCollection<ResourceT> {
    private final int collectionSize;
    private final List<PageT> pageList;

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract CollectionT createCollection(List<PageT> list, int i);

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFixedSizeCollection(List<PageT> list, int i) {
        this.pageList = list;
        this.collectionSize = i;
    }

    @Override // com.google.api.gax.paging.FixedSizeCollection
    public Iterable<ResourceT> getValues() {
        return new Iterable<ResourceT>() { // from class: com.google.api.gax.paging.AbstractFixedSizeCollection.1
            @Override // java.lang.Iterable
            public Iterator<ResourceT> iterator() {
                return new CollectionResourcesIterator();
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.api.gax.paging.FixedSizeCollection
    public CollectionT getNextCollection() {
        if (hasNextCollection()) {
            return (CollectionT) createCollection(getPages(getLastPage().getNextPage(), this.collectionSize), this.collectionSize);
        }
        return null;
    }

    @Override // com.google.api.gax.paging.FixedSizeCollection
    public boolean hasNextCollection() {
        return getLastPage().hasNextPage();
    }

    @Override // com.google.api.gax.paging.FixedSizeCollection
    public String getNextPageToken() {
        return getLastPage().getNextPageToken();
    }

    @Override // com.google.api.gax.paging.FixedSizeCollection
    public int getCollectionSize() {
        Iterator<PageT> it = this.pageList.iterator();
        int i = 0;
        while (it.hasNext()) {
            i += it.next().getPageElementCount();
        }
        return i;
    }

    private PageT getLastPage() {
        return this.pageList.get(r0.size() - 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public List<PageT> getPages(PageT paget, int i) {
        Preconditions.checkNotNull(paget);
        Preconditions.checkState(i > 0);
        Integer extractPageSize = paget.getContext().getPageDescriptor().extractPageSize(paget.getRequest());
        if (extractPageSize == null) {
            throw new ValidationException("Error while expanding Page to FixedSizeCollection: No pageSize parameter found. The pageSize parameter must be set on the request object, and must be less than the collectionSize parameter, in order to create a FixedSizeCollection object.", new Object[0]);
        }
        if (extractPageSize.intValue() > i) {
            throw new ValidationException("Error while expanding Page to FixedSizeCollection: collectionSize parameter is less than the pageSize optional argument specified on the request object. collectionSize: " + i + ", pageSize: " + extractPageSize, new Object[0]);
        }
        if (paget.getPageElementCount() > i) {
            throw new ValidationException("Cannot construct a FixedSizeCollection with collectionSize less than the number of elements in the first page", new Object[0]);
        }
        int pageElementCount = i - paget.getPageElementCount();
        ArrayList newArrayList = Lists.newArrayList();
        newArrayList.add(paget);
        while (pageElementCount > 0) {
            paget = (PageT) paget.getNextPage(pageElementCount);
            if (paget == null) {
                break;
            }
            int pageElementCount2 = paget.getPageElementCount();
            if (pageElementCount2 > pageElementCount) {
                throw new ValidationException("API returned a number of elements exceeding the specified page_size limit. page_size: " + i + ", elements received: " + pageElementCount2, new Object[0]);
            }
            pageElementCount -= pageElementCount2;
            newArrayList.add(paget);
        }
        return newArrayList;
    }

    /* JADX WARN: Classes with same name are omitted:
      
     */
    /* loaded from: classes2.dex */
    private class CollectionResourcesIterator extends AbstractIterator<ResourceT> {
        private final Iterator<PageT> pageIterator;
        private Iterator<ResourceT> resourceIterator;

        private CollectionResourcesIterator() {
            this.pageIterator = AbstractFixedSizeCollection.this.pageList.iterator();
            this.resourceIterator = this.pageIterator.next().getValues().iterator();
        }

        @Override // com.google.common.collect.AbstractIterator
        protected ResourceT computeNext() {
            while (!this.resourceIterator.hasNext()) {
                if (this.pageIterator.hasNext()) {
                    this.resourceIterator = this.pageIterator.next().getValues().iterator();
                } else {
                    return endOfData();
                }
            }
            return this.resourceIterator.next();
        }
    }
}
