package com.pudutech.disinfect.baselib.network.response;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: RespApkVersionData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0007J\u000b\u0010\u0012\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÆ\u0003J+\u0010\u0015\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\t\"\u0004\b\u0011\u0010\u000b¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/RespApkVersionData;", "", "versionName", "", "versionCode", "", "pkName", "(Ljava/lang/String;ILjava/lang/String;)V", "getPkName", "()Ljava/lang/String;", "setPkName", "(Ljava/lang/String;)V", "getVersionCode", "()I", "setVersionCode", "(I)V", "getVersionName", "setVersionName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class RespApkVersionData {
    private String pkName;
    private int versionCode;
    private String versionName;

    public static /* synthetic */ RespApkVersionData copy$default(RespApkVersionData respApkVersionData, String str, int i, String str2, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = respApkVersionData.versionName;
        }
        if ((i2 & 2) != 0) {
            i = respApkVersionData.versionCode;
        }
        if ((i2 & 4) != 0) {
            str2 = respApkVersionData.pkName;
        }
        return respApkVersionData.copy(str, i, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getVersionName() {
        return this.versionName;
    }

    /* renamed from: component2, reason: from getter */
    public final int getVersionCode() {
        return this.versionCode;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPkName() {
        return this.pkName;
    }

    public final RespApkVersionData copy(String versionName, int versionCode, String pkName) {
        return new RespApkVersionData(versionName, versionCode, pkName);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RespApkVersionData)) {
            return false;
        }
        RespApkVersionData respApkVersionData = (RespApkVersionData) other;
        return Intrinsics.areEqual(this.versionName, respApkVersionData.versionName) && this.versionCode == respApkVersionData.versionCode && Intrinsics.areEqual(this.pkName, respApkVersionData.pkName);
    }

    public int hashCode() {
        String str = this.versionName;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.versionCode)) * 31;
        String str2 = this.pkName;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "RespApkVersionData(versionName=" + this.versionName + ", versionCode=" + this.versionCode + ", pkName=" + this.pkName + ")";
    }

    public RespApkVersionData(String str, int i, String str2) {
        this.versionName = str;
        this.versionCode = i;
        this.pkName = str2;
    }

    public final String getPkName() {
        return this.pkName;
    }

    public final int getVersionCode() {
        return this.versionCode;
    }

    public final String getVersionName() {
        return this.versionName;
    }

    public final void setPkName(String str) {
        this.pkName = str;
    }

    public final void setVersionCode(int i) {
        this.versionCode = i;
    }

    public final void setVersionName(String str) {
        this.versionName = str;
    }
}
