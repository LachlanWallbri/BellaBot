package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class UnderstanderResult implements Parcelable {
    public static final Parcelable.Creator<UnderstanderResult> CREATOR = new Parcelable.Creator<UnderstanderResult>() { // from class: com.iflytek.cloud.UnderstanderResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public UnderstanderResult createFromParcel(Parcel parcel) {
            return new UnderstanderResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public UnderstanderResult[] newArray(int i) {
            return new UnderstanderResult[i];
        }
    };

    /* renamed from: a */
    private String f2834a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UnderstanderResult(Parcel parcel) {
        this.f2834a = "";
        this.f2834a = parcel.readString();
    }

    public UnderstanderResult(String str) {
        this.f2834a = "";
        if (str != null) {
            this.f2834a = str;
        }
    }

    public String getResultString() {
        return this.f2834a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2834a);
    }
}
