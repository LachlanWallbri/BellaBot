package com.pudutech.factory_test.single_test;

import com.pudutech.factory_test.single_test.AtomizationModuleTestActivity;
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
import kotlinx.coroutines.DelayKt;

/* compiled from: AtomizationModuleTestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.AtomizationModuleTestActivity$runTesting2$1", m3970f = "AtomizationModuleTestActivity.kt", m3971i = {0}, m3972l = {179}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes.dex */
final class AtomizationModuleTestActivity$runTesting2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5167p$;
    final /* synthetic */ AtomizationModuleTestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AtomizationModuleTestActivity$runTesting2$1(AtomizationModuleTestActivity atomizationModuleTestActivity, Continuation continuation) {
        super(2, continuation);
        this.this$0 = atomizationModuleTestActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AtomizationModuleTestActivity$runTesting2$1 atomizationModuleTestActivity$runTesting2$1 = new AtomizationModuleTestActivity$runTesting2$1(this.this$0, completion);
        atomizationModuleTestActivity$runTesting2$1.f5167p$ = (CoroutineScope) obj;
        return atomizationModuleTestActivity$runTesting2$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AtomizationModuleTestActivity$runTesting2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.f5167p$;
            this.label = 1;
            if (DelayKt.delay(10000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (this.this$0.getSprayChamberLevel() > 0.0d) {
            this.this$0.setStep(AtomizationModuleTestActivity.Step.SUCCESS);
        } else {
            this.this$0.setStep(AtomizationModuleTestActivity.Step.FAIL);
            this.this$0.getMTestItem().setFailDescription("雾化室液位高度" + this.this$0.getSprayChamberLevel() + " （单位mm）\n检测失败");
        }
        this.this$0.FSM();
        return Unit.INSTANCE;
    }
}
