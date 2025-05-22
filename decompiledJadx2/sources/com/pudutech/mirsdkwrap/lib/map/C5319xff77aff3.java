package com.pudutech.mirsdkwrap.lib.map;

import com.pudutech.base.Pdlog;
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

/* compiled from: RobotMapManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/mirsdkwrap/lib/map/RobotMapManager$checkLocationSelftInit$1$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.mirsdkwrap.lib.map.RobotMapManager$checkLocationSelftInit$1$invokeSuspend$$inlined$repeat$lambda$1 */
/* loaded from: classes4.dex */
final class C5319xff77aff3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6500p$;
    final /* synthetic */ RobotMapManager$checkLocationSelftInit$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C5319xff77aff3(Continuation continuation, RobotMapManager$checkLocationSelftInit$1 robotMapManager$checkLocationSelftInit$1) {
        super(2, continuation);
        this.this$0 = robotMapManager$checkLocationSelftInit$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C5319xff77aff3 c5319xff77aff3 = new C5319xff77aff3(completion, this.this$0);
        c5319xff77aff3.f6500p$ = (CoroutineScope) obj;
        return c5319xff77aff3;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C5319xff77aff3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6500p$;
        RobotMapManager robotMapManager = RobotMapManager.INSTANCE;
        str = RobotMapManager.TAG;
        Pdlog.m3273d(str, "checkLocationInit true");
        this.this$0.$cb.invoke(Boxing.boxBoolean(true));
        return Unit.INSTANCE;
    }
}
