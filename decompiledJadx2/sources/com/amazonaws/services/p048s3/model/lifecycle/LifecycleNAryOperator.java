package com.amazonaws.services.p048s3.model.lifecycle;

import java.util.List;

/* loaded from: classes.dex */
abstract class LifecycleNAryOperator extends LifecycleFilterPredicate {
    private final List<LifecycleFilterPredicate> operands;

    public LifecycleNAryOperator(List<LifecycleFilterPredicate> list) {
        this.operands = list;
    }

    public List<LifecycleFilterPredicate> getOperands() {
        return this.operands;
    }
}
