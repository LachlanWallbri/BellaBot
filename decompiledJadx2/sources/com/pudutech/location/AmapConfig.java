package com.pudutech.location;

import com.amap.api.location.AMapLocationClientOption;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationListener.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010'\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\u000b\"\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000b\"\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u000b\"\u0004\b\u0011\u0010\rR\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0015\"\u0004\b\u001a\u0010\u0017R\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0006\"\u0004\b#\u0010\bR\u001a\u0010$\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0006\"\u0004\b&\u0010\b¨\u0006("}, m3961d2 = {"Lcom/pudutech/location/AmapConfig;", "", "()V", "hardver", "", "getHardver", "()Ljava/lang/String;", "setHardver", "(Ljava/lang/String;)V", "isLocationCacheEnable", "", "()Z", "setLocationCacheEnable", "(Z)V", "isOnceLocation", "setOnceLocation", "isOpenReport", "setOpenReport", "locationHttpTimeout", "", "getLocationHttpTimeout", "()J", "setLocationHttpTimeout", "(J)V", "locationInterval", "getLocationInterval", "setLocationInterval", "locationMode", "Lcom/amap/api/location/AMapLocationClientOption$AMapLocationMode;", "getLocationMode", "()Lcom/amap/api/location/AMapLocationClientOption$AMapLocationMode;", "setLocationMode", "(Lcom/amap/api/location/AMapLocationClientOption$AMapLocationMode;)V", "mac", "getMac", "setMac", "softver", "getSoftver", "setSoftver", "toString", "module_amap_location_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class AmapConfig {
    private boolean isOnceLocation;
    private long locationHttpTimeout = 30000;
    private long locationInterval = 10000;
    private AMapLocationClientOption.AMapLocationMode locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy;
    private boolean isLocationCacheEnable = true;
    private boolean isOpenReport = true;
    private String mac = "";
    private String softver = "";
    private String hardver = "";

    public final long getLocationHttpTimeout() {
        return this.locationHttpTimeout;
    }

    public final void setLocationHttpTimeout(long j) {
        this.locationHttpTimeout = j;
    }

    public final long getLocationInterval() {
        return this.locationInterval;
    }

    public final void setLocationInterval(long j) {
        this.locationInterval = j;
    }

    /* renamed from: isOnceLocation, reason: from getter */
    public final boolean getIsOnceLocation() {
        return this.isOnceLocation;
    }

    public final void setOnceLocation(boolean z) {
        this.isOnceLocation = z;
    }

    public final AMapLocationClientOption.AMapLocationMode getLocationMode() {
        return this.locationMode;
    }

    public final void setLocationMode(AMapLocationClientOption.AMapLocationMode aMapLocationMode) {
        Intrinsics.checkParameterIsNotNull(aMapLocationMode, "<set-?>");
        this.locationMode = aMapLocationMode;
    }

    /* renamed from: isLocationCacheEnable, reason: from getter */
    public final boolean getIsLocationCacheEnable() {
        return this.isLocationCacheEnable;
    }

    public final void setLocationCacheEnable(boolean z) {
        this.isLocationCacheEnable = z;
    }

    /* renamed from: isOpenReport, reason: from getter */
    public final boolean getIsOpenReport() {
        return this.isOpenReport;
    }

    public final void setOpenReport(boolean z) {
        this.isOpenReport = z;
    }

    public final String getMac() {
        return this.mac;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final String getSoftver() {
        return this.softver;
    }

    public final void setSoftver(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.softver = str;
    }

    public final String getHardver() {
        return this.hardver;
    }

    public final void setHardver(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.hardver = str;
    }

    public String toString() {
        return "AmapConfig(locationHttpTimeout=" + this.locationHttpTimeout + ", locationInterval=" + this.locationInterval + ", isOnceLocation=" + this.isOnceLocation + ", locationMode=" + this.locationMode + ", isLocationCacheEnable=" + this.isLocationCacheEnable + ", isOpenReport=" + this.isOpenReport + ", mac='" + this.mac + "', softver='" + this.softver + "', hardver='" + this.hardver + "')";
    }
}
