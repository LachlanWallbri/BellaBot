package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class IdentityResult implements Parcelable {
    public static final Parcelable.Creator<IdentityResult> CREATOR = new Parcelable.Creator<IdentityResult>() { // from class: com.iflytek.cloud.IdentityResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IdentityResult createFromParcel(Parcel parcel) {
            return new IdentityResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public IdentityResult[] newArray(int i) {
            return new IdentityResult[i];
        }
    };

    /* renamed from: a */
    private String f2769a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public IdentityResult(String str) {
        this.f2769a = "";
        if (str != null) {
            this.f2769a = str;
        }
    }

    private IdentityResult(Parcel parcel) {
        this.f2769a = "";
        this.f2769a = parcel.readString();
    }

    public String getResultString() {
        return this.f2769a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2769a);
    }
}
