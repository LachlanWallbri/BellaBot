package com.pudutech.bumblebee.presenter.map_switch_task;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MapUpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/bumblebee/presenter/map_switch_task/MapUpdateManager$moveMapFileAndDelTem$1$2$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.bumblebee.presenter.map_switch_task.MapUpdateManager$moveMapFileAndDelTem$1$invokeSuspend$$inlined$forEach$lambda$1 */
/* loaded from: classes4.dex */
final class C4076xb851e1f1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4658p$;
    final /* synthetic */ MapUpdateManager$moveMapFileAndDelTem$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4076xb851e1f1(Continuation continuation, MapUpdateManager$moveMapFileAndDelTem$1 mapUpdateManager$moveMapFileAndDelTem$1) {
        super(2, continuation);
        this.this$0 = mapUpdateManager$moveMapFileAndDelTem$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4076xb851e1f1 c4076xb851e1f1 = new C4076xb851e1f1(completion, this.this$0);
        c4076xb851e1f1.f4658p$ = (CoroutineScope) obj;
        return c4076xb851e1f1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4076xb851e1f1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4658p$;
        Function1 function1 = this.this$0.$result;
        if (function1 != null) {
        }
        return Unit.INSTANCE;
    }
}
