package com.pudutech.peanut.presenter.delivery_task;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: DeliveryMode.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0007\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001f\u0010\u0007\u001a\u00020\b2\u0012\u0010\t\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\n\"\u00020\u0000¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010¨\u0006\u0011"}, m3961d2 = {"Lcom/pudutech/peanut/presenter/delivery_task/DeliveryMode;", "", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "isOneOf", "", "mode", "", "([Lcom/pudutech/peanut/presenter/delivery_task/DeliveryMode;)Z", "GENERAL", "DIRECT", "BIRTHDAY", "SPECIAL", "CALL_DIRECT", "presenter_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public enum DeliveryMode {
    GENERAL(1),
    DIRECT(4),
    BIRTHDAY(5),
    SPECIAL(6),
    CALL_DIRECT(7);

    private final int value;

    DeliveryMode(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    public final boolean isOneOf(DeliveryMode... mode) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        return ArraysKt.contains(mode, this);
    }
}
