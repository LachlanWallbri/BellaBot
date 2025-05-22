package com.pudutech.location;

import com.pudutech.base.Pdlog;
import com.pudutech.location.net.FlowCardStatusRes;
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
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/location/FlowCardManager$getStatusFlowCardSingle$1$2$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.location.FlowCardManager$getStatusFlowCardSingle$1$invokeSuspend$$inlined$onSuccess$lambda$1 */
/* loaded from: classes5.dex */
final class C4765x81513bb3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCardStatusRes $it;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5486p$;
    final /* synthetic */ FlowCardManager$getStatusFlowCardSingle$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4765x81513bb3(FlowCardStatusRes flowCardStatusRes, Continuation continuation, FlowCardManager$getStatusFlowCardSingle$1 flowCardManager$getStatusFlowCardSingle$1) {
        super(2, continuation);
        this.$it = flowCardStatusRes;
        this.this$0 = flowCardManager$getStatusFlowCardSingle$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4765x81513bb3 c4765x81513bb3 = new C4765x81513bb3(this.$it, completion, this.this$0);
        c4765x81513bb3.f5486p$ = (CoroutineScope) obj;
        return c4765x81513bb3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4765x81513bb3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5486p$;
        FlowCardStatusRes flowCardStatusRes = this.$it;
        if (flowCardStatusRes == null || flowCardStatusRes.getCode() != 0) {
            str = this.this$0.this$0.TAG;
            Pdlog.m3273d(str, "getStatusFlowCardSingle onSuccess failure  = " + this.$it);
            this.this$0.$callback.invoke(null);
        } else {
            str2 = this.this$0.this$0.TAG;
            Pdlog.m3273d(str2, "getStatusFlowCardSingle onSuccess  = " + this.$it);
            this.this$0.this$0.printStatusInfo(this.$it);
            this.this$0.$callback.invoke(this.$it);
        }
        return Unit.INSTANCE;
    }
}
