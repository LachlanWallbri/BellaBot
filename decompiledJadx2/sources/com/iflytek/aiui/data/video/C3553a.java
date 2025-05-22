package com.iflytek.aiui.data.video;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* renamed from: com.iflytek.aiui.data.video.a */
/* loaded from: classes4.dex */
public class C3553a implements Comparable<C3553a>, Parcelable {

    /* renamed from: a */
    private final int f2186a;

    /* renamed from: b */
    private final int f2187b;

    /* renamed from: c */
    private static final HashMap<Integer, HashMap<Integer, C3553a>> f2185c = new HashMap<>(16);
    public static final Parcelable.Creator<C3553a> CREATOR = new a();

    /* renamed from: com.iflytek.aiui.data.video.a$a */
    /* loaded from: classes4.dex */
    static final class a implements Parcelable.Creator<C3553a> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public C3553a createFromParcel(Parcel parcel) {
            return C3553a.m817d(parcel.readInt(), parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public C3553a[] newArray(int i) {
            return new C3553a[i];
        }
    }

    private C3553a(int i, int i2) {
        this.f2186a = i;
        this.f2187b = i2;
    }

    /* renamed from: b */
    private static int m816b(int i, int i2) {
        while (i2 != 0) {
            int i3 = i2;
            i2 = i % i2;
            i = i3;
        }
        return i;
    }

    /* renamed from: d */
    public static C3553a m817d(int i, int i2) {
        int m816b = m816b(i, i2);
        int i3 = i / m816b;
        int i4 = i2 / m816b;
        HashMap<Integer, HashMap<Integer, C3553a>> hashMap = f2185c;
        HashMap<Integer, C3553a> hashMap2 = hashMap.get(Integer.valueOf(i3));
        if (hashMap2 == null) {
            C3553a c3553a = new C3553a(i3, i4);
            HashMap<Integer, C3553a> hashMap3 = new HashMap<>();
            hashMap3.put(Integer.valueOf(i4), c3553a);
            hashMap.put(Integer.valueOf(i3), hashMap3);
            return c3553a;
        }
        C3553a c3553a2 = hashMap2.get(Integer.valueOf(i4));
        if (c3553a2 != null) {
            return c3553a2;
        }
        C3553a c3553a3 = new C3553a(i3, i4);
        hashMap2.put(Integer.valueOf(i4), c3553a3);
        return c3553a3;
    }

    /* renamed from: e */
    public static C3553a m818e(String str) {
        int indexOf = str.indexOf(58);
        if (indexOf == -1) {
            throw new IllegalArgumentException("Malformed aspect ratio: " + str);
        }
        try {
            return m817d(Integer.parseInt(str.substring(0, indexOf)), Integer.parseInt(str.substring(indexOf + 1)));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Malformed aspect ratio: " + str, e);
        }
    }

    @Override // java.lang.Comparable
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compareTo(C3553a c3553a) {
        if (equals(c3553a)) {
            return 0;
        }
        return m821f() - c3553a.m821f() > 0.0f ? 1 : -1;
    }

    /* renamed from: c */
    public boolean m820c(C3557e c3557e) {
        int m816b = m816b(c3557e.m831c(), c3557e.m830b());
        return this.f2186a == c3557e.m831c() / m816b && this.f2187b == c3557e.m830b() / m816b;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C3553a)) {
            return false;
        }
        C3553a c3553a = (C3553a) obj;
        return this.f2186a == c3553a.f2186a && this.f2187b == c3553a.f2187b;
    }

    /* renamed from: f */
    public float m821f() {
        return this.f2186a / this.f2187b;
    }

    public int hashCode() {
        int i = this.f2187b;
        int i2 = this.f2186a;
        return i ^ ((i2 << 16) | (i2 >>> 16));
    }

    public String toString() {
        return this.f2186a + ":" + this.f2187b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2186a);
        parcel.writeInt(this.f2187b);
    }
}
