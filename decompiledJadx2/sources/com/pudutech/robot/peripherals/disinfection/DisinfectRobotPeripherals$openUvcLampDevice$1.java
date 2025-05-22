package com.pudutech.robot.peripherals.disinfection;

import com.pudutech.bumblebee.robot.Constans;
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

/* compiled from: DisinfectRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.disinfection.DisinfectRobotPeripherals$openUvcLampDevice$1", m3970f = "DisinfectRobotPeripherals.kt", m3971i = {0, 0}, m3972l = {197}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "data"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes6.dex */
final class DisinfectRobotPeripherals$openUvcLampDevice$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $open;
    final /* synthetic */ boolean $openUvc;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7338p$;
    final /* synthetic */ DisinfectRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DisinfectRobotPeripherals$openUvcLampDevice$1(DisinfectRobotPeripherals disinfectRobotPeripherals, boolean z, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = disinfectRobotPeripherals;
        this.$open = z;
        this.$openUvc = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        DisinfectRobotPeripherals$openUvcLampDevice$1 disinfectRobotPeripherals$openUvcLampDevice$1 = new DisinfectRobotPeripherals$openUvcLampDevice$1(this.this$0, this.$open, this.$openUvc, completion);
        disinfectRobotPeripherals$openUvcLampDevice$1.f7338p$ = (CoroutineScope) obj;
        return disinfectRobotPeripherals$openUvcLampDevice$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DisinfectRobotPeripherals$openUvcLampDevice$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7338p$;
            byte[] bArr = {Constans.CAN_REV_UV_SYS_RESULT, 8, 2, this.$open, this.$openUvc, 0, 0};
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
