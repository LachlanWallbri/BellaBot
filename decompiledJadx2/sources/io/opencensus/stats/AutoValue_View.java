package io.opencensus.stats;

import io.opencensus.stats.View;
import io.opencensus.tags.TagKey;
import java.util.List;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_View extends View {
    private final Aggregation aggregation;
    private final List<TagKey> columns;
    private final String description;
    private final Measure measure;
    private final View.Name name;
    private final View.AggregationWindow window;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_View(View.Name name, String str, Measure measure, Aggregation aggregation, List<TagKey> list, View.AggregationWindow aggregationWindow) {
        if (name == null) {
            throw new NullPointerException("Null name");
        }
        this.name = name;
        if (str == null) {
            throw new NullPointerException("Null description");
        }
        this.description = str;
        if (measure == null) {
            throw new NullPointerException("Null measure");
        }
        this.measure = measure;
        if (aggregation == null) {
            throw new NullPointerException("Null aggregation");
        }
        this.aggregation = aggregation;
        if (list == null) {
            throw new NullPointerException("Null columns");
        }
        this.columns = list;
        if (aggregationWindow == null) {
            throw new NullPointerException("Null window");
        }
        this.window = aggregationWindow;
    }

    @Override // io.opencensus.stats.View
    public View.Name getName() {
        return this.name;
    }

    @Override // io.opencensus.stats.View
    public String getDescription() {
        return this.description;
    }

    @Override // io.opencensus.stats.View
    public Measure getMeasure() {
        return this.measure;
    }

    @Override // io.opencensus.stats.View
    public Aggregation getAggregation() {
        return this.aggregation;
    }

    @Override // io.opencensus.stats.View
    public List<TagKey> getColumns() {
        return this.columns;
    }

    @Override // io.opencensus.stats.View
    @Deprecated
    public View.AggregationWindow getWindow() {
        return this.window;
    }

    public String toString() {
        return "View{name=" + this.name + ", description=" + this.description + ", measure=" + this.measure + ", aggregation=" + this.aggregation + ", columns=" + this.columns + ", window=" + this.window + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof View)) {
            return false;
        }
        View view = (View) obj;
        return this.name.equals(view.getName()) && this.description.equals(view.getDescription()) && this.measure.equals(view.getMeasure()) && this.aggregation.equals(view.getAggregation()) && this.columns.equals(view.getColumns()) && this.window.equals(view.getWindow());
    }

    public int hashCode() {
        return ((((((((((this.name.hashCode() ^ 1000003) * 1000003) ^ this.description.hashCode()) * 1000003) ^ this.measure.hashCode()) * 1000003) ^ this.aggregation.hashCode()) * 1000003) ^ this.columns.hashCode()) * 1000003) ^ this.window.hashCode();
    }
}
