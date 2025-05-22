package com.amazonaws.services.p048s3.model.inventory;

import java.io.Serializable;

/* loaded from: classes.dex */
public class InventorySchedule implements Serializable {
    private String frequency;

    public String getFrequency() {
        return this.frequency;
    }

    public void setFrequency(String str) {
        this.frequency = str;
    }

    public void setFrequency(InventoryFrequency inventoryFrequency) {
        setFrequency(inventoryFrequency == null ? (String) null : inventoryFrequency.toString());
    }

    public InventorySchedule withFrequency(String str) {
        setFrequency(str);
        return this;
    }

    public InventorySchedule withFrequency(InventoryFrequency inventoryFrequency) {
        setFrequency(inventoryFrequency);
        return this;
    }
}
