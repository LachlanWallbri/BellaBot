package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import io.grpc.Status;
import io.grpc.netty.shaded.io.grpc.netty.NettyServerStream;
import io.grpc.netty.shaded.io.grpc.netty.WriteQueue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class CancelServerStreamCommand extends WriteQueue.AbstractQueuedCommand {
    private final Status reason;
    private final NettyServerStream.TransportState stream;

    /* JADX INFO: Access modifiers changed from: package-private */
    public CancelServerStreamCommand(NettyServerStream.TransportState transportState, Status status) {
        this.stream = (NettyServerStream.TransportState) Preconditions.checkNotNull(transportState, "stream");
        this.reason = (Status) Preconditions.checkNotNull(status, "reason");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public NettyServerStream.TransportState stream() {
        return this.stream;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Status reason() {
        return this.reason;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        CancelServerStreamCommand cancelServerStreamCommand = (CancelServerStreamCommand) obj;
        return Objects.equal(this.stream, cancelServerStreamCommand.stream) && Objects.equal(this.reason, cancelServerStreamCommand.reason);
    }

    public int hashCode() {
        return Objects.hashCode(this.stream, this.reason);
    }

    public String toString() {
        return MoreObjects.toStringHelper(this).add("stream", this.stream).add("reason", this.reason).toString();
    }
}
