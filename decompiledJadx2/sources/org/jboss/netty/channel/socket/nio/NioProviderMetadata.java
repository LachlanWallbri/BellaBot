package org.jboss.netty.channel.socket.nio;

import com.iflytek.aiui.constant.InternalConstant;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jboss.netty.logging.InternalLogger;
import org.jboss.netty.logging.InternalLoggerFactory;
import org.jboss.netty.util.internal.SystemPropertyUtil;

/* loaded from: classes7.dex */
final class NioProviderMetadata {
    static final int CONSTRAINT_LEVEL;
    private static final String CONSTRAINT_LEVEL_PROPERTY = "org.jboss.netty.channel.socket.nio.constraintLevel";
    private static final String OLD_CONSTRAINT_LEVEL_PROPERTY = "java.nio.channels.spi.constraintLevel";
    static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NioProviderMetadata.class);

    static {
        int i;
        int i2 = SystemPropertyUtil.get(CONSTRAINT_LEVEL_PROPERTY, -1);
        if (i2 < 0 || i2 > 2) {
            i2 = SystemPropertyUtil.get(OLD_CONSTRAINT_LEVEL_PROPERTY, -1);
            if (i2 < 0 || i2 > 2) {
                i2 = -1;
            } else {
                logger.warn("System property 'java.nio.channels.spi.constraintLevel' has been deprecated.  Use 'org.jboss.netty.channel.socket.nio.constraintLevel' instead.");
            }
        }
        if (i2 >= 0) {
            logger.debug("Setting the NIO constraint level to: " + i2);
        }
        if (i2 < 0) {
            i = detectConstraintLevelFromSystemProperties();
            if (i < 0) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Couldn't determine the NIO constraint level from the system properties; using the safest level (2)");
                }
                i = 2;
            } else if (i != 0) {
                if (logger.isInfoEnabled()) {
                    logger.info("Using the autodetected NIO constraint level: " + i + " (Use better NIO provider for better performance)");
                }
            } else if (logger.isDebugEnabled()) {
                logger.debug("Using the autodetected NIO constraint level: " + i);
            }
        } else {
            i = i2;
        }
        CONSTRAINT_LEVEL = i;
        int i3 = CONSTRAINT_LEVEL;
        if (i3 < 0 || i3 > 2) {
            throw new Error("Unexpected NIO constraint level: " + CONSTRAINT_LEVEL + ", please report this error.");
        }
    }

    private static int detectConstraintLevelFromSystemProperties() {
        String str;
        String str2 = SystemPropertyUtil.get("java.specification.version");
        String str3 = SystemPropertyUtil.get("java.vm.info", "");
        String str4 = SystemPropertyUtil.get("os.name");
        String str5 = SystemPropertyUtil.get("java.vm.vendor");
        try {
            str = SelectorProvider.provider().getClass().getName();
        } catch (Exception unused) {
            str = null;
        }
        if (str2 != null && str4 != null && str5 != null && str != null) {
            String lowerCase = str4.toLowerCase();
            String lowerCase2 = str5.toLowerCase();
            if (lowerCase2.indexOf("sun") >= 0) {
                if (lowerCase.indexOf("linux") >= 0) {
                    if (str.equals("sun.nio.ch.EPollSelectorProvider") || str.equals("sun.nio.ch.PollSelectorProvider")) {
                        return 0;
                    }
                } else if (lowerCase.indexOf("windows") >= 0) {
                    if (str.equals("sun.nio.ch.WindowsSelectorProvider")) {
                        return 0;
                    }
                } else if ((lowerCase.indexOf("sun") >= 0 || lowerCase.indexOf("solaris") >= 0) && str.equals("sun.nio.ch.DevPollSelectorProvider")) {
                    return 0;
                }
            } else if (lowerCase2.indexOf("apple") >= 0) {
                if (lowerCase.indexOf("mac") >= 0 && lowerCase.indexOf(InternalConstant.KEY_OS) >= 0 && str.equals("sun.nio.ch.KQueueSelectorProvider")) {
                    return 0;
                }
            } else if (lowerCase2.indexOf("ibm") >= 0) {
                if (lowerCase.indexOf("linux") >= 0 || lowerCase.indexOf("aix") >= 0) {
                    if (str2.equals("1.5") || str2.matches("^1\\.5\\D.*$")) {
                        if (str.equals("sun.nio.ch.PollSelectorProvider")) {
                            return 1;
                        }
                    } else if (str2.equals("1.6") || str2.matches("^1\\.6\\D.*$")) {
                        Matcher matcher = Pattern.compile("(?:^|[^0-9])([2-9][0-9]{3}(?:0[1-9]|1[0-2])(?:0[1-9]|[12][0-9]|3[01]))(?:$|[^0-9])").matcher(str3);
                        if (matcher.find()) {
                            if (Long.parseLong(matcher.group(1)) < 20081105) {
                                return 2;
                            }
                            if (str.equals("sun.nio.ch.EPollSelectorProvider")) {
                                return 0;
                            }
                            if (str.equals("sun.nio.ch.PollSelectorProvider")) {
                                return 1;
                            }
                        }
                    }
                }
            } else if (lowerCase2.indexOf("bea") >= 0 || lowerCase2.indexOf("oracle") >= 0) {
                if (lowerCase.indexOf("linux") >= 0) {
                    if (str.equals("sun.nio.ch.EPollSelectorProvider") || str.equals("sun.nio.ch.PollSelectorProvider")) {
                        return 0;
                    }
                } else if (lowerCase.indexOf("windows") >= 0 && str.equals("sun.nio.ch.WindowsSelectorProvider")) {
                    return 0;
                }
            } else if (lowerCase2.indexOf("apache") >= 0 && str.equals("org.apache.harmony.nio.internal.SelectorProviderImpl")) {
                return 1;
            }
        }
        return -1;
    }

    private static int autodetect() {
        ServerSocketChannel serverSocketChannel;
        SelectorLoop selectorLoop;
        long j;
        boolean z;
        boolean z2;
        int i;
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        try {
            serverSocketChannel = ServerSocketChannel.open();
            try {
                try {
                    serverSocketChannel.socket().bind(new InetSocketAddress(0));
                    serverSocketChannel.configureBlocking(false);
                } catch (Throwable unused) {
                    selectorLoop = null;
                }
                try {
                    selectorLoop = new SelectorLoop();
                    try {
                        try {
                            serverSocketChannel.register(selectorLoop.selector, 0);
                            SelectionKey keyFor = serverSocketChannel.keyFor(selectorLoop.selector);
                            newCachedThreadPool.execute(selectorLoop);
                            int i2 = 0;
                            while (true) {
                                j = 50;
                                if (i2 >= 10) {
                                    z = true;
                                    break;
                                }
                                while (true) {
                                    if (selectorLoop.selecting) {
                                        try {
                                            Thread.sleep(50L);
                                        } catch (InterruptedException unused2) {
                                        }
                                        if (selectorLoop.selecting) {
                                            break;
                                        }
                                    } else {
                                        Thread.yield();
                                    }
                                }
                                long nanoTime = System.nanoTime();
                                keyFor.interestOps(keyFor.interestOps() | 16);
                                keyFor.interestOps(keyFor.interestOps() & (-17));
                                if (System.nanoTime() - nanoTime >= 500000000) {
                                    z = false;
                                    break;
                                }
                                i2++;
                            }
                            if (z) {
                                i = 0;
                            } else {
                                int i3 = 0;
                                while (true) {
                                    if (i3 >= 10) {
                                        z2 = true;
                                        break;
                                    }
                                    while (true) {
                                        if (selectorLoop.selecting) {
                                            try {
                                                Thread.sleep(j);
                                            } catch (InterruptedException unused3) {
                                            }
                                            if (selectorLoop.selecting) {
                                                break;
                                            }
                                        } else {
                                            Thread.yield();
                                        }
                                    }
                                    long nanoTime2 = System.nanoTime();
                                    int interestOps = keyFor.interestOps();
                                    synchronized (selectorLoop) {
                                        selectorLoop.selector.wakeup();
                                        keyFor.interestOps(interestOps | 16);
                                        keyFor.interestOps(interestOps & (-17));
                                    }
                                    if (System.nanoTime() - nanoTime2 >= 500000000) {
                                        z2 = false;
                                        break;
                                    }
                                    i3++;
                                    j = 50;
                                }
                                i = z2 ? 1 : 2;
                            }
                            if (serverSocketChannel != null) {
                                try {
                                    serverSocketChannel.close();
                                } catch (Throwable th) {
                                    if (logger.isWarnEnabled()) {
                                        logger.warn("Failed to close a temporary socket.", th);
                                    }
                                }
                            }
                            selectorLoop.done = true;
                            try {
                                newCachedThreadPool.shutdownNow();
                            } catch (InterruptedException | NullPointerException unused4) {
                            }
                            do {
                                try {
                                    selectorLoop.selector.wakeup();
                                } catch (Throwable unused5) {
                                }
                            } while (!newCachedThreadPool.awaitTermination(1L, TimeUnit.SECONDS));
                            try {
                                selectorLoop.selector.close();
                            } catch (Throwable th2) {
                                if (logger.isWarnEnabled()) {
                                    logger.warn("Failed to close a temporary selector.", th2);
                                }
                            }
                            return i;
                        } catch (Throwable th3) {
                            if (logger.isWarnEnabled()) {
                                logger.warn("Failed to register a temporary selector.", th3);
                            }
                            if (serverSocketChannel != null) {
                                try {
                                    serverSocketChannel.close();
                                } catch (Throwable th4) {
                                    if (logger.isWarnEnabled()) {
                                        logger.warn("Failed to close a temporary socket.", th4);
                                    }
                                }
                            }
                            selectorLoop.done = true;
                            try {
                                newCachedThreadPool.shutdownNow();
                            } catch (InterruptedException | NullPointerException unused6) {
                            }
                            do {
                                try {
                                    selectorLoop.selector.wakeup();
                                } catch (Throwable unused7) {
                                }
                            } while (!newCachedThreadPool.awaitTermination(1L, TimeUnit.SECONDS));
                            try {
                                selectorLoop.selector.close();
                            } catch (Throwable th5) {
                                if (logger.isWarnEnabled()) {
                                    logger.warn("Failed to close a temporary selector.", th5);
                                }
                            }
                            return -1;
                        }
                    } catch (Throwable unused8) {
                        if (serverSocketChannel != null) {
                            try {
                                serverSocketChannel.close();
                            } catch (Throwable th6) {
                                if (logger.isWarnEnabled()) {
                                    logger.warn("Failed to close a temporary socket.", th6);
                                }
                            }
                        }
                        if (selectorLoop != null) {
                            selectorLoop.done = true;
                            try {
                                newCachedThreadPool.shutdownNow();
                            } catch (InterruptedException | NullPointerException unused9) {
                            }
                            do {
                                try {
                                    selectorLoop.selector.wakeup();
                                } catch (Throwable unused10) {
                                }
                            } while (!newCachedThreadPool.awaitTermination(1L, TimeUnit.SECONDS));
                            try {
                                selectorLoop.selector.close();
                            } catch (Throwable th7) {
                                if (logger.isWarnEnabled()) {
                                    logger.warn("Failed to close a temporary selector.", th7);
                                }
                            }
                        }
                        return -1;
                    }
                } catch (Throwable th8) {
                    if (logger.isWarnEnabled()) {
                        logger.warn("Failed to open a temporary selector.", th8);
                    }
                    if (serverSocketChannel != null) {
                        try {
                            serverSocketChannel.close();
                        } catch (Throwable th9) {
                            if (logger.isWarnEnabled()) {
                                logger.warn("Failed to close a temporary socket.", th9);
                            }
                        }
                    }
                    return -1;
                }
            } catch (Throwable th10) {
                if (logger.isWarnEnabled()) {
                    logger.warn("Failed to configure a temporary socket.", th10);
                }
                if (serverSocketChannel != null) {
                    try {
                        serverSocketChannel.close();
                    } catch (Throwable th11) {
                        if (logger.isWarnEnabled()) {
                            logger.warn("Failed to close a temporary socket.", th11);
                        }
                    }
                }
                return -1;
            }
        } catch (Throwable unused11) {
            serverSocketChannel = null;
            selectorLoop = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class SelectorLoop implements Runnable {
        volatile boolean done;
        volatile boolean selecting;
        final Selector selector = Selector.open();

        SelectorLoop() throws IOException {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (!this.done) {
                synchronized (this) {
                }
                try {
                    this.selecting = true;
                    try {
                        this.selector.select(1000L);
                        this.selecting = false;
                        Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
                        Iterator<SelectionKey> it = selectedKeys.iterator();
                        while (it.hasNext()) {
                            it.next().interestOps(0);
                        }
                        selectedKeys.clear();
                    } catch (Throwable th) {
                        this.selecting = false;
                        throw th;
                        break;
                    }
                } catch (IOException e) {
                    if (NioProviderMetadata.logger.isWarnEnabled()) {
                        NioProviderMetadata.logger.warn("Failed to wait for a temporary selector.", e);
                    }
                }
            }
        }
    }

    public static void main(String[] strArr) throws Exception {
        for (Map.Entry entry : System.getProperties().entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println();
        System.out.println("Hard-coded Constraint Level: " + CONSTRAINT_LEVEL);
        System.out.println("Auto-detected Constraint Level: " + autodetect());
    }

    private NioProviderMetadata() {
    }
}
