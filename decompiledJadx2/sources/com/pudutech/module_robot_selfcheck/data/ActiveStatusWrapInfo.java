package com.pudutech.module_robot_selfcheck.data;

import com.pudutech.module_robot_selfcheck.enums.ActiveStatus;
import com.pudutech.module_robot_selfcheck.enums.InactiveType;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ActiveStatusWrapInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\b\u0010\u0013\u001a\u00020\u0014H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/module_robot_selfcheck/data/ActiveStatusWrapInfo;", "", "activeState", "Lcom/pudutech/module_robot_selfcheck/enums/ActiveStatus;", "inactiveType", "Lcom/pudutech/module_robot_selfcheck/enums/InactiveType;", "(Lcom/pudutech/module_robot_selfcheck/enums/ActiveStatus;Lcom/pudutech/module_robot_selfcheck/enums/InactiveType;)V", "getActiveState", "()Lcom/pudutech/module_robot_selfcheck/enums/ActiveStatus;", "getInactiveType", "()Lcom/pudutech/module_robot_selfcheck/enums/InactiveType;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "module_robot_selfcheck_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class ActiveStatusWrapInfo {
    private final ActiveStatus activeState;
    private final InactiveType inactiveType;

    public static /* synthetic */ ActiveStatusWrapInfo copy$default(ActiveStatusWrapInfo activeStatusWrapInfo, ActiveStatus activeStatus, InactiveType inactiveType, int i, Object obj) {
        if ((i & 1) != 0) {
            activeStatus = activeStatusWrapInfo.activeState;
        }
        if ((i & 2) != 0) {
            inactiveType = activeStatusWrapInfo.inactiveType;
        }
        return activeStatusWrapInfo.copy(activeStatus, inactiveType);
    }

    /* renamed from: component1, reason: from getter */
    public final ActiveStatus getActiveState() {
        return this.activeState;
    }

    /* renamed from: component2, reason: from getter */
    public final InactiveType getInactiveType() {
        return this.inactiveType;
    }

    public final ActiveStatusWrapInfo copy(ActiveStatus activeState, InactiveType inactiveType) {
        Intrinsics.checkParameterIsNotNull(activeState, "activeState");
        return new ActiveStatusWrapInfo(activeState, inactiveType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ActiveStatusWrapInfo)) {
            return false;
        }
        ActiveStatusWrapInfo activeStatusWrapInfo = (ActiveStatusWrapInfo) other;
        return Intrinsics.areEqual(this.activeState, activeStatusWrapInfo.activeState) && Intrinsics.areEqual(this.inactiveType, activeStatusWrapInfo.inactiveType);
    }

    public int hashCode() {
        ActiveStatus activeStatus = this.activeState;
        int hashCode = (activeStatus != null ? activeStatus.hashCode() : 0) * 31;
        InactiveType inactiveType = this.inactiveType;
        return hashCode + (inactiveType != null ? inactiveType.hashCode() : 0);
    }

    public ActiveStatusWrapInfo(ActiveStatus activeState, InactiveType inactiveType) {
        Intrinsics.checkParameterIsNotNull(activeState, "activeState");
        this.activeState = activeState;
        this.inactiveType = inactiveType;
    }

    public final ActiveStatus getActiveState() {
        return this.activeState;
    }

    public /* synthetic */ ActiveStatusWrapInfo(ActiveStatus activeStatus, InactiveType inactiveType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(activeStatus, (i & 2) != 0 ? (InactiveType) null : inactiveType);
    }

    public final InactiveType getInactiveType() {
        return this.inactiveType;
    }

    public String toString() {
        return "ActiveStatusWrapInfo(activeState=" + this.activeState + ", inactiveType=" + this.inactiveType + ')';
    }
}
