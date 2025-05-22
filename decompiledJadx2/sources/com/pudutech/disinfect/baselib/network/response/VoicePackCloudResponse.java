package com.pudutech.disinfect.baselib.network.response;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes3.dex
 */
/* compiled from: VoicePackCloudResponse.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0016\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005¢\u0006\u0002\u0010\u0006J\u0019\u0010\n\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0003J#\u0010\u000b\u001a\u00020\u00002\u0018\b\u0002\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005HÆ\u0001J\t\u0010\f\u001a\u00020\rHÖ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\rHÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\u0019\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\rHÖ\u0001R*\u0010\u0002\u001a\u0012\u0012\u0004\u0012\u00020\u00040\u0003j\b\u0012\u0004\u0012\u00020\u0004`\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\u0006¨\u0006\u001a"}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/VoicePackCloudResponse;", "Landroid/os/Parcelable;", "list", "Ljava/util/ArrayList;", "Lcom/pudutech/disinfect/baselib/network/response/VoicePackCloud;", "Lkotlin/collections/ArrayList;", "(Ljava/util/ArrayList;)V", "getList", "()Ljava/util/ArrayList;", "setList", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class VoicePackCloudResponse implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private ArrayList<VoicePackCloud> list;

    /* JADX WARN: Classes with same name are omitted:
      classes3.dex
     */
    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            int readInt = in.readInt();
            ArrayList arrayList = new ArrayList(readInt);
            while (readInt != 0) {
                arrayList.add((VoicePackCloud) VoicePackCloud.CREATOR.createFromParcel(in));
                readInt--;
            }
            return new VoicePackCloudResponse(arrayList);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new VoicePackCloudResponse[i];
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ VoicePackCloudResponse copy$default(VoicePackCloudResponse voicePackCloudResponse, ArrayList arrayList, int i, Object obj) {
        if ((i & 1) != 0) {
            arrayList = voicePackCloudResponse.list;
        }
        return voicePackCloudResponse.copy(arrayList);
    }

    public final ArrayList<VoicePackCloud> component1() {
        return this.list;
    }

    public final VoicePackCloudResponse copy(ArrayList<VoicePackCloud> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        return new VoicePackCloudResponse(list);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof VoicePackCloudResponse) && Intrinsics.areEqual(this.list, ((VoicePackCloudResponse) other).list);
        }
        return true;
    }

    public int hashCode() {
        ArrayList<VoicePackCloud> arrayList = this.list;
        if (arrayList != null) {
            return arrayList.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "VoicePackCloudResponse(list=" + this.list + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        ArrayList<VoicePackCloud> arrayList = this.list;
        parcel.writeInt(arrayList.size());
        Iterator<VoicePackCloud> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(parcel, 0);
        }
    }

    public VoicePackCloudResponse(ArrayList<VoicePackCloud> list) {
        Intrinsics.checkParameterIsNotNull(list, "list");
        this.list = list;
    }

    public final ArrayList<VoicePackCloud> getList() {
        return this.list;
    }

    public final void setList(ArrayList<VoicePackCloud> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.list = arrayList;
    }
}
