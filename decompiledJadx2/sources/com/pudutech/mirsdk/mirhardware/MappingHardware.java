package com.pudutech.mirsdk.mirhardware;

import android.content.ComponentName;
import android.os.IBinder;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.freeinstall_ui.manager.AbnormalManager;
import com.pudutech.mirsdk.base.Event;
import com.pudutech.mirsdk.hardware.CameraInterface;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.IMarkerCameraState;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import com.pudutech.mirsdk.mirhardware.MappingHardware;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

/* compiled from: MappingHardware.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0002\u0011\u0014\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u001c\u0010\u001c\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!H\u0016J\u0012\u0010\"\u001a\u00020\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082D¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0010\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0012R\u0010\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0018\u001a\u00020\u0019¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006#"}, m3961d2 = {"Lcom/pudutech/mirsdk/mirhardware/MappingHardware;", "Lcom/pudutech/base/architecture/AIDLConnection;", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "()V", "TAG", "", "errorEvent", "Lcom/pudutech/mirsdk/base/Event;", "getErrorEvent", "()Lcom/pudutech/mirsdk/base/Event;", "faceCameraFlag", "", "getFaceCameraFlag", "()Z", "setFaceCameraFlag", "(Z)V", "faceCameraStateListener", "com/pudutech/mirsdk/mirhardware/MappingHardware$faceCameraStateListener$1", "Lcom/pudutech/mirsdk/mirhardware/MappingHardware$faceCameraStateListener$1;", "iHardware", "com/pudutech/mirsdk/mirhardware/MappingHardware$iHardware$1", "Lcom/pudutech/mirsdk/mirhardware/MappingHardware$iHardware$1;", "openStep", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "robotStatus", "Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "getRobotStatus", "()Lcom/pudutech/mirsdk/mirhardware/RobotStatus;", "onServiceConnected", "", "name", "Landroid/content/ComponentName;", "service", "Landroid/os/IBinder;", "onServiceDisconnected", "MirFunction_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class MappingHardware extends AIDLConnection<HardwareInterface> {
    private final String TAG;
    private final Event<String> errorEvent;
    private boolean faceCameraFlag;
    private MappingHardware$faceCameraStateListener$1 faceCameraStateListener;
    private MappingHardware$iHardware$1 iHardware;
    private StepState openStep;
    private final RobotStatus robotStatus;

    @Metadata(m3959bv = {1, 0, 3}, m3962k = 3, m3963mv = {1, 1, 16})
    /* loaded from: classes4.dex */
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = new int[StepState.values().length];
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            $EnumSwitchMapping$0[StepState.Success.ordinal()] = 1;
            $EnumSwitchMapping$0[StepState.Fail.ordinal()] = 2;
            $EnumSwitchMapping$1 = new int[HardwareOpenStep.values().length];
            $EnumSwitchMapping$1[HardwareOpenStep.Finish.ordinal()] = 1;
        }
    }

    /* compiled from: MappingHardware.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012,\u0010\u0003\u001a( \u0002*\u0013\u0018\u00010\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u00070\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, m3961d2 = {"<anonymous>", "Lcom/pudutech/mirsdk/hardware/HardwareInterface;", "kotlin.jvm.PlatformType", "p1", "Landroid/os/IBinder;", "Lkotlin/ParameterName;", "name", "p0", "invoke"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.mirhardware.MappingHardware$1 */
    /* loaded from: classes4.dex */
    static final /* synthetic */ class C52741 extends FunctionReference implements Function1<IBinder, HardwareInterface> {
        public static final C52741 INSTANCE = new C52741();

        C52741() {
            super(1);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return "asInterface";
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(HardwareInterface.Stub.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "asInterface(Landroid/os/IBinder;)Lcom/pudutech/mirsdk/hardware/HardwareInterface;";
        }

        @Override // kotlin.jvm.functions.Function1
        public final HardwareInterface invoke(IBinder iBinder) {
            return HardwareInterface.Stub.asInterface(iBinder);
        }
    }

    /* JADX WARN: Type inference failed for: r0v5, types: [com.pudutech.mirsdk.mirhardware.MappingHardware$iHardware$1] */
    /* JADX WARN: Type inference failed for: r0v6, types: [com.pudutech.mirsdk.mirhardware.MappingHardware$faceCameraStateListener$1] */
    public MappingHardware() {
        super("com.pudutech.mirsdk.hardware.HardwareService", C52741.INSTANCE, null, 4, null);
        this.TAG = "MappingHardware";
        this.openStep = StepState.Idle;
        this.errorEvent = new Event<>();
        this.robotStatus = new RobotStatus();
        this.iHardware = new IHardware.Stub() { // from class: com.pudutech.mirsdk.mirhardware.MappingHardware$iHardware$1
            public void geomagneticAntiDrop(String p0, int p1, int p2, int p3) {
            }

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

            public void onChargingFail() {
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

            public void onLockMotorStatus(boolean p0) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onSlamCorePower(boolean p0) {
            }

            public void securityFeedback(boolean p0) {
            }

            @Override // com.pudutech.mirsdk.hardware.IHardware
            public void onOpenStep(HardwareOpenStep step, StepState stepState, String description) {
                CameraInterface camera;
                MappingHardware$faceCameraStateListener$1 mappingHardware$faceCameraStateListener$1;
                if (step == null || MappingHardware.WhenMappings.$EnumSwitchMapping$1[step.ordinal()] != 1 || stepState == null) {
                    return;
                }
                int i = MappingHardware.WhenMappings.$EnumSwitchMapping$0[stepState.ordinal()];
                if (i != 1) {
                    if (i != 2) {
                        return;
                    }
                    MappingHardware.this.openStep = StepState.Fail;
                    return;
                }
                MappingHardware.this.openStep = StepState.Success;
                HardwareInterface hardwareInterface = MappingHardware.this.getInterface();
                if (hardwareInterface == null || (camera = hardwareInterface.getCamera()) == null) {
                    return;
                }
                mappingHardware$faceCameraStateListener$1 = MappingHardware.this.faceCameraStateListener;
                camera.addMonocularCameraStateListener("Mapmonocular", mappingHardware$faceCameraStateListener$1);
            }

            /* JADX WARN: Code restructure failed: missing block: B:12:0x0033, code lost:
            
                if ((!(r5.length == 0)) == false) goto L19;
             */
            /* JADX WARN: Code restructure failed: missing block: B:14:0x0054, code lost:
            
                if (r4.this$0.getRobotStatus().getEmergencyKeyPressed().getValue().booleanValue() == false) goto L29;
             */
            /* JADX WARN: Code restructure failed: missing block: B:15:0x0056, code lost:
            
                r4.this$0.getErrorEvent().emit(com.pudutech.freeinstall_ui.manager.AbnormalManager.TYPE_EMERGENCYBRAKE);
                r5 = r4.this$0.TAG;
                com.pudutech.base.Pdlog.m3273d(r5, "errorss is WheelError EmergencyBrake ");
             */
            /* JADX WARN: Code restructure failed: missing block: B:16:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:18:0x0071, code lost:
            
                r4.this$0.getErrorEvent().emit(com.pudutech.freeinstall_ui.manager.AbnormalManager.TYPE_WHEELERROR);
                r5 = r4.this$0.TAG;
                com.pudutech.base.Pdlog.m3273d(r5, "errorss is WheelError WheelError");
             */
            /* JADX WARN: Code restructure failed: missing block: B:19:?, code lost:
            
                return;
             */
            /* JADX WARN: Code restructure failed: missing block: B:26:0x003e, code lost:
            
                if ((!(r6.length == 0)) != false) goto L26;
             */
            @Override // com.pudutech.mirsdk.hardware.IHardware
            /*
                Code decompiled incorrectly, please refer to instructions dump.
            */
            public void onWheelError(WheelError[] left, WheelError[] rigtht) {
                MappingHardware.this.getRobotStatus().getEmergencyKeyPressed().setValue(Boolean.valueOf((left != null && ArraysKt.contains(left, WheelError.EmergencyKeyPressed)) || (rigtht != null && ArraysKt.contains(rigtht, WheelError.EmergencyKeyPressed))));
                if (left != null) {
                }
                if (rigtht != null) {
                }
                MappingHardware.this.getErrorEvent().emit(AbnormalManager.TYPE_CLEARERROR);
            }
        };
        this.faceCameraStateListener = new IMarkerCameraState.Stub() { // from class: com.pudutech.mirsdk.mirhardware.MappingHardware$faceCameraStateListener$1
            @Override // com.pudutech.mirsdk.hardware.IMarkerCameraState
            public void onCameraFrameTick() {
            }

            @Override // com.pudutech.mirsdk.hardware.IMarkerCameraState
            public void onOpened(boolean p0) {
                String str;
                str = MappingHardware.this.TAG;
                Pdlog.m3273d(str, "faceCameraStateListener camera open " + p0);
                MappingHardware.this.setFaceCameraFlag(p0);
            }
        };
    }

    public final Event<String> getErrorEvent() {
        return this.errorEvent;
    }

    public final boolean getFaceCameraFlag() {
        return this.faceCameraFlag;
    }

    public final void setFaceCameraFlag(boolean z) {
        this.faceCameraFlag = z;
    }

    public final RobotStatus getRobotStatus() {
        return this.robotStatus;
    }

    @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
    public void onServiceDisconnected(ComponentName name) {
        super.onServiceDisconnected(name);
        HardwareInterface hardwareInterface = getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.removeListener("mappingfunction");
        }
    }

    @Override // com.pudutech.base.architecture.AIDLConnection, android.content.ServiceConnection
    public void onServiceConnected(ComponentName name, IBinder service) {
        super.onServiceConnected(name, service);
        HardwareInterface hardwareInterface = getInterface();
        if (hardwareInterface != null) {
            hardwareInterface.addListener("mappingfunction", this.iHardware);
        }
    }
}
