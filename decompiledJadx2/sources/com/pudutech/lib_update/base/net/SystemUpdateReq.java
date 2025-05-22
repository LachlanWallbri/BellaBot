package com.pudutech.lib_update.base.net;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003Jc\u0010 \u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010!\u001a\u00020\"2\b\u0010#\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010$\u001a\u00020%HÖ\u0001J\t\u0010&\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000eR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006'"}, m3961d2 = {"Lcom/pudutech/lib_update/base/net/SystemUpdateReq;", "", "version_name", "", "version_code", "mac", "product_name", "request_ver_name", "request_ver_code", "app_version", "sys_build_id", "channel_name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getApp_version", "()Ljava/lang/String;", "getChannel_name", "getMac", "getProduct_name", "getRequest_ver_code", "getRequest_ver_name", "getSys_build_id", "getVersion_code", "getVersion_name", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class SystemUpdateReq {
    private final String app_version;
    private final String channel_name;
    private final String mac;
    private final String product_name;
    private final String request_ver_code;
    private final String request_ver_name;
    private final String sys_build_id;
    private final String version_code;
    private final String version_name;

    /* renamed from: component1, reason: from getter */
    public final String getVersion_name() {
        return this.version_name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getVersion_code() {
        return this.version_code;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component4, reason: from getter */
    public final String getProduct_name() {
        return this.product_name;
    }

    /* renamed from: component5, reason: from getter */
    public final String getRequest_ver_name() {
        return this.request_ver_name;
    }

    /* renamed from: component6, reason: from getter */
    public final String getRequest_ver_code() {
        return this.request_ver_code;
    }

    /* renamed from: component7, reason: from getter */
    public final String getApp_version() {
        return this.app_version;
    }

    /* renamed from: component8, reason: from getter */
    public final String getSys_build_id() {
        return this.sys_build_id;
    }

    /* renamed from: component9, reason: from getter */
    public final String getChannel_name() {
        return this.channel_name;
    }

    public final SystemUpdateReq copy(String version_name, String version_code, String mac, String product_name, String request_ver_name, String request_ver_code, String app_version, String sys_build_id, String channel_name) {
        Intrinsics.checkParameterIsNotNull(version_name, "version_name");
        Intrinsics.checkParameterIsNotNull(version_code, "version_code");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(request_ver_name, "request_ver_name");
        Intrinsics.checkParameterIsNotNull(request_ver_code, "request_ver_code");
        Intrinsics.checkParameterIsNotNull(app_version, "app_version");
        Intrinsics.checkParameterIsNotNull(sys_build_id, "sys_build_id");
        Intrinsics.checkParameterIsNotNull(channel_name, "channel_name");
        return new SystemUpdateReq(version_name, version_code, mac, product_name, request_ver_name, request_ver_code, app_version, sys_build_id, channel_name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SystemUpdateReq)) {
            return false;
        }
        SystemUpdateReq systemUpdateReq = (SystemUpdateReq) other;
        return Intrinsics.areEqual(this.version_name, systemUpdateReq.version_name) && Intrinsics.areEqual(this.version_code, systemUpdateReq.version_code) && Intrinsics.areEqual(this.mac, systemUpdateReq.mac) && Intrinsics.areEqual(this.product_name, systemUpdateReq.product_name) && Intrinsics.areEqual(this.request_ver_name, systemUpdateReq.request_ver_name) && Intrinsics.areEqual(this.request_ver_code, systemUpdateReq.request_ver_code) && Intrinsics.areEqual(this.app_version, systemUpdateReq.app_version) && Intrinsics.areEqual(this.sys_build_id, systemUpdateReq.sys_build_id) && Intrinsics.areEqual(this.channel_name, systemUpdateReq.channel_name);
    }

    public int hashCode() {
        String str = this.version_name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.version_code;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.mac;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.product_name;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.request_ver_name;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.request_ver_code;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.app_version;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.sys_build_id;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.channel_name;
        return hashCode8 + (str9 != null ? str9.hashCode() : 0);
    }

    public String toString() {
        return "SystemUpdateReq(version_name=" + this.version_name + ", version_code=" + this.version_code + ", mac=" + this.mac + ", product_name=" + this.product_name + ", request_ver_name=" + this.request_ver_name + ", request_ver_code=" + this.request_ver_code + ", app_version=" + this.app_version + ", sys_build_id=" + this.sys_build_id + ", channel_name=" + this.channel_name + ")";
    }

    public SystemUpdateReq(String version_name, String version_code, String mac, String product_name, String request_ver_name, String request_ver_code, String app_version, String sys_build_id, String channel_name) {
        Intrinsics.checkParameterIsNotNull(version_name, "version_name");
        Intrinsics.checkParameterIsNotNull(version_code, "version_code");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(request_ver_name, "request_ver_name");
        Intrinsics.checkParameterIsNotNull(request_ver_code, "request_ver_code");
        Intrinsics.checkParameterIsNotNull(app_version, "app_version");
        Intrinsics.checkParameterIsNotNull(sys_build_id, "sys_build_id");
        Intrinsics.checkParameterIsNotNull(channel_name, "channel_name");
        this.version_name = version_name;
        this.version_code = version_code;
        this.mac = mac;
        this.product_name = product_name;
        this.request_ver_name = request_ver_name;
        this.request_ver_code = request_ver_code;
        this.app_version = app_version;
        this.sys_build_id = sys_build_id;
        this.channel_name = channel_name;
    }

    public final String getVersion_name() {
        return this.version_name;
    }

    public final String getVersion_code() {
        return this.version_code;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getProduct_name() {
        return this.product_name;
    }

    public final String getRequest_ver_name() {
        return this.request_ver_name;
    }

    public final String getRequest_ver_code() {
        return this.request_ver_code;
    }

    public final String getApp_version() {
        return this.app_version;
    }

    public final String getSys_build_id() {
        return this.sys_build_id;
    }

    public final String getChannel_name() {
        return this.channel_name;
    }
}
