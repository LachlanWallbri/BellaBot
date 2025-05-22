package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.buffer.ByteBuf;
import io.grpc.netty.shaded.io.netty.buffer.ByteBufAllocator;
import io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator;
import io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier;
import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.AbstractMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class DefaultMaxBytesRecvByteBufAllocator implements MaxBytesRecvByteBufAllocator {
    private volatile int maxBytesPerIndividualRead;
    private volatile int maxBytesPerRead;

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private final class HandleImpl implements RecvByteBufAllocator.ExtendedHandle {
        private int attemptBytesRead;
        private int bytesToRead;
        private final UncheckedBooleanSupplier defaultMaybeMoreSupplier;
        private int individualReadMax;
        private int lastBytesRead;

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void incMessagesRead(int i) {
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void readComplete() {
        }

        private HandleImpl() {
            this.defaultMaybeMoreSupplier = new UncheckedBooleanSupplier() { // from class: io.grpc.netty.shaded.io.netty.channel.DefaultMaxBytesRecvByteBufAllocator.HandleImpl.1
                @Override // io.grpc.netty.shaded.io.netty.util.UncheckedBooleanSupplier, io.grpc.netty.shaded.io.netty.util.BooleanSupplier
                public boolean get() {
                    return HandleImpl.this.attemptBytesRead == HandleImpl.this.lastBytesRead;
                }
            };
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public ByteBuf allocate(ByteBufAllocator byteBufAllocator) {
            return byteBufAllocator.ioBuffer(guess());
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public int guess() {
            return Math.min(this.individualReadMax, this.bytesToRead);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void reset(ChannelConfig channelConfig) {
            this.bytesToRead = DefaultMaxBytesRecvByteBufAllocator.this.maxBytesPerRead();
            this.individualReadMax = DefaultMaxBytesRecvByteBufAllocator.this.maxBytesPerIndividualRead();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void lastBytesRead(int i) {
            this.lastBytesRead = i;
            this.bytesToRead -= i;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public int lastBytesRead() {
            return this.lastBytesRead;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public boolean continueReading() {
            return continueReading(this.defaultMaybeMoreSupplier);
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.ExtendedHandle
        public boolean continueReading(UncheckedBooleanSupplier uncheckedBooleanSupplier) {
            return this.bytesToRead > 0 && uncheckedBooleanSupplier.get();
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public void attemptedBytesRead(int i) {
            this.attemptBytesRead = i;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator.Handle
        public int attemptedBytesRead() {
            return this.attemptBytesRead;
        }
    }

    public DefaultMaxBytesRecvByteBufAllocator() {
        this(65536, 65536);
    }

    public DefaultMaxBytesRecvByteBufAllocator(int i, int i2) {
        checkMaxBytesPerReadPair(i, i2);
        this.maxBytesPerRead = i;
        this.maxBytesPerIndividualRead = i2;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.RecvByteBufAllocator
    public RecvByteBufAllocator.Handle newHandle() {
        return new HandleImpl();
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.MaxBytesRecvByteBufAllocator
    public int maxBytesPerRead() {
        return this.maxBytesPerRead;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.MaxBytesRecvByteBufAllocator
    public DefaultMaxBytesRecvByteBufAllocator maxBytesPerRead(int i) {
        ObjectUtil.checkPositive(i, "maxBytesPerRead");
        synchronized (this) {
            int maxBytesPerIndividualRead = maxBytesPerIndividualRead();
            if (i < maxBytesPerIndividualRead) {
                throw new IllegalArgumentException("maxBytesPerRead cannot be less than maxBytesPerIndividualRead (" + maxBytesPerIndividualRead + "): " + i);
            }
            this.maxBytesPerRead = i;
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.MaxBytesRecvByteBufAllocator
    public int maxBytesPerIndividualRead() {
        return this.maxBytesPerIndividualRead;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.MaxBytesRecvByteBufAllocator
    public DefaultMaxBytesRecvByteBufAllocator maxBytesPerIndividualRead(int i) {
        ObjectUtil.checkPositive(i, "maxBytesPerIndividualRead");
        synchronized (this) {
            int maxBytesPerRead = maxBytesPerRead();
            if (i > maxBytesPerRead) {
                throw new IllegalArgumentException("maxBytesPerIndividualRead cannot be greater than maxBytesPerRead (" + maxBytesPerRead + "): " + i);
            }
            this.maxBytesPerIndividualRead = i;
        }
        return this;
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.MaxBytesRecvByteBufAllocator
    public synchronized Map.Entry<Integer, Integer> maxBytesPerReadPair() {
        return new AbstractMap.SimpleEntry(Integer.valueOf(this.maxBytesPerRead), Integer.valueOf(this.maxBytesPerIndividualRead));
    }

    private static void checkMaxBytesPerReadPair(int i, int i2) {
        ObjectUtil.checkPositive(i, "maxBytesPerRead");
        ObjectUtil.checkPositive(i2, "maxBytesPerIndividualRead");
        if (i >= i2) {
            return;
        }
        throw new IllegalArgumentException("maxBytesPerRead cannot be less than maxBytesPerIndividualRead (" + i2 + "): " + i);
    }

    @Override // io.grpc.netty.shaded.io.netty.channel.MaxBytesRecvByteBufAllocator
    public DefaultMaxBytesRecvByteBufAllocator maxBytesPerReadPair(int i, int i2) {
        checkMaxBytesPerReadPair(i, i2);
        synchronized (this) {
            this.maxBytesPerRead = i;
            this.maxBytesPerIndividualRead = i2;
        }
        return this;
    }
}
