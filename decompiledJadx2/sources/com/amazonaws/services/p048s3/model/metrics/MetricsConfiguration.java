package com.amazonaws.services.p048s3.model.metrics;

import java.io.Serializable;

/* loaded from: classes.dex */
public class MetricsConfiguration implements Serializable {
    private MetricsFilter filter;

    /* renamed from: id */
    private String f1196id;

    public String getId() {
        return this.f1196id;
    }

    public void setId(String str) {
        this.f1196id = str;
    }

    public MetricsConfiguration withId(String str) {
        setId(str);
        return this;
    }

    public MetricsFilter getFilter() {
        return this.filter;
    }

    public void setFilter(MetricsFilter metricsFilter) {
        this.filter = metricsFilter;
    }

    public MetricsConfiguration withFilter(MetricsFilter metricsFilter) {
        setFilter(metricsFilter);
        return this;
    }
}
