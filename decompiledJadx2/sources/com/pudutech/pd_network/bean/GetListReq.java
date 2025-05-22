package com.pudutech.pd_network.bean;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: gateway.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0080\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/pd_network/bean/GetListReq;", "", "pid", "", "mac", "lang_type", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getLang_type", "()Ljava/lang/String;", "getMac", "getPid", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class GetListReq {
    private final String lang_type;
    private final String mac;
    private final String pid;

    public static /* synthetic */ GetListReq copy$default(GetListReq getListReq, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = getListReq.pid;
        }
        if ((i & 2) != 0) {
            str2 = getListReq.mac;
        }
        if ((i & 4) != 0) {
            str3 = getListReq.lang_type;
        }
        return getListReq.copy(str, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPid() {
        return this.pid;
    }

    /* renamed from: component2, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLang_type() {
        return this.lang_type;
    }

    public final GetListReq copy(String pid, String mac, String lang_type) {
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(lang_type, "lang_type");
        return new GetListReq(pid, mac, lang_type);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GetListReq)) {
            return false;
        }
        GetListReq getListReq = (GetListReq) other;
        return Intrinsics.areEqual(this.pid, getListReq.pid) && Intrinsics.areEqual(this.mac, getListReq.mac) && Intrinsics.areEqual(this.lang_type, getListReq.lang_type);
    }

    public int hashCode() {
        String str = this.pid;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.mac;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.lang_type;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "GetListReq(pid=" + this.pid + ", mac=" + this.mac + ", lang_type=" + this.lang_type + ")";
    }

    public GetListReq(String pid, String mac, String lang_type) {
        Intrinsics.checkParameterIsNotNull(pid, "pid");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(lang_type, "lang_type");
        this.pid = pid;
        this.mac = mac;
        this.lang_type = lang_type;
    }

    public final String getPid() {
        return this.pid;
    }

    public final String getMac() {
        return this.mac;
    }

    public final String getLang_type() {
        return this.lang_type;
    }
}
