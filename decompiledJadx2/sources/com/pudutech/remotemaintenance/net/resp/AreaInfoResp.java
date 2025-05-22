package com.pudutech.remotemaintenance.net.resp;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AreaInfoResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0012"}, m3961d2 = {"Lcom/pudutech/remotemaintenance/net/resp/AreaInfoResp;", "", "region_id", "", "url", "(Ljava/lang/String;Ljava/lang/String;)V", "getRegion_id", "()Ljava/lang/String;", "getUrl", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final /* data */ class AreaInfoResp {
    private final String region_id;
    private final String url;

    public static /* synthetic */ AreaInfoResp copy$default(AreaInfoResp areaInfoResp, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = areaInfoResp.region_id;
        }
        if ((i & 2) != 0) {
            str2 = areaInfoResp.url;
        }
        return areaInfoResp.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRegion_id() {
        return this.region_id;
    }

    /* renamed from: component2, reason: from getter */
    public final String getUrl() {
        return this.url;
    }

    public final AreaInfoResp copy(String region_id, String url) {
        Intrinsics.checkParameterIsNotNull(region_id, "region_id");
        Intrinsics.checkParameterIsNotNull(url, "url");
        return new AreaInfoResp(region_id, url);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AreaInfoResp)) {
            return false;
        }
        AreaInfoResp areaInfoResp = (AreaInfoResp) other;
        return Intrinsics.areEqual(this.region_id, areaInfoResp.region_id) && Intrinsics.areEqual(this.url, areaInfoResp.url);
    }

    public int hashCode() {
        String str = this.region_id;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.url;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "AreaInfoResp(region_id=" + this.region_id + ", url=" + this.url + ")";
    }

    public AreaInfoResp(String region_id, String url) {
        Intrinsics.checkParameterIsNotNull(region_id, "region_id");
        Intrinsics.checkParameterIsNotNull(url, "url");
        this.region_id = region_id;
        this.url = url;
    }

    public final String getRegion_id() {
        return this.region_id;
    }

    public final String getUrl() {
        return this.url;
    }
}
