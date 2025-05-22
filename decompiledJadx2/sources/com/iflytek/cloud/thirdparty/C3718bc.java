package com.iflytek.cloud.thirdparty;

import android.os.Parcel;
import android.os.Parcelable;

/* renamed from: com.iflytek.cloud.thirdparty.bc */
/* loaded from: classes3.dex */
public class C3718bc implements Parcelable {
    public static final Parcelable.Creator<C3718bc> CREATOR = new Parcelable.Creator<C3718bc>() { // from class: com.iflytek.cloud.thirdparty.bc.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C3718bc createFromParcel(Parcel parcel) {
            C3718bc c3718bc = new C3718bc();
            c3718bc.f3162a = parcel.readString();
            c3718bc.f3163b = parcel.readString();
            c3718bc.f3164c = parcel.readString();
            c3718bc.f3165d = parcel.readString();
            c3718bc.f3166e = parcel.readString();
            c3718bc.f3167f = parcel.readString();
            c3718bc.f3168g = parcel.readString();
            return c3718bc;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C3718bc[] newArray(int i) {
            return new C3718bc[i];
        }
    };

    /* renamed from: a */
    private String f3162a;

    /* renamed from: b */
    private String f3163b;

    /* renamed from: c */
    private String f3164c;

    /* renamed from: d */
    private String f3165d;

    /* renamed from: e */
    private String f3166e;

    /* renamed from: f */
    private String f3167f;

    /* renamed from: g */
    private String f3168g;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public C3718bc() {
        this.f3162a = null;
        this.f3163b = null;
        this.f3164c = null;
        this.f3165d = null;
        this.f3166e = null;
        this.f3167f = null;
        this.f3168g = null;
    }

    public C3718bc(String str, String str2, String str3, String str4, String str5, String str6) {
        this.f3162a = null;
        this.f3163b = null;
        this.f3164c = null;
        this.f3165d = null;
        this.f3166e = null;
        this.f3167f = null;
        this.f3168g = null;
        this.f3162a = str;
        this.f3163b = str2;
        this.f3164c = str3;
        this.f3165d = str4;
        this.f3166e = str5;
        this.f3168g = str6;
    }

    /* renamed from: a */
    public String m2031a() {
        return this.f3162a;
    }

    /* renamed from: b */
    public String m2032b() {
        return this.f3163b;
    }

    /* renamed from: c */
    public String m2033c() {
        return this.f3165d;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f3162a);
        parcel.writeString(this.f3163b);
        parcel.writeString(this.f3164c);
        parcel.writeString(this.f3165d);
        parcel.writeString(this.f3166e);
        parcel.writeString(this.f3167f);
        parcel.writeString(this.f3168g);
    }
}
