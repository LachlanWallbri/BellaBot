package com.pudutech.schedulerlib.connection;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* compiled from: PeanutEspService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/schedulerlib/connection/PeanutEspService$initEsp$1$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.schedulerlib.connection.PeanutEspService$initEsp$1$invokeSuspend$$inlined$fixedRateTimer$1$lambda$1 */
/* loaded from: classes7.dex */
final class C5730x997a0652 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7443p$;
    final /* synthetic */ C5729x2b574326 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C5730x997a0652(Continuation continuation, C5729x2b574326 c5729x2b574326) {
        super(2, continuation);
        this.this$0 = c5729x2b574326;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C5730x997a0652 c5730x997a0652 = new C5730x997a0652(completion, this.this$0);
        c5730x997a0652.f7443p$ = (CoroutineScope) obj;
        return c5730x997a0652;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C5730x997a0652) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7443p$;
            PeanutEspService.INSTANCE.unInitEsp();
            this.L$0 = coroutineScope;
            this.label = 1;
            if (DelayKt.delay(1000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        PeanutEspService.access$initEasyNode(PeanutEspService.INSTANCE, this.this$0.this$0.$callback, this.this$0.this$0.$context);
        return Unit.INSTANCE;
    }
}
