package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.Status;
import io.grpc.netty.shaded.io.grpc.netty.NettyClientStream;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
class CancelClientStreamCommand extends WriteQueue.AbstractQueuedCommand {

    @Nullable
    private final Status reason;
    private final NettyClientStream.TransportState stream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CancelClientStreamCommand(NettyClientStream.TransportState transportState, Status status) {
        this.stream = (NettyClientStream.TransportState) Preconditions.checkNotNull(transportState, "stream");
        Preconditions.checkArgument(status == null || !status.isOk(), "Should not cancel with OK status");
        this.reason = status;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyClientStream.TransportState stream() {
        return this.stream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public Status reason() {
        return this.reason;
    }
}
