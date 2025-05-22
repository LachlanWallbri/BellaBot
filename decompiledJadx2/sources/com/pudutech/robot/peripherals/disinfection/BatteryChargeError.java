package com.pudutech.robot.peripherals.disinfection;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.mozilla.javascript.ES6Iterator;

/* compiled from: BatteryChargeError.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0086\u0001\u0018\u0000 \u001e2\b\u0012\u0004\u0012\u00020\u00000\u00012\u00020\u0002:\u0001\u001eB\u000f\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\u0018\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001d¨\u0006\u001f"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/BatteryChargeError;", "", "Landroid/os/Parcelable;", ES6Iterator.VALUE_PROPERTY, "", "(Ljava/lang/String;ILjava/lang/String;)V", "getValue", "()Ljava/lang/String;", "describeContents", "", "writeToParcel", "", "parcel", "Landroid/os/Parcel;", "flags", "BATTERY_COMMUNICATION_FAIL_BIT0", "BATTERY_OVERFLOW_VOLTAGE_BIT1", "BATTERY_CHARGE_OVERFLOW_VOLTAGE_BIT2", "BATTERY_CHARGE_OVERFLOW_ELECTRIC_BIT3", "BATTERY_CHARGE_OVERTIME_BIT4", "BATTERIES_TEMPERATURE_TOO_HIGH_BIT5", "BATTERY_VOLTAGE_MISMATCH_BIT6", "BATTERY_CHARGING_UNDERVOLTAGE_BIT7", "BATTERY_LOW_POWER_BIT8", "BATTERY_CHARGING_LOWTEMPERATURE_FAILURE_BIT9", "BATTERY_DISCHARGE_HIGH_TEMPERATURE_WARNING_BIT10", "BATTERY_DISCHARGE_LOWER_TEMPERATURE_WARNING_BIT11", "BATTERY_POOR_CONTACT_BIT12", "BATTERY_CHARGING_PORT_FAILURE_BIT13", "BATTERY_DISCHARGING_PORT_FAILURE_BIT14", "CREATOR", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public enum BatteryChargeError implements Parcelable {
    BATTERY_COMMUNICATION_FAIL_BIT0("battery_communication_fail_bit0(通讯失败)"),
    BATTERY_OVERFLOW_VOLTAGE_BIT1("battery_overflow_voltage_bit1(电池过压)"),
    BATTERY_CHARGE_OVERFLOW_VOLTAGE_BIT2("battery_charge_overflow_voltage_bit2(充电过压)"),
    BATTERY_CHARGE_OVERFLOW_ELECTRIC_BIT3("battery_charge_overflow_electric_bit3(充电过流)"),
    BATTERY_CHARGE_OVERTIME_BIT4("battery_charge_overtime_bit4(充电超时)"),
    BATTERIES_TEMPERATURE_TOO_HIGH_BIT5("battery_temperature_too_high_bit5(电芯温度过高)"),
    BATTERY_VOLTAGE_MISMATCH_BIT6("battery_voltage_mismatch_bit6(电量电压失配)"),
    BATTERY_CHARGING_UNDERVOLTAGE_BIT7("battery_charging_under_voltage_bit7(充电欠压)"),
    BATTERY_LOW_POWER_BIT8("battery_lower_power_bit8(低电量(并联电池均电量过低))"),
    BATTERY_CHARGING_LOWTEMPERATURE_FAILURE_BIT9("battery_charging_low_temperature_failure_bit9(充电低温故障)"),
    BATTERY_DISCHARGE_HIGH_TEMPERATURE_WARNING_BIT10("battery_discharge_high_temperature_warning_bit10(放电高温预警)"),
    BATTERY_DISCHARGE_LOWER_TEMPERATURE_WARNING_BIT11("battery_discharge_low_temperature_warning_bit11(放电低温预警)"),
    BATTERY_POOR_CONTACT_BIT12("battery_poor_contact_bit12(接触不良(充电界面与送餐界面来回跳动))"),
    BATTERY_CHARGING_PORT_FAILURE_BIT13("battery_charging_port_failure_bit13(存在充电端口故障)"),
    BATTERY_DISCHARGING_PORT_FAILURE_BIT14("battery_discharging_port_failure_bit14(存在放电端口故障)");


    /* renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    BatteryChargeError(String str) {
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

    /* compiled from: BatteryChargeError.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, m3961d2 = {"Lcom/pudutech/robot/peripherals/disinfection/BatteryChargeError$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/pudutech/robot/peripherals/disinfection/BatteryChargeError;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/pudutech/robot/peripherals/disinfection/BatteryChargeError;", "module_robot_peripherals_release"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.robot.peripherals.disinfection.BatteryChargeError$CREATOR, reason: from kotlin metadata */
    /* loaded from: classes6.dex */
    public static final class Companion implements Parcelable.Creator<BatteryChargeError> {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BatteryChargeError createFromParcel(Parcel parcel) {
            Intrinsics.checkParameterIsNotNull(parcel, "parcel");
            String readString = parcel.readString();
            if (readString == null) {
                Intrinsics.throwNpe();
            }
            Intrinsics.checkExpressionValueIsNotNull(readString, "parcel.readString()!!");
            return BatteryChargeError.valueOf(readString);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BatteryChargeError[] newArray(int size) {
            return new BatteryChargeError[size];
        }
    }
}
