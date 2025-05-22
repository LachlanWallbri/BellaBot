package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues;

import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MessagePassingQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.PortableJvmInfo;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class MessagePassingQueueUtil {
    public static <E> int drain(MessagePassingQueue<E> messagePassingQueue, MessagePassingQueue.Consumer<E> consumer, int i) {
        if (consumer == null) {
            throw new IllegalArgumentException("c is null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("limit is negative: " + i);
        }
        int i2 = 0;
        if (i == 0) {
            return 0;
        }
        while (i2 < i) {
            E relaxedPoll = messagePassingQueue.relaxedPoll();
            if (relaxedPoll == null) {
                break;
            }
            consumer.accept(relaxedPoll);
            i2++;
        }
        return i2;
    }

    public static <E> int drain(MessagePassingQueue<E> messagePassingQueue, MessagePassingQueue.Consumer<E> consumer) {
        if (consumer == null) {
            throw new IllegalArgumentException("c is null");
        }
        int i = 0;
        while (true) {
            E relaxedPoll = messagePassingQueue.relaxedPoll();
            if (relaxedPoll == null) {
                return i;
            }
            i++;
            consumer.accept(relaxedPoll);
        }
    }

    public static <E> void drain(MessagePassingQueue<E> messagePassingQueue, MessagePassingQueue.Consumer<E> consumer, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        E relaxedPoll;
        if (consumer == null) {
            throw new IllegalArgumentException("c is null");
        }
        if (waitStrategy == null) {
            throw new IllegalArgumentException("wait is null");
        }
        if (exitCondition == null) {
            throw new IllegalArgumentException("exit condition is null");
        }
        while (true) {
            int i = 0;
            while (exitCondition.keepRunning()) {
                relaxedPoll = messagePassingQueue.relaxedPoll();
                if (relaxedPoll == null) {
                    i = waitStrategy.idle(i);
                }
            }
            return;
            consumer.accept(relaxedPoll);
        }
    }

    public static <E> void fill(MessagePassingQueue<E> messagePassingQueue, MessagePassingQueue.Supplier<E> supplier, MessagePassingQueue.WaitStrategy waitStrategy, MessagePassingQueue.ExitCondition exitCondition) {
        if (waitStrategy == null) {
            throw new IllegalArgumentException("waiter is null");
        }
        if (exitCondition == null) {
            throw new IllegalArgumentException("exit condition is null");
        }
        while (true) {
            int i = 0;
            while (exitCondition.keepRunning()) {
                if (messagePassingQueue.fill(supplier, PortableJvmInfo.RECOMENDED_OFFER_BATCH) == 0) {
                    i = waitStrategy.idle(i);
                }
            }
            return;
        }
    }

    public static <E> int fillBounded(MessagePassingQueue<E> messagePassingQueue, MessagePassingQueue.Supplier<E> supplier) {
        return fillInBatchesToLimit(messagePassingQueue, supplier, PortableJvmInfo.RECOMENDED_OFFER_BATCH, messagePassingQueue.capacity());
    }

    public static <E> int fillInBatchesToLimit(MessagePassingQueue<E> messagePassingQueue, MessagePassingQueue.Supplier<E> supplier, int i, int i2) {
        long j = 0;
        do {
            int fill = messagePassingQueue.fill(supplier, i);
            if (fill == 0) {
                return (int) j;
            }
            j += fill;
        } while (j <= i2);
        return (int) j;
    }

    public static <E> int fillUnbounded(MessagePassingQueue<E> messagePassingQueue, MessagePassingQueue.Supplier<E> supplier) {
        return fillInBatchesToLimit(messagePassingQueue, supplier, PortableJvmInfo.RECOMENDED_OFFER_BATCH, 4096);
    }
}
