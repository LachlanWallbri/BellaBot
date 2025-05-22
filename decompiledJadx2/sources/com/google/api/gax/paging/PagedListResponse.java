package com.google.api.gax.paging;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface PagedListResponse<ResourceT> {
    FixedSizeCollection<ResourceT> expandToFixedSizeCollection(int i);

    String getNextPageToken();

    Page<ResourceT> getPage();

    Iterable<ResourceT> iterateAll();

    Iterable<? extends FixedSizeCollection<ResourceT>> iterateFixedSizeCollections(int i);

    Iterable<? extends Page<ResourceT>> iteratePages();
}
