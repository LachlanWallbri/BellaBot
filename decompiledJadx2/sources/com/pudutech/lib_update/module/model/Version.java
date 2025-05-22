package com.pudutech.lib_update.module.model;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Version.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u001c\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BA\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003¢\u0006\u0002\u0010\tJ\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003JE\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\"\u001a\u00020#HÖ\u0001J\t\u0010$\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u000b\"\u0004\b\u0013\u0010\rR\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u000b\"\u0004\b\u0015\u0010\rR\u001a\u0010\u0005\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000b\"\u0004\b\u0017\u0010\r¨\u0006%"}, m3961d2 = {"Lcom/pudutech/lib_update/module/model/Version;", "", "url", "", "md5", "version_name", "version_code", "version_description", "level", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLevel", "()Ljava/lang/String;", "setLevel", "(Ljava/lang/String;)V", "getMd5", "setMd5", "getUrl", "setUrl", "getVersion_code", "setVersion_code", "getVersion_description", "setVersion_description", "getVersion_name", "setVersion_name", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "", "toString", "lib_update_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class Version {
    private String level;
    private String md5;
    private String url;
    private String version_code;
    private String version_description;
    private String version_name;

    public Version() {
        this(null, null, null, null, null, null, 63, null);
    }

    public static /* synthetic */ Version copy$default(Version version, String str, String str2, String str3, String str4, String str5, String str6, int i, Object obj) {
        if ((i & 1) != 0) {
            str = version.url;
        }
        if ((i & 2) != 0) {
            str2 = version.md5;
        }
        String str7 = str2;
        if ((i & 4) != 0) {
            str3 = version.version_name;
        }
        String str8 = str3;
        if ((i & 8) != 0) {
            str4 = version.version_code;
        }
        String str9 = str4;
        if ((i & 16) != 0) {
            str5 = version.version_description;
        }
        String str10 = str5;
        if ((i & 32) != 0) {
            str6 = version.level;
        }
        return version.copy(str, str7, str8, str9, str10, str6);
    }

    /* renamed from: component1, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMd5() {
        return this.md5;
    }

    /* renamed from: component3, reason: from getter */
    public final String getVersion_name() {
        return this.version_name;
    }

    /* renamed from: component4, reason: from getter */
    public final String getVersion_code() {
        return this.version_code;
    }

    /* renamed from: component5, reason: from getter */
    public final String getVersion_description() {
        return this.version_description;
    }

    /* renamed from: component6, reason: from getter */
    public final String getLevel() {
        return this.level;
    }

    public final Version copy(String url, String md5, String version_name, String version_code, String version_description, String level) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(version_name, "version_name");
        Intrinsics.checkParameterIsNotNull(version_code, "version_code");
        Intrinsics.checkParameterIsNotNull(version_description, "version_description");
        Intrinsics.checkParameterIsNotNull(level, "level");
        return new Version(url, md5, version_name, version_code, version_description, level);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Version)) {
            return false;
        }
        Version version = (Version) other;
        return Intrinsics.areEqual(this.url, version.url) && Intrinsics.areEqual(this.md5, version.md5) && Intrinsics.areEqual(this.version_name, version.version_name) && Intrinsics.areEqual(this.version_code, version.version_code) && Intrinsics.areEqual(this.version_description, version.version_description) && Intrinsics.areEqual(this.level, version.level);
    }

    public int hashCode() {
        String str = this.url;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.md5;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.version_name;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.version_code;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.version_description;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.level;
        return hashCode5 + (str6 != null ? str6.hashCode() : 0);
    }

    public String toString() {
        return "Version(url=" + this.url + ", md5=" + this.md5 + ", version_name=" + this.version_name + ", version_code=" + this.version_code + ", version_description=" + this.version_description + ", level=" + this.level + ")";
    }

    public Version(String url, String md5, String version_name, String version_code, String version_description, String level) {
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(version_name, "version_name");
        Intrinsics.checkParameterIsNotNull(version_code, "version_code");
        Intrinsics.checkParameterIsNotNull(version_description, "version_description");
        Intrinsics.checkParameterIsNotNull(level, "level");
        this.url = url;
        this.md5 = md5;
        this.version_name = version_name;
        this.version_code = version_code;
        this.version_description = version_description;
        this.level = level;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.url = str;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.md5 = str;
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

    public final String getVersion_description() {
        return this.version_description;
    }

    public final void setVersion_description(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.version_description = str;
    }

    public /* synthetic */ Version(String str, String str2, String str3, String str4, String str5, String str6, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? "" : str, (i & 2) != 0 ? "" : str2, (i & 4) != 0 ? "" : str3, (i & 8) != 0 ? "" : str4, (i & 16) != 0 ? "" : str5, (i & 32) != 0 ? "" : str6);
    }

    public final String getLevel() {
        return this.level;
    }

    public final void setLevel(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.level = str;
    }
}
