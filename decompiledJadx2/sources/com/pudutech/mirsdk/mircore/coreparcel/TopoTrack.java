package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TopoTrack.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u001e\u001a\u00020\u0019H\u0016J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0019H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\t\"\u0004\b\u0014\u0010\u000bR\u001c\u0010\u0015\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000f\"\u0004\b\u0017\u0010\u0011R\u001a\u0010\u0018\u001a\u00020\u0019X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006$"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/TopoTrack;", "Landroid/os/Parcelable;", "()V", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "dual_width", "", "getDual_width", "()D", "setDual_width", "(D)V", "end_pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getEnd_pose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setEnd_pose", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "path_width", "getPath_width", "setPath_width", "start_pose", "getStart_pose", "setStart_pose", "topo_id", "", "getTopo_id", "()I", "setTopo_id", "(I)V", "describeContents", "writeToParcel", "", "dest", "flags", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class TopoTrack implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private double dual_width;
    private Vector3d end_pose;
    private double path_width;
    private Vector3d start_pose;
    private int topo_id;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final int getTopo_id() {
        return this.topo_id;
    }

    public final void setTopo_id(int i) {
        this.topo_id = i;
    }

    public final Vector3d getStart_pose() {
        return this.start_pose;
    }

    public final void setStart_pose(Vector3d vector3d) {
        this.start_pose = vector3d;
    }

    public final Vector3d getEnd_pose() {
        return this.end_pose;
    }

    public final void setEnd_pose(Vector3d vector3d) {
        this.end_pose = vector3d;
    }

    public final double getPath_width() {
        return this.path_width;
    }

    public final void setPath_width(double d) {
        this.path_width = d;
    }

    public final double getDual_width() {
        return this.dual_width;
    }

    public final void setDual_width(double d) {
        this.dual_width = d;
    }

    public TopoTrack() {
        this.topo_id = 0;
        this.start_pose = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.end_pose = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.path_width = 0.0d;
        this.dual_width = 0.0d;
    }

    public TopoTrack(Parcel source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.topo_id = source.readInt();
        Parcelable readParcelable = source.readParcelable(Vector3d.class.getClassLoader());
        if (readParcelable == null) {
            Intrinsics.throwNpe();
        }
        this.start_pose = (Vector3d) readParcelable;
        Parcelable readParcelable2 = source.readParcelable(Vector3d.class.getClassLoader());
        if (readParcelable2 == null) {
            Intrinsics.throwNpe();
        }
        this.end_pose = (Vector3d) readParcelable2;
        this.path_width = source.readDouble();
        this.dual_width = source.readDouble();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkParameterIsNotNull(dest, "dest");
        dest.writeInt(this.topo_id);
        dest.writeParcelable(this.start_pose, flags);
        dest.writeParcelable(this.end_pose, flags);
        dest.writeDouble(this.path_width);
        dest.writeDouble(this.dual_width);
    }

    /* compiled from: TopoTrack.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/TopoTrack$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/TopoTrack;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/TopoTrack;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.TopoTrack$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes4.dex */
    public static final class Companion implements Parcelable.Creator<TopoTrack> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TopoTrack createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new TopoTrack(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TopoTrack[] newArray(int size) {
            return new TopoTrack[size];
        }
    }
}
