package com.iflytek.speech;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class WakeuperResult implements Parcelable {
    public static final Parcelable.Creator<WakeuperResult> CREATOR = new Parcelable.Creator<WakeuperResult>() { // from class: com.iflytek.speech.WakeuperResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WakeuperResult createFromParcel(Parcel parcel) {
            return new WakeuperResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public WakeuperResult[] newArray(int i) {
            return new WakeuperResult[i];
        }
    };
    private String json;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public WakeuperResult(Parcel parcel) {
        this.json = "";
        this.json = parcel.readString();
    }

    public WakeuperResult(String str) {
        this.json = "";
        if (str != null) {
            this.json = str;
        }
    }

    public String getResultString() {
        return this.json;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.json);
    }
}
