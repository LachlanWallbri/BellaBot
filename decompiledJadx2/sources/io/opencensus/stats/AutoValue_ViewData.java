package io.opencensus.stats;

import io.opencensus.common.Timestamp;
import io.opencensus.stats.ViewData;
import io.opencensus.tags.TagValue;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes8.dex */
public final class AutoValue_ViewData extends ViewData {
    private final Map<List<TagValue>, AggregationData> aggregationMap;
    private final Timestamp end;
    private final Timestamp start;
    private final View view;
    private final ViewData.AggregationWindowData windowData;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AutoValue_ViewData(View view, Map<List<TagValue>, AggregationData> map, ViewData.AggregationWindowData aggregationWindowData, Timestamp timestamp, Timestamp timestamp2) {
        if (view == null) {
            throw new NullPointerException("Null view");
        }
        this.view = view;
        if (map == null) {
            throw new NullPointerException("Null aggregationMap");
        }
        this.aggregationMap = map;
        if (aggregationWindowData == null) {
            throw new NullPointerException("Null windowData");
        }
        this.windowData = aggregationWindowData;
        if (timestamp == null) {
            throw new NullPointerException("Null start");
        }
        this.start = timestamp;
        if (timestamp2 == null) {
            throw new NullPointerException("Null end");
        }
        this.end = timestamp2;
    }

    @Override // io.opencensus.stats.ViewData
    public View getView() {
        return this.view;
    }

    @Override // io.opencensus.stats.ViewData
    public Map<List<TagValue>, AggregationData> getAggregationMap() {
        return this.aggregationMap;
    }

    @Override // io.opencensus.stats.ViewData
    @Deprecated
    public ViewData.AggregationWindowData getWindowData() {
        return this.windowData;
    }

    @Override // io.opencensus.stats.ViewData
    public Timestamp getStart() {
        return this.start;
    }

    @Override // io.opencensus.stats.ViewData
    public Timestamp getEnd() {
        return this.end;
    }

    public String toString() {
        return "ViewData{view=" + this.view + ", aggregationMap=" + this.aggregationMap + ", windowData=" + this.windowData + ", start=" + this.start + ", end=" + this.end + "}";
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ViewData)) {
            return false;
        }
        ViewData viewData = (ViewData) obj;
        return this.view.equals(viewData.getView()) && this.aggregationMap.equals(viewData.getAggregationMap()) && this.windowData.equals(viewData.getWindowData()) && this.start.equals(viewData.getStart()) && this.end.equals(viewData.getEnd());
    }

    public int hashCode() {
        return ((((((((this.view.hashCode() ^ 1000003) * 1000003) ^ this.aggregationMap.hashCode()) * 1000003) ^ this.windowData.hashCode()) * 1000003) ^ this.start.hashCode()) * 1000003) ^ this.end.hashCode();
    }
}
