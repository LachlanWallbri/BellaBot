package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Destination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 82\u00020\u0001:\u00018B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u00103\u001a\u00020\rH\u0016J\u001a\u00104\u001a\u0002052\b\u00106\u001a\u0004\u0018\u00010\u00042\u0006\u00107\u001a\u00020\rH\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\u001cX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010\u0018\"\u0004\b#\u0010\u001aR\u001a\u0010$\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0018\"\u0004\b&\u0010\u001aR\u001a\u0010'\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b(\u0010\u0018\"\u0004\b)\u0010\u001aR\u001a\u0010*\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u000f\"\u0004\b,\u0010\u0011R\u001a\u0010-\u001a\u00020.X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u00100\"\u0004\b1\u00102¨\u00069"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "Landroid/os/Parcelable;", "()V", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", MapElement.Key.DIR, "", "getDir", "()D", "setDir", "(D)V", MapElement.Key.DIR_MODE, "", "getDirMode", "()I", "setDirMode", "(I)V", "doubleDir", "getDoubleDir", "setDoubleDir", MapElement.Key.GROUP, "", "getGroup", "()Ljava/lang/String;", "setGroup", "(Ljava/lang/String;)V", "high_precision", "", "getHigh_precision", "()Z", "setHigh_precision", "(Z)V", "id", "getId", "setId", "mode", "getMode", "setMode", "name", "getName", "setName", "sort_weight", "getSort_weight", "setSort_weight", MapElement.Key.VECTOR, "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getVector", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setVector", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "describeContents", "writeToParcel", "", "dest", "flags", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class Destination implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private double dir;
    private int dirMode;
    private int doubleDir;
    private String group;
    private boolean high_precision;
    private String id;
    private String mode;
    private String name;
    private int sort_weight;
    private Vector3d vector;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final Vector3d getVector() {
        return this.vector;
    }

    public final void setVector(Vector3d vector3d) {
        Intrinsics.checkParameterIsNotNull(vector3d, "<set-?>");
        this.vector = vector3d;
    }

    public final double getDir() {
        return this.dir;
    }

    public final void setDir(double d) {
        this.dir = d;
    }

    public final int getDoubleDir() {
        return this.doubleDir;
    }

    public final void setDoubleDir(int i) {
        this.doubleDir = i;
    }

    public final int getDirMode() {
        return this.dirMode;
    }

    public final void setDirMode(int i) {
        this.dirMode = i;
    }

    public final String getName() {
        return this.name;
    }

    public final void setName(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.name = str;
    }

    public final String getMode() {
        return this.mode;
    }

    public final void setMode(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.mode = str;
    }

    public final String getId() {
        return this.id;
    }

    public final void setId(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.id = str;
    }

    public final String getGroup() {
        return this.group;
    }

    public final void setGroup(String str) {
        Intrinsics.checkParameterIsNotNull(str, "<set-?>");
        this.group = str;
    }

    public final boolean getHigh_precision() {
        return this.high_precision;
    }

    public final void setHigh_precision(boolean z) {
        this.high_precision = z;
    }

    public final int getSort_weight() {
        return this.sort_weight;
    }

    public final void setSort_weight(int i) {
        this.sort_weight = i;
    }

    public Destination() {
        this.vector = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.name = "";
        this.mode = "";
        this.id = "";
        this.group = "";
        this.high_precision = true;
        this.vector = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.dir = 0.0d;
        this.doubleDir = 0;
        this.dirMode = 0;
        this.name = "";
        this.mode = "";
        this.id = "";
        this.group = "";
        this.high_precision = true;
        this.sort_weight = 0;
    }

    public Destination(Parcel source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.vector = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.name = "";
        this.mode = "";
        this.id = "";
        this.group = "";
        this.high_precision = true;
        Parcelable readParcelable = source.readParcelable(Vector3d.class.getClassLoader());
        if (readParcelable == null) {
            Intrinsics.throwNpe();
        }
        this.vector = (Vector3d) readParcelable;
        this.dir = source.readDouble();
        this.doubleDir = source.readInt();
        this.dirMode = source.readInt();
        String readString = source.readString();
        if (readString == null) {
            Intrinsics.throwNpe();
        }
        this.name = readString;
        String readString2 = source.readString();
        if (readString2 == null) {
            Intrinsics.throwNpe();
        }
        this.mode = readString2;
        String readString3 = source.readString();
        if (readString3 == null) {
            Intrinsics.throwNpe();
        }
        this.id = readString3;
        String readString4 = source.readString();
        if (readString4 == null) {
            Intrinsics.throwNpe();
        }
        this.group = readString4;
        this.high_precision = source.readByte() != ((byte) 0);
        this.sort_weight = source.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (dest != null) {
            dest.writeParcelable(this.vector, flags);
        }
        if (dest != null) {
            dest.writeDouble(this.dir);
        }
        if (dest != null) {
            dest.writeInt(this.doubleDir);
        }
        if (dest != null) {
            dest.writeInt(this.dirMode);
        }
        if (dest != null) {
            dest.writeString(this.name);
        }
        if (dest != null) {
            dest.writeString(this.mode);
        }
        if (dest != null) {
            dest.writeString(this.id);
        }
        if (dest != null) {
            dest.writeString(this.group);
        }
        if (dest != null) {
            dest.writeByte(this.high_precision ? (byte) 1 : (byte) 0);
        }
        if (dest != null) {
            dest.writeInt(this.sort_weight);
        }
    }

    /* compiled from: Destination.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/Destination$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/Destination;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.Destination$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes4.dex */
    public static final class Companion implements Parcelable.Creator<Destination> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Destination[] newArray(int size) {
            return new Destination[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Destination createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new Destination(source);
        }
    }
}
