package com.pudutech.pd_network.verify;

import com.pudutech.pd_network.sn.SNComponent;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotVerifyExecute.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.verify.RobotVerifyExecute$resetSN$1", m3970f = "RobotVerifyExecute.kt", m3971i = {0}, m3972l = {110}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class RobotVerifyExecute$resetSN$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6876p$;
    final /* synthetic */ RobotVerifyExecute this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotVerifyExecute$resetSN$1(RobotVerifyExecute robotVerifyExecute, Continuation continuation) {
        super(2, continuation);
        this.this$0 = robotVerifyExecute;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotVerifyExecute$resetSN$1 robotVerifyExecute$resetSN$1 = new RobotVerifyExecute$resetSN$1(this.this$0, completion);
        robotVerifyExecute$resetSN$1.f6876p$ = (CoroutineScope) obj;
        return robotVerifyExecute$resetSN$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotVerifyExecute$resetSN$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RobotVerifyExecute robotVerifyExecute;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6876p$;
            RobotVerifyExecute robotVerifyExecute2 = this.this$0;
            SNComponent sNComponent = SNComponent.INSTANCE;
            this.L$0 = coroutineScope;
            this.L$1 = robotVerifyExecute2;
            this.label = 1;
            obj = sNComponent.mo3303sn(this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            robotVerifyExecute = robotVerifyExecute2;
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            robotVerifyExecute = (RobotVerifyExecute) this.L$1;
            ResultKt.throwOnFailure(obj);
        }
        robotVerifyExecute.mSN = (String) obj;
        return Unit.INSTANCE;
    }
}
