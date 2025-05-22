package com.amazonaws.metrics;

/* loaded from: classes.dex */
public abstract class ByteThroughputProvider {
    private int byteCount;
    private long duration;
    private final ThroughputMetricType throughputType;

    /* JADX INFO: Access modifiers changed from: protected */
    public ByteThroughputProvider(ThroughputMetricType throughputMetricType) {
        this.throughputType = throughputMetricType;
    }

    public ThroughputMetricType getThroughputMetricType() {
        return this.throughputType;
    }

    public int getByteCount() {
        return this.byteCount;
    }

    public long getDurationNano() {
        return this.duration;
    }

    public String getProviderId() {
        return super.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void increment(int i, long j) {
        this.byteCount += i;
        this.duration += System.nanoTime() - j;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void reset() {
        this.byteCount = 0;
        this.duration = 0L;
    }

    public String toString() {
        return String.format("providerId=%s, throughputType=%s, byteCount=%d, duration=%d", getProviderId(), this.throughputType, Integer.valueOf(this.byteCount), Long.valueOf(this.duration));
    }
}
