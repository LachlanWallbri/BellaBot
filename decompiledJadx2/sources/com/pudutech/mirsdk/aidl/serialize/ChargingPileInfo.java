package com.pudutech.mirsdk.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ChargingPileInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u001d\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 32\u00020\u0001:\u00013B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B5\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u0006\u0012\u0006\u0010\r\u001a\u00020\u0006¢\u0006\u0002\u0010\u000eJ\t\u0010!\u001a\u00020\u0006HÆ\u0003J\t\u0010\"\u001a\u00020\u0006HÆ\u0003J\t\u0010#\u001a\u00020\tHÆ\u0003J\t\u0010$\u001a\u00020\u000bHÆ\u0003J\t\u0010%\u001a\u00020\u0006HÆ\u0003J\t\u0010&\u001a\u00020\u0006HÆ\u0003JE\u0010'\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00062\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00062\b\b\u0002\u0010\r\u001a\u00020\u0006HÆ\u0001J\b\u0010(\u001a\u00020)H\u0016J\u0013\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-HÖ\u0003J\t\u0010.\u001a\u00020)HÖ\u0001J\t\u0010/\u001a\u00020\u0006HÖ\u0001J\u0018\u00100\u001a\u0002012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00102\u001a\u00020)H\u0016R\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\f\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0014\"\u0004\b\u0018\u0010\u0016R\u001a\u0010\u0007\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016R\u001a\u0010\r\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0014\"\u0004\b\u001c\u0010\u0016R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u00064"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/ChargingPileInfo;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "floor", "", "id", "pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "alignRange", "", MapElement.Key.GROUP, "mac", "(Ljava/lang/String;Ljava/lang/String;Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;DLjava/lang/String;Ljava/lang/String;)V", "getAlignRange", "()D", "setAlignRange", "(D)V", "getFloor", "()Ljava/lang/String;", "setFloor", "(Ljava/lang/String;)V", "getGroup", "setGroup", "getId", "setId", "getMac", "setMac", "getPose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setPose", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "component1", "component2", "component3", "component4", "component5", "component6", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "flags", "CREATOR", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class ChargingPileInfo implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private double alignRange;
    private String floor;
    private String group;
    private String id;
    private String mac;
    private Vector3d pose;

    public static /* synthetic */ ChargingPileInfo copy$default(ChargingPileInfo chargingPileInfo, String str, String str2, Vector3d vector3d, double d, String str3, String str4, int i, Object obj) {
        if ((i & 1) != 0) {
            str = chargingPileInfo.floor;
        }
        if ((i & 2) != 0) {
            str2 = chargingPileInfo.id;
        }
        String str5 = str2;
        if ((i & 4) != 0) {
            vector3d = chargingPileInfo.pose;
        }
        Vector3d vector3d2 = vector3d;
        if ((i & 8) != 0) {
            d = chargingPileInfo.alignRange;
        }
        double d2 = d;
        if ((i & 16) != 0) {
            str3 = chargingPileInfo.group;
        }
        String str6 = str3;
        if ((i & 32) != 0) {
            str4 = chargingPileInfo.mac;
        }
        return chargingPileInfo.copy(str, str5, vector3d2, d2, str6, str4);
    }

    /* renamed from: component1, reason: from getter */
    public final String getFloor() {
        return this.floor;
    }

    /* renamed from: component2, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component3, reason: from getter */
    public final Vector3d getPose() {
        return this.pose;
    }

    /* renamed from: component4, reason: from getter */
    public final double getAlignRange() {
        return this.alignRange;
    }

    /* renamed from: component5, reason: from getter */
    public final String getGroup() {
        return this.group;
    }

    /* renamed from: component6, reason: from getter */
    public final String getMac() {
        return this.mac;
    }

    public final ChargingPileInfo copy(String floor, String id, Vector3d pose, double alignRange, String group, String mac) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        Intrinsics.checkParameterIsNotNull(group, "group");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        return new ChargingPileInfo(floor, id, pose, alignRange, group, mac);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ChargingPileInfo)) {
            return false;
        }
        ChargingPileInfo chargingPileInfo = (ChargingPileInfo) other;
        return Intrinsics.areEqual(this.floor, chargingPileInfo.floor) && Intrinsics.areEqual(this.id, chargingPileInfo.id) && Intrinsics.areEqual(this.pose, chargingPileInfo.pose) && Double.compare(this.alignRange, chargingPileInfo.alignRange) == 0 && Intrinsics.areEqual(this.group, chargingPileInfo.group) && Intrinsics.areEqual(this.mac, chargingPileInfo.mac);
    }

    public int hashCode() {
        String str = this.floor;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.id;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Vector3d vector3d = this.pose;
        int hashCode3 = (hashCode2 + (vector3d != null ? vector3d.hashCode() : 0)) * 31;
        long doubleToLongBits = Double.doubleToLongBits(this.alignRange);
        int i = (hashCode3 + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31;
        String str3 = this.group;
        int hashCode4 = (i + (str3 != null ? str3.hashCode() : 0)) * 31;
        String str4 = this.mac;
        return hashCode4 + (str4 != null ? str4.hashCode() : 0);
    }

    public String toString() {
        return "ChargingPileInfo(floor=" + this.floor + ", id=" + this.id + ", pose=" + this.pose + ", alignRange=" + this.alignRange + ", group=" + this.group + ", mac=" + this.mac + ")";
    }

    public ChargingPileInfo(String floor, String id, Vector3d pose, double d, String group, String mac) {
        Intrinsics.checkParameterIsNotNull(floor, "floor");
        Intrinsics.checkParameterIsNotNull(id, "id");
        Intrinsics.checkParameterIsNotNull(pose, "pose");
        Intrinsics.checkParameterIsNotNull(group, "group");
        Intrinsics.checkParameterIsNotNull(mac, "mac");
        this.floor = floor;
        this.id = id;
        this.pose = pose;
        this.alignRange = d;
        this.group = group;
        this.mac = mac;
    }

    public final double getAlignRange() {
        return this.alignRange;
    }

    public final String getFloor() {
        return this.floor;
    }

    public final String getGroup() {
        return this.group;
    }

    public final String getId() {
        return this.id;
    }

    public final String getMac() {
        return this.mac;
    }

    public final Vector3d getPose() {
        return this.pose;
    }

    public final void setAlignRange(double d) {
        this.alignRange = d;
    }

    public final void setFloor(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.floor = str;
    }

    public final void setGroup(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.group = str;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final void setMac(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mac = str;
    }

    public final void setPose(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.pose = vector3d;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public ChargingPileInfo(Parcel parcel) {
        this(r2, r3, (Vector3d) r1, r5, r7, r8);
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        String readString = parcel.readString();
        Intrinsics.checkExpressionValueIsNotNull(readString, "parcel.readString()");
        String readString2 = parcel.readString();
        Intrinsics.checkExpressionValueIsNotNull(readString2, "parcel.readString()");
        Parcelable readParcelable = parcel.readParcelable(Vector3d.class.getClassLoader());
        Intrinsics.checkExpressionValueIsNotNull(readParcelable, "parcel.readParcelable(Ve…::class.java.classLoader)");
        double readDouble = parcel.readDouble();
        String readString3 = parcel.readString();
        Intrinsics.checkExpressionValueIsNotNull(readString3, "parcel.readString()");
        String readString4 = parcel.readString();
        Intrinsics.checkExpressionValueIsNotNull(readString4, "parcel.readString()");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(this.floor);
        parcel.writeString(this.id);
        parcel.writeParcelable(this.pose, flags);
        parcel.writeDouble(this.alignRange);
        parcel.writeString(this.group);
        parcel.writeString(this.mac);
    }

    /* compiled from: ChargingPileInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/ChargingPileInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/aidl/serialize/ChargingPileInfo;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/aidl/serialize/ChargingPileInfo;", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.aidl.serialize.ChargingPileInfo$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes5.dex */
    public static final class Companion implements Parcelable.Creator<ChargingPileInfo> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChargingPileInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new ChargingPileInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChargingPileInfo[] newArray(int size) {
            return new ChargingPileInfo[size];
        }
    }
}
