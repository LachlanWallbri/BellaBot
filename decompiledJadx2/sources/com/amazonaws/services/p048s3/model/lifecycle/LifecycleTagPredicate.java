package com.amazonaws.services.p048s3.model.lifecycle;

import com.amazonaws.services.p048s3.model.Tag;

/* loaded from: classes.dex */
public final class LifecycleTagPredicate extends LifecycleFilterPredicate {
    private final Tag tag;

    public LifecycleTagPredicate(Tag tag) {
        this.tag = tag;
    }

    public Tag getTag() {
        return this.tag;
    }

    @Override // com.amazonaws.services.p048s3.model.lifecycle.LifecycleFilterPredicate
    public void accept(LifecyclePredicateVisitor lifecyclePredicateVisitor) {
        lifecyclePredicateVisitor.visit(this);
    }
}
