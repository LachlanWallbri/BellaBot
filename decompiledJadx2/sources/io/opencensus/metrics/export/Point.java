package io.opencensus.metrics.export;

import io.opencensus.common.Timestamp;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Point {
    public abstract Timestamp getTimestamp();

    public abstract Value getValue();

    public static Point create(Value value, Timestamp timestamp) {
        return new AutoValue_Point(value, timestamp);
    }
}
