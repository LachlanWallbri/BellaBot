package com.pudutech.antichannelconflict.location.network;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b'\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Be\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u0003\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003¢\u0006\u0002\u0010\u000fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\t\u0010!\u001a\u00020\u0003HÆ\u0003J\t\u0010\"\u001a\u00020\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\u0081\u0001\u0010)\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u0003HÆ\u0001J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010-\u001a\u00020.HÖ\u0001J\t\u0010/\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\t\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0011R\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0011R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0011R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0011R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0011R\u0011\u0010\n\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0011¨\u00060"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/location/network/LocationData;", "", "city", "", "province", "poi", "adcode", "street", TmpConstant.SERVICE_DESC, "country", "type", RequestParameters.SUBRESOURCE_LOCATION, "road", "radius", "citycode", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAdcode", "()Ljava/lang/String;", "getCity", "getCitycode", "getCountry", "getDesc", "getLocation", "getPoi", "getProvince", "getRadius", "getRoad", "getStreet", "getType", "component1", "component10", "component11", "component12", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class LocationData {
    private final String adcode;
    private final String city;
    private final String citycode;
    private final String country;
    private final String desc;
    private final String location;
    private final String poi;
    private final String province;
    private final String radius;
    private final String road;
    private final String street;
    private final String type;

    /* renamed from: component1, reason: from getter */
    public final String getCity() {
        return this.city;
    }

    /* renamed from: component10, reason: from getter */
    public final String getRoad() {
        return this.road;
    }

    /* renamed from: component11, reason: from getter */
    public final String getRadius() {
        return this.radius;
    }

    /* renamed from: component12, reason: from getter */
    public final String getCitycode() {
        return this.citycode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getProvince() {
        return this.province;
    }

    /* renamed from: component3, reason: from getter */
    public final String getPoi() {
        return this.poi;
    }

    /* renamed from: component4, reason: from getter */
    public final String getAdcode() {
        return this.adcode;
    }

    /* renamed from: component5, reason: from getter */
    public final String getStreet() {
        return this.street;
    }

    /* renamed from: component6, reason: from getter */
    public final String getDesc() {
        return this.desc;
    }

    /* renamed from: component7, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* renamed from: component8, reason: from getter */
    public final String getType() {
        return this.type;
    }

    /* renamed from: component9, reason: from getter */
    public final String getLocation() {
        return this.location;
    }

    public final LocationData copy(String city, String province, String poi, String adcode, String street, String desc, String country, String type, String location, String road, String radius, String citycode) {
        Intrinsics.checkParameterIsNotNull(city, "city");
        Intrinsics.checkParameterIsNotNull(province, "province");
        Intrinsics.checkParameterIsNotNull(poi, "poi");
        Intrinsics.checkParameterIsNotNull(adcode, "adcode");
        Intrinsics.checkParameterIsNotNull(street, "street");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        Intrinsics.checkParameterIsNotNull(country, "country");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(location, "location");
        Intrinsics.checkParameterIsNotNull(road, "road");
        Intrinsics.checkParameterIsNotNull(radius, "radius");
        Intrinsics.checkParameterIsNotNull(citycode, "citycode");
        return new LocationData(city, province, poi, adcode, street, desc, country, type, location, road, radius, citycode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof LocationData)) {
            return false;
        }
        LocationData locationData = (LocationData) other;
        return Intrinsics.areEqual(this.city, locationData.city) && Intrinsics.areEqual(this.province, locationData.province) && Intrinsics.areEqual(this.poi, locationData.poi) && Intrinsics.areEqual(this.adcode, locationData.adcode) && Intrinsics.areEqual(this.street, locationData.street) && Intrinsics.areEqual(this.desc, locationData.desc) && Intrinsics.areEqual(this.country, locationData.country) && Intrinsics.areEqual(this.type, locationData.type) && Intrinsics.areEqual(this.location, locationData.location) && Intrinsics.areEqual(this.road, locationData.road) && Intrinsics.areEqual(this.radius, locationData.radius) && Intrinsics.areEqual(this.citycode, locationData.citycode);
    }

    public int hashCode() {
        String str = this.city;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.province;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.poi;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.adcode;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.street;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        String str6 = this.desc;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.country;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.type;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.location;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.road;
        int hashCode10 = (hashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.radius;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.citycode;
        return hashCode11 + (str12 != null ? str12.hashCode() : 0);
    }

    public String toString() {
        return "LocationData(city=" + this.city + ", province=" + this.province + ", poi=" + this.poi + ", adcode=" + this.adcode + ", street=" + this.street + ", desc=" + this.desc + ", country=" + this.country + ", type=" + this.type + ", location=" + this.location + ", road=" + this.road + ", radius=" + this.radius + ", citycode=" + this.citycode + ")";
    }

    public LocationData(String city, String province, String poi, String adcode, String street, String desc, String country, String type, String location, String road, String radius, String citycode) {
        Intrinsics.checkParameterIsNotNull(city, "city");
        Intrinsics.checkParameterIsNotNull(province, "province");
        Intrinsics.checkParameterIsNotNull(poi, "poi");
        Intrinsics.checkParameterIsNotNull(adcode, "adcode");
        Intrinsics.checkParameterIsNotNull(street, "street");
        Intrinsics.checkParameterIsNotNull(desc, "desc");
        Intrinsics.checkParameterIsNotNull(country, "country");
        Intrinsics.checkParameterIsNotNull(type, "type");
        Intrinsics.checkParameterIsNotNull(location, "location");
        Intrinsics.checkParameterIsNotNull(road, "road");
        Intrinsics.checkParameterIsNotNull(radius, "radius");
        Intrinsics.checkParameterIsNotNull(citycode, "citycode");
        this.city = city;
        this.province = province;
        this.poi = poi;
        this.adcode = adcode;
        this.street = street;
        this.desc = desc;
        this.country = country;
        this.type = type;
        this.location = location;
        this.road = road;
        this.radius = radius;
        this.citycode = citycode;
    }

    public final String getAdcode() {
        return this.adcode;
    }

    public final String getCity() {
        return this.city;
    }

    public final String getCitycode() {
        return this.citycode;
    }

    public final String getCountry() {
        return this.country;
    }

    public final String getDesc() {
        return this.desc;
    }

    public final String getLocation() {
        return this.location;
    }

    public final String getPoi() {
        return this.poi;
    }

    public final String getProvince() {
        return this.province;
    }

    public final String getRadius() {
        return this.radius;
    }

    public final String getRoad() {
        return this.road;
    }

    public final String getStreet() {
        return this.street;
    }

    public final String getType() {
        return this.type;
    }
}
