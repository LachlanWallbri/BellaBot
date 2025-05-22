package com.pudutech.robot.opensdk.bean;

import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DeviceOnlineBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/DeviceOnlineBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "state", "", "sourceType", MapElement.Source.SOURCE, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getSource", "()Ljava/lang/String;", "getSourceType", "getState", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class DeviceOnlineBody implements IBody {
    private final String source;
    private final String sourceType;
    private final String state;

    public static /* synthetic */ DeviceOnlineBody copy$default(DeviceOnlineBody deviceOnlineBody, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceOnlineBody.state;
        }
        if ((i & 2) != 0) {
            str2 = deviceOnlineBody.sourceType;
        }
        if ((i & 4) != 0) {
            str3 = deviceOnlineBody.source;
        }
        return deviceOnlineBody.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getState() {
        return this.state;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSourceType() {
        return this.sourceType;
    }

    /* renamed from: component3, reason: from getter */
    public final String getSource() {
        return this.source;
    }

    public final DeviceOnlineBody copy(String state, String sourceType, String source) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(sourceType, "sourceType");
        Intrinsics.checkParameterIsNotNull(source, "source");
        return new DeviceOnlineBody(state, sourceType, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceOnlineBody)) {
            return false;
        }
        DeviceOnlineBody deviceOnlineBody = (DeviceOnlineBody) other;
        return Intrinsics.areEqual(this.state, deviceOnlineBody.state) && Intrinsics.areEqual(this.sourceType, deviceOnlineBody.sourceType) && Intrinsics.areEqual(this.source, deviceOnlineBody.source);
    }

    public int hashCode() {
        String str = this.state;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.sourceType;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.source;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "DeviceOnlineBody(state=" + this.state + ", sourceType=" + this.sourceType + ", source=" + this.source + ")";
    }

    public DeviceOnlineBody(String state, String sourceType, String source) {
        Intrinsics.checkParameterIsNotNull(state, "state");
        Intrinsics.checkParameterIsNotNull(sourceType, "sourceType");
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.state = state;
        this.sourceType = sourceType;
        this.source = source;
    }

    public final String getState() {
        return this.state;
    }

    public final String getSourceType() {
        return this.sourceType;
    }

    public /* synthetic */ DeviceOnlineBody(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3);
    }

    public final String getSource() {
        return this.source;
    }
}
