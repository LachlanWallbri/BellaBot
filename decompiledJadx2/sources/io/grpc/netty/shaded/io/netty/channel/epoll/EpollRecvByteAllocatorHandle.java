package io.grpc.netty.shaded.io.netty.channel.epoll;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.unix.PreferredDirectByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class EpollRecvByteAllocatorHandle extends RecvByteBufAllocator.DelegatingHandle implements RecvByteBufAllocator.ExtendedHandle {
    private final UncheckedBooleanSupplier defaultMaybeMoreDataSupplier;
    private boolean isEdgeTriggered;
    private final PreferredDirectByteBufAllocator preferredDirectByteBufAllocator;
    private boolean receivedRdHup;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EpollRecvByteAllocatorHandle(RecvByteBufAllocator.ExtendedHandle extendedHandle) {
        super(extendedHandle);
        this.preferredDirectByteBufAllocator = new PreferredDirectByteBufAllocator();
        this.defaultMaybeMoreDataSupplier = new UncheckedBooleanSupplier() { // from class: io.grpc.netty.shaded.io.netty.channel.epoll.EpollRecvByteAllocatorHandle.1
            @Override // io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier, io.grpc.netty.shaded.io.netty.util.BooleanSupplier
            public boolean get() {
                return EpollRecvByteAllocatorHandle.this.maybeMoreDataToRead();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void receivedRdHup() {
        this.receivedRdHup = true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isReceivedRdHup() {
        return this.receivedRdHup;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean maybeMoreDataToRead() {
        return (this.isEdgeTriggered && lastBytesRead() > 0) || (!this.isEdgeTriggered && lastBytesRead() == attemptedBytesRead());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void edgeTriggered(boolean z) {
        this.isEdgeTriggered = z;
    }

    final boolean isEdgeTriggered() {
        return this.isEdgeTriggered;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.DelegatingHandle, io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
    public final ByteBuf allocate(ByteBufAllocator byteBufAllocator) {
        this.preferredDirectByteBufAllocator.updateAllocator(byteBufAllocator);
        return delegate().allocate(this.preferredDirectByteBufAllocator);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.ExtendedHandle
    public final boolean continueReading(UncheckedBooleanSupplier uncheckedBooleanSupplier) {
        return ((RecvByteBufAllocator.ExtendedHandle) delegate()).continueReading(uncheckedBooleanSupplier);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.DelegatingHandle, io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
    public final boolean continueReading() {
        return continueReading(this.defaultMaybeMoreDataSupplier);
    }
}
