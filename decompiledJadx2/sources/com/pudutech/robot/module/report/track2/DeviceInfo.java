package com.pudutech.robot.module.report.track2;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: device_ext_info.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/robot/module/report/track2/DeviceInfo;", "", "product_name", "", "sn", "(Ljava/lang/String;Ljava/lang/String;)V", "getProduct_name", "()Ljava/lang/String;", "getSn", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_robot_report_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class DeviceInfo {
    private final String product_name;
    private final String sn;

    public static /* synthetic */ DeviceInfo copy$default(DeviceInfo deviceInfo, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceInfo.product_name;
        }
        if ((i & 2) != 0) {
            str2 = deviceInfo.sn;
        }
        return deviceInfo.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getProduct_name() {
        return this.product_name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getSn() {
        return this.sn;
    }

    public final DeviceInfo copy(String product_name, String sn) {
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(sn, "sn");
        return new DeviceInfo(product_name, sn);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceInfo)) {
            return false;
        }
        DeviceInfo deviceInfo = (DeviceInfo) other;
        return Intrinsics.areEqual(this.product_name, deviceInfo.product_name) && Intrinsics.areEqual(this.sn, deviceInfo.sn);
    }

    public int hashCode() {
        String str = this.product_name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.sn;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "DeviceInfo(product_name=" + this.product_name + ", sn=" + this.sn + ")";
    }

    public DeviceInfo(String product_name, String sn) {
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(sn, "sn");
        this.product_name = product_name;
        this.sn = sn;
    }

    public final String getProduct_name() {
        return this.product_name;
    }

    public final String getSn() {
        return this.sn;
    }
}
