package com.pudutech.antichannelconflict.escape.network;

import android.os.Build;
import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: NetworkUtil.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b$\b\u0086\b\u0018\u00002\u00020\u0001Bc\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003¢\u0006\u0002\u0010\u0010J\u000b\u0010\"\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010#\u001a\u00020\u0003HÆ\u0003J\t\u0010$\u001a\u00020\u0003HÆ\u0003J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0007HÆ\u0003J\t\u0010'\u001a\u00020\u0003HÆ\u0003J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u000bHÆ\u0003J\t\u0010*\u001a\u00020\rHÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003Jo\u0010,\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003HÆ\u0001J\u0013\u0010-\u001a\u00020\u00072\b\u0010.\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010/\u001a\u00020\rHÖ\u0001J\t\u00100\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0016\u0010\u000f\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0016\u0010\u000e\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u001e\u0010\u0005\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0014\"\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0018R\u0018\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u0016\u0010\f\u001a\u00020\r8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0014R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!¨\u00061"}, m3961d2 = {"Lcom/pudutech/antichannelconflict/escape/network/EscapeReq;", "", "macAddress", "", "longitude", "latitude", "autoLock", "", "systemVersion", "softVersion", "timestamp", "", "productCategoryId", "", "changeType", "bts", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;JILjava/lang/String;Ljava/lang/String;)V", "getAutoLock", "()Z", "getBts", "()Ljava/lang/String;", "getChangeType", "getLatitude", "setLatitude", "(Ljava/lang/String;)V", "getLongitude", "setLongitude", "getMacAddress", "getProductCategoryId", "()I", "getSoftVersion", "getSystemVersion", "getTimestamp", "()J", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "AntiChannelConflict_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class EscapeReq {

    @SerializedName("autoLock")
    private final boolean autoLock;

    @SerializedName("base_station_info")
    private final String bts;

    @SerializedName("change_type")
    private final String changeType;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("mac")
    private final String macAddress;

    @SerializedName("product_category_id")
    private final int productCategoryId;

    @SerializedName("softver")
    private final String softVersion;

    @SerializedName("systemid")
    private final String systemVersion;

    @SerializedName("timestamp")
    private final long timestamp;

    /* renamed from: component1, reason: from getter */
    public final String getMacAddress() {
        return this.macAddress;
    }

    /* renamed from: component10, reason: from getter */
    public final String getBts() {
        return this.bts;
    }

    /* renamed from: component2, reason: from getter */
    public final String getLongitude() {
        return this.longitude;
    }

    /* renamed from: component3, reason: from getter */
    public final String getLatitude() {
        return this.latitude;
    }

    /* renamed from: component4, reason: from getter */
    public final boolean getAutoLock() {
        return this.autoLock;
    }

    /* renamed from: component5, reason: from getter */
    public final String getSystemVersion() {
        return this.systemVersion;
    }

    /* renamed from: component6, reason: from getter */
    public final String getSoftVersion() {
        return this.softVersion;
    }

    /* renamed from: component7, reason: from getter */
    public final long getTimestamp() {
        return this.timestamp;
    }

    /* renamed from: component8, reason: from getter */
    public final int getProductCategoryId() {
        return this.productCategoryId;
    }

    /* renamed from: component9, reason: from getter */
    public final String getChangeType() {
        return this.changeType;
    }

    public final EscapeReq copy(String macAddress, String longitude, String latitude, boolean autoLock, String systemVersion, String softVersion, long timestamp, int productCategoryId, String changeType, String bts) {
        Intrinsics.checkParameterIsNotNull(longitude, "longitude");
        Intrinsics.checkParameterIsNotNull(latitude, "latitude");
        Intrinsics.checkParameterIsNotNull(systemVersion, "systemVersion");
        Intrinsics.checkParameterIsNotNull(softVersion, "softVersion");
        Intrinsics.checkParameterIsNotNull(changeType, "changeType");
        Intrinsics.checkParameterIsNotNull(bts, "bts");
        return new EscapeReq(macAddress, longitude, latitude, autoLock, systemVersion, softVersion, timestamp, productCategoryId, changeType, bts);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EscapeReq)) {
            return false;
        }
        EscapeReq escapeReq = (EscapeReq) other;
        return Intrinsics.areEqual(this.macAddress, escapeReq.macAddress) && Intrinsics.areEqual(this.longitude, escapeReq.longitude) && Intrinsics.areEqual(this.latitude, escapeReq.latitude) && this.autoLock == escapeReq.autoLock && Intrinsics.areEqual(this.systemVersion, escapeReq.systemVersion) && Intrinsics.areEqual(this.softVersion, escapeReq.softVersion) && this.timestamp == escapeReq.timestamp && this.productCategoryId == escapeReq.productCategoryId && Intrinsics.areEqual(this.changeType, escapeReq.changeType) && Intrinsics.areEqual(this.bts, escapeReq.bts);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int hashCode() {
        String str = this.macAddress;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.longitude;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.latitude;
        int hashCode3 = (hashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
        boolean z = this.autoLock;
        int i = z;
        if (z != 0) {
            i = 1;
        }
        int i2 = (hashCode3 + i) * 31;
        String str4 = this.systemVersion;
        int hashCode4 = (i2 + (str4 != null ? str4.hashCode() : 0)) * 31;
        String str5 = this.softVersion;
        int hashCode5 = (hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31;
        long j = this.timestamp;
        int i3 = (((hashCode5 + ((int) (j ^ (j >>> 32)))) * 31) + this.productCategoryId) * 31;
        String str6 = this.changeType;
        int hashCode6 = (i3 + (str6 != null ? str6.hashCode() : 0)) * 31;
        String str7 = this.bts;
        return hashCode6 + (str7 != null ? str7.hashCode() : 0);
    }

    public String toString() {
        return "EscapeReq(macAddress=" + this.macAddress + ", longitude=" + this.longitude + ", latitude=" + this.latitude + ", autoLock=" + this.autoLock + ", systemVersion=" + this.systemVersion + ", softVersion=" + this.softVersion + ", timestamp=" + this.timestamp + ", productCategoryId=" + this.productCategoryId + ", changeType=" + this.changeType + ", bts=" + this.bts + ")";
    }

    public EscapeReq(String str, String longitude, String latitude, boolean z, String systemVersion, String softVersion, long j, int i, String changeType, String bts) {
        Intrinsics.checkParameterIsNotNull(longitude, "longitude");
        Intrinsics.checkParameterIsNotNull(latitude, "latitude");
        Intrinsics.checkParameterIsNotNull(systemVersion, "systemVersion");
        Intrinsics.checkParameterIsNotNull(softVersion, "softVersion");
        Intrinsics.checkParameterIsNotNull(changeType, "changeType");
        Intrinsics.checkParameterIsNotNull(bts, "bts");
        this.macAddress = str;
        this.longitude = longitude;
        this.latitude = latitude;
        this.autoLock = z;
        this.systemVersion = systemVersion;
        this.softVersion = softVersion;
        this.timestamp = j;
        this.productCategoryId = i;
        this.changeType = changeType;
        this.bts = bts;
    }

    public final String getMacAddress() {
        return this.macAddress;
    }

    public final String getLongitude() {
        return this.longitude;
    }

    public final void setLongitude(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.longitude = str;
    }

    public final String getLatitude() {
        return this.latitude;
    }

    public final void setLatitude(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.latitude = str;
    }

    public final boolean getAutoLock() {
        return this.autoLock;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ EscapeReq(String str, String str2, String str3, boolean z, String str4, String str5, long j, int i, String str6, String str7, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(r4, str2, str3, z, r8, (i2 & 32) != 0 ? "" : str5, (i2 & 64) != 0 ? System.currentTimeMillis() / 1000 : j, i, (i2 & 256) != 0 ? "no_change" : str6, (i2 & 512) != 0 ? "" : str7);
        String str8;
        String str9 = (i2 & 1) != 0 ? "" : str;
        if ((i2 & 16) != 0) {
            String str10 = Build.ID;
            Intrinsics.checkExpressionValueIsNotNull(str10, "Build.ID");
            str8 = str10;
        } else {
            str8 = str4;
        }
    }

    public final String getSystemVersion() {
        return this.systemVersion;
    }

    public final String getSoftVersion() {
        return this.softVersion;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final int getProductCategoryId() {
        return this.productCategoryId;
    }

    public final String getChangeType() {
        return this.changeType;
    }

    public final String getBts() {
        return this.bts;
    }
}
