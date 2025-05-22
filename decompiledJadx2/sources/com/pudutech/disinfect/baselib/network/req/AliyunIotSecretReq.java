package com.pudutech.disinfect.baselib.network.req;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: AliyunIotSecretReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\u0004¨\u0006\u0010"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/AliyunIotSecretReq;", "", "deviceName", "", "(Ljava/lang/String;)V", "getDeviceName", "()Ljava/lang/String;", "setDeviceName", "component1", "copy", "equals", "", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class AliyunIotSecretReq {

    @SerializedName("deviceName")
    private String deviceName;

    public static /* synthetic */ AliyunIotSecretReq copy$default(AliyunIotSecretReq aliyunIotSecretReq, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = aliyunIotSecretReq.deviceName;
        }
        return aliyunIotSecretReq.copy(str);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDeviceName() {
        return this.deviceName;
    }

    public final AliyunIotSecretReq copy(String deviceName) {
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        return new AliyunIotSecretReq(deviceName);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof AliyunIotSecretReq) && Intrinsics.areEqual(this.deviceName, ((AliyunIotSecretReq) other).deviceName);
        }
        return true;
    }

    public int hashCode() {
        String str = this.deviceName;
        if (str != null) {
            return str.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "AliyunIotSecretReq(deviceName=" + this.deviceName + ")";
    }

    public AliyunIotSecretReq(String deviceName) {
        Intrinsics.checkParameterIsNotNull(deviceName, "deviceName");
        this.deviceName = deviceName;
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final void setDeviceName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceName = str;
    }
}
