package io.grpc.netty.shaded.io.grpc.netty;

import io.grpc.Status;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class GracefulCloseCommand extends WriteQueue.AbstractQueuedCommand {
    private final Status status;

    public GracefulCloseCommand(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return this.status;
    }
}
