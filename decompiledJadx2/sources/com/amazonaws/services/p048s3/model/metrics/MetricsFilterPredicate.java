package com.amazonaws.services.p048s3.model.metrics;

import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class MetricsFilterPredicate implements Serializable {
    public abstract void accept(MetricsPredicateVisitor metricsPredicateVisitor);
}
