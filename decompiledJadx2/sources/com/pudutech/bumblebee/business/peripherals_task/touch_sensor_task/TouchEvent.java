package com.pudutech.bumblebee.business.peripherals_task.touch_sensor_task;

import kotlin.Metadata;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: TouchEvent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/peripherals_task/touch_sensor_task/TouchEvent;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "HAPPY", "HAPPY_LEVEL2", "HAPPY_LEVEL3", "ANGER", "ANGER_LEVEL2", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public enum TouchEvent {
    HAPPY(1),
    HAPPY_LEVEL2(2),
    HAPPY_LEVEL3(3),
    ANGER(4),
    ANGER_LEVEL2(5);

    private final int value;

    TouchEvent(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }
}
