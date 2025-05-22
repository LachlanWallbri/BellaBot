package com.pudutech.mqtt.component.client.utils;

import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  classes5.dex
 */
/* loaded from: classes6.dex */
public class UniqueIdInteger implements Serializable {
    private static final long serialVersionUID = 1;
    private int value = 1;

    private int addInc() {
        this.value++;
        if (this.value > 65535) {
            this.value = 1;
        }
        return this.value;
    }

    /* renamed from: id */
    public int m3301id() {
        return this.value;
    }
}
