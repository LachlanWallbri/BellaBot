package io.opencensus.metrics;

import io.opencensus.metrics.MetricOptions;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_MetricOptions extends MetricOptions {
    private final Map<LabelKey, LabelValue> constantLabels;
    private final String description;
    private final List<LabelKey> labelKeys;
    private final String unit;

    private AutoValue_MetricOptions(String str, String str2, List<LabelKey> list, Map<LabelKey, LabelValue> map) {
        this.description = str;
        this.unit = str2;
        this.labelKeys = list;
        this.constantLabels = map;
    }

    @Override // io.opencensus.metrics.MetricOptions
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.metrics.MetricOptions
    public String getUnit() {
        return this.unit;
    }

    @Override // io.opencensus.metrics.MetricOptions
    public List<LabelKey> getLabelKeys() {
        return this.labelKeys;
    }

    @Override // io.opencensus.metrics.MetricOptions
    public Map<LabelKey, LabelValue> getConstantLabels() {
        return this.constantLabels;
    }

    public String toString() {
        return "MetricOptions{description=" + this.description + ", unit=" + this.unit + ", labelKeys=" + this.labelKeys + ", constantLabels=" + this.constantLabels + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MetricOptions)) {
            return false;
        }
        MetricOptions metricOptions = (MetricOptions) obj;
        return this.description.equals(metricOptions.getDescription()) && this.unit.equals(metricOptions.getUnit()) && this.labelKeys.equals(metricOptions.getLabelKeys()) && this.constantLabels.equals(metricOptions.getConstantLabels());
    }

    public int hashCode() {
        return ((((((this.description.hashCode() ^ 1000003) * 1000003) ^ this.unit.hashCode()) * 1000003) ^ this.labelKeys.hashCode()) * 1000003) ^ this.constantLabels.hashCode();
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
     */
    /* loaded from: classes8.dex */
    static final class Builder extends MetricOptions.Builder {
        private Map<LabelKey, LabelValue> constantLabels;
        private String description;
        private List<LabelKey> labelKeys;
        private String unit;

        @Override // io.opencensus.metrics.MetricOptions.Builder
        public MetricOptions.Builder setDescription(String str) {
            if (str == null) {
                throw new NullPointerException("Null description");
            }
            this.description = str;
            return this;
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        public MetricOptions.Builder setUnit(String str) {
            if (str == null) {
                throw new NullPointerException("Null unit");
            }
            this.unit = str;
            return this;
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        public MetricOptions.Builder setLabelKeys(List<LabelKey> list) {
            if (list == null) {
                throw new NullPointerException("Null labelKeys");
            }
            this.labelKeys = list;
            return this;
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        List<LabelKey> getLabelKeys() {
            List<LabelKey> list = this.labelKeys;
            if (list != null) {
                return list;
            }
            throw new IllegalStateException("Property \"labelKeys\" has not been set");
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        public MetricOptions.Builder setConstantLabels(Map<LabelKey, LabelValue> map) {
            if (map == null) {
                throw new NullPointerException("Null constantLabels");
            }
            this.constantLabels = map;
            return this;
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        Map<LabelKey, LabelValue> getConstantLabels() {
            Map<LabelKey, LabelValue> map = this.constantLabels;
            if (map != null) {
                return map;
            }
            throw new IllegalStateException("Property \"constantLabels\" has not been set");
        }

        @Override // io.opencensus.metrics.MetricOptions.Builder
        MetricOptions autoBuild() {
            String str = "";
            if (this.description == null) {
                str = " description";
            }
            if (this.unit == null) {
                str = str + " unit";
            }
            if (this.labelKeys == null) {
                str = str + " labelKeys";
            }
            if (this.constantLabels == null) {
                str = str + " constantLabels";
            }
            if (!str.isEmpty()) {
                throw new IllegalStateException("Missing required properties:" + str);
            }
            return new AutoValue_MetricOptions(this.description, this.unit, this.labelKeys, this.constantLabels);
        }
    }
}
