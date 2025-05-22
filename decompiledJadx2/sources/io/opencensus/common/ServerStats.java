package io.opencensus.common;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class ServerStats {
    public abstract long getLbLatencyNs();

    public abstract long getServiceLatencyNs();

    public abstract byte getTraceOption();

    public static ServerStats create(long j, long j2, byte b) {
        if (j < 0) {
            throw new IllegalArgumentException("'getLbLatencyNs' is less than zero: " + j);
        }
        if (j2 < 0) {
            throw new IllegalArgumentException("'getServiceLatencyNs' is less than zero: " + j2);
        }
        return new AutoValue_ServerStats(j, j2, b);
    }
}
