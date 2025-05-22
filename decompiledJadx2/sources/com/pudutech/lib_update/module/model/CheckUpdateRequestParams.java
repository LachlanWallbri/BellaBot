package com.pudutech.lib_update.module.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CheckUpdateRequestParams.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b$\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BU\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003JY\u0010&\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010'\u001a\u00020(2\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010*\u001a\u00020+HÖ\u0001J\t\u0010,\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\t\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\r\"\u0004\b\u0013\u0010\u000fR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\r\"\u0004\b\u0015\u0010\u000fR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\r\"\u0004\b\u0017\u0010\u000fR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\r\"\u0004\b\u0019\u0010\u000fR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\r\"\u0004\b\u001b\u0010\u000fR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\r\"\u0004\b\u001d\u0010\u000f¨\u0006-"}, m3961d2 = {"Lcom/pudutech/lib_update/module/model/CheckUpdateRequestParams;", "", "version_name", "", "version_code", "mac", "product_name", "request_ver_name", "request_ver_code", "app_version", "language", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getApp_version", "()Ljava/lang/String;", "setApp_version", "(Ljava/lang/String;)V", "getLanguage", "setLanguage", "getMac", "setMac", "getProduct_name", "setProduct_name", "getRequest_ver_code", "setRequest_ver_code", "getRequest_ver_name", "setRequest_ver_name", "getVersion_code", "setVersion_code", "getVersion_name", "setVersion_name", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "equals", "", "other", "hashCode", "", "toString", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class CheckUpdateRequestParams {
    private String app_version;
    private String language;
    private String mac;
    private String product_name;
    private String request_ver_code;
    private String request_ver_name;
    private String version_code;
    private String version_name;

    public CheckUpdateRequestParams() {
        this(null, null, null, null, null, null, null, null, 255, null);
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
    public final String getLanguage() {
        return this.language;
    }

    public final CheckUpdateRequestParams copy(String version_name, String version_code, String mac, String product_name, String request_ver_name, String request_ver_code, String app_version, String language) {
        Intrinsics.checkParameterIsNotNull(version_name, "version_name");
        Intrinsics.checkParameterIsNotNull(version_code, "version_code");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(request_ver_name, "request_ver_name");
        Intrinsics.checkParameterIsNotNull(request_ver_code, "request_ver_code");
        Intrinsics.checkParameterIsNotNull(app_version, "app_version");
        Intrinsics.checkParameterIsNotNull(language, "language");
        return new CheckUpdateRequestParams(version_name, version_code, mac, product_name, request_ver_name, request_ver_code, app_version, language);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof CheckUpdateRequestParams)) {
            return false;
        }
        CheckUpdateRequestParams checkUpdateRequestParams = (CheckUpdateRequestParams) other;
        return Intrinsics.areEqual(this.version_name, checkUpdateRequestParams.version_name) && Intrinsics.areEqual(this.version_code, checkUpdateRequestParams.version_code) && Intrinsics.areEqual(this.mac, checkUpdateRequestParams.mac) && Intrinsics.areEqual(this.product_name, checkUpdateRequestParams.product_name) && Intrinsics.areEqual(this.request_ver_name, checkUpdateRequestParams.request_ver_name) && Intrinsics.areEqual(this.request_ver_code, checkUpdateRequestParams.request_ver_code) && Intrinsics.areEqual(this.app_version, checkUpdateRequestParams.app_version) && Intrinsics.areEqual(this.language, checkUpdateRequestParams.language);
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
        String str8 = this.language;
        return hashCode7 + (str8 != null ? str8.hashCode() : 0);
    }

    public String toString() {
        return "CheckUpdateRequestParams(version_name=" + this.version_name + ", version_code=" + this.version_code + ", mac=" + this.mac + ", product_name=" + this.product_name + ", request_ver_name=" + this.request_ver_name + ", request_ver_code=" + this.request_ver_code + ", app_version=" + this.app_version + ", language=" + this.language + ")";
    }

    public CheckUpdateRequestParams(String version_name, String version_code, String mac, String product_name, String request_ver_name, String request_ver_code, String app_version, String language) {
        Intrinsics.checkParameterIsNotNull(version_name, "version_name");
        Intrinsics.checkParameterIsNotNull(version_code, "version_code");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(product_name, "product_name");
        Intrinsics.checkParameterIsNotNull(request_ver_name, "request_ver_name");
        Intrinsics.checkParameterIsNotNull(request_ver_code, "request_ver_code");
        Intrinsics.checkParameterIsNotNull(app_version, "app_version");
        Intrinsics.checkParameterIsNotNull(language, "language");
        this.version_name = version_name;
        this.version_code = version_code;
        this.mac = mac;
        this.product_name = product_name;
        this.request_ver_name = request_ver_name;
        this.request_ver_code = request_ver_code;
        this.app_version = app_version;
        this.language = language;
    }

    public final String getVersion_name() {
        return this.version_name;
    }

    public final void setVersion_name(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.version_name = str;
    }

    public final String getVersion_code() {
        return this.version_code;
    }

    public final void setVersion_code(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.version_code = str;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final String getProduct_name() {
        return this.product_name;
    }

    public final void setProduct_name(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.product_name = str;
    }

    public final String getRequest_ver_name() {
        return this.request_ver_name;
    }

    public final void setRequest_ver_name(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.request_ver_name = str;
    }

    public final String getRequest_ver_code() {
        return this.request_ver_code;
    }

    public final void setRequest_ver_code(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.request_ver_code = str;
    }

    public final String getApp_version() {
        return this.app_version;
    }

    public final void setApp_version(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.app_version = str;
    }

    public /* synthetic */ CheckUpdateRequestParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "1.0.0.0" : str, (i & 2) != 0 ? "1" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6, (i & 64) != 0 ? "" : str7, (i & 128) == 0 ? str8 : "");
    }

    public final String getLanguage() {
        return this.language;
    }

    public final void setLanguage(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.language = str;
    }
}
