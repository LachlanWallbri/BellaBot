package com.pudutech.mirsdk.hardware.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: ScheduleCommunicateInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 $2\u00020\u0001:\u0001$B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u001f\u001a\u00020\u0007H\u0016J\u0018\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00042\u0006\u0010#\u001a\u00020\u0007H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR*\u0010\f\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000eX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\t\"\u0004\b\u001b\u0010\u000bR*\u0010\u001c\u001a\u0012\u0012\u0004\u0012\u00020\u000e0\rj\b\u0012\u0004\u0012\u00020\u000e`\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0011\"\u0004\b\u001e\u0010\u0013¨\u0006%"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/ScheduleCommunicateInfo;", "Landroid/os/Parcelable;", "()V", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "esp_count", "", "getEsp_count", "()I", "setEsp_count", "(I)V", "esp_linker", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getEsp_linker", "()Ljava/util/ArrayList;", "setEsp_linker", "(Ljava/util/ArrayList;)V", "map_flag", "getMap_flag", "()Ljava/lang/String;", "setMap_flag", "(Ljava/lang/String;)V", "udp_count", "getUdp_count", "setUdp_count", "udp_linker", "getUdp_linker", "setUdp_linker", "describeContents", "writeToParcel", "", "dest", "flags", "CREATOR", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public final class ScheduleCommunicateInfo implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int esp_count;
    private ArrayList<String> esp_linker;
    private String map_flag;
    private int udp_count;
    private ArrayList<String> udp_linker;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final int getEsp_count() {
        return this.esp_count;
    }

    public final void setEsp_count(int i) {
        this.esp_count = i;
    }

    public final int getUdp_count() {
        return this.udp_count;
    }

    public final void setUdp_count(int i) {
        this.udp_count = i;
    }

    public final String getMap_flag() {
        return this.map_flag;
    }

    public final void setMap_flag(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.map_flag = str;
    }

    public final ArrayList<String> getEsp_linker() {
        return this.esp_linker;
    }

    public final void setEsp_linker(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.esp_linker = arrayList;
    }

    public final ArrayList<String> getUdp_linker() {
        return this.udp_linker;
    }

    public final void setUdp_linker(ArrayList<String> arrayList) {
        Intrinsics.checkParameterIsNotNull(arrayList, "<set-?>");
        this.udp_linker = arrayList;
    }

    public ScheduleCommunicateInfo() {
        this.map_flag = "";
        this.esp_linker = new ArrayList<>();
        this.udp_linker = new ArrayList<>();
        this.esp_count = 0;
        this.udp_count = 0;
        this.map_flag = "";
        this.esp_linker = new ArrayList<>();
        this.udp_linker = new ArrayList<>();
    }

    public ScheduleCommunicateInfo(Parcel source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.map_flag = "";
        this.esp_linker = new ArrayList<>();
        this.udp_linker = new ArrayList<>();
        this.esp_count = source.readInt();
        this.udp_count = source.readInt();
        String readString = source.readString();
        if (readString == null) {
            Intrinsics.throwNpe();
        }
        this.map_flag = readString;
        ArrayList<String> createStringArrayList = source.createStringArrayList();
        Intrinsics.checkExpressionValueIsNotNull(createStringArrayList, "source.createStringArrayList()");
        this.esp_linker = createStringArrayList;
        ArrayList<String> createStringArrayList2 = source.createStringArrayList();
        Intrinsics.checkExpressionValueIsNotNull(createStringArrayList2, "source.createStringArrayList()");
        this.udp_linker = createStringArrayList2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkParameterIsNotNull(dest, "dest");
        dest.writeInt(this.esp_count);
        dest.writeInt(this.udp_count);
        dest.writeString(this.map_flag);
        dest.writeStringList(this.esp_linker);
        dest.writeStringList(this.udp_linker);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: ScheduleCommunicateInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/ScheduleCommunicateInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/hardware/serialize/ScheduleCommunicateInfo;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/hardware/serialize/ScheduleCommunicateInfo;", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.serialize.ScheduleCommunicateInfo$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion implements Parcelable.Creator<ScheduleCommunicateInfo> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScheduleCommunicateInfo createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new ScheduleCommunicateInfo(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScheduleCommunicateInfo[] newArray(int size) {
            ScheduleCommunicateInfo[] scheduleCommunicateInfoArr = new ScheduleCommunicateInfo[size];
            for (int i = 0; i < size; i++) {
                scheduleCommunicateInfoArr[i] = new ScheduleCommunicateInfo();
            }
            return scheduleCommunicateInfoArr;
        }
    }
}
