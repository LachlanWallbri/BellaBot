package com.pudutech.lib_update.base.net;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ApiService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003JE\u0010\u0017\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001e"}, m3961d2 = {"Lcom/pudutech/lib_update/base/net/AppUpdateReq;", "", "version_name", "", "version_code", "mac", "product_name", "channel_name", "language", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getChannel_name", "()Ljava/lang/String;", "getLanguage", "getMac", "getProduct_name", "getVersion_code", "getVersion_name", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class AppUpdateReq {
    private final String channel_name;
    private final String language;
    private final String mac;
    private final String product_name;
    private final String version_code;
    private final String version_name;

    public static /* synthetic */ AppUpdateReq copy$default(AppUpdateReq appUpdateReq, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = appUpdateReq.version_name;
        }
        if ((i & 2) != 0) {
            str2 = appUpdateReq.version_code;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = appUpdateReq.mac;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = appUpdateReq.product_name;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = appUpdateReq.channel_name;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = appUpdateReq.language;
        }
        return appUpdateReq.copy(str, str7, str8, str9, str10, str6);
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

    /* renamed from: component6, reason: from getter */
    public final String getLanguage() {
        return this.language;
    }

    public final AppUpdateReq copy(String version_name, String version_code, String mac, String product_name, String channel_name, String language) {
        Intrinsics.checkParameterIsNotNull(version_name, "version_name");
        Intrinsics.checkParameterIsNotNull(version_code, "version_code");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(channel_name, "channel_name");
        Intrinsics.checkParameterIsNotNull(language, "language");
        return new AppUpdateReq(version_name, version_code, mac, product_name, channel_name, language);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppUpdateReq)) {
            return false;
        }
        AppUpdateReq appUpdateReq = (AppUpdateReq) other;
        return Intrinsics.areEqual(this.version_name, appUpdateReq.version_name) && Intrinsics.areEqual(this.version_code, appUpdateReq.version_code) && Intrinsics.areEqual(this.mac, appUpdateReq.mac) && Intrinsics.areEqual(this.product_name, appUpdateReq.product_name) && Intrinsics.areEqual(this.channel_name, appUpdateReq.channel_name) && Intrinsics.areEqual(this.language, appUpdateReq.language);
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
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.language;
        return hashCode5 + (str6 != null ? str6.hashCode() : 0);
    }

    public String toString() {
        return "AppUpdateReq(version_name=" + this.version_name + ", version_code=" + this.version_code + ", mac=" + this.mac + ", product_name=" + this.product_name + ", channel_name=" + this.channel_name + ", language=" + this.language + ")";
    }

    public AppUpdateReq(String version_name, String version_code, String mac, String product_name, String channel_name, String language) {
        Intrinsics.checkParameterIsNotNull(version_name, "version_name");
        Intrinsics.checkParameterIsNotNull(version_code, "version_code");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(channel_name, "channel_name");
        Intrinsics.checkParameterIsNotNull(language, "language");
        this.version_name = version_name;
        this.version_code = version_code;
        this.mac = mac;
        this.product_name = product_name;
        this.channel_name = channel_name;
        this.language = language;
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

    public final String getLanguage() {
        return this.language;
    }
}
