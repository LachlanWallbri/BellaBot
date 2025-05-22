package org.ros.android.android_tutorial_pubsub.serialize;

import android.os.SystemClock;
import android.util.Log;

import org.ros.android.android_tutorial_pubsub.CANBus;

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

    public WheelError[] newArray(int size) {
        return new WheelError[size];
    }

    public static void updateWheelErrors(CANBus canBus, List<WheelError> errorsLeft, List<WheelError> errorsRight) {
        // STUB
    }

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
