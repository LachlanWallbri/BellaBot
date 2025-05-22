package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.util.UncheckedBooleanSupplier;
import io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* loaded from: classes.dex */
class EpollRecvByteAllocatorHandle implements RecvByteBufAllocator.ExtendedHandle {
    private final UncheckedBooleanSupplier defaultMaybeMoreDataSupplier = new UncheckedBooleanSupplier() { // from class: io.netty.channel.epoll.EpollRecvByteAllocatorHandle.1
        @Override // io.netty.util.UncheckedBooleanSupplier, io.netty.util.BooleanSupplier
        public boolean get() {
            return EpollRecvByteAllocatorHandle.this.maybeMoreDataToRead();
        }
    };
    private final RecvByteBufAllocator.ExtendedHandle delegate;
    private boolean isEdgeTriggered;
    private boolean receivedRdHup;

    /* JADX INFO: Access modifiers changed from: package-private */
    public EpollRecvByteAllocatorHandle(RecvByteBufAllocator.ExtendedHandle extendedHandle) {
        this.delegate = (RecvByteBufAllocator.ExtendedHandle) ObjectUtil.checkNotNull(extendedHandle, "handle");
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
        return (this.isEdgeTriggered && lastBytesRead() > 0) || (!this.isEdgeTriggered && lastBytesRead() == attemptedBytesRead()) || this.receivedRdHup;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void edgeTriggered(boolean z) {
        this.isEdgeTriggered = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean isEdgeTriggered() {
        return this.isEdgeTriggered;
    }

    @Override // io.netty.channel.RecvByteBufAllocator.Handle
    public final ByteBuf allocate(ByteBufAllocator byteBufAllocator) {
        return this.delegate.allocate(byteBufAllocator);
    }

    @Override // io.netty.channel.RecvByteBufAllocator.Handle
    public final int guess() {
        return this.delegate.guess();
    }

    @Override // io.netty.channel.RecvByteBufAllocator.Handle
    public final void reset(ChannelConfig channelConfig) {
        this.delegate.reset(channelConfig);
    }

    @Override // io.netty.channel.RecvByteBufAllocator.Handle
    public final void incMessagesRead(int i) {
        this.delegate.incMessagesRead(i);
    }

    @Override // io.netty.channel.RecvByteBufAllocator.Handle
    public final void lastBytesRead(int i) {
        this.delegate.lastBytesRead(i);
    }

    @Override // io.netty.channel.RecvByteBufAllocator.Handle
    public final int lastBytesRead() {
        return this.delegate.lastBytesRead();
    }

    @Override // io.netty.channel.RecvByteBufAllocator.Handle
    public final int attemptedBytesRead() {
        return this.delegate.attemptedBytesRead();
    }

    @Override // io.netty.channel.RecvByteBufAllocator.Handle
    public final void attemptedBytesRead(int i) {
        this.delegate.attemptedBytesRead(i);
    }

    @Override // io.netty.channel.RecvByteBufAllocator.Handle
    public final void readComplete() {
        this.delegate.readComplete();
    }

    @Override // io.netty.channel.RecvByteBufAllocator.ExtendedHandle
    public final boolean continueReading(UncheckedBooleanSupplier uncheckedBooleanSupplier) {
        return this.delegate.continueReading(uncheckedBooleanSupplier);
    }

    @Override // io.netty.channel.RecvByteBufAllocator.Handle
    public final boolean continueReading() {
        return this.delegate.continueReading(this.defaultMaybeMoreDataSupplier);
    }
}
