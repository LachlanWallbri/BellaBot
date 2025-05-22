package com.pudutech.bumblebee.robot_ui.manager;

import com.pudu.library.loracall.LoRaClient;
import com.pudutech.bumblebee.robot_ui.manager.LoRaConnectState;
import com.pudutech.gatecontrollerlib.GateControllerMsg;
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
import kotlinx.coroutines.flow.MutableStateFlow;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: LoRaManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.manager.LoRaManager$crtGateway$1", m3970f = "LoRaManager.kt", m3971i = {0}, m3972l = {GateControllerMsg.ControlCode.Error}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes3.dex */
public final class LoRaManager$crtGateway$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4849p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public LoRaManager$crtGateway$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LoRaManager$crtGateway$1 loRaManager$crtGateway$1 = new LoRaManager$crtGateway$1(completion);
        loRaManager$crtGateway$1.f4849p$ = (CoroutineScope) obj;
        return loRaManager$crtGateway$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoRaManager$crtGateway$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        MutableStateFlow mutableStateFlow;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4849p$;
            LoRaClient companion = LoRaClient.INSTANCE.getInstance();
            this.L$0 = coroutineScope;
            this.label = 1;
            if (companion.enableWifiModule(false, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        LoRaManager loRaManager = LoRaManager.INSTANCE;
        mutableStateFlow = LoRaManager._loraConnectStateFL;
        mutableStateFlow.setValue(LoRaConnectState.Disconnect.INSTANCE);
        return Unit.INSTANCE;
    }
}
