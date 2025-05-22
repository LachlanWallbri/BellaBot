package com.amazonaws.services.p048s3.model.analytics;

import java.util.List;

/* loaded from: classes.dex */
abstract class AnalyticsNAryOperator extends AnalyticsFilterPredicate {
    private final List<AnalyticsFilterPredicate> operands;

    public AnalyticsNAryOperator(List<AnalyticsFilterPredicate> list) {
        this.operands = list;
    }

    public List<AnalyticsFilterPredicate> getOperands() {
        return this.operands;
    }
}
