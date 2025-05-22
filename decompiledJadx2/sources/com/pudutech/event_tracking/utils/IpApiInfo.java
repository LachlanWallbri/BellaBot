package com.pudutech.event_tracking.utils;

import com.pudutech.remotemaintenance.aliyun.config.OSSConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

/* compiled from: IpApiInfoUtils.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b)\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bu\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\u0003\u0012\u0006\u0010\f\u001a\u00020\u0003\u0012\u0006\u0010\r\u001a\u00020\u0003\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003\u0012\u0006\u0010\u0010\u001a\u00020\u0003\u0012\u0006\u0010\u0011\u001a\u00020\u0003¢\u0006\u0002\u0010\u0012J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\u0003HÆ\u0003J\t\u0010-\u001a\u00020\tHÆ\u0003J\t\u0010.\u001a\u00020\tHÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\t\u00100\u001a\u00020\u0003HÆ\u0003J\u0095\u0001\u00101\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0003HÆ\u0001J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u000206HÖ\u0001J\t\u00107\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u000b\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0014R\u0011\u0010\f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u0011\u0010\r\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0014¨\u00068"}, m3961d2 = {"Lcom/pudutech/event_tracking/utils/IpApiInfo;", "", "as", "", "city", "country", "countryCode", "isp", "lat", "", "lon", "org", "query", OSSConfig.PARAM_REGION, "regionName", "status", "timezone", ArchiveStreamFactory.ZIP, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DDLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getAs", "()Ljava/lang/String;", "getCity", "getCountry", "getCountryCode", "getIsp", "getLat", "()D", "getLon", "getOrg", "getQuery", "getRegion", "getRegionName", "getStatus", "getTimezone", "getZip", "component1", "component10", "component11", "component12", "component13", "component14", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "", "other", "hashCode", "", "toString", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class IpApiInfo {
    private final String as;
    private final String city;
    private final String country;
    private final String countryCode;
    private final String isp;
    private final double lat;
    private final double lon;
    private final String org;
    private final String query;
    private final String region;
    private final String regionName;
    private final String status;
    private final String timezone;
    private final String zip;

    /* renamed from: component1, reason: from getter */
    public final String getAs() {
        return this.as;
    }

    /* renamed from: component10, reason: from getter */
    public final String getRegion() {
        return this.region;
    }

    /* renamed from: component11, reason: from getter */
    public final String getRegionName() {
        return this.regionName;
    }

    /* renamed from: component12, reason: from getter */
    public final String getStatus() {
        return this.status;
    }

    /* renamed from: component13, reason: from getter */
    public final String getTimezone() {
        return this.timezone;
    }

    /* renamed from: component14, reason: from getter */
    public final String getZip() {
        return this.zip;
    }

    /* renamed from: component2, reason: from getter */
    public final String getCity() {
        return this.city;
    }

    /* renamed from: component3, reason: from getter */
    public final String getCountry() {
        return this.country;
    }

    /* renamed from: component4, reason: from getter */
    public final String getCountryCode() {
        return this.countryCode;
    }

    /* renamed from: component5, reason: from getter */
    public final String getIsp() {
        return this.isp;
    }

    /* renamed from: component6, reason: from getter */
    public final double getLat() {
        return this.lat;
    }

    /* renamed from: component7, reason: from getter */
    public final double getLon() {
        return this.lon;
    }

    /* renamed from: component8, reason: from getter */
    public final String getOrg() {
        return this.org;
    }

    /* renamed from: component9, reason: from getter */
    public final String getQuery() {
        return this.query;
    }

    public final IpApiInfo copy(String as, String city, String country, String countryCode, String isp, double lat, double lon, String org2, String query, String region, String regionName, String status, String timezone, String zip) {
        Intrinsics.checkParameterIsNotNull(as, "as");
        Intrinsics.checkParameterIsNotNull(city, "city");
        Intrinsics.checkParameterIsNotNull(country, "country");
        Intrinsics.checkParameterIsNotNull(countryCode, "countryCode");
        Intrinsics.checkParameterIsNotNull(isp, "isp");
        Intrinsics.checkParameterIsNotNull(org2, "org");
        Intrinsics.checkParameterIsNotNull(query, "query");
        Intrinsics.checkParameterIsNotNull(region, "region");
        Intrinsics.checkParameterIsNotNull(regionName, "regionName");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(timezone, "timezone");
        Intrinsics.checkParameterIsNotNull(zip, "zip");
        return new IpApiInfo(as, city, country, countryCode, isp, lat, lon, org2, query, region, regionName, status, timezone, zip);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof IpApiInfo)) {
            return false;
        }
        IpApiInfo ipApiInfo = (IpApiInfo) other;
        return Intrinsics.areEqual(this.as, ipApiInfo.as) && Intrinsics.areEqual(this.city, ipApiInfo.city) && Intrinsics.areEqual(this.country, ipApiInfo.country) && Intrinsics.areEqual(this.countryCode, ipApiInfo.countryCode) && Intrinsics.areEqual(this.isp, ipApiInfo.isp) && Double.compare(this.lat, ipApiInfo.lat) == 0 && Double.compare(this.lon, ipApiInfo.lon) == 0 && Intrinsics.areEqual(this.org, ipApiInfo.org) && Intrinsics.areEqual(this.query, ipApiInfo.query) && Intrinsics.areEqual(this.region, ipApiInfo.region) && Intrinsics.areEqual(this.regionName, ipApiInfo.regionName) && Intrinsics.areEqual(this.status, ipApiInfo.status) && Intrinsics.areEqual(this.timezone, ipApiInfo.timezone) && Intrinsics.areEqual(this.zip, ipApiInfo.zip);
    }

    public int hashCode() {
        String str = this.as;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.city;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.country;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.countryCode;
        int hashCode4 = (hashCode3 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.isp;
        int hashCode5 = (((((hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31) + Double.hashCode(this.lat)) * 31) + Double.hashCode(this.lon)) * 31;
        String str6 = this.org;
        int hashCode6 = (hashCode5 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.query;
        int hashCode7 = (hashCode6 + (str7 != null ? str7.hashCode() : 0)) * 31;
        String str8 = this.region;
        int hashCode8 = (hashCode7 + (str8 != null ? str8.hashCode() : 0)) * 31;
        String str9 = this.regionName;
        int hashCode9 = (hashCode8 + (str9 != null ? str9.hashCode() : 0)) * 31;
        String str10 = this.status;
        int hashCode10 = (hashCode9 + (str10 != null ? str10.hashCode() : 0)) * 31;
        String str11 = this.timezone;
        int hashCode11 = (hashCode10 + (str11 != null ? str11.hashCode() : 0)) * 31;
        String str12 = this.zip;
        return hashCode11 + (str12 != null ? str12.hashCode() : 0);
    }

    public String toString() {
        return "IpApiInfo(as=" + this.as + ", city=" + this.city + ", country=" + this.country + ", countryCode=" + this.countryCode + ", isp=" + this.isp + ", lat=" + this.lat + ", lon=" + this.lon + ", org=" + this.org + ", query=" + this.query + ", region=" + this.region + ", regionName=" + this.regionName + ", status=" + this.status + ", timezone=" + this.timezone + ", zip=" + this.zip + ")";
    }

    public IpApiInfo(String as, String city, String country, String countryCode, String isp, double d, double d2, String org2, String query, String region, String regionName, String status, String timezone, String zip) {
        Intrinsics.checkParameterIsNotNull(as, "as");
        Intrinsics.checkParameterIsNotNull(city, "city");
        Intrinsics.checkParameterIsNotNull(country, "country");
        Intrinsics.checkParameterIsNotNull(countryCode, "countryCode");
        Intrinsics.checkParameterIsNotNull(isp, "isp");
        Intrinsics.checkParameterIsNotNull(org2, "org");
        Intrinsics.checkParameterIsNotNull(query, "query");
        Intrinsics.checkParameterIsNotNull(region, "region");
        Intrinsics.checkParameterIsNotNull(regionName, "regionName");
        Intrinsics.checkParameterIsNotNull(status, "status");
        Intrinsics.checkParameterIsNotNull(timezone, "timezone");
        Intrinsics.checkParameterIsNotNull(zip, "zip");
        this.as = as;
        this.city = city;
        this.country = country;
        this.countryCode = countryCode;
        this.isp = isp;
        this.lat = d;
        this.lon = d2;
        this.org = org2;
        this.query = query;
        this.region = region;
        this.regionName = regionName;
        this.status = status;
        this.timezone = timezone;
        this.zip = zip;
    }

    public final String getAs() {
        return this.as;
    }

    public final String getCity() {
        return this.city;
    }

    public final String getCountry() {
        return this.country;
    }

    public final String getCountryCode() {
        return this.countryCode;
    }

    public final String getIsp() {
        return this.isp;
    }

    public final double getLat() {
        return this.lat;
    }

    public final double getLon() {
        return this.lon;
    }

    public final String getOrg() {
        return this.org;
    }

    public final String getQuery() {
        return this.query;
    }

    public final String getRegion() {
        return this.region;
    }

    public final String getRegionName() {
        return this.regionName;
    }

    public final String getStatus() {
        return this.status;
    }

    public final String getTimezone() {
        return this.timezone;
    }

    public final String getZip() {
        return this.zip;
    }
}
