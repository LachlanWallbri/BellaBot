package com.amazonaws.services.p048s3.model.metrics;

import java.util.List;

/* loaded from: classes.dex */
public final class MetricsAndOperator extends MetricsNAryOperator {
    @Override // com.amazonaws.services.p048s3.model.metrics.MetricsNAryOperator
    public /* bridge */ /* synthetic */ List getOperands() {
        return super.getOperands();
    }

    public MetricsAndOperator(List<MetricsFilterPredicate> list) {
        super(list);
    }

    @Override // com.amazonaws.services.p048s3.model.metrics.MetricsFilterPredicate
    public void accept(MetricsPredicateVisitor metricsPredicateVisitor) {
        metricsPredicateVisitor.visit(this);
    }
}
