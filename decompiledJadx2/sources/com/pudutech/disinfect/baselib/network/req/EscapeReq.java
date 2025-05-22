package com.pudutech.disinfect.baselib.network.req;

import android.os.Build;
import com.google.gson.annotations.SerializedName;
import com.pudutech.disinfect.baselib.util.PackageUtil;
import com.pudutech.disinfect.baselib.util.WifiUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: EscapeReq.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0016\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001BI\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001a\u001a\u00020\u0007HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001d\u001a\u00020\u000bHÆ\u0003JS\u0010\u001e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001J\u0013\u0010\u001f\u001a\u00020\u00072\b\u0010 \u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0018\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0010R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010R\u0016\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0016\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0010R\u0016\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016¨\u0006$"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/req/EscapeReq;", "", "macAddress", "", "longitude", "latitude", "autoLock", "", "systemVersion", "softVersion", "timestamp", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/String;J)V", "getAutoLock", "()Z", "getLatitude", "()Ljava/lang/String;", "getLongitude", "getMacAddress", "getSoftVersion", "getSystemVersion", "getTimestamp", "()J", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final /* data */ class EscapeReq {

    @SerializedName("autoLock")
    private final boolean autoLock;

    @SerializedName("latitude")
    private final String latitude;

    @SerializedName("longitude")
    private final String longitude;

    @SerializedName("mac")
    private final String macAddress;

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

    public final EscapeReq copy(String macAddress, String longitude, String latitude, boolean autoLock, String systemVersion, String softVersion, long timestamp) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
        Intrinsics.checkParameterIsNotNull(systemVersion, "systemVersion");
        Intrinsics.checkParameterIsNotNull(softVersion, "softVersion");
        return new EscapeReq(macAddress, longitude, latitude, autoLock, systemVersion, softVersion, timestamp);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof EscapeReq)) {
            return false;
        }
        EscapeReq escapeReq = (EscapeReq) other;
        return Intrinsics.areEqual(this.macAddress, escapeReq.macAddress) && Intrinsics.areEqual(this.longitude, escapeReq.longitude) && Intrinsics.areEqual(this.latitude, escapeReq.latitude) && this.autoLock == escapeReq.autoLock && Intrinsics.areEqual(this.systemVersion, escapeReq.systemVersion) && Intrinsics.areEqual(this.softVersion, escapeReq.softVersion) && this.timestamp == escapeReq.timestamp;
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
        return ((hashCode4 + (str5 != null ? str5.hashCode() : 0)) * 31) + Long.hashCode(this.timestamp);
    }

    public String toString() {
        return "EscapeReq(macAddress=" + this.macAddress + ", longitude=" + this.longitude + ", latitude=" + this.latitude + ", autoLock=" + this.autoLock + ", systemVersion=" + this.systemVersion + ", softVersion=" + this.softVersion + ", timestamp=" + this.timestamp + ")";
    }

    public EscapeReq(String macAddress, String str, String str2, boolean z, String systemVersion, String softVersion, long j) {
        Intrinsics.checkParameterIsNotNull(macAddress, "macAddress");
        Intrinsics.checkParameterIsNotNull(systemVersion, "systemVersion");
        Intrinsics.checkParameterIsNotNull(softVersion, "softVersion");
        this.macAddress = macAddress;
        this.longitude = str;
        this.latitude = str2;
        this.autoLock = z;
        this.systemVersion = systemVersion;
        this.softVersion = softVersion;
        this.timestamp = j;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public /* synthetic */ EscapeReq(String str, String str2, String str3, boolean z, String str4, String str5, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(r2, str2, str3, z, r6, (i & 32) != 0 ? PackageUtil.INSTANCE.getSoftVersion() : str5, (i & 64) != 0 ? System.currentTimeMillis() / 1000 : j);
        String str6;
        String str7;
        if ((i & 1) != 0) {
            String mac = WifiUtil.INSTANCE.getMac();
            str6 = mac == null ? "" : mac;
        } else {
            str6 = str;
        }
        if ((i & 16) != 0) {
            String str8 = Build.ID;
            Intrinsics.checkExpressionValueIsNotNull(str8, "Build.ID");
            str7 = str8;
        } else {
            str7 = str4;
        }
    }

    public final String getMacAddress() {
        return this.macAddress;
    }

    public final String getLongitude() {
        return this.longitude;
    }

    public final String getLatitude() {
        return this.latitude;
    }

    public final boolean getAutoLock() {
        return this.autoLock;
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
}
