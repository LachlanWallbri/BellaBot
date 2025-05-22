package com.tencent.bugly.crashreport.common.strategy;

import android.os.Parcel;
import android.os.Parcelable;
import com.tencent.bugly.proguard.C5942z;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* compiled from: BUGLY */
/* loaded from: classes7.dex */
public class StrategyBean implements Parcelable {
    public static final Parcelable.Creator<StrategyBean> CREATOR = new Parcelable.Creator<StrategyBean>() { // from class: com.tencent.bugly.crashreport.common.strategy.StrategyBean.1
        @Override // android.os.Parcelable.Creator
        public final /* synthetic */ StrategyBean createFromParcel(Parcel parcel) {
            return new StrategyBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public final /* bridge */ /* synthetic */ StrategyBean[] newArray(int i) {
            return new StrategyBean[i];
        }
    };

    /* renamed from: a */
    public static String f7784a = "http://rqd.uu.qq.com/rqd/sync";

    /* renamed from: b */
    public static String f7785b = "http://android.bugly.qq.com/rqd/async";

    /* renamed from: c */
    public static String f7786c = "http://android.bugly.qq.com/rqd/async";

    /* renamed from: d */
    public static String f7787d;

    /* renamed from: e */
    public long f7788e;

    /* renamed from: f */
    public long f7789f;

    /* renamed from: g */
    public boolean f7790g;

    /* renamed from: h */
    public boolean f7791h;

    /* renamed from: i */
    public boolean f7792i;

    /* renamed from: j */
    public boolean f7793j;

    /* renamed from: k */
    public boolean f7794k;

    /* renamed from: l */
    public boolean f7795l;

    /* renamed from: m */
    public boolean f7796m;

    /* renamed from: n */
    public boolean f7797n;

    /* renamed from: o */
    public boolean f7798o;

    /* renamed from: p */
    public long f7799p;

    /* renamed from: q */
    public long f7800q;

    /* renamed from: r */
    public String f7801r;

    /* renamed from: s */
    public String f7802s;

    /* renamed from: t */
    public String f7803t;

    /* renamed from: u */
    public String f7804u;

    /* renamed from: v */
    public Map<String, String> f7805v;

    /* renamed from: w */
    public int f7806w;

    /* renamed from: x */
    public long f7807x;

    /* renamed from: y */
    public long f7808y;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public StrategyBean() {
        this.f7788e = -1L;
        this.f7789f = -1L;
        this.f7790g = true;
        this.f7791h = true;
        this.f7792i = true;
        this.f7793j = true;
        this.f7794k = false;
        this.f7795l = true;
        this.f7796m = true;
        this.f7797n = true;
        this.f7798o = true;
        this.f7800q = 30000L;
        this.f7801r = f7785b;
        this.f7802s = f7786c;
        this.f7803t = f7784a;
        this.f7806w = 10;
        this.f7807x = 300000L;
        this.f7808y = -1L;
        this.f7789f = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        sb.append("S(@L@L");
        sb.append("@)");
        f7787d = sb.toString();
        sb.setLength(0);
        sb.append("*^@K#K");
        sb.append("@!");
        this.f7804u = sb.toString();
    }

    public StrategyBean(Parcel parcel) {
        this.f7788e = -1L;
        this.f7789f = -1L;
        boolean z = true;
        this.f7790g = true;
        this.f7791h = true;
        this.f7792i = true;
        this.f7793j = true;
        this.f7794k = false;
        this.f7795l = true;
        this.f7796m = true;
        this.f7797n = true;
        this.f7798o = true;
        this.f7800q = 30000L;
        this.f7801r = f7785b;
        this.f7802s = f7786c;
        this.f7803t = f7784a;
        this.f7806w = 10;
        this.f7807x = 300000L;
        this.f7808y = -1L;
        try {
            f7787d = "S(@L@L@)";
            this.f7789f = parcel.readLong();
            this.f7790g = parcel.readByte() == 1;
            this.f7791h = parcel.readByte() == 1;
            this.f7792i = parcel.readByte() == 1;
            this.f7801r = parcel.readString();
            this.f7802s = parcel.readString();
            this.f7804u = parcel.readString();
            this.f7805v = C5942z.m3880b(parcel);
            this.f7793j = parcel.readByte() == 1;
            this.f7794k = parcel.readByte() == 1;
            this.f7797n = parcel.readByte() == 1;
            this.f7798o = parcel.readByte() == 1;
            this.f7800q = parcel.readLong();
            this.f7795l = parcel.readByte() == 1;
            if (parcel.readByte() != 1) {
                z = false;
            }
            this.f7796m = z;
            this.f7799p = parcel.readLong();
            this.f7806w = parcel.readInt();
            this.f7807x = parcel.readLong();
            this.f7808y = parcel.readLong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f7789f);
        parcel.writeByte(this.f7790g ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f7791h ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f7792i ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f7801r);
        parcel.writeString(this.f7802s);
        parcel.writeString(this.f7804u);
        C5942z.m3882b(parcel, this.f7805v);
        parcel.writeByte(this.f7793j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f7794k ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f7797n ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f7798o ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f7800q);
        parcel.writeByte(this.f7795l ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f7796m ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f7799p);
        parcel.writeInt(this.f7806w);
        parcel.writeLong(this.f7807x);
        parcel.writeLong(this.f7808y);
    }
}
