package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ShadowData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowState;", "", "desired", "Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowStateValue;", "reported", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowStateValue;Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowStateValue;)V", "getDesired", "()Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowStateValue;", "setDesired", "(Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowStateValue;)V", "getReported", "setReported", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ShadowState {
    private ShadowStateValue desired;
    private ShadowStateValue reported;

    public static /* synthetic */ ShadowState copy$default(ShadowState shadowState, ShadowStateValue shadowStateValue, ShadowStateValue shadowStateValue2, int i, Object obj) {
        if ((i & 1) != 0) {
            shadowStateValue = shadowState.desired;
        }
        if ((i & 2) != 0) {
            shadowStateValue2 = shadowState.reported;
        }
        return shadowState.copy(shadowStateValue, shadowStateValue2);
    }

    /* renamed from: component1, reason: from getter */
    public final ShadowStateValue getDesired() {
        return this.desired;
    }

    /* renamed from: component2, reason: from getter */
    public final ShadowStateValue getReported() {
        return this.reported;
    }

    public final ShadowState copy(ShadowStateValue desired, ShadowStateValue reported) {
        return new ShadowState(desired, reported);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShadowState)) {
            return false;
        }
        ShadowState shadowState = (ShadowState) other;
        return Intrinsics.areEqual(this.desired, shadowState.desired) && Intrinsics.areEqual(this.reported, shadowState.reported);
    }

    public int hashCode() {
        ShadowStateValue shadowStateValue = this.desired;
        int hashCode = (shadowStateValue != null ? shadowStateValue.hashCode() : 0) * 31;
        ShadowStateValue shadowStateValue2 = this.reported;
        return hashCode + (shadowStateValue2 != null ? shadowStateValue2.hashCode() : 0);
    }

    public String toString() {
        return "ShadowState(desired=" + this.desired + ", reported=" + this.reported + ")";
    }

    public ShadowState(ShadowStateValue shadowStateValue, ShadowStateValue shadowStateValue2) {
        this.desired = shadowStateValue;
        this.reported = shadowStateValue2;
    }

    public final ShadowStateValue getDesired() {
        return this.desired;
    }

    public final ShadowStateValue getReported() {
        return this.reported;
    }

    public final void setDesired(ShadowStateValue shadowStateValue) {
        this.desired = shadowStateValue;
    }

    public final void setReported(ShadowStateValue shadowStateValue) {
        this.reported = shadowStateValue;
    }
}
