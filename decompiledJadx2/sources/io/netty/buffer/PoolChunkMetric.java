package io.netty.buffer;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes.dex */
public interface PoolChunkMetric {
    int chunkSize();

    int freeBytes();

    int usage();
}
