package com.google.api.gax.batching;

import com.google.api.core.BetaApi;
import com.google.api.core.InternalApi;
import com.google.api.core.SettableApiFuture;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi("For google-cloud-java client use only.")
@BetaApi("The surface for batching is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public abstract class BatchEntry<ElementT, ElementResultT> {
    @Nullable
    public abstract ElementT getElement();

    public abstract SettableApiFuture<ElementResultT> getResultFuture();

    public static <ElementT, ElementResultT> BatchEntry<ElementT, ElementResultT> create(@Nullable ElementT elementt, SettableApiFuture<ElementResultT> settableApiFuture) {
        return new AutoValue_BatchEntry(elementt, settableApiFuture);
    }
}
