package com.amazonaws.services.p048s3.model.inventory;

/* loaded from: classes.dex */
public enum InventoryFrequency {
    Daily("Daily"),
    Weekly("Weekly");

    private final String frequency;

    InventoryFrequency(String str) {
        this.frequency = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.frequency;
    }
}
