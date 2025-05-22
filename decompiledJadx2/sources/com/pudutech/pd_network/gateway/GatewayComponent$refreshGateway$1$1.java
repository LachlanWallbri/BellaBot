package com.pudutech.pd_network.gateway;

import com.pudutech.pd_network.log.NetWorkLog;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: GatewayComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.gateway.GatewayComponent$refreshGateway$1$1", m3970f = "GatewayComponent.kt", m3971i = {0}, m3972l = {116}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class GatewayComponent$refreshGateway$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function0 $this_run;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6839p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GatewayComponent$refreshGateway$1$1(Function0 function0, Continuation continuation) {
        super(2, continuation);
        this.$this_run = function0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        GatewayComponent$refreshGateway$1$1 gatewayComponent$refreshGateway$1$1 = new GatewayComponent$refreshGateway$1$1(this.$this_run, completion);
        gatewayComponent$refreshGateway$1$1.f6839p$ = (CoroutineScope) obj;
        return gatewayComponent$refreshGateway$1$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((GatewayComponent$refreshGateway$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6839p$;
            GatewayComponent$refreshGateway$1$1$res$1 gatewayComponent$refreshGateway$1$1$res$1 = new GatewayComponent$refreshGateway$1$1$res$1(this, null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = TimeoutKt.withTimeoutOrNull(15000L, gatewayComponent$refreshGateway$1$1$res$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        if (((Boolean) obj) == null) {
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            GatewayComponent gatewayComponent = GatewayComponent.INSTANCE;
            str = GatewayComponent.TAG;
            netWorkLog.mo3280i(str, "refreshGateway.error");
        }
        return Unit.INSTANCE;
    }
}
