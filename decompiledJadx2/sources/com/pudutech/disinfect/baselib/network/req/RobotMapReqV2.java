package com.pudutech.disinfect.baselib.network.req;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RobotMapReqV2.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001BK\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0006\u0012\b\b\u0002\u0010\n\u001a\u00020\u0003¢\u0006\u0002\u0010\u000bJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0006HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003JO\u0010$\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0003HÆ\u0001J\u0013\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010(\u001a\u00020\u0006HÖ\u0001J\t\u0010)\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\t\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\f\"\u0004\b\u0010\u0010\u000eR\u001a\u0010\b\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u001a\u0010\u0007\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u0014R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0012\"\u0004\b\u0018\u0010\u0014R\u001a\u0010\n\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0012\"\u0004\b\u001a\u0010\u0014R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0012\"\u0004\b\u001c\u0010\u0014¨\u0006*"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/RobotMapReqV2;", "", "name", "", "url", "lv", "", "md5", "mac", "is_use", "point_info", "(Ljava/lang/String;Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;ILjava/lang/String;)V", "()I", "set_use", "(I)V", "getLv", "setLv", "getMac", "()Ljava/lang/String;", "setMac", "(Ljava/lang/String;)V", "getMd5", "setMd5", "getName", "setName", "getPoint_info", "setPoint_info", "getUrl", "setUrl", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "", "other", "hashCode", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class RobotMapReqV2 {
    private int is_use;
    private int lv;
    private String mac;
    private String md5;
    private String name;
    private String point_info;
    private String url;

    public RobotMapReqV2() {
        this(null, null, 0, null, null, 0, null, 127, null);
    }

    public static /* synthetic */ RobotMapReqV2 copy$default(RobotMapReqV2 robotMapReqV2, String str, String str2, int i, String str3, String str4, int i2, String str5, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = robotMapReqV2.name;
        }
        if ((i3 & 2) != 0) {
            str2 = robotMapReqV2.url;
        }
        String str6 = str2;
        if ((i3 & 4) != 0) {
            i = robotMapReqV2.lv;
        }
        int i4 = i;
        if ((i3 & 8) != 0) {
            str3 = robotMapReqV2.md5;
        }
        String str7 = str3;
        if ((i3 & 16) != 0) {
            str4 = robotMapReqV2.mac;
        }
        String str8 = str4;
        if ((i3 & 32) != 0) {
            i2 = robotMapReqV2.is_use;
        }
        int i5 = i2;
        if ((i3 & 64) != 0) {
            str5 = robotMapReqV2.point_info;
        }
        return robotMapReqV2.copy(str, str6, i4, str7, str8, i5, str5);
    }

    /* renamed from: component1, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    /* renamed from: component3, reason: from getter */
    public final int getLv() {
        return this.lv;
    }

    /* renamed from: component4, reason: from getter */
    public final String getMd5() {
        return this.md5;
    }

    /* renamed from: component5, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    /* renamed from: component6, reason: from getter */
    public final int getIs_use() {
        return this.is_use;
    }

    /* renamed from: component7, reason: from getter */
    public final String getPoint_info() {
        return this.point_info;
    }

    public final RobotMapReqV2 copy(String name, String url, int lv, String md5, String mac, int is_use, String point_info) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(point_info, "point_info");
        return new RobotMapReqV2(name, url, lv, md5, mac, is_use, point_info);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RobotMapReqV2)) {
            return false;
        }
        RobotMapReqV2 robotMapReqV2 = (RobotMapReqV2) other;
        return Intrinsics.areEqual(this.name, robotMapReqV2.name) && Intrinsics.areEqual(this.url, robotMapReqV2.url) && this.lv == robotMapReqV2.lv && Intrinsics.areEqual(this.md5, robotMapReqV2.md5) && Intrinsics.areEqual(this.mac, robotMapReqV2.mac) && this.is_use == robotMapReqV2.is_use && Intrinsics.areEqual(this.point_info, robotMapReqV2.point_info);
    }

    public int hashCode() {
        String str = this.name;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.url;
        int hashCode2 = (((hashCode + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.lv)) * 31;
        String str3 = this.md5;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.mac;
        int hashCode4 = (((hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31) + Integer.hashCode(this.is_use)) * 31;
        String str5 = this.point_info;
        return hashCode4 + (str5 != null ? str5.hashCode() : 0);
    }

    public String toString() {
        return "RobotMapReqV2(name=" + this.name + ", url=" + this.url + ", lv=" + this.lv + ", md5=" + this.md5 + ", mac=" + this.mac + ", is_use=" + this.is_use + ", point_info=" + this.point_info + ")";
    }

    public RobotMapReqV2(String name, String url, int i, String md5, String mac, int i2, String point_info) {
        Intrinsics.checkParameterIsNotNull(name, "name");
        Intrinsics.checkParameterIsNotNull(url, "url");
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        Intrinsics.checkParameterIsNotNull(point_info, "point_info");
        this.name = name;
        this.url = url;
        this.lv = i;
        this.md5 = md5;
        this.mac = mac;
        this.is_use = i2;
        this.point_info = point_info;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final String getUrl() {
        return this.url;
    }

    public final void setUrl(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.url = str;
    }

    public final int getLv() {
        return this.lv;
    }

    public final void setLv(int i) {
        this.lv = i;
    }

    public final String getMd5() {
        return this.md5;
    }

    public final void setMd5(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.md5 = str;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final int is_use() {
        return this.is_use;
    }

    public final void set_use(int i) {
        this.is_use = i;
    }

    public /* synthetic */ RobotMapReqV2(String str, String str2, int i, String str3, String str4, int i2, String str5, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? "" : str, (i3 & 2) != 0 ? "" : str2, (i3 & 4) != 0 ? 0 : i, (i3 & 8) != 0 ? "" : str3, (i3 & 16) != 0 ? "" : str4, (i3 & 32) != 0 ? 0 : i2, (i3 & 64) != 0 ? "" : str5);
    }

    public final String getPoint_info() {
        return this.point_info;
    }

    public final void setPoint_info(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.point_info = str;
    }
}
