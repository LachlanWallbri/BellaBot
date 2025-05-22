package io.netty.handler.codec.compression;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public final class ZlibCodecFactory {
    private static final int DEFAULT_JDK_MEM_LEVEL = 8;
    private static final int DEFAULT_JDK_WINDOW_SIZE = 15;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) ZlibCodecFactory.class);
    private static final boolean noJdkZlibDecoder;
    private static final boolean noJdkZlibEncoder;
    private static final boolean supportsWindowSizeAndMemLevel;

    static {
        boolean z = true;
        boolean z2 = SystemPropertyUtil.getBoolean("io.netty.noJdkZlibDecoder", PlatformDependent.javaVersion() < 7);
        noJdkZlibDecoder = z2;
        logger.debug("-Dio.netty.noJdkZlibDecoder: {}", Boolean.valueOf(z2));
        boolean z3 = SystemPropertyUtil.getBoolean("io.netty.noJdkZlibEncoder", false);
        noJdkZlibEncoder = z3;
        logger.debug("-Dio.netty.noJdkZlibEncoder: {}", Boolean.valueOf(z3));
        if (!noJdkZlibDecoder && PlatformDependent.javaVersion() < 7) {
            z = false;
        }
        supportsWindowSizeAndMemLevel = z;
    }

    public static boolean isSupportingWindowSizeAndMemLevel() {
        return supportsWindowSizeAndMemLevel;
    }

    public static ZlibEncoder newZlibEncoder(int i) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibEncoder) {
            return new JZlibEncoder(i);
        }
        return new JdkZlibEncoder(i);
    }

    public static ZlibEncoder newZlibEncoder(ZlibWrapper zlibWrapper) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibEncoder) {
            return new JZlibEncoder(zlibWrapper);
        }
        return new JdkZlibEncoder(zlibWrapper);
    }

    public static ZlibEncoder newZlibEncoder(ZlibWrapper zlibWrapper, int i) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibEncoder) {
            return new JZlibEncoder(zlibWrapper, i);
        }
        return new JdkZlibEncoder(zlibWrapper, i);
    }

    public static ZlibEncoder newZlibEncoder(ZlibWrapper zlibWrapper, int i, int i2, int i3) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibEncoder || i2 != 15 || i3 != 8) {
            return new JZlibEncoder(zlibWrapper, i, i2, i3);
        }
        return new JdkZlibEncoder(zlibWrapper, i);
    }

    public static ZlibEncoder newZlibEncoder(byte[] bArr) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibEncoder) {
            return new JZlibEncoder(bArr);
        }
        return new JdkZlibEncoder(bArr);
    }

    public static ZlibEncoder newZlibEncoder(int i, byte[] bArr) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibEncoder) {
            return new JZlibEncoder(i, bArr);
        }
        return new JdkZlibEncoder(i, bArr);
    }

    public static ZlibEncoder newZlibEncoder(int i, int i2, int i3, byte[] bArr) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibEncoder || i2 != 15 || i3 != 8) {
            return new JZlibEncoder(i, i2, i3, bArr);
        }
        return new JdkZlibEncoder(i, bArr);
    }

    public static ZlibDecoder newZlibDecoder() {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibDecoder) {
            return new JZlibDecoder();
        }
        return new JdkZlibDecoder(true);
    }

    public static ZlibDecoder newZlibDecoder(ZlibWrapper zlibWrapper) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibDecoder) {
            return new JZlibDecoder(zlibWrapper);
        }
        return new JdkZlibDecoder(zlibWrapper, true);
    }

    public static ZlibDecoder newZlibDecoder(byte[] bArr) {
        if (PlatformDependent.javaVersion() < 7 || noJdkZlibDecoder) {
            return new JZlibDecoder(bArr);
        }
        return new JdkZlibDecoder(bArr);
    }

    private ZlibCodecFactory() {
    }
}
