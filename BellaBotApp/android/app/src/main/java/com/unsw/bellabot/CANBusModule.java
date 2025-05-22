package com.unsw.bellabot;


import androidx.annotation.NonNull;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableMap;


public class CANBusModule extends ReactContextBaseJavaModule {
    private final CANBus cANBus = CANBus.getInstance();

    /**
     * Constructor for the CANBusModule.
     *
     * @param context the React application context.
     */
    CANBusModule(ReactApplicationContext context) {
        super(context);
    }

    /**
     * Returns the name of this module.
     *
     * @return the name of the module.
     */
    @NonNull
    @Override
    public String getName() {
        return "CANBusModule";
    }

    /**
     * Gets the battery percentage from the CANBus.
     *
     * @param promise the promise to resolve with the battery percentage.
     */
    @ReactMethod
    public void getBatteryPercent(Promise promise) {
        try {
            promise.resolve(cANBus.getLastPowerPercent());
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    /**
     * Gets the linear speed from the CANBus encoder.
     *
     * @param promise the promise to resolve with the linear speed.
     */
    @ReactMethod
    public void getLinearSpeed(Promise promise) {
        try {
            promise.resolve(cANBus.getEncoder().getSpeed().getLineSpeed());
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    /**
     * Gets the angular speed from the CANBus encoder.
     *
     * @param promise the promise to resolve with the angular speed.
     */
    @ReactMethod
    public void getAngularSpeed(Promise promise) {
        try {
            promise.resolve(cANBus.getEncoder().getSpeed().getAngularSpeed());
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    /**
     * Gets the instant gyroscope data from the CANBus.
     * Returns a map, with each axis as it's own value.
     *
     * @param promise the promise to resolve with the gyroscope data.
     */
    @ReactMethod
    public void getInstantGyroscope(Promise promise) {
        try {
            Gyroscope.Data last = cANBus.getGyroscope().getLast();
            WritableMap map = Arguments.createMap();

            map.putDouble("x", last.getX());
            map.putDouble("y", last.getY());
            map.putDouble("z", last.getZ());

            promise.resolve(map);
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    /**
     * Gets the accumulative gyroscope data from the CANBus.
     * Returns a map, with each axis as it's own value.
     *
     * @param promise the promise to resolve with the gyroscope data.
     */
    @ReactMethod
    public void getAccumulativeGyroscope(Promise promise) {
        try {
            Gyroscope.Data acc = cANBus.getGyroscope().getAccumulate();
            WritableMap map = Arguments.createMap();

            map.putDouble("x", acc.getX());
            map.putDouble("y", acc.getY());
            map.putDouble("z", acc.getZ());

            promise.resolve(map);
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    /**
     * Gets the left wheel error from the CANBus.
     * Returns a list of the errors.
     * Check `WheelError.java` for details about the
     * possible errors
     * @param promise the promise to resolve with the left wheel error.
     */
    @ReactMethod
    public void getLeftWheelError(Promise promise) {
        try {
            promise.resolve(cANBus.getLeftWheelError());
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    /**
     * Gets the right wheel error from the CANBus.
     * Returns a list of the errors.
     * Check `WheelError.java` for details about the
     * possible errors
     *
     * @param promise the promise to resolve with the right wheel error.
     */
    @ReactMethod
    public void getRightWheelError(Promise promise) {
        try {
            promise.resolve(cANBus.getRightWheelError());
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }


    /**
     * Checks if the CANBus is connected.
     *
     * @param promise the promise to resolve with the connection status.
     */
    @ReactMethod
    public void getIsCanBusConnected(Promise promise) {
        try {
            promise.resolve(cANBus.getIsConnect());
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    /**
     * Gets the last charge state from the CANBus.
     * Function returns the state as a string.
     * Check `ChargeState.java` for details about the
     * possible states
     *
     * @param promise the promise to resolve with the last charge state.
     */
    @ReactMethod
    public void getLastChargeState(Promise promise) {
        try {
            promise.resolve(cANBus.getLastChargeState().name());
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    /**
     * Sets the acceleration data on the CANBus.
     *
     * @param newAcceleration the new acceleration value.
     * @param promise         the promise to resolve with a success message.
     */
    @ReactMethod
    public void setAcceleration(double newAcceleration, Promise promise) {
        try {
            cANBus.setAccelerationData((byte) 1, newAcceleration);
            promise.resolve("Acceleration was set");
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    /**
     * Sets the deceleration data on the CANBus.
     *
     * @param newDeceleration the new deceleration value.
     * @param promise         the promise to resolve with a success message.
     */
    @ReactMethod
    public void setDeceleration(double newDeceleration, Promise promise) {
        try {
            cANBus.setAccelerationData((byte) 2, newDeceleration);
            promise.resolve("Deceleration was set");
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }

    /**
     * Sets the emergency deceleration data on the CANBus.
     * Changing this value is dangerous. Be cautious with this function.
     *
     * @param newDeceleration the new emergency deceleration value.
     * @param promise         the promise to resolve with a success message.
     */
    @ReactMethod
    public void setEmergencyDeceleration(double newDeceleration, Promise promise) {
        try {
            cANBus.setAccelerationData((byte) 3, newDeceleration);
            promise.resolve("Emergency Deceleration was set");
        } catch (Exception e) {
            promise.reject("Error", e);
        }
    }
}
