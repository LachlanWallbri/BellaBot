package org.jboss.netty.util.internal;

import java.util.Collection;
import java.util.concurrent.BlockingQueue;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
public final class QueueFactory {
    private static final boolean useUnsafe = DetectionUtil.hasUnsafe();
    private static final InternalLogger LOGGER = InternalLoggerFactory.getInstance((Class<?>) QueueFactory.class);

    private QueueFactory() {
    }

    public static <T> BlockingQueue<T> createQueue(Class<T> cls) {
        if (DetectionUtil.javaVersion() >= 7) {
            return new java.util.concurrent.LinkedTransferQueue();
        }
        try {
            if (useUnsafe) {
                return new LinkedTransferQueue();
            }
        } catch (Throwable th) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Unable to instance LinkedTransferQueue, fallback to LegacyLinkedTransferQueue", th);
            }
        }
        return new LegacyLinkedTransferQueue();
    }

    public static <T> BlockingQueue<T> createQueue(Collection<? extends T> collection, Class<T> cls) {
        if (DetectionUtil.javaVersion() >= 7) {
            return new java.util.concurrent.LinkedTransferQueue();
        }
        try {
            if (useUnsafe) {
                return new LinkedTransferQueue(collection);
            }
        } catch (Throwable th) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Unable to instance LinkedTransferQueue, fallback to LegacyLinkedTransferQueue", th);
            }
        }
        return new LegacyLinkedTransferQueue(collection);
    }
}
