package com.pudutech.robot.peripherals.hola;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: LoRaLevel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/hola/LoRaLevel;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "High", "Low", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum LoRaLevel {
    High("0"),
    Low("1");

    private final String value;

    LoRaLevel(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }
}
