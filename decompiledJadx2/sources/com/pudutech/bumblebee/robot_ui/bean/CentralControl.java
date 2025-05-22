package com.pudutech.bumblebee.robot_ui.bean;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CentralControl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004J\u000b\u0010\b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0015\u0010\t\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001H\u0096\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0003H\u0016R\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot_ui/bean/CentralControl;", "", "mac", "", "(Ljava/lang/String;)V", "getMac", "()Ljava/lang/String;", "setMac", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "robot_ui_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final /* data */ class CentralControl {
    private String mac;

    public static /* synthetic */ CentralControl copy$default(CentralControl centralControl, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = centralControl.mac;
        }
        return centralControl.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    public final CentralControl copy(String mac) {
        return new CentralControl(mac);
    }

    public CentralControl(String str) {
        this.mac = str;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        this.mac = str;
    }

    public String toString() {
        return "CentralControl(mac=" + this.mac + ')';
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(getClass(), other != null ? other.getClass() : null)) {
            return false;
        }
        if (other != null) {
            return !(Intrinsics.areEqual(this.mac, ((CentralControl) other).mac) ^ true);
        }
        throw new TypeCastException("null cannot be cast to non-null type com.pudutech.bumblebee.robot_ui.bean.CentralControl");
    }

    public int hashCode() {
        String str = this.mac;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }
}
