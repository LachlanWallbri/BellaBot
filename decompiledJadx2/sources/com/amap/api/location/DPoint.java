package com.amap.api.location;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class DPoint implements Parcelable {
    public static final Parcelable.Creator<DPoint> CREATOR = new Parcelable.Creator<DPoint>() { // from class: com.amap.api.location.DPoint.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DPoint createFromParcel(Parcel parcel) {
            return new DPoint(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ DPoint[] newArray(int i) {
            return new DPoint[i];
        }
    };

    /* renamed from: a */
    private double f1129a;

    /* renamed from: b */
    private double f1130b;

    public DPoint() {
        this.f1129a = 0.0d;
        this.f1130b = 0.0d;
    }

    public DPoint(double d, double d2) {
        this.f1129a = 0.0d;
        this.f1130b = 0.0d;
        d2 = d2 > 180.0d ? 180.0d : d2;
        d2 = d2 < -180.0d ? -180.0d : d2;
        d = d > 90.0d ? 90.0d : d;
        d = d < -90.0d ? -90.0d : d;
        this.f1129a = d2;
        this.f1130b = d;
    }

    protected DPoint(Parcel parcel) {
        this.f1129a = 0.0d;
        this.f1130b = 0.0d;
        this.f1129a = parcel.readDouble();
        this.f1130b = parcel.readDouble();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof DPoint)) {
            return false;
        }
        DPoint dPoint = (DPoint) obj;
        return this.f1130b == dPoint.f1130b && this.f1129a == dPoint.f1129a;
    }

    public double getLatitude() {
        return this.f1130b;
    }

    public double getLongitude() {
        return this.f1129a;
    }

    public int hashCode() {
        return Double.valueOf((this.f1130b + this.f1129a) * 1000000.0d).intValue();
    }

    public void setLatitude(double d) {
        if (d > 90.0d) {
            d = 90.0d;
        }
        if (d < -90.0d) {
            d = -90.0d;
        }
        this.f1130b = d;
    }

    public void setLongitude(double d) {
        if (d > 180.0d) {
            d = 180.0d;
        }
        if (d < -180.0d) {
            d = -180.0d;
        }
        this.f1129a = d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.f1129a);
        parcel.writeDouble(this.f1130b);
    }
}
