package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.base.Pdlog;
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
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveByDestination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByDestination$updateActive$1", m3970f = "MoveByDestination.kt", m3971i = {0}, m3972l = {169}, m3973m = "invokeSuspend", m3974n = {"$this$runAsyn"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class MoveByDestination$updateActive$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6573p$;
    final /* synthetic */ MoveByDestination this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByDestination$updateActive$1(MoveByDestination moveByDestination, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveByDestination;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveByDestination$updateActive$1 moveByDestination$updateActive$1 = new MoveByDestination$updateActive$1(this.this$0, completion);
        moveByDestination$updateActive$1.f6573p$ = (CoroutineScope) obj;
        return moveByDestination$updateActive$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveByDestination$updateActive$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        RobotMoveInterfaceDecorator robotMoveInterfaceDecorator;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6573p$;
            Pdlog.m3273d(this.this$0.getTAG(), "updateActive11 " + this.this$0.getCurrentMoveState());
            switch (this.this$0.getCurrentMoveState()) {
                case Idle:
                case Arrive:
                    MoveByDestination moveByDestination = this.this$0;
                    this.L$0 = coroutineScope;
                    this.label = 1;
                    if (moveByDestination.doNextTask(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    break;
                case Moving:
                case Approaching:
                case Stuck:
                case Resume:
                case Avoid:
                    Pdlog.m3274e(this.this$0.getTAG(), "updateActive : robot is running , do not need active");
                    break;
                case Pause:
                case Error:
                    robotMoveInterfaceDecorator = this.this$0.moveInterfaceDecorator;
                    robotMoveInterfaceDecorator.resume();
                    break;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }
}
