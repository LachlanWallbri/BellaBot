package com.pudutech.location;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FlowCardManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/location/FlowCardManager$getStatusFlowCardSingle$1$3$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.location.FlowCardManager$getStatusFlowCardSingle$1$invokeSuspend$$inlined$onFailure$lambda$1 */
/* loaded from: classes5.dex */
final class C4764x3fc4120c extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5485p$;
    final /* synthetic */ FlowCardManager$getStatusFlowCardSingle$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4764x3fc4120c(Continuation continuation, FlowCardManager$getStatusFlowCardSingle$1 flowCardManager$getStatusFlowCardSingle$1) {
        super(2, continuation);
        this.this$0 = flowCardManager$getStatusFlowCardSingle$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4764x3fc4120c c4764x3fc4120c = new C4764x3fc4120c(completion, this.this$0);
        c4764x3fc4120c.f5485p$ = (CoroutineScope) obj;
        return c4764x3fc4120c;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4764x3fc4120c) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5485p$;
        this.this$0.$callback.invoke(null);
        return Unit.INSTANCE;
    }
}
