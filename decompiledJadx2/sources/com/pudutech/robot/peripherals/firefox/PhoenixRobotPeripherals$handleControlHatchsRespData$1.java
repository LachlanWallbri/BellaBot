package com.pudutech.robot.peripherals.firefox;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.UByte;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: PhoenixRobotPeripherals.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.firefox.PhoenixRobotPeripherals$handleControlHatchsRespData$1", m3970f = "PhoenixRobotPeripherals.kt", m3971i = {0, 0}, m3972l = {295}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "responseData"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes6.dex */
final class PhoenixRobotPeripherals$handleControlHatchsRespData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ byte[] $data;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7359p$;
    final /* synthetic */ PhoenixRobotPeripherals this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PhoenixRobotPeripherals$handleControlHatchsRespData$1(PhoenixRobotPeripherals phoenixRobotPeripherals, byte[] bArr, Continuation continuation) {
        super(2, continuation);
        this.this$0 = phoenixRobotPeripherals;
        this.$data = bArr;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PhoenixRobotPeripherals$handleControlHatchsRespData$1 phoenixRobotPeripherals$handleControlHatchsRespData$1 = new PhoenixRobotPeripherals$handleControlHatchsRespData$1(this.this$0, this.$data, completion);
        phoenixRobotPeripherals$handleControlHatchsRespData$1.f7359p$ = (CoroutineScope) obj;
        return phoenixRobotPeripherals$handleControlHatchsRespData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PhoenixRobotPeripherals$handleControlHatchsRespData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7359p$;
            byte[] bArr = {UByte.m4528constructorimpl(this.$data[0]), PhoenixRobotPeripherals.INSTANCE.getCONTROL_HATCH_OPERATION_RESPONSE_CALLBACK(), UByte.m4528constructorimpl(this.$data[2]), UByte.m4528constructorimpl(this.$data[3]), UByte.m4528constructorimpl(this.$data[4]), UByte.m4528constructorimpl(this.$data[5]), UByte.m4528constructorimpl(this.$data[6]), UByte.m4528constructorimpl(this.$data[7])};
            PhoenixRobotPeripherals phoenixRobotPeripherals = this.this$0;
            this.L$0 = coroutineScope;
            this.L$1 = bArr;
            this.label = 1;
            if (phoenixRobotPeripherals.m4476sendDatab7CxX8A(bArr, this) == coroutine_suspended) {
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
