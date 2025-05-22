package com.pudutech.disinfect.baselib.network.req;

import com.aliyun.alink.p022h2.api.Constraint;
import com.google.gson.annotations.SerializedName;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: AliyunIotSecretResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0005HÆ\u0003J'\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0005HÖ\u0001R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001e\u0010\u0006\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\t\"\u0004\b\r\u0010\u000bR\u001e\u0010\u0002\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/AliyunIotSecretResp;", "", "success", "", Constraint.PARAM_DEVICE_SECRET, "", "error", "(ZLjava/lang/String;Ljava/lang/String;)V", "getDeviceSecret", "()Ljava/lang/String;", "setDeviceSecret", "(Ljava/lang/String;)V", "getError", "setError", "getSuccess", "()Z", "setSuccess", "(Z)V", "component1", "component2", "component3", "copy", "equals", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class AliyunIotSecretResp {

    @SerializedName("DeviceSecret")
    private String deviceSecret;

    @SerializedName(MoveError.LEVEL_ERROR)
    private String error;

    @SerializedName("Success")
    private boolean success;

    public static /* synthetic */ AliyunIotSecretResp copy$default(AliyunIotSecretResp aliyunIotSecretResp, boolean z, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = aliyunIotSecretResp.success;
        }
        if ((i & 2) != 0) {
            str = aliyunIotSecretResp.deviceSecret;
        }
        if ((i & 4) != 0) {
            str2 = aliyunIotSecretResp.error;
        }
        return aliyunIotSecretResp.copy(z, str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getSuccess() {
        return this.success;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDeviceSecret() {
        return this.deviceSecret;
    }

    /* renamed from: component3, reason: from getter */
    public final String getError() {
        return this.error;
    }

    public final AliyunIotSecretResp copy(boolean success, String deviceSecret, String error) {
        Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
        Intrinsics.checkParameterIsNotNull(error, "error");
        return new AliyunIotSecretResp(success, deviceSecret, error);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AliyunIotSecretResp)) {
            return false;
        }
        AliyunIotSecretResp aliyunIotSecretResp = (AliyunIotSecretResp) other;
        return this.success == aliyunIotSecretResp.success && Intrinsics.areEqual(this.deviceSecret, aliyunIotSecretResp.deviceSecret) && Intrinsics.areEqual(this.error, aliyunIotSecretResp.error);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    public int hashCode() {
        boolean z = this.success;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        String str = this.deviceSecret;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.error;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "AliyunIotSecretResp(success=" + this.success + ", deviceSecret=" + this.deviceSecret + ", error=" + this.error + ")";
    }

    public AliyunIotSecretResp(boolean z, String deviceSecret, String error) {
        Intrinsics.checkParameterIsNotNull(deviceSecret, "deviceSecret");
        Intrinsics.checkParameterIsNotNull(error, "error");
        this.success = z;
        this.deviceSecret = deviceSecret;
        this.error = error;
    }

    public final boolean getSuccess() {
        return this.success;
    }

    public final void setSuccess(boolean z) {
        this.success = z;
    }

    public final String getDeviceSecret() {
        return this.deviceSecret;
    }

    public final void setDeviceSecret(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.deviceSecret = str;
    }

    public final String getError() {
        return this.error;
    }

    public final void setError(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.error = str;
    }
}
