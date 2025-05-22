package org.apache.commons.compress.compressors.zstandard;

import org.apache.commons.compress.utils.OsgiUtils;
import org.objenesis.instantiator.util.ClassDefinitionUtils;

/* loaded from: classes8.dex */
public class ZstdUtils {
    private static final byte[] ZSTANDARD_FRAME_MAGIC = {40, -75, 47, -3};
    private static final byte[] SKIPPABLE_FRAME_MAGIC = {ClassDefinitionUtils.OPS_aload_0, 77, 24};
    private static volatile CachedAvailability cachedZstdAvailability = CachedAvailability.DONT_CACHE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public enum CachedAvailability {
        DONT_CACHE,
        CACHED_AVAILABLE,
        CACHED_UNAVAILABLE
    }

    static {
        setCacheZstdAvailablity(!OsgiUtils.isRunningInOsgiEnvironment());
    }

    private ZstdUtils() {
    }

    public static boolean isZstdCompressionAvailable() {
        CachedAvailability cachedAvailability = cachedZstdAvailability;
        if (cachedAvailability != CachedAvailability.DONT_CACHE) {
            return cachedAvailability == CachedAvailability.CACHED_AVAILABLE;
        }
        return internalIsZstdCompressionAvailable();
    }

    private static boolean internalIsZstdCompressionAvailable() {
        try {
            Class.forName("com.github.luben.zstd.ZstdInputStream");
            return true;
        } catch (Exception | NoClassDefFoundError unused) {
            return false;
        }
    }

    public static void setCacheZstdAvailablity(boolean z) {
        if (!z) {
            cachedZstdAvailability = CachedAvailability.DONT_CACHE;
        } else if (cachedZstdAvailability == CachedAvailability.DONT_CACHE) {
            cachedZstdAvailability = internalIsZstdCompressionAvailable() ? CachedAvailability.CACHED_AVAILABLE : CachedAvailability.CACHED_UNAVAILABLE;
        }
    }

    public static boolean matches(byte[] bArr, int i) {
        boolean z;
        if (i < ZSTANDARD_FRAME_MAGIC.length) {
            return false;
        }
        int i2 = 0;
        while (true) {
            byte[] bArr2 = ZSTANDARD_FRAME_MAGIC;
            if (i2 >= bArr2.length) {
                z = true;
                break;
            }
            if (bArr[i2] != bArr2[i2]) {
                z = false;
                break;
            }
            i2++;
        }
        if (z) {
            return true;
        }
        if (80 != (bArr[0] & 240)) {
            return false;
        }
        int i3 = 0;
        while (true) {
            byte[] bArr3 = SKIPPABLE_FRAME_MAGIC;
            if (i3 >= bArr3.length) {
                return true;
            }
            int i4 = i3 + 1;
            if (bArr[i4] != bArr3[i3]) {
                return false;
            }
            i3 = i4;
        }
    }

    static CachedAvailability getCachedZstdAvailability() {
        return cachedZstdAvailability;
    }
}
