package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: Segment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 #2\u00020\u0001:\u0001#B\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u001e\u001a\u00020\u0016H\u0016J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00042\u0006\u0010\"\u001a\u00020\u0016H\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u000f\"\u0004\b\u0014\u0010\u0011R\u001a\u0010\u0015\u001a\u00020\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\t\"\u0004\b\u001d\u0010\u000b¨\u0006$"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/Segment;", "Landroid/os/Parcelable;", "()V", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "end_pose", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getEnd_pose", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setEnd_pose", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "max_speed", "", "getMax_speed", "()D", "setMax_speed", "(D)V", "path_width", "getPath_width", "setPath_width", "seg_id", "", "getSeg_id", "()I", "setSeg_id", "(I)V", "start_pose", "getStart_pose", "setStart_pose", "describeContents", "writeToParcel", "", "dest", "flags", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class Segment implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Vector3d end_pose;
    private double max_speed;
    private double path_width;
    private int seg_id;
    private Vector3d start_pose;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final int getSeg_id() {
        return this.seg_id;
    }

    public final void setSeg_id(int i) {
        this.seg_id = i;
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

    public final double getMax_speed() {
        return this.max_speed;
    }

    public final void setMax_speed(double d) {
        this.max_speed = d;
    }

    public Segment() {
        this.seg_id = 0;
        this.start_pose = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.end_pose = new Vector3d(0.0d, 0.0d, 0.0d, 7, null);
        this.path_width = 0.0d;
        this.max_speed = 0.0d;
    }

    public Segment(Parcel source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.seg_id = source.readInt();
        this.start_pose = (Vector3d) source.readParcelable(Vector3d.class.getClassLoader());
        this.end_pose = (Vector3d) source.readParcelable(Vector3d.class.getClassLoader());
        this.path_width = source.readDouble();
        this.max_speed = source.readDouble();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkParameterIsNotNull(dest, "dest");
        dest.writeInt(this.seg_id);
        dest.writeParcelable(this.start_pose, flags);
        dest.writeParcelable(this.end_pose, flags);
        dest.writeDouble(this.path_width);
        dest.writeDouble(this.max_speed);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: Segment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/Segment$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/Segment;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/Segment;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.Segment$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<Segment> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Segment createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new Segment(source);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Segment[] newArray(int size) {
            return new Segment[size];
        }
    }
}
