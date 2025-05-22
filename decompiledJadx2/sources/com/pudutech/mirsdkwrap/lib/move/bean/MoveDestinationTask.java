package com.pudutech.mirsdkwrap.lib.move.bean;

import com.pudutech.mirsdkwrap.lib.map.Destination;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByDestinationTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveDestinationTask;", "", "destination", "Lcom/pudutech/mirsdkwrap/lib/map/Destination;", "moveMode", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveMode;", "(Lcom/pudutech/mirsdkwrap/lib/map/Destination;Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveMode;)V", "getDestination", "()Lcom/pudutech/mirsdkwrap/lib/map/Destination;", "getMoveMode", "()Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveMode;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MoveDestinationTask {
    private final Destination destination;
    private final MoveMode moveMode;

    public static /* synthetic */ MoveDestinationTask copy$default(MoveDestinationTask moveDestinationTask, Destination destination, MoveMode moveMode, int i, Object obj) {
        if ((i & 1) != 0) {
            destination = moveDestinationTask.destination;
        }
        if ((i & 2) != 0) {
            moveMode = moveDestinationTask.moveMode;
        }
        return moveDestinationTask.copy(destination, moveMode);
    }

    /* renamed from: component1, reason: from getter */
    public final Destination getDestination() {
        return this.destination;
    }

    /* renamed from: component2, reason: from getter */
    public final MoveMode getMoveMode() {
        return this.moveMode;
    }

    public final MoveDestinationTask copy(Destination destination, MoveMode moveMode) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(moveMode, "moveMode");
        return new MoveDestinationTask(destination, moveMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MoveDestinationTask)) {
            return false;
        }
        MoveDestinationTask moveDestinationTask = (MoveDestinationTask) other;
        return Intrinsics.areEqual(this.destination, moveDestinationTask.destination) && Intrinsics.areEqual(this.moveMode, moveDestinationTask.moveMode);
    }

    public int hashCode() {
        Destination destination = this.destination;
        int hashCode = (destination != null ? destination.hashCode() : 0) * 31;
        MoveMode moveMode = this.moveMode;
        return hashCode + (moveMode != null ? moveMode.hashCode() : 0);
    }

    public String toString() {
        return "MoveDestinationTask(destination=" + this.destination + ", moveMode=" + this.moveMode + ")";
    }

    public MoveDestinationTask(Destination destination, MoveMode moveMode) {
        Intrinsics.checkParameterIsNotNull(destination, "destination");
        Intrinsics.checkParameterIsNotNull(moveMode, "moveMode");
        this.destination = destination;
        this.moveMode = moveMode;
    }

    public final Destination getDestination() {
        return this.destination;
    }

    public /* synthetic */ MoveDestinationTask(Destination destination, MoveMode moveMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(destination, (i & 2) != 0 ? MoveMode.Normal : moveMode);
    }

    public final MoveMode getMoveMode() {
        return this.moveMode;
    }
}
