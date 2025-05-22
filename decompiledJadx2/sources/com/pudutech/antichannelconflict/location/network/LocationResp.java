package com.pudutech.antichannelconflict.location.network;

import com.iflytek.cloud.SpeechUtility;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0018HÖ\u0001J\t\u0010\u0019\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\n¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/location/network/LocationResp;", "", "infocode", "", "info", "status", SpeechUtility.TAG_RESOURCE_RESULT, "Lcom/pudutech/antichannelconflict/location/network/LocationData;", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/antichannelconflict/location/network/LocationData;)V", "getInfo", "()Ljava/lang/String;", "getInfocode", "getResult", "()Lcom/pudutech/antichannelconflict/location/network/LocationData;", "getStatus", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LocationResp {
    private final String info;
    private final String infocode;
    private final LocationData result;
    private final String status;

    public static /* synthetic */ LocationResp copy$default(LocationResp locationResp, String str, String str2, String str3, LocationData locationData, int i, Object obj) {
        if ((i & 1) != 0) {
            str = locationResp.infocode;
        }
        if ((i & 2) != 0) {
            str2 = locationResp.info;
        }
        if ((i & 4) != 0) {
            str3 = locationResp.status;
        }
        if ((i & 8) != 0) {
            locationData = locationResp.result;
        }
        return locationResp.copy(str, str2, str3, locationData);
    }

    /* renamed from: component1, reason: from getter */
    public final String getInfocode() {
        return this.infocode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getInfo() {
        return this.info;
    }

    /* renamed from: component3, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component4, reason: from getter */
    public final LocationData getResult() {
        return this.result;
    }

    public final LocationResp copy(String infocode, String info, String status, LocationData result) {
        Intrinsics.checkParameterIsNotNull(infocode, "infocode");
        Intrinsics.checkParameterIsNotNull(info, "info");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(result, "result");
        return new LocationResp(infocode, info, status, result);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocationResp)) {
            return false;
        }
        LocationResp locationResp = (LocationResp) other;
        return Intrinsics.areEqual(this.infocode, locationResp.infocode) && Intrinsics.areEqual(this.info, locationResp.info) && Intrinsics.areEqual(this.status, locationResp.status) && Intrinsics.areEqual(this.result, locationResp.result);
    }

    public int hashCode() {
        String str = this.infocode;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.info;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.status;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        LocationData locationData = this.result;
        return hashCode3 + (locationData != null ? locationData.hashCode() : 0);
    }

    public String toString() {
        return "LocationResp(infocode=" + this.infocode + ", info=" + this.info + ", status=" + this.status + ", result=" + this.result + ")";
    }

    public LocationResp(String infocode, String info, String status, LocationData result) {
        Intrinsics.checkParameterIsNotNull(infocode, "infocode");
        Intrinsics.checkParameterIsNotNull(info, "info");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(result, "result");
        this.infocode = infocode;
        this.info = info;
        this.status = status;
        this.result = result;
    }

    public final String getInfo() {
        return this.info;
    }

    public final String getInfocode() {
        return this.infocode;
    }

    public final LocationData getResult() {
        return this.result;
    }

    public final String getStatus() {
        return this.status;
    }
}
