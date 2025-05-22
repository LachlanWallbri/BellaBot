package com.pudutech.mirsdk.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapInfo.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0013\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0086\b\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u001d\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0018\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0019\u001a\u00020\bHÆ\u0003J\t\u0010\u001a\u001a\u00020\nHÆ\u0003J'\u0010\u001b\u001a\u00020\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\b\u0010\u001c\u001a\u00020\u0006H\u0016J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\u0006HÖ\u0001J\t\u0010\"\u001a\u00020\nHÖ\u0001J\u0018\u0010#\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u0006H\u0016R\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006'"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "mapVersion", "", "md5", "", "mapName", "", "(I[BLjava/lang/String;)V", "getMapName", "()Ljava/lang/String;", "setMapName", "(Ljava/lang/String;)V", "getMapVersion", "()I", "setMapVersion", "(I)V", "getMd5", "()[B", "setMd5", "([B)V", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "flags", "CREATOR", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final /* data */ class MapInfo implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String mapName;
    private int mapVersion;
    private byte[] md5;

    public static /* synthetic */ MapInfo copy$default(MapInfo mapInfo, int i, byte[] bArr, String str, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = mapInfo.mapVersion;
        }
        if ((i2 & 2) != 0) {
            bArr = mapInfo.md5;
        }
        if ((i2 & 4) != 0) {
            str = mapInfo.mapName;
        }
        return mapInfo.copy(i, bArr, str);
    }

    /* renamed from: component1, reason: from getter */
    public final int getMapVersion() {
        return this.mapVersion;
    }

    /* renamed from: component2, reason: from getter */
    public final byte[] getMd5() {
        return this.md5;
    }

    /* renamed from: component3, reason: from getter */
    public final String getMapName() {
        return this.mapName;
    }

    public final MapInfo copy(int mapVersion, byte[] md5, String mapName) {
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        return new MapInfo(mapVersion, md5, mapName);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MapInfo)) {
            return false;
        }
        MapInfo mapInfo = (MapInfo) other;
        return this.mapVersion == mapInfo.mapVersion && Intrinsics.areEqual(this.md5, mapInfo.md5) && Intrinsics.areEqual(this.mapName, mapInfo.mapName);
    }

    public int hashCode() {
        int i = this.mapVersion * 31;
        byte[] bArr = this.md5;
        int hashCode = (i + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31;
        String str = this.mapName;
        return hashCode + (str != null ? str.hashCode() : 0);
    }

    public String toString() {
        return "MapInfo(mapVersion=" + this.mapVersion + ", md5=" + Arrays.toString(this.md5) + ", mapName=" + this.mapName + ")";
    }

    public MapInfo(int i, byte[] md5, String mapName) {
        Intrinsics.checkParameterIsNotNull(md5, "md5");
        Intrinsics.checkParameterIsNotNull(mapName, "mapName");
        this.mapVersion = i;
        this.md5 = md5;
        this.mapName = mapName;
    }

    public final String getMapName() {
        return this.mapName;
    }

    public final int getMapVersion() {
        return this.mapVersion;
    }

    public final byte[] getMd5() {
        return this.md5;
    }

    public final void setMapName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mapName = str;
    }

    public final void setMapVersion(int i) {
        this.mapVersion = i;
    }

    public final void setMd5(byte[] bArr) {
        Intrinsics.checkParameterIsNotNull(bArr, "<set-?>");
        this.md5 = bArr;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public MapInfo(Parcel parcel) {
        this(r0, r1, r4);
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        int readInt = parcel.readInt();
        byte[] createByteArray = parcel.createByteArray();
        Intrinsics.checkExpressionValueIsNotNull(createByteArray, "parcel.createByteArray()");
        String readString = parcel.readString();
        Intrinsics.checkExpressionValueIsNotNull(readString, "parcel.readString()");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeInt(this.mapVersion);
        parcel.writeByteArray(this.md5);
        parcel.writeString(this.mapName);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MapInfo.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/MapInfo$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/aidl/serialize/MapInfo;", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.aidl.serialize.MapInfo$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes5.dex */
    public static final class Companion implements Parcelable.Creator<MapInfo> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapInfo createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            return new MapInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapInfo[] newArray(int size) {
            return new MapInfo[size];
        }
    }
}
