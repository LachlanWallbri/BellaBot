package io.grpc.alts;

import com.google.common.base.Preconditions;
import io.grpc.CallCredentials;
import io.grpc.Status;
import java.util.concurrent.Executor;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class FailingCallCredentials extends CallCredentials {
    private final Status status;

    @Override // io.grpc.CallCredentials
    public void thisUsesUnstableApi() {
    }

    public FailingCallCredentials(Status status) {
        this.status = (Status) Preconditions.checkNotNull(status, "status");
    }

    @Override // io.grpc.CallCredentials
    public void applyRequestMetadata(CallCredentials.RequestInfo requestInfo, Executor executor, CallCredentials.MetadataApplier metadataApplier) {
        metadataApplier.fail(this.status);
    }
}
