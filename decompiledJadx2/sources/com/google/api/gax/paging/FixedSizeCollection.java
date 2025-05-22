package com.google.api.gax.paging;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface FixedSizeCollection<ResourceT> {
    int getCollectionSize();

    FixedSizeCollection<ResourceT> getNextCollection();

    String getNextPageToken();

    Iterable<ResourceT> getValues();

    boolean hasNextCollection();
}
