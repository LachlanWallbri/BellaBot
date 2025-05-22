package com.pudutech.bumblebee.presenter.net.resp;

import com.aliyun.alink.p022h2.api.Constraint;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: IotRegionResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J!\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R \u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR \u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0015"}, m3961d2 = {"Lcom/pudutech/bumblebee/presenter/net/resp/IotRegionResp;", "", Constraint.REGION_ID, "", "endpoint", "(Ljava/lang/String;Ljava/lang/String;)V", "getEndpoint", "()Ljava/lang/String;", "setEndpoint", "(Ljava/lang/String;)V", "getRegionId", "setRegionId", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "module_bumblebee_presenter_robotRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class IotRegionResp {

    @SerializedName("endpoint")
    private String endpoint;

    @SerializedName(Constraint.REGION_ID)
    private String regionId;

    public static /* synthetic */ IotRegionResp copy$default(IotRegionResp iotRegionResp, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = iotRegionResp.regionId;
        }
        if ((i & 2) != 0) {
            str2 = iotRegionResp.endpoint;
        }
        return iotRegionResp.copy(str, str2);
    }

    /* renamed from: component1, reason: from getter */
    public final String getRegionId() {
        return this.regionId;
    }

    /* renamed from: component2, reason: from getter */
    public final String getEndpoint() {
        return this.endpoint;
    }

    public final IotRegionResp copy(String regionId, String endpoint) {
        return new IotRegionResp(regionId, endpoint);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IotRegionResp)) {
            return false;
        }
        IotRegionResp iotRegionResp = (IotRegionResp) other;
        return Intrinsics.areEqual(this.regionId, iotRegionResp.regionId) && Intrinsics.areEqual(this.endpoint, iotRegionResp.endpoint);
    }

    public int hashCode() {
        String str = this.regionId;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.endpoint;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public String toString() {
        return "IotRegionResp(regionId=" + this.regionId + ", endpoint=" + this.endpoint + ")";
    }

    public IotRegionResp(String str, String str2) {
        this.regionId = str;
        this.endpoint = str2;
    }

    public final String getRegionId() {
        return this.regionId;
    }

    public final void setRegionId(String str) {
        this.regionId = str;
    }

    public final String getEndpoint() {
        return this.endpoint;
    }

    public final void setEndpoint(String str) {
        this.endpoint = str;
    }
}
