package com.pudutech.mirsdk;

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
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$lockWheel$2", m3970f = "MoveAction.kt", m3971i = {0, 0, 1, 1}, m3972l = {660, 661}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking", "$this$apply", "$this$runBlocking", "$this$apply"}, m3975s = {"L$0", "L$2", "L$0", "L$2"})
/* loaded from: classes5.dex */
public final class MoveAction$lockWheel$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5540p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$lockWheel$2(MoveAction moveAction, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$lockWheel$2 moveAction$lockWheel$2 = new MoveAction$lockWheel$2(this.this$0, completion);
        moveAction$lockWheel$2.f5540p$ = (CoroutineScope) obj;
        return moveAction$lockWheel$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$lockWheel$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0066 A[RETURN] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Job job;
        Job job2;
        MoveAction moveAction;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5540p$;
            job = this.this$0.lockWheelJob;
            if (job != null) {
                this.L$0 = coroutineScope;
                this.L$1 = job;
                this.L$2 = job;
                this.label = 1;
                if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                job2 = job;
                moveAction = this.this$0;
                this.L$0 = coroutineScope;
                this.L$1 = job;
                this.L$2 = job2;
                this.label = 2;
                if (moveAction.controlWheelCmd(false, this) == coroutine_suspended) {
                }
            }
        } else if (i == 1) {
            Job job3 = (Job) this.L$2;
            Job job4 = (Job) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            job2 = job3;
            job = job4;
            moveAction = this.this$0;
            this.L$0 = coroutineScope;
            this.L$1 = job;
            this.L$2 = job2;
            this.label = 2;
            if (moveAction.controlWheelCmd(false, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        this.this$0.lockWheelJob = (Job) null;
        return Unit.INSTANCE;
    }
}
