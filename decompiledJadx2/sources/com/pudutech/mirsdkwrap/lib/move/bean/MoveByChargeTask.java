package com.pudutech.mirsdkwrap.lib.move.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByChargeTask.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/MoveByChargeTask;", "", "point", "", "(Ljava/lang/String;)V", "getPoint", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class MoveByChargeTask {
    private final String point;

    public static /* synthetic */ MoveByChargeTask copy$default(MoveByChargeTask moveByChargeTask, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = moveByChargeTask.point;
        }
        return moveByChargeTask.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPoint() {
        return this.point;
    }

    public final MoveByChargeTask copy(String point) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        return new MoveByChargeTask(point);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof MoveByChargeTask) && Intrinsics.areEqual(this.point, ((MoveByChargeTask) other).point);
        }
        return true;
    }

    public int hashCode() {
        String str = this.point;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "MoveByChargeTask(point=" + this.point + ")";
    }

    public MoveByChargeTask(String point) {
        Intrinsics.checkParameterIsNotNull(point, "point");
        this.point = point;
    }

    public final String getPoint() {
        return this.point;
    }
}
