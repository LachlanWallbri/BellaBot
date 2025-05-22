package io.grpc.netty.shaded.io.grpc.netty;

import com.google.common.base.Preconditions;
import io.grpc.netty.shaded.io.netty.channel.Channel;
import io.grpc.netty.shaded.io.netty.channel.ChannelFuture;
import io.grpc.netty.shaded.io.netty.channel.ChannelPromise;
import io.perfmark.Link;
import io.perfmark.PerfMark;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public class WriteQueue {
    static final int DEQUE_CHUNK_SIZE = 128;
    private final Channel channel;
    private final Runnable later = new Runnable() { // from class: io.grpc.netty.shaded.io.grpc.netty.WriteQueue.1
        @Override // java.lang.Runnable
        public void run() {
            WriteQueue.this.flush();
        }
    };
    private final AtomicBoolean scheduled = new AtomicBoolean();
    private final Queue<QueuedCommand> queue = new ConcurrentLinkedQueue();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    public interface QueuedCommand {
        Link getLink();

        ChannelPromise promise();

        void promise(ChannelPromise channelPromise);

        void run(Channel channel);
    }

    public WriteQueue(Channel channel) {
        this.channel = (Channel) Preconditions.checkNotNull(channel, "channel");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void scheduleFlush() {
        if (this.scheduled.compareAndSet(false, true)) {
            this.channel.eventLoop().execute(this.later);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelFuture enqueue(QueuedCommand queuedCommand, boolean z) {
        Preconditions.checkArgument(queuedCommand.promise() == null, "promise must not be set on command");
        ChannelPromise newPromise = this.channel.newPromise();
        queuedCommand.promise(newPromise);
        this.queue.add(queuedCommand);
        if (z) {
            scheduleFlush();
        }
        return newPromise;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void enqueue(Runnable runnable, boolean z) {
        this.queue.add(new RunnableCommand(runnable));
        if (z) {
            scheduleFlush();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void drainNow() {
        Preconditions.checkState(this.channel.eventLoop().inEventLoop(), "must be on the event loop");
        if (this.queue.peek() == null) {
            return;
        }
        flush();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flush() {
        PerfMark.startTask("WriteQueue.periodicFlush");
        int i = 0;
        boolean z = false;
        while (true) {
            try {
                QueuedCommand poll = this.queue.poll();
                if (poll == null) {
                    break;
                }
                poll.run(this.channel);
                i++;
                if (i == 128) {
                    PerfMark.startTask("WriteQueue.flush0");
                    try {
                        this.channel.flush();
                        PerfMark.stopTask("WriteQueue.flush0");
                        z = true;
                        i = 0;
                    } catch (Throwable th) {
                        PerfMark.stopTask("WriteQueue.flush0");
                        throw th;
                    }
                }
            } finally {
            }
            PerfMark.stopTask("WriteQueue.periodicFlush");
            this.scheduled.set(false);
            if (!this.queue.isEmpty()) {
                scheduleFlush();
            }
        }
        if (i != 0 || !z) {
            PerfMark.startTask("WriteQueue.flush1");
            try {
                this.channel.flush();
            } finally {
                PerfMark.stopTask("WriteQueue.flush1");
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static class RunnableCommand implements QueuedCommand {
        private final Link link = PerfMark.linkOut();
        private final Runnable runnable;

        public RunnableCommand(Runnable runnable) {
            this.runnable = runnable;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
        public final void promise(ChannelPromise channelPromise) {
            throw new UnsupportedOperationException();
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
        public final ChannelPromise promise() {
            throw new UnsupportedOperationException();
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
        public final void run(Channel channel) {
            this.runnable.run();
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
        public Link getLink() {
            return this.link;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    static abstract class AbstractQueuedCommand implements QueuedCommand {
        private final Link link = PerfMark.linkOut();
        private ChannelPromise promise;

        @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
        public final void promise(ChannelPromise channelPromise) {
            this.promise = channelPromise;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
        public final ChannelPromise promise() {
            return this.promise;
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
        public final void run(Channel channel) {
            channel.write(this, this.promise);
        }

        @Override // io.grpc.netty.shaded.io.grpc.netty.WriteQueue.QueuedCommand
        public Link getLink() {
            return this.link;
        }
    }
}
