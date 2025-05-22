package com.example.jobeeekerapp.serialize;

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

    /**
     * Constructs a ChargeState with the specified value.
     *
     * @param currentState The byte value representing the charge state.
     */
    ChargeState(byte currentState) {
        this.value = currentState;
    }

    /**
     * Gets the byte value representing the charge state.
     *
     * @return The byte value of the charge state.
     */
    public final byte getValue() {
        return this.value;
    }

    /**
     * Returns the ChargeState corresponding to the specified byte value.
     *
     * @param value The byte value representing the charge state.
     * @return The ChargeState corresponding to the given byte value. If no matching state is found, ChargeState#Idle is returned.
     */
    public static ChargeState fromValue(byte value) {
        for (ChargeState chargeState : ChargeState.values()) {
            if (value == chargeState.getValue()) {
                return chargeState;
            }
        }
        return ChargeState.Idle;
    }
}