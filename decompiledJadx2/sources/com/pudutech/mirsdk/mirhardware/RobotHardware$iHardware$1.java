package com.pudutech.mirsdk.mirhardware;

import android.os.Build;
import android.os.SystemClock;
import com.pudutech.base.Pdlog;
import com.pudutech.base.SpUtils;
import com.pudutech.mirsdk.DmesgWorker;
import com.pudutech.mirsdk.FunctionScope;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.aidl.serialize.InitStep;
import com.pudutech.mirsdk.config.SDKConfig;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: RobotHardware.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000I\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0007H\u0016J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00072\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016J\u0010\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u000bH\u0016J\b\u0010\u0015\u001a\u00020\u0003H\u0016J\b\u0010\u0016\u001a\u00020\u0003H\u0016J\u0010\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0010\u0010\u0018\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0012\u0010\u0019\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J&\u0010\u001b\u001a\u00020\u00032\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010\u0005H\u0016J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J1\u0010\"\u001a\u00020\u00032\u0010\u0010#\u001a\f\u0012\u0006\b\u0001\u0012\u00020%\u0018\u00010$2\u0010\u0010&\u001a\f\u0012\u0006\b\u0001\u0012\u00020%\u0018\u00010$H\u0016¢\u0006\u0002\u0010'J\u0010\u0010(\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u000bH\u0016J\u0010\u0010)\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H\u0016¨\u0006*"}, m3961d2 = {"com/pudutech/mirsdk/mirhardware/RobotHardware$iHardware$1", "Lcom/pudutech/mirsdk/hardware/IHardware$Stub;", "geomagneticAntiDrop", "", "p0", "", "p1", "", "p2", "p3", "onAntiCollisionSwitch", "", "onBattery", "percent", "chargeState", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "onBatteryFloorLevelLimit", "onBatterySOH", "onBumperSwitchStatus", "onCameraIRDLED", "lightOn", "onChargingFail", "onConnectedChargingPile", "onDisinfectionPower", "onHarewareEmergencyBrake", "onHarewareInfoReport", "onLockMotorStatus", "onOpenStep", "step", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareOpenStep;", "stepState", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "description", "onSlamCorePower", "onWheelError", "left", "", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "right", "([Lcom/pudutech/mirsdk/hardware/serialize/WheelError;[Lcom/pudutech/mirsdk/hardware/serialize/WheelError;)V", "securityFeedback", "sensormagnetic", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotHardware$iHardware$1 extends IHardware.Stub {
    final /* synthetic */ RobotHardware this$0;

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onBatterySOH(int p0) {
    }

    public void onChargingFail() {
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onSlamCorePower(boolean p0) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public RobotHardware$iHardware$1(RobotHardware robotHardware) {
        this.this$0 = robotHardware;
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x008d, code lost:
    
        if ((!(r23.length == 0)) == false) goto L38;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x009a, code lost:
    
        r1 = r22.this$0.TAG;
        com.pudutech.base.Pdlog.m3273d(r1, "already in error, ignore");
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a9, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x0098, code lost:
    
        if ((!(r24.length == 0)) != false) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00dd, code lost:
    
        if ((r23.length == 0) != false) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e7, code lost:
    
        if ((r24.length == 0) != false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0037, code lost:
    
        if ((!(r23.length == 0)) == false) goto L11;
     */
    @Override // com.pudutech.mirsdk.hardware.IHardware
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onWheelError(WheelError[] left, WheelError[] right) {
        String str;
        boolean z;
        String str2;
        HashMap hashMap;
        long j;
        HashMap hashMap2;
        String str3;
        String str4;
        HashMap hashMap3;
        long j2;
        HashMap hashMap4;
        String str5;
        String str6;
        String str7;
        str = this.this$0.TAG;
        Pdlog.m3276v(str, "onWheelError enter");
        this.this$0.getWatchDog().tick("CAN");
        z = this.this$0.initStatus;
        if (!z) {
            if (left != null) {
            }
            if (right != null) {
                if (!(!(right.length == 0))) {
                    return;
                }
                str7 = this.this$0.TAG;
                Pdlog.m3273d(str7, "robot is in init process, ignore wheel errors");
                return;
            }
            return;
        }
        this.this$0.getRobotStatus().getEmergencyKeyPressed().setValue(Boolean.valueOf((left != null && ArraysKt.contains(left, WheelError.EmergencyKeyPressed)) || (right != null && ArraysKt.contains(right, WheelError.EmergencyKeyPressed))));
        if (this.this$0.getWheelInError()) {
            if (left != null) {
            }
            if (right != null) {
            }
            str6 = this.this$0.TAG;
            Pdlog.m3273d(str6, "wheel error cleared");
            this.this$0.setWheelInError(false);
            this.this$0.setWheelInWarning(false);
            this.this$0.getWheelMulfunction().clear();
            return;
        }
        if (this.this$0.getWheelInWarning()) {
            if (left != null) {
            }
            if (right != null) {
            }
            this.this$0.getWheelMulfunction().clear();
        }
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (left != null) {
            if (!(left.length == 0)) {
                long j3 = elapsedRealtime;
                String joinToString$default = ArraysKt.joinToString$default(left, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null);
                str4 = this.this$0.TAG;
                Pdlog.m3277w(str4, "onWheelError Left:" + joinToString$default);
                int length = left.length;
                int i = 0;
                while (i < length) {
                    WheelError wheelError = left[i];
                    hashMap3 = this.this$0.ignoreWheelErrorMap;
                    if (hashMap3.containsKey(wheelError)) {
                        hashMap4 = this.this$0.ignoreWheelErrorMap;
                        if (Intrinsics.areEqual(hashMap4.get(wheelError), (Object) false)) {
                            str5 = this.this$0.TAG;
                            Pdlog.m3277w(str5, "left wheel err " + wheelError + " occurs, ignore response!");
                            j2 = j3;
                            i++;
                            j3 = j2;
                        }
                    }
                    j2 = j3;
                    this.this$0.updateWheelWelfunction(wheelError, "WheelErrorLeft", j2);
                    i++;
                    j3 = j2;
                }
                elapsedRealtime = j3;
            }
        }
        if (right != null) {
            if (!(right.length == 0)) {
                long j4 = elapsedRealtime;
                String joinToString$default2 = ArraysKt.joinToString$default(right, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null);
                str2 = this.this$0.TAG;
                Pdlog.m3277w(str2, "onWheelError Right:" + joinToString$default2);
                int length2 = right.length;
                int i2 = 0;
                while (i2 < length2) {
                    WheelError wheelError2 = right[i2];
                    hashMap = this.this$0.ignoreWheelErrorMap;
                    if (hashMap.containsKey(wheelError2)) {
                        hashMap2 = this.this$0.ignoreWheelErrorMap;
                        if (Intrinsics.areEqual(hashMap2.get(wheelError2), (Object) false)) {
                            str3 = this.this$0.TAG;
                            Pdlog.m3277w(str3, "right wheel err " + wheelError2 + " occurs, ignore response!");
                            j = j4;
                            i2++;
                            j4 = j;
                        }
                    }
                    j = j4;
                    this.this$0.updateWheelWelfunction(wheelError2, "WheelErrorRight", j);
                    i2++;
                    j4 = j;
                }
            }
        }
        this.this$0.reportWheelError();
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onOpenStep(HardwareOpenStep step, StepState stepState, String description) {
        String str;
        String str2;
        String str3;
        RGBDInterface rGBDInterface;
        RobotHardware$rgbdStatusListener$1 robotHardware$rgbdStatusListener$1;
        MachineInfo machineInfo;
        HashMap<MachineInfo.Byte23Info, UByte> byte23Data;
        if (step == null || stepState == null) {
            str = this.this$0.TAG;
            Pdlog.m3277w(str, "onOpenStep null param, step:" + step + " state:" + stepState + " desc:" + description);
            return;
        }
        str2 = this.this$0.TAG;
        Pdlog.m3275i(str2, "onOpenStep " + step + " success:" + stepState);
        switch (RobotHardware.WhenMappings.$EnumSwitchMapping$5[step.ordinal()]) {
            case 1:
                int i = RobotHardware.WhenMappings.$EnumSwitchMapping$0[stepState.ordinal()];
                if (i == 1) {
                    this.this$0.onOpenSuccess();
                    this.this$0.openStep = StepState.Success;
                    return;
                } else {
                    if (i != 2) {
                        return;
                    }
                    this.this$0.openStep = StepState.Fail;
                    if (Build.VERSION.SDK_INT >= 26) {
                        DmesgWorker.INSTANCE.dmesgWork();
                        return;
                    }
                    return;
                }
            case 2:
                RobotHardware robotHardware = this.this$0;
                InitStep initStep = InitStep.CheckMachineInfo;
                if (description == null) {
                    description = "";
                }
                robotHardware.onInitStepNotifyHardware(initStep, stepState, description);
                return;
            case 3:
                RobotHardware robotHardware2 = this.this$0;
                InitStep initStep2 = InitStep.CheckESP;
                if (description == null) {
                    description = "";
                }
                robotHardware2.onInitStepNotifyHardware(initStep2, stepState, description);
                return;
            case 4:
            case 5:
            case 6:
            case 7:
                int i2 = RobotHardware.WhenMappings.$EnumSwitchMapping$2[stepState.ordinal()];
                if (i2 == 1) {
                    RobotHardware robotHardware3 = this.this$0;
                    InitStep initStep3 = InitStep.CheckCAN;
                    if (description == null) {
                        description = "";
                    }
                    robotHardware3.onInitStepNotifyHardware(initStep3, stepState, description);
                    return;
                }
                if (i2 == 2) {
                    if (RobotHardware.WhenMappings.$EnumSwitchMapping$1[step.ordinal()] == 1) {
                        this.this$0.onInitStepNotifyHardware(InitStep.CheckCAN, stepState, "");
                        return;
                    } else {
                        this.this$0.onInitStepNotifyHardware(InitStep.CheckCAN, StepState.Running, "");
                        return;
                    }
                }
                this.this$0.onInitStepNotifyHardware(InitStep.CheckCAN, StepState.Running, "");
                return;
            case 8:
                RobotHardware robotHardware4 = this.this$0;
                InitStep initStep4 = InitStep.CheckIMU;
                if (description == null) {
                    description = "";
                }
                robotHardware4.onInitStepNotifyHardware(initStep4, stepState, description);
                return;
            case 9:
                RobotHardware robotHardware5 = this.this$0;
                InitStep initStep5 = InitStep.CheckEncoder;
                if (description == null) {
                    description = "";
                }
                robotHardware5.onInitStepNotifyHardware(initStep5, stepState, description);
                return;
            case 10:
                RobotHardware robotHardware6 = this.this$0;
                InitStep initStep6 = InitStep.CheckBattery;
                if (description == null) {
                    description = "";
                }
                robotHardware6.onInitStepNotifyHardware(initStep6, stepState, description);
                return;
            case 11:
                RobotHardware robotHardware7 = this.this$0;
                InitStep initStep7 = InitStep.CheckLidar;
                if (description == null) {
                    description = "";
                }
                robotHardware7.onInitStepNotifyHardware(initStep7, stepState, description);
                return;
            case 12:
                RobotHardware robotHardware8 = this.this$0;
                InitStep initStep8 = InitStep.CheckMagicSensor;
                if (description == null) {
                    description = "";
                }
                robotHardware8.onInitStepNotifyHardware(initStep8, stepState, description);
                return;
            case 13:
                if (stepState == StepState.Success && SpUtils.get(SDKConfig.INSTANCE.getProcessContext(), "mirhardware", "mark_enable", true)) {
                    str3 = this.this$0.TAG;
                    Pdlog.m3273d(str3, "watchDog watch camera w 1000 e 3000");
                    this.this$0.getWatchDog().watch("Camera", 1000L, SolicitService.CAMERA_OPEN_TIME_OUT);
                }
                RobotHardware robotHardware9 = this.this$0;
                InitStep initStep9 = InitStep.CheckCamera;
                if (description == null) {
                    description = "";
                }
                robotHardware9.onInitStepNotifyHardware(initStep9, stepState, description);
                return;
            case 14:
            case 15:
            case 16:
            case 17:
                int i3 = RobotHardware.WhenMappings.$EnumSwitchMapping$4[stepState.ordinal()];
                if (i3 == 1) {
                    RobotHardware robotHardware10 = this.this$0;
                    InitStep initStep10 = InitStep.CheckRGBD;
                    if (description == null) {
                        description = "";
                    }
                    robotHardware10.onInitStepNotifyHardware(initStep10, stepState, description);
                    return;
                }
                if (i3 == 2) {
                    if (RobotHardware.WhenMappings.$EnumSwitchMapping$3[step.ordinal()] == 1) {
                        RobotHardware robotHardware11 = this.this$0;
                        InitStep initStep11 = InitStep.CheckRGBD;
                        if (description == null) {
                            description = "";
                        }
                        robotHardware11.onInitStepNotifyHardware(initStep11, stepState, description);
                        HardwareInterface hardwareInterface = this.this$0.getInterface();
                        UByte uByte = (hardwareInterface == null || (machineInfo = hardwareInterface.getMachineInfo()) == null || (byte23Data = machineInfo.getByte23Data()) == null) ? null : byte23Data.get(MachineInfo.Byte23Info.RGBDVersion);
                        if (uByte != null ? true ^ UByte.m4535equalsimpl0(uByte.getData(), MachineInfo.RGBDType.NODevice.getId()) : true) {
                            this.this$0.getWatchDog().watch("RGBD", 300L, 4000L);
                        }
                        HardwareInterface hardwareInterface2 = this.this$0.getInterface();
                        if (hardwareInterface2 == null || (rGBDInterface = hardwareInterface2.getRGBDInterface()) == null) {
                            return;
                        }
                        robotHardware$rgbdStatusListener$1 = this.this$0.rgbdStatusListener;
                        rGBDInterface.addStateListener("function", robotHardware$rgbdStatusListener$1);
                        return;
                    }
                    this.this$0.onInitStepNotifyHardware(InitStep.CheckRGBD, StepState.Running, "");
                    return;
                }
                this.this$0.onInitStepNotifyHardware(InitStep.CheckRGBD, StepState.Running, "");
                return;
            default:
                return;
        }
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onBattery(int percent, ChargeState chargeState) {
        String str;
        Intrinsics.checkParameterIsNotNull(chargeState, "chargeState");
        BatteryState batteryState = new BatteryState();
        batteryState.setPercent(percent);
        batteryState.setChargeState(chargeState);
        this.this$0.getRobotStatus().getBattery().setValue(batteryState);
        str = this.this$0.TAG;
        Pdlog.m3276v(str, "onBattery " + percent + " chargeState:" + chargeState);
        this.this$0.getWatchDog().tick("Battery");
        this.this$0.getWatchDog().tick("CAN");
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onCameraIRDLED(boolean lightOn) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3275i(str, "onCameraIRDLED lightOn:" + lightOn);
        this.this$0.getRobotStatus().isCameraIRDLEDLightOn().setValue(Boolean.valueOf(lightOn));
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onHarewareInfoReport(String p0) {
        Pdlog.m3273d("RobotHardware", "onHarewareInfoReport() = " + p0);
        this.this$0.getReportCloud().invoke(p0);
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onHarewareEmergencyBrake(boolean p0) {
        this.this$0.getEmergencyEvent().emit(Boolean.valueOf(p0));
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onAntiCollisionSwitch(boolean p0) {
        this.this$0.getRobotStatus().getCollisionStatus().setValue(Boolean.valueOf(p0));
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onDisinfectionPower(boolean p0) {
        this.this$0.getRobotStatus().getDisinfectionOn().setValue(Boolean.valueOf(p0));
    }

    @Override // com.pudutech.mirsdk.hardware.IHardware
    public void onConnectedChargingPile() {
        this.this$0.getChargingPileConnectedEvent().emit(true);
    }

    public void onBatteryFloorLevelLimit(int p0) {
        this.this$0.getRobotStatus().getBatteryFloorLevelLimit().getValue().setLevel(p0);
        BuildersKt__Builders_commonKt.launch$default(FunctionScope.INSTANCE, FunctionScope.INSTANCE.getListenerWorker(), null, new RobotHardware$iHardware$1$onBatteryFloorLevelLimit$1(this, null), 2, null);
    }

    public void onLockMotorStatus(boolean p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "LockMotorStatus is " + p0);
        this.this$0.getRobotStatus().getLockMotorStatus().setValue(Boolean.valueOf(p0));
    }

    public void geomagneticAntiDrop(String p0, int p1, int p2, int p3) {
        String str;
        Intrinsics.checkParameterIsNotNull(p0, "p0");
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "geomagneticAntiDrop call is " + p0 + " x=" + p1 + " y=" + p2 + " z=" + p3);
        this.this$0.getFallDrop().emit(new GeomagneticData(p0, p1, p2, p3));
    }

    public void sensormagnetic(int p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "sensormagnetic detect device type = " + p0);
    }

    public void securityFeedback(boolean p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "securityFeedback is " + p0);
        this.this$0.getSecurityEvent().emit(Boolean.valueOf(p0));
    }

    public void onBumperSwitchStatus(boolean p0) {
        String str;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "onBumperSwitchStatus is " + p0);
        this.this$0.getRobotStatus().getBumperCheckStatus().setValue(Boolean.valueOf(p0));
    }
}
