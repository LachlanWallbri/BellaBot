package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotMoveInterfaceDecorator.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/mirsdkwrap/lib/move/RobotMoveInterfaceDecorator$stopFakeStatus$1$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class RobotMoveInterfaceDecorator$stopFakeStatus$$inlined$let$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Job $it;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6613p$;
    final /* synthetic */ RobotMoveInterfaceDecorator this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotMoveInterfaceDecorator$stopFakeStatus$$inlined$let$lambda$1(Job job, Continuation continuation, RobotMoveInterfaceDecorator robotMoveInterfaceDecorator) {
        super(2, continuation);
        this.$it = job;
        this.this$0 = robotMoveInterfaceDecorator;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotMoveInterfaceDecorator$stopFakeStatus$$inlined$let$lambda$1 robotMoveInterfaceDecorator$stopFakeStatus$$inlined$let$lambda$1 = new RobotMoveInterfaceDecorator$stopFakeStatus$$inlined$let$lambda$1(this.$it, completion, this.this$0);
        robotMoveInterfaceDecorator$stopFakeStatus$$inlined$let$lambda$1.f6613p$ = (CoroutineScope) obj;
        return robotMoveInterfaceDecorator$stopFakeStatus$$inlined$let$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotMoveInterfaceDecorator$stopFakeStatus$$inlined$let$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6613p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "stopFakeStatus : join start");
            Job job = this.$it;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (job.join(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "stopFakeStatus : join end");
        return Unit.INSTANCE;
    }
}
