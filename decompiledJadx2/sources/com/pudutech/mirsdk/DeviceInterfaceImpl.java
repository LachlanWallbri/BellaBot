package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.Tools;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.aidl.DeviceInterface;
import com.pudutech.mirsdk.aidl.IDeviceListener;
import com.pudutech.mirsdk.aidl.serialize.BoardInfo;
import com.pudutech.mirsdk.aidl.serialize.LidarDeviceInfo;
import com.pudutech.mirsdk.aidl.serialize.RgbdDeviceInfo;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.LidarInterface;
import com.pudutech.mirsdk.hardware.RGBDInterface;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.mirhardware.RobotHardware;
import com.pudutech.mirsdk.mirhardware.RobotStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: DeviceInterfaceImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u001c\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\n2\b\u0010!\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\"\u001a\u00020\u001f2\u0006\u0010#\u001a\u00020$H\u0016J\u0010\u0010%\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020$H\u0016J\b\u0010'\u001a\u00020\nH\u0016J\u000e\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)H\u0016J\u000e\u0010+\u001a\b\u0012\u0004\u0012\u00020\n0)H\u0016J\u000e\u0010,\u001a\b\u0012\u0004\u0012\u00020-0)H\u0016J\u000e\u0010.\u001a\b\u0012\u0004\u0012\u00020\n0)H\u0016J\u000e\u0010/\u001a\b\u0012\u0004\u0012\u0002000)H\u0016J\u000e\u00101\u001a\b\u0012\u0004\u0012\u00020\n0)H\u0016J\u0010\u00102\u001a\u00020\u001f2\u0006\u00103\u001a\u00020$H\u0016J\u0012\u00104\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010\nH\u0016J\b\u00105\u001a\u00020\u001fH\u0016J\b\u00106\u001a\u00020$H\u0016J\b\u00107\u001a\u00020\u001fH\u0016J\b\u00108\u001a\u00020\u001fH\u0016J\b\u00109\u001a\u00020\u001fH\u0016J\b\u0010:\u001a\u00020\u001fH\u0016J\b\u0010;\u001a\u00020\u001fH\u0016J\b\u0010<\u001a\u00020\u001fH\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082D¢\u0006\u0002\n\u0000R \u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006="}, m3961d2 = {"Lcom/pudutech/mirsdk/DeviceInterfaceImpl;", "Lcom/pudutech/mirsdk/aidl/DeviceInterface$Stub;", "robotStatus", "Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "robotHardware", "Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "watchDog", "Lcom/pudutech/mirsdk/WatchDog;", "(Lcom/pudutech/mirsdk/mirhardware/RobotStatus;Lcom/pudutech/mirsdk/mirhardware/RobotHardware;Lcom/pudutech/mirsdk/WatchDog;)V", "TAG", "", "deviceListeners", "Lcom/pudutech/base/architecture/ThreadSafeListener;", "Lcom/pudutech/mirsdk/aidl/IDeviceListener;", "getDeviceListeners", "()Lcom/pudutech/base/architecture/ThreadSafeListener;", "setDeviceListeners", "(Lcom/pudutech/base/architecture/ThreadSafeListener;)V", "getRobotHardware", "()Lcom/pudutech/mirsdk/mirhardware/RobotHardware;", "setRobotHardware", "(Lcom/pudutech/mirsdk/mirhardware/RobotHardware;)V", "getRobotStatus", "()Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "setRobotStatus", "(Lcom/pudutech/mirsdk/mirhardware/RobotStatus;)V", "getWatchDog", "()Lcom/pudutech/mirsdk/WatchDog;", "setWatchDog", "(Lcom/pudutech/mirsdk/WatchDog;)V", "addListener", "", "name", "listener", "controlIRLED", "lightOn", "", "controlRGBD", "p0", "getBatterySn", "getBoardUidList", "", "Lcom/pudutech/mirsdk/aidl/serialize/BoardInfo;", "getCameraSnList", "getLidarDeviceList", "Lcom/pudutech/mirsdk/aidl/serialize/LidarDeviceInfo;", "getLidarSnList", "getRgbdDeviceList", "Lcom/pudutech/mirsdk/aidl/serialize/RgbdDeviceInfo;", "getRgbdSnList", "lockMotor", "lock", "removeListener", "resetUSB", "rgbdOilStainCheck", "runDisinfection", "runMarkerCamera", "startLidar", "stopDisinfection", "stopLidar", "stopMarkerCamera", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class DeviceInterfaceImpl extends DeviceInterface.Stub {
    private final String TAG;
    private ThreadSafeListener<IDeviceListener> deviceListeners;
    private RobotHardware robotHardware;
    private RobotStatus robotStatus;
    private WatchDog watchDog;

    public DeviceInterfaceImpl(RobotStatus robotStatus, RobotHardware robotHardware, WatchDog watchDog) {
        Intrinsics.checkParameterIsNotNull(robotStatus, "robotStatus");
        Intrinsics.checkParameterIsNotNull(robotHardware, "robotHardware");
        Intrinsics.checkParameterIsNotNull(watchDog, "watchDog");
        this.robotStatus = robotStatus;
        this.robotHardware = robotHardware;
        this.watchDog = watchDog;
        this.TAG = "DeviceInterfaceImpl";
        this.deviceListeners = new ThreadSafeListener<>();
        this.robotStatus.getLidarOpened().onChange(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                DeviceInterfaceImpl.this.getDeviceListeners().notify(new Function2<IDeviceListener, String, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDeviceListener iDeviceListener, String str) {
                        invoke2(iDeviceListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDeviceListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onLidar(z);
                    }
                });
            }
        });
        this.robotStatus.isCameraIRDLEDLightOn().onChange(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                DeviceInterfaceImpl.this.getDeviceListeners().notify(new Function2<IDeviceListener, String, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDeviceListener iDeviceListener, String str) {
                        invoke2(iDeviceListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDeviceListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onIRLED(z);
                    }
                });
            }
        });
        this.robotStatus.getRgbdOpened().onChange(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.3
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                DeviceInterfaceImpl.this.getDeviceListeners().notify(new Function2<IDeviceListener, String, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.3.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDeviceListener iDeviceListener, String str) {
                        invoke2(iDeviceListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDeviceListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onRGBD(z);
                    }
                });
            }
        });
        this.robotStatus.getCameraOpened().onChange(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                DeviceInterfaceImpl.this.getDeviceListeners().notify(new Function2<IDeviceListener, String, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.4.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDeviceListener iDeviceListener, String str) {
                        invoke2(iDeviceListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDeviceListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onMarkerCamera(z);
                    }
                });
            }
        });
        this.robotStatus.getCollisionStatus().onChange(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.5
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                DeviceInterfaceImpl.this.getDeviceListeners().notify(new Function2<IDeviceListener, String, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.5.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDeviceListener iDeviceListener, String str) {
                        invoke2(iDeviceListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDeviceListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onCollision(z);
                    }
                });
            }
        });
        this.robotStatus.getDisinfectionOn().onChange(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.6
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                DeviceInterfaceImpl.this.getDeviceListeners().notify(new Function2<IDeviceListener, String, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.6.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDeviceListener iDeviceListener, String str) {
                        invoke2(iDeviceListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDeviceListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onDisinfectionPower(z);
                    }
                });
            }
        });
        this.robotStatus.getEmergencyKeyPressed().onChange(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.7
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                DeviceInterfaceImpl.this.getDeviceListeners().notify(new Function2<IDeviceListener, String, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.7.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDeviceListener iDeviceListener, String str) {
                        invoke2(iDeviceListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDeviceListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onEmergencyKeyPressed(z);
                    }
                });
            }
        });
        this.robotStatus.getLockMotorStatus().onChange(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.8
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                DeviceInterfaceImpl.this.getDeviceListeners().notify(new Function2<IDeviceListener, String, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.8.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDeviceListener iDeviceListener, String str) {
                        invoke2(iDeviceListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDeviceListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onLockMotor(z);
                    }
                });
            }
        });
        this.robotStatus.getBumperCheckStatus().onChange(new Function1<Boolean, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.9
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                invoke(bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(final boolean z) {
                DeviceInterfaceImpl.this.getDeviceListeners().notify(new Function2<IDeviceListener, String, Unit>() { // from class: com.pudutech.mirsdk.DeviceInterfaceImpl.9.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IDeviceListener iDeviceListener, String str) {
                        invoke2(iDeviceListener, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IDeviceListener l, String str) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        l.onBumperSwitchStatus(z);
                    }
                });
            }
        });
    }

    public final RobotHardware getRobotHardware() {
        return this.robotHardware;
    }

    public final RobotStatus getRobotStatus() {
        return this.robotStatus;
    }

    public final WatchDog getWatchDog() {
        return this.watchDog;
    }

    public final void setRobotHardware(RobotHardware robotHardware) {
        Intrinsics.checkParameterIsNotNull(robotHardware, "<set-?>");
        this.robotHardware = robotHardware;
    }

    public final void setRobotStatus(RobotStatus robotStatus) {
        Intrinsics.checkParameterIsNotNull(robotStatus, "<set-?>");
        this.robotStatus = robotStatus;
    }

    public final void setWatchDog(WatchDog watchDog) {
        Intrinsics.checkParameterIsNotNull(watchDog, "<set-?>");
        this.watchDog = watchDog;
    }

    public final ThreadSafeListener<IDeviceListener> getDeviceListeners() {
        return this.deviceListeners;
    }

    public final void setDeviceListeners(ThreadSafeListener<IDeviceListener> threadSafeListener) {
        Intrinsics.checkParameterIsNotNull(threadSafeListener, "<set-?>");
        this.deviceListeners = threadSafeListener;
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void startLidar() {
        LidarInterface lidarInterface;
        Pdlog.m3273d(this.TAG, "startLidar called");
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null && (lidarInterface = hardwareInterface.getLidarInterface()) != null) {
            lidarInterface.open();
        }
        this.watchDog.watch("Lidar", 300L, 1000L);
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void stopLidar() {
        LidarInterface lidarInterface;
        Pdlog.m3273d(this.TAG, "stopLidar called");
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null && (lidarInterface = hardwareInterface.getLidarInterface()) != null) {
            lidarInterface.stop();
        }
        this.watchDog.removeWatch("Lidar");
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void addListener(String name, IDeviceListener listener) {
        if (name == null || listener == null) {
            return;
        }
        this.deviceListeners.add(name, listener);
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void removeListener(String name) {
        if (name != null) {
            this.deviceListeners.remove(name);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void controlIRLED(boolean lightOn) {
        Pdlog.m3273d(this.TAG, "controlIRLED called, param: " + lightOn);
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.controlCameraIRDLED(lightOn);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void controlRGBD(boolean p0) {
        MachineInfo machineInfo;
        HashMap<MachineInfo.Byte23Info, UByte> byte23Data;
        Pdlog.m3273d(this.TAG, "controlRGBD called, param: " + p0);
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.controlRGBD(p0);
        }
        HardwareInterface hardwareInterface2 = this.robotHardware.getInterface();
        UByte uByte = (hardwareInterface2 == null || (machineInfo = hardwareInterface2.getMachineInfo()) == null || (byte23Data = machineInfo.getByte23Data()) == null) ? null : byte23Data.get(MachineInfo.Byte23Info.RGBDVersion);
        if (uByte != null ? UByte.m4535equalsimpl0(uByte.getData(), MachineInfo.RGBDType.NODevice.getId()) : false) {
            return;
        }
        if (p0) {
            this.watchDog.watch("RGBD", 300L, 4000L);
        } else {
            this.watchDog.removeWatch("RGBD");
        }
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public boolean rgbdOilStainCheck() {
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            return hardwareInterface.oilStainCheck();
        }
        return true;
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void runMarkerCamera() {
        CameraInterface camera;
        Pdlog.m3273d(this.TAG, "runMarkerCamera called");
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null && (camera = hardwareInterface.getCamera()) != null) {
            camera.openMarkerCamera();
        }
        this.watchDog.watch("Camera", 1000L, SolicitService.CAMERA_OPEN_TIME_OUT);
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void stopMarkerCamera() {
        CameraInterface camera;
        Pdlog.m3273d(this.TAG, "stopMarkerCamera called");
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null && (camera = hardwareInterface.getCamera()) != null) {
            camera.closeMarkerCamera();
        }
        this.watchDog.removeWatch("Camera");
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void runDisinfection() {
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.controlDisinfectionPower(true);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void stopDisinfection() {
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.controlDisinfectionPower(false);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void lockMotor(boolean lock) {
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.lockMotor(lock ? 1 : 0);
        }
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public void resetUSB() {
        Pdlog.m3275i(this.TAG, "reset rgbd usb");
        controlRGBD(false);
        Tools.execCommand("chmod 777 /sys/class/pudu/gpioctl/usb2_en2", true);
        Tools.execCommand("echo 1 > /sys/class/pudu/gpioctl/usb2_en2", true);
        Thread.sleep(100L);
        Tools.execCommand("echo 0 > /sys/class/pudu/gpioctl/usb2_en2", true);
        Thread.sleep(7000L);
        Tools.execCommand("chmod -R 777 /dev/bus/usb*", true);
        Tools.execCommand("chmod 777 /dev/video*", true);
        Tools.execCommand("chmod 777 /dev/ttyACM*", true);
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public List<String> getRgbdSnList() {
        RGBDInterface rGBDInterface;
        List rgbdSnList;
        List<String> mutableList;
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        return (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null || (rgbdSnList = rGBDInterface.getRgbdSnList()) == null || (mutableList = CollectionsKt.toMutableList((Collection) rgbdSnList)) == null) ? new ArrayList() : mutableList;
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public List<String> getLidarSnList() {
        LidarInterface lidarInterface;
        List lidarSnList;
        List<String> mutableList;
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        return (hardwareInterface == null || (lidarInterface = hardwareInterface.getLidarInterface()) == null || (lidarSnList = lidarInterface.getLidarSnList()) == null || (mutableList = CollectionsKt.toMutableList((Collection) lidarSnList)) == null) ? new ArrayList() : mutableList;
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public List<String> getCameraSnList() {
        CameraInterface camera;
        ArrayList arrayList = new ArrayList();
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        byte[] onGetCameraIDNative = (hardwareInterface == null || (camera = hardwareInterface.getCamera()) == null) ? null : camera.onGetCameraIDNative();
        if (onGetCameraIDNative != null) {
            arrayList.add(new String(onGetCameraIDNative, Charsets.UTF_8));
        }
        return arrayList;
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public String getBatterySn() {
        String batterySn;
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        return (hardwareInterface == null || (batterySn = hardwareInterface.getBatterySn()) == null) ? "" : batterySn;
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public List<BoardInfo> getBoardUidList() {
        ArrayList arrayList = new ArrayList();
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        List<com.pudutech.mirsdk.hardware.serialize.BoardInfo> boardUidList = hardwareInterface != null ? hardwareInterface.getBoardUidList() : null;
        if (boardUidList != null) {
            for (com.pudutech.mirsdk.hardware.serialize.BoardInfo boardInfo : boardUidList) {
                arrayList.add(new BoardInfo(boardInfo.getHardwareBoardName(), boardInfo.getUid()));
            }
        }
        return arrayList;
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public List<RgbdDeviceInfo> getRgbdDeviceList() {
        RGBDInterface rGBDInterface;
        ArrayList arrayList = new ArrayList();
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        List<com.pudutech.mirsdk.hardware.serialize.RgbdDeviceInfo> rgbdDeviceList = (hardwareInterface == null || (rGBDInterface = hardwareInterface.getRGBDInterface()) == null) ? null : rGBDInterface.getRgbdDeviceList();
        if (rgbdDeviceList != null) {
            for (com.pudutech.mirsdk.hardware.serialize.RgbdDeviceInfo rgbdDeviceInfo : rgbdDeviceList) {
                arrayList.add(new RgbdDeviceInfo(rgbdDeviceInfo.getName(), rgbdDeviceInfo.getSn()));
            }
        }
        return arrayList;
    }

    @Override // com.pudutech.mirsdk.aidl.DeviceInterface
    public List<LidarDeviceInfo> getLidarDeviceList() {
        LidarInterface lidarInterface;
        ArrayList arrayList = new ArrayList();
        HardwareInterface hardwareInterface = this.robotHardware.getInterface();
        List<com.pudutech.mirsdk.hardware.serialize.LidarDeviceInfo> lidarDeviceList = (hardwareInterface == null || (lidarInterface = hardwareInterface.getLidarInterface()) == null) ? null : lidarInterface.getLidarDeviceList();
        if (lidarDeviceList != null) {
            for (com.pudutech.mirsdk.hardware.serialize.LidarDeviceInfo lidarDeviceInfo : lidarDeviceList) {
                arrayList.add(new LidarDeviceInfo(lidarDeviceInfo.getName(), lidarDeviceInfo.getSn()));
            }
        }
        return arrayList;
    }
}
