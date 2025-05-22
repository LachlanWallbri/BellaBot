package com.iflytek.cloud;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes3.dex */
public class EvaluatorResult implements Parcelable {
    public static final Parcelable.Creator<EvaluatorResult> CREATOR = new Parcelable.Creator<EvaluatorResult>() { // from class: com.iflytek.cloud.EvaluatorResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public EvaluatorResult createFromParcel(Parcel parcel) {
            return new EvaluatorResult(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public EvaluatorResult[] newArray(int i) {
            return new EvaluatorResult[i];
        }
    };

    /* renamed from: a */
    private String f2760a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private EvaluatorResult(Parcel parcel) {
        this.f2760a = "";
        this.f2760a = parcel.readString();
    }

    public EvaluatorResult(String str) {
        this.f2760a = "";
        if (str != null) {
            this.f2760a = str;
        }
    }

    public String getResultString() {
        return this.f2760a;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f2760a);
    }
}
