package com.iflytek.speech;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class VerifierResult implements Parcelable {
    public static final Parcelable.Creator<VerifierResult> CREATOR = new Parcelable.Creator<VerifierResult>() { // from class: com.iflytek.speech.VerifierResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VerifierResult createFromParcel(Parcel parcel) {
            return new VerifierResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public VerifierResult[] newArray(int i) {
            return new VerifierResult[i];
        }
    };
    private String json;
    public String sst;
    public boolean ret = false;
    public String dcs = "";
    public String vid = "";
    public int suc = 0;
    public int rgn = 0;
    public String trs = "";
    public String source = "";

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VerifierResult(Parcel parcel) {
        this.json = "";
        this.json = parcel.readString();
    }

    public VerifierResult(String str) {
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
