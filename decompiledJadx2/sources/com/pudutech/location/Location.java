package com.pudutech.location;

import com.aliyun.alink.linksdk.tools.p045ut.AUserTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\tHÆ\u0003JE\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\tHÖ\u0001J\b\u0010\u001e\u001a\u00020\u0003H\u0016R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/location/Location;", "", "longitude", "", "latitude", "type", "accuracy", "address", AUserTrack.UTKEY_ERROR_CODE, "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)V", "getAccuracy", "()Ljava/lang/String;", "getAddress", "getErrorCode", "()I", "getLatitude", "getLongitude", "getType", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "equals", "", "other", "hashCode", "toString", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class Location {
    private final String accuracy;
    private final String address;
    private final int errorCode;
    private final String latitude;
    private final String longitude;
    private final String type;

    public static /* synthetic */ Location copy$default(Location location, String str, String str2, String str3, String str4, String str5, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = location.longitude;
        }
        if ((i2 & 2) != 0) {
            str2 = location.latitude;
        }
        String str6 = str2;
        if ((i2 & 4) != 0) {
            str3 = location.type;
        }
        String str7 = str3;
        if ((i2 & 8) != 0) {
            str4 = location.accuracy;
        }
        String str8 = str4;
        if ((i2 & 16) != 0) {
            str5 = location.address;
        }
        String str9 = str5;
        if ((i2 & 32) != 0) {
            i = location.errorCode;
        }
        return location.copy(str, str6, str7, str8, str9, i);
    }

    /* renamed from: component1, reason: from getter */
    public final String getLongitude() {
        return this.longitude;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLatitude() {
        return this.latitude;
    }

    /* renamed from: component3, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAccuracy() {
        return this.accuracy;
    }

    /* renamed from: component5, reason: from getter */
    public final String getAddress() {
        return this.address;
    }

    /* renamed from: component6, reason: from getter */
    public final int getErrorCode() {
        return this.errorCode;
    }

    public final Location copy(String longitude, String latitude, String type, String accuracy, String address, int errorCode) {
        Intrinsics.checkParameterIsNotNull(longitude, "longitude");
        Intrinsics.checkParameterIsNotNull(latitude, "latitude");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(accuracy, "accuracy");
        Intrinsics.checkParameterIsNotNull(address, "address");
        return new Location(longitude, latitude, type, accuracy, address, errorCode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Location)) {
            return false;
        }
        Location location = (Location) other;
        return Intrinsics.areEqual(this.longitude, location.longitude) && Intrinsics.areEqual(this.latitude, location.latitude) && Intrinsics.areEqual(this.type, location.type) && Intrinsics.areEqual(this.accuracy, location.accuracy) && Intrinsics.areEqual(this.address, location.address) && this.errorCode == location.errorCode;
    }

    public int hashCode() {
        String str = this.longitude;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.latitude;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.type;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.accuracy;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.address;
        return ((hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31) + Integer.hashCode(this.errorCode);
    }

    public Location(String longitude, String latitude, String type, String accuracy, String address, int i) {
        Intrinsics.checkParameterIsNotNull(longitude, "longitude");
        Intrinsics.checkParameterIsNotNull(latitude, "latitude");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(accuracy, "accuracy");
        Intrinsics.checkParameterIsNotNull(address, "address");
        this.longitude = longitude;
        this.latitude = latitude;
        this.type = type;
        this.accuracy = accuracy;
        this.address = address;
        this.errorCode = i;
    }

    public final String getLongitude() {
        return this.longitude;
    }

    public final String getLatitude() {
        return this.latitude;
    }

    public final String getType() {
        return this.type;
    }

    public final String getAccuracy() {
        return this.accuracy;
    }

    public final String getAddress() {
        return this.address;
    }

    public final int getErrorCode() {
        return this.errorCode;
    }

    public String toString() {
        return "经度:" + this.longitude + ", 纬度:" + this.latitude + ", 定位结果来源:" + this.type + ", 定位精度:" + this.accuracy + "(米), 详细地址:" + this.address + ", 错误码:" + this.errorCode;
    }
}
