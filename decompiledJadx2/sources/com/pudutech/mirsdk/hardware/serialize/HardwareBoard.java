package com.pudutech.mirsdk.hardware.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareBoard.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0001\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u001eB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\n\u001a\u00020\u0004H\u0016J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0004H\u0016R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001d¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/HardwareBoard;", "", "Landroid/os/Parcelable;", "id", "", "(Ljava/lang/String;II)V", "getId", "()I", "setId", "(I)V", "describeContents", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "Main", "MarkerMCU", "Extern3399", "Tail", "Tray1", "Tray2", "Tray3", "Tray4", "AtomizationUV", "RotationUV", "PowerManagement", "PeaunatHead", "CAN2USB", "Unknown", "CREATOR", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes2.dex */
public enum HardwareBoard implements Parcelable {
    Main(2),
    MarkerMCU(3),
    Extern3399(4),
    Tail(6),
    Tray1(7),
    Tray2(8),
    Tray3(9),
    Tray4(10),
    AtomizationUV(11),
    RotationUV(12),
    PowerManagement(32),
    PeaunatHead(33),
    CAN2USB(238),
    Unknown(-1);


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private int id;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    HardwareBoard(int i) {
        this.id = i;
    }

    public final int getId() {
        return this.id;
    }

    public final void setId(int i) {
        this.id = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(name());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareBoard.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u000e\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tJ\u001d\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u000b2\u0006\u0010\f\u001a\u00020\tH\u0016¢\u0006\u0002\u0010\r¨\u0006\u000e"}, m3961d2 = {"Lcom/pudutech/mirsdk/hardware/serialize/HardwareBoard$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareBoard;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "fromId", "id", "", "newArray", "", "size", "(I)[Lcom/pudutech/mirsdk/hardware/serialize/HardwareBoard;", "MirHardwareAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.serialize.HardwareBoard$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes2.dex */
    public static final class Companion implements Parcelable.Creator<HardwareBoard> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final HardwareBoard fromId(int id) {
            HardwareBoard hardwareBoard;
            HardwareBoard[] values = HardwareBoard.values();
            int length = values.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    hardwareBoard = null;
                    break;
                }
                hardwareBoard = values[i];
                if (hardwareBoard.getId() == id) {
                    break;
                }
                i++;
            }
            if (hardwareBoard != null) {
                return hardwareBoard;
            }
            HardwareBoard hardwareBoard2 = HardwareBoard.Unknown;
            hardwareBoard2.setId(id);
            return hardwareBoard2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HardwareBoard createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            String readString = parcel.readString();
            Intrinsics.checkExpressionValueIsNotNull(readString, "parcel.readString()");
            return HardwareBoard.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HardwareBoard[] newArray(int size) {
            return new HardwareBoard[size];
        }
    }
}
