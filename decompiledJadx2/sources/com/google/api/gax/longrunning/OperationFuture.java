package com.google.api.gax.longrunning;

import com.google.api.core.ApiFuture;
import com.google.api.core.BetaApi;
import com.google.api.gax.retrying.RetryingFuture;
import java.util.concurrent.ExecutionException;

/* JADX WARN: Classes with same name are omitted:
  
 */
@BetaApi("The surface for long-running operations is not stable yet and may change in the future.")
/* loaded from: classes2.dex */
public interface OperationFuture<ResponseT, MetadataT> extends ApiFuture<ResponseT> {
    ApiFuture<OperationSnapshot> getInitialFuture();

    ApiFuture<MetadataT> getMetadata();

    String getName() throws InterruptedException, ExecutionException;

    RetryingFuture<OperationSnapshot> getPollingFuture();

    ApiFuture<MetadataT> peekMetadata();
}
