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

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$rotate$2", m3970f = "MoveAction.kt", m3971i = {0, 1}, m3972l = {229, 233}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes5.dex */
final class MoveAction$rotate$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ double $p0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5552p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$rotate$2(MoveAction moveAction, double d, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
        this.$p0 = d;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$rotate$2 moveAction$rotate$2 = new MoveAction$rotate$2(this.this$0, this.$p0, completion);
        moveAction$rotate$2.f5552p$ = (CoroutineScope) obj;
        return moveAction$rotate$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$rotate$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x006e  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        CoroutineScope coroutineScope2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope3 = this.f5552p$;
            if (!CoroutineScopeKt.isActive(coroutineScope3)) {
                return Unit.INSTANCE;
            }
            MoveAction moveAction = this.this$0;
            this.L$0 = coroutineScope3;
            this.label = 1;
            Object checkAndClearWheelError = moveAction.checkAndClearWheelError(this);
            if (checkAndClearWheelError == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope3;
            obj = checkAndClearWheelError;
        } else {
            if (i != 1) {
                if (i == 2) {
                    coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    if (!CoroutineScopeKt.isActive(coroutineScope2)) {
                        this.this$0.rotateTask(this.$p0);
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        if (!((Boolean) obj).booleanValue()) {
            return Unit.INSTANCE;
        }
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
            return Unit.INSTANCE;
        }
        MoveAction moveAction2 = this.this$0;
        this.L$0 = coroutineScope;
        this.label = 2;
        if (MoveAction.stopAndWaitBrake$default(moveAction2, false, this, 1, null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        coroutineScope2 = coroutineScope;
        if (!CoroutineScopeKt.isActive(coroutineScope2)) {
        }
    }
}
