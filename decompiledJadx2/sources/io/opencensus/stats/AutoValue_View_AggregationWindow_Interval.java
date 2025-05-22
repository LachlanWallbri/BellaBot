package io.opencensus.stats;

import io.opencensus.common.Duration;
import io.opencensus.stats.View;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
@Deprecated
/* loaded from: classes8.dex */
final class AutoValue_View_AggregationWindow_Interval extends View.AggregationWindow.Interval {
    private final Duration duration;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_View_AggregationWindow_Interval(Duration duration) {
        if (duration == null) {
            throw new NullPointerException("Null duration");
        }
        this.duration = duration;
    }

    @Override // io.opencensus.stats.View.AggregationWindow.Interval
    public Duration getDuration() {
        return this.duration;
    }

    public String toString() {
        return "Interval{duration=" + this.duration + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof View.AggregationWindow.Interval) {
            return this.duration.equals(((View.AggregationWindow.Interval) obj).getDuration());
        }
        return false;
    }

    public int hashCode() {
        return this.duration.hashCode() ^ 1000003;
    }
}
