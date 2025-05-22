package com.pudutech.bumblebee.robot.activity;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
 */
/* compiled from: DisinfectionActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot.activity.DisinfectionActivity$disliIDisinfectionRobotListener$1$onLiquidLevelChange$1", m3970f = "DisinfectionActivity.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* renamed from: com.pudutech.bumblebee.robot.activity.DisinfectionActivity$disliIDisinfectionRobotListener$1$onLiquidLevelChange$1 */
/* loaded from: classes.dex */
final class C4154x7a36ff3a extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

    /* renamed from: $l */
    final /* synthetic */ double f4734$l;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4735p$;
    final /* synthetic */ DisinfectionActivity$disliIDisinfectionRobotListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C4154x7a36ff3a(DisinfectionActivity$disliIDisinfectionRobotListener$1 disinfectionActivity$disliIDisinfectionRobotListener$1, double d, Continuation continuation) {
        super(2, continuation);
        this.this$0 = disinfectionActivity$disliIDisinfectionRobotListener$1;
        this.f4734$l = d;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C4154x7a36ff3a c4154x7a36ff3a = new C4154x7a36ff3a(this.this$0, this.f4734$l, completion);
        c4154x7a36ff3a.f4735p$ = (CoroutineScope) obj;
        return c4154x7a36ff3a;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C4154x7a36ff3a) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        this.this$0.this$0.setLiquidLevelTv(this.f4734$l);
        return Unit.INSTANCE;
    }
}
