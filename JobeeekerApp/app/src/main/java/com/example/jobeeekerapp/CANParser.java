package com.example.jobeeekerapp;

import android.util.Log;

import com.example.jobeeekerapp.serialize.ChargeState;
import com.example.jobeeekerapp.serialize.WheelError;

import java.util.List;

public final class CANParser {
    private final String TAG = "CANparser";
    private final CANBus canBus;
    private static final byte MAX_VALUE = -1;

    /**
     * Constructor for CANParser.
     *
     * @param canBus The CANBus instance to be used by this parser.
     */
    public CANParser(CANBus canBus) {
        this.canBus = canBus;
    }

    /**
     * Parses incoming data based on the provided index. Some index have not been implemented due to
     * incomplete de-compilation of the factory test app. There also exists the following index
     * which the Bellabot calls: -56, -57, 96, 97, 100, 101, 116.
     *
     * @param index The identifier indicating the type of data to parse.
     * @param bytes The data to parse.
     */
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
                Log.d(this.TAG, "Index " + index + " Hardware info not implemented");
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

    /**
     * Parses the encoder data from the given byte array and updates the CANBus encoder.
     *
     * @param bytes The byte array containing encoder data.
     */
    private void parseEncoder(byte[] bytes) {
        // Extract the left encoder value from the byte array
        short encoderLeft = (short) ((((bytes[1] & MAX_VALUE) & 255) << 8) | (bytes[2] & MAX_VALUE));

        // Extract the right encoder value from the byte array
        short encoderRight = (short) ((bytes[4] & MAX_VALUE) | (((bytes[3] & MAX_VALUE) & 255) << 8));

        // Update the CANBus encoder with the parsed values
        canBus.getEncoder().update(encoderLeft, encoderRight);

        // Set the receivedEncoder flag to true
        canBus.setReceivedEncoder(true);
    }

    /**
     * Parses the battery percentage from the given byte array and updates the CANBus with the latest value.
     *
     * @param bytes The byte array containing the battery percentage data.
     */
    private void parseBatteryPercent(byte[] bytes) {
        // Extract the battery percentage from the byte array (second byte)
        int percent = bytes[1] & MAX_VALUE;

        // Update the CANBus with the extracted battery percentage
        canBus.setLastPowerPercent(percent);

        Log.d(this.TAG, "Battery percent at " + percent);
    }

    /**
     * Parses the charging state from the given byte array and updates the CANBus with the latest state.
     *
     * @param bytes The byte array containing the charging state data.
     */
    private void parseCharging(byte[] bytes) {
        // Extract the charging state from the byte array (second byte)
        int charging = (bytes[1] & MAX_VALUE);

        // Update the CANBus with the extracted charging state
        if (charging == 1) {
            canBus.setLastChargeState(ChargeState.Charging);
        } else {
            canBus.setLastChargeState(ChargeState.Idle);
        }

        Log.d(this.TAG, "Battery charging: " + charging);
    }

    /**
     * Parses IMU (Inertial Measurement Unit) data from the given byte array and updates the CANBus gyroscope.
     *
     * @param bytes The byte array containing the IMU data.
     */
    private void parseIMU(byte[] bytes) {
        // Extract the x-axis value from the byte array
        short x = (short) ((((bytes[1] & MAX_VALUE) & 255) << 8) | (bytes[2] & MAX_VALUE));
        // Extract the y-axis value from the byte array
        short y = (short) ((((bytes[3] & MAX_VALUE) & 255) << 8) | (bytes[4] & MAX_VALUE));
        // Extract the z-axis value from the byte array
        short z = (short) ((bytes[6] & MAX_VALUE) | (((bytes[5] & MAX_VALUE) & 255) << 8));

        // Update the gyroscope data in the CANBus with the extracted values
        canBus.getGyroscope().update(x, y, z);

        // Indicate that IMU data has been received
        canBus.setReceivedIMU(true);
    }

    /**
     * Parses wheel error data from the given byte array based on the specified index.
     *
     * @param bytes The byte array containing the wheel error data.
     * @param index The index indicating the protocol version.
     */
    private void parseWheelError(byte[] bytes, int index) {
        // Variables to hold the errors for the left and right wheels
        List<WheelError> errorsLeft;
        List<WheelError> errorsRight;

        int protocolVersion = 0;
        if (index == 66) {
            // This is the old motor protocol
            errorsLeft = WheelError.getErrors1(bytes[1] & MAX_VALUE);
            errorsRight = WheelError.getErrors1(bytes[2] & MAX_VALUE);
        } else if (index == 67) {
            // This is the new motor protocol (1)
            errorsLeft = WheelError.getErrors2(bytes[1] & MAX_VALUE, bytes[2] & MAX_VALUE);
            errorsRight = WheelError.getErrors2(bytes[3] & MAX_VALUE, bytes[4] & MAX_VALUE);
            protocolVersion = 1;
        } else if (index == 71) {
            // This is the new motor protocol (2)
            errorsLeft = WheelError.getErrors3(bytes[1] & MAX_VALUE, bytes[2] & MAX_VALUE);
            errorsRight = WheelError.getErrors3(bytes[3] & MAX_VALUE, bytes[4] & MAX_VALUE);
            protocolVersion = 2;
        } else {
            Log.d(this.TAG, "Invalid index for the parseWheelError command");
            return;
        }

        // Notify the CANBus of the parsed wheel errors
        canBus.notifyWheelError(errorsLeft, errorsRight, protocolVersion);
    }

    /**
     * Parses acceleration data from the given byte array.
     *
     * @param bytes The byte array containing the acceleration data.
     */
    private void parseAcceleration(byte[] bytes) {
        // The type of acceleration
        byte accelerationType = bytes[1];

        // Parse the acceleration value
        double acceleration = (double) (((bytes[2] & MAX_VALUE) << 24) | ((bytes[3] & MAX_VALUE) << 16)
                | ((bytes[4] & MAX_VALUE) << 8) | (bytes[5] & MAX_VALUE)) / 10000;

        // Determine the type of acceleration as a string
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

        Log.d(this.TAG, "id: " + bytes[0] + ", accel type: " + typeString + ", data: " + acceleration);

        // Set the last accelerations received
        canBus.setLastAcceleration(acceleration, accelerationType);
    }

    /**
     * Parses the IRD LED status from the given byte array.
     *
     * @param bytes The byte array containing the IRD LED status.
     */
    private void parseIRDLED(byte[] bytes) {
        // Extract the IRD LED status from the second byte
        Log.d(this.TAG, "isCameraIRDLEDLightOn: " + ((bytes[1] & MAX_VALUE) == 1));
    }

    /**
     * Parses the battery charge information from the given byte array.
     *
     * @param bytes The byte array containing the battery charge information.
     */
    private void parseBatteryCharge(byte[] bytes) {
        // Extract and set the last power percentage from the fourth byte
        int percent = bytes[3] & MAX_VALUE;
        canBus.setLastPowerPercent(percent);

        // Extract and set the last charge state from the second byte
        ChargeState currentState = ChargeState.fromValue(bytes[1]);
        canBus.setLastChargeState(currentState);

        Log.d(this.TAG, "Battery percent at " + percent + ", current state: " + currentState);
    }


    /**
     * Parses the motor lock status from the given byte array.
     *
     * @param bytes The byte array containing the motor lock status information.
     */
    private void parseMotorLock(byte[] bytes) {
        // Extract the lock result from the second byte
        int lockStatus = bytes[1] & 255;

        // Check the lock result and log the corresponding status
        if (lockStatus == 0) {
            Log.d(this.TAG, "lockStatus unlock success");
            canBus.setWheelLocked(false);
        } else if (lockStatus == 1) {
            Log.d(this.TAG, "lockStatus lock success");
            canBus.setWheelLocked(true);
        } else {
            Log.d(this.TAG, "lockStatus invalid");
        }
    }

    /**
     * Parses the bumper switch data received from the CAN bus.
     *
     * @param bytes The byte array containing the bumper switch data.
     */
    private void parseBumperSwitch(byte[] bytes) {
        int switchResult = (bytes[1] & 255);
        Log.d(this.TAG, "parseBumperSwitch bumperSwitchResult = " + switchResult);
    }

    /**
     * Parses the anti-collision switch data received from the CAN bus.
     *
     * @param bytes The byte array containing the anti-collision switch data.
     */
    private void parseAntiCollisionSwitch(byte[] bytes) {
        Log.d(this.TAG, "onAntiCollision: " + ((bytes[1] & MAX_VALUE) == 1));
    }

    /**
     * Parses the SLAM core power data received from the CAN bus.
     *
     * @param bytes The byte array containing the SLAM core power data.
     */
    private void parseSlamCorePower(byte[] bytes) {
        Log.d(this.TAG, "onSlamCorePower: " + ((bytes[1] & MAX_VALUE) == 1));
    }

    /**
     * Parses the disinfection power data received from the CAN bus.
     *
     * @param bytes The byte array containing the disinfection power data.
     */
    private void parseDisinfectionPower(byte[] bytes) {
        Log.d(this.TAG, "onDisinfectionPower: " + ((bytes[1] & MAX_VALUE) == 1));
    }

    /**
     * Parses the battery state of health (SOH) data received from the CAN bus.
     *
     * @param bytes The byte array containing the battery SOH data.
     */
    private void parseBatterySOH(byte[] bytes) {
        Log.d(this.TAG, "onBatterySOH: " + ((bytes[3] & MAX_VALUE) == 1));
    }
}
