package io.grpc.netty.shaded.io.netty.util.internal;

import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.Thread;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class ThreadLocalRandom extends Random {
    private static final long addend = 11;
    private static volatile long initialSeedUniquifier = 0;
    private static final long mask = 281474976710655L;
    private static final long multiplier = 25214903917L;
    private static volatile long seedGeneratorEndTime = 0;
    private static final long seedGeneratorStartTime;
    private static final Thread seedGeneratorThread;
    private static final BlockingQueue<Long> seedQueue;
    private static final long serialVersionUID = -5851777807851030925L;
    boolean initialized;
    private long pad0;
    private long pad1;
    private long pad2;
    private long pad3;
    private long pad4;
    private long pad5;
    private long pad6;
    private long pad7;
    private long rnd;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ThreadLocalRandom.class);
    private static final AtomicLong seedUniquifier = new AtomicLong();

    private static long mix64(long j) {
        long j2 = (j ^ (j >>> 33)) * (-49064778989728563L);
        long j3 = (j2 ^ (j2 >>> 33)) * (-4265267296055464877L);
        return j3 ^ (j3 >>> 33);
    }

    static {
        initialSeedUniquifier = SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.initialSeedUniquifier", 0L);
        if (initialSeedUniquifier == 0) {
            if (SystemPropertyUtil.getBoolean("java.util.secureRandomSeed", false)) {
                seedQueue = new LinkedBlockingQueue();
                seedGeneratorStartTime = System.nanoTime();
                seedGeneratorThread = new Thread("initialSeedUniquifierGenerator") { // from class: io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom.1
                    @Override // java.lang.Thread, java.lang.Runnable
                    public void run() {
                        byte[] generateSeed = new SecureRandom().generateSeed(8);
                        long unused = ThreadLocalRandom.seedGeneratorEndTime = System.nanoTime();
                        ThreadLocalRandom.seedQueue.add(Long.valueOf(((generateSeed[0] & 255) << 56) | ((generateSeed[1] & 255) << 48) | ((generateSeed[2] & 255) << 40) | ((generateSeed[3] & 255) << 32) | ((generateSeed[4] & 255) << 24) | ((generateSeed[5] & 255) << 16) | ((generateSeed[6] & 255) << 8) | (generateSeed[7] & 255)));
                    }
                };
                seedGeneratorThread.setDaemon(true);
                seedGeneratorThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: io.grpc.netty.shaded.io.netty.util.internal.ThreadLocalRandom.2
                    @Override // java.lang.Thread.UncaughtExceptionHandler
                    public void uncaughtException(Thread thread, Throwable th) {
                        ThreadLocalRandom.logger.debug("An exception has been raised by {}", thread.getName(), th);
                    }
                });
                seedGeneratorThread.start();
                return;
            }
            initialSeedUniquifier = mix64(System.currentTimeMillis()) ^ mix64(System.nanoTime());
            seedGeneratorThread = null;
            seedQueue = null;
            seedGeneratorStartTime = 0L;
            return;
        }
        seedGeneratorThread = null;
        seedQueue = null;
        seedGeneratorStartTime = 0L;
    }

    public static void setInitialSeedUniquifier(long j) {
        initialSeedUniquifier = j;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x003f, code lost:
    
        r4 = r10.longValue();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static long getInitialSeedUniquifier() {
        Long poll;
        long j = initialSeedUniquifier;
        if (j != 0) {
            return j;
        }
        synchronized (ThreadLocalRandom.class) {
            long j2 = initialSeedUniquifier;
            if (j2 != 0) {
                return j2;
            }
            long nanos = seedGeneratorStartTime + TimeUnit.SECONDS.toNanos(3L);
            boolean z = false;
            while (true) {
                long nanoTime = nanos - System.nanoTime();
                if (nanoTime <= 0) {
                    try {
                        poll = seedQueue.poll();
                    } catch (InterruptedException unused) {
                        z = true;
                        logger.warn("Failed to generate a seed from SecureRandom due to an InterruptedException.");
                    }
                } else {
                    poll = seedQueue.poll(nanoTime, TimeUnit.NANOSECONDS);
                }
                if (poll != null) {
                    break;
                }
                if (nanoTime <= 0) {
                    seedGeneratorThread.interrupt();
                    logger.warn("Failed to generate a seed from SecureRandom within {} seconds. Not enough entropy?", (Object) 3L);
                    break;
                }
            }
            long reverse = (j2 ^ 3627065505421648153L) ^ Long.reverse(System.nanoTime());
            initialSeedUniquifier = reverse;
            if (z) {
                Thread.currentThread().interrupt();
                seedGeneratorThread.interrupt();
            }
            if (seedGeneratorEndTime == 0) {
                seedGeneratorEndTime = System.nanoTime();
            }
            return reverse;
        }
    }

    private static long newSeed() {
        long j;
        long initialSeedUniquifier2;
        long j2;
        do {
            j = seedUniquifier.get();
            initialSeedUniquifier2 = j != 0 ? j : getInitialSeedUniquifier();
            j2 = 181783497276652981L * initialSeedUniquifier2;
        } while (!seedUniquifier.compareAndSet(j, j2));
        if (j == 0 && logger.isDebugEnabled()) {
            if (seedGeneratorEndTime != 0) {
                logger.debug(String.format("-Dio.netty.initialSeedUniquifier: 0x%016x (took %d ms)", Long.valueOf(initialSeedUniquifier2), Long.valueOf(TimeUnit.NANOSECONDS.toMillis(seedGeneratorEndTime - seedGeneratorStartTime))));
            } else {
                logger.debug(String.format("-Dio.netty.initialSeedUniquifier: 0x%016x", Long.valueOf(initialSeedUniquifier2)));
            }
        }
        return System.nanoTime() ^ j2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ThreadLocalRandom() {
        super(newSeed());
        this.initialized = true;
    }

    public static ThreadLocalRandom current() {
        return InternalThreadLocalMap.get().random();
    }

    @Override // java.util.Random
    public void setSeed(long j) {
        if (this.initialized) {
            throw new UnsupportedOperationException();
        }
        this.rnd = (j ^ multiplier) & mask;
    }

    @Override // java.util.Random
    protected int next(int i) {
        this.rnd = ((this.rnd * multiplier) + addend) & mask;
        return (int) (this.rnd >>> (48 - i));
    }

    public int nextInt(int i, int i2) {
        if (i >= i2) {
            throw new IllegalArgumentException();
        }
        return nextInt(i2 - i) + i;
    }

    public long nextLong(long j) {
        long j2 = 0;
        if (j <= 0) {
            throw new IllegalArgumentException("n must be positive");
        }
        while (j >= 2147483647L) {
            int next = next(2);
            long j3 = j >>> 1;
            if ((next & 2) != 0) {
                j3 = j - j3;
            }
            if ((next & 1) == 0) {
                j2 += j - j3;
            }
            j = j3;
        }
        return j2 + nextInt((int) j);
    }

    public long nextLong(long j, long j2) {
        if (j >= j2) {
            throw new IllegalArgumentException();
        }
        return nextLong(j2 - j) + j;
    }

    public double nextDouble(double d) {
        if (d <= 0.0d) {
            throw new IllegalArgumentException("n must be positive");
        }
        return nextDouble() * d;
    }

    public double nextDouble(double d, double d2) {
        if (d >= d2) {
            throw new IllegalArgumentException();
        }
        return (nextDouble() * (d2 - d)) + d;
    }
}
