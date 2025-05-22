package com.pudutech.mirsdk.hardware;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.ThreadSafeListener;
import com.pudutech.mirsdk.hardware.HardwareInterfaceStub;
import com.pudutech.mirsdk.hardware.serialize.HardwareOpenStep;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
  classes5.dex
 */
/* compiled from: HardwareInterfaceStub.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkIMU$2", m3970f = "HardwareInterfaceStub.kt", m3971i = {0, 0}, m3972l = {337}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "checkLoop"}, m3975s = {"L$0", "I$0"})
/* loaded from: classes.dex */
public final class HardwareInterfaceStub$checkIMU$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HardwareInterfaceStub.SelfCheckSensorInfo $selfCheckSensorInfo;
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5902p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public HardwareInterfaceStub$checkIMU$2(HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo, Continuation continuation) {
        super(2, continuation);
        this.$selfCheckSensorInfo = selfCheckSensorInfo;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        HardwareInterfaceStub$checkIMU$2 hardwareInterfaceStub$checkIMU$2 = new HardwareInterfaceStub$checkIMU$2(this.$selfCheckSensorInfo, completion);
        hardwareInterfaceStub$checkIMU$2.f5902p$ = (CoroutineScope) obj;
        return hardwareInterfaceStub$checkIMU$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((HardwareInterfaceStub$checkIMU$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0065  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003a  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0056 -> B:5:0x0059). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        ThreadSafeListener threadSafeListener;
        CoroutineScope coroutineScope;
        int i;
        HardwareInterfaceStub$checkIMU$2 hardwareInterfaceStub$checkIMU$2;
        String str;
        ThreadSafeListener threadSafeListener2;
        String str2;
        ThreadSafeListener threadSafeListener3;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5902p$;
            HardwareInterfaceStub hardwareInterfaceStub = HardwareInterfaceStub.INSTANCE;
            threadSafeListener = HardwareInterfaceStub.hardwareListener;
            threadSafeListener.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkIMU$2.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str3) {
                    invoke2(iHardware, str3);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IHardware l, String str3) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                    l.onOpenStep(HardwareOpenStep.IMUCheck, StepState.Running, "");
                }
            });
            coroutineScope = coroutineScope2;
            i = 0;
            hardwareInterfaceStub$checkIMU$2 = this;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        } else if (i2 == 1) {
            i = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            hardwareInterfaceStub$checkIMU$2 = this;
            i += 20;
            if (!CoroutineScopeKt.isActive(coroutineScope) && !HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getReceivedIMU() && i < 5000) {
                hardwareInterfaceStub$checkIMU$2.L$0 = coroutineScope;
                hardwareInterfaceStub$checkIMU$2.I$0 = i;
                hardwareInterfaceStub$checkIMU$2.label = 1;
                if (DelayKt.delay(20L, hardwareInterfaceStub$checkIMU$2) == coroutine_suspended) {
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
                if (HardwareInterfaceStub.access$getCanBus$p(HardwareInterfaceStub.INSTANCE).getReceivedIMU()) {
                    HardwareInterfaceStub hardwareInterfaceStub2 = HardwareInterfaceStub.INSTANCE;
                    str2 = HardwareInterfaceStub.TAG;
                    Pdlog.m3275i(str2, "read imu data success");
                    HardwareInterfaceStub hardwareInterfaceStub3 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener3 = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener3.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkIMU$2.2
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str3) {
                            invoke2(iHardware, str3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str3) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.IMUCheck, StepState.Success, "");
                        }
                    });
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo = hardwareInterfaceStub$checkIMU$2.$selfCheckSensorInfo;
                    if (selfCheckSensorInfo != null) {
                        selfCheckSensorInfo.onState(HardwareInterfaceStub.SelfCheckSensor.IMU, true);
                    }
                } else {
                    HardwareInterfaceStub hardwareInterfaceStub4 = HardwareInterfaceStub.INSTANCE;
                    str = HardwareInterfaceStub.TAG;
                    Pdlog.m3274e(str, "wait imu timeout");
                    HardwareInterfaceStub hardwareInterfaceStub5 = HardwareInterfaceStub.INSTANCE;
                    threadSafeListener2 = HardwareInterfaceStub.hardwareListener;
                    threadSafeListener2.notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.HardwareInterfaceStub$checkIMU$2.3
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str3) {
                            invoke2(iHardware, str3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str3) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                            l.onOpenStep(HardwareOpenStep.IMUCheck, StepState.Fail, "wait imu timeout");
                        }
                    });
                    HardwareInterfaceStub.SelfCheckSensorInfo selfCheckSensorInfo2 = hardwareInterfaceStub$checkIMU$2.$selfCheckSensorInfo;
                    if (selfCheckSensorInfo2 != null) {
                        selfCheckSensorInfo2.onState(HardwareInterfaceStub.SelfCheckSensor.IMU, false);
                    }
                }
                return Unit.INSTANCE;
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
