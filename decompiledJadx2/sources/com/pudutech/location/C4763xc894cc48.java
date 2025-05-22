package com.pudutech.location;

import com.pudutech.base.Pdlog;
import com.pudutech.location.net.FlowCardRes;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: FlowCardManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/location/FlowCardManager$activateFlowCardSingle$1$2$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.location.FlowCardManager$activateFlowCardSingle$1$invokeSuspend$$inlined$onSuccess$lambda$1 */
/* loaded from: classes5.dex */
final class C4763xc894cc48 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FlowCardRes $it;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5484p$;
    final /* synthetic */ FlowCardManager$activateFlowCardSingle$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4763xc894cc48(FlowCardRes flowCardRes, Continuation continuation, FlowCardManager$activateFlowCardSingle$1 flowCardManager$activateFlowCardSingle$1) {
        super(2, continuation);
        this.$it = flowCardRes;
        this.this$0 = flowCardManager$activateFlowCardSingle$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4763xc894cc48 c4763xc894cc48 = new C4763xc894cc48(this.$it, completion, this.this$0);
        c4763xc894cc48.f5484p$ = (CoroutineScope) obj;
        return c4763xc894cc48;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4763xc894cc48) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
        CoroutineScope coroutineScope = this.f5484p$;
        FlowCardRes flowCardRes = this.$it;
        if (flowCardRes == null || flowCardRes.getCode() != 0) {
            str = this.this$0.this$0.TAG;
            Pdlog.m3273d(str, "activateFlowCardSingle onSuccess failure  = " + this.$it);
            this.this$0.$callback.invoke(Boxing.boxBoolean(false));
        } else {
            str2 = this.this$0.this$0.TAG;
            Pdlog.m3273d(str2, "activateFlowCardSingle onSuccess  = " + this.$it);
            this.this$0.$callback.invoke(Boxing.boxBoolean(true));
        }
        return Unit.INSTANCE;
    }
}
