package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.mirsdkwrap.lib.interf.MoveCruiseStateListener;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveCruise.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/mirsdkwrap/lib/move/MoveCruise$onArrive$2$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
final class MoveCruise$onArrive$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $it;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6597p$;
    final /* synthetic */ MoveCruise this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveCruise$onArrive$$inlined$let$lambda$1(String str, Continuation continuation, MoveCruise moveCruise) {
        super(2, continuation);
        this.$it = str;
        this.this$0 = moveCruise;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveCruise$onArrive$$inlined$let$lambda$1 moveCruise$onArrive$$inlined$let$lambda$1 = new MoveCruise$onArrive$$inlined$let$lambda$1(this.$it, completion, this.this$0);
        moveCruise$onArrive$$inlined$let$lambda$1.f6597p$ = (CoroutineScope) obj;
        return moveCruise$onArrive$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveCruise$onArrive$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6597p$;
        MoveCruiseStateListener onMoveStateListener = this.this$0.getOnMoveStateListener();
        if (onMoveStateListener != null) {
            onMoveStateListener.onStayPointArrive(this.$it);
        }
        return Unit.INSTANCE;
    }
}
