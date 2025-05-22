package com.pudutech.bumblebee.presenter.robot_open_task.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqttReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/robot_open_task/bean/ReqMac;", "", "mac", "", "(Ljava/lang/String;)V", "getMac", "()Ljava/lang/String;", "setMac", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class ReqMac {
    private String mac;

    public static /* synthetic */ ReqMac copy$default(ReqMac reqMac, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = reqMac.mac;
        }
        return reqMac.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    public final ReqMac copy(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        return new ReqMac(mac);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof ReqMac) && Intrinsics.areEqual(this.mac, ((ReqMac) other).mac);
        }
        return true;
    }

    public int hashCode() {
        String str = this.mac;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "ReqMac(mac=" + this.mac + ")";
    }

    public ReqMac(String mac) {
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        this.mac = mac;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }
}
