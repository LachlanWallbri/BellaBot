package com.pudutech.mirsdk.mircore.coreparcel;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdk.hardware.serialize.SchedulingMode;
import com.pudutech.mirsdk.hardware.serialize.Vector3d;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: ScheduleFillInState.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0007\b\u0016¢\u0006\u0002\u0010\u0002B\u000f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u0019H\u0016R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001c\u0010\f\u001a\u0004\u0018\u00010\rX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0012\u001a\u00020\u0013X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/ScheduleFillInState;", "Landroid/os/Parcelable;", "()V", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "changed", "", "getChanged", "()Z", "setChanged", "(Z)V", "final_goal", "Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "getFinal_goal", "()Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;", "setFinal_goal", "(Lcom/pudutech/mirsdk/hardware/serialize/Vector3d;)V", "scheduling_mode", "Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode;", "getScheduling_mode", "()Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode;", "setScheduling_mode", "(Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode;)V", "describeContents", "", "writeToParcel", "", "dest", "flags", "CREATOR", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class ScheduleFillInState implements Parcelable {

    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private boolean changed;
    private Vector3d final_goal;
    private SchedulingMode scheduling_mode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public final boolean getChanged() {
        return this.changed;
    }

    public final void setChanged(boolean z) {
        this.changed = z;
    }

    public final Vector3d getFinal_goal() {
        return this.final_goal;
    }

    public final void setFinal_goal(Vector3d vector3d) {
        this.final_goal = vector3d;
    }

    public final SchedulingMode getScheduling_mode() {
        return this.scheduling_mode;
    }

    public final void setScheduling_mode(SchedulingMode schedulingMode) {
        Intrinsics.checkParameterIsNotNull(schedulingMode, "<set-?>");
        this.scheduling_mode = schedulingMode;
    }

    public ScheduleFillInState() {
        this.scheduling_mode = SchedulingMode.Free;
        this.changed = false;
        this.scheduling_mode = SchedulingMode.Free;
    }

    public ScheduleFillInState(Parcel source) {
        Intrinsics.checkParameterIsNotNull(source, "source");
        this.scheduling_mode = SchedulingMode.Free;
        this.changed = source.readByte() != ((byte) 0);
        this.final_goal = (Vector3d) source.readParcelable(Vector3d.class.getClassLoader());
        Parcelable readParcelable = source.readParcelable(SchedulingMode.class.getClassLoader());
        if (readParcelable == null) {
            Intrinsics.throwNpe();
        }
        this.scheduling_mode = (SchedulingMode) readParcelable;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        Intrinsics.checkParameterIsNotNull(dest, "dest");
        dest.writeByte(this.changed ? (byte) 1 : (byte) 0);
        dest.writeParcelable(this.final_goal, flags);
        dest.writeParcelable(this.scheduling_mode, flags);
    }

    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
     */
    /* compiled from: ScheduleFillInState.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/mircore/coreparcel/ScheduleFillInState$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/mircore/coreparcel/ScheduleFillInState;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/mircore/coreparcel/ScheduleFillInState;", "MirCoreAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mircore.coreparcel.ScheduleFillInState$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<ScheduleFillInState> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScheduleFillInState[] newArray(int size) {
            return new ScheduleFillInState[size];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ScheduleFillInState createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            return new ScheduleFillInState(source);
        }
    }
}
