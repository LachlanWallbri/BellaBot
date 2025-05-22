package com.google.api.gax.paging;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface Page<ResourceT> {
    Page<ResourceT> getNextPage();

    String getNextPageToken();

    Iterable<ResourceT> getValues();

    boolean hasNextPage();

    Iterable<ResourceT> iterateAll();
}
