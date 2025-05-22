package com.google.api.gax.retrying;

import com.google.api.core.AbstractApiFuture;
import com.google.api.core.InternalApi;

/* JADX WARN: Classes with same name are omitted:
  
 */
@InternalApi
/* loaded from: classes2.dex */
public final class NonCancellableFuture<ResponseT> extends AbstractApiFuture<ResponseT> {
    @Override // com.google.api.core.AbstractApiFuture, java.util.concurrent.Future
    public boolean cancel(boolean z) {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancelPrivately() {
        super.cancel(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean setPrivately(ResponseT responset) {
        return super.set(responset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean setExceptionPrivately(Throwable th) {
        return super.setException(th);
    }
}
