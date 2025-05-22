package com.pudutech.robot.peripherals.disinfection;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: UvcLampDeviceError.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\b\u0086\u0001\u0018\u0000 \u001b2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u001bB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001a¨\u0006\u001c"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;", "", "Landroid/os/Parcelable;", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "DRIVER_BOARD_NO_RESPONSE", "DRIVER_BOARD_COMM_ERROR", "DRIVER_CURRENT_ERROR_1", "DRIVER_CURRENT_ERROR_2", "DRIVER_CURRENT_ERROR_3", "DRIVER_CURRENT_ERROR_4", "DRIVER_VOLTAGE_ERROR_1", "DRIVER_VOLTAGE_ERROR_2", "DRIVER_VOLTAGE_ERROR_3", "DRIVER_VOLTAGE_ERROR_4", "LOWER_VOLTAGE", "RAYS_SLOW_START_UP_ERROR", "CREATOR", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum UvcLampDeviceError implements Parcelable {
    DRIVER_BOARD_NO_RESPONSE("DRIVER_BOARD_NO_RESPONSE"),
    DRIVER_BOARD_COMM_ERROR("DRIVER_BOARD_COMM_ERROR"),
    DRIVER_CURRENT_ERROR_1("DRIVER_CURRENT_ERROR_1"),
    DRIVER_CURRENT_ERROR_2("DRIVER_CURRENT_ERROR_2"),
    DRIVER_CURRENT_ERROR_3("DRIVER_CURRENT_ERROR_3"),
    DRIVER_CURRENT_ERROR_4("DRIVER_CURRENT_ERROR_4"),
    DRIVER_VOLTAGE_ERROR_1("DRIVER_VOLTAGE_ERROR_1"),
    DRIVER_VOLTAGE_ERROR_2("DRIVER_VOLTAGE_ERROR_2"),
    DRIVER_VOLTAGE_ERROR_3("DRIVER_VOLTAGE_ERROR_3"),
    DRIVER_VOLTAGE_ERROR_4("DRIVER_VOLTAGE_ERROR_4"),
    LOWER_VOLTAGE("LOWER_VOLTAGE"),
    RAYS_SLOW_START_UP_ERROR("RAYS_SLOW_START_UP_ERROR");


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    UvcLampDeviceError(String str) {
        this.value = str;
    }

    public final String getValue() {
        return this.value;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkParameterIsNotNull(parcel, "parcel");
        parcel.writeString(name());
    }

    /* compiled from: UvcLampDeviceError.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/robot/peripherals/disinfection/UvcLampDeviceError;", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.robot.peripherals.disinfection.UvcLampDeviceError$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<UvcLampDeviceError> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UvcLampDeviceError createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            String readString = parcel.readString();
            if (readString == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(readString, "parcel.readString()!!");
            return UvcLampDeviceError.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public UvcLampDeviceError[] newArray(int size) {
            return new UvcLampDeviceError[size];
        }
    }
}
