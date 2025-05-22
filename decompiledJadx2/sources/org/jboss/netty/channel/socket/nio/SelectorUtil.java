package org.jboss.netty.channel.socket.nio;

import java.io.IOException;
import java.nio.channels.CancelledKeyException;
import java.nio.channels.Selector;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;

/* loaded from: classes7.dex */
final class SelectorUtil {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SelectorUtil.class);
    static final int DEFAULT_IO_THREADS = Runtime.getRuntime().availableProcessors() * 2;

    static {
        try {
            if (System.getProperty("sun.nio.ch.bugLevel") == null) {
                System.setProperty("sun.nio.ch.bugLevel", "");
            }
        } catch (SecurityException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Unable to get/set System Property 'sun.nio.ch.bugLevel'", e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void select(Selector selector) throws IOException {
        try {
            selector.select(10L);
        } catch (CancelledKeyException e) {
            if (logger.isDebugEnabled()) {
                logger.debug(CancelledKeyException.class.getSimpleName() + " raised by a Selector - JDK bug?", e);
            }
        }
    }

    private SelectorUtil() {
    }
}
