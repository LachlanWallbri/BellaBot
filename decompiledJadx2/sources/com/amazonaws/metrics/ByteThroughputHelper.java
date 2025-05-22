package com.amazonaws.metrics;

import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
class ByteThroughputHelper extends ByteThroughputProvider {
    private static final int REPORT_INTERVAL_SECS = 10;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ByteThroughputHelper(ThroughputMetricType throughputMetricType) {
        super(throughputMetricType);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public long startTiming() {
        if (TimeUnit.NANOSECONDS.toSeconds(getDurationNano()) > 10) {
            reportMetrics();
        }
        return System.nanoTime();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void reportMetrics() {
        if (getByteCount() > 0) {
            AwsSdkMetrics.getServiceMetricCollector().collectByteThroughput(this);
            reset();
        }
    }

    @Override // com.amazonaws.metrics.ByteThroughputProvider
    public void increment(int i, long j) {
        super.increment(i, j);
    }
}
