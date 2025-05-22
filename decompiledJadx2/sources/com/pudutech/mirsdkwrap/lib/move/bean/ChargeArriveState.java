package com.pudutech.mirsdkwrap.lib.move.bean;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.mirsdkwrap.lib.enums.RobotState;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ChargeArriveState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\n\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u001f\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0014"}, m3961d2 = {"Lcom/pudutech/mirsdkwrap/lib/move/bean/ChargeArriveState;", "", "mode", "", TmpConstant.SERVICE_DESC, "(Ljava/lang/String;Ljava/lang/String;)V", "getDesc", "()Ljava/lang/String;", "getMode", "component1", "component2", "copy", "equals", "", "other", "getModeState", "Lcom/pudutech/mirsdkwrap/lib/enums/RobotState;", "hashCode", "", "toString", "module_robot_mirsdk_wrapper_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class ChargeArriveState {
    private final String desc;
    private final String mode;

    public static /* synthetic */ ChargeArriveState copy$default(ChargeArriveState chargeArriveState, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = chargeArriveState.mode;
        }
        if ((i & 2) != 0) {
            str2 = chargeArriveState.desc;
        }
        return chargeArriveState.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMode() {
        return this.mode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    public final ChargeArriveState copy(String mode, String desc) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        return new ChargeArriveState(mode, desc);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChargeArriveState)) {
            return false;
        }
        ChargeArriveState chargeArriveState = (ChargeArriveState) other;
        return Intrinsics.areEqual(this.mode, chargeArriveState.mode) && Intrinsics.areEqual(this.desc, chargeArriveState.desc);
    }

    public int hashCode() {
        String str = this.mode;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.desc;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "ChargeArriveState(mode=" + this.mode + ", desc=" + this.desc + ")";
    }

    public ChargeArriveState(String mode, String str) {
        Intrinsics.checkParameterIsNotNull(mode, "mode");
        this.mode = mode;
        this.desc = str;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getMode() {
        return this.mode;
    }

    public final RobotState getModeState() {
        String str = this.desc;
        return Intrinsics.areEqual(str, RobotState.FailTrack.name()) ? RobotState.FailTrack : Intrinsics.areEqual(str, RobotState.FailOverTime.name()) ? RobotState.FailOverTime : Intrinsics.areEqual(str, RobotState.FailStuck.name()) ? RobotState.FailStuck : RobotState.Success;
    }
}
