package io.opencensus.metrics.export;

import io.opencensus.internal.Utils;
import io.opencensus.metrics.export.MetricDescriptor;
import io.opencensus.metrics.export.Value;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class Metric {
    public abstract MetricDescriptor getMetricDescriptor();

    public abstract List<TimeSeries> getTimeSeriesList();

    public static Metric create(MetricDescriptor metricDescriptor, List<TimeSeries> list) {
        Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "timeSeriesList"), "timeSeries");
        return createInternal(metricDescriptor, Collections.unmodifiableList(new ArrayList(list)));
    }

    public static Metric createWithOneTimeSeries(MetricDescriptor metricDescriptor, TimeSeries timeSeries) {
        return createInternal(metricDescriptor, Collections.singletonList(Utils.checkNotNull(timeSeries, "timeSeries")));
    }

    private static Metric createInternal(MetricDescriptor metricDescriptor, List<TimeSeries> list) {
        Utils.checkNotNull(metricDescriptor, "metricDescriptor");
        checkTypeMatch(metricDescriptor.getType(), list);
        return new AutoValue_Metric(metricDescriptor, list);
    }

    private static void checkTypeMatch(MetricDescriptor.Type type, List<TimeSeries> list) {
        Iterator<TimeSeries> it = list.iterator();
        while (it.hasNext()) {
            Iterator<Point> it2 = it.next().getPoints().iterator();
            while (it2.hasNext()) {
                Value value = it2.next().getValue();
                String simpleName = value.getClass().getSuperclass() != null ? value.getClass().getSuperclass().getSimpleName() : "";
                switch (type) {
                    case GAUGE_INT64:
                    case CUMULATIVE_INT64:
                        Utils.checkArgument(value instanceof Value.ValueLong, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                    case CUMULATIVE_DOUBLE:
                    case GAUGE_DOUBLE:
                        Utils.checkArgument(value instanceof Value.ValueDouble, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                    case GAUGE_DISTRIBUTION:
                    case CUMULATIVE_DISTRIBUTION:
                        Utils.checkArgument(value instanceof Value.ValueDistribution, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                    case SUMMARY:
                        Utils.checkArgument(value instanceof Value.ValueSummary, "Type mismatch: %s, %s.", type, simpleName);
                        break;
                }
            }
        }
    }
}
