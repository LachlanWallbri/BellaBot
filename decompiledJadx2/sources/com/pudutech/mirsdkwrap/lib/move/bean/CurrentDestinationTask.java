package com.pudutech.mirsdkwrap.lib.move.bean;

import com.pudutech.mirsdk.mircore.coreparcel.DestinationWithAccRange;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CurrentDestinationTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/CurrentDestinationTask;", "", "dwr", "Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "task", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveDestinationTask;", "(Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveDestinationTask;)V", "getDwr", "()Lcom/pudutech/mirsdk/mircore/coreparcel/DestinationWithAccRange;", "getTask", "()Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveDestinationTask;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class CurrentDestinationTask {
    private final DestinationWithAccRange dwr;
    private final MoveDestinationTask task;

    public static /* synthetic */ CurrentDestinationTask copy$default(CurrentDestinationTask currentDestinationTask, DestinationWithAccRange destinationWithAccRange, MoveDestinationTask moveDestinationTask, int i, Object obj) {
        if ((i & 1) != 0) {
            destinationWithAccRange = currentDestinationTask.dwr;
        }
        if ((i & 2) != 0) {
            moveDestinationTask = currentDestinationTask.task;
        }
        return currentDestinationTask.copy(destinationWithAccRange, moveDestinationTask);
    }

    /* renamed from: component1, reason: from getter */
    public final DestinationWithAccRange getDwr() {
        return this.dwr;
    }

    /* renamed from: component2, reason: from getter */
    public final MoveDestinationTask getTask() {
        return this.task;
    }

    public final CurrentDestinationTask copy(DestinationWithAccRange dwr, MoveDestinationTask task) {
        Intrinsics.checkParameterIsNotNull(dwr, "dwr");
        Intrinsics.checkParameterIsNotNull(task, "task");
        return new CurrentDestinationTask(dwr, task);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CurrentDestinationTask)) {
            return false;
        }
        CurrentDestinationTask currentDestinationTask = (CurrentDestinationTask) other;
        return Intrinsics.areEqual(this.dwr, currentDestinationTask.dwr) && Intrinsics.areEqual(this.task, currentDestinationTask.task);
    }

    public int hashCode() {
        DestinationWithAccRange destinationWithAccRange = this.dwr;
        int hashCode = (destinationWithAccRange != null ? destinationWithAccRange.hashCode() : 0) * 31;
        MoveDestinationTask moveDestinationTask = this.task;
        return hashCode + (moveDestinationTask != null ? moveDestinationTask.hashCode() : 0);
    }

    public String toString() {
        return "CurrentDestinationTask(dwr=" + this.dwr + ", task=" + this.task + ")";
    }

    public CurrentDestinationTask(DestinationWithAccRange dwr, MoveDestinationTask task) {
        Intrinsics.checkParameterIsNotNull(dwr, "dwr");
        Intrinsics.checkParameterIsNotNull(task, "task");
        this.dwr = dwr;
        this.task = task;
    }

    public final DestinationWithAccRange getDwr() {
        return this.dwr;
    }

    public final MoveDestinationTask getTask() {
        return this.task;
    }
}
