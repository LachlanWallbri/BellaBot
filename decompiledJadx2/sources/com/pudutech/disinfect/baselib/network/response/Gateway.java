package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: iot_call.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0016\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\nHÆ\u0003J\t\u0010 \u001a\u00020\fHÆ\u0003JY\u0010!\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\fHÆ\u0001J\u0013\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010%\u001a\u00020\fHÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006'"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/Gateway;", "", "device_addr", "", "mac", "name", "network_order", "network_type", "pid", "rf_setting", "Lcom/pudutech/disinfect/baselib/network/response/RfSetting;", "shop_id", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/disinfect/baselib/network/response/RfSetting;I)V", "getDevice_addr", "()Ljava/lang/String;", "getMac", "getName", "getNetwork_order", "getNetwork_type", "getPid", "getRf_setting", "()Lcom/pudutech/disinfect/baselib/network/response/RfSetting;", "getShop_id", "()I", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class Gateway {
    private final String device_addr;
    private final String mac;
    private final String name;
    private final String network_order;
    private final String network_type;
    private final String pid;
    private final RfSetting rf_setting;
    private final int shop_id;

    /* renamed from: component1, reason: from getter */
    public final String getDevice_addr() {
        return this.device_addr;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component3, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getNetwork_order() {
        return this.network_order;
    }

    /* renamed from: component5, reason: from getter */
    public final String getNetwork_type() {
        return this.network_type;
    }

    /* renamed from: component6, reason: from getter */
    public final String getPid() {
        return this.pid;
    }

    /* renamed from: component7, reason: from getter */
    public final RfSetting getRf_setting() {
        return this.rf_setting;
    }

    /* renamed from: component8, reason: from getter */
    public final int getShop_id() {
        return this.shop_id;
    }

    public final Gateway copy(String device_addr, String mac, String name, String network_order, String network_type, String pid, RfSetting rf_setting, int shop_id) {
        Intrinsics.checkParameterIsNotNull(device_addr, "device_addr");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(network_order, "network_order");
        Intrinsics.checkParameterIsNotNull(network_type, "network_type");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(rf_setting, "rf_setting");
        return new Gateway(device_addr, mac, name, network_order, network_type, pid, rf_setting, shop_id);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Gateway)) {
            return false;
        }
        Gateway gateway = (Gateway) other;
        return Intrinsics.areEqual(this.device_addr, gateway.device_addr) && Intrinsics.areEqual(this.mac, gateway.mac) && Intrinsics.areEqual(this.name, gateway.name) && Intrinsics.areEqual(this.network_order, gateway.network_order) && Intrinsics.areEqual(this.network_type, gateway.network_type) && Intrinsics.areEqual(this.pid, gateway.pid) && Intrinsics.areEqual(this.rf_setting, gateway.rf_setting) && this.shop_id == gateway.shop_id;
    }

    public int hashCode() {
        String str = this.device_addr;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mac;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.name;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.network_order;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.network_type;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.pid;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        RfSetting rfSetting = this.rf_setting;
        return ((hashCode6 + (rfSetting != null ? rfSetting.hashCode() : 0)) * 31) + Integer.hashCode(this.shop_id);
    }

    public String toString() {
        return "Gateway(device_addr=" + this.device_addr + ", mac=" + this.mac + ", name=" + this.name + ", network_order=" + this.network_order + ", network_type=" + this.network_type + ", pid=" + this.pid + ", rf_setting=" + this.rf_setting + ", shop_id=" + this.shop_id + ")";
    }

    public Gateway(String device_addr, String mac, String name, String network_order, String network_type, String pid, RfSetting rf_setting, int i) {
        Intrinsics.checkParameterIsNotNull(device_addr, "device_addr");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(network_order, "network_order");
        Intrinsics.checkParameterIsNotNull(network_type, "network_type");
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(rf_setting, "rf_setting");
        this.device_addr = device_addr;
        this.mac = mac;
        this.name = name;
        this.network_order = network_order;
        this.network_type = network_type;
        this.pid = pid;
        this.rf_setting = rf_setting;
        this.shop_id = i;
    }

    public final String getDevice_addr() {
        return this.device_addr;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getName() {
        return this.name;
    }

    public final String getNetwork_order() {
        return this.network_order;
    }

    public final String getNetwork_type() {
        return this.network_type;
    }

    public final String getPid() {
        return this.pid;
    }

    public final RfSetting getRf_setting() {
        return this.rf_setting;
    }

    public final int getShop_id() {
        return this.shop_id;
    }
}
