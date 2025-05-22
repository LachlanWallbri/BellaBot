package org.apache.commons.compress.compressors.brotli;

import org.apache.commons.compress.utils.OsgiUtils;

/* loaded from: classes8.dex */
public class BrotliUtils {
    private static volatile CachedAvailability cachedBrotliAvailability = CachedAvailability.DONT_CACHE;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes8.dex */
    public enum CachedAvailability {
        DONT_CACHE,
        CACHED_AVAILABLE,
        CACHED_UNAVAILABLE
    }

    static {
        setCacheBrotliAvailablity(!OsgiUtils.isRunningInOsgiEnvironment());
    }

    private BrotliUtils() {
    }

    public static boolean isBrotliCompressionAvailable() {
        CachedAvailability cachedAvailability = cachedBrotliAvailability;
        if (cachedAvailability != CachedAvailability.DONT_CACHE) {
            return cachedAvailability == CachedAvailability.CACHED_AVAILABLE;
        }
        return internalIsBrotliCompressionAvailable();
    }

    private static boolean internalIsBrotliCompressionAvailable() {
        try {
            Class.forName("org.brotli.dec.BrotliInputStream");
            return true;
        } catch (Exception | NoClassDefFoundError unused) {
            return false;
        }
    }

    public static void setCacheBrotliAvailablity(boolean z) {
        if (!z) {
            cachedBrotliAvailability = CachedAvailability.DONT_CACHE;
        } else if (cachedBrotliAvailability == CachedAvailability.DONT_CACHE) {
            cachedBrotliAvailability = internalIsBrotliCompressionAvailable() ? CachedAvailability.CACHED_AVAILABLE : CachedAvailability.CACHED_UNAVAILABLE;
        }
    }

    static CachedAvailability getCachedBrotliAvailability() {
        return cachedBrotliAvailability;
    }
}
