package com.pudutech.peanut.robot_ui.manager;

import com.pudutech.base.Pdlog;
import com.pudutech.peanut.robot_ui.manager.InitRobotAppManager;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: InitRobotAppManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.manager.InitRobotAppManager$mirInitListener$1$invoke$2", m3970f = "InitRobotAppManager.kt", m3971i = {0}, m3972l = {113}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes5.dex */
public final class InitRobotAppManager$mirInitListener$1$invoke$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7000p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public InitRobotAppManager$mirInitListener$1$invoke$2(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        InitRobotAppManager$mirInitListener$1$invoke$2 initRobotAppManager$mirInitListener$1$invoke$2 = new InitRobotAppManager$mirInitListener$1$invoke$2(completion);
        initRobotAppManager$mirInitListener$1$invoke$2.f7000p$ = (CoroutineScope) obj;
        return initRobotAppManager$mirInitListener$1$invoke$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((InitRobotAppManager$mirInitListener$1$invoke$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.L$0 = this.f7000p$;
            this.label = 1;
            if (DelayKt.delay(300000L, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        InitRobotAppManager initRobotAppManager = InitRobotAppManager.INSTANCE;
        str = InitRobotAppManager.TAG;
        Pdlog.m3273d(str, "init is timeout");
        InitRobotAppManager initRobotAppManager2 = InitRobotAppManager.INSTANCE;
        InitRobotAppManager.initStatus = InitRobotAppManager.InitStatus.TIMEOUT;
        InitRobotAppManager.INSTANCE.notifyResult();
        return Unit.INSTANCE;
    }
}
