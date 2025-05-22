package com.google.api.gax.paging;

import com.google.api.core.ApiFuture;

/* JADX WARN: Classes with same name are omitted:
  
 */
/* loaded from: classes2.dex */
public interface AsyncPage<ResourceT> extends Page<ResourceT> {
    ApiFuture<? extends AsyncPage<ResourceT>> getNextPageAsync();
}
