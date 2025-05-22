package com.amazonaws.services.p048s3.model.analytics;

/* loaded from: classes.dex */
public final class AnalyticsPrefixPredicate extends AnalyticsFilterPredicate {
    private final String prefix;

    public AnalyticsPrefixPredicate(String str) {
        this.prefix = str;
    }

    public String getPrefix() {
        return this.prefix;
    }

    @Override // com.amazonaws.services.p048s3.model.analytics.AnalyticsFilterPredicate
    public void accept(AnalyticsPredicateVisitor analyticsPredicateVisitor) {
        analyticsPredicateVisitor.visit(this);
    }
}
