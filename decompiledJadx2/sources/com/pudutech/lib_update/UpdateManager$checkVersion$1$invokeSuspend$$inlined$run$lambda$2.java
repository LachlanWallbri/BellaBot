package com.pudutech.lib_update;

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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/lib_update/UpdateManager$checkVersion$1$1$2"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes5.dex */
public final class UpdateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5410p$;
    final /* synthetic */ UpdateManager$checkVersion$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2(Continuation continuation, UpdateManager$checkVersion$1 updateManager$checkVersion$1) {
        super(2, continuation);
        this.this$0 = updateManager$checkVersion$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2 updateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2 = new UpdateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2(completion, this.this$0);
        updateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2.f5410p$ = (CoroutineScope) obj;
        return updateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5410p$;
        Function1 function1 = this.this$0.$onError;
        if (function1 != null) {
            return (Unit) function1.invoke(new Exception("获取不到新版本"));
        }
        return null;
    }
}
