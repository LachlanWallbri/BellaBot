package com.pudutech.mirsdk.hardware.can;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IHardware;
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
import kotlinx.coroutines.DelayKt;

/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$getMagneticSensor$2", m3970f = "CANBus.kt", m3971i = {0, 1}, m3972l = {936, 938}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes5.dex */
final class CANBus$getMagneticSensor$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $time;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6028p$;
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$getMagneticSensor$2(CANBus cANBus, Ref.IntRef intRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cANBus;
        this.$time = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CANBus$getMagneticSensor$2 cANBus$getMagneticSensor$2 = new CANBus$getMagneticSensor$2(this.this$0, this.$time, completion);
        cANBus$getMagneticSensor$2.f6028p$ = (CoroutineScope) obj;
        return cANBus$getMagneticSensor$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CANBus$getMagneticSensor$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CANBus$getMagneticSensor$2 cANBus$getMagneticSensor$2;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6028p$;
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    cANBus$getMagneticSensor$2 = this;
                    cANBus$getMagneticSensor$2.this$0.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$getMagneticSensor$2.1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                            invoke2(iHardware, str2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(IHardware l, String str2) {
                            Intrinsics.checkParameterIsNotNull(l, "l");
                            Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                            l.sensormagnetic(0);
                        }
                    });
                    cANBus$getMagneticSensor$2.this$0.setMagicSensorCount$mirhardware_packRelease(0);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        cANBus$getMagneticSensor$2 = this;
        do {
            Ref.IntRef intRef = cANBus$getMagneticSensor$2.$time;
            int i2 = intRef.element;
            intRef.element = i2 - 1;
            if (i2 > 0) {
                str = cANBus$getMagneticSensor$2.this$0.TAG;
                Pdlog.m3273d(str, "send sensorMagneticJob time is " + cANBus$getMagneticSensor$2.$time.element);
                cANBus$getMagneticSensor$2.this$0.m4425sendGBYM_sE(new byte[]{-81, 1, 0, 0, 0, 0, 0});
                cANBus$getMagneticSensor$2.L$0 = coroutineScope;
                cANBus$getMagneticSensor$2.label = 1;
            } else {
                cANBus$getMagneticSensor$2.L$0 = coroutineScope;
                cANBus$getMagneticSensor$2.label = 2;
                if (DelayKt.delay(500L, cANBus$getMagneticSensor$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                cANBus$getMagneticSensor$2.this$0.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$getMagneticSensor$2.1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str2) {
                        invoke2(iHardware, str2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(IHardware l, String str2) {
                        Intrinsics.checkParameterIsNotNull(l, "l");
                        Intrinsics.checkParameterIsNotNull(str2, "<anonymous parameter 1>");
                        l.sensormagnetic(0);
                    }
                });
                cANBus$getMagneticSensor$2.this$0.setMagicSensorCount$mirhardware_packRelease(0);
                return Unit.INSTANCE;
            }
        } while (DelayKt.delay(500L, cANBus$getMagneticSensor$2) != coroutine_suspended);
        return coroutine_suspended;
    }
}
