package com.pudutech.mirsdk.hardware.can;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.hardware.IHardware;
import kotlin.Metadata;
import kotlin.ResultKt;
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
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: CANBus.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.hardware.can.CANBus$lockMotor$2", m3970f = "CANBus.kt", m3971i = {0}, m3972l = {962}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class CANBus$lockMotor$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $isLock;
    final /* synthetic */ Ref.IntRef $time;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6030p$;
    final /* synthetic */ CANBus this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    CANBus$lockMotor$2(CANBus cANBus, Ref.IntRef intRef, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = cANBus;
        this.$time = intRef;
        this.$isLock = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        CANBus$lockMotor$2 cANBus$lockMotor$2 = new CANBus$lockMotor$2(this.this$0, this.$time, this.$isLock, completion);
        cANBus$lockMotor$2.f6030p$ = (CoroutineScope) obj;
        return cANBus$lockMotor$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((CANBus$lockMotor$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6030p$;
            CANBus$lockMotor$2$result$1 cANBus$lockMotor$2$result$1 = new CANBus$lockMotor$2$result$1(this, null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = TimeoutKt.withTimeoutOrNull(6000L, cANBus$lockMotor$2$result$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        Boolean bool = (Boolean) obj;
        if (Intrinsics.areEqual(bool, Boxing.boxBoolean(false))) {
            str2 = this.this$0.TAG;
            Pdlog.m3273d(str2, "send LockMotor timeout ");
            this.this$0.getHardwareListener().notify(new Function2<IHardware, String, Unit>() { // from class: com.pudutech.mirsdk.hardware.can.CANBus$lockMotor$2.1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(IHardware iHardware, String str3) {
                    invoke2(iHardware, str3);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(IHardware l, String str3) {
                    Intrinsics.checkParameterIsNotNull(l, "l");
                    Intrinsics.checkParameterIsNotNull(str3, "<anonymous parameter 1>");
                    l.onLockMotorStatus(false);
                }
            });
        } else {
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "send LockMotor timeout " + bool);
        }
        return Unit.INSTANCE;
    }
}
