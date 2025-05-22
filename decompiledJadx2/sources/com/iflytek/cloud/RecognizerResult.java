package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class RecognizerResult implements Parcelable {
    public static final Parcelable.Creator<RecognizerResult> CREATOR = new Parcelable.Creator<RecognizerResult>() { // from class: com.iflytek.cloud.RecognizerResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RecognizerResult createFromParcel(Parcel parcel) {
            return new RecognizerResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RecognizerResult[] newArray(int i) {
            return new RecognizerResult[i];
        }
    };

    /* renamed from: a */
    private String f2775a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RecognizerResult(Parcel parcel) {
        this.f2775a = "";
        this.f2775a = parcel.readString();
    }

    public RecognizerResult(String str) {
        this.f2775a = "";
        if (str != null) {
            this.f2775a = str;
        }
    }

    public String getResultString() {
        return this.f2775a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2775a);
    }
}
