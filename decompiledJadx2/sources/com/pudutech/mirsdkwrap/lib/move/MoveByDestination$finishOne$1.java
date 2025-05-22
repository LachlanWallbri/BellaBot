package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.mirsdkwrap.lib.interf.MoveByDestinationStateListener;
import com.pudutech.mirsdkwrap.lib.move.bean.CurrentDestinationTask;
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
/* compiled from: MoveByDestination.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByDestination$finishOne$1", m3970f = "MoveByDestination.kt", m3971i = {0, 1, 2}, m3972l = {99, 101, 104}, m3973m = "invokeSuspend", m3974n = {"$this$runAsyn", "$this$runAsyn", "$this$runAsyn"}, m3975s = {"L$0", "L$0", "L$0"})
/* loaded from: classes6.dex */
public final class MoveByDestination$finishOne$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $doNext;
    final /* synthetic */ boolean $needRemove;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6565p$;
    final /* synthetic */ MoveByDestination this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveByDestination$finishOne$1(MoveByDestination moveByDestination, boolean z, boolean z2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveByDestination;
        this.$needRemove = z;
        this.$doNext = z2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveByDestination$finishOne$1 moveByDestination$finishOne$1 = new MoveByDestination$finishOne$1(this.this$0, this.$needRemove, this.$doNext, completion);
        moveByDestination$finishOne$1.f6565p$ = (CoroutineScope) obj;
        return moveByDestination$finishOne$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveByDestination$finishOne$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x007b  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CurrentDestinationTask currentDestinationTask;
        CoroutineScope coroutineScope2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6565p$;
            currentDestinationTask = this.this$0.currentTask;
            if (currentDestinationTask == null) {
                return Unit.INSTANCE;
            }
            this.this$0.currentTask = (CurrentDestinationTask) null;
            if (!this.$needRemove) {
                MoveByDestination moveByDestination = this.this$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (moveByDestination.cancelStatus(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope2 = coroutineScope;
            }
            if (this.$doNext) {
            }
            return Unit.INSTANCE;
        }
        if (i != 1) {
            if (i != 2) {
                if (i == 3) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope2;
            if (this.$doNext) {
                MoveByDestination moveByDestination2 = this.this$0;
                this.L$0 = coroutineScope;
                this.label = 3;
                if (moveByDestination2.doNextTask(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                this.this$0.runOnUi(new C53421(null));
            }
            return Unit.INSTANCE;
        }
        coroutineScope2 = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(obj);
        MoveByDestination moveByDestination3 = this.this$0;
        this.L$0 = coroutineScope2;
        this.label = 2;
        if (moveByDestination3.genMoveReport(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        coroutineScope = coroutineScope2;
        if (this.$doNext) {
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MoveByDestination.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MoveByDestination$finishOne$1$1", m3970f = "MoveByDestination.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.mirsdkwrap.lib.move.MoveByDestination$finishOne$1$1 */
    /* loaded from: classes6.dex */
    public static final class C53421 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6566p$;

        C53421(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C53421 c53421 = new C53421(completion);
            c53421.f6566p$ = (CoroutineScope) obj;
            return c53421;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C53421) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6566p$;
            MoveByDestinationStateListener onMoveStateListener = MoveByDestination$finishOne$1.this.this$0.getOnMoveStateListener();
            if (onMoveStateListener != null) {
                onMoveStateListener.onDone(null);
            }
            return Unit.INSTANCE;
        }
    }
}
