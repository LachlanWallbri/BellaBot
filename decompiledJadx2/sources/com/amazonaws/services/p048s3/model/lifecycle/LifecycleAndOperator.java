package com.amazonaws.services.p048s3.model.lifecycle;

import java.util.List;

/* loaded from: classes.dex */
public final class LifecycleAndOperator extends LifecycleNAryOperator {
    @Override // com.amazonaws.services.p048s3.model.lifecycle.LifecycleNAryOperator
    public /* bridge */ /* synthetic */ List getOperands() {
        return super.getOperands();
    }

    public LifecycleAndOperator(List<LifecycleFilterPredicate> list) {
        super(list);
    }

    @Override // com.amazonaws.services.p048s3.model.lifecycle.LifecycleFilterPredicate
    public void accept(LifecyclePredicateVisitor lifecyclePredicateVisitor) {
        lifecyclePredicateVisitor.visit(this);
    }
}
