package com.pudutech.mirsdkwrap.lib.move.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByGroupTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByGroupTask;", "", "groupName", "", "moveMode", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveMode;", "(Ljava/lang/String;Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveMode;)V", "getGroupName", "()Ljava/lang/String;", "getMoveMode", "()Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveMode;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MoveByGroupTask {
    private final String groupName;
    private final MoveMode moveMode;

    public static /* synthetic */ MoveByGroupTask copy$default(MoveByGroupTask moveByGroupTask, String str, MoveMode moveMode, int i, Object obj) {
        if ((i & 1) != 0) {
            str = moveByGroupTask.groupName;
        }
        if ((i & 2) != 0) {
            moveMode = moveByGroupTask.moveMode;
        }
        return moveByGroupTask.copy(str, moveMode);
    }

    /* renamed from: component1, reason: from getter */
    public final String getGroupName() {
        return this.groupName;
    }

    /* renamed from: component2, reason: from getter */
    public final MoveMode getMoveMode() {
        return this.moveMode;
    }

    public final MoveByGroupTask copy(String groupName, MoveMode moveMode) {
        Intrinsics.checkParameterIsNotNull(groupName, "groupName");
        Intrinsics.checkParameterIsNotNull(moveMode, "moveMode");
        return new MoveByGroupTask(groupName, moveMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MoveByGroupTask)) {
            return false;
        }
        MoveByGroupTask moveByGroupTask = (MoveByGroupTask) other;
        return Intrinsics.areEqual(this.groupName, moveByGroupTask.groupName) && Intrinsics.areEqual(this.moveMode, moveByGroupTask.moveMode);
    }

    public int hashCode() {
        String str = this.groupName;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        MoveMode moveMode = this.moveMode;
        return hashCode + (moveMode != null ? moveMode.hashCode() : 0);
    }

    public String toString() {
        return "MoveByGroupTask(groupName=" + this.groupName + ", moveMode=" + this.moveMode + ")";
    }

    public MoveByGroupTask(String groupName, MoveMode moveMode) {
        Intrinsics.checkParameterIsNotNull(groupName, "groupName");
        Intrinsics.checkParameterIsNotNull(moveMode, "moveMode");
        this.groupName = groupName;
        this.moveMode = moveMode;
    }

    public final String getGroupName() {
        return this.groupName;
    }

    public /* synthetic */ MoveByGroupTask(String str, MoveMode moveMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? MoveMode.Normal : moveMode);
    }

    public final MoveMode getMoveMode() {
        return this.moveMode;
    }
}
