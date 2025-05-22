package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.location.DPoint;
import java.util.List;

/* loaded from: classes.dex */
public class DistrictItem implements Parcelable {
    public static final Parcelable.Creator<DistrictItem> CREATOR = new Parcelable.Creator<DistrictItem>() { // from class: com.amap.api.fence.DistrictItem.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ DistrictItem createFromParcel(Parcel parcel) {
            return new DistrictItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ DistrictItem[] newArray(int i) {
            return new DistrictItem[i];
        }
    };

    /* renamed from: a */
    private String f1043a;

    /* renamed from: b */
    private String f1044b;

    /* renamed from: c */
    private String f1045c;

    /* renamed from: d */
    private List<DPoint> f1046d;

    public DistrictItem() {
        this.f1043a = "";
        this.f1044b = null;
        this.f1045c = null;
        this.f1046d = null;
    }

    protected DistrictItem(Parcel parcel) {
        this.f1043a = "";
        this.f1044b = null;
        this.f1045c = null;
        this.f1046d = null;
        this.f1043a = parcel.readString();
        this.f1044b = parcel.readString();
        this.f1045c = parcel.readString();
        this.f1046d = parcel.createTypedArrayList(DPoint.CREATOR);
    }

    public static Parcelable.Creator<DistrictItem> getCreator() {
        return CREATOR;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAdcode() {
        return this.f1045c;
    }

    public String getCitycode() {
        return this.f1044b;
    }

    public String getDistrictName() {
        return this.f1043a;
    }

    public List<DPoint> getPolyline() {
        return this.f1046d;
    }

    public void setAdcode(String str) {
        this.f1045c = str;
    }

    public void setCitycode(String str) {
        this.f1044b = str;
    }

    public void setDistrictName(String str) {
        this.f1043a = str;
    }

    public void setPolyline(List<DPoint> list) {
        this.f1046d = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1043a);
        parcel.writeString(this.f1044b);
        parcel.writeString(this.f1045c);
        parcel.writeTypedList(this.f1046d);
    }
}
