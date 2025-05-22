package com.google.api.gax.paging;

import com.google.api.core.BetaApi;
import java.util.Collections;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi
/* loaded from: classes2.dex */
public class Pages {
    private Pages() {
    }

    public static <ResourceT> Page<ResourceT> empty() {
        return new Page<ResourceT>() { // from class: com.google.api.gax.paging.Pages.1
            @Override // com.google.api.gax.paging.Page
            public Page<ResourceT> getNextPage() {
                return null;
            }

            @Override // com.google.api.gax.paging.Page
            public String getNextPageToken() {
                return "";
            }

            @Override // com.google.api.gax.paging.Page
            public boolean hasNextPage() {
                return false;
            }

            @Override // com.google.api.gax.paging.Page
            public Iterable<ResourceT> iterateAll() {
                return Collections.emptyList();
            }

            @Override // com.google.api.gax.paging.Page
            public Iterable<ResourceT> getValues() {
                return Collections.emptyList();
            }
        };
    }
}
