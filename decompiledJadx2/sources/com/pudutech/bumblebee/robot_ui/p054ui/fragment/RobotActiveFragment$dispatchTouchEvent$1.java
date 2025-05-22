package com.pudutech.bumblebee.robot_ui.p054ui.fragment;

import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.robot_ui.agent.AgentTestManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotActiveFragment.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$dispatchTouchEvent$1", m3970f = "RobotActiveFragment.kt", m3971i = {0, 0, 1, 1}, m3972l = {330, 333}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "idx", "$this$launch", "idx"}, m3975s = {"L$0", "I$0", "L$0", "I$0"})
/* loaded from: classes3.dex */
public final class RobotActiveFragment$dispatchTouchEvent$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4916p$;
    final /* synthetic */ RobotActiveFragment this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RobotActiveFragment$dispatchTouchEvent$1(RobotActiveFragment robotActiveFragment, Continuation continuation) {
        super(2, continuation);
        this.this$0 = robotActiveFragment;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotActiveFragment$dispatchTouchEvent$1 robotActiveFragment$dispatchTouchEvent$1 = new RobotActiveFragment$dispatchTouchEvent$1(this.this$0, completion);
        robotActiveFragment$dispatchTouchEvent$1.f4916p$ = (CoroutineScope) obj;
        return robotActiveFragment$dispatchTouchEvent$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotActiveFragment$dispatchTouchEvent$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0067  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:21:0x0047 -> B:16:0x004a). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        int i;
        RobotActiveFragment$dispatchTouchEvent$1 robotActiveFragment$dispatchTouchEvent$1;
        RobotActiveFragment$dispatchTouchEvent$1 robotActiveFragment$dispatchTouchEvent$12;
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f4916p$;
            i = 0;
            robotActiveFragment$dispatchTouchEvent$1 = this;
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
            return Unit.INSTANCE;
        }
        if (i2 != 1) {
            if (i2 == 2) {
                int i3 = this.I$0;
                ResultKt.throwOnFailure(obj);
                robotActiveFragment$dispatchTouchEvent$12 = this;
                if (!((Boolean) obj).booleanValue()) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new C43261(null), 2, null);
                } else {
                    str = robotActiveFragment$dispatchTouchEvent$12.this$0.TAG;
                    Pdlog.m3273d(str, "代理商测试次数用完");
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        i = this.I$0;
        coroutineScope = (CoroutineScope) this.L$0;
        ResultKt.throwOnFailure(obj);
        robotActiveFragment$dispatchTouchEvent$1 = this;
        i++;
        if (i >= 5) {
            AgentTestManager agentTestManager = AgentTestManager.INSTANCE;
            robotActiveFragment$dispatchTouchEvent$1.L$0 = coroutineScope;
            robotActiveFragment$dispatchTouchEvent$1.I$0 = i;
            robotActiveFragment$dispatchTouchEvent$1.label = 2;
            Object isCanAgentTest = agentTestManager.isCanAgentTest(robotActiveFragment$dispatchTouchEvent$1);
            if (isCanAgentTest == coroutine_suspended) {
                return coroutine_suspended;
            }
            robotActiveFragment$dispatchTouchEvent$12 = robotActiveFragment$dispatchTouchEvent$1;
            obj = isCanAgentTest;
            if (!((Boolean) obj).booleanValue()) {
            }
            return Unit.INSTANCE;
        }
        if (CoroutineScopeKt.isActive(coroutineScope)) {
            robotActiveFragment$dispatchTouchEvent$1.L$0 = coroutineScope;
            robotActiveFragment$dispatchTouchEvent$1.I$0 = i;
            robotActiveFragment$dispatchTouchEvent$1.label = 1;
            if (DelayKt.delay(1000L, robotActiveFragment$dispatchTouchEvent$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            i++;
            if (i >= 5) {
            }
            if (CoroutineScopeKt.isActive(coroutineScope)) {
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RobotActiveFragment.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$dispatchTouchEvent$1$1", m3970f = "RobotActiveFragment.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment$dispatchTouchEvent$1$1 */
    /* loaded from: classes3.dex */
    public static final class C43261 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f4917p$;

        C43261(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C43261 c43261 = new C43261(completion);
            c43261.f4917p$ = (CoroutineScope) obj;
            return c43261;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C43261) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4917p$;
            RobotActiveFragment.showPasswordDialog$default(RobotActiveFragment$dispatchTouchEvent$1.this.this$0, new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.robot_ui.ui.fragment.RobotActiveFragment.dispatchTouchEvent.1.1.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    if (z) {
                        RobotActiveFragment$dispatchTouchEvent$1.this.this$0.disDialog();
                        if (AgentTestManager.INSTANCE.isHaveTestData()) {
                            if (AgentTestManager.INSTANCE.isOut24H()) {
                                RobotActiveFragment$dispatchTouchEvent$1.this.this$0.cleanFactory();
                                return;
                            } else {
                                AgentTestManager.INSTANCE.startCountdown();
                                RobotActiveFragment$dispatchTouchEvent$1.this.this$0.gotoSelfCheckActivity();
                                return;
                            }
                        }
                        AgentTestManager agentTestManager = AgentTestManager.INSTANCE;
                        agentTestManager.saveAgentTestData();
                        agentTestManager.startCountdown();
                        RobotActiveFragment$dispatchTouchEvent$1.this.this$0.gotoSelfCheckActivity();
                    }
                }
            }, null, 2, null);
            return Unit.INSTANCE;
        }
    }
}
