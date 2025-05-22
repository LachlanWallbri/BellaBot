package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class WakeuperResult implements Parcelable {
    public static final Parcelable.Creator<WakeuperResult> CREATOR = new Parcelable.Creator<WakeuperResult>() { // from class: com.iflytek.cloud.WakeuperResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public WakeuperResult createFromParcel(Parcel parcel) {
            return new WakeuperResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public WakeuperResult[] newArray(int i) {
            return new WakeuperResult[i];
        }
    };

    /* renamed from: a */
    private String f2839a;

    /* renamed from: b */
    private byte[] f2840b;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WakeuperResult(String str, byte[] bArr) {
        this(str);
        this.f2840b = bArr;
    }

    public WakeuperResult(Parcel parcel) {
        this.f2839a = "";
        this.f2840b = null;
        this.f2839a = parcel.readString();
    }

    public WakeuperResult(String str) {
        this.f2839a = "";
        this.f2840b = null;
        if (str != null) {
            this.f2839a = str;
        }
    }

    public String getResultString() {
        return this.f2839a;
    }

    public byte[] getBuffer() {
        return this.f2840b;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2839a);
    }
}
