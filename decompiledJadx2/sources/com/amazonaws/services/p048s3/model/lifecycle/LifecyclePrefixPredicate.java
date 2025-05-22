package com.amazonaws.services.p048s3.model.lifecycle;

/* loaded from: classes.dex */
public final class LifecyclePrefixPredicate extends LifecycleFilterPredicate {
    private final String prefix;

    public LifecyclePrefixPredicate(String str) {
        this.prefix = str;
    }

    public String getPrefix() {
        return this.prefix;
    }

    @Override // com.amazonaws.services.p048s3.model.lifecycle.LifecycleFilterPredicate
    public void accept(LifecyclePredicateVisitor lifecyclePredicateVisitor) {
        lifecyclePredicateVisitor.visit(this);
    }
}
