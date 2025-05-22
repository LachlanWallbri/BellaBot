package com.iflytek.speech;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class UnderstanderResult implements Parcelable {
    public static final Parcelable.Creator<UnderstanderResult> CREATOR = new Parcelable.Creator<UnderstanderResult>() { // from class: com.iflytek.speech.UnderstanderResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UnderstanderResult createFromParcel(Parcel parcel) {
            return new UnderstanderResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UnderstanderResult[] newArray(int i) {
            return new UnderstanderResult[i];
        }
    };
    private String mXml;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public UnderstanderResult(Parcel parcel) {
        this.mXml = "";
        this.mXml = parcel.readString();
    }

    public UnderstanderResult(String str) {
        this.mXml = "";
        if (str != null) {
            this.mXml = str;
        }
    }

    public String getResultString() {
        return this.mXml;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mXml);
    }
}
