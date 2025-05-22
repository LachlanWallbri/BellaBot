package io.grpc.netty.shaded.io.netty.util.internal.shaded.org.jctools.util;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
public interface PortableJvmInfo {
    public static final int CACHE_LINE_SIZE = Integer.getInteger("jctools.cacheLineSize", 64).intValue();
    public static final int CPUs = Runtime.getRuntime().availableProcessors();
    public static final int RECOMENDED_OFFER_BATCH;
    public static final int RECOMENDED_POLL_BATCH;

    static {
        int i = CPUs;
        RECOMENDED_OFFER_BATCH = i * 4;
        RECOMENDED_POLL_BATCH = i * 4;
    }
}
