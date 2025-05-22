package com.amap.api.fence;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes.dex */
public class PoiItem implements Parcelable {
    public static final Parcelable.Creator<PoiItem> CREATOR = new Parcelable.Creator<PoiItem>() { // from class: com.amap.api.fence.PoiItem.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ PoiItem createFromParcel(Parcel parcel) {
            return new PoiItem(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ PoiItem[] newArray(int i) {
            return new PoiItem[i];
        }
    };

    /* renamed from: a */
    private String f1065a;

    /* renamed from: b */
    private String f1066b;

    /* renamed from: c */
    private String f1067c;

    /* renamed from: d */
    private String f1068d;

    /* renamed from: e */
    private String f1069e;

    /* renamed from: f */
    private double f1070f;

    /* renamed from: g */
    private double f1071g;

    /* renamed from: h */
    private String f1072h;

    /* renamed from: i */
    private String f1073i;

    /* renamed from: j */
    private String f1074j;

    /* renamed from: k */
    private String f1075k;

    public PoiItem() {
        this.f1065a = "";
        this.f1066b = "";
        this.f1067c = "";
        this.f1068d = "";
        this.f1069e = "";
        this.f1070f = 0.0d;
        this.f1071g = 0.0d;
        this.f1072h = "";
        this.f1073i = "";
        this.f1074j = "";
        this.f1075k = "";
    }

    protected PoiItem(Parcel parcel) {
        this.f1065a = "";
        this.f1066b = "";
        this.f1067c = "";
        this.f1068d = "";
        this.f1069e = "";
        this.f1070f = 0.0d;
        this.f1071g = 0.0d;
        this.f1072h = "";
        this.f1073i = "";
        this.f1074j = "";
        this.f1075k = "";
        this.f1065a = parcel.readString();
        this.f1066b = parcel.readString();
        this.f1067c = parcel.readString();
        this.f1068d = parcel.readString();
        this.f1069e = parcel.readString();
        this.f1070f = parcel.readDouble();
        this.f1071g = parcel.readDouble();
        this.f1072h = parcel.readString();
        this.f1073i = parcel.readString();
        this.f1074j = parcel.readString();
        this.f1075k = parcel.readString();
    }

    public static Parcelable.Creator<PoiItem> getCreator() {
        return CREATOR;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getAddress() {
        return this.f1069e;
    }

    public String getAdname() {
        return this.f1075k;
    }

    public String getCity() {
        return this.f1074j;
    }

    public double getLatitude() {
        return this.f1070f;
    }

    public double getLongitude() {
        return this.f1071g;
    }

    public String getPoiId() {
        return this.f1066b;
    }

    public String getPoiName() {
        return this.f1065a;
    }

    public String getPoiType() {
        return this.f1067c;
    }

    public String getProvince() {
        return this.f1073i;
    }

    public String getTel() {
        return this.f1072h;
    }

    public String getTypeCode() {
        return this.f1068d;
    }

    public void setAddress(String str) {
        this.f1069e = str;
    }

    public void setAdname(String str) {
        this.f1075k = str;
    }

    public void setCity(String str) {
        this.f1074j = str;
    }

    public void setLatitude(double d) {
        this.f1070f = d;
    }

    public void setLongitude(double d) {
        this.f1071g = d;
    }

    public void setPoiId(String str) {
        this.f1066b = str;
    }

    public void setPoiName(String str) {
        this.f1065a = str;
    }

    public void setPoiType(String str) {
        this.f1067c = str;
    }

    public void setProvince(String str) {
        this.f1073i = str;
    }

    public void setTel(String str) {
        this.f1072h = str;
    }

    public void setTypeCode(String str) {
        this.f1068d = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1065a);
        parcel.writeString(this.f1066b);
        parcel.writeString(this.f1067c);
        parcel.writeString(this.f1068d);
        parcel.writeString(this.f1069e);
        parcel.writeDouble(this.f1070f);
        parcel.writeDouble(this.f1071g);
        parcel.writeString(this.f1072h);
        parcel.writeString(this.f1073i);
        parcel.writeString(this.f1074j);
        parcel.writeString(this.f1075k);
    }
}
