package com.pudutech.remotemaintenance.net.resp;

import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceRegisterResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J1\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\t¨\u0006\u0018"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/net/resp/DeviceRegisterResp;", "", "ProductKey", "", DataRecordKey.MODEL, "DeviceSecret", "IotId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDeviceName", "()Ljava/lang/String;", "getDeviceSecret", "getIotId", "getProductKey", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final /* data */ class DeviceRegisterResp {
    private final String DeviceName;
    private final String DeviceSecret;
    private final String IotId;
    private final String ProductKey;

    public static /* synthetic */ DeviceRegisterResp copy$default(DeviceRegisterResp deviceRegisterResp, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = deviceRegisterResp.ProductKey;
        }
        if ((i & 2) != 0) {
            str2 = deviceRegisterResp.DeviceName;
        }
        if ((i & 4) != 0) {
            str3 = deviceRegisterResp.DeviceSecret;
        }
        if ((i & 8) != 0) {
            str4 = deviceRegisterResp.IotId;
        }
        return deviceRegisterResp.copy(str, str2, str3, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getProductKey() {
        return this.ProductKey;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDeviceName() {
        return this.DeviceName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDeviceSecret() {
        return this.DeviceSecret;
    }

    /* renamed from: component4, reason: from getter */
    public final String getIotId() {
        return this.IotId;
    }

    public final DeviceRegisterResp copy(String ProductKey, String DeviceName, String DeviceSecret, String IotId) {
        Intrinsics.checkParameterIsNotNull(ProductKey, "ProductKey");
        Intrinsics.checkParameterIsNotNull(DeviceName, "DeviceName");
        Intrinsics.checkParameterIsNotNull(DeviceSecret, "DeviceSecret");
        Intrinsics.checkParameterIsNotNull(IotId, "IotId");
        return new DeviceRegisterResp(ProductKey, DeviceName, DeviceSecret, IotId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DeviceRegisterResp)) {
            return false;
        }
        DeviceRegisterResp deviceRegisterResp = (DeviceRegisterResp) other;
        return Intrinsics.areEqual(this.ProductKey, deviceRegisterResp.ProductKey) && Intrinsics.areEqual(this.DeviceName, deviceRegisterResp.DeviceName) && Intrinsics.areEqual(this.DeviceSecret, deviceRegisterResp.DeviceSecret) && Intrinsics.areEqual(this.IotId, deviceRegisterResp.IotId);
    }

    public int hashCode() {
        String str = this.ProductKey;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.DeviceName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.DeviceSecret;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.IotId;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "DeviceRegisterResp(ProductKey=" + this.ProductKey + ", DeviceName=" + this.DeviceName + ", DeviceSecret=" + this.DeviceSecret + ", IotId=" + this.IotId + ")";
    }

    public DeviceRegisterResp(String ProductKey, String DeviceName, String DeviceSecret, String IotId) {
        Intrinsics.checkParameterIsNotNull(ProductKey, "ProductKey");
        Intrinsics.checkParameterIsNotNull(DeviceName, "DeviceName");
        Intrinsics.checkParameterIsNotNull(DeviceSecret, "DeviceSecret");
        Intrinsics.checkParameterIsNotNull(IotId, "IotId");
        this.ProductKey = ProductKey;
        this.DeviceName = DeviceName;
        this.DeviceSecret = DeviceSecret;
        this.IotId = IotId;
    }

    public final String getDeviceName() {
        return this.DeviceName;
    }

    public final String getDeviceSecret() {
        return this.DeviceSecret;
    }

    public final String getIotId() {
        return this.IotId;
    }

    public final String getProductKey() {
        return this.ProductKey;
    }
}
