package io.grpc.netty.shaded.io.netty.util.internal;

import androidx.core.os.EnvironmentCompat;
import io.grpc.netty.shaded.io.netty.util.CharsetUtil;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLogger;
import io.grpc.netty.shaded.io.netty.util.internal.logging.InternalLoggerFactory;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MpscArrayQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MpscChunkedArrayQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.MpscUnboundedArrayQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.SpscLinkedQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.atomic.MpscAtomicArrayQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.atomic.MpscChunkedAtomicArrayQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.atomic.MpscUnboundedAtomicArrayQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.queues.atomic.SpscLinkedAtomicQueue;
import io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicLong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xerial.snappy.OSInfo;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public final class PlatformDependent {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final boolean BIG_ENDIAN_NATIVE_ORDER;
    private static final Cleaner CLEANER;
    private static final boolean DIRECT_BUFFER_PREFERRED;
    private static final AtomicLong DIRECT_MEMORY_COUNTER;
    private static final long DIRECT_MEMORY_LIMIT;
    private static final String LINUX_ID_LIKE_PREFIX = "ID_LIKE=";
    private static final String LINUX_ID_PREFIX = "ID=";
    private static final Set<String> LINUX_OS_CLASSIFIERS;
    private static final int MAX_ALLOWED_MPSC_CAPACITY = 1073741824;
    private static final boolean MAYBE_SUPER_USER;
    private static final int MIN_MAX_MPSC_CAPACITY = 2048;
    private static final int MPSC_CHUNK_SIZE = 1024;
    private static final Cleaner NOOP;
    private static final ThreadLocalRandomProvider RANDOM_PROVIDER;
    private static final int UNINITIALIZED_ARRAY_ALLOCATION_THRESHOLD;
    private static final boolean USE_DIRECT_BUFFER_NO_CLEANER;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PlatformDependent.class);
    private static final Pattern MAX_DIRECT_MEMORY_SIZE_ARG_PATTERN = Pattern.compile("\\s*-XX:MaxDirectMemorySize\\s*=\\s*([0-9]+)\\s*([kKmMgG]?)\\s*$");
    private static final boolean IS_WINDOWS = isWindows0();
    private static final boolean IS_OSX = isOsx0();
    private static final boolean IS_J9_JVM = isJ9Jvm0();
    private static final boolean IS_IVKVM_DOT_NET = isIkvmDotNet0();
    private static final boolean CAN_ENABLE_TCP_NODELAY_BY_DEFAULT = !isAndroid();
    private static final Throwable UNSAFE_UNAVAILABILITY_CAUSE = unsafeUnavailabilityCause0();
    private static final long MAX_DIRECT_MEMORY = maxDirectMemory0();
    private static final long BYTE_ARRAY_BASE_OFFSET = byteArrayBaseOffset0();
    private static final File TMPDIR = tmpdir0();
    private static final int BIT_MODE = bitMode0();
    private static final String NORMALIZED_ARCH = normalizeArch(SystemPropertyUtil.get("os.arch", ""));
    private static final String NORMALIZED_OS = normalizeOs(SystemPropertyUtil.get("os.name", ""));
    private static final String[] ALLOWED_LINUX_OS_CLASSIFIERS = {"fedora", "suse", "arch"};
    private static final int ADDRESS_SIZE = addressSize0();
    private static final String[] OS_RELEASE_FILES = {"/etc/os-release", "/usr/lib/os-release"};

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private interface ThreadLocalRandomProvider {
        Random current();
    }

    private static int hashCodeAsciiSanitizeByte(char c) {
        return c & 31;
    }

    static {
        BIG_ENDIAN_NATIVE_ORDER = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
        NOOP = new Cleaner() { // from class: io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent.1
            @Override // io.grpc.netty.shaded.io.netty.util.internal.Cleaner
            public void freeDirectBuffer(ByteBuffer byteBuffer) {
            }
        };
        if (javaVersion() >= 7) {
            RANDOM_PROVIDER = new ThreadLocalRandomProvider() { // from class: io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent.2
                @Override // io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent.ThreadLocalRandomProvider
                public Random current() {
                    return java.util.concurrent.ThreadLocalRandom.current();
                }
            };
        } else {
            RANDOM_PROVIDER = new ThreadLocalRandomProvider() { // from class: io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent.3
                @Override // io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent.ThreadLocalRandomProvider
                public Random current() {
                    return ThreadLocalRandom.current();
                }
            };
        }
        long j = SystemPropertyUtil.getLong("io.grpc.netty.shaded.io.netty.maxDirectMemory", -1L);
        if (j == 0 || !hasUnsafe() || !PlatformDependent0.hasDirectBufferNoCleanerConstructor()) {
            USE_DIRECT_BUFFER_NO_CLEANER = false;
            DIRECT_MEMORY_COUNTER = null;
        } else {
            USE_DIRECT_BUFFER_NO_CLEANER = true;
            if (j < 0) {
                j = MAX_DIRECT_MEMORY;
                if (j <= 0) {
                    DIRECT_MEMORY_COUNTER = null;
                } else {
                    DIRECT_MEMORY_COUNTER = new AtomicLong();
                }
            } else {
                DIRECT_MEMORY_COUNTER = new AtomicLong();
            }
        }
        logger.debug("-Dio.netty.maxDirectMemory: {} bytes", Long.valueOf(j));
        if (j < 1) {
            j = MAX_DIRECT_MEMORY;
        }
        DIRECT_MEMORY_LIMIT = j;
        int i = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.uninitializedArrayAllocationThreshold", 1024);
        if (javaVersion() < 9 || !PlatformDependent0.hasAllocateArrayMethod()) {
            i = -1;
        }
        UNINITIALIZED_ARRAY_ALLOCATION_THRESHOLD = i;
        logger.debug("-Dio.netty.uninitializedArrayAllocationThreshold: {}", Integer.valueOf(UNINITIALIZED_ARRAY_ALLOCATION_THRESHOLD));
        MAYBE_SUPER_USER = maybeSuperUser0();
        if (!isAndroid()) {
            if (javaVersion() >= 9) {
                CLEANER = CleanerJava9.isSupported() ? new CleanerJava9() : NOOP;
            } else {
                CLEANER = CleanerJava6.isSupported() ? new CleanerJava6() : NOOP;
            }
        } else {
            CLEANER = NOOP;
        }
        DIRECT_BUFFER_PREFERRED = (CLEANER == NOOP || SystemPropertyUtil.getBoolean("io.grpc.netty.shaded.io.netty.noPreferDirect", false)) ? false : true;
        if (logger.isDebugEnabled()) {
            logger.debug("-Dio.netty.noPreferDirect: {}", Boolean.valueOf(true ^ DIRECT_BUFFER_PREFERRED));
        }
        if (CLEANER == NOOP && !PlatformDependent0.isExplicitNoUnsafe()) {
            logger.info("Your platform does not provide complete low-level API for accessing direct buffers reliably. Unless explicitly requested, heap buffer will always be preferred to avoid potential system instability.");
        }
        final Set unmodifiableSet = Collections.unmodifiableSet(new HashSet(Arrays.asList(ALLOWED_LINUX_OS_CLASSIFIERS)));
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (final String str : OS_RELEASE_FILES) {
            final File file = new File(str);
            if (((Boolean) AccessController.doPrivileged(new PrivilegedAction<Boolean>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent.4
                /* JADX WARN: Can't rename method to resolve collision */
                /* JADX WARN: Code restructure failed: missing block: B:38:0x008e, code lost:
                
                    if (r3 == null) goto L34;
                 */
                /* JADX WARN: Removed duplicated region for block: B:43:0x0099 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                @Override // java.security.PrivilegedAction
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                public Boolean run() {
                    Throwable th;
                    BufferedReader bufferedReader;
                    SecurityException e;
                    IOException e2;
                    try {
                        if (file.exists()) {
                            BufferedReader bufferedReader2 = null;
                            try {
                                try {
                                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), CharsetUtil.UTF_8));
                                } catch (IOException e3) {
                                    bufferedReader = null;
                                    e2 = e3;
                                } catch (SecurityException e4) {
                                    bufferedReader = null;
                                    e = e4;
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (0 != 0) {
                                    }
                                    throw th;
                                }
                                while (true) {
                                    try {
                                        String readLine = bufferedReader.readLine();
                                        if (readLine != null) {
                                            if (readLine.startsWith(PlatformDependent.LINUX_ID_PREFIX)) {
                                                PlatformDependent.addClassifier(unmodifiableSet, linkedHashSet, PlatformDependent.normalizeOsReleaseVariableValue(readLine.substring(3)));
                                            } else if (readLine.startsWith(PlatformDependent.LINUX_ID_LIKE_PREFIX)) {
                                                PlatformDependent.addClassifier(unmodifiableSet, linkedHashSet, PlatformDependent.normalizeOsReleaseVariableValue(readLine.substring(8)).split("[ ]+"));
                                            }
                                        }
                                    } catch (IOException e5) {
                                        e2 = e5;
                                        PlatformDependent.logger.debug("Error while reading content of {}", str, e2);
                                        if (bufferedReader != null) {
                                            break;
                                            bufferedReader.close();
                                        }
                                        return true;
                                    } catch (SecurityException e6) {
                                        e = e6;
                                        PlatformDependent.logger.debug("Unable to read {}", str, e);
                                    }
                                    try {
                                        break;
                                    } catch (IOException unused) {
                                        return true;
                                    }
                                }
                                bufferedReader.close();
                            } catch (Throwable th3) {
                                th = th3;
                                if (0 != 0) {
                                    try {
                                        bufferedReader2.close();
                                    } catch (IOException unused2) {
                                    }
                                }
                                throw th;
                            }
                        }
                    } catch (SecurityException e7) {
                        PlatformDependent.logger.debug("Unable to check if {} exists", str, e7);
                    }
                    return false;
                }
            })).booleanValue()) {
                break;
            }
        }
        LINUX_OS_CLASSIFIERS = Collections.unmodifiableSet(linkedHashSet);
    }

    public static boolean hasDirectBufferNoCleanerConstructor() {
        return PlatformDependent0.hasDirectBufferNoCleanerConstructor();
    }

    public static byte[] allocateUninitializedArray(int i) {
        int i2 = UNINITIALIZED_ARRAY_ALLOCATION_THRESHOLD;
        if (i2 < 0 || i2 > i) {
            return new byte[i];
        }
        return PlatformDependent0.allocateUninitializedArray(i);
    }

    public static boolean isAndroid() {
        return PlatformDependent0.isAndroid();
    }

    public static boolean isWindows() {
        return IS_WINDOWS;
    }

    public static boolean isOsx() {
        return IS_OSX;
    }

    public static boolean maybeSuperUser() {
        return MAYBE_SUPER_USER;
    }

    public static int javaVersion() {
        return PlatformDependent0.javaVersion();
    }

    public static boolean canEnableTcpNoDelayByDefault() {
        return CAN_ENABLE_TCP_NODELAY_BY_DEFAULT;
    }

    public static boolean hasUnsafe() {
        return UNSAFE_UNAVAILABILITY_CAUSE == null;
    }

    public static Throwable getUnsafeUnavailabilityCause() {
        return UNSAFE_UNAVAILABILITY_CAUSE;
    }

    public static boolean isUnaligned() {
        return PlatformDependent0.isUnaligned();
    }

    public static boolean directBufferPreferred() {
        return DIRECT_BUFFER_PREFERRED;
    }

    public static long maxDirectMemory() {
        return DIRECT_MEMORY_LIMIT;
    }

    public static long usedDirectMemory() {
        AtomicLong atomicLong = DIRECT_MEMORY_COUNTER;
        if (atomicLong != null) {
            return atomicLong.get();
        }
        return -1L;
    }

    public static File tmpdir() {
        return TMPDIR;
    }

    public static int bitMode() {
        return BIT_MODE;
    }

    public static int addressSize() {
        return ADDRESS_SIZE;
    }

    public static long allocateMemory(long j) {
        return PlatformDependent0.allocateMemory(j);
    }

    public static void freeMemory(long j) {
        PlatformDependent0.freeMemory(j);
    }

    public static long reallocateMemory(long j, long j2) {
        return PlatformDependent0.reallocateMemory(j, j2);
    }

    public static void throwException(Throwable th) {
        if (hasUnsafe()) {
            PlatformDependent0.throwException(th);
        } else {
            throwException0(th);
        }
    }

    private static <E extends Throwable> void throwException0(Throwable th) throws Throwable {
        throw th;
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentHashMap() {
        return new ConcurrentHashMap();
    }

    public static LongCounter newLongCounter() {
        if (javaVersion() >= 8) {
            return new LongAdderCounter();
        }
        return new AtomicLongCounter();
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentHashMap(int i) {
        return new ConcurrentHashMap(i);
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentHashMap(int i, float f) {
        return new ConcurrentHashMap(i, f);
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentHashMap(int i, float f, int i2) {
        return new ConcurrentHashMap(i, f, i2);
    }

    public static <K, V> ConcurrentMap<K, V> newConcurrentHashMap(Map<? extends K, ? extends V> map) {
        return new ConcurrentHashMap(map);
    }

    public static void freeDirectBuffer(ByteBuffer byteBuffer) {
        CLEANER.freeDirectBuffer(byteBuffer);
    }

    public static long directBufferAddress(ByteBuffer byteBuffer) {
        return PlatformDependent0.directBufferAddress(byteBuffer);
    }

    public static ByteBuffer directBuffer(long j, int i) {
        if (PlatformDependent0.hasDirectBufferNoCleanerConstructor()) {
            return PlatformDependent0.newDirectBuffer(j, i);
        }
        throw new UnsupportedOperationException("sun.misc.Unsafe or java.nio.DirectByteBuffer.<init>(long, int) not available");
    }

    public static Object getObject(Object obj, long j) {
        return PlatformDependent0.getObject(obj, j);
    }

    public static int getInt(Object obj, long j) {
        return PlatformDependent0.getInt(obj, j);
    }

    public static byte getByte(long j) {
        return PlatformDependent0.getByte(j);
    }

    public static short getShort(long j) {
        return PlatformDependent0.getShort(j);
    }

    public static int getInt(long j) {
        return PlatformDependent0.getInt(j);
    }

    public static long getLong(long j) {
        return PlatformDependent0.getLong(j);
    }

    public static byte getByte(byte[] bArr, int i) {
        return PlatformDependent0.getByte(bArr, i);
    }

    public static byte getByte(byte[] bArr, long j) {
        return PlatformDependent0.getByte(bArr, j);
    }

    public static short getShort(byte[] bArr, int i) {
        return PlatformDependent0.getShort(bArr, i);
    }

    public static int getInt(byte[] bArr, int i) {
        return PlatformDependent0.getInt(bArr, i);
    }

    public static int getInt(int[] iArr, long j) {
        return PlatformDependent0.getInt(iArr, j);
    }

    public static long getLong(byte[] bArr, int i) {
        return PlatformDependent0.getLong(bArr, i);
    }

    public static long getLong(long[] jArr, long j) {
        return PlatformDependent0.getLong(jArr, j);
    }

    private static long getLongSafe(byte[] bArr, int i) {
        if (BIG_ENDIAN_NATIVE_ORDER) {
            return (bArr[i + 7] & 255) | (bArr[i] << 56) | ((bArr[i + 1] & 255) << 48) | ((bArr[i + 2] & 255) << 40) | ((bArr[i + 3] & 255) << 32) | ((bArr[i + 4] & 255) << 24) | ((bArr[i + 5] & 255) << 16) | ((bArr[i + 6] & 255) << 8);
        }
        return (bArr[i + 7] << 56) | ((bArr[i + 2] & 255) << 16) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    private static int getIntSafe(byte[] bArr, int i) {
        int i2;
        int i3;
        if (BIG_ENDIAN_NATIVE_ORDER) {
            i2 = (bArr[i] << 24) | ((bArr[i + 1] & 255) << 16) | ((bArr[i + 2] & 255) << 8);
            i3 = bArr[i + 3] & 255;
        } else {
            i2 = (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
            i3 = bArr[i + 3] << 24;
        }
        return i3 | i2;
    }

    private static short getShortSafe(byte[] bArr, int i) {
        int i2;
        int i3;
        if (BIG_ENDIAN_NATIVE_ORDER) {
            i2 = bArr[i] << 8;
            i3 = bArr[i + 1] & 255;
        } else {
            i2 = bArr[i] & 255;
            i3 = bArr[i + 1] << 8;
        }
        return (short) (i3 | i2);
    }

    private static int hashCodeAsciiCompute(CharSequence charSequence, int i, int i2) {
        int hashCodeAsciiSanitizeInt;
        int hashCodeAsciiSanitizeInt2;
        if (BIG_ENDIAN_NATIVE_ORDER) {
            hashCodeAsciiSanitizeInt = (i2 * (-862048943)) + (hashCodeAsciiSanitizeInt(charSequence, i + 4) * 461845907);
            hashCodeAsciiSanitizeInt2 = hashCodeAsciiSanitizeInt(charSequence, i);
        } else {
            hashCodeAsciiSanitizeInt = (i2 * (-862048943)) + (hashCodeAsciiSanitizeInt(charSequence, i) * 461845907);
            hashCodeAsciiSanitizeInt2 = hashCodeAsciiSanitizeInt(charSequence, i + 4);
        }
        return hashCodeAsciiSanitizeInt + hashCodeAsciiSanitizeInt2;
    }

    private static int hashCodeAsciiSanitizeInt(CharSequence charSequence, int i) {
        int charAt;
        int charAt2;
        if (BIG_ENDIAN_NATIVE_ORDER) {
            charAt = (charSequence.charAt(i + 3) & 31) | ((charSequence.charAt(i + 2) & 31) << 8) | ((charSequence.charAt(i + 1) & 31) << 16);
            charAt2 = (charSequence.charAt(i) & 31) << 24;
        } else {
            charAt = ((charSequence.charAt(i + 3) & 31) << 24) | ((charSequence.charAt(i + 2) & 31) << 16) | ((charSequence.charAt(i + 1) & 31) << 8);
            charAt2 = charSequence.charAt(i) & 31;
        }
        return charAt2 | charAt;
    }

    private static int hashCodeAsciiSanitizeShort(CharSequence charSequence, int i) {
        int charAt;
        int charAt2;
        if (BIG_ENDIAN_NATIVE_ORDER) {
            charAt = charSequence.charAt(i + 1) & 31;
            charAt2 = (charSequence.charAt(i) & 31) << 8;
        } else {
            charAt = (charSequence.charAt(i + 1) & 31) << 8;
            charAt2 = charSequence.charAt(i) & 31;
        }
        return charAt2 | charAt;
    }

    public static void putByte(long j, byte b) {
        PlatformDependent0.putByte(j, b);
    }

    public static void putShort(long j, short s) {
        PlatformDependent0.putShort(j, s);
    }

    public static void putInt(long j, int i) {
        PlatformDependent0.putInt(j, i);
    }

    public static void putLong(long j, long j2) {
        PlatformDependent0.putLong(j, j2);
    }

    public static void putByte(byte[] bArr, int i, byte b) {
        PlatformDependent0.putByte(bArr, i, b);
    }

    public static void putShort(byte[] bArr, int i, short s) {
        PlatformDependent0.putShort(bArr, i, s);
    }

    public static void putInt(byte[] bArr, int i, int i2) {
        PlatformDependent0.putInt(bArr, i, i2);
    }

    public static void putLong(byte[] bArr, int i, long j) {
        PlatformDependent0.putLong(bArr, i, j);
    }

    public static void putObject(Object obj, long j, Object obj2) {
        PlatformDependent0.putObject(obj, j, obj2);
    }

    public static long objectFieldOffset(Field field) {
        return PlatformDependent0.objectFieldOffset(field);
    }

    public static void copyMemory(long j, long j2, long j3) {
        PlatformDependent0.copyMemory(j, j2, j3);
    }

    public static void copyMemory(byte[] bArr, int i, long j, long j2) {
        PlatformDependent0.copyMemory(bArr, BYTE_ARRAY_BASE_OFFSET + i, null, j, j2);
    }

    public static void copyMemory(long j, byte[] bArr, int i, long j2) {
        PlatformDependent0.copyMemory(null, j, bArr, BYTE_ARRAY_BASE_OFFSET + i, j2);
    }

    public static void setMemory(byte[] bArr, int i, long j, byte b) {
        PlatformDependent0.setMemory(bArr, BYTE_ARRAY_BASE_OFFSET + i, j, b);
    }

    public static void setMemory(long j, long j2, byte b) {
        PlatformDependent0.setMemory(j, j2, b);
    }

    public static ByteBuffer allocateDirectNoCleaner(int i) {
        incrementMemoryCounter(i);
        try {
            return PlatformDependent0.allocateDirectNoCleaner(i);
        } catch (Throwable th) {
            decrementMemoryCounter(i);
            throwException(th);
            return null;
        }
    }

    public static ByteBuffer reallocateDirectNoCleaner(ByteBuffer byteBuffer, int i) {
        int capacity = i - byteBuffer.capacity();
        incrementMemoryCounter(capacity);
        try {
            return PlatformDependent0.reallocateDirectNoCleaner(byteBuffer, i);
        } catch (Throwable th) {
            decrementMemoryCounter(capacity);
            throwException(th);
            return null;
        }
    }

    public static void freeDirectNoCleaner(ByteBuffer byteBuffer) {
        int capacity = byteBuffer.capacity();
        PlatformDependent0.freeMemory(PlatformDependent0.directBufferAddress(byteBuffer));
        decrementMemoryCounter(capacity);
    }

    private static void incrementMemoryCounter(int i) {
        AtomicLong atomicLong = DIRECT_MEMORY_COUNTER;
        if (atomicLong != null) {
            long j = i;
            long addAndGet = atomicLong.addAndGet(j);
            if (addAndGet <= DIRECT_MEMORY_LIMIT) {
                return;
            }
            DIRECT_MEMORY_COUNTER.addAndGet(-i);
            throw new OutOfDirectMemoryError("failed to allocate " + i + " byte(s) of direct memory (used: " + (addAndGet - j) + ", max: " + DIRECT_MEMORY_LIMIT + ')');
        }
    }

    private static void decrementMemoryCounter(int i) {
        AtomicLong atomicLong = DIRECT_MEMORY_COUNTER;
        if (atomicLong != null) {
            atomicLong.addAndGet(-i);
        }
    }

    public static boolean useDirectBufferNoCleaner() {
        return USE_DIRECT_BUFFER_NO_CLEANER;
    }

    public static boolean equals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (!hasUnsafe() || !PlatformDependent0.unalignedAccess()) {
            return equalsSafe(bArr, i, bArr2, i2, i3);
        }
        return PlatformDependent0.equals(bArr, i, bArr2, i2, i3);
    }

    public static boolean isZero(byte[] bArr, int i, int i2) {
        if (!hasUnsafe() || !PlatformDependent0.unalignedAccess()) {
            return isZeroSafe(bArr, i, i2);
        }
        return PlatformDependent0.isZero(bArr, i, i2);
    }

    public static int equalsConstantTime(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (!hasUnsafe() || !PlatformDependent0.unalignedAccess()) {
            return ConstantTimeUtils.equalsConstantTime(bArr, i, bArr2, i2, i3);
        }
        return PlatformDependent0.equalsConstantTime(bArr, i, bArr2, i2, i3);
    }

    public static int hashCodeAscii(byte[] bArr, int i, int i2) {
        if (!hasUnsafe() || !PlatformDependent0.unalignedAccess()) {
            return hashCodeAsciiSafe(bArr, i, i2);
        }
        return PlatformDependent0.hashCodeAscii(bArr, i, i2);
    }

    public static int hashCodeAscii(CharSequence charSequence) {
        int i;
        int length = charSequence.length();
        int i2 = length & 7;
        int i3 = -1028477387;
        if (length >= 32) {
            for (int i4 = length - 8; i4 >= i2; i4 -= 8) {
                i3 = hashCodeAsciiCompute(charSequence, i4, i3);
            }
        } else if (length >= 8) {
            i3 = hashCodeAsciiCompute(charSequence, length - 8, -1028477387);
            if (length >= 16) {
                i3 = hashCodeAsciiCompute(charSequence, length - 16, i3);
                if (length >= 24) {
                    i3 = hashCodeAsciiCompute(charSequence, length - 24, i3);
                }
            }
        }
        if (i2 == 0) {
            return i3;
        }
        if (((i2 != 2) && (i2 != 4)) && (i2 != 6)) {
            i3 = (i3 * (-862048943)) + hashCodeAsciiSanitizeByte(charSequence.charAt(0));
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 != 1) & (i2 != 4) & (i2 != 5)) {
            i3 = (i3 * (i == 0 ? -862048943 : 461845907)) + PlatformDependent0.hashCodeAsciiSanitize(hashCodeAsciiSanitizeShort(charSequence, i));
            i += 2;
        }
        if (i2 >= 4) {
            return (i3 * (((i == 0) || (i == 3)) ? -862048943 : 461845907)) + hashCodeAsciiSanitizeInt(charSequence, i);
        }
        return i3;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class Mpsc {
        private static final boolean USE_MPSC_CHUNKED_ARRAY_QUEUE;

        private Mpsc() {
        }

        static {
            if ((PlatformDependent.hasUnsafe() ? AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.grpc.netty.shaded.io.netty.util.internal.PlatformDependent.Mpsc.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    return UnsafeAccess.UNSAFE;
                }
            }) : null) == null) {
                PlatformDependent.logger.debug("org.jctools-core.MpscChunkedArrayQueue: unavailable");
                USE_MPSC_CHUNKED_ARRAY_QUEUE = false;
            } else {
                PlatformDependent.logger.debug("org.jctools-core.MpscChunkedArrayQueue: available");
                USE_MPSC_CHUNKED_ARRAY_QUEUE = true;
            }
        }

        static <T> Queue<T> newMpscQueue(int i) {
            int max = Math.max(Math.min(i, 1073741824), 2048);
            return USE_MPSC_CHUNKED_ARRAY_QUEUE ? new MpscChunkedArrayQueue(1024, max) : new MpscChunkedAtomicArrayQueue(1024, max);
        }

        static <T> Queue<T> newMpscQueue() {
            return USE_MPSC_CHUNKED_ARRAY_QUEUE ? new MpscUnboundedArrayQueue(1024) : new MpscUnboundedAtomicArrayQueue(1024);
        }
    }

    public static <T> Queue<T> newMpscQueue() {
        return Mpsc.newMpscQueue();
    }

    public static <T> Queue<T> newMpscQueue(int i) {
        return Mpsc.newMpscQueue(i);
    }

    public static <T> Queue<T> newSpscQueue() {
        return hasUnsafe() ? new SpscLinkedQueue() : new SpscLinkedAtomicQueue();
    }

    public static <T> Queue<T> newFixedMpscQueue(int i) {
        return hasUnsafe() ? new MpscArrayQueue(i) : new MpscAtomicArrayQueue(i);
    }

    public static ClassLoader getClassLoader(Class<?> cls) {
        return PlatformDependent0.getClassLoader(cls);
    }

    public static ClassLoader getContextClassLoader() {
        return PlatformDependent0.getContextClassLoader();
    }

    public static ClassLoader getSystemClassLoader() {
        return PlatformDependent0.getSystemClassLoader();
    }

    public static <C> Deque<C> newConcurrentDeque() {
        if (javaVersion() < 7) {
            return new LinkedBlockingDeque();
        }
        return new ConcurrentLinkedDeque();
    }

    public static Random threadLocalRandom() {
        return RANDOM_PROVIDER.current();
    }

    private static boolean isWindows0() {
        boolean contains = SystemPropertyUtil.get("os.name", "").toLowerCase(Locale.US).contains("win");
        if (contains) {
            logger.debug("Platform: Windows");
        }
        return contains;
    }

    private static boolean isOsx0() {
        String replaceAll = SystemPropertyUtil.get("os.name", "").toLowerCase(Locale.US).replaceAll("[^a-z0-9]+", "");
        boolean z = replaceAll.startsWith("macosx") || replaceAll.startsWith("osx");
        if (z) {
            logger.debug("Platform: MacOS");
        }
        return z;
    }

    private static boolean maybeSuperUser0() {
        String str = SystemPropertyUtil.get("user.name");
        if (isWindows()) {
            return "Administrator".equals(str);
        }
        return "root".equals(str) || "toor".equals(str);
    }

    private static Throwable unsafeUnavailabilityCause0() {
        if (isAndroid()) {
            logger.debug("sun.misc.Unsafe: unavailable (Android)");
            return new UnsupportedOperationException("sun.misc.Unsafe: unavailable (Android)");
        }
        if (isIkvmDotNet()) {
            logger.debug("sun.misc.Unsafe: unavailable (IKVM.NET)");
            return new UnsupportedOperationException("sun.misc.Unsafe: unavailable (IKVM.NET)");
        }
        Throwable unsafeUnavailabilityCause = PlatformDependent0.getUnsafeUnavailabilityCause();
        if (unsafeUnavailabilityCause != null) {
            return unsafeUnavailabilityCause;
        }
        try {
            boolean hasUnsafe = PlatformDependent0.hasUnsafe();
            logger.debug("sun.misc.Unsafe: {}", hasUnsafe ? "available" : "unavailable");
            if (hasUnsafe) {
                return null;
            }
            return PlatformDependent0.getUnsafeUnavailabilityCause();
        } catch (Throwable th) {
            logger.trace("Could not determine if Unsafe is available", th);
            return new UnsupportedOperationException("Could not determine if Unsafe is available", th);
        }
    }

    public static boolean isJ9Jvm() {
        return IS_J9_JVM;
    }

    private static boolean isJ9Jvm0() {
        String lowerCase = SystemPropertyUtil.get("java.vm.name", "").toLowerCase();
        return lowerCase.startsWith("ibm j9") || lowerCase.startsWith("eclipse openj9");
    }

    public static boolean isIkvmDotNet() {
        return IS_IVKVM_DOT_NET;
    }

    private static boolean isIkvmDotNet0() {
        return SystemPropertyUtil.get("java.vm.name", "").toUpperCase(Locale.US).equals("IKVM.NET");
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x008d, code lost:
    
        r6 = java.lang.Long.parseLong(r8.group(1));
        r0 = r8.group(2).charAt(0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x00a0, code lost:
    
        if (r0 == 'G') goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x00a4, code lost:
    
        if (r0 == 'K') goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a8, code lost:
    
        if (r0 == 'M') goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x00ac, code lost:
    
        if (r0 == 'g') goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b0, code lost:
    
        if (r0 == 'k') goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00b4, code lost:
    
        if (r0 == 'm') goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b7, code lost:
    
        r3 = 1048576;
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c1, code lost:
    
        r6 = r6 * r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00bb, code lost:
    
        r3 = 1024;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00be, code lost:
    
        r3 = 1073741824;
     */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0046 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0047 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private static long maxDirectMemory0() {
        ClassLoader classLoader;
        long j;
        String lowerCase;
        try {
            classLoader = getSystemClassLoader();
            try {
                lowerCase = SystemPropertyUtil.get("java.vm.name", "").toLowerCase();
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            classLoader = null;
        }
        if (!lowerCase.startsWith("ibm j9") && !lowerCase.startsWith("eclipse openj9")) {
            j = ((Number) Class.forName("sun.misc.VM", true, classLoader).getDeclaredMethod("maxDirectMemory", new Class[0]).invoke(null, new Object[0])).longValue();
            if (j <= 0) {
                return j;
            }
            try {
                Class<?> cls = Class.forName("java.lang.management.ManagementFactory", true, classLoader);
                List list = (List) Class.forName("java.lang.management.RuntimeMXBean", true, classLoader).getDeclaredMethod("getInputArguments", new Class[0]).invoke(cls.getDeclaredMethod("getRuntimeMXBean", new Class[0]).invoke(null, new Object[0]), new Object[0]);
                int size = list.size() - 1;
                while (true) {
                    if (size < 0) {
                        break;
                    }
                    Matcher matcher = MAX_DIRECT_MEMORY_SIZE_ARG_PATTERN.matcher((CharSequence) list.get(size));
                    if (matcher.matches()) {
                        break;
                    }
                    size--;
                }
            } catch (Throwable unused3) {
            }
            if (j <= 0) {
                long maxMemory = Runtime.getRuntime().maxMemory();
                logger.debug("maxDirectMemory: {} bytes (maybe)", Long.valueOf(maxMemory));
                return maxMemory;
            }
            logger.debug("maxDirectMemory: {} bytes", Long.valueOf(j));
            return j;
        }
        j = 0;
        if (j <= 0) {
        }
    }

    private static File tmpdir0() {
        File file;
        File directory;
        try {
            directory = toDirectory(SystemPropertyUtil.get("io.grpc.netty.shaded.io.netty.tmpdir"));
        } catch (Throwable unused) {
        }
        if (directory != null) {
            logger.debug("-Dio.netty.tmpdir: {}", directory);
            return directory;
        }
        File directory2 = toDirectory(SystemPropertyUtil.get("java.io.tmpdir"));
        if (directory2 != null) {
            logger.debug("-Dio.netty.tmpdir: {} (java.io.tmpdir)", directory2);
            return directory2;
        }
        if (isWindows()) {
            File directory3 = toDirectory(System.getenv("TEMP"));
            if (directory3 != null) {
                logger.debug("-Dio.netty.tmpdir: {} (%TEMP%)", directory3);
                return directory3;
            }
            String str = System.getenv("USERPROFILE");
            if (str != null) {
                File directory4 = toDirectory(str + "\\AppData\\Local\\Temp");
                if (directory4 != null) {
                    logger.debug("-Dio.netty.tmpdir: {} (%USERPROFILE%\\AppData\\Local\\Temp)", directory4);
                    return directory4;
                }
                File directory5 = toDirectory(str + "\\Local Settings\\Temp");
                if (directory5 != null) {
                    logger.debug("-Dio.netty.tmpdir: {} (%USERPROFILE%\\Local Settings\\Temp)", directory5);
                    return directory5;
                }
            }
        } else {
            File directory6 = toDirectory(System.getenv("TMPDIR"));
            if (directory6 != null) {
                logger.debug("-Dio.netty.tmpdir: {} ($TMPDIR)", directory6);
                return directory6;
            }
        }
        if (isWindows()) {
            file = new File("C:\\Windows\\Temp");
        } else {
            file = new File("/tmp");
        }
        logger.warn("Failed to get the temporary directory; falling back to: {}", file);
        return file;
    }

    private static File toDirectory(String str) {
        if (str == null) {
            return null;
        }
        File file = new File(str);
        file.mkdirs();
        if (!file.isDirectory()) {
            return null;
        }
        try {
            return file.getAbsoluteFile();
        } catch (Exception unused) {
            return file;
        }
    }

    private static int bitMode0() {
        int i = SystemPropertyUtil.getInt("io.grpc.netty.shaded.io.netty.bitMode", 0);
        if (i > 0) {
            logger.debug("-Dio.netty.bitMode: {}", Integer.valueOf(i));
            return i;
        }
        int i2 = SystemPropertyUtil.getInt("sun.arch.data.model", 0);
        if (i2 > 0) {
            logger.debug("-Dio.netty.bitMode: {} (sun.arch.data.model)", Integer.valueOf(i2));
            return i2;
        }
        int i3 = SystemPropertyUtil.getInt("com.ibm.vm.bitmode", 0);
        if (i3 > 0) {
            logger.debug("-Dio.netty.bitMode: {} (com.ibm.vm.bitmode)", Integer.valueOf(i3));
            return i3;
        }
        String trim = SystemPropertyUtil.get("os.arch", "").toLowerCase(Locale.US).trim();
        if ("amd64".equals(trim) || OSInfo.X86_64.equals(trim)) {
            i3 = 64;
        } else if ("i386".equals(trim) || "i486".equals(trim) || "i586".equals(trim) || "i686".equals(trim)) {
            i3 = 32;
        }
        if (i3 > 0) {
            logger.debug("-Dio.netty.bitMode: {} (os.arch: {})", Integer.valueOf(i3), trim);
        }
        Matcher matcher = Pattern.compile("([1-9][0-9]+)-?bit").matcher(SystemPropertyUtil.get("java.vm.name", "").toLowerCase(Locale.US));
        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1));
        }
        return 64;
    }

    private static int addressSize0() {
        if (hasUnsafe()) {
            return PlatformDependent0.addressSize();
        }
        return -1;
    }

    private static long byteArrayBaseOffset0() {
        if (hasUnsafe()) {
            return PlatformDependent0.byteArrayBaseOffset();
        }
        return -1L;
    }

    private static boolean equalsSafe(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        int i4 = i3 + i;
        while (i < i4) {
            if (bArr[i] != bArr2[i2]) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    private static boolean isZeroSafe(byte[] bArr, int i, int i2) {
        int i3 = i2 + i;
        while (i < i3) {
            if (bArr[i] != 0) {
                return false;
            }
            i++;
        }
        return true;
    }

    static int hashCodeAsciiSafe(byte[] bArr, int i, int i2) {
        int i3;
        int hashCodeAsciiSanitize;
        int i4 = i2 & 7;
        int i5 = i + i4;
        int i6 = -1028477387;
        for (int i7 = (i - 8) + i2; i7 >= i5; i7 -= 8) {
            i6 = PlatformDependent0.hashCodeAsciiCompute(getLongSafe(bArr, i7), i6);
        }
        switch (i4) {
            case 1:
                i3 = i6 * (-862048943);
                hashCodeAsciiSanitize = PlatformDependent0.hashCodeAsciiSanitize(bArr[i]);
                break;
            case 2:
                i3 = i6 * (-862048943);
                hashCodeAsciiSanitize = PlatformDependent0.hashCodeAsciiSanitize(getShortSafe(bArr, i));
                break;
            case 3:
                i3 = ((i6 * (-862048943)) + PlatformDependent0.hashCodeAsciiSanitize(bArr[i])) * 461845907;
                hashCodeAsciiSanitize = PlatformDependent0.hashCodeAsciiSanitize(getShortSafe(bArr, i + 1));
                break;
            case 4:
                i3 = i6 * (-862048943);
                hashCodeAsciiSanitize = PlatformDependent0.hashCodeAsciiSanitize(getIntSafe(bArr, i));
                break;
            case 5:
                i3 = ((i6 * (-862048943)) + PlatformDependent0.hashCodeAsciiSanitize(bArr[i])) * 461845907;
                hashCodeAsciiSanitize = PlatformDependent0.hashCodeAsciiSanitize(getIntSafe(bArr, i + 1));
                break;
            case 6:
                i3 = ((i6 * (-862048943)) + PlatformDependent0.hashCodeAsciiSanitize(getShortSafe(bArr, i))) * 461845907;
                hashCodeAsciiSanitize = PlatformDependent0.hashCodeAsciiSanitize(getIntSafe(bArr, i + 2));
                break;
            case 7:
                i3 = ((((i6 * (-862048943)) + PlatformDependent0.hashCodeAsciiSanitize(bArr[i])) * 461845907) + PlatformDependent0.hashCodeAsciiSanitize(getShortSafe(bArr, i + 1))) * (-862048943);
                hashCodeAsciiSanitize = PlatformDependent0.hashCodeAsciiSanitize(getIntSafe(bArr, i + 3));
                break;
            default:
                return i6;
        }
        return i3 + hashCodeAsciiSanitize;
    }

    public static String normalizedArch() {
        return NORMALIZED_ARCH;
    }

    public static String normalizedOs() {
        return NORMALIZED_OS;
    }

    public static Set<String> normalizedLinuxClassifiers() {
        return LINUX_OS_CLASSIFIERS;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void addClassifier(Set<String> set, Set<String> set2, String... strArr) {
        for (String str : strArr) {
            if (set.contains(str)) {
                set2.add(str);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String normalizeOsReleaseVariableValue(String str) {
        return str.trim().replaceAll("[\"']", "");
    }

    private static String normalize(String str) {
        return str.toLowerCase(Locale.US).replaceAll("[^a-z0-9]+", "");
    }

    private static String normalizeArch(String str) {
        String normalize = normalize(str);
        return normalize.matches("^(x8664|amd64|ia32e|em64t|x64)$") ? OSInfo.X86_64 : normalize.matches("^(x8632|x86|i[3-6]86|ia32|x32)$") ? "x86_32" : normalize.matches("^(ia64|itanium64)$") ? "itanium_64" : normalize.matches("^(sparc|sparc32)$") ? "sparc_32" : normalize.matches("^(sparcv9|sparc64)$") ? "sparc_64" : normalize.matches("^(arm|arm32)$") ? "arm_32" : OSInfo.AARCH_64.equals(normalize) ? "aarch_64" : normalize.matches("^(ppc|ppc32)$") ? "ppc_32" : OSInfo.PPC64.equals(normalize) ? "ppc_64" : "ppc64le".equals(normalize) ? "ppcle_64" : OSInfo.IBMZ.equals(normalize) ? "s390_32" : OSInfo.IBMZ_64.equals(normalize) ? "s390_64" : EnvironmentCompat.MEDIA_UNKNOWN;
    }

    private static String normalizeOs(String str) {
        String normalize = normalize(str);
        if (normalize.startsWith("aix")) {
            return "aix";
        }
        if (normalize.startsWith("hpux")) {
            return "hpux";
        }
        if (normalize.startsWith("os400") && (normalize.length() <= 5 || !Character.isDigit(normalize.charAt(5)))) {
            return "os400";
        }
        if (normalize.startsWith("linux")) {
            return "linux";
        }
        String str2 = "osx";
        if (!normalize.startsWith("macosx") && !normalize.startsWith("osx")) {
            if (normalize.startsWith("freebsd")) {
                return "freebsd";
            }
            if (normalize.startsWith("openbsd")) {
                return "openbsd";
            }
            if (normalize.startsWith("netbsd")) {
                return "netbsd";
            }
            str2 = "sunos";
            if (!normalize.startsWith("solaris") && !normalize.startsWith("sunos")) {
                return normalize.startsWith("windows") ? "windows" : EnvironmentCompat.MEDIA_UNKNOWN;
            }
        }
        return str2;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes7.dex */
    private static final class AtomicLongCounter extends AtomicLong implements LongCounter {
        private static final long serialVersionUID = 4074772784610639305L;

        private AtomicLongCounter() {
        }

        @Override // io.grpc.netty.shaded.io.netty.util.internal.LongCounter
        public void add(long j) {
            addAndGet(j);
        }

        @Override // io.grpc.netty.shaded.io.netty.util.internal.LongCounter
        public void increment() {
            incrementAndGet();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.internal.LongCounter
        public void decrement() {
            decrementAndGet();
        }

        @Override // io.grpc.netty.shaded.io.netty.util.internal.LongCounter
        public long value() {
            return get();
        }
    }

    private PlatformDependent() {
    }
}
