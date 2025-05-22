package org.ros.android.android_tutorial_pubsub;

import android.os.SystemClock;
import android.util.Log;
import org.ros.android.android_tutorial_pubsub.serialize.ChargeState;
import org.ros.android.android_tutorial_pubsub.serialize.WheelError;

import java.util.ArrayList;
import java.util.List;

public final class CANParser {
    private final String TAG = "CANparser";
    private final CANBus canBus;
    private static final byte MAX_VALUE = -1;

    public CANParser(CANBus canBus) {
        this.canBus = canBus;
    }

    public void parserManager(int index, byte[] bytes) {
        switch(index) {
            case 1:
                parseEncoder(bytes);
                break;
            case 9:
                parseBatteryPercent(bytes);
                break;
            case 13:
                parseCharging(bytes);
                break;
            case 16:
                parseIMU(bytes);
                break;
            case 19:
                // TODO : Hardware version
                break;
            case 66:
            case 67:
            case 71:
                parseWheelError(bytes, index);
                break;
            case 73:
                parseAcceleration(bytes);
                break;
            case 114:
                parseIRDLED(bytes);
                break;
            case 115:
                parseBatteryCharge(bytes);
                break;
            case -30:
                parseBumperSwitch(bytes);
                break;
            case -77:
                Log.d(this.TAG, "Index " + index + " Battery fault not implemented");
                break;
            case -81:
                Log.d(this.TAG, "Index " + index + " Magnetic sensor not implemented");
                break;
            case -82:
                Log.d(this.TAG, "Index " + index + " Parse security not implemented");
                break;
            case -85:
                Log.d(this.TAG, "Index " + index + " Geomagnetic anti drop (right) not implemented");
                break;
            case -86:
                Log.d(this.TAG, "Index " + index + " Geomagnetic anti drop (left) not implemented");
                break;
            case -87:
                parseMotorLock(bytes);
                break;
            case -93:
                Log.d(this.TAG, "Index " + index + " Charge pile not implemented");
                break;
            case -104:
                parseAntiCollisionSwitch(bytes);
                break;
            case -122:
                parseSlamCorePower(bytes);
                break;
            case -124:
                parseDisinfectionPower(bytes);
                break;
            case -126:
                Log.d(this.TAG, "Index " + index + " Battery health not implemented");
                break;
            case -127:
                parseBatterySOH(bytes);
                break;
            default:
                Log.d(this.TAG, "Unable to parse data with id " + index);
                break;
        }
    }

    private void parseEncoder(byte[] bytes) {
        short encoderLeft = (short) ((((bytes[1] & MAX_VALUE) & 255) << 8) | (bytes[2] & MAX_VALUE));
        short encoderRight = (short) ((bytes[4] & MAX_VALUE) | (((bytes[3] & MAX_VALUE) & 255) << 8));
        canBus.getEncoder().update(encoderLeft, encoderRight);
        canBus.setReceivedEncoder(true);
    }

    private void parseBatteryPercent(byte[] bytes) {
        // Set last power percent
        int percent = bytes[1] & MAX_VALUE;
        canBus.setLastPowerPercent(percent);

        Log.d(this.TAG, "Battery percent at " + percent);
    }

    private void parseCharging(byte[] bytes) {
        int charging = (bytes[1] & MAX_VALUE);
        if (charging == 1) {
            canBus.setLastChargeState(ChargeState.Charging);
        } else {
            canBus.setLastChargeState(ChargeState.Idle);
        }
        Log.d(this.TAG, "Battery charging: " + charging);
    }

    private void parseIMU(byte[] bytes) {
        short x = (short) ((((bytes[1] & MAX_VALUE) & 255) << 8) | (bytes[2] & MAX_VALUE));
        short y = (short) ((((bytes[3] & MAX_VALUE) & 255) << 8) | (bytes[4] & MAX_VALUE));
        short z = (short) ((bytes[6] & MAX_VALUE) | (((bytes[5] & MAX_VALUE) & 255) << 8));
        canBus.getGyroscope().update(x, y, z);
        canBus.setReceivedIMU(true);
    }

    private void parseWheelError(byte[] bytes, int index) {
        // Fetch wheel errors
        List<WheelError> errorsLeft;
        List<WheelError> errorsRight;
        if (index == 66) {
            // This is the old motor protocol
            errorsLeft = WheelError.getErrors1(bytes[1] & MAX_VALUE);
            errorsRight = WheelError.getErrors1(bytes[2] & MAX_VALUE);
        } else if (index == 67) {
            errorsLeft = WheelError.getErrors2(bytes[1] & MAX_VALUE, bytes[2] & MAX_VALUE);
            errorsRight = WheelError.getErrors2(bytes[3] & MAX_VALUE, bytes[4] & MAX_VALUE);
        } else if (index == 71) {
            errorsLeft = WheelError.getErrors3(bytes[1] & MAX_VALUE, bytes[2] & MAX_VALUE);
            errorsRight = WheelError.getErrors3(bytes[3] & MAX_VALUE, bytes[4] & MAX_VALUE);
        } else {
            Log.d(this.TAG, "Invalid index for the parseWheelError command");
            return;
        }

        // Fetch current wheel errors (left)
        List<WheelError> lastErrorsLeft = canBus.getWheelError(true);
        if (!lastErrorsLeft.isEmpty()) {
            errorsLeft.addAll(lastErrorsLeft);
        }

        // Fetch current wheel errors (right)
        List<WheelError> lastErrorsRight = canBus.getWheelError(true);
        if (!lastErrorsRight.isEmpty()) {
            errorsRight.addAll(lastErrorsRight);
        }

        canBus.notifyWheelError(errorsLeft, errorsRight);
    }

    private void parseAcceleration(byte[] bytes) {
        int id = bytes[0];
        byte accelerationType = bytes[1];
        double acceleration = (double) (((bytes[2] & MAX_VALUE) << 24) | ((bytes[3] & MAX_VALUE) << 16)
                | ((bytes[4] & MAX_VALUE) << 8) | (bytes[5] & MAX_VALUE)) / 10000;

        String typeString = "not recognised";
        switch(accelerationType) {
            case (byte) 0:
                typeString = "Unknown";
                break;
            case (byte) 1:
                typeString = "Acceleration";
                break;
            case (byte) 2:
                typeString = "Deceleration";
                break;
            case (byte) 3:
                typeString = "EmergencyStopDeceleration";
                break;
        }

        Log.d(this.TAG, "id: " + id + ", accel type: " + typeString + ", data: " + acceleration);
    }

    private void parseIRDLED(byte[] bytes) {
        Log.d(this.TAG, "isCameraIRDLEDLightOn: " + ((bytes[1] & MAX_VALUE) == 1));
    }

    private void parseBatteryCharge(byte[] bytes) {
        // Set last power percent
        int percent = bytes[3] & MAX_VALUE;
        canBus.setLastPowerPercent(percent);

        // Set last charge state
        ChargeState currentState = ChargeState.fromValue(bytes[1]);
        canBus.setLastChargeState(currentState);

        Log.d(this.TAG, "Battery percent at " + percent + ", current state: " + currentState);
    }

    private void parseMotorLock(byte[] bytes) {
        int lockResult = (bytes[1] & 255);
        if (lockResult == 0 || lockResult == 1) {
            if (lockResult == 0) {
                Log.d(this.TAG, "lockStatus unlock success");
            } else {
                Log.d(this.TAG, "lockStatus lock success");
            }
        } else {
            Log.d(this.TAG, "lockStatus invalid");
        }
    }

    private void parseBumperSwitch(byte[] bytes) {
        int switchResult = (bytes[1] & 255);
        Log.d(this.TAG, "parseBumperSwitch bumperSwitchResult = " + switchResult);
    }

    private void parseAntiCollisionSwitch(byte[] bytes) {
        Log.d(this.TAG, "onDisinfectionPower: " + ((bytes[1] & MAX_VALUE) == 1));
    }

    private void parseSlamCorePower(byte[] bytes) {
        Log.d(this.TAG, "onSlamCorePower: " + ((bytes[1] & MAX_VALUE) == 1));
    }

    private void parseDisinfectionPower(byte[] bytes) {
        Log.d(this.TAG, "onDisinfectionPower: " + ((bytes[1] & MAX_VALUE) == 1));
    }

    private void parseBatterySOH(byte[] bytes) {
        Log.d(this.TAG, "onBatterySOH: " + ((bytes[3] & MAX_VALUE) == 1));
    }
}
