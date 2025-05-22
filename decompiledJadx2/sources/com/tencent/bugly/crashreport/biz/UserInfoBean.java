package com.tencent.bugly.crashreport.biz;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.os.EnvironmentCompat;
import com.tencent.bugly.proguard.C5942z;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class UserInfoBean implements Parcelable {
    public static final Parcelable.Creator<UserInfoBean> CREATOR = new Parcelable.Creator<UserInfoBean>() { // from class: com.tencent.bugly.crashreport.biz.UserInfoBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ UserInfoBean createFromParcel(Parcel parcel) {
            return new UserInfoBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ UserInfoBean[] newArray(int i) {
            return new UserInfoBean[i];
        }
    };

    /* renamed from: a */
    public long f7650a;

    /* renamed from: b */
    public int f7651b;

    /* renamed from: c */
    public String f7652c;

    /* renamed from: d */
    public String f7653d;

    /* renamed from: e */
    public long f7654e;

    /* renamed from: f */
    public long f7655f;

    /* renamed from: g */
    public long f7656g;

    /* renamed from: h */
    public long f7657h;

    /* renamed from: i */
    public long f7658i;

    /* renamed from: j */
    public String f7659j;

    /* renamed from: k */
    public long f7660k;

    /* renamed from: l */
    public boolean f7661l;

    /* renamed from: m */
    public String f7662m;

    /* renamed from: n */
    public String f7663n;

    /* renamed from: o */
    public int f7664o;

    /* renamed from: p */
    public int f7665p;

    /* renamed from: q */
    public int f7666q;

    /* renamed from: r */
    public Map<String, String> f7667r;

    /* renamed from: s */
    public Map<String, String> f7668s;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UserInfoBean() {
        this.f7660k = 0L;
        this.f7661l = false;
        this.f7662m = EnvironmentCompat.MEDIA_UNKNOWN;
        this.f7665p = -1;
        this.f7666q = -1;
        this.f7667r = null;
        this.f7668s = null;
    }

    public UserInfoBean(Parcel parcel) {
        this.f7660k = 0L;
        this.f7661l = false;
        this.f7662m = EnvironmentCompat.MEDIA_UNKNOWN;
        this.f7665p = -1;
        this.f7666q = -1;
        this.f7667r = null;
        this.f7668s = null;
        this.f7651b = parcel.readInt();
        this.f7652c = parcel.readString();
        this.f7653d = parcel.readString();
        this.f7654e = parcel.readLong();
        this.f7655f = parcel.readLong();
        this.f7656g = parcel.readLong();
        this.f7657h = parcel.readLong();
        this.f7658i = parcel.readLong();
        this.f7659j = parcel.readString();
        this.f7660k = parcel.readLong();
        this.f7661l = parcel.readByte() == 1;
        this.f7662m = parcel.readString();
        this.f7665p = parcel.readInt();
        this.f7666q = parcel.readInt();
        this.f7667r = C5942z.m3880b(parcel);
        this.f7668s = C5942z.m3880b(parcel);
        this.f7663n = parcel.readString();
        this.f7664o = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f7651b);
        parcel.writeString(this.f7652c);
        parcel.writeString(this.f7653d);
        parcel.writeLong(this.f7654e);
        parcel.writeLong(this.f7655f);
        parcel.writeLong(this.f7656g);
        parcel.writeLong(this.f7657h);
        parcel.writeLong(this.f7658i);
        parcel.writeString(this.f7659j);
        parcel.writeLong(this.f7660k);
        parcel.writeByte(this.f7661l ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f7662m);
        parcel.writeInt(this.f7665p);
        parcel.writeInt(this.f7666q);
        C5942z.m3882b(parcel, this.f7667r);
        C5942z.m3882b(parcel, this.f7668s);
        parcel.writeString(this.f7663n);
        parcel.writeInt(this.f7664o);
    }
}
