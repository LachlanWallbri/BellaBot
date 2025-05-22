package org.ros.android.android_tutorial_pubsub.serialize;

public enum ChargeState {
    Idle((byte) 0),
    Charging((byte) 1),
    ChargeFull((byte) 2),
    ChargeErrorContact((byte) 3),
    ChargeErrorElectric((byte) 4),
    ErrorBatteryPackComm((byte) 5),
    ErrorOverVolt((byte) 6),
    ErrorOverElectric((byte) 7),
    ErrorOverTemperature((byte) 8),
    ErrorLowTemperature((byte) 9),
    Retain((byte) 10),
    ChargingUsePile((byte) 11),
    ChargeFullUsePile((byte) 12),
    StopChargeUsePile((byte) 13),
    ErrorOverTime((byte) -1);

    private final byte value;

    ChargeState(byte b) {
        this.value = b;
    }

    public final byte getValue() {
        return this.value;
    }

    public static ChargeState fromValue(byte value) {
        for (ChargeState chargeState : ChargeState.values()) {
            if (value == chargeState.getValue()) {
                return chargeState;
            }
        }
        return ChargeState.Idle;
    }
}