package com.amazonaws.services.p048s3.model.metrics;

import java.io.Serializable;

/* loaded from: classes.dex */
public class MetricsFilter implements Serializable {
    private MetricsFilterPredicate predicate;

    public MetricsFilter() {
    }

    public MetricsFilter(MetricsFilterPredicate metricsFilterPredicate) {
        this.predicate = metricsFilterPredicate;
    }

    public MetricsFilterPredicate getPredicate() {
        return this.predicate;
    }

    public void setPredicate(MetricsFilterPredicate metricsFilterPredicate) {
        this.predicate = metricsFilterPredicate;
    }

    public MetricsFilter withPredicate(MetricsFilterPredicate metricsFilterPredicate) {
        setPredicate(metricsFilterPredicate);
        return this;
    }
}
