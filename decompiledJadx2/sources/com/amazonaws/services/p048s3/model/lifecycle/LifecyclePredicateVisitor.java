package com.amazonaws.services.p048s3.model.lifecycle;

/* loaded from: classes.dex */
public interface LifecyclePredicateVisitor {
    void visit(LifecycleAndOperator lifecycleAndOperator);

    void visit(LifecyclePrefixPredicate lifecyclePrefixPredicate);

    void visit(LifecycleTagPredicate lifecycleTagPredicate);
}
