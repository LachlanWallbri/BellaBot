package io.opencensus.metrics.export;

import io.opencensus.internal.Utils;
import io.opencensus.metrics.LabelKey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class MetricDescriptor {

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public enum Type {
        GAUGE_INT64,
        GAUGE_DOUBLE,
        GAUGE_DISTRIBUTION,
        CUMULATIVE_INT64,
        CUMULATIVE_DOUBLE,
        CUMULATIVE_DISTRIBUTION,
        SUMMARY
    }

    public abstract String getDescription();

    public abstract List<LabelKey> getLabelKeys();

    public abstract String getName();

    public abstract Type getType();

    public abstract String getUnit();

    public static MetricDescriptor create(String str, String str2, String str3, Type type, List<LabelKey> list) {
        Utils.checkListElementNotNull((List) Utils.checkNotNull(list, "labelKeys"), "labelKey");
        return new AutoValue_MetricDescriptor(str, str2, str3, type, Collections.unmodifiableList(new ArrayList(list)));
    }
}
