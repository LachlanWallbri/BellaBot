package com.pudutech.disinfect.baselib.network.response.ad;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AdverListResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001d\u0010\u0011\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J1\u0010\u0013\u001a\u00020\u00002\u001c\b\u0002\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0015HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0015HÖ\u0001R.\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003j\n\u0012\u0004\u0012\u00020\u0004\u0018\u0001`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\""}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/ad/AdverListResp;", "Landroid/os/Parcelable;", "ad", "Ljava/util/ArrayList;", "Lcom/pudutech/disinfect/baselib/network/response/ad/AdInfo;", "Lkotlin/collections/ArrayList;", "update_time", "", "(Ljava/util/ArrayList;J)V", "getAd", "()Ljava/util/ArrayList;", "setAd", "(Ljava/util/ArrayList;)V", "getUpdate_time", "()J", "setUpdate_time", "(J)V", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class AdverListResp implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private ArrayList<AdInfo> ad;
    private long update_time;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            ArrayList arrayList;
            Intrinsics.checkParameterIsNotNull(in, "in");
            if (in.readInt() != 0) {
                int readInt = in.readInt();
                arrayList = new ArrayList(readInt);
                while (readInt != 0) {
                    arrayList.add((AdInfo) AdInfo.CREATOR.createFromParcel(in));
                    readInt--;
                }
            } else {
                arrayList = null;
            }
            return new AdverListResp(arrayList, in.readLong());
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new AdverListResp[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ AdverListResp copy$default(AdverListResp adverListResp, ArrayList arrayList, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = adverListResp.ad;
        }
        if ((i & 2) != 0) {
            j = adverListResp.update_time;
        }
        return adverListResp.copy(arrayList, j);
    }

    public final ArrayList<AdInfo> component1() {
        return this.ad;
    }

    /* renamed from: component2, reason: from getter */
    public final long getUpdate_time() {
        return this.update_time;
    }

    public final AdverListResp copy(ArrayList<AdInfo> ad, long update_time) {
        return new AdverListResp(ad, update_time);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AdverListResp)) {
            return false;
        }
        AdverListResp adverListResp = (AdverListResp) other;
        return Intrinsics.areEqual(this.ad, adverListResp.ad) && this.update_time == adverListResp.update_time;
    }

    public int hashCode() {
        ArrayList<AdInfo> arrayList = this.ad;
        return ((arrayList != null ? arrayList.hashCode() : 0) * 31) + Long.hashCode(this.update_time);
    }

    public String toString() {
        return "AdverListResp(ad=" + this.ad + ", update_time=" + this.update_time + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        ArrayList<AdInfo> arrayList = this.ad;
        if (arrayList != null) {
            parcel.writeInt(1);
            parcel.writeInt(arrayList.size());
            Iterator<AdInfo> it = arrayList.iterator();
            while (it.hasNext()) {
                it.next().writeToParcel(parcel, 0);
            }
        } else {
            parcel.writeInt(0);
        }
        parcel.writeLong(this.update_time);
    }

    public AdverListResp(ArrayList<AdInfo> arrayList, long j) {
        this.ad = arrayList;
        this.update_time = j;
    }

    public /* synthetic */ AdverListResp(ArrayList arrayList, long j, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(arrayList, (i & 2) != 0 ? -1L : j);
    }

    public final ArrayList<AdInfo> getAd() {
        return this.ad;
    }

    public final long getUpdate_time() {
        return this.update_time;
    }

    public final void setAd(ArrayList<AdInfo> arrayList) {
        this.ad = arrayList;
    }

    public final void setUpdate_time(long j) {
        this.update_time = j;
    }
}
