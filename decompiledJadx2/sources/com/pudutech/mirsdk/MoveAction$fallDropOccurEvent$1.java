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
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$fallDropOccurEvent$1", m3970f = "MoveAction.kt", m3971i = {0}, m3972l = {984}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class MoveAction$fallDropOccurEvent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5529p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$fallDropOccurEvent$1(MoveAction moveAction, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$fallDropOccurEvent$1 moveAction$fallDropOccurEvent$1 = new MoveAction$fallDropOccurEvent$1(this.this$0, completion);
        moveAction$fallDropOccurEvent$1.f5529p$ = (CoroutineScope) obj;
        return moveAction$fallDropOccurEvent$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$fallDropOccurEvent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* compiled from: MoveAction.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$fallDropOccurEvent$1$1", m3970f = "MoveAction.kt", m3971i = {0, 1}, m3972l = {824, 825}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
    /* renamed from: com.pudutech.mirsdk.MoveAction$fallDropOccurEvent$1$1 */
    /* loaded from: classes4.dex */
    static final class C47871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5530p$;

        C47871(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C47871 c47871 = new C47871(completion);
            c47871.f5530p$ = (CoroutineScope) obj;
            return c47871;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C47871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:16:0x0054 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:17:0x0055  */
        /* JADX WARN: Removed duplicated region for block: B:18:0x0059  */
        /* JADX WARN: Removed duplicated region for block: B:9:0x0036  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:17:0x0055 -> B:7:0x0030). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final Object invokeSuspend(Object obj) {
            CoroutineScope coroutineScope;
            C47871 c47871;
            Object obj2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = this.f5530p$;
            } else if (i == 1) {
                CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                Object obj3 = coroutine_suspended;
                c47871 = this;
                c47871.L$0 = coroutineScope2;
                c47871.label = 2;
                if (DelayKt.delay(100L, c47871) != obj3) {
                    return obj3;
                }
                CoroutineScope coroutineScope3 = coroutineScope2;
                obj2 = obj3;
                coroutineScope = coroutineScope3;
                if (!CoroutineScopeKt.isActive(coroutineScope)) {
                    MoveAction moveAction = MoveAction$fallDropOccurEvent$1.this.this$0;
                    c47871.L$0 = coroutineScope;
                    c47871.label = 1;
                    if (moveAction.controlWheelCmd(true, c47871) == obj2) {
                        return obj2;
                    }
                    Object obj4 = obj2;
                    coroutineScope2 = coroutineScope;
                    obj3 = obj4;
                    c47871.L$0 = coroutineScope2;
                    c47871.label = 2;
                    if (DelayKt.delay(100L, c47871) != obj3) {
                    }
                } else {
                    return Unit.INSTANCE;
                }
            } else {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope = coroutineScope4;
            }
            obj2 = coroutine_suspended;
            c47871 = this;
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
            }
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job job;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5529p$;
            job = this.this$0.operatorjob;
            if (job == null) {
                return null;
            }
            this.L$0 = coroutineScope;
            this.label = 1;
            if (JobKt.cancelAndJoin(job, this) == coroutine_suspended) {
                return coroutine_suspended;
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
