package com.pudutech.pd_network.sn;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SNUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\u0006\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\t\"\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\t¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/pd_network/sn/SNBean;", "", "sn", "", "packageName", "time", "random", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getPackageName", "()Ljava/lang/String;", "getRandom", "setRandom", "(Ljava/lang/String;)V", "getSn", "getTime", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "pd_network_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final /* data */ class SNBean {
    private final String packageName;
    private String random;
    private final String sn;
    private final String time;

    public static /* synthetic */ SNBean copy$default(SNBean sNBean, String str, String str2, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = sNBean.sn;
        }
        if ((i & 2) != 0) {
            str2 = sNBean.packageName;
        }
        if ((i & 4) != 0) {
            str3 = sNBean.time;
        }
        if ((i & 8) != 0) {
            str4 = sNBean.random;
        }
        return sNBean.copy(str, str2, str3, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getSn() {
        return this.sn;
    }

    /* renamed from: component2, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getTime() {
        return this.time;
    }

    /* renamed from: component4, reason: from getter */
    public final String getRandom() {
        return this.random;
    }

    public final SNBean copy(String sn, String packageName, String time, String random) {
        Intrinsics.checkParameterIsNotNull(sn, "sn");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(random, "random");
        return new SNBean(sn, packageName, time, random);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SNBean)) {
            return false;
        }
        SNBean sNBean = (SNBean) other;
        return Intrinsics.areEqual(this.sn, sNBean.sn) && Intrinsics.areEqual(this.packageName, sNBean.packageName) && Intrinsics.areEqual(this.time, sNBean.time) && Intrinsics.areEqual(this.random, sNBean.random);
    }

    public int hashCode() {
        String str = this.sn;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.packageName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.time;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.random;
        return hashCode3 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "SNBean(sn=" + this.sn + ", packageName=" + this.packageName + ", time=" + this.time + ", random=" + this.random + ")";
    }

    public SNBean(String sn, String packageName, String time, String random) {
        Intrinsics.checkParameterIsNotNull(sn, "sn");
        Intrinsics.checkParameterIsNotNull(packageName, "packageName");
        Intrinsics.checkParameterIsNotNull(time, "time");
        Intrinsics.checkParameterIsNotNull(random, "random");
        this.sn = sn;
        this.packageName = packageName;
        this.time = time;
        this.random = random;
    }

    public final String getSn() {
        return this.sn;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final String getTime() {
        return this.time;
    }

    public /* synthetic */ SNBean(String str, String str2, String str3, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? "" : str4);
    }

    public final String getRandom() {
        return this.random;
    }

    public final void setRandom(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.random = str;
    }
}
