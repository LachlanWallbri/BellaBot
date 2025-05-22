package com.pudutech.factory_test;

import android.content.ComponentName;
import android.os.IBinder;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.bumblebee.robot.aidl.RobotInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* compiled from: ServiceConnection.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000C\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t*\u0001\u0013\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000\"\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0014\u0010\f\u001a\u00020\u0001X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0010\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0014\":\u0010\u0015\u001a\"\u0012\u0006\u0012\u0004\u0018\u00010\u0017\u0012\u0006\u0012\u0004\u0018\u00010\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0016X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d\"\u001c\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#\"\u0014\u0010$\u001a\u00020\u0001X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u000e\"\u0017\u0010&\u001a\b\u0012\u0004\u0012\u00020\u001f0\t¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u000b¨\u0006("}, m3961d2 = {"TAG", "", "hdInterface", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "getHdInterface", "()Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "setHdInterface", "(Lcom/pudutech/mirsdk/hardware/HardwareInterface;)V", "hdService", "Lcom/pudutech/base/architecture/AIDLConnection;", "getHdService", "()Lcom/pudutech/base/architecture/AIDLConnection;", "hdServicePath", "getHdServicePath", "()Ljava/lang/String;", "isHdInterfaceInitDone", "", "isRbServiceConnected", "listener", "com/pudutech/factory_test/ServiceConnectionKt$listener$1", "Lcom/pudutech/factory_test/ServiceConnectionKt$listener$1;", "onHardwareOpenDone", "Lkotlin/Function3;", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareOpenStep;", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "", "getOnHardwareOpenDone", "()Lkotlin/jvm/functions/Function3;", "setOnHardwareOpenDone", "(Lkotlin/jvm/functions/Function3;)V", "rbInterface", "Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "getRbInterface", "()Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;", "setRbInterface", "(Lcom/pudutech/bumblebee/robot/aidl/RobotInterface;)V", "rbSericePath", "getRbSericePath", "rbService", "getRbService", "factorytest_3.11_2021-06-12_release"}, m3962k = 2, m3963mv = {1, 1, 16})
/* loaded from: classes.dex */
public final class ServiceConnectionKt {
    private static final String TAG = "ServiceConnection";
    private static HardwareInterface hdInterface = null;
    private static final AIDLConnection<HardwareInterface> hdService;
    private static final String hdServicePath = "com.pudutech.mirsdk.hardware.HardwareService";
    private static boolean isHdInterfaceInitDone = false;
    private static boolean isRbServiceConnected = false;
    private static final ServiceConnectionKt$listener$1 listener;
    private static Function3<? super HardwareOpenStep, ? super StepState, ? super String, Unit> onHardwareOpenDone = null;
    private static RobotInterface rbInterface = null;
    private static final String rbSericePath = "com.pudutech.bumblebee.robot.RobotService";
    private static final AIDLConnection<RobotInterface> rbService;

    public static final String getHdServicePath() {
        return hdServicePath;
    }

    public static final String getRbSericePath() {
        return rbSericePath;
    }

    public static final HardwareInterface getHdInterface() {
        return hdInterface;
    }

    public static final void setHdInterface(HardwareInterface hardwareInterface) {
        hdInterface = hardwareInterface;
    }

    public static final RobotInterface getRbInterface() {
        return rbInterface;
    }

    public static final void setRbInterface(RobotInterface robotInterface) {
        rbInterface = robotInterface;
    }

    /* JADX WARN: Type inference failed for: r0v4, types: [com.pudutech.factory_test.ServiceConnectionKt$listener$1] */
    static {
        final ServiceConnectionKt$hdService$2 serviceConnectionKt$hdService$2 = ServiceConnectionKt$hdService$2.INSTANCE;
        final String str = hdServicePath;
        hdService = new AIDLConnection<HardwareInterface>(str, serviceConnectionKt$hdService$2) { // from class: com.pudutech.factory_test.ServiceConnectionKt$hdService$1
            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                String str2;
                ServiceConnectionKt$listener$1 serviceConnectionKt$listener$1;
                super.onServiceConnected(name, service);
                str2 = ServiceConnectionKt.TAG;
                Pdlog.m3275i(str2, "onConnected name=" + name + "  " + service);
                ServiceConnectionKt.setHdInterface(getInterface());
                HardwareInterface hdInterface2 = ServiceConnectionKt.getHdInterface();
                if (hdInterface2 != null) {
                    serviceConnectionKt$listener$1 = ServiceConnectionKt.listener;
                    hdInterface2.addListener("factory_test", serviceConnectionKt$listener$1);
                }
                HardwareInterface hdInterface3 = ServiceConnectionKt.getHdInterface();
                if (hdInterface3 != null) {
                    hdInterface3.open();
                }
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                String str2;
                super.onBindingDied(name);
                str2 = ServiceConnectionKt.TAG;
                Pdlog.m3277w(str2, "onBindingDied name=" + name);
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName name) {
                String str2;
                super.onNullBinding(name);
                str2 = ServiceConnectionKt.TAG;
                Pdlog.m3277w(str2, "onNullBinding name=" + name);
            }

            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                String str2;
                super.onServiceDisconnected(name);
                str2 = ServiceConnectionKt.TAG;
                Pdlog.m3277w(str2, "onServiceDisconnected name=" + name);
            }
        };
        final String str2 = rbSericePath;
        final ServiceConnectionKt$rbService$2 serviceConnectionKt$rbService$2 = ServiceConnectionKt$rbService$2.INSTANCE;
        rbService = new AIDLConnection<RobotInterface>(str2, serviceConnectionKt$rbService$2) { // from class: com.pudutech.factory_test.ServiceConnectionKt$rbService$1
            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceConnected(ComponentName name, IBinder service) {
                String str3;
                super.onServiceConnected(name, service);
                str3 = ServiceConnectionKt.TAG;
                Pdlog.m3275i(str3, "onConnected name=" + name + "  " + service);
                ServiceConnectionKt.setRbInterface(getInterface());
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ServiceConnectionKt$rbService$1$onServiceConnected$1(null), 2, null);
            }

            @Override // android.content.ServiceConnection
            public void onBindingDied(ComponentName name) {
                String str3;
                super.onBindingDied(name);
                str3 = ServiceConnectionKt.TAG;
                Pdlog.m3277w(str3, "onBindingDied name=" + name);
            }

            @Override // android.content.ServiceConnection
            public void onNullBinding(ComponentName name) {
                String str3;
                super.onNullBinding(name);
                str3 = ServiceConnectionKt.TAG;
                Pdlog.m3277w(str3, "onNullBinding name=" + name);
            }

            @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
            public void onServiceDisconnected(ComponentName name) {
                String str3;
                super.onServiceDisconnected(name);
                str3 = ServiceConnectionKt.TAG;
                Pdlog.m3277w(str3, "onServiceDisconnected name=" + name);
                BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ServiceConnectionKt$rbService$1$onServiceDisconnected$1(null), 2, null);
            }
        };
        listener = new IHardware.Stub() { // from class: com.pudutech.factory_test.ServiceConnectionKt$listener$1
            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onAntiCollisionSwitch(boolean p0) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onBattery(int p0, ChargeState p1) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onBatterySOH(int p0) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onCameraIRDLED(boolean p0) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onConnectedChargingPile() {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onDisinfectionPower(boolean p0) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onHarewareEmergencyBrake(boolean p0) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onHarewareInfoReport(String p0) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onSlamCorePower(boolean p0) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onWheelError(WheelError[] p0, WheelError[] p1) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onOpenStep(HardwareOpenStep p0, StepState p1, String p2) {
                if (p0 == HardwareOpenStep.Finish) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new ServiceConnectionKt$listener$1$onOpenStep$1(null), 2, null);
                }
                Function3<HardwareOpenStep, StepState, String, Unit> onHardwareOpenDone2 = ServiceConnectionKt.getOnHardwareOpenDone();
                if (onHardwareOpenDone2 != null) {
                    onHardwareOpenDone2.invoke(p0, p1, p2);
                }
            }
        };
    }

    public static final AIDLConnection<HardwareInterface> getHdService() {
        return hdService;
    }

    public static final AIDLConnection<RobotInterface> getRbService() {
        return rbService;
    }

    public static final Function3<HardwareOpenStep, StepState, String, Unit> getOnHardwareOpenDone() {
        return onHardwareOpenDone;
    }

    public static final void setOnHardwareOpenDone(Function3<? super HardwareOpenStep, ? super StepState, ? super String, Unit> function3) {
        onHardwareOpenDone = function3;
    }
}
