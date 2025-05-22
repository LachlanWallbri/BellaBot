package com.pudutech.mirsdk.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MapPackageConfig.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B)\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\u000e\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t¢\u0006\u0002\u0010\u000bJ\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0017H\u0016R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\r\"\u0004\b\u0011\u0010\u000fR\"\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/MapPackageConfig;", "Landroid/os/Parcelable;", "p0", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "def_floor", "", "def_map", "list", "", "Lcom/pudutech/mirsdk/aidl/serialize/MapListConfig;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;)V", "getDef_floor", "()Ljava/lang/String;", "setDef_floor", "(Ljava/lang/String;)V", "getDef_map", "setDef_map", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "describeContents", "", "writeToParcel", "", "p1", "CREATOR", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class MapPackageConfig implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private String def_floor;
    private String def_map;
    private List<MapListConfig> list;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MapPackageConfig(String str, String str2, List<MapListConfig> list) {
        this.def_floor = str;
        this.def_map = str2;
        this.list = list;
    }

    public final String getDef_floor() {
        return this.def_floor;
    }

    public final String getDef_map() {
        return this.def_map;
    }

    public final List<MapListConfig> getList() {
        return this.list;
    }

    public final void setDef_floor(String str) {
        this.def_floor = str;
    }

    public final void setDef_map(String str) {
        this.def_map = str;
    }

    public final void setList(List<MapListConfig> list) {
        this.list = list;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MapPackageConfig(Parcel p0) {
        this(null, null, null);
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        this.def_floor = p0.readString();
        this.def_map = p0.readString();
        if (this.list == null) {
            this.list = new ArrayList();
        }
        p0.readTypedList(this.list, MapListConfig.INSTANCE);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel p0, int p1) {
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        p0.writeString(this.def_floor);
        p0.writeString(this.def_map);
        p0.writeTypedList(this.list);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: MapPackageConfig.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\u0005\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\n¨\u0006\u000b"}, m3961d2 = {"Lcom/pudutech/mirsdk/aidl/serialize/MapPackageConfig$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/aidl/serialize/MapPackageConfig;", "()V", "createFromParcel", "p0", "Landroid/os/Parcel;", "newArray", "", "", "(I)[Lcom/pudutech/mirsdk/aidl/serialize/MapPackageConfig;", "MirFunctionAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.aidl.serialize.MapPackageConfig$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes5.dex */
    public static final class Companion implements Parcelable.Creator<MapPackageConfig> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapPackageConfig createFromParcel(Parcel p0) {
            Intrinsics.checkParameterIsNotNull(p0, "p0");
            return new MapPackageConfig(p0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MapPackageConfig[] newArray(int p0) {
            return new MapPackageConfig[p0];
        }
    }
}
