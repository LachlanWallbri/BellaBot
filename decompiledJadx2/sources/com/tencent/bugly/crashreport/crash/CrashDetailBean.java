package com.tencent.bugly.crashreport.crash;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.crashreport.common.info.PlugInBean;
import com.tencent.bugly.proguard.C5942z;
import java.util.Map;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class CrashDetailBean implements Parcelable, Comparable<CrashDetailBean> {
    public static final Parcelable.Creator<CrashDetailBean> CREATOR = new Parcelable.Creator<CrashDetailBean>() { // from class: com.tencent.bugly.crashreport.crash.CrashDetailBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ CrashDetailBean createFromParcel(Parcel parcel) {
            return new CrashDetailBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ CrashDetailBean[] newArray(int i) {
            return new CrashDetailBean[i];
        }
    };

    /* renamed from: A */
    public String f7825A;

    /* renamed from: B */
    public String f7826B;

    /* renamed from: C */
    public long f7827C;

    /* renamed from: D */
    public long f7828D;

    /* renamed from: E */
    public long f7829E;

    /* renamed from: F */
    public long f7830F;

    /* renamed from: G */
    public long f7831G;

    /* renamed from: H */
    public long f7832H;

    /* renamed from: I */
    public String f7833I;

    /* renamed from: J */
    public String f7834J;

    /* renamed from: K */
    public String f7835K;

    /* renamed from: L */
    public String f7836L;

    /* renamed from: M */
    public long f7837M;

    /* renamed from: N */
    public boolean f7838N;

    /* renamed from: O */
    public Map<String, String> f7839O;

    /* renamed from: P */
    public int f7840P;

    /* renamed from: Q */
    public int f7841Q;

    /* renamed from: R */
    public Map<String, String> f7842R;

    /* renamed from: S */
    public Map<String, String> f7843S;

    /* renamed from: T */
    public byte[] f7844T;

    /* renamed from: U */
    public String f7845U;

    /* renamed from: V */
    public String f7846V;

    /* renamed from: W */
    private String f7847W;

    /* renamed from: a */
    public long f7848a;

    /* renamed from: b */
    public int f7849b;

    /* renamed from: c */
    public String f7850c;

    /* renamed from: d */
    public boolean f7851d;

    /* renamed from: e */
    public String f7852e;

    /* renamed from: f */
    public String f7853f;

    /* renamed from: g */
    public String f7854g;

    /* renamed from: h */
    public Map<String, PlugInBean> f7855h;

    /* renamed from: i */
    public Map<String, PlugInBean> f7856i;

    /* renamed from: j */
    public boolean f7857j;

    /* renamed from: k */
    public boolean f7858k;

    /* renamed from: l */
    public int f7859l;

    /* renamed from: m */
    public String f7860m;

    /* renamed from: n */
    public String f7861n;

    /* renamed from: o */
    public String f7862o;

    /* renamed from: p */
    public String f7863p;

    /* renamed from: q */
    public String f7864q;

    /* renamed from: r */
    public long f7865r;

    /* renamed from: s */
    public String f7866s;

    /* renamed from: t */
    public int f7867t;

    /* renamed from: u */
    public String f7868u;

    /* renamed from: v */
    public String f7869v;

    /* renamed from: w */
    public String f7870w;

    /* renamed from: x */
    public String f7871x;

    /* renamed from: y */
    public byte[] f7872y;

    /* renamed from: z */
    public Map<String, String> f7873z;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(CrashDetailBean crashDetailBean) {
        CrashDetailBean crashDetailBean2 = crashDetailBean;
        if (crashDetailBean2 == null) {
            return 1;
        }
        long j = this.f7865r - crashDetailBean2.f7865r;
        if (j <= 0) {
            return j < 0 ? -1 : 0;
        }
        return 1;
    }

    public CrashDetailBean() {
        this.f7848a = -1L;
        this.f7849b = 0;
        this.f7850c = UUID.randomUUID().toString();
        this.f7851d = false;
        this.f7852e = "";
        this.f7853f = "";
        this.f7854g = "";
        this.f7855h = null;
        this.f7856i = null;
        this.f7857j = false;
        this.f7858k = false;
        this.f7859l = 0;
        this.f7860m = "";
        this.f7861n = "";
        this.f7862o = "";
        this.f7863p = "";
        this.f7864q = "";
        this.f7865r = -1L;
        this.f7866s = null;
        this.f7867t = 0;
        this.f7868u = "";
        this.f7869v = "";
        this.f7870w = null;
        this.f7871x = null;
        this.f7872y = null;
        this.f7873z = null;
        this.f7825A = "";
        this.f7826B = "";
        this.f7827C = -1L;
        this.f7828D = -1L;
        this.f7829E = -1L;
        this.f7830F = -1L;
        this.f7831G = -1L;
        this.f7832H = -1L;
        this.f7833I = "";
        this.f7847W = "";
        this.f7834J = "";
        this.f7835K = "";
        this.f7836L = "";
        this.f7837M = -1L;
        this.f7838N = false;
        this.f7839O = null;
        this.f7840P = -1;
        this.f7841Q = -1;
        this.f7842R = null;
        this.f7843S = null;
        this.f7844T = null;
        this.f7845U = null;
        this.f7846V = null;
    }

    public CrashDetailBean(Parcel parcel) {
        this.f7848a = -1L;
        this.f7849b = 0;
        this.f7850c = UUID.randomUUID().toString();
        this.f7851d = false;
        this.f7852e = "";
        this.f7853f = "";
        this.f7854g = "";
        this.f7855h = null;
        this.f7856i = null;
        this.f7857j = false;
        this.f7858k = false;
        this.f7859l = 0;
        this.f7860m = "";
        this.f7861n = "";
        this.f7862o = "";
        this.f7863p = "";
        this.f7864q = "";
        this.f7865r = -1L;
        this.f7866s = null;
        this.f7867t = 0;
        this.f7868u = "";
        this.f7869v = "";
        this.f7870w = null;
        this.f7871x = null;
        this.f7872y = null;
        this.f7873z = null;
        this.f7825A = "";
        this.f7826B = "";
        this.f7827C = -1L;
        this.f7828D = -1L;
        this.f7829E = -1L;
        this.f7830F = -1L;
        this.f7831G = -1L;
        this.f7832H = -1L;
        this.f7833I = "";
        this.f7847W = "";
        this.f7834J = "";
        this.f7835K = "";
        this.f7836L = "";
        this.f7837M = -1L;
        this.f7838N = false;
        this.f7839O = null;
        this.f7840P = -1;
        this.f7841Q = -1;
        this.f7842R = null;
        this.f7843S = null;
        this.f7844T = null;
        this.f7845U = null;
        this.f7846V = null;
        this.f7849b = parcel.readInt();
        this.f7850c = parcel.readString();
        this.f7851d = parcel.readByte() == 1;
        this.f7852e = parcel.readString();
        this.f7853f = parcel.readString();
        this.f7854g = parcel.readString();
        this.f7857j = parcel.readByte() == 1;
        this.f7858k = parcel.readByte() == 1;
        this.f7859l = parcel.readInt();
        this.f7860m = parcel.readString();
        this.f7861n = parcel.readString();
        this.f7862o = parcel.readString();
        this.f7863p = parcel.readString();
        this.f7864q = parcel.readString();
        this.f7865r = parcel.readLong();
        this.f7866s = parcel.readString();
        this.f7867t = parcel.readInt();
        this.f7868u = parcel.readString();
        this.f7869v = parcel.readString();
        this.f7870w = parcel.readString();
        this.f7873z = C5942z.m3880b(parcel);
        this.f7825A = parcel.readString();
        this.f7826B = parcel.readString();
        this.f7827C = parcel.readLong();
        this.f7828D = parcel.readLong();
        this.f7829E = parcel.readLong();
        this.f7830F = parcel.readLong();
        this.f7831G = parcel.readLong();
        this.f7832H = parcel.readLong();
        this.f7833I = parcel.readString();
        this.f7847W = parcel.readString();
        this.f7834J = parcel.readString();
        this.f7835K = parcel.readString();
        this.f7836L = parcel.readString();
        this.f7837M = parcel.readLong();
        this.f7838N = parcel.readByte() == 1;
        this.f7839O = C5942z.m3880b(parcel);
        this.f7855h = C5942z.m3862a(parcel);
        this.f7856i = C5942z.m3862a(parcel);
        this.f7840P = parcel.readInt();
        this.f7841Q = parcel.readInt();
        this.f7842R = C5942z.m3880b(parcel);
        this.f7843S = C5942z.m3880b(parcel);
        this.f7844T = parcel.createByteArray();
        this.f7872y = parcel.createByteArray();
        this.f7845U = parcel.readString();
        this.f7846V = parcel.readString();
        this.f7871x = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f7849b);
        parcel.writeString(this.f7850c);
        parcel.writeByte(this.f7851d ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f7852e);
        parcel.writeString(this.f7853f);
        parcel.writeString(this.f7854g);
        parcel.writeByte(this.f7857j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f7858k ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f7859l);
        parcel.writeString(this.f7860m);
        parcel.writeString(this.f7861n);
        parcel.writeString(this.f7862o);
        parcel.writeString(this.f7863p);
        parcel.writeString(this.f7864q);
        parcel.writeLong(this.f7865r);
        parcel.writeString(this.f7866s);
        parcel.writeInt(this.f7867t);
        parcel.writeString(this.f7868u);
        parcel.writeString(this.f7869v);
        parcel.writeString(this.f7870w);
        C5942z.m3882b(parcel, this.f7873z);
        parcel.writeString(this.f7825A);
        parcel.writeString(this.f7826B);
        parcel.writeLong(this.f7827C);
        parcel.writeLong(this.f7828D);
        parcel.writeLong(this.f7829E);
        parcel.writeLong(this.f7830F);
        parcel.writeLong(this.f7831G);
        parcel.writeLong(this.f7832H);
        parcel.writeString(this.f7833I);
        parcel.writeString(this.f7847W);
        parcel.writeString(this.f7834J);
        parcel.writeString(this.f7835K);
        parcel.writeString(this.f7836L);
        parcel.writeLong(this.f7837M);
        parcel.writeByte(this.f7838N ? (byte) 1 : (byte) 0);
        C5942z.m3882b(parcel, this.f7839O);
        C5942z.m3863a(parcel, this.f7855h);
        C5942z.m3863a(parcel, this.f7856i);
        parcel.writeInt(this.f7840P);
        parcel.writeInt(this.f7841Q);
        C5942z.m3882b(parcel, this.f7842R);
        C5942z.m3882b(parcel, this.f7843S);
        parcel.writeByteArray(this.f7844T);
        parcel.writeByteArray(this.f7872y);
        parcel.writeString(this.f7845U);
        parcel.writeString(this.f7846V);
        parcel.writeString(this.f7871x);
    }
}
