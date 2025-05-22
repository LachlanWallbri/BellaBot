package org.xerial.snappy;

import java.io.IOException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.Checksum;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes9.dex */
final class SnappyFramed {
    private static final Supplier<Checksum> CHECKSUM_SUPPLIER;
    public static final int COMPRESSED_DATA_FLAG = 0;
    public static final byte[] HEADER_BYTES;
    private static final int MASK_DELTA = -1568478504;
    public static final int STREAM_IDENTIFIER_FLAG = 255;
    public static final int UNCOMPRESSED_DATA_FLAG = 1;

    public static int mask(int i) {
        return ((i << 17) | (i >>> 15)) + MASK_DELTA;
    }

    SnappyFramed() {
    }

    static {
        Supplier<Checksum> supplier;
        try {
            final MethodHandle asType = MethodHandles.publicLookup().findConstructor(Class.forName("java.util.zip.CRC32C"), MethodType.methodType(Void.TYPE)).asType(MethodType.methodType(Checksum.class));
            supplier = new Supplier() { // from class: org.xerial.snappy.-$$Lambda$SnappyFramed$uXCTwaPdk5pXK91FZDGeVIzVclY
                @Override // java.util.function.Supplier
                public final Object get() {
                    return SnappyFramed.lambda$static$0(asType);
                }
            };
        } catch (Throwable th) {
            Logger.getLogger(SnappyFramed.class.getName()).log(Level.FINE, "java.util.zip.CRC32C not loaded, using PureJavaCrc32C", th);
            supplier = null;
        }
        if (supplier == null) {
            supplier = new Supplier() { // from class: org.xerial.snappy.-$$Lambda$KXz_ih7YrRYM3cbYgb3PakvajAU
                @Override // java.util.function.Supplier
                public final Object get() {
                    return new PureJavaCrc32C();
                }
            };
        }
        CHECKSUM_SUPPLIER = supplier;
        HEADER_BYTES = new byte[]{-1, 6, 0, 0, 115, 78, 97, 80, 112, ClassDefinitionUtils.OPS_dup};
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Checksum lambda$static$0(MethodHandle methodHandle) {
        try {
            return (Checksum) methodHandle.invokeExact();
        } catch (Throwable th) {
            throw new IllegalStateException(th);
        }
    }

    public static Checksum getCRC32C() {
        return CHECKSUM_SUPPLIER.get();
    }

    public static int maskedCrc32c(Checksum checksum, byte[] bArr, int i, int i2) {
        checksum.reset();
        checksum.update(bArr, i, i2);
        return mask((int) checksum.getValue());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final int readBytes(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer) throws IOException {
        int i;
        int remaining = byteBuffer.remaining();
        int read = readableByteChannel.read(byteBuffer);
        if (read < remaining) {
            i = read;
            while (byteBuffer.remaining() != 0 && read != -1) {
                read = readableByteChannel.read(byteBuffer);
                if (read != -1) {
                    i += read;
                }
            }
        } else {
            i = read;
        }
        if (i > 0) {
            byteBuffer.limit(byteBuffer.position());
        } else {
            byteBuffer.position(byteBuffer.limit());
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int skip(ReadableByteChannel readableByteChannel, int i, ByteBuffer byteBuffer) throws IOException {
        if (i <= 0) {
            return 0;
        }
        int i2 = 0;
        int i3 = i;
        while (i3 > 0 && i2 != -1) {
            byteBuffer.clear();
            if (i3 < byteBuffer.capacity()) {
                byteBuffer.limit(i3);
            }
            i2 = readableByteChannel.read(byteBuffer);
            if (i2 > 0) {
                i3 -= i2;
            }
        }
        byteBuffer.clear();
        return i - i3;
    }
}
