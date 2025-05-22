package com.pudutech.disinfect.baselib.network.response;

import com.aliyun.alink.p022h2.api.Constraint;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: AliyunIotSecretResp2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/AliyunIotSecretResp2;", "", Constraint.PARAM_DEVICE_SECRET, "", "productKey", "(Ljava/lang/String;Ljava/lang/String;)V", "getDeviceSecret", "()Ljava/lang/String;", "setDeviceSecret", "(Ljava/lang/String;)V", "getProductKey", "setProductKey", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class AliyunIotSecretResp2 {

    @SerializedName(Constraint.PARAM_DEVICE_SECRET)
    private String deviceSecret;

    @SerializedName("productKey")
    private String productKey;

    public static /* synthetic */ AliyunIotSecretResp2 copy$default(AliyunIotSecretResp2 aliyunIotSecretResp2, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = aliyunIotSecretResp2.deviceSecret;
        }
        if ((i & 2) != 0) {
            str2 = aliyunIotSecretResp2.productKey;
        }
        return aliyunIotSecretResp2.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDeviceSecret() {
        return this.deviceSecret;
    }

    /* renamed from: component2, reason: from getter */
    public final String getProductKey() {
        return this.productKey;
    }

    public final AliyunIotSecretResp2 copy(String deviceSecret, String productKey) {
        Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        return new AliyunIotSecretResp2(deviceSecret, productKey);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AliyunIotSecretResp2)) {
            return false;
        }
        AliyunIotSecretResp2 aliyunIotSecretResp2 = (AliyunIotSecretResp2) other;
        return Intrinsics.areEqual(this.deviceSecret, aliyunIotSecretResp2.deviceSecret) && Intrinsics.areEqual(this.productKey, aliyunIotSecretResp2.productKey);
    }

    public int hashCode() {
        String str = this.deviceSecret;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.productKey;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "AliyunIotSecretResp2(deviceSecret=" + this.deviceSecret + ", productKey=" + this.productKey + ")";
    }

    public AliyunIotSecretResp2(String deviceSecret, String productKey) {
        Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
        Intrinsics.checkParameterIsNotNull(productKey, "productKey");
        this.deviceSecret = deviceSecret;
        this.productKey = productKey;
    }

    public final String getDeviceSecret() {
        return this.deviceSecret;
    }

    public final void setDeviceSecret(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceSecret = str;
    }

    public final String getProductKey() {
        return this.productKey;
    }

    public final void setProductKey(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.productKey = str;
    }
}
