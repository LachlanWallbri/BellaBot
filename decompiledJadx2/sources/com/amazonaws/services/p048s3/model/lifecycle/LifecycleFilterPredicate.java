package com.amazonaws.services.p048s3.model.lifecycle;

import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class LifecycleFilterPredicate implements Serializable {
    public abstract void accept(LifecyclePredicateVisitor lifecyclePredicateVisitor);
}
