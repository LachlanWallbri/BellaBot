package io.grpc.netty.shaded.io.netty.channel;

import io.grpc.netty.shaded.io.netty.util.internal.ObjectUtil;
import java.util.ArrayDeque;
import java.util.Queue;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ChannelFlushPromiseNotifier {
    private final Queue<FlushCheckpoint> flushCheckpoints;
    private final boolean tryNotify;
    private long writeCounter;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface FlushCheckpoint {
        long flushCheckpoint();

        void flushCheckpoint(long j);

        ChannelPromise promise();
    }

    public ChannelFlushPromiseNotifier(boolean z) {
        this.flushCheckpoints = new ArrayDeque();
        this.tryNotify = z;
    }

    public ChannelFlushPromiseNotifier() {
        this(false);
    }

    @Deprecated
    public ChannelFlushPromiseNotifier add(ChannelPromise channelPromise, int i) {
        return add(channelPromise, i);
    }

    public ChannelFlushPromiseNotifier add(ChannelPromise channelPromise, long j) {
        ObjectUtil.checkNotNull(channelPromise, "promise");
        ObjectUtil.checkPositiveOrZero(j, "pendingDataSize");
        long j2 = this.writeCounter + j;
        if (channelPromise instanceof FlushCheckpoint) {
            FlushCheckpoint flushCheckpoint = (FlushCheckpoint) channelPromise;
            flushCheckpoint.flushCheckpoint(j2);
            this.flushCheckpoints.add(flushCheckpoint);
        } else {
            this.flushCheckpoints.add(new DefaultFlushCheckpoint(j2, channelPromise));
        }
        return this;
    }

    public ChannelFlushPromiseNotifier increaseWriteCounter(long j) {
        ObjectUtil.checkPositiveOrZero(j, "delta");
        this.writeCounter += j;
        return this;
    }

    public long writeCounter() {
        return this.writeCounter;
    }

    public ChannelFlushPromiseNotifier notifyPromises() {
        notifyPromises0(null);
        return this;
    }

    @Deprecated
    public ChannelFlushPromiseNotifier notifyFlushFutures() {
        return notifyPromises();
    }

    public ChannelFlushPromiseNotifier notifyPromises(Throwable th) {
        notifyPromises();
        while (true) {
            FlushCheckpoint poll = this.flushCheckpoints.poll();
            if (poll == null) {
                return this;
            }
            if (this.tryNotify) {
                poll.promise().tryFailure(th);
            } else {
                poll.promise().setFailure(th);
            }
        }
    }

    @Deprecated
    public ChannelFlushPromiseNotifier notifyFlushFutures(Throwable th) {
        return notifyPromises(th);
    }

    public ChannelFlushPromiseNotifier notifyPromises(Throwable th, Throwable th2) {
        notifyPromises0(th);
        while (true) {
            FlushCheckpoint poll = this.flushCheckpoints.poll();
            if (poll == null) {
                return this;
            }
            if (this.tryNotify) {
                poll.promise().tryFailure(th2);
            } else {
                poll.promise().setFailure(th2);
            }
        }
    }

    @Deprecated
    public ChannelFlushPromiseNotifier notifyFlushFutures(Throwable th, Throwable th2) {
        return notifyPromises(th, th2);
    }

    private void notifyPromises0(Throwable th) {
        if (this.flushCheckpoints.isEmpty()) {
            this.writeCounter = 0L;
            return;
        }
        long j = this.writeCounter;
        while (true) {
            FlushCheckpoint peek = this.flushCheckpoints.peek();
            if (peek == null) {
                this.writeCounter = 0L;
                break;
            }
            if (peek.flushCheckpoint() > j) {
                if (j > 0 && this.flushCheckpoints.size() == 1) {
                    this.writeCounter = 0L;
                    peek.flushCheckpoint(peek.flushCheckpoint() - j);
                }
            } else {
                this.flushCheckpoints.remove();
                ChannelPromise promise = peek.promise();
                if (th == null) {
                    if (this.tryNotify) {
                        promise.trySuccess();
                    } else {
                        promise.setSuccess();
                    }
                } else if (this.tryNotify) {
                    promise.tryFailure(th);
                } else {
                    promise.setFailure(th);
                }
            }
        }
        long j2 = this.writeCounter;
        if (j2 >= 549755813888L) {
            this.writeCounter = 0L;
            for (FlushCheckpoint flushCheckpoint : this.flushCheckpoints) {
                flushCheckpoint.flushCheckpoint(flushCheckpoint.flushCheckpoint() - j2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public static class DefaultFlushCheckpoint implements FlushCheckpoint {
        private long checkpoint;
        private final ChannelPromise future;

        DefaultFlushCheckpoint(long j, ChannelPromise channelPromise) {
            this.checkpoint = j;
            this.future = channelPromise;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFlushPromiseNotifier.FlushCheckpoint
        public long flushCheckpoint() {
            return this.checkpoint;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFlushPromiseNotifier.FlushCheckpoint
        public void flushCheckpoint(long j) {
            this.checkpoint = j;
        }

        @Override // io.grpc.netty.shaded.io.netty.channel.ChannelFlushPromiseNotifier.FlushCheckpoint
        public ChannelPromise promise() {
            return this.future;
        }
    }
}
