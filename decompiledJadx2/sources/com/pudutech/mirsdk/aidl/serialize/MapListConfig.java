package com.pudutech.mirsdk.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapListConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000  2\u00020\u0001:\u0001 B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005B7\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0007\u0012\u0014\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\f¢\u0006\u0002\u0010\rJ\b\u0010\u001c\u001a\u00020\tH\u0016J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\tH\u0016R\u001c\u0010\n\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R(\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0018\u00010\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001b¨\u0006!"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/MapListConfig;", "Landroid/os/Parcelable;", "()V", "p0", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "floor", "", "findex", "", "def", "list", "", "(Ljava/lang/String;ILjava/lang/String;Ljava/util/Map;)V", "getDef", "()Ljava/lang/String;", "setDef", "(Ljava/lang/String;)V", "getFindex", "()I", "setFindex", "(I)V", "getFloor", "setFloor", "getList", "()Ljava/util/Map;", "setList", "(Ljava/util/Map;)V", "describeContents", "writeToParcel", "", "p1", "CREATOR", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapListConfig implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String def;
    private int findex;
    private String floor;
    private Map<String, String> list;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MapListConfig(String str, int i, String str2, Map<String, String> map) {
        this.floor = str;
        this.findex = i;
        this.def = str2;
        this.list = map;
    }

    public final String getDef() {
        return this.def;
    }

    public final int getFindex() {
        return this.findex;
    }

    public final String getFloor() {
        return this.floor;
    }

    public final Map<String, String> getList() {
        return this.list;
    }

    public final void setDef(String str) {
        this.def = str;
    }

    public final void setFindex(int i) {
        this.findex = i;
    }

    public final void setFloor(String str) {
        this.floor = str;
    }

    public final void setList(Map<String, String> map) {
        this.list = map;
    }

    public MapListConfig() {
        this(null, 0, null, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MapListConfig(Parcel p0) {
        this();
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        this.floor = p0.readString();
        this.findex = p0.readInt();
        this.def = p0.readString();
        if (this.list == null) {
            this.list = new LinkedHashMap();
        }
        p0.readMap(this.list, StringCompanionObject.INSTANCE.getClass().getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel p0, int p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        p0.writeString(this.floor);
        p0.writeInt(this.findex);
        p0.writeString(this.def);
        p0.writeMap(this.list);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MapListConfig.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/MapListConfig$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/aidl/serialize/MapListConfig;", "()V", "createFromParcel", "p0", "Landroid/os/Parcel;", "newArray", "", "", "(I)[Lcom/pudutech/mirsdk/aidl/serialize/MapListConfig;", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.aidl.serialize.MapListConfig$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes5.dex */
    public static final class Companion implements Parcelable.Creator<MapListConfig> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapListConfig createFromParcel(Parcel p0) {
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            return new MapListConfig(p0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapListConfig[] newArray(int p0) {
            return new MapListConfig[p0];
        }
    }
}
