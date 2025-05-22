package com.pudutech.robot.peripherals.disinfection;

import com.pudutech.bumblebee.robot.Constans;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: DisinfectRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/robot/peripherals/disinfection/DisinfectRobotPeripherals$closeDevice$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class DisinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7310p$;
    final /* synthetic */ DisinfectRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$1(Continuation continuation, DisinfectRobotPeripherals disinfectRobotPeripherals) {
        super(2, continuation);
        this.this$0 = disinfectRobotPeripherals;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DisinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$1 disinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$1 = new DisinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$1(completion, this.this$0);
        disinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$1.f7310p$ = (CoroutineScope) obj;
        return disinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DisinfectRobotPeripherals$closeDevice$$inlined$forEach$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7310p$;
            byte[] bArr = {Constans.CAN_REV_SPRAY_SYS_RESULT, 7, 2, 0, 0, 0, 0};
            DisinfectRobotPeripherals disinfectRobotPeripherals = this.this$0;
            this.L$0 = coroutineScope;
            this.L$1 = bArr;
            this.label = 1;
            if (disinfectRobotPeripherals.m4476sendDatab7CxX8A(bArr, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
