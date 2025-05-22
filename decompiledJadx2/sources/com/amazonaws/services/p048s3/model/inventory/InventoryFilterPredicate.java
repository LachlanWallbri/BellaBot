package com.amazonaws.services.p048s3.model.inventory;

import java.io.Serializable;

/* loaded from: classes.dex */
public abstract class InventoryFilterPredicate implements Serializable {
    public abstract void accept(InventoryPredicateVisitor inventoryPredicateVisitor);
}
