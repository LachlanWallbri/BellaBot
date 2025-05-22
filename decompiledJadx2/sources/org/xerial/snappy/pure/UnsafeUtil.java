package org.xerial.snappy.pure;

import java.lang.reflect.Field;
import java.nio.Buffer;
import org.xerial.snappy.SnappyError;
import org.xerial.snappy.SnappyErrorCode;
import sun.misc.Unsafe;

/* loaded from: classes9.dex */
final class UnsafeUtil {
    private static final Field ADDRESS_ACCESSOR;
    public static final Unsafe UNSAFE;

    private UnsafeUtil() {
    }

    static {
        try {
            Field declaredField = Unsafe.class.getDeclaredField("theUnsafe");
            declaredField.setAccessible(true);
            UNSAFE = (Unsafe) declaredField.get(null);
            try {
                Field declaredField2 = Buffer.class.getDeclaredField("address");
                declaredField2.setAccessible(true);
                ADDRESS_ACCESSOR = declaredField2;
            } catch (Exception unused) {
                throw new SnappyError(SnappyErrorCode.UNSUPPORTED_PLATFORM, "pure-java snappy requires access to java.nio.Buffer raw address field");
            }
        } catch (Exception unused2) {
            throw new SnappyError(SnappyErrorCode.UNSUPPORTED_PLATFORM, "pure-java snappy requires access to sun.misc.Unsafe");
        }
    }

    public static long getAddress(Buffer buffer) {
        try {
            return ((Long) ADDRESS_ACCESSOR.get(buffer)).longValue();
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
