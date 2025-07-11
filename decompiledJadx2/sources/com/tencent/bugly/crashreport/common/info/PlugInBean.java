package com.tencent.bugly.crashreport.common.info;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class PlugInBean implements Parcelable {
    public static final Parcelable.Creator<PlugInBean> CREATOR = new Parcelable.Creator<PlugInBean>() { // from class: com.tencent.bugly.crashreport.common.info.PlugInBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ PlugInBean createFromParcel(Parcel parcel) {
            return new PlugInBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ PlugInBean[] newArray(int i) {
            return new PlugInBean[i];
        }
    };

    /* renamed from: a */
    public final String f7698a;

    /* renamed from: b */
    public final String f7699b;

    /* renamed from: c */
    public final String f7700c;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PlugInBean(String str, String str2, String str3) {
        this.f7698a = str;
        this.f7699b = str2;
        this.f7700c = str3;
    }

    public String toString() {
        return "plid:" + this.f7698a + " plV:" + this.f7699b + " plUUID:" + this.f7700c;
    }

    public PlugInBean(Parcel parcel) {
        this.f7698a = parcel.readString();
        this.f7699b = parcel.readString();
        this.f7700c = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f7698a);
        parcel.writeString(this.f7699b);
        parcel.writeString(this.f7700c);
    }
}
