package com.pudutech.mirsdkwrap.lib.move.bean;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveCruiseTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\t¢\u0006\u0002\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0013\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\tHÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u001c\b\u0002\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\tHÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0019\u001a\u00020\bHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR%\u0010\u0006\u001a\u0016\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007j\n\u0012\u0004\u0012\u00020\b\u0018\u0001`\t¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveCruiseTask;", "", "id", "", "moveMode", "Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveMode;", "stayPoint", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "(ILcom/pudutech/mirsdkwrap/lib/move/bean/MoveMode;Ljava/util/ArrayList;)V", "getId", "()I", "getMoveMode", "()Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveMode;", "getStayPoint", "()Ljava/util/ArrayList;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MoveCruiseTask {
    private final int id;
    private final MoveMode moveMode;
    private final ArrayList<String> stayPoint;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MoveCruiseTask copy$default(MoveCruiseTask moveCruiseTask, int i, MoveMode moveMode, ArrayList arrayList, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = moveCruiseTask.id;
        }
        if ((i2 & 2) != 0) {
            moveMode = moveCruiseTask.moveMode;
        }
        if ((i2 & 4) != 0) {
            arrayList = moveCruiseTask.stayPoint;
        }
        return moveCruiseTask.copy(i, moveMode, arrayList);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final MoveMode getMoveMode() {
        return this.moveMode;
    }

    public final ArrayList<String> component3() {
        return this.stayPoint;
    }

    public final MoveCruiseTask copy(int id, MoveMode moveMode, ArrayList<String> stayPoint) {
        Intrinsics.checkParameterIsNotNull(moveMode, "moveMode");
        return new MoveCruiseTask(id, moveMode, stayPoint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MoveCruiseTask)) {
            return false;
        }
        MoveCruiseTask moveCruiseTask = (MoveCruiseTask) other;
        return this.id == moveCruiseTask.id && Intrinsics.areEqual(this.moveMode, moveCruiseTask.moveMode) && Intrinsics.areEqual(this.stayPoint, moveCruiseTask.stayPoint);
    }

    public int hashCode() {
        int i = this.id * 31;
        MoveMode moveMode = this.moveMode;
        int hashCode = (i + (moveMode != null ? moveMode.hashCode() : 0)) * 31;
        ArrayList<String> arrayList = this.stayPoint;
        return hashCode + (arrayList != null ? arrayList.hashCode() : 0);
    }

    public String toString() {
        return "MoveCruiseTask(id=" + this.id + ", moveMode=" + this.moveMode + ", stayPoint=" + this.stayPoint + ")";
    }

    public MoveCruiseTask(int i, MoveMode moveMode, ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(moveMode, "moveMode");
        this.id = i;
        this.moveMode = moveMode;
        this.stayPoint = arrayList;
    }

    public final int getId() {
        return this.id;
    }

    public /* synthetic */ MoveCruiseTask(int i, MoveMode moveMode, ArrayList arrayList, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? MoveMode.Normal : moveMode, (i2 & 4) != 0 ? (ArrayList) null : arrayList);
    }

    public final MoveMode getMoveMode() {
        return this.moveMode;
    }

    public final ArrayList<String> getStayPoint() {
        return this.stayPoint;
    }
}
