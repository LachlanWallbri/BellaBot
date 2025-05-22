package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public abstract class DefaultMaxMessagesRecvByteBufAllocator implements MaxMessagesRecvByteBufAllocator {
    private volatile int maxMessagesPerRead;
    private volatile boolean respectMaybeMoreData;

    public DefaultMaxMessagesRecvByteBufAllocator() {
        this(1);
    }

    public DefaultMaxMessagesRecvByteBufAllocator(int i) {
        this.respectMaybeMoreData = true;
        maxMessagesPerRead(i);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.MaxMessagesRecvByteBufAllocator
    public int maxMessagesPerRead() {
        return this.maxMessagesPerRead;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.MaxMessagesRecvByteBufAllocator
    public MaxMessagesRecvByteBufAllocator maxMessagesPerRead(int i) {
        ObjectUtil.checkPositive(i, "maxMessagesPerRead");
        this.maxMessagesPerRead = i;
        return this;
    }

    public DefaultMaxMessagesRecvByteBufAllocator respectMaybeMoreData(boolean z) {
        this.respectMaybeMoreData = z;
        return this;
    }

    public final boolean respectMaybeMoreData() {
        return this.respectMaybeMoreData;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public abstract class MaxMessageHandle implements RecvByteBufAllocator.ExtendedHandle {
        private int attemptedBytesRead;
        private ChannelConfig config;
        private final UncheckedBooleanSupplier defaultMaybeMoreSupplier = new UncheckedBooleanSupplier() { // from class: io.grpc.netty.shaded.io.netty.channel.DefaultMaxMessagesRecvByteBufAllocator.MaxMessageHandle.1
            @Override // io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier, io.grpc.netty.shaded.io.netty.util.BooleanSupplier
            public boolean get() {
                return MaxMessageHandle.this.attemptedBytesRead == MaxMessageHandle.this.lastBytesRead;
            }
        };
        private int lastBytesRead;
        private int maxMessagePerRead;
        private final boolean respectMaybeMoreData;
        private int totalBytesRead;
        private int totalMessages;

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void readComplete() {
        }

        public MaxMessageHandle() {
            this.respectMaybeMoreData = DefaultMaxMessagesRecvByteBufAllocator.this.respectMaybeMoreData;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void reset(ChannelConfig channelConfig) {
            this.config = channelConfig;
            this.maxMessagePerRead = DefaultMaxMessagesRecvByteBufAllocator.this.maxMessagesPerRead();
            this.totalBytesRead = 0;
            this.totalMessages = 0;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public ByteBuf allocate(ByteBufAllocator byteBufAllocator) {
            return byteBufAllocator.ioBuffer(guess());
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public final void incMessagesRead(int i) {
            this.totalMessages += i;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void lastBytesRead(int i) {
            this.lastBytesRead = i;
            if (i > 0) {
                this.totalBytesRead += i;
            }
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public final int lastBytesRead() {
            return this.lastBytesRead;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public boolean continueReading() {
            return continueReading(this.defaultMaybeMoreSupplier);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.ExtendedHandle
        public boolean continueReading(UncheckedBooleanSupplier uncheckedBooleanSupplier) {
            return this.config.isAutoRead() && (!this.respectMaybeMoreData || uncheckedBooleanSupplier.get()) && this.totalMessages < this.maxMessagePerRead && this.totalBytesRead > 0;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public int attemptedBytesRead() {
            return this.attemptedBytesRead;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void attemptedBytesRead(int i) {
            this.attemptedBytesRead = i;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public final int totalBytesRead() {
            int i = this.totalBytesRead;
            if (i < 0) {
                return Integer.MAX_VALUE;
            }
            return i;
        }
    }
}
