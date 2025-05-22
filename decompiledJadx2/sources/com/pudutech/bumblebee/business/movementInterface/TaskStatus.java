package com.pudutech.bumblebee.business.movementInterface;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TaskStatus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\t\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001f\u0010\u0003\u001a\u00020\u00042\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00000\u0006\"\u00020\u0000¢\u0006\u0002\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;", "", "(Ljava/lang/String;I)V", "isOneOf", "", "status", "", "([Lcom/pudutech/bumblebee/business/movementInterface/TaskStatus;)Z", "AWAIT", "ON_THE_WAY", "APPROACHING", "ARRIVAL", "DONE", "CANCEL", "DONE_BEFORE_ARRIVAL", "module_bumblebee_business_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public enum TaskStatus {
    AWAIT,
    ON_THE_WAY,
    APPROACHING,
    ARRIVAL,
    DONE,
    CANCEL,
    DONE_BEFORE_ARRIVAL;

    public final boolean isOneOf(TaskStatus... status) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        return ArraysKt.contains(status, this);
    }
}
