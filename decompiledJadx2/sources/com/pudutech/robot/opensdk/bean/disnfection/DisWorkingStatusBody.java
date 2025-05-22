package com.pudutech.robot.opensdk.bean.disnfection;

import com.pudutech.robot.opensdk.interf.IBody;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: DisWorkingStatusBody.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/bean/disnfection/DisWorkingStatusBody;", "Lcom/pudutech/robot/opensdk/interf/IBody;", "mode", "", "status", "dis_device", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDis_device", "()Ljava/lang/String;", "getMode", "getStatus", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class DisWorkingStatusBody implements IBody {
    private final String dis_device;
    private final String mode;
    private final String status;

    public static /* synthetic */ DisWorkingStatusBody copy$default(DisWorkingStatusBody disWorkingStatusBody, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = disWorkingStatusBody.mode;
        }
        if ((i & 2) != 0) {
            str2 = disWorkingStatusBody.status;
        }
        if ((i & 4) != 0) {
            str3 = disWorkingStatusBody.dis_device;
        }
        return disWorkingStatusBody.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMode() {
        return this.mode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDis_device() {
        return this.dis_device;
    }

    public final DisWorkingStatusBody copy(String mode, String status, String dis_device) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        return new DisWorkingStatusBody(mode, status, dis_device);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DisWorkingStatusBody)) {
            return false;
        }
        DisWorkingStatusBody disWorkingStatusBody = (DisWorkingStatusBody) other;
        return Intrinsics.areEqual(this.mode, disWorkingStatusBody.mode) && Intrinsics.areEqual(this.status, disWorkingStatusBody.status) && Intrinsics.areEqual(this.dis_device, disWorkingStatusBody.dis_device);
    }

    public int hashCode() {
        String str = this.mode;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.status;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.dis_device;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "DisWorkingStatusBody(mode=" + this.mode + ", status=" + this.status + ", dis_device=" + this.dis_device + ")";
    }

    public DisWorkingStatusBody(String str, String status, String str2) {
        Intrinsics.checkParameterIsNotNull(status, "status");
        this.mode = str;
        this.status = status;
        this.dis_device = str2;
    }

    public /* synthetic */ DisWorkingStatusBody(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (String) null : str, str2, (i & 4) != 0 ? (String) null : str3);
    }

    public final String getMode() {
        return this.mode;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getDis_device() {
        return this.dis_device;
    }
}
