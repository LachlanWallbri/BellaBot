package com.pudutech.mirsdk.hardware.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import com.pudutech.mirsdk.compat.topo.MapElement;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveError;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: SchedulingMode.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0001\u0018\u0000 \u001c2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u001cB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u0004H\u0016J\u001a\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001b¨\u0006\u001d"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode;", "", "Landroid/os/Parcelable;", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;II)V", "getValue", "()I", "setValue", "(I)V", "describeContents", "writeToParcel", "", "dest", "Landroid/os/Parcel;", "flags", "Free", "Normal", "Arrived", "Avoid", "AvoidNormal", "AvoidWait", MoveError.LEVEL_ERROR, "AvoidLeft", "AvoidRight", "NormalChange", "AvoidChange", "NormalRight", "CREATOR", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public enum SchedulingMode implements Parcelable {
    Free(0),
    Normal(1),
    Arrived(2),
    Avoid(3),
    AvoidNormal(4),
    AvoidWait(5),
    Error(6),
    AvoidLeft(7),
    AvoidRight(8),
    NormalChange(9),
    AvoidChange(10),
    NormalRight(11);


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    SchedulingMode(int i) {
        this.value = i;
    }

    public final int getValue() {
        return this.value;
    }

    public final void setValue(int i) {
        this.value = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel dest, int flags) {
        if (dest != null) {
            dest.writeString(name());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: SchedulingMode.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode;", "()V", "createFromParcel", MapElement.Source.SOURCE, "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/mirsdk/hardware/serialize/SchedulingMode;", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.serialize.SchedulingMode$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion implements Parcelable.Creator<SchedulingMode> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SchedulingMode createFromParcel(Parcel source) {
            Intrinsics.checkParameterIsNotNull(source, "source");
            String readString = source.readString();
            if (readString == null) {
                Intrinsics.throwNpe();
            }
            return SchedulingMode.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SchedulingMode[] newArray(int size) {
            SchedulingMode[] schedulingModeArr = new SchedulingMode[size];
            for (int i = 0; i < size; i++) {
                schedulingModeArr[i] = SchedulingMode.Free;
            }
            return schedulingModeArr;
        }
    }
}
