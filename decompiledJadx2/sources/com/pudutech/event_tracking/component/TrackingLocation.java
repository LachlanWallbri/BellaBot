package com.pudutech.event_tracking.component;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LocationComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\t\u0010\u0013\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J;\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\tHÖ\u0001J\t\u0010\u001d\u001a\u00020\u0006HÖ\u0001R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000eR\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/event_tracking/component/TrackingLocation;", "", "lat", "", "lng", "province", "", "city", "locationAccuracy", "", "(DDLjava/lang/String;Ljava/lang/String;I)V", "getCity", "()Ljava/lang/String;", "getLat", "()D", "getLng", "getLocationAccuracy", "()I", "getProvince", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "Companion", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class TrackingLocation {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String city;
    private final double lat;
    private final double lng;
    private final int locationAccuracy;
    private final String province;

    /* renamed from: component1, reason: from getter */
    public final double getLat() {
        return this.lat;
    }

    /* renamed from: component2, reason: from getter */
    public final double getLng() {
        return this.lng;
    }

    /* renamed from: component3, reason: from getter */
    public final String getProvince() {
        return this.province;
    }

    /* renamed from: component4, reason: from getter */
    public final String getCity() {
        return this.city;
    }

    /* renamed from: component5, reason: from getter */
    public final int getLocationAccuracy() {
        return this.locationAccuracy;
    }

    public final TrackingLocation copy(double lat, double lng, String province, String city, int locationAccuracy) {
        Intrinsics.checkParameterIsNotNull(province, "province");
        Intrinsics.checkParameterIsNotNull(city, "city");
        return new TrackingLocation(lat, lng, province, city, locationAccuracy);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TrackingLocation)) {
            return false;
        }
        TrackingLocation trackingLocation = (TrackingLocation) other;
        return Double.compare(this.lat, trackingLocation.lat) == 0 && Double.compare(this.lng, trackingLocation.lng) == 0 && Intrinsics.areEqual(this.province, trackingLocation.province) && Intrinsics.areEqual(this.city, trackingLocation.city) && this.locationAccuracy == trackingLocation.locationAccuracy;
    }

    public int hashCode() {
        int hashCode = ((Double.hashCode(this.lat) * 31) + Double.hashCode(this.lng)) * 31;
        String str = this.province;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.city;
        return ((hashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.locationAccuracy);
    }

    public String toString() {
        return "TrackingLocation(lat=" + this.lat + ", lng=" + this.lng + ", province=" + this.province + ", city=" + this.city + ", locationAccuracy=" + this.locationAccuracy + ")";
    }

    public TrackingLocation(double d, double d2, String province, String city, int i) {
        Intrinsics.checkParameterIsNotNull(province, "province");
        Intrinsics.checkParameterIsNotNull(city, "city");
        this.lat = d;
        this.lng = d2;
        this.province = province;
        this.city = city;
        this.locationAccuracy = i;
    }

    public final double getLat() {
        return this.lat;
    }

    public final double getLng() {
        return this.lng;
    }

    public final String getProvince() {
        return this.province;
    }

    public final String getCity() {
        return this.city;
    }

    public final int getLocationAccuracy() {
        return this.locationAccuracy;
    }

    /* compiled from: LocationComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, m3961d2 = {"Lcom/pudutech/event_tracking/component/TrackingLocation$Companion;", "", "()V", "defaultLocation", "Lcom/pudutech/event_tracking/component/TrackingLocation;", "event_tracking_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TrackingLocation defaultLocation() {
            return new TrackingLocation(-1.0d, -1.0d, "", "", -1);
        }
    }
}
