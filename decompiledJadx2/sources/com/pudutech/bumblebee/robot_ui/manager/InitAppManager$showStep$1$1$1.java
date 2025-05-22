package com.pudutech.bumblebee.robot_ui.manager;

import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.ReqRobotMac;
import com.pudutech.mirsdkwrap.lib.map.RobotMapManager;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: InitAppManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.manager.InitAppManager$showStep$1$1$1", m3970f = "InitAppManager.kt", m3971i = {0}, m3972l = {216}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes3.dex */
final class InitAppManager$showStep$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $it;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4845p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public InitAppManager$showStep$1$1$1(String str, Continuation continuation) {
        super(2, continuation);
        this.$it = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        InitAppManager$showStep$1$1$1 initAppManager$showStep$1$1$1 = new InitAppManager$showStep$1$1$1(this.$it, completion);
        initAppManager$showStep$1$1$1.f4845p$ = (CoroutineScope) obj;
        return initAppManager$showStep$1$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((InitAppManager$showStep$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f4845p$;
                NetWorkApiManager.MapManagerService map = NetWorkApiManager.INSTANCE.getMap();
                ReqRobotMac reqRobotMac = new ReqRobotMac(this.$it, CollectionsKt.arrayListOf(RobotMapManager.INSTANCE.getDefaultPdmap()));
                this.L$0 = coroutineScope;
                this.label = 1;
                if (map.switchMap(reqRobotMac, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
        } catch (Exception e) {
            InitAppManager initAppManager = InitAppManager.INSTANCE;
            str = InitAppManager.TAG;
            Pdlog.m3274e(str, "showStep switchMap:" + e + ' ');
        }
        return Unit.INSTANCE;
    }
}
