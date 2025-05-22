package com.pudutech.robot.opensdk.aliyun.bean;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AliyunThingProData.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0005\u0012\b\b\u0002\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0005HÆ\u0003J\t\u0010 \u001a\u00020\u0007HÆ\u0003J\t\u0010!\u001a\u00020\u0005HÆ\u0003J\t\u0010\"\u001a\u00020\nHÆ\u0003J;\u0010#\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00052\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\u0013\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010'\u001a\u00020\u0003HÖ\u0001J\t\u0010(\u001a\u00020\u0005HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\b\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0011\"\u0004\b\u001d\u0010\u0013¨\u0006)"}, m3961d2 = {"Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingProData;", "", "id", "", "version", "", "params", "Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingParams;", "method", "sys", "Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingSys;", "(ILjava/lang/String;Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingParams;Ljava/lang/String;Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingSys;)V", "getId", "()I", "setId", "(I)V", "getMethod", "()Ljava/lang/String;", "setMethod", "(Ljava/lang/String;)V", "getParams", "()Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingParams;", "setParams", "(Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingParams;)V", "getSys", "()Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingSys;", "setSys", "(Lcom/pudutech/robot/opensdk/aliyun/bean/AliyunThingSys;)V", "getVersion", "setVersion", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "robot_open_sdk_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes7.dex */
public final /* data */ class AliyunThingProData {
    private int id;
    private String method;
    private AliyunThingParams params;
    private AliyunThingSys sys;
    private String version;

    public static /* synthetic */ AliyunThingProData copy$default(AliyunThingProData aliyunThingProData, int i, String str, AliyunThingParams aliyunThingParams, String str2, AliyunThingSys aliyunThingSys, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = aliyunThingProData.id;
        }
        if ((i2 & 2) != 0) {
            str = aliyunThingProData.version;
        }
        String str3 = str;
        if ((i2 & 4) != 0) {
            aliyunThingParams = aliyunThingProData.params;
        }
        AliyunThingParams aliyunThingParams2 = aliyunThingParams;
        if ((i2 & 8) != 0) {
            str2 = aliyunThingProData.method;
        }
        String str4 = str2;
        if ((i2 & 16) != 0) {
            aliyunThingSys = aliyunThingProData.sys;
        }
        return aliyunThingProData.copy(i, str3, aliyunThingParams2, str4, aliyunThingSys);
    }

    /* renamed from: component1, reason: from getter */
    public final int getId() {
        return this.id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getVersion() {
        return this.version;
    }

    /* renamed from: component3, reason: from getter */
    public final AliyunThingParams getParams() {
        return this.params;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMethod() {
        return this.method;
    }

    /* renamed from: component5, reason: from getter */
    public final AliyunThingSys getSys() {
        return this.sys;
    }

    public final AliyunThingProData copy(int id, String version, AliyunThingParams params, String method, AliyunThingSys sys) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(sys, "sys");
        return new AliyunThingProData(id, version, params, method, sys);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AliyunThingProData)) {
            return false;
        }
        AliyunThingProData aliyunThingProData = (AliyunThingProData) other;
        return this.id == aliyunThingProData.id && Intrinsics.areEqual(this.version, aliyunThingProData.version) && Intrinsics.areEqual(this.params, aliyunThingProData.params) && Intrinsics.areEqual(this.method, aliyunThingProData.method) && Intrinsics.areEqual(this.sys, aliyunThingProData.sys);
    }

    public int hashCode() {
        int hashCode = Integer.hashCode(this.id) * 31;
        String str = this.version;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        AliyunThingParams aliyunThingParams = this.params;
        int hashCode3 = (hashCode2 + (aliyunThingParams != null ? aliyunThingParams.hashCode() : 0)) * 31;
        String str2 = this.method;
        int hashCode4 = (hashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        AliyunThingSys aliyunThingSys = this.sys;
        return hashCode4 + (aliyunThingSys != null ? aliyunThingSys.hashCode() : 0);
    }

    public String toString() {
        return "AliyunThingProData(id=" + this.id + ", version=" + this.version + ", params=" + this.params + ", method=" + this.method + ", sys=" + this.sys + ")";
    }

    public AliyunThingProData(int i, String version, AliyunThingParams params, String method, AliyunThingSys sys) {
        Intrinsics.checkParameterIsNotNull(version, "version");
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(method, "method");
        Intrinsics.checkParameterIsNotNull(sys, "sys");
        this.id = i;
        this.version = version;
        this.params = params;
        this.method = method;
        this.sys = sys;
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    public final String getVersion() {
        return this.version;
    }

    public final void setVersion(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.version = str;
    }

    public final AliyunThingParams getParams() {
        return this.params;
    }

    public final void setParams(AliyunThingParams aliyunThingParams) {
        Intrinsics.checkParameterIsNotNull(aliyunThingParams, "<set-?>");
        this.params = aliyunThingParams;
    }

    public final String getMethod() {
        return this.method;
    }

    public final void setMethod(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.method = str;
    }

    public /* synthetic */ AliyunThingProData(int i, String str, AliyunThingParams aliyunThingParams, String str2, AliyunThingSys aliyunThingSys, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? "1.0" : str, aliyunThingParams, (i2 & 8) != 0 ? TmpConstant.METHOD_PROPERTY_POST : str2, (i2 & 16) != 0 ? new AliyunThingSys(0, 1, null) : aliyunThingSys);
    }

    public final AliyunThingSys getSys() {
        return this.sys;
    }

    public final void setSys(AliyunThingSys aliyunThingSys) {
        Intrinsics.checkParameterIsNotNull(aliyunThingSys, "<set-?>");
        this.sys = aliyunThingSys;
    }
}
