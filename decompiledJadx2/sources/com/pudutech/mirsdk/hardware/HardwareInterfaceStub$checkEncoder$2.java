package com.pudutech.mirsdk.hardware;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import org.apache.http.HttpStatus;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkEncoder$2", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0, 0, 0, 0, 0, 0}, m3972l = {HttpStatus.SC_GONE}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "encoderPulsePerCircle", "reductionRatio", "encoderSampleTimesPerPulse", "machineWheelPerimeter", "wheelSpacing", "checkLoop"}, m3975s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0"})
/* loaded from: classes.dex */
public final class HardwareInterfaceStub$checkEncoder$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HardwareInterfaceStub.SelfCheckSensorInfo $selfCheckSensorInfo;
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5901p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareInterfaceStub$checkEncoder$2(HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation) {
        super(2, continuation);
        this.$selfCheckSensorInfo = selfCheckSensorInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$checkEncoder$2 hardwareInterfaceStub$checkEncoder$2 = new HardwareInterfaceStub$checkEncoder$2(this.$selfCheckSensorInfo, completion);
        hardwareInterfaceStub$checkEncoder$2.f5901p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$checkEncoder$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$checkEncoder$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0136  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x015c -> B:5:0x015f). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        MachineInfoProcess machineInfoProcess;
        MachineInfoProcess machineInfoProcess2;
        MachineInfoProcess machineInfoProcess3;
        Float f;
        MachineInfoProcess machineInfoProcess4;
        MachineInfoProcess machineInfoProcess5;
        String str;
        String str2;
        ThreadSafeListener threadSafeListener;
        HardwareInterfaceStub$checkEncoder$2 hardwareInterfaceStub$checkEncoder$2;
        CoroutineScope coroutineScope;
        int i;
        Float f2;
        Float f3;
        Float f4;
        Float f5;
        String str3;
        ThreadSafeListener threadSafeListener2;
        String str4;
        ThreadSafeListener threadSafeListener3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5901p$;
            HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess = HardwareInterfaceStub.machineInfoProcess;
            Float f6 = machineInfoProcess.getMachineInfo().getFloat(MachineInfo.FloatInfo.encoderPulsePerCircle);
            HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess2 = HardwareInterfaceStub.machineInfoProcess;
            Float f7 = machineInfoProcess2.getMachineInfo().getFloat(MachineInfo.FloatInfo.reductionRatio);
            HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess3 = HardwareInterfaceStub.machineInfoProcess;
            f = machineInfoProcess3.getMachineInfo().getFloat(MachineInfo.FloatInfo.encoderSampleTimesPerPulse);
            HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess4 = HardwareInterfaceStub.machineInfoProcess;
            Float f8 = machineInfoProcess4.getMachineInfo().getFloat(MachineInfo.FloatInfo.machineWheelPerimeter);
            HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
            machineInfoProcess5 = HardwareInterfaceStub.machineInfoProcess;
            Float f9 = machineInfoProcess5.getMachineInfo().getFloat(MachineInfo.FloatInfo.machineWheelbase);
            HardwareInterfaceStub hardwareInterfaceStub6 = HardwareInterfaceStub.INSTANCE;
            str = HardwareInterfaceStub.TAG;
            Pdlog.m3275i(str, "set encoder configure from machine info, encoderPulsePerCircle:" + f6 + " reductionRatio:" + f7 + " encoderSampleTimesPerPulse:" + f + " machineWheelPerimeter:" + f8 + " wheelSpacing:" + f9);
            if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                return Unit.INSTANCE;
            }
            if (f6 != null && f7 != null && f != null && f8 != null && f9 != null) {
                HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getEncoder().setConfigure(f6.floatValue(), f7.floatValue(), f.floatValue(), f8.floatValue(), f9.floatValue());
            } else {
                HardwareInterfaceStub hardwareInterfaceStub7 = HardwareInterfaceStub.INSTANCE;
                str2 = HardwareInterfaceStub.TAG;
                Pdlog.m3274e(str2, "encoder configure set fail");
            }
            HardwareInterfaceStub hardwareInterfaceStub8 = HardwareInterfaceStub.INSTANCE;
            threadSafeListener = HardwareInterfaceStub.hardwareListener;
            threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkEncoder$2.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str5) {
                    invoke2(iHardware, str5);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IHardware l, String str5) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                    l.onOpenStep(HardwareOpenStep.EncoderCheck, StepState.Running, "");
                }
            });
            if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                return Unit.INSTANCE;
            }
            hardwareInterfaceStub$checkEncoder$2 = this;
            coroutineScope = coroutineScope2;
            i = 0;
            f2 = f6;
            f3 = f9;
            f4 = f7;
            f5 = f8;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i2 == 1) {
            i = this.I$0;
            f3 = (Float) this.L$5;
            f5 = (Float) this.L$4;
            f = (Float) this.L$3;
            f4 = (Float) this.L$2;
            f2 = (Float) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            hardwareInterfaceStub$checkEncoder$2 = this;
            i += 20;
            if (!CoroutineScopeKt.isActive(coroutineScope) && !HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getReceivedEncoder() && i < 5000) {
                hardwareInterfaceStub$checkEncoder$2.L$0 = coroutineScope;
                hardwareInterfaceStub$checkEncoder$2.L$1 = f2;
                hardwareInterfaceStub$checkEncoder$2.L$2 = f4;
                hardwareInterfaceStub$checkEncoder$2.L$3 = f;
                hardwareInterfaceStub$checkEncoder$2.L$4 = f5;
                hardwareInterfaceStub$checkEncoder$2.L$5 = f3;
                hardwareInterfaceStub$checkEncoder$2.I$0 = i;
                hardwareInterfaceStub$checkEncoder$2.label = 1;
                if (DelayKt.delay(20L, hardwareInterfaceStub$checkEncoder$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i += 20;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                }
            } else {
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    return Unit.INSTANCE;
                }
                if (HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getReceivedEncoder()) {
                    HardwareInterfaceStub hardwareInterfaceStub9 = HardwareInterfaceStub.INSTANCE;
                    str4 = HardwareInterfaceStub.TAG;
                    Pdlog.m3275i(str4, "read encoder data success");
                    HardwareInterfaceStub hardwareInterfaceStub10 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener3 = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener3.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkEncoder$2.2
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str5) {
                            invoke2(iHardware, str5);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str5) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.EncoderCheck, StepState.Success, "");
                        }
                    });
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo = hardwareInterfaceStub$checkEncoder$2.$selfCheckSensorInfo;
                    if (selfCheckSensorInfo != null) {
                        selfCheckSensorInfo.onState(HardwareInterfaceStub.SelfCheckSensor.Encoder, true);
                    }
                } else {
                    HardwareInterfaceStub hardwareInterfaceStub11 = HardwareInterfaceStub.INSTANCE;
                    str3 = HardwareInterfaceStub.TAG;
                    Pdlog.m3275i(str3, "wait encoder timeout");
                    HardwareInterfaceStub hardwareInterfaceStub12 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener2 = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener2.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkEncoder$2.3
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str5) {
                            invoke2(iHardware, str5);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str5) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str5, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.EncoderCheck, StepState.Fail, "wait encoder timeout");
                        }
                    });
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo2 = hardwareInterfaceStub$checkEncoder$2.$selfCheckSensorInfo;
                    if (selfCheckSensorInfo2 != null) {
                        selfCheckSensorInfo2.onState(HardwareInterfaceStub.SelfCheckSensor.Encoder, false);
                    }
                }
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
