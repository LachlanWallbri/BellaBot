package com.example.jobeeekerapp.serialize;

import java.util.ArrayList;
import java.util.List;

public enum WheelError {
    SchOver,
    Stall,
    SpeedOverOld,
    MotorFly,
    SpdFollowErr,
    SpdLose,
    JY01Close,
    Preseve,
    HardwareCurrentOver,
    MotherCurrentOver,
    MotherVoltageOver,
    MotherVoltageLow,
    TemperatureOver,
    EncoderError,
    ABZBreak,
    HallError,
    SpeedOver,
    MotorStuck,
    MotorOver,
    SpeedFlowDeviation,
    CANCmdLose,
    CANBreak,
    CoincidentAxisError,
    InternalError,
    CurrentZeroDriftError,
    TaskLoadOver,
    EepromError,
    OutLosePhase,
    PhaseCurOver,
    MotorTempOver,
    MosTempOver,
    MosSoftStartError,
    BumpSwitchReset,
    EmergencyKeyPressed;

    /**
     * This method is used to instantiate an array of WheelError objects with a given size.
     * The array elements are initially set to null.
     *
     * @param size The size of the array to be created.
     * @return A new array of WheelError with the specified size.
     */
    public WheelError[] newArray(int size) {
        return new WheelError[size];
    }


    /**
     * Retrieves a list of WheelErrors based on the provided error code. Each value corresponds to
     * specific error flags set in the code. Each bit in the error code represents a different type
     * of error. The method checks each bit to determine which errors are present.
     *
     * This method corresponds to the old motor wheel errors
     *
     * @param err The error code, where each bit represents a different WheelError.
     * @return A List of WheelError values corresponding to the set bits in the error code.
     */
    public static List<WheelError> getErrors1(int err) {
        ArrayList<WheelError> arrayList = new ArrayList<WheelError>();
        if ((err & 1) != 0) {
            arrayList.add(WheelError.SchOver);
        }
        if ((err & 1) != 0) {
            arrayList.add(WheelError.Stall);
        }
        if ((err & 4) != 0) {
            arrayList.add(WheelError.SpeedOverOld);
        }
        if ((err & 8) != 0) {
            arrayList.add(WheelError.MotorFly);
        }
        if ((err & 16) != 0) {
            arrayList.add(WheelError.SpdFollowErr);
        }
        if ((err & 32) != 0) {
            arrayList.add(WheelError.SpdLose);
        }
        if ((err & 64) != 0) {
            arrayList.add(WheelError.JY01Close);
        }
        if ((err & 128) != 0) {
            arrayList.add(WheelError.Preseve);
        }
        return arrayList;
    }

    /**
     * Retrieves a list of WheelErrors based on the provided error code. Each value corresponds to
     * specific error flags set in the code. Each bit in the error code represents a different type
     * of error. The method checks each bit to determine which errors are present.
     *
     * This method corresponds to a subset of the new motor wheel errors (1).
     *
     * @param err1 The first error code, where each bit represents a different WheelError.
     * @param err2 The second error code, where each bit represents a different WheelError.
     * @return A List of WheelError values corresponding to the set bits in the error code.
     */
    public static List<WheelError> getErrors2(int err1, int err2) {
        ArrayList<WheelError> arrayList = new ArrayList<WheelError>();
        if ((err2 & 1) != 0) {
            arrayList.add(WheelError.HardwareCurrentOver);
        }
        if ((err2 & 2) != 0) {
            arrayList.add(WheelError.MotherCurrentOver);
        }
        if ((err2 & 4) != 0) {
            arrayList.add(WheelError.MotherVoltageOver);
        }
        if ((err2 & 8) != 0) {
            arrayList.add(WheelError.MotherVoltageLow);
        }
        if ((err2 & 16) != 0) {
            arrayList.add(WheelError.TemperatureOver);
        }
        if ((err2 & 32) != 0) {
            arrayList.add(WheelError.EncoderError);
        }
        if ((err2 & 64) != 0) {
            arrayList.add(WheelError.ABZBreak);
        }
        if ((err2 & 128) != 0) {
            arrayList.add(WheelError.HallError);
        }
        if ((err1 & 1) != 0) {
            arrayList.add(WheelError.SpeedOver);
        }
        if ((err1 & 2) != 0) {
            arrayList.add(WheelError.MotorStuck);
        }
        if ((err1 & 4) != 0) {
            arrayList.add(WheelError.MotorOver);
        }
        if ((err1 & 8) != 0) {
            arrayList.add(WheelError.SpeedFlowDeviation);
        }
        if ((err1 & 16) != 0) {
            arrayList.add(WheelError.CANCmdLose);
        }
        if ((err1 & 32) != 0) {
            arrayList.add(WheelError.CANBreak);
        }
        if ((err1 & 128) != 0) {
            arrayList.add(WheelError.InternalError);
        }
        return arrayList;
    }

    /**
     * Retrieves a list of WheelErrors based on the provided error code. Each value corresponds to
     * specific error flags set in the code. Each bit in the error code represents a different type
     * of error. The method checks each bit to determine which errors are present.
     *
     * This method corresponds to a subset of the new motor wheel errors (2).
     *
     * @param err1 The first error code, where each bit represents a different WheelError.
     * @param err2 The second error code, where each bit represents a different WheelError.
     * @return A List of WheelError values corresponding to the set bits in the error code.
     */
    public static List<WheelError> getErrors3(int err1, int err2) {
        ArrayList<WheelError> arrayList = new ArrayList<WheelError>();
        if ((err2 & 1) != 0) {
            arrayList.add(WheelError.CurrentZeroDriftError);
        }
        if ((err2 & 2) != 0) {
            arrayList.add(WheelError.TaskLoadOver);
        }
        if ((err2 & 4) != 0) {
            arrayList.add(WheelError.EepromError);
        }
        if ((err2 & 8) != 0) {
            arrayList.add(WheelError.OutLosePhase);
        }
        if ((err2 & 16) != 0) {
            arrayList.add(WheelError.PhaseCurOver);
        }
        if ((err2 & 32) != 0) {
            arrayList.add(WheelError.MotorTempOver);
        }
        if ((err2 & 64) != 0) {
            arrayList.add(WheelError.MosTempOver);
        }
        if ((err2 & 128) != 0) {
            arrayList.add(WheelError.MosSoftStartError);
        }
        if ((err1 & 1) != 0) {
            arrayList.add(WheelError.BumpSwitchReset);
        }
        if ((err1 & 128) != 0) {
            arrayList.add(WheelError.EmergencyKeyPressed);
        }
        return arrayList;
    }
}
