package com.pudutech.disinfect.baselib.network.response;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SwitchEnvResp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B#\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0003HÆ\u0003¢\u0006\u0002\u0010\tJ\u0011\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J,\u0010\u0013\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001¢\u0006\u0002\u0010\u0014J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\u0019\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0003HÖ\u0001R\u001e\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\f\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\""}, m3961d2 = {"Lcom/pudutech/disinfect/baselib/network/response/SwitchEnvResp;", "Landroid/os/Parcelable;", "count", "", "item", "", "Lcom/pudutech/disinfect/baselib/network/response/EnvItem;", "(Ljava/lang/Integer;Ljava/util/List;)V", "getCount", "()Ljava/lang/Integer;", "setCount", "(Ljava/lang/Integer;)V", "Ljava/lang/Integer;", "getItem", "()Ljava/util/List;", "setItem", "(Ljava/util/List;)V", "component1", "component2", "copy", "(Ljava/lang/Integer;Ljava/util/List;)Lcom/pudutech/disinfect/baselib/network/response/SwitchEnvResp;", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "module_baselib_robot_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class SwitchEnvResp implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Creator();
    private Integer count;
    private List<EnvItem> item;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes5.dex */
    public static class Creator implements Parcelable.Creator {
        @Override // android.os.Parcelable.Creator
        public final Object createFromParcel(Parcel in) {
            Intrinsics.checkParameterIsNotNull(in, "in");
            ArrayList arrayList = null;
            Integer valueOf = in.readInt() != 0 ? Integer.valueOf(in.readInt()) : null;
            if (in.readInt() != 0) {
                int readInt = in.readInt();
                ArrayList arrayList2 = new ArrayList(readInt);
                while (readInt != 0) {
                    arrayList2.add((EnvItem) EnvItem.CREATOR.createFromParcel(in));
                    readInt--;
                }
                arrayList = arrayList2;
            }
            return new SwitchEnvResp(valueOf, arrayList);
        }

        @Override // android.os.Parcelable.Creator
        public final Object[] newArray(int i) {
            return new SwitchEnvResp[i];
        }
    }

    public SwitchEnvResp() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SwitchEnvResp copy$default(SwitchEnvResp switchEnvResp, Integer num, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            num = switchEnvResp.count;
        }
        if ((i & 2) != 0) {
            list = switchEnvResp.item;
        }
        return switchEnvResp.copy(num, list);
    }

    /* renamed from: component1, reason: from getter */
    public final Integer getCount() {
        return this.count;
    }

    public final List<EnvItem> component2() {
        return this.item;
    }

    public final SwitchEnvResp copy(Integer count, List<EnvItem> item) {
        return new SwitchEnvResp(count, item);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SwitchEnvResp)) {
            return false;
        }
        SwitchEnvResp switchEnvResp = (SwitchEnvResp) other;
        return Intrinsics.areEqual(this.count, switchEnvResp.count) && Intrinsics.areEqual(this.item, switchEnvResp.item);
    }

    public int hashCode() {
        Integer num = this.count;
        int hashCode = (num != null ? num.hashCode() : 0) * 31;
        List<EnvItem> list = this.item;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    public String toString() {
        return "SwitchEnvResp(count=" + this.count + ", item=" + this.item + ")";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        Integer num = this.count;
        if (num != null) {
            parcel.writeInt(1);
            parcel.writeInt(num.intValue());
        } else {
            parcel.writeInt(0);
        }
        List<EnvItem> list = this.item;
        if (list == null) {
            parcel.writeInt(0);
            return;
        }
        parcel.writeInt(1);
        parcel.writeInt(list.size());
        Iterator<EnvItem> it = list.iterator();
        while (it.hasNext()) {
            it.next().writeToParcel(parcel, 0);
        }
    }

    public SwitchEnvResp(Integer num, List<EnvItem> list) {
        this.count = num;
        this.item = list;
    }

    public /* synthetic */ SwitchEnvResp(Integer num, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? (Integer) null : num, (i & 2) != 0 ? (List) null : list);
    }

    public final Integer getCount() {
        return this.count;
    }

    public final void setCount(Integer num) {
        this.count = num;
    }

    public final List<EnvItem> getItem() {
        return this.item;
    }

    public final void setItem(List<EnvItem> list) {
        this.item = list;
    }
}
