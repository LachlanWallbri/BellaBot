package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.mirsdkwrap.lib.move.bean.MoveDestinationTask;
import com.pudutech.mirsdkwrap.lib.move.bean.MoveReportData;
import java.util.ArrayList;
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
/* compiled from: MoveByDestination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/mirsdkwrap/lib/move/MoveByDestination$cancelAll$1$list$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.mirsdkwrap.lib.move.MoveByDestination$cancelAll$1$invokeSuspend$$inlined$map$lambda$1 */
/* loaded from: classes6.dex */
final class C5338x8911cb2b extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MoveDestinationTask $it;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6554p$;
    final /* synthetic */ MoveByDestination$cancelAll$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C5338x8911cb2b(MoveDestinationTask moveDestinationTask, Continuation continuation, MoveByDestination$cancelAll$1 moveByDestination$cancelAll$1) {
        super(2, continuation);
        this.$it = moveDestinationTask;
        this.this$0 = moveByDestination$cancelAll$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C5338x8911cb2b c5338x8911cb2b = new C5338x8911cb2b(this.$it, completion, this.this$0);
        c5338x8911cb2b.f6554p$ = (CoroutineScope) obj;
        return c5338x8911cb2b;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C5338x8911cb2b) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        ArrayList arrayList;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6554p$;
        arrayList = this.this$0.this$0.moveReport;
        arrayList.add(new MoveReportData(this.$it.getDestination().getName(), true, 0L, 0, 0L, 0.0d, 0.0d, 0L, 252, null));
        return Unit.INSTANCE;
    }
}
