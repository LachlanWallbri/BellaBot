package com.pudutech.robot.opensdk.aliyun.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: ShadowUpdate.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0011\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0007HÆ\u0003¢\u0006\u0002\u0010\u000eJ0\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\n\n\u0002\u0010\u000f\u001a\u0004\b\r\u0010\u000e¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowUpdate;", "", "method", "", "state", "Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowState;", "version", "", "(Ljava/lang/String;Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowState;Ljava/lang/Long;)V", "getMethod", "()Ljava/lang/String;", "getState", "()Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowState;", "getVersion", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "copy", "(Ljava/lang/String;Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowState;Ljava/lang/Long;)Lcom/pudutech/robot/opensdk/aliyun/bean/ShadowUpdate;", "equals", "", "other", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class ShadowUpdate {
    private final String method;
    private final ShadowState state;
    private final Long version;

    public static /* synthetic */ ShadowUpdate copy$default(ShadowUpdate shadowUpdate, String str, ShadowState shadowState, Long l, int i, Object obj) {
        if ((i & 1) != 0) {
            str = shadowUpdate.method;
        }
        if ((i & 2) != 0) {
            shadowState = shadowUpdate.state;
        }
        if ((i & 4) != 0) {
            l = shadowUpdate.version;
        }
        return shadowUpdate.copy(str, shadowState, l);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMethod() {
        return this.method;
    }

    /* renamed from: component2, reason: from getter */
    public final ShadowState getState() {
        return this.state;
    }

    /* renamed from: component3, reason: from getter */
    public final Long getVersion() {
        return this.version;
    }

    public final ShadowUpdate copy(String method, ShadowState state, Long version) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        return new ShadowUpdate(method, state, version);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShadowUpdate)) {
            return false;
        }
        ShadowUpdate shadowUpdate = (ShadowUpdate) other;
        return Intrinsics.areEqual(this.method, shadowUpdate.method) && Intrinsics.areEqual(this.state, shadowUpdate.state) && Intrinsics.areEqual(this.version, shadowUpdate.version);
    }

    public int hashCode() {
        String str = this.method;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        ShadowState shadowState = this.state;
        int hashCode2 = (hashCode + (shadowState != null ? shadowState.hashCode() : 0)) * 31;
        Long l = this.version;
        return hashCode2 + (l != null ? l.hashCode() : 0);
    }

    public String toString() {
        return "ShadowUpdate(method=" + this.method + ", state=" + this.state + ", version=" + this.version + ")";
    }

    public ShadowUpdate(String method, ShadowState shadowState, Long l) {
        Intrinsics.checkParameterIsNotNull(method, "method");
        this.method = method;
        this.state = shadowState;
        this.version = l;
    }

    public final String getMethod() {
        return this.method;
    }

    public /* synthetic */ ShadowUpdate(String str, ShadowState shadowState, Long l, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? (ShadowState) null : shadowState, (i & 4) != 0 ? (Long) null : l);
    }

    public final ShadowState getState() {
        return this.state;
    }

    public final Long getVersion() {
        return this.version;
    }
}
