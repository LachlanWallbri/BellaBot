package io.grpc.internal;

import com.google.common.base.Preconditions;
import io.grpc.Metadata;
import io.grpc.Status;
import io.grpc.internal.ClientStreamListener;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class FailingClientStream extends NoopClientStream {
    private final Status error;
    private final ClientStreamListener.RpcProgress rpcProgress;
    private boolean started;

    public FailingClientStream(Status status) {
        this(status, ClientStreamListener.RpcProgress.PROCESSED);
    }

    public FailingClientStream(Status status, ClientStreamListener.RpcProgress rpcProgress) {
        Preconditions.checkArgument(!status.isOk(), "error must not be OK");
        this.error = status;
        this.rpcProgress = rpcProgress;
    }

    @Override // io.grpc.internal.NoopClientStream, io.grpc.internal.ClientStream
    public void start(ClientStreamListener clientStreamListener) {
        Preconditions.checkState(!this.started, "already started");
        this.started = true;
        clientStreamListener.closed(this.error, this.rpcProgress, new Metadata());
    }

    Status getError() {
        return this.error;
    }

    @Override // io.grpc.internal.NoopClientStream, io.grpc.internal.ClientStream
    public void appendTimeoutInsight(InsightBuilder insightBuilder) {
        insightBuilder.appendKeyValue("error", this.error).appendKeyValue("progress", this.rpcProgress);
    }
}
