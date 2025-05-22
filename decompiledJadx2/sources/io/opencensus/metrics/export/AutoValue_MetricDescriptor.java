package io.opencensus.metrics.export;

import io.opencensus.metrics.LabelKey;
import io.opencensus.metrics.export.MetricDescriptor;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
final class AutoValue_MetricDescriptor extends MetricDescriptor {
    private final String description;
    private final List<LabelKey> labelKeys;
    private final String name;
    private final MetricDescriptor.Type type;
    private final String unit;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_MetricDescriptor(String str, String str2, String str3, MetricDescriptor.Type type, List<LabelKey> list) {
        if (str == null) {
            throw new NullPointerException("Null name");
        }
        this.name = str;
        if (str2 == null) {
            throw new NullPointerException("Null description");
        }
        this.description = str2;
        if (str3 == null) {
            throw new NullPointerException("Null unit");
        }
        this.unit = str3;
        if (type == null) {
            throw new NullPointerException("Null type");
        }
        this.type = type;
        if (list == null) {
            throw new NullPointerException("Null labelKeys");
        }
        this.labelKeys = list;
    }

    @Override // io.opencensus.metrics.export.MetricDescriptor
    public String getName() {
        return this.name;
    }

    @Override // io.opencensus.metrics.export.MetricDescriptor
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.metrics.export.MetricDescriptor
    public String getUnit() {
        return this.unit;
    }

    @Override // io.opencensus.metrics.export.MetricDescriptor
    public MetricDescriptor.Type getType() {
        return this.type;
    }

    @Override // io.opencensus.metrics.export.MetricDescriptor
    public List<LabelKey> getLabelKeys() {
        return this.labelKeys;
    }

    public String toString() {
        return "MetricDescriptor{name=" + this.name + ", description=" + this.description + ", unit=" + this.unit + ", type=" + this.type + ", labelKeys=" + this.labelKeys + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof MetricDescriptor)) {
            return false;
        }
        MetricDescriptor metricDescriptor = (MetricDescriptor) obj;
        return this.name.equals(metricDescriptor.getName()) && this.description.equals(metricDescriptor.getDescription()) && this.unit.equals(metricDescriptor.getUnit()) && this.type.equals(metricDescriptor.getType()) && this.labelKeys.equals(metricDescriptor.getLabelKeys());
    }

    public int hashCode() {
        return ((((((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode()) * 1000003) ^ this.unit.hashCode()) * 1000003) ^ this.type.hashCode()) * 1000003) ^ this.labelKeys.hashCode();
    }
}
