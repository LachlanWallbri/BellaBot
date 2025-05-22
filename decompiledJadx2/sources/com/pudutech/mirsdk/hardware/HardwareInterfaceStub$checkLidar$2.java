package com.pudutech.mirsdk.hardware;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.HardwareInterfaceStub;
import com.pudutech.mirsdk.hardware.ILidarState;
import com.pudutech.mirsdk.hardware.can.CANBus;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.LidarStopReason;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByteArray;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import org.eclipse.paho.android.service.MqttServiceConstants;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkLidar$2", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 0, 0, 0, 0, 0}, m3972l = {496}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "lidarVersion", "lidarOpenStep", "listener_name", "errorDescription", "timeout", "checkLoop"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "J$0", "I$0"})
/* loaded from: classes.dex */
public final class HardwareInterfaceStub$checkLidar$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HardwareInterfaceStub.SelfCheckSensorInfo $selfCheckSensorInfo;
    int I$0;
    long J$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5903p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareInterfaceStub$checkLidar$2(HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation) {
        super(2, continuation);
        this.$selfCheckSensorInfo = selfCheckSensorInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$checkLidar$2 hardwareInterfaceStub$checkLidar$2 = new HardwareInterfaceStub$checkLidar$2(this.$selfCheckSensorInfo, completion);
        hardwareInterfaceStub$checkLidar$2.f5903p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$checkLidar$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$checkLidar$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
      classes4.dex
      classes5.dex
     */
    /* compiled from: HardwareInterfaceStub.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0004\b\u0007\u0010\b"}, m3961d2 = {"<anonymous>", "", "p1", "Lkotlin/UByteArray;", "Lkotlin/ParameterName;", "name", "bytes", "invoke", "([B)V"}, m3962k = 3, m3963mv = {1, 1, 16})
    /* renamed from: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkLidar$2$1 */
    /* loaded from: classes.dex */
    public static final /* synthetic */ class C49841 extends FunctionReference implements Function1<UByteArray, Unit> {
        C49841(CANBus cANBus) {
            super(1, cANBus);
        }

        @Override // kotlin.jvm.internal.CallableReference, kotlin.reflect.KCallable
        public final String getName() {
            return MqttServiceConstants.SEND_ACTION;
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final KDeclarationContainer getOwner() {
            return Reflection.getOrCreateKotlinClass(CANBus.class);
        }

        @Override // kotlin.jvm.internal.CallableReference
        public final String getSignature() {
            return "send-GBYM_sE([B)V";
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(UByteArray uByteArray) {
            invoke(uByteArray.getStorage());
            return Unit.INSTANCE;
        }

        public final void invoke(byte[] p1) {
            Intrinsics.checkParameterIsNotNull(p1, "p1");
            ((CANBus) this.receiver).m4425sendGBYM_sE(p1);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x011e  */
    /* JADX WARN: Type inference failed for: r7v3, types: [com.pudutech.mirsdk.hardware.serialize.StepState, T] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0141 -> B:5:0x0144). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        MachineInfoProcess machineInfoProcess;
        MachineInfoProcess machineInfoProcess2;
        ThreadSafeListener threadSafeListener;
        MachineInfoProcess machineInfoProcess3;
        HardwareInterfaceStub$checkLidar$2 hardwareInterfaceStub$checkLidar$2;
        CoroutineScope coroutineScope;
        int i;
        Integer num;
        final Ref.ObjectRef objectRef;
        long j;
        String str;
        final Ref.ObjectRef objectRef2;
        String str2;
        ThreadSafeListener threadSafeListener2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5903p$;
            LidarInterfaceImpl access$getLidarInterfaceImpl$p = HardwareInterfaceStub.access$getLidarInterfaceImpl$p(HardwareInterfaceStub.INSTANCE);
            HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess = HardwareInterfaceStub.machineInfoProcess;
            access$getLidarInterfaceImpl$p.setMachineType(machineInfoProcess.getMachineInfo().getProductType());
            HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess2 = HardwareInterfaceStub.machineInfoProcess;
            Integer num2 = machineInfoProcess2.getMachineInfo().getInt(MachineInfo.IntInfo.ldsSensorVersion);
            if (num2 == null) {
                HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                str2 = HardwareInterfaceStub.TAG;
                Pdlog.m3274e(str2, "lidar version get from machine info fail");
                HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo = this.$selfCheckSensorInfo;
                if (selfCheckSensorInfo != null) {
                    selfCheckSensorInfo.onState(HardwareInterfaceStub.SelfCheckSensor.Lidar, false);
                }
                return Unit.INSTANCE;
            }
            HardwareInterfaceStub.access$getLidarInterfaceImpl$p(HardwareInterfaceStub.INSTANCE).init(num2.intValue(), new C49841(HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE)));
            HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
            threadSafeListener = HardwareInterfaceStub.hardwareListener;
            threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkLidar$2.2
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str3) {
                    invoke2(iHardware, str3);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IHardware l, String str3) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                    l.onOpenStep(HardwareOpenStep.LidarCheck, StepState.Running, "");
                }
            });
            final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            objectRef3.element = StepState.Running;
            final Ref.ObjectRef objectRef4 = new Ref.ObjectRef();
            objectRef4.element = "";
            HardwareInterfaceStub.access$getLidarInterfaceImpl$p(HardwareInterfaceStub.INSTANCE).addStateListener("self_check_temp", new ILidarState.Stub() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkLidar$2.3
                @Override // com.pudutech.mirsdk.hardware.ILidarState
                public void onFrameTick() {
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // com.pudutech.mirsdk.hardware.ILidarState
                public void onStartScan(boolean result, String description) {
                    String str3;
                    Intrinsics.checkParameterIsNotNull(description, "description");
                    HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
                    str3 = HardwareInterfaceStub.TAG;
                    Pdlog.m3275i(str3, "lidarInterface onStartScan " + result);
                    Ref.ObjectRef.this.element = result ? StepState.Success : StepState.Fail;
                    objectRef4.element = description;
                }

                @Override // com.pudutech.mirsdk.hardware.ILidarState
                public void onStopScan(LidarStopReason reason) {
                    String str3;
                    HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
                    str3 = HardwareInterfaceStub.TAG;
                    Pdlog.m3277w(str3, "lidarInterface onStopScan " + reason);
                }

                @Override // com.pudutech.mirsdk.hardware.ILidarState
                public void onError(String errorMsg) {
                    String str3;
                    HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
                    str3 = HardwareInterfaceStub.TAG;
                    Pdlog.m3277w(str3, "lidarInterface onError: " + errorMsg);
                }
            });
            HardwareInterfaceStub.access$getLidarInterfaceImpl$p(HardwareInterfaceStub.INSTANCE).open();
            if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                HardwareInterfaceStub.access$getLidarInterfaceImpl$p(HardwareInterfaceStub.INSTANCE).removeStateListener("self_check_temp");
                return Unit.INSTANCE;
            }
            long j2 = 30000;
            HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess3 = HardwareInterfaceStub.machineInfoProcess;
            Integer num3 = machineInfoProcess3.getMachineInfo().getInt(MachineInfo.IntInfo.ldsSensorVersion);
            if (num3 != null && num3.intValue() == 16) {
                j2 = HardwareConfig.SlamLidarOpenTimeOut;
            }
            if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                return Unit.INSTANCE;
            }
            hardwareInterfaceStub$checkLidar$2 = this;
            coroutineScope = coroutineScope2;
            i = 0;
            long j3 = j2;
            num = num2;
            objectRef = objectRef3;
            j = j3;
            str = "self_check_temp";
            objectRef2 = objectRef4;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
            HardwareInterfaceStub.INSTANCE.getLidarInterface().removeStateListener(str);
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i2 == 1) {
            i = this.I$0;
            j = this.J$0;
            objectRef2 = (Ref.ObjectRef) this.L$4;
            str = (String) this.L$3;
            objectRef = (Ref.ObjectRef) this.L$2;
            num = (Integer) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            hardwareInterfaceStub$checkLidar$2 = this;
            i += 10;
            if (!CoroutineScopeKt.isActive(coroutineScope) && ((StepState) objectRef.element) == StepState.Running && i < j) {
                hardwareInterfaceStub$checkLidar$2.L$0 = coroutineScope;
                hardwareInterfaceStub$checkLidar$2.L$1 = num;
                hardwareInterfaceStub$checkLidar$2.L$2 = objectRef;
                hardwareInterfaceStub$checkLidar$2.L$3 = str;
                hardwareInterfaceStub$checkLidar$2.L$4 = objectRef2;
                hardwareInterfaceStub$checkLidar$2.J$0 = j;
                hardwareInterfaceStub$checkLidar$2.I$0 = i;
                hardwareInterfaceStub$checkLidar$2.label = 1;
                if (DelayKt.delay(10L, hardwareInterfaceStub$checkLidar$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i += 10;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
                HardwareInterfaceStub.INSTANCE.getLidarInterface().removeStateListener(str);
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
            } else {
                HardwareInterfaceStub.INSTANCE.getLidarInterface().removeStateListener(str);
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
                HardwareInterfaceStub hardwareInterfaceStub6 = HardwareInterfaceStub.INSTANCE;
                threadSafeListener2 = HardwareInterfaceStub.hardwareListener;
                threadSafeListener2.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkLidar$2.4
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str3) {
                        invoke2(iHardware, str3);
                        return Unit.INSTANCE;
                    }

                    /* JADX WARN: Multi-variable type inference failed */
                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str3) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                        l.onOpenStep(HardwareOpenStep.LidarCheck, (StepState) Ref.ObjectRef.this.element, (String) objectRef2.element);
                    }
                });
                HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo2 = hardwareInterfaceStub$checkLidar$2.$selfCheckSensorInfo;
                if (selfCheckSensorInfo2 != null) {
                    selfCheckSensorInfo2.onState(HardwareInterfaceStub.SelfCheckSensor.Lidar, ((StepState) objectRef.element) == StepState.Success);
                }
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
