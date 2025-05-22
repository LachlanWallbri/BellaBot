package com.pudutech.mirsdk.hardware.activity;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.hardware.Encoder;
import com.pudutech.mirsdk.hardware.Gyroscope;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.HardwareScopeKt;
import com.pudutech.mirsdk.hardware.ICANData;
import com.pudutech.mirsdk.hardware.IHardware;
import com.pudutech.mirsdk.hardware.ISensorData;
import com.pudutech.mirsdk.hardware.base.CommonKt;
import com.pudutech.mirsdk.hardware.serialize.ChargeState;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.mirsdk.hardware.serialize.WheelError;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import org.mozilla.javascript.ES6Iterator;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes.dex
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.activity.HardwareActivity$connectService$1", m3970f = "HardwareActivity.kt", m3971i = {0}, m3972l = {176}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes2.dex */
public final class HardwareActivity$connectService$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5944p$;
    final /* synthetic */ HardwareActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareActivity$connectService$1(HardwareActivity hardwareActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = hardwareActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareActivity$connectService$1 hardwareActivity$connectService$1 = new HardwareActivity$connectService$1(this.this$0, completion);
        hardwareActivity$connectService$1.f5944p$ = (CoroutineScope) obj;
        return hardwareActivity$connectService$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareActivity$connectService$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        HardwareConnection hardwareConnection;
        String str2;
        HardwareConnection hardwareConnection2;
        HardwareConnection hardwareConnection3;
        HardwareConnection hardwareConnection4;
        String str3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5944p$;
            str = this.this$0.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append('[');
            Thread currentThread = Thread.currentThread();
            Intrinsics.checkExpressionValueIsNotNull(currentThread, "Thread.currentThread()");
            sb.append(currentThread.getName());
            sb.append(']');
            Pdlog.m3273d(str, sb.toString());
            hardwareConnection = this.this$0.hardware;
            HardwareActivity hardwareActivity = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = AIDLConnection.connect$default(hardwareConnection, hardwareActivity, null, this, 2, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (!((Boolean) obj).booleanValue()) {
            str3 = this.this$0.TAG;
            Pdlog.m3275i(str3, "connect service fail");
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$connectService$1.1
                @Override // java.lang.Runnable
                public final void run() {
                    HardwareActivity$connectService$1.this.this$0.showState("connect hardware service fail");
                }
            });
        } else {
            str2 = this.this$0.TAG;
            Pdlog.m3275i(str2, "connect service success");
            hardwareConnection2 = this.this$0.hardware;
            HardwareInterface hardwareInterface = hardwareConnection2.getInterface();
            if (hardwareInterface != null) {
                hardwareInterface.addCANDataListener("hardware_app", null, new ICANData.Stub() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$connectService$1.2
                    @Override // com.pudutech.mirsdk.hardware.ICANData
                    public void onData(int p0, byte[] data) {
                        String str4;
                        str4 = HardwareActivity$connectService$1.this.this$0.TAG;
                        Object[] objArr = new Object[1];
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("on can data:");
                        sb2.append(data != null ? CommonKt.toHexString(data) : null);
                        objArr[0] = sb2.toString();
                        Pdlog.m3276v(str4, objArr);
                    }
                });
            }
            hardwareConnection3 = this.this$0.hardware;
            HardwareInterface hardwareInterface2 = hardwareConnection3.getInterface();
            if (hardwareInterface2 != null) {
                hardwareInterface2.addListener("hardware_app", new BinderC50833());
            }
            hardwareConnection4 = this.this$0.hardware;
            HardwareInterface hardwareInterface3 = hardwareConnection4.getInterface();
            if (hardwareInterface3 != null) {
                hardwareInterface3.addSensorListener("Debug", new BinderC50844());
            }
            this.this$0.runOnUiThread(new Runnable() { // from class: com.pudutech.mirsdk.hardware.activity.HardwareActivity$connectService$1.5
                @Override // java.lang.Runnable
                public final void run() {
                    HardwareActivity$connectService$1.this.this$0.showHardwareVersion();
                    HardwareActivity$connectService$1.this.this$0.showMachineInfoPreview();
                    HardwareActivity$connectService$1.this.this$0.setupLidarListener();
                    HardwareActivity$connectService$1.this.this$0.setupMarkerCameraListener();
                    HardwareActivity$connectService$1.this.this$0.setupRGBDListener();
                }
            });
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000G\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\u001a\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\b\u0010\u000e\u001a\u00020\u0003H\u0016J\u0010\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0005H\u0016J\u0012\u0010\u0013\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J$\u0010\u0016\u001a\u00020\u00032\b\u0010\u0017\u001a\u0004\u0018\u00010\u00182\b\u0010\u0012\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u0015H\u0016J\u0010\u0010\u001b\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0005H\u0016J1\u0010\u001c\u001a\u00020\u00032\u0010\u0010\u0007\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001e\u0018\u00010\u001d2\u0010\u0010\t\u001a\f\u0012\u0006\b\u0001\u0012\u00020\u001e\u0018\u00010\u001dH\u0016¢\u0006\u0002\u0010\u001f¨\u0006 "}, m3961d2 = {"com/pudutech/mirsdk/hardware/activity/HardwareActivity$connectService$1$3", "Lcom/pudutech/mirsdk/hardware/IHardware$Stub;", "onAntiCollisionSwitch", "", "pressed", "", "onBattery", "p0", "", "p1", "Lcom/pudutech/mirsdk/hardware/serialize/ChargeState;", "onBatterySOH", ES6Iterator.VALUE_PROPERTY, "onCameraIRDLED", "onConnectedChargingPile", "onDisinfectionPower", "powerOn", "onHarewareEmergencyBrake", "state", "onHarewareInfoReport", "info", "", "onOpenStep", "step", "Lcom/pudutech/mirsdk/hardware/serialize/HardwareOpenStep;", "Lcom/pudutech/mirsdk/hardware/serialize/StepState;", "description", "onSlamCorePower", "onWheelError", "", "Lcom/pudutech/mirsdk/hardware/serialize/WheelError;", "([Lcom/pudutech/mirsdk/hardware/serialize/WheelError;[Lcom/pudutech/mirsdk/hardware/serialize/WheelError;)V", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.activity.HardwareActivity$connectService$1$3 */
    /* loaded from: classes2.dex */
    public static final class BinderC50833 extends IHardware.Stub {
        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onAntiCollisionSwitch(boolean pressed) {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onConnectedChargingPile() {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onHarewareInfoReport(String info) {
        }

        BinderC50833() {
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onWheelError(WheelError[] p0, WheelError[] p1) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$3$onWheelError$1(this, p0, p1, null), 2, null);
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onOpenStep(HardwareOpenStep step, StepState state, String description) {
            Intrinsics.checkParameterIsNotNull(description, "description");
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$3$onOpenStep$1(this, step, state, null), 2, null);
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onCameraIRDLED(boolean p0) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$3$onCameraIRDLED$1(this, p0, null), 2, null);
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onBattery(int p0, ChargeState p1) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$3$onBattery$1(this, p0, p1, null), 2, null);
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onHarewareEmergencyBrake(boolean state) {
            String str;
            str = HardwareActivity$connectService$1.this.this$0.TAG;
            Pdlog.m3275i(str, "onHarewareEmergencyBrake:" + state);
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$3$onHarewareEmergencyBrake$1(this, null), 2, null);
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), null, null, new HardwareActivity$connectService$1$3$onHarewareEmergencyBrake$2(this, null), 3, null);
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onDisinfectionPower(boolean powerOn) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$3$onDisinfectionPower$1(this, powerOn, null), 2, null);
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onBatterySOH(int value) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$3$onBatterySOH$1(this, value, null), 2, null);
        }

        @Override // com.pudutech.mirsdk.hardware.IHardware
        public void onSlamCorePower(boolean powerOn) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$3$onSlamCorePower$1(this, powerOn, null), 2, null);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
      classes.dex
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareActivity.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\t*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J(\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016J \u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u0005H\u0016¨\u0006\u000e"}, m3961d2 = {"com/pudutech/mirsdk/hardware/activity/HardwareActivity$connectService$1$4", "Lcom/pudutech/mirsdk/hardware/ISensorData$Stub;", "onEncoder", "", "p0", "", "p1", "p2", "onIMU", "p3", "onSpeed", "lineSpeed", "angularSpeed", "interval", "mirhardware_packRelease"}, m3962k = 1, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.activity.HardwareActivity$connectService$1$4 */
    /* loaded from: classes2.dex */
    public static final class BinderC50844 extends ISensorData.Stub {
        BinderC50844() {
        }

        @Override // com.pudutech.mirsdk.hardware.ISensorData
        public void onSpeed(double lineSpeed, double angularSpeed, double interval) {
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$4$onSpeed$1(this, lineSpeed, angularSpeed, null), 2, null);
        }

        @Override // com.pudutech.mirsdk.hardware.ISensorData
        public void onIMU(double p0, double p1, double p2, double p3) {
            Gyroscope.Data data;
            Gyroscope.Data data2;
            Gyroscope.Data data3;
            data = HardwareActivity$connectService$1.this.this$0.gyroAccumulate;
            data.setX(data.getX() + (p0 * 0.025d));
            data2 = HardwareActivity$connectService$1.this.this$0.gyroAccumulate;
            data2.setY(data2.getY() + (p1 * 0.025d));
            data3 = HardwareActivity$connectService$1.this.this$0.gyroAccumulate;
            data3.setZ(data3.getZ() + (0.025d * p2));
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$4$onIMU$1(this, p0, p1, p2, null), 2, null);
        }

        @Override // com.pudutech.mirsdk.hardware.ISensorData
        public void onEncoder(double p0, double p1, double p2) {
            Encoder.DoubleWheel doubleWheel;
            Encoder.DoubleWheel doubleWheel2;
            doubleWheel = HardwareActivity$connectService$1.this.this$0.encoderAccumulate;
            doubleWheel.setLeft(doubleWheel.getLeft() + p0);
            doubleWheel2 = HardwareActivity$connectService$1.this.this$0.encoderAccumulate;
            doubleWheel2.setRight(doubleWheel2.getRight() + p1);
            BuildersKt__Builders_commonKt.launch$default(HardwareScopeKt.getHardwareScope(), Dispatchers.getMain(), null, new HardwareActivity$connectService$1$4$onEncoder$1(this, p0, p1, null), 2, null);
        }
    }
}
