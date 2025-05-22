package com.amazonaws.services.p048s3.model.analytics;

import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class AnalyticsFilterPredicate implements Serializable {
    public abstract void accept(AnalyticsPredicateVisitor analyticsPredicateVisitor);
}
