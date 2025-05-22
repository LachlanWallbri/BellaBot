package io.opencensus.metrics;

import io.opencensus.internal.Utils;
import io.opencensus.metrics.AutoValue_MetricOptions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public abstract class MetricOptions {
    public abstract Map<LabelKey, LabelValue> getConstantLabels();

    public abstract String getDescription();

    public abstract List<LabelKey> getLabelKeys();

    public abstract String getUnit();

    public static Builder builder() {
        return new AutoValue_MetricOptions.Builder().setDescription("").setUnit("1").setLabelKeys(Collections.emptyList()).setConstantLabels(Collections.emptyMap());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    public static abstract class Builder {
        abstract MetricOptions autoBuild();

        abstract Map<LabelKey, LabelValue> getConstantLabels();

        abstract List<LabelKey> getLabelKeys();

        public abstract Builder setConstantLabels(Map<LabelKey, LabelValue> map);

        public abstract Builder setDescription(String str);

        public abstract Builder setLabelKeys(List<LabelKey> list);

        public abstract Builder setUnit(String str);

        public MetricOptions build() {
            setLabelKeys(Collections.unmodifiableList(new ArrayList(getLabelKeys())));
            setConstantLabels(Collections.unmodifiableMap(new LinkedHashMap(getConstantLabels())));
            MetricOptions autoBuild = autoBuild();
            Utils.checkListElementNotNull(autoBuild.getLabelKeys(), "labelKeys elements");
            Utils.checkMapElementNotNull(autoBuild.getConstantLabels(), "constantLabels elements");
            HashSet hashSet = new HashSet();
            for (LabelKey labelKey : autoBuild.getLabelKeys()) {
                if (hashSet.contains(labelKey.getKey())) {
                    throw new IllegalArgumentException("Invalid LabelKey in labelKeys");
                }
                hashSet.add(labelKey.getKey());
            }
            for (Map.Entry<LabelKey, LabelValue> entry : autoBuild.getConstantLabels().entrySet()) {
                if (hashSet.contains(entry.getKey().getKey())) {
                    throw new IllegalArgumentException("Invalid LabelKey in constantLabels");
                }
                hashSet.add(entry.getKey().getKey());
            }
            return autoBuild;
        }
    }
}
