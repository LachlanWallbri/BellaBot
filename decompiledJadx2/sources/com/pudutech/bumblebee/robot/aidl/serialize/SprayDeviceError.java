package com.pudutech.bumblebee.robot.aidl.serialize;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: SprayDeviceError.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0086\u0001\u0018\u0000 \u001a2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u001aB\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016j\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019¨\u0006\u001b"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/aidl/serialize/SprayDeviceError;", "", "Landroid/os/Parcelable;", "(Ljava/lang/String;I)V", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "WATER_TANK_SENSOR_ERROR", "SPRAY_CHAMBER_SENSOR_ERROR", "PUMP_ERROR", "SOLENOID_VALVE_ERROR", "BLOWER_CURRENT_ERROR", "SPRAY_DRIVE_CURRENT_ERROR_1", "SPRAY_DRIVE_CURRENT_ERROR_2", "SPRAY_DRIVE_CURRENT_ERROR_3", "SPRAY_DRIVE_CURRENT_ERROR_4", "SPRAY_DRIVE_VOLTAGE_ERROR_1", "SPRAY_DRIVE_VOLTAGE_ERROR_2", "SPRAY_DRIVE_VOLTAGE_ERROR_3", "SPRAY_DRIVE_VOLTAGE_ERROR_4", "LOWER_VOLTAGE", "LOWER_LIQUID", "CREATOR", "RobotAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public enum SprayDeviceError implements Parcelable {
    WATER_TANK_SENSOR_ERROR,
    SPRAY_CHAMBER_SENSOR_ERROR,
    PUMP_ERROR,
    SOLENOID_VALVE_ERROR,
    BLOWER_CURRENT_ERROR,
    SPRAY_DRIVE_CURRENT_ERROR_1,
    SPRAY_DRIVE_CURRENT_ERROR_2,
    SPRAY_DRIVE_CURRENT_ERROR_3,
    SPRAY_DRIVE_CURRENT_ERROR_4,
    SPRAY_DRIVE_VOLTAGE_ERROR_1,
    SPRAY_DRIVE_VOLTAGE_ERROR_2,
    SPRAY_DRIVE_VOLTAGE_ERROR_3,
    SPRAY_DRIVE_VOLTAGE_ERROR_4,
    LOWER_VOLTAGE,
    LOWER_LIQUID;


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(name());
    }

    /* JADX WARN: Classes with same name are omitted:
      classes2.dex
     */
    /* compiled from: SprayDeviceError.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/bumblebee/robot/aidl/serialize/SprayDeviceError$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/bumblebee/robot/aidl/serialize/SprayDeviceError;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/bumblebee/robot/aidl/serialize/SprayDeviceError;", "RobotAIDL_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.bumblebee.robot.aidl.serialize.SprayDeviceError$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes.dex */
    public static final class Companion implements Parcelable.Creator<SprayDeviceError> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SprayDeviceError createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            String readString = parcel.readString();
            if (readString == null) {
                Intrinsics.throwNpe();
            }
            return SprayDeviceError.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SprayDeviceError[] newArray(int size) {
            return new SprayDeviceError[size];
        }
    }
}
