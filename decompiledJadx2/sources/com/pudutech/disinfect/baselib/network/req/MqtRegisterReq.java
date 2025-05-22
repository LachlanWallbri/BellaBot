package com.pudutech.disinfect.baselib.network.req;

import com.aliyun.alink.linksdk.tmp.api.DevFoundOutputParams;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MqtRegisterReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/MqtRegisterReq;", "", DevFoundOutputParams.PARAMS_DEVICE_NAME, "", "product_device", "device_name_type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDevice_name", "()Ljava/lang/String;", "getDevice_name_type", "getProduct_device", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class MqtRegisterReq {
    private final String device_name;
    private final String device_name_type;
    private final String product_device;

    public static /* synthetic */ MqtRegisterReq copy$default(MqtRegisterReq mqtRegisterReq, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = mqtRegisterReq.device_name;
        }
        if ((i & 2) != 0) {
            str2 = mqtRegisterReq.product_device;
        }
        if ((i & 4) != 0) {
            str3 = mqtRegisterReq.device_name_type;
        }
        return mqtRegisterReq.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getDevice_name() {
        return this.device_name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getProduct_device() {
        return this.product_device;
    }

    /* renamed from: component3, reason: from getter */
    public final String getDevice_name_type() {
        return this.device_name_type;
    }

    public final MqtRegisterReq copy(String device_name, String product_device, String device_name_type) {
        Intrinsics.checkParameterIsNotNull(device_name, "device_name");
        Intrinsics.checkParameterIsNotNull(product_device, "product_device");
        Intrinsics.checkParameterIsNotNull(device_name_type, "device_name_type");
        return new MqtRegisterReq(device_name, product_device, device_name_type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MqtRegisterReq)) {
            return false;
        }
        MqtRegisterReq mqtRegisterReq = (MqtRegisterReq) other;
        return Intrinsics.areEqual(this.device_name, mqtRegisterReq.device_name) && Intrinsics.areEqual(this.product_device, mqtRegisterReq.product_device) && Intrinsics.areEqual(this.device_name_type, mqtRegisterReq.device_name_type);
    }

    public int hashCode() {
        String str = this.device_name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.product_device;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.device_name_type;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "MqtRegisterReq(device_name=" + this.device_name + ", product_device=" + this.product_device + ", device_name_type=" + this.device_name_type + ")";
    }

    public MqtRegisterReq(String device_name, String product_device, String device_name_type) {
        Intrinsics.checkParameterIsNotNull(device_name, "device_name");
        Intrinsics.checkParameterIsNotNull(product_device, "product_device");
        Intrinsics.checkParameterIsNotNull(device_name_type, "device_name_type");
        this.device_name = device_name;
        this.product_device = product_device;
        this.device_name_type = device_name_type;
    }

    public final String getDevice_name() {
        return this.device_name;
    }

    public final String getProduct_device() {
        return this.product_device;
    }

    public /* synthetic */ MqtRegisterReq(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? "mac" : str3);
    }

    public final String getDevice_name_type() {
        return this.device_name_type;
    }
}
