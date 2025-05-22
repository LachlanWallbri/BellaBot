package com.pudutech.mirsdk.hardware;

import androidx.core.app.NotificationCompat;
import com.google.logging.type.LogSeverity;
import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.SolicitService;
import com.pudutech.mirsdk.hardware.HardwareInterfaceStub;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkMagicSensor$2", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {335, 356}, m3973m = "invokeSuspend", m3974n = {"$this$launch", NotificationCompat.CATEGORY_MESSAGE, "$this$launch", NotificationCompat.CATEGORY_MESSAGE, "ret"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
final class HardwareInterfaceStub$checkMagicSensor$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HardwareInterfaceStub.SelfCheckSensorInfo $selfCheckInfo;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5904p$;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    HardwareInterfaceStub$checkMagicSensor$2(HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation) {
        super(2, continuation);
        this.$selfCheckInfo = selfCheckSensorInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$checkMagicSensor$2 hardwareInterfaceStub$checkMagicSensor$2 = new HardwareInterfaceStub$checkMagicSensor$2(this.$selfCheckInfo, completion);
        hardwareInterfaceStub$checkMagicSensor$2.f5904p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$checkMagicSensor$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$checkMagicSensor$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x011e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0119  */
    /* JADX WARN: Type inference failed for: r14v17, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r14v36, types: [T, java.lang.String] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        MachineInfoProcess machineInfoProcess;
        ThreadSafeListener threadSafeListener;
        final Ref.ObjectRef objectRef;
        ThreadSafeListener threadSafeListener2;
        final Ref.ObjectRef objectRef2;
        Boolean bool;
        ThreadSafeListener threadSafeListener3;
        ThreadSafeListener threadSafeListener4;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5904p$;
            HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess = HardwareInterfaceStub.machineInfoProcess;
            if (machineInfoProcess.getMachineInfo().getMagicSensor() == MachineInfo.MagicSensor.NODevice || !HardwareInterfaceStub.access$getEnableMagic$p(HardwareInterfaceStub.INSTANCE)) {
                Pdlog.m3273d("Hardware", "MagicSensorCheck don't support");
                this.$selfCheckInfo.onState(HardwareInterfaceStub.SelfCheckSensor.MagicSensor, true);
                return Unit.INSTANCE;
            }
            Pdlog.m3273d("Hardware", "MagicSensor is valid");
            HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).geomagneticCalibration(LogSeverity.EMERGENCY_VALUE, LogSeverity.EMERGENCY_VALUE, true);
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            objectRef3.element = "";
            HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
            threadSafeListener = HardwareInterfaceStub.hardwareListener;
            threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkMagicSensor$2.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                    invoke2(iHardware, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IHardware it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpenStep(HardwareOpenStep.MagicSensorCheck, StepState.Running, "");
                }
            });
            HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getMagneticSensor();
            HardwareInterfaceStub$checkMagicSensor$2$ret$1 hardwareInterfaceStub$checkMagicSensor$2$ret$1 = new HardwareInterfaceStub$checkMagicSensor$2$ret$1(null);
            this.L$0 = coroutineScope;
            this.L$1 = objectRef3;
            this.label = 1;
            Object withTimeoutOrNull = TimeoutKt.withTimeoutOrNull(SolicitService.CAMERA_OPEN_TIME_OUT, hardwareInterfaceStub$checkMagicSensor$2$ret$1, this);
            if (withTimeoutOrNull == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef = objectRef3;
            obj = withTimeoutOrNull;
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                objectRef2 = (Ref.ObjectRef) this.L$1;
                ResultKt.throwOnFailure(obj);
                bool = (Boolean) obj;
                if (bool == null ? bool.booleanValue() : false) {
                    objectRef2.element = "magic sensor data callback is abnormal,left is " + HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getLeftMagicSensor$mirhardware_packRelease() + " right is " + HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getLeftMagicSensor$mirhardware_packRelease();
                    StringBuilder sb = new StringBuilder();
                    sb.append("MagicSensorCheck msg is ");
                    sb.append((String) objectRef2.element);
                    Pdlog.m3273d("Hardware", sb.toString());
                    HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener4 = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener4.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkMagicSensor$2.3
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                            invoke2(iHardware, str);
                            return Unit.INSTANCE;
                        }

                        /* JADX WARN: Multi-variable type inference failed */
                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware it, String str) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            it.onOpenStep(HardwareOpenStep.MagicSensorCheck, StepState.Fail, (String) Ref.ObjectRef.this.element);
                        }
                    });
                    this.$selfCheckInfo.onState(HardwareInterfaceStub.SelfCheckSensor.MagicSensor, false);
                    HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).geomagneticCalibration(0, 0, false);
                    return Unit.INSTANCE;
                }
                HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                threadSafeListener3 = HardwareInterfaceStub.hardwareListener;
                threadSafeListener3.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkMagicSensor$2.4
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                        invoke2(iHardware, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onOpenStep(HardwareOpenStep.MagicSensorCheck, StepState.Success, "");
                    }
                });
                this.$selfCheckInfo.onState(HardwareInterfaceStub.SelfCheckSensor.MagicSensor, true);
                HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).geomagneticCalibration(0, 0, false);
                return Unit.INSTANCE;
            }
            objectRef = (Ref.ObjectRef) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        Integer num = (Integer) obj;
        if (num == null || num.intValue() != 3) {
            Pdlog.m3273d("Hardware", "MagicSensorCheck  ret =" + num);
            objectRef.element = "magic sensor count is abnormal callback is " + num;
            HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
            threadSafeListener2 = HardwareInterfaceStub.hardwareListener;
            threadSafeListener2.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkMagicSensor$2.2
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                    invoke2(iHardware, str);
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IHardware it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpenStep(HardwareOpenStep.MagicSensorCheck, StepState.Fail, (String) Ref.ObjectRef.this.element);
                }
            });
            this.$selfCheckInfo.onState(HardwareInterfaceStub.SelfCheckSensor.MagicSensor, false);
            HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).geomagneticCalibration(0, 0, false);
            return Unit.INSTANCE;
        }
        C4992x833a2e6b c4992x833a2e6b = new C4992x833a2e6b(null);
        this.L$0 = coroutineScope;
        this.L$1 = objectRef;
        this.L$2 = num;
        this.label = 2;
        obj = TimeoutKt.withTimeoutOrNull(SolicitService.CAMERA_OPEN_TIME_OUT, c4992x833a2e6b, this);
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        objectRef2 = objectRef;
        bool = (Boolean) obj;
        if (bool == null ? bool.booleanValue() : false) {
        }
    }
}
