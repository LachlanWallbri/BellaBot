package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
import com.pudutech.mirsdk.base.SDKRobotState;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MoveAction.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.MoveAction$actionPause$2", m3970f = "MoveAction.kt", m3971i = {0, 1}, m3972l = {1295, 1296}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes5.dex */
public final class MoveAction$actionPause$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5522p$;
    final /* synthetic */ MoveAction this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MoveAction$actionPause$2(MoveAction moveAction, Continuation continuation) {
        super(2, continuation);
        this.this$0 = moveAction;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MoveAction$actionPause$2 moveAction$actionPause$2 = new MoveAction$actionPause$2(this.this$0, completion);
        moveAction$actionPause$2.f5522p$ = (CoroutineScope) obj;
        return moveAction$actionPause$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MoveAction$actionPause$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0085  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0088  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x006d  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        String str;
        RobotState robotState;
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5522p$;
            str = this.this$0.TAG;
            Pdlog.m3275i(str, "pause async");
            if (!CoroutineScopeKt.isActive(coroutineScope)) {
                return Unit.INSTANCE;
            }
            MoveAction moveAction = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (MoveAction.stop$default(moveAction, false, this, 1, null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope = coroutineScope2;
                    robotState = this.this$0.robotState;
                    if (robotState == RobotState.Approaching) {
                        z = this.this$0.cruiseTask;
                        if (!z) {
                            this.this$0.onStateChange(SDKRobotState.Arrive, "");
                            return Unit.INSTANCE;
                        }
                    }
                    if (!CoroutineScopeKt.isActive(coroutineScope)) {
                        this.this$0.onStateChange(SDKRobotState.Pause, "");
                        return Unit.INSTANCE;
                    }
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            coroutineScope = coroutineScope3;
        }
        this.L$0 = coroutineScope;
        this.label = 2;
        if (DelayKt.delay(10L, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        robotState = this.this$0.robotState;
        if (robotState == RobotState.Approaching) {
        }
        if (!CoroutineScopeKt.isActive(coroutineScope)) {
        }
    }
}
