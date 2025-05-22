package io.opencensus.metrics.export;

import io.opencensus.common.Timestamp;
import io.opencensus.internal.Utils;
import io.opencensus.metrics.LabelValue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class TimeSeries {
    public abstract List<LabelValue> getLabelValues();

    public abstract List<Point> getPoints();

    @Nullable
    public abstract Timestamp getStartTimestamp();

    public static TimeSeries create(List<LabelValue> list, List<Point> list2, @Nullable Timestamp timestamp) {
        Utils.checkListElementNotNull((List) Utils.checkNotNull(list2, "points"), "point");
        return createInternal(list, Collections.unmodifiableList(new ArrayList(list2)), timestamp);
    }

    public static TimeSeries create(List<LabelValue> list) {
        return createInternal(list, Collections.emptyList(), null);
    }

    public static TimeSeries createWithOnePoint(List<LabelValue> list, Point point, @Nullable Timestamp timestamp) {
        Utils.checkNotNull(point, "point");
        return createInternal(list, Collections.singletonList(point), timestamp);
    }

    public TimeSeries setPoint(Point point) {
        Utils.checkNotNull(point, "point");
        return new AutoValue_TimeSeries(getLabelValues(), Collections.singletonList(point), null);
    }

    private static TimeSeries createInternal(List<LabelValue> list, List<Point> list2, @Nullable Timestamp timestamp) {
        Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelValues"), "labelValue");
        return new AutoValue_TimeSeries(Collections.unmodifiableList(new ArrayList(list)), list2, timestamp);
    }
}
