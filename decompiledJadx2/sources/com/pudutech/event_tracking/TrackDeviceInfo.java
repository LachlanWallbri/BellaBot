package com.pudutech.event_tracking;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: tracking_device.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\u0006\u0010\u0007\u001a\u00020\u0003¢\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0017\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0018\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\f¨\u0006\u0019"}, m3961d2 = {"Lcom/pudutech/event_tracking/TrackDeviceInfo;", "", "appPackage", "", "appId", "", "appName", "deviceType", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;)V", "getAppId", "()I", "getAppName", "()Ljava/lang/String;", "getAppPackage", "getDeviceType", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TrackDeviceInfo {
    private final int appId;
    private final String appName;
    private final String appPackage;
    private final String deviceType;

    public static /* synthetic */ TrackDeviceInfo copy$default(TrackDeviceInfo trackDeviceInfo, String str, int i, String str2, String str3, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = trackDeviceInfo.appPackage;
        }
        if ((i2 & 2) != 0) {
            i = trackDeviceInfo.appId;
        }
        if ((i2 & 4) != 0) {
            str2 = trackDeviceInfo.appName;
        }
        if ((i2 & 8) != 0) {
            str3 = trackDeviceInfo.deviceType;
        }
        return trackDeviceInfo.copy(str, i, str2, str3);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAppPackage() {
        return this.appPackage;
    }

    /* renamed from: component2, reason: from getter */
    public final int getAppId() {
        return this.appId;
    }

    /* renamed from: component3, reason: from getter */
    public final String getAppName() {
        return this.appName;
    }

    /* renamed from: component4, reason: from getter */
    public final String getDeviceType() {
        return this.deviceType;
    }

    public final TrackDeviceInfo copy(String appPackage, int appId, String appName, String deviceType) {
        Intrinsics.checkParameterIsNotNull(appPackage, "appPackage");
        Intrinsics.checkParameterIsNotNull(appName, "appName");
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        return new TrackDeviceInfo(appPackage, appId, appName, deviceType);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackDeviceInfo)) {
            return false;
        }
        TrackDeviceInfo trackDeviceInfo = (TrackDeviceInfo) other;
        return Intrinsics.areEqual(this.appPackage, trackDeviceInfo.appPackage) && this.appId == trackDeviceInfo.appId && Intrinsics.areEqual(this.appName, trackDeviceInfo.appName) && Intrinsics.areEqual(this.deviceType, trackDeviceInfo.deviceType);
    }

    public int hashCode() {
        String str = this.appPackage;
        int hashCode = (((str != null ? str.hashCode() : 0) * 31) + Integer.hashCode(this.appId)) * 31;
        String str2 = this.appName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.deviceType;
        return hashCode2 + (str3 != null ? str3.hashCode() : 0);
    }

    public String toString() {
        return "TrackDeviceInfo(appPackage=" + this.appPackage + ", appId=" + this.appId + ", appName=" + this.appName + ", deviceType=" + this.deviceType + ")";
    }

    public TrackDeviceInfo(String appPackage, int i, String appName, String deviceType) {
        Intrinsics.checkParameterIsNotNull(appPackage, "appPackage");
        Intrinsics.checkParameterIsNotNull(appName, "appName");
        Intrinsics.checkParameterIsNotNull(deviceType, "deviceType");
        this.appPackage = appPackage;
        this.appId = i;
        this.appName = appName;
        this.deviceType = deviceType;
    }

    public final String getAppPackage() {
        return this.appPackage;
    }

    public final int getAppId() {
        return this.appId;
    }

    public final String getAppName() {
        return this.appName;
    }

    public final String getDeviceType() {
        return this.deviceType;
    }
}
