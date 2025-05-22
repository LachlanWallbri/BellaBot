package com.pudutech.remotemaintenance.aliyun;

import com.alibaba.fastjson.annotation.JSONField;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.aliyun.alink.p022h2.api.Constraint;
import com.amazonaws.cognito.clientcontext.datacollection.DataRecordKey;
import kotlin.Metadata;

/* compiled from: DeviceInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0012\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0015\u001a\u00020\u0004H\u0016R \u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR \u0010\t\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0006\"\u0004\b\u000b\u0010\bR \u0010\f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u0006\"\u0004\b\u000e\u0010\bR \u0010\u000f\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR \u0010\u0012\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\b¨\u0006\u0016"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/aliyun/DeviceInfo;", "", "()V", "deviceName", "", "getDeviceName", "()Ljava/lang/String;", "setDeviceName", "(Ljava/lang/String;)V", Constraint.PARAM_DEVICE_SECRET, "getDeviceSecret", "setDeviceSecret", TmpConstant.DEVICE_IOTID, "getIotId", "setIotId", "productKey", "getProductKey", "setProductKey", "region_id", "getRegion_id", "setRegion_id", "toString", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class DeviceInfo {

    @JSONField(name = DataRecordKey.MODEL)
    private String deviceName;

    @JSONField(name = "DeviceSecret")
    private String deviceSecret;

    @JSONField(name = "IotId")
    private String iotId;

    @JSONField(name = "ProductKey")
    private String productKey;

    @JSONField(name = "region_id")
    private String region_id;

    public final String getProductKey() {
        return this.productKey;
    }

    public final void setProductKey(String str) {
        this.productKey = str;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final void setDeviceName(String str) {
        this.deviceName = str;
    }

    public final String getDeviceSecret() {
        return this.deviceSecret;
    }

    public final void setDeviceSecret(String str) {
        this.deviceSecret = str;
    }

    public final String getIotId() {
        return this.iotId;
    }

    public final void setIotId(String str) {
        this.iotId = str;
    }

    public final String getRegion_id() {
        return this.region_id;
    }

    public final void setRegion_id(String str) {
        this.region_id = str;
    }

    public String toString() {
        return "DeviceInfo(productKey=" + this.productKey + ", deviceName=" + this.deviceName + ", deviceSecret=" + this.deviceSecret + ", iotId=" + this.iotId + ", region_id=" + this.region_id + ')';
    }
}
