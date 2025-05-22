package io.opencensus.metrics.export;

import io.opencensus.internal.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Summary {
    @Nullable
    public abstract Long getCount();

    public abstract Snapshot getSnapshot();

    @Nullable
    public abstract Double getSum();

    public static Summary create(@Nullable Long l, @Nullable Double d, Snapshot snapshot) {
        checkCountAndSum(l, d);
        Utils.checkNotNull(snapshot, "snapshot");
        return new AutoValue_Summary(l, d, snapshot);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Snapshot {
        @Nullable
        public abstract Long getCount();

        @Nullable
        public abstract Double getSum();

        public abstract List<ValueAtPercentile> getValueAtPercentiles();

        public static Snapshot create(@Nullable Long l, @Nullable Double d, List<ValueAtPercentile> list) {
            Summary.checkCountAndSum(l, d);
            Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "valueAtPercentiles"), "valueAtPercentile");
            return new AutoValue_Summary_Snapshot(l, d, Collections.unmodifiableList(new ArrayList(list)));
        }

        /* JADX WARN: Classes with same name are omitted:
          classes6.dex
         */
        /* loaded from: classes8.dex */
        public static abstract class ValueAtPercentile {
            public abstract double getPercentile();

            public abstract double getValue();

            public static ValueAtPercentile create(double d, double d2) {
                Utils.checkArgument(0.0d < d && d <= 100.0d, "percentile must be in the interval (0.0, 100.0]");
                Utils.checkArgument(d2 >= 0.0d, "value must be non-negative");
                return new AutoValue_Summary_Snapshot_ValueAtPercentile(d, d2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void checkCountAndSum(@Nullable Long l, @Nullable Double d) {
        Utils.checkArgument(l == null || l.longValue() >= 0, "count must be non-negative.");
        Utils.checkArgument(d == null || d.doubleValue() >= 0.0d, "sum must be non-negative.");
        if (l == null || l.longValue() != 0) {
            return;
        }
        Utils.checkArgument(d == null || d.doubleValue() == 0.0d, "sum must be 0 if count is 0.");
    }
}
