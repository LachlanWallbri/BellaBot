package io.netty.util.internal;

import com.google.common.base.Ascii;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;
import org.objenesis.strategy.PlatformDescription;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes8.dex
 */
/* loaded from: classes.dex */
public final class PlatformDependent0 {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final long ADDRESS_FIELD_OFFSET;
    private static final Method ALLOCATE_ARRAY_METHOD;
    private static final long BYTE_ARRAY_BASE_OFFSET;
    private static final Constructor<?> DIRECT_BUFFER_CONSTRUCTOR;
    static final int HASH_CODE_ASCII_SEED = -1028477387;
    static final int HASH_CODE_C1 = -862048943;
    static final int HASH_CODE_C2 = 461845907;
    private static final Object INTERNAL_UNSAFE;
    private static final boolean UNALIGNED;
    static final Unsafe UNSAFE;
    private static final long UNSAFE_COPY_THRESHOLD = 1048576;
    private static final Throwable UNSAFE_UNAVAILABILITY_CAUSE;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) PlatformDependent0.class);
    private static final Throwable EXPLICIT_NO_UNSAFE_CAUSE = explicitNoUnsafeCause0();
    private static final int JAVA_VERSION = javaVersion0();
    private static final boolean IS_ANDROID = isAndroid0();
    private static final boolean IS_EXPLICIT_TRY_REFLECTION_SET_ACCESSIBLE = explicitTryReflectionSetAccessible0();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int hashCodeAsciiSanitize(byte b) {
        return b & Ascii.f1926US;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int hashCodeAsciiSanitize(int i) {
        return i & 522133279;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int hashCodeAsciiSanitize(short s) {
        return s & 7967;
    }

    static {
        final ByteBuffer allocateDirect;
        final Unsafe unsafe;
        Field field;
        long j;
        Constructor<?> constructor;
        boolean z;
        Method method;
        Throwable th = EXPLICIT_NO_UNSAFE_CAUSE;
        Method method2 = null;
        if (th != null) {
            allocateDirect = null;
            unsafe = null;
            field = null;
        } else {
            allocateDirect = ByteBuffer.allocateDirect(1);
            Object doPrivileged = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.netty.util.internal.PlatformDependent0.1
                @Override // java.security.PrivilegedAction
                public Object run() {
                    try {
                        Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
                        Throwable trySetAccessible = ReflectionUtil.trySetAccessible(declaredField, false);
                        return trySetAccessible != null ? trySetAccessible : declaredField.get(null);
                    } catch (IllegalAccessException e) {
                        return e;
                    } catch (NoClassDefFoundError e2) {
                        return e2;
                    } catch (NoSuchFieldException e3) {
                        return e3;
                    } catch (SecurityException e4) {
                        return e4;
                    }
                }
            });
            if (doPrivileged instanceof Throwable) {
                th = (Throwable) doPrivileged;
                logger.debug("sun.misc.Unsafe.theUnsafe: unavailable", th);
                unsafe = null;
            } else {
                unsafe = (Unsafe) doPrivileged;
                logger.debug("sun.misc.Unsafe.theUnsafe: available");
            }
            if (unsafe != null) {
                Object doPrivileged2 = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.netty.util.internal.PlatformDependent0.2
                    @Override // java.security.PrivilegedAction
                    public Object run() {
                        try {
                            unsafe.getClass().getDeclaredMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
                            return null;
                        } catch (NoSuchMethodException e) {
                            return e;
                        } catch (SecurityException e2) {
                            return e2;
                        }
                    }
                });
                if (doPrivileged2 == null) {
                    logger.debug("sun.misc.Unsafe.copyMemory: available");
                } else {
                    th = (Throwable) doPrivileged2;
                    logger.debug("sun.misc.Unsafe.copyMemory: unavailable", th);
                    unsafe = null;
                }
            }
            if (unsafe != null) {
                Object doPrivileged3 = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.netty.util.internal.PlatformDependent0.3
                    @Override // java.security.PrivilegedAction
                    public Object run() {
                        try {
                            Field declaredField = Buffer.class.getDeclaredField("address");
                            if (unsafe.getLong(allocateDirect, unsafe.objectFieldOffset(declaredField)) == 0) {
                                return null;
                            }
                            return declaredField;
                        } catch (NoSuchFieldException e) {
                            return e;
                        } catch (SecurityException e2) {
                            return e2;
                        }
                    }
                });
                if (doPrivileged3 instanceof Field) {
                    field = (Field) doPrivileged3;
                    logger.debug("java.nio.Buffer.address: available");
                } else {
                    Throwable th2 = (Throwable) doPrivileged3;
                    logger.debug("java.nio.Buffer.address: unavailable", th2);
                    unsafe = null;
                    th = th2;
                    field = null;
                }
            } else {
                field = null;
            }
            if (unsafe != null) {
                long arrayIndexScale = unsafe.arrayIndexScale(byte[].class);
                if (arrayIndexScale != 1) {
                    logger.debug("unsafe.arrayIndexScale is {} (expected: 1). Not using unsafe.", Long.valueOf(arrayIndexScale));
                    th = new UnsupportedOperationException("Unexpected unsafe.arrayIndexScale");
                    unsafe = null;
                }
            }
        }
        UNSAFE_UNAVAILABILITY_CAUSE = th;
        UNSAFE = unsafe;
        if (unsafe == null) {
            ADDRESS_FIELD_OFFSET = -1L;
            BYTE_ARRAY_BASE_OFFSET = -1L;
            UNALIGNED = false;
            DIRECT_BUFFER_CONSTRUCTOR = null;
            ALLOCATE_ARRAY_METHOD = null;
        } else {
            try {
                Object doPrivileged4 = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.netty.util.internal.PlatformDependent0.4
                    @Override // java.security.PrivilegedAction
                    public Object run() {
                        try {
                            Constructor<?> declaredConstructor = allocateDirect.getClass().getDeclaredConstructor(Long.TYPE, Integer.TYPE);
                            Throwable trySetAccessible = ReflectionUtil.trySetAccessible(declaredConstructor, true);
                            return trySetAccessible != null ? trySetAccessible : declaredConstructor;
                        } catch (NoSuchMethodException e) {
                            return e;
                        } catch (SecurityException e2) {
                            return e2;
                        }
                    }
                });
                if (doPrivileged4 instanceof Constructor) {
                    j = UNSAFE.allocateMemory(1L);
                    try {
                        ((Constructor) doPrivileged4).newInstance(Long.valueOf(j), 1);
                        constructor = (Constructor) doPrivileged4;
                        logger.debug("direct buffer constructor: available");
                    } catch (IllegalAccessException | InstantiationException | InvocationTargetException unused) {
                        constructor = null;
                    } catch (Throwable th3) {
                        th = th3;
                        if (j != -1) {
                            UNSAFE.freeMemory(j);
                        }
                        throw th;
                    }
                } else {
                    logger.debug("direct buffer constructor: unavailable", (Throwable) doPrivileged4);
                    constructor = null;
                    j = -1;
                }
                if (j != -1) {
                    UNSAFE.freeMemory(j);
                }
                DIRECT_BUFFER_CONSTRUCTOR = constructor;
                ADDRESS_FIELD_OFFSET = objectFieldOffset(field);
                BYTE_ARRAY_BASE_OFFSET = UNSAFE.arrayBaseOffset(byte[].class);
                Object doPrivileged5 = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.netty.util.internal.PlatformDependent0.5
                    @Override // java.security.PrivilegedAction
                    public Object run() {
                        try {
                            Method declaredMethod = Class.forName("java.nio.Bits", false, PlatformDependent0.getSystemClassLoader()).getDeclaredMethod("unaligned", new Class[0]);
                            Throwable trySetAccessible = ReflectionUtil.trySetAccessible(declaredMethod, true);
                            return trySetAccessible != null ? trySetAccessible : declaredMethod.invoke(null, new Object[0]);
                        } catch (ClassNotFoundException e) {
                            return e;
                        } catch (IllegalAccessException e2) {
                            return e2;
                        } catch (NoSuchMethodException e3) {
                            return e3;
                        } catch (SecurityException e4) {
                            return e4;
                        } catch (InvocationTargetException e5) {
                            return e5;
                        }
                    }
                });
                if (doPrivileged5 instanceof Boolean) {
                    z = ((Boolean) doPrivileged5).booleanValue();
                    logger.debug("java.nio.Bits.unaligned: available, {}", Boolean.valueOf(z));
                } else {
                    boolean matches = SystemPropertyUtil.get("os.arch", "").matches("^(i[3-6]86|x86(_64)?|x64|amd64)$");
                    logger.debug("java.nio.Bits.unaligned: unavailable {}", Boolean.valueOf(matches), (Throwable) doPrivileged5);
                    z = matches;
                }
                UNALIGNED = z;
                if (javaVersion() >= 9) {
                    final Object doPrivileged6 = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.netty.util.internal.PlatformDependent0.6
                        @Override // java.security.PrivilegedAction
                        public Object run() {
                            try {
                                return PlatformDependent0.getClassLoader(PlatformDependent0.class).loadClass("jdk.internal.misc.Unsafe").getDeclaredMethod("getUnsafe", new Class[0]).invoke(null, new Object[0]);
                            } catch (Throwable th4) {
                                return th4;
                            }
                        }
                    });
                    if (doPrivileged6 instanceof Throwable) {
                        method = null;
                    } else {
                        Object doPrivileged7 = AccessController.doPrivileged(new PrivilegedAction<Object>() { // from class: io.netty.util.internal.PlatformDependent0.7
                            @Override // java.security.PrivilegedAction
                            public Object run() {
                                try {
                                    return doPrivileged6.getClass().getDeclaredMethod("allocateUninitializedArray", Class.class, Integer.TYPE);
                                } catch (NoSuchMethodException e) {
                                    return e;
                                } catch (SecurityException e2) {
                                    return e2;
                                }
                            }
                        });
                        if (doPrivileged7 instanceof Method) {
                            try {
                                Method method3 = (Method) doPrivileged7;
                                method = doPrivileged6;
                                doPrivileged6 = doPrivileged7;
                                method2 = method3;
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                method = doPrivileged6;
                                doPrivileged6 = e;
                            }
                        } else {
                            method = doPrivileged6;
                            doPrivileged6 = doPrivileged7;
                        }
                    }
                    if (doPrivileged6 instanceof Throwable) {
                        logger.debug("jdk.internal.misc.Unsafe.allocateUninitializedArray(int): unavailable", (Throwable) doPrivileged6);
                    } else {
                        logger.debug("jdk.internal.misc.Unsafe.allocateUninitializedArray(int): available");
                    }
                } else {
                    logger.debug("jdk.internal.misc.Unsafe.allocateUninitializedArray(int): unavailable prior to Java9");
                    method = null;
                }
                ALLOCATE_ARRAY_METHOD = method2;
                method2 = method;
            } catch (Throwable th4) {
                th = th4;
                j = -1;
            }
        }
        INTERNAL_UNSAFE = method2;
        logger.debug("java.nio.DirectByteBuffer.<init>(long, int): {}", DIRECT_BUFFER_CONSTRUCTOR != null ? "available" : "unavailable");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isExplicitNoUnsafe() {
        return EXPLICIT_NO_UNSAFE_CAUSE == null;
    }

    private static Throwable explicitNoUnsafeCause0() {
        boolean z = SystemPropertyUtil.getBoolean("io.netty.noUnsafe", false);
        logger.debug("-Dio.netty.noUnsafe: {}", Boolean.valueOf(z));
        if (z) {
            logger.debug("sun.misc.Unsafe: unavailable (io.netty.noUnsafe)");
            return new UnsupportedOperationException("sun.misc.Unsafe: unavailable (io.netty.noUnsafe)");
        }
        String str = SystemPropertyUtil.contains("io.netty.tryUnsafe") ? "io.netty.tryUnsafe" : "org.jboss.netty.tryUnsafe";
        if (SystemPropertyUtil.getBoolean(str, true)) {
            return null;
        }
        String str2 = "sun.misc.Unsafe: unavailable (" + str + ")";
        logger.debug(str2);
        return new UnsupportedOperationException(str2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isUnaligned() {
        return UNALIGNED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasUnsafe() {
        return UNSAFE != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Throwable getUnsafeUnavailabilityCause() {
        return UNSAFE_UNAVAILABILITY_CAUSE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean unalignedAccess() {
        return UNALIGNED;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void throwException(Throwable th) {
        UNSAFE.throwException((Throwable) ObjectUtil.checkNotNull(th, "cause"));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasDirectBufferNoCleanerConstructor() {
        return DIRECT_BUFFER_CONSTRUCTOR != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer reallocateDirectNoCleaner(ByteBuffer byteBuffer, int i) {
        return newDirectBuffer(UNSAFE.reallocateMemory(directBufferAddress(byteBuffer), i), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer allocateDirectNoCleaner(int i) {
        return newDirectBuffer(UNSAFE.allocateMemory(i), i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean hasAllocateArrayMethod() {
        return ALLOCATE_ARRAY_METHOD != null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte[] allocateUninitializedArray(int i) {
        try {
            return (byte[]) ALLOCATE_ARRAY_METHOD.invoke(INTERNAL_UNSAFE, Byte.TYPE, Integer.valueOf(i));
        } catch (IllegalAccessException e) {
            throw new Error(e);
        } catch (InvocationTargetException e2) {
            throw new Error(e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ByteBuffer newDirectBuffer(long j, int i) {
        ObjectUtil.checkPositiveOrZero(i, "capacity");
        try {
            return (ByteBuffer) DIRECT_BUFFER_CONSTRUCTOR.newInstance(Long.valueOf(j), Integer.valueOf(i));
        } catch (Throwable th) {
            if (th instanceof Error) {
                throw th;
            }
            throw new Error(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long directBufferAddress(ByteBuffer byteBuffer) {
        return getLong(byteBuffer, ADDRESS_FIELD_OFFSET);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long byteArrayBaseOffset() {
        return BYTE_ARRAY_BASE_OFFSET;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object getObject(Object obj, long j) {
        return UNSAFE.getObject(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getInt(Object obj, long j) {
        return UNSAFE.getInt(obj, j);
    }

    private static long getLong(Object obj, long j) {
        return UNSAFE.getLong(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long objectFieldOffset(Field field) {
        return UNSAFE.objectFieldOffset(field);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte getByte(long j) {
        return UNSAFE.getByte(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short getShort(long j) {
        return UNSAFE.getShort(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getInt(long j) {
        return UNSAFE.getInt(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getLong(long j) {
        return UNSAFE.getLong(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte getByte(byte[] bArr, int i) {
        return UNSAFE.getByte(bArr, BYTE_ARRAY_BASE_OFFSET + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static short getShort(byte[] bArr, int i) {
        return UNSAFE.getShort(bArr, BYTE_ARRAY_BASE_OFFSET + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int getInt(byte[] bArr, int i) {
        return UNSAFE.getInt(bArr, BYTE_ARRAY_BASE_OFFSET + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long getLong(byte[] bArr, int i) {
        return UNSAFE.getLong(bArr, BYTE_ARRAY_BASE_OFFSET + i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putByte(long j, byte b) {
        UNSAFE.putByte(j, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putShort(long j, short s) {
        UNSAFE.putShort(j, s);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putInt(long j, int i) {
        UNSAFE.putInt(j, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putLong(long j, long j2) {
        UNSAFE.putLong(j, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putByte(byte[] bArr, int i, byte b) {
        UNSAFE.putByte(bArr, BYTE_ARRAY_BASE_OFFSET + i, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putShort(byte[] bArr, int i, short s) {
        UNSAFE.putShort(bArr, BYTE_ARRAY_BASE_OFFSET + i, s);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putInt(byte[] bArr, int i, int i2) {
        UNSAFE.putInt(bArr, BYTE_ARRAY_BASE_OFFSET + i, i2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void putLong(byte[] bArr, int i, long j) {
        UNSAFE.putLong(bArr, BYTE_ARRAY_BASE_OFFSET + i, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void copyMemory(long j, long j2, long j3) {
        while (j3 > 0) {
            long min = Math.min(j3, 1048576L);
            UNSAFE.copyMemory(j, j2, min);
            j3 -= min;
            j += min;
            j2 += min;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void copyMemory(Object obj, long j, Object obj2, long j2, long j3) {
        long j4 = j;
        long j5 = j2;
        long j6 = j3;
        while (j6 > 0) {
            long min = Math.min(j6, 1048576L);
            UNSAFE.copyMemory(obj, j4, obj2, j5, min);
            j6 -= min;
            j4 += min;
            j5 += min;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setMemory(long j, long j2, byte b) {
        UNSAFE.setMemory(j, j2, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void setMemory(Object obj, long j, long j2, byte b) {
        UNSAFE.setMemory(obj, j, j2, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean equals(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        if (i3 <= 0) {
            return true;
        }
        long j = BYTE_ARRAY_BASE_OFFSET;
        long j2 = i + j;
        long j3 = j + i2;
        int i4 = i3 & 7;
        long j4 = i4 + j2;
        long j5 = i3;
        long j6 = (j3 - 8) + j5;
        long j7 = (j2 - 8) + j5;
        long j8 = j6;
        while (j7 >= j4) {
            long j9 = j4;
            if (UNSAFE.getLong(bArr, j7) != UNSAFE.getLong(bArr2, j8)) {
                return false;
            }
            j7 -= 8;
            j8 -= 8;
            j4 = j9;
        }
        if (i4 >= 4) {
            i4 -= 4;
            long j10 = i4;
            if (UNSAFE.getInt(bArr, j2 + j10) != UNSAFE.getInt(bArr2, j10 + j3)) {
                return false;
            }
        }
        return i4 >= 2 ? UNSAFE.getChar(bArr, j2) == UNSAFE.getChar(bArr2, j3) && (i4 == 2 || bArr[i + 2] == bArr2[i2 + 2]) : bArr[i] == bArr2[i2];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int equalsConstantTime(byte[] bArr, int i, byte[] bArr2, int i2, int i3) {
        long j = BYTE_ARRAY_BASE_OFFSET;
        long j2 = i + j;
        int i4 = i3 & 7;
        long j3 = i4 + j2;
        long j4 = i3;
        long j5 = (j2 - 8) + j4;
        long j6 = ((j + i2) - 8) + j4;
        long j7 = 0;
        while (j5 >= j3) {
            j7 |= UNSAFE.getLong(bArr, j5) ^ UNSAFE.getLong(bArr2, j6);
            j5 -= 8;
            j6 -= 8;
        }
        switch (i4) {
            case 1:
                return ConstantTimeUtils.equalsConstantTime((UNSAFE.getByte(bArr, j2) ^ UNSAFE.getByte(bArr2, r3)) | j7, 0L);
            case 2:
                return ConstantTimeUtils.equalsConstantTime((UNSAFE.getChar(bArr, j2) ^ UNSAFE.getChar(bArr2, r3)) | j7, 0L);
            case 3:
                return ConstantTimeUtils.equalsConstantTime((UNSAFE.getByte(bArr, j2) ^ UNSAFE.getByte(bArr2, r3)) | (UNSAFE.getChar(bArr, j2 + 1) ^ UNSAFE.getChar(bArr2, 1 + r3)) | j7, 0L);
            case 4:
                return ConstantTimeUtils.equalsConstantTime((UNSAFE.getInt(bArr, j2) ^ UNSAFE.getInt(bArr2, r3)) | j7, 0L);
            case 5:
                return ConstantTimeUtils.equalsConstantTime((UNSAFE.getByte(bArr, j2) ^ UNSAFE.getByte(bArr2, r3)) | (UNSAFE.getInt(bArr, j2 + 1) ^ UNSAFE.getInt(bArr2, 1 + r3)) | j7, 0L);
            case 6:
                return ConstantTimeUtils.equalsConstantTime((UNSAFE.getChar(bArr, j2) ^ UNSAFE.getChar(bArr2, r3)) | (UNSAFE.getInt(bArr, j2 + 2) ^ UNSAFE.getInt(bArr2, 2 + r3)) | j7, 0L);
            case 7:
                return ConstantTimeUtils.equalsConstantTime((UNSAFE.getByte(bArr, j2) ^ UNSAFE.getByte(bArr2, r3)) | (UNSAFE.getChar(bArr, j2 + 1) ^ UNSAFE.getChar(bArr2, 1 + r3)) | j7 | (UNSAFE.getInt(bArr, j2 + 3) ^ UNSAFE.getInt(bArr2, 3 + r3)), 0L);
            default:
                return ConstantTimeUtils.equalsConstantTime(j7, 0L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isZero(byte[] bArr, int i, int i2) {
        if (i2 <= 0) {
            return true;
        }
        long j = BYTE_ARRAY_BASE_OFFSET + i;
        int i3 = i2 & 7;
        long j2 = i3 + j;
        for (long j3 = (j - 8) + i2; j3 >= j2; j3 -= 8) {
            if (UNSAFE.getLong(bArr, j3) != 0) {
                return false;
            }
        }
        if (i3 >= 4) {
            i3 -= 4;
            if (UNSAFE.getInt(bArr, i3 + j) != 0) {
                return false;
            }
        }
        return i3 >= 2 ? UNSAFE.getChar(bArr, j) == 0 && (i3 == 2 || bArr[i + 2] == 0) : bArr[i] == 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int hashCodeAscii(byte[] bArr, int i, int i2) {
        int i3;
        int hashCodeAsciiSanitize;
        long j = BYTE_ARRAY_BASE_OFFSET + i;
        int i4 = i2 & 7;
        long j2 = i4 + j;
        int i5 = HASH_CODE_ASCII_SEED;
        for (long j3 = (j - 8) + i2; j3 >= j2; j3 -= 8) {
            i5 = hashCodeAsciiCompute(UNSAFE.getLong(bArr, j3), i5);
        }
        switch (i4) {
            case 1:
                i3 = i5 * HASH_CODE_C1;
                hashCodeAsciiSanitize = hashCodeAsciiSanitize(UNSAFE.getByte(bArr, j));
                break;
            case 2:
                i3 = i5 * HASH_CODE_C1;
                hashCodeAsciiSanitize = hashCodeAsciiSanitize(UNSAFE.getShort(bArr, j));
                break;
            case 3:
                i3 = ((i5 * HASH_CODE_C1) + hashCodeAsciiSanitize(UNSAFE.getByte(bArr, j))) * HASH_CODE_C2;
                hashCodeAsciiSanitize = hashCodeAsciiSanitize(UNSAFE.getShort(bArr, j + 1));
                break;
            case 4:
                i3 = i5 * HASH_CODE_C1;
                hashCodeAsciiSanitize = hashCodeAsciiSanitize(UNSAFE.getInt(bArr, j));
                break;
            case 5:
                i3 = ((i5 * HASH_CODE_C1) + hashCodeAsciiSanitize(UNSAFE.getByte(bArr, j))) * HASH_CODE_C2;
                hashCodeAsciiSanitize = hashCodeAsciiSanitize(UNSAFE.getInt(bArr, j + 1));
                break;
            case 6:
                i3 = ((i5 * HASH_CODE_C1) + hashCodeAsciiSanitize(UNSAFE.getShort(bArr, j))) * HASH_CODE_C2;
                hashCodeAsciiSanitize = hashCodeAsciiSanitize(UNSAFE.getInt(bArr, j + 2));
                break;
            case 7:
                i3 = ((((i5 * HASH_CODE_C1) + hashCodeAsciiSanitize(UNSAFE.getByte(bArr, j))) * HASH_CODE_C2) + hashCodeAsciiSanitize(UNSAFE.getShort(bArr, 1 + j))) * HASH_CODE_C1;
                hashCodeAsciiSanitize = hashCodeAsciiSanitize(UNSAFE.getInt(bArr, j + 3));
                break;
            default:
                return i5;
        }
        return i3 + hashCodeAsciiSanitize;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int hashCodeAsciiCompute(long j, int i) {
        return (i * HASH_CODE_C1) + (hashCodeAsciiSanitize((int) j) * HASH_CODE_C2) + ((int) ((j & 2242545357458243584L) >>> 32));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassLoader getClassLoader(final Class<?> cls) {
        if (System.getSecurityManager() == null) {
            return cls.getClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() { // from class: io.netty.util.internal.PlatformDependent0.8
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public ClassLoader run() {
                return cls.getClassLoader();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassLoader getContextClassLoader() {
        if (System.getSecurityManager() == null) {
            return Thread.currentThread().getContextClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() { // from class: io.netty.util.internal.PlatformDependent0.9
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public ClassLoader run() {
                return Thread.currentThread().getContextClassLoader();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static ClassLoader getSystemClassLoader() {
        if (System.getSecurityManager() == null) {
            return ClassLoader.getSystemClassLoader();
        }
        return (ClassLoader) AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() { // from class: io.netty.util.internal.PlatformDependent0.10
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public ClassLoader run() {
                return ClassLoader.getSystemClassLoader();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int addressSize() {
        return UNSAFE.addressSize();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long allocateMemory(long j) {
        return UNSAFE.allocateMemory(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void freeMemory(long j) {
        UNSAFE.freeMemory(j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long reallocateMemory(long j, long j2) {
        return UNSAFE.reallocateMemory(j, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isAndroid() {
        return IS_ANDROID;
    }

    private static boolean isAndroid0() {
        boolean equals = PlatformDescription.DALVIK.equals(SystemPropertyUtil.get("java.vm.name"));
        if (equals) {
            logger.debug("Platform: Android");
        }
        return equals;
    }

    private static boolean explicitTryReflectionSetAccessible0() {
        return SystemPropertyUtil.getBoolean("io.netty.tryReflectionSetAccessible", javaVersion() < 9);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isExplicitTryReflectionSetAccessible() {
        return IS_EXPLICIT_TRY_REFLECTION_SET_ACCESSIBLE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int javaVersion() {
        return JAVA_VERSION;
    }

    private static int javaVersion0() {
        int majorVersionFromJavaSpecificationVersion = isAndroid0() ? 6 : majorVersionFromJavaSpecificationVersion();
        logger.debug("Java version: {}", Integer.valueOf(majorVersionFromJavaSpecificationVersion));
        return majorVersionFromJavaSpecificationVersion;
    }

    static int majorVersionFromJavaSpecificationVersion() {
        return majorVersion(SystemPropertyUtil.get("java.specification.version", "1.6"));
    }

    static int majorVersion(String str) {
        String[] split = str.split("\\.");
        int[] iArr = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            iArr[i] = Integer.parseInt(split[i]);
        }
        if (iArr[0] == 1) {
            return iArr[1];
        }
        return iArr[0];
    }

    private PlatformDependent0() {
    }
}
