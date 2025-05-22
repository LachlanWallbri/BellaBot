package com.slamtec.slamware.robot;

import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class HealthInfo {
    private boolean error;
    private ArrayList<BaseError> errors;
    private boolean fatal;
    private boolean warning;
    private Boolean hasSystemEmergencyStop = null;
    private Boolean hasLidarDisconnected = null;
    private Boolean hasSdpDisconnected = null;
    private Boolean hasDepthCameraDisconnected = null;

    public HealthInfo() {
    }

    public HealthInfo(boolean z, boolean z2, boolean z3, ArrayList<BaseError> arrayList) {
        this.warning = z;
        this.error = z2;
        this.fatal = z3;
        this.errors = arrayList;
    }

    public boolean isWarning() {
        return this.warning;
    }

    public void setWarning(boolean z) {
        this.warning = z;
    }

    public boolean isError() {
        return this.error;
    }

    public void setError(boolean z) {
        this.error = z;
    }

    public boolean isFatal() {
        return this.fatal;
    }

    public void setFatal(boolean z) {
        this.fatal = z;
    }

    public ArrayList<BaseError> getErrors() {
        return this.errors;
    }

    public void setErrors(ArrayList<BaseError> arrayList) {
        this.errors = arrayList;
    }

    public Boolean getHasSystemEmergencyStop() {
        return this.hasSystemEmergencyStop;
    }

    public void setHasSystemEmergencyStop(Boolean bool) {
        this.hasSystemEmergencyStop = bool;
    }

    public Boolean getHasLidarDisconnected() {
        return this.hasLidarDisconnected;
    }

    public void setHasLidarDisconnected(Boolean bool) {
        this.hasLidarDisconnected = bool;
    }

    public Boolean getHasSdpDisconnected() {
        return this.hasSdpDisconnected;
    }

    public void setHasSdpDisconnected(Boolean bool) {
        this.hasSdpDisconnected = bool;
    }

    public Boolean getHasDepthCameraDisconnected() {
        return this.hasDepthCameraDisconnected;
    }

    public void setHasDepthCameraDisconnected(Boolean bool) {
        this.hasDepthCameraDisconnected = bool;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes6.dex
      classes7.dex
     */
    /* loaded from: classes2.dex */
    public static class BaseError {
        public static final int BaseComponentErrorTypeMotionBlowerStall = 4102;
        public static final int BaseComponentErrorTypeMotionBrushStall = 4101;
        public static final int BaseComponentErrorTypeMotionControllerDown = 4097;
        public static final int BaseComponentErrorTypeMotionMotorAlarm = 4098;
        public static final int BaseComponentErrorTypeMotionMotorDown = 4099;
        public static final int BaseComponentErrorTypeMotionNone = 4096;
        public static final int BaseComponentErrorTypeMotionOdometryDown = 4100;
        public static final int BaseComponentErrorTypePowerControllerDown = 3073;
        public static final int BaseComponentErrorTypePowerNone = 3072;
        public static final int BaseComponentErrorTypePowerOverCurrent = 3075;
        public static final int BaseComponentErrorTypePowerPowerLow = 3074;
        public static final int BaseComponentErrorTypeSensorBumperDown = 5122;
        public static final int BaseComponentErrorTypeSensorCliffDown = 5123;
        public static final int BaseComponentErrorTypeSensorControllerDown = 5121;
        public static final int BaseComponentErrorTypeSensorDustbinBlock = 5125;
        public static final int BaseComponentErrorTypeSensorDustbinGone = 5126;
        public static final int BaseComponentErrorTypeSensorIMUDown = 5130;
        public static final int BaseComponentErrorTypeSensorMagSelfTestFailed = 5129;
        public static final int BaseComponentErrorTypeSensorMagTapeTriggered = 5128;
        public static final int BaseComponentErrorTypeSensorNone = 5120;
        public static final int BaseComponentErrorTypeSensorSonarDown = 5124;
        public static final int BaseComponentErrorTypeSensorWallIrDown = 5127;
        public static final int BaseComponentErrorTypeSystemBrakeReleased = 2055;
        public static final int BaseComponentErrorTypeSystemCtrlBusDisconnected = 2053;
        public static final int BaseComponentErrorTypeSystemEmergencyStop = 2049;
        public static final int BaseComponentErrorTypeSystemNone = 2048;
        public static final int BaseComponentErrorTypeSystemSlamwareRebooted = 2054;
        public static final int BaseComponentErrorTypeSystemSlamwareRelocalizationFailed = 2056;
        public static final int BaseComponentErrorTypeSystemTemperatureHigh = 2050;
        public static final int BaseComponentErrorTypeSystemTemperatureLow = 2051;
        public static final int BaseComponentErrorTypeSystemWatchDogOverFlow = 2052;
        public static final int BaseComponentErrorTypeUnknown = -1;
        public static final int BaseComponentErrorTypeUser = 1024;
        public static final int BaseErrorComponentMotion = 3;
        public static final int BaseErrorComponentPower = 2;
        public static final int BaseErrorComponentSensor = 4;
        public static final int BaseErrorComponentSystem = 1;
        public static final int BaseErrorComponentUnknown = 255;
        public static final int BaseErrorComponentUser = 0;
        public static final int BaseErrorLevelError = 2;
        public static final int BaseErrorLevelFatal = 3;
        public static final int BaseErrorLevelHealthy = 0;
        public static final int BaseErrorLevelUnknown = 255;
        public static final int BaseErrorLevelWarn = 1;
        public static final int INVALID_COMPONENT_ERROR_DEVICE_ID = -1;
        public static final int MOTION_BRUSH_ID_LEFT_SIDE = 1;
        public static final int MOTION_BRUSH_ID_RIGHT_SIDE = 2;
        public static final int MOTION_BRUSH_ID_ROLLING = 0;
        private int componentErrorCode;
        private int componentErrorDeviceId;
        private int componentErrorType;
        private int errorCode;
        private int errorComponent;
        private int errorLevel;
        private String errorMessage;

        /* renamed from: id */
        private int f7600id;

        public BaseError() {
            this.componentErrorType = -1;
            this.componentErrorDeviceId = -1;
        }

        public BaseError(int i, int i2, int i3, int i4, int i5, String str) {
            this.componentErrorType = -1;
            this.componentErrorDeviceId = -1;
            this.errorCode = i2;
            this.errorLevel = i3;
            this.errorComponent = i4;
            this.componentErrorCode = i5;
            this.errorMessage = str;
        }

        public BaseError(int i, int i2, int i3, int i4, int i5, String str, int i6, int i7) {
            this.componentErrorType = -1;
            this.componentErrorDeviceId = -1;
            this.errorCode = i2;
            this.errorLevel = i3;
            this.errorComponent = i4;
            this.componentErrorCode = i5;
            this.errorMessage = str;
            this.componentErrorType = i6;
            this.componentErrorDeviceId = i7;
        }

        public int getId() {
            return this.f7600id;
        }

        public void setId(int i) {
            this.f7600id = i;
        }

        public int getErrorCode() {
            return this.errorCode;
        }

        public void setErrorCode(int i) {
            this.errorCode = i;
        }

        public int getErrorLevel() {
            return this.errorLevel;
        }

        public void setErrorLevel(int i) {
            this.errorLevel = i;
        }

        public int getErrorComponent() {
            return this.errorComponent;
        }

        public void setErrorComponent(int i) {
            this.errorComponent = i;
        }

        public int getComponentErrorCode() {
            return this.componentErrorCode;
        }

        public void setComponentErrorCode(int i) {
            this.componentErrorCode = i;
        }

        public String getErrorMessage() {
            return this.errorMessage;
        }

        public void setErrorMessage(String str) {
            this.errorMessage = str;
        }

        public int getComponentErrorType() {
            return this.componentErrorType;
        }

        public void setComponentErrorType(int i) {
            this.componentErrorType = i;
        }

        public int getComponentErrorDeviceId() {
            return this.componentErrorDeviceId;
        }

        public void setComponentErrorDeviceId(int i) {
            this.componentErrorDeviceId = i;
        }
    }
}
