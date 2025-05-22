package com.pudutech.lib_update.base.net;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J;\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0018\u001a\u00020\u0019HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/lib_update/base/net/SwitchVersionReq;", "", "version_name", "", "version_code", "mac", "product_name", "channel_name", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannel_name", "()Ljava/lang/String;", "getMac", "getProduct_name", "getVersion_code", "getVersion_name", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "", "toString", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class SwitchVersionReq {
    private final String channel_name;
    private final String mac;
    private final String product_name;
    private final String version_code;
    private final String version_name;

    public static /* synthetic */ SwitchVersionReq copy$default(SwitchVersionReq switchVersionReq, String str, String str2, String str3, String str4, String str5, int i, Object obj) {
        if ((i & 1) != 0) {
            str = switchVersionReq.version_name;
        }
        if ((i & 2) != 0) {
            str2 = switchVersionReq.version_code;
        }
        String str6 = str2;
        if ((i & 4) != 0) {
            str3 = switchVersionReq.mac;
        }
        String str7 = str3;
        if ((i & 8) != 0) {
            str4 = switchVersionReq.product_name;
        }
        String str8 = str4;
        if ((i & 16) != 0) {
            str5 = switchVersionReq.channel_name;
        }
        return switchVersionReq.copy(str, str6, str7, str8, str5);
    }

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
    public final String getChannel_name() {
        return this.channel_name;
    }

    public final SwitchVersionReq copy(String version_name, String version_code, String mac, String product_name, String channel_name) {
        Intrinsics.checkParameterIsNotNull(version_name, "version_name");
        Intrinsics.checkParameterIsNotNull(version_code, "version_code");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(channel_name, "channel_name");
        return new SwitchVersionReq(version_name, version_code, mac, product_name, channel_name);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SwitchVersionReq)) {
            return false;
        }
        SwitchVersionReq switchVersionReq = (SwitchVersionReq) other;
        return Intrinsics.areEqual(this.version_name, switchVersionReq.version_name) && Intrinsics.areEqual(this.version_code, switchVersionReq.version_code) && Intrinsics.areEqual(this.mac, switchVersionReq.mac) && Intrinsics.areEqual(this.product_name, switchVersionReq.product_name) && Intrinsics.areEqual(this.channel_name, switchVersionReq.channel_name);
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
        String str5 = this.channel_name;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "SwitchVersionReq(version_name=" + this.version_name + ", version_code=" + this.version_code + ", mac=" + this.mac + ", product_name=" + this.product_name + ", channel_name=" + this.channel_name + ")";
    }

    public SwitchVersionReq(String version_name, String version_code, String mac, String product_name, String channel_name) {
        Intrinsics.checkParameterIsNotNull(version_name, "version_name");
        Intrinsics.checkParameterIsNotNull(version_code, "version_code");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(channel_name, "channel_name");
        this.version_name = version_name;
        this.version_code = version_code;
        this.mac = mac;
        this.product_name = product_name;
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

    public final String getChannel_name() {
        return this.channel_name;
    }
}
