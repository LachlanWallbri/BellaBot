package com.amap.api.fence;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.amap.api.location.DPoint;
import com.loc.C3876cx;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class GeoFence implements Parcelable {
    public static final int ADDGEOFENCE_SUCCESS = 0;
    public static final String BUNDLE_KEY_CUSTOMID = "customId";
    public static final String BUNDLE_KEY_FENCE = "fence";
    public static final String BUNDLE_KEY_FENCEID = "fenceid";
    public static final String BUNDLE_KEY_FENCESTATUS = "event";
    public static final String BUNDLE_KEY_LOCERRORCODE = "location_errorcode";
    public static final Parcelable.Creator<GeoFence> CREATOR = new Parcelable.Creator<GeoFence>() { // from class: com.amap.api.fence.GeoFence.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ GeoFence createFromParcel(Parcel parcel) {
            return new GeoFence(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ GeoFence[] newArray(int i) {
            return new GeoFence[i];
        }
    };
    public static final int ERROR_CODE_EXISTS = 17;
    public static final int ERROR_CODE_FAILURE_AUTH = 7;
    public static final int ERROR_CODE_FAILURE_CONNECTION = 4;
    public static final int ERROR_CODE_INVALID_PARAMETER = 1;
    public static final int ERROR_CODE_UNKNOWN = 8;
    public static final int ERROR_NO_VALIDFENCE = 16;
    public static final int STATUS_IN = 1;
    public static final int STATUS_LOCFAIL = 4;
    public static final int STATUS_OUT = 2;
    public static final int STATUS_STAYED = 3;
    public static final int STATUS_UNKNOWN = 0;
    public static final int TYPE_AMAPPOI = 2;
    public static final int TYPE_DISTRICT = 3;
    public static final int TYPE_POLYGON = 1;
    public static final int TYPE_ROUND = 0;

    /* renamed from: a */
    private String f1047a;

    /* renamed from: b */
    private String f1048b;

    /* renamed from: c */
    private String f1049c;

    /* renamed from: d */
    private PendingIntent f1050d;

    /* renamed from: e */
    private int f1051e;

    /* renamed from: f */
    private PoiItem f1052f;

    /* renamed from: g */
    private List<DistrictItem> f1053g;

    /* renamed from: h */
    private List<List<DPoint>> f1054h;

    /* renamed from: i */
    private float f1055i;

    /* renamed from: j */
    private long f1056j;

    /* renamed from: k */
    private int f1057k;

    /* renamed from: l */
    private float f1058l;

    /* renamed from: m */
    private float f1059m;

    /* renamed from: n */
    private DPoint f1060n;

    /* renamed from: o */
    private int f1061o;

    /* renamed from: p */
    private long f1062p;

    public GeoFence() {
        this.f1050d = null;
        this.f1051e = 0;
        this.f1052f = null;
        this.f1053g = null;
        this.f1055i = 0.0f;
        this.f1056j = -1L;
        this.f1057k = 1;
        this.f1058l = 0.0f;
        this.f1059m = 0.0f;
        this.f1060n = null;
        this.f1061o = 0;
        this.f1062p = -1L;
    }

    protected GeoFence(Parcel parcel) {
        this.f1050d = null;
        this.f1051e = 0;
        this.f1052f = null;
        this.f1053g = null;
        this.f1055i = 0.0f;
        this.f1056j = -1L;
        this.f1057k = 1;
        this.f1058l = 0.0f;
        this.f1059m = 0.0f;
        this.f1060n = null;
        this.f1061o = 0;
        this.f1062p = -1L;
        this.f1047a = parcel.readString();
        this.f1048b = parcel.readString();
        this.f1049c = parcel.readString();
        this.f1050d = (PendingIntent) parcel.readParcelable(PendingIntent.class.getClassLoader());
        this.f1051e = parcel.readInt();
        this.f1052f = (PoiItem) parcel.readParcelable(PoiItem.class.getClassLoader());
        this.f1053g = parcel.createTypedArrayList(DistrictItem.CREATOR);
        this.f1055i = parcel.readFloat();
        this.f1056j = parcel.readLong();
        this.f1057k = parcel.readInt();
        this.f1058l = parcel.readFloat();
        this.f1059m = parcel.readFloat();
        this.f1060n = (DPoint) parcel.readParcelable(DPoint.class.getClassLoader());
        this.f1061o = parcel.readInt();
        this.f1062p = parcel.readLong();
        int readInt = parcel.readInt();
        if (readInt != 0) {
            this.f1054h = new ArrayList();
            for (int i = 0; i < readInt; i++) {
                this.f1054h.add(parcel.createTypedArrayList(DPoint.CREATOR));
            }
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GeoFence)) {
            return false;
        }
        GeoFence geoFence = (GeoFence) obj;
        if (TextUtils.isEmpty(this.f1048b)) {
            if (!TextUtils.isEmpty(geoFence.f1048b)) {
                return false;
            }
        } else if (!this.f1048b.equals(geoFence.f1048b)) {
            return false;
        }
        DPoint dPoint = this.f1060n;
        if (dPoint == null) {
            if (geoFence.f1060n != null) {
                return false;
            }
        } else if (!dPoint.equals(geoFence.f1060n)) {
            return false;
        }
        if (this.f1055i != geoFence.f1055i) {
            return false;
        }
        List<List<DPoint>> list = this.f1054h;
        List<List<DPoint>> list2 = geoFence.f1054h;
        return list == null ? list2 == null : list.equals(list2);
    }

    public int getActivatesAction() {
        return this.f1057k;
    }

    public DPoint getCenter() {
        return this.f1060n;
    }

    public String getCustomId() {
        return this.f1048b;
    }

    public List<DistrictItem> getDistrictItemList() {
        return this.f1053g;
    }

    public long getEnterTime() {
        return this.f1062p;
    }

    public long getExpiration() {
        return this.f1056j;
    }

    public String getFenceId() {
        return this.f1047a;
    }

    public float getMaxDis2Center() {
        return this.f1059m;
    }

    public float getMinDis2Center() {
        return this.f1058l;
    }

    public PendingIntent getPendingIntent() {
        return this.f1050d;
    }

    public String getPendingIntentAction() {
        return this.f1049c;
    }

    public PoiItem getPoiItem() {
        return this.f1052f;
    }

    public List<List<DPoint>> getPointList() {
        return this.f1054h;
    }

    public float getRadius() {
        return this.f1055i;
    }

    public int getStatus() {
        return this.f1061o;
    }

    public int getType() {
        return this.f1051e;
    }

    public int hashCode() {
        return this.f1048b.hashCode() + this.f1054h.hashCode() + this.f1060n.hashCode() + ((int) (this.f1055i * 100.0f));
    }

    public void setActivatesAction(int i) {
        this.f1057k = i;
    }

    public void setCenter(DPoint dPoint) {
        this.f1060n = dPoint;
    }

    public void setCustomId(String str) {
        this.f1048b = str;
    }

    public void setDistrictItemList(List<DistrictItem> list) {
        this.f1053g = list;
    }

    public void setEnterTime(long j) {
        this.f1062p = j;
    }

    public void setExpiration(long j) {
        this.f1056j = j < 0 ? -1L : j + C3876cx.m2985b();
    }

    public void setFenceId(String str) {
        this.f1047a = str;
    }

    public void setMaxDis2Center(float f) {
        this.f1059m = f;
    }

    public void setMinDis2Center(float f) {
        this.f1058l = f;
    }

    public void setPendingIntent(PendingIntent pendingIntent) {
        this.f1050d = pendingIntent;
    }

    public void setPendingIntentAction(String str) {
        this.f1049c = str;
    }

    public void setPoiItem(PoiItem poiItem) {
        this.f1052f = poiItem;
    }

    public void setPointList(List<List<DPoint>> list) {
        this.f1054h = list;
    }

    public void setRadius(float f) {
        this.f1055i = f;
    }

    public void setStatus(int i) {
        this.f1061o = i;
    }

    public void setType(int i) {
        this.f1051e = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1047a);
        parcel.writeString(this.f1048b);
        parcel.writeString(this.f1049c);
        parcel.writeParcelable(this.f1050d, i);
        parcel.writeInt(this.f1051e);
        parcel.writeParcelable(this.f1052f, i);
        parcel.writeTypedList(this.f1053g);
        parcel.writeFloat(this.f1055i);
        parcel.writeLong(this.f1056j);
        parcel.writeInt(this.f1057k);
        parcel.writeFloat(this.f1058l);
        parcel.writeFloat(this.f1059m);
        parcel.writeParcelable(this.f1060n, i);
        parcel.writeInt(this.f1061o);
        parcel.writeLong(this.f1062p);
        List<List<DPoint>> list = this.f1054h;
        if (list == null || list.isEmpty()) {
            return;
        }
        parcel.writeInt(this.f1054h.size());
        Iterator<List<DPoint>> it = this.f1054h.iterator();
        while (it.hasNext()) {
            parcel.writeTypedList(it.next());
        }
    }
}
