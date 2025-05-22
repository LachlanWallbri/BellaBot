package com.pudutech.mirsdk.hardware;

import androidx.core.app.NotificationCompat;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.HardwareInterfaceStub;
import com.pudutech.mirsdk.hardware.RGBDInterfaceImpl;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
import com.pudutech.mirsdk.hardware.serialize.MachineInfo;
import com.pudutech.mirsdk.hardware.serialize.StepState;
import com.pudutech.rgbdlib.RGBDSensor;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {627, 697}, m3973m = "invokeSuspend", m3974n = {"$this$launch", NotificationCompat.CATEGORY_MESSAGE, "$this$launch", "description", "checkLoop"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "I$0"})
/* loaded from: classes.dex */
public final class HardwareInterfaceStub$checkRGBD$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HardwareInterfaceStub.SelfCheckSensorInfo $selfCheckSensorInfo;
    int I$0;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5907p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareInterfaceStub$checkRGBD$2(HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation) {
        super(2, continuation);
        this.$selfCheckSensorInfo = selfCheckSensorInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$checkRGBD$2 hardwareInterfaceStub$checkRGBD$2 = new HardwareInterfaceStub$checkRGBD$2(this.$selfCheckSensorInfo, completion);
        hardwareInterfaceStub$checkRGBD$2.f5907p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$checkRGBD$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$checkRGBD$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:20:0x01c5  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x015f  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0192  */
    /* JADX WARN: Type inference failed for: r0v20, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r0v24, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v48, types: [T, java.lang.String] */
    /* JADX WARN: Type inference failed for: r12v75, types: [T, java.lang.String] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x01b6 -> B:6:0x01b9). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        ThreadSafeListener threadSafeListener;
        MachineInfoProcess machineInfoProcess;
        final Ref.ObjectRef objectRef;
        ThreadSafeListener threadSafeListener2;
        ThreadSafeListener threadSafeListener3;
        MachineInfoProcess machineInfoProcess2;
        UByte uByte;
        HardwareInterfaceStub$checkRGBD$2 hardwareInterfaceStub$checkRGBD$2;
        final Ref.ObjectRef objectRef2;
        CoroutineScope coroutineScope2;
        int i;
        ThreadSafeListener threadSafeListener4;
        MachineInfoProcess machineInfoProcess3;
        ThreadSafeListener threadSafeListener5;
        ThreadSafeListener threadSafeListener6;
        ThreadSafeListener threadSafeListener7;
        ThreadSafeListener threadSafeListener8;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5907p$;
            HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
            threadSafeListener = HardwareInterfaceStub.hardwareListener;
            threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                    invoke2(iHardware, str);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IHardware it, String str) {
                    Intrinsics.checkParameterIsNotNull(it, "it");
                    Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                    it.onOpenStep(HardwareOpenStep.RGBDConnectCheck, StepState.Running, "");
                }
            });
            HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess = HardwareInterfaceStub.machineInfoProcess;
            UByte uByte2 = machineInfoProcess.getMachineInfo().getByte23Data().get(MachineInfo.Byte23Info.RGBDVersion);
            if (uByte2 == null ? false : UByte.m4535equalsimpl0(uByte2.getData(), MachineInfo.RGBDType.NODevice.getId())) {
                HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                threadSafeListener2 = HardwareInterfaceStub.hardwareListener;
                threadSafeListener2.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2.2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                        invoke2(iHardware, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onOpenStep(HardwareOpenStep.RGBDConnectCheck, StepState.Success, "");
                    }
                });
                HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                threadSafeListener3 = HardwareInterfaceStub.hardwareListener;
                threadSafeListener3.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2.6
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                        invoke2(iHardware, str);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware it, String str) {
                        Intrinsics.checkParameterIsNotNull(it, "it");
                        Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                        it.onOpenStep(HardwareOpenStep.RGBDDataCheck, StepState.Running, "");
                    }
                });
                HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
                machineInfoProcess2 = HardwareInterfaceStub.machineInfoProcess;
                uByte = machineInfoProcess2.getMachineInfo().getByte23Data().get(MachineInfo.Byte23Info.RGBDVersion);
                if (!(uByte != null ? false : UByte.m4535equalsimpl0(uByte.getData(), MachineInfo.RGBDType.NODevice.getId()))) {
                    HardwareInterfaceStub hardwareInterfaceStub6 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener4 = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener4.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2.7
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                            invoke2(iHardware, str);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware it, String str) {
                            Intrinsics.checkParameterIsNotNull(it, "it");
                            Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                            it.onOpenStep(HardwareOpenStep.RGBDDataCheck, StepState.Success, "");
                        }
                    });
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo = this.$selfCheckSensorInfo;
                    if (selfCheckSensorInfo != null) {
                        selfCheckSensorInfo.onState(HardwareInterfaceStub.SelfCheckSensor.RGBD, true);
                    }
                    return Unit.INSTANCE;
                }
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
                Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
                objectRef3.element = "";
                hardwareInterfaceStub$checkRGBD$2 = this;
                objectRef2 = objectRef3;
                coroutineScope2 = coroutineScope;
                i = 0;
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                }
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                }
            } else {
                objectRef = new Ref.ObjectRef();
                objectRef.element = "";
                HardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1 hardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1 = new HardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1(null);
                this.L$0 = coroutineScope;
                this.L$1 = objectRef;
                this.label = 1;
                obj = TimeoutKt.withTimeoutOrNull(5000L, hardwareInterfaceStub$checkRGBD$2$checkRgbdInitResult$1, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i2 == 1) {
            objectRef = (Ref.ObjectRef) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else if (i2 == 2) {
            int i3 = this.I$0;
            objectRef2 = (Ref.ObjectRef) this.L$1;
            coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            HardwareInterfaceStub$checkRGBD$2 hardwareInterfaceStub$checkRGBD$22 = this;
            int i4 = i3 + 20;
            hardwareInterfaceStub$checkRGBD$2 = hardwareInterfaceStub$checkRGBD$22;
            i = i4;
            if (!CoroutineScopeKt.isActive(coroutineScope2) && !HardwareInterfaceStub.access$getRgbdInterfaceImpl$p(HardwareInterfaceStub.INSTANCE).isEnabled() && i < 30000) {
                hardwareInterfaceStub$checkRGBD$2.L$0 = coroutineScope2;
                hardwareInterfaceStub$checkRGBD$2.L$1 = objectRef2;
                hardwareInterfaceStub$checkRGBD$2.I$0 = i;
                hardwareInterfaceStub$checkRGBD$2.label = 2;
                if (DelayKt.delay(20L, hardwareInterfaceStub$checkRGBD$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                HardwareInterfaceStub$checkRGBD$2 hardwareInterfaceStub$checkRGBD$23 = hardwareInterfaceStub$checkRGBD$2;
                i3 = i;
                hardwareInterfaceStub$checkRGBD$22 = hardwareInterfaceStub$checkRGBD$23;
                int i42 = i3 + 20;
                hardwareInterfaceStub$checkRGBD$2 = hardwareInterfaceStub$checkRGBD$22;
                i = i42;
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                }
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                }
            } else {
                if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                    return Unit.INSTANCE;
                }
                if (!HardwareInterfaceStub.access$getRgbdInterfaceImpl$p(HardwareInterfaceStub.INSTANCE).isEnabled()) {
                    objectRef2.element = "rgbd data check fail,";
                    RGBDInterfaceImpl.RGBDDevicesStatus rgbdDevicesStatus = HardwareInterfaceStub.access$getRgbdInterfaceImpl$p(HardwareInterfaceStub.INSTANCE).getRgbdDevicesStatus();
                    if (!rgbdDevicesStatus.getLeftRGBDStatus()) {
                        objectRef2.element = ((String) objectRef2.element) + "[left no data]";
                    }
                    if (!rgbdDevicesStatus.getRightRGBDStatus()) {
                        objectRef2.element = ((String) objectRef2.element) + "[right no data]";
                    }
                    if (!rgbdDevicesStatus.getCenterRGBDStatus()) {
                        objectRef2.element = ((String) objectRef2.element) + "[center no data]";
                    }
                }
                HardwareInterfaceStub hardwareInterfaceStub7 = HardwareInterfaceStub.INSTANCE;
                threadSafeListener8 = HardwareInterfaceStub.hardwareListener;
                threadSafeListener8.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2.8
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
                        it.onOpenStep(HardwareOpenStep.RGBDDataCheck, HardwareInterfaceStub.access$getRgbdInterfaceImpl$p(HardwareInterfaceStub.INSTANCE).isEnabled() ? StepState.Success : StepState.Fail, (String) Ref.ObjectRef.this.element);
                    }
                });
                HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo2 = hardwareInterfaceStub$checkRGBD$2.$selfCheckSensorInfo;
                if (selfCheckSensorInfo2 != null) {
                    selfCheckSensorInfo2.onState(HardwareInterfaceStub.SelfCheckSensor.RGBD, HardwareInterfaceStub.access$getRgbdInterfaceImpl$p(HardwareInterfaceStub.INSTANCE).isEnabled());
                }
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (!Intrinsics.areEqual((Boolean) obj, Boxing.boxBoolean(true))) {
            objectRef.element = "rgbd initialized overtime 5s";
            HardwareInterfaceStub hardwareInterfaceStub8 = HardwareInterfaceStub.INSTANCE;
            threadSafeListener7 = HardwareInterfaceStub.hardwareListener;
            threadSafeListener7.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2.3
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
                    it.onOpenStep(HardwareOpenStep.RGBDConnectCheck, StepState.Fail, (String) Ref.ObjectRef.this.element);
                }
            });
            HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo3 = this.$selfCheckSensorInfo;
            if (selfCheckSensorInfo3 != null) {
                selfCheckSensorInfo3.onState(HardwareInterfaceStub.SelfCheckSensor.RGBD, false);
            }
            return Unit.INSTANCE;
        }
        RGBDInterfaceImpl access$getRgbdInterfaceImpl$p = HardwareInterfaceStub.access$getRgbdInterfaceImpl$p(HardwareInterfaceStub.INSTANCE);
        HardwareInterfaceStub hardwareInterfaceStub9 = HardwareInterfaceStub.INSTANCE;
        machineInfoProcess3 = HardwareInterfaceStub.machineInfoProcess;
        RGBDSensor.Result start = access$getRgbdInterfaceImpl$p.start(machineInfoProcess3.getMachineInfo());
        if (!start.isSuccess()) {
            objectRef.element = "rgbd connection abnormal: " + start.getDescription();
            HardwareInterfaceStub hardwareInterfaceStub10 = HardwareInterfaceStub.INSTANCE;
            threadSafeListener6 = HardwareInterfaceStub.hardwareListener;
            threadSafeListener6.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2.4
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
                    it.onOpenStep(HardwareOpenStep.RGBDConnectCheck, StepState.Fail, (String) Ref.ObjectRef.this.element);
                }
            });
            HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo4 = this.$selfCheckSensorInfo;
            if (selfCheckSensorInfo4 != null) {
                selfCheckSensorInfo4.onState(HardwareInterfaceStub.SelfCheckSensor.RGBD, false);
            }
            return Unit.INSTANCE;
        }
        HardwareInterfaceStub hardwareInterfaceStub11 = HardwareInterfaceStub.INSTANCE;
        threadSafeListener5 = HardwareInterfaceStub.hardwareListener;
        threadSafeListener5.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2.5
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
                it.onOpenStep(HardwareOpenStep.RGBDConnectCheck, StepState.Success, (String) Ref.ObjectRef.this.element);
            }
        });
        HardwareInterfaceStub hardwareInterfaceStub42 = HardwareInterfaceStub.INSTANCE;
        threadSafeListener3 = HardwareInterfaceStub.hardwareListener;
        threadSafeListener3.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkRGBD$2.6
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str) {
                invoke2(iHardware, str);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(IHardware it, String str) {
                Intrinsics.checkParameterIsNotNull(it, "it");
                Intrinsics.checkParameterIsNotNull(str, "<anonymous parameter 1>");
                it.onOpenStep(HardwareOpenStep.RGBDDataCheck, StepState.Running, "");
            }
        });
        HardwareInterfaceStub hardwareInterfaceStub52 = HardwareInterfaceStub.INSTANCE;
        machineInfoProcess2 = HardwareInterfaceStub.machineInfoProcess;
        uByte = machineInfoProcess2.getMachineInfo().getByte23Data().get(MachineInfo.Byte23Info.RGBDVersion);
        if (!(uByte != null ? false : UByte.m4535equalsimpl0(uByte.getData(), MachineInfo.RGBDType.NODevice.getId()))) {
        }
    }
}
