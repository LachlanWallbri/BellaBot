package com.pudu.library.loracall;

import android.content.Context;
import com.pudu.library.loracall.MsgReceiveHandle;
import com.pudu.library.loracall.bean.LoRaDeviceState;
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

/* compiled from: LoRaHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudu.library.loracall.LoRaHelper$init$2", m3970f = "LoRaHelper.kt", m3971i = {0}, m3972l = {51}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
final class LoRaHelper$init$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ LoRaConfig $config;
    final /* synthetic */ Context $context;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4374p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoRaHelper$init$2(Context context, LoRaConfig loRaConfig, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
        this.$config = loRaConfig;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LoRaHelper$init$2 loRaHelper$init$2 = new LoRaHelper$init$2(this.$context, this.$config, completion);
        loRaHelper$init$2.f4374p$ = (CoroutineScope) obj;
        return loRaHelper$init$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoRaHelper$init$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4374p$;
            LoRaClient companion = LoRaClient.INSTANCE.getInstance();
            Context applicationContext = this.$context.getApplicationContext();
            Intrinsics.checkExpressionValueIsNotNull(applicationContext, "context.applicationContext");
            LoRaConfig loRaConfig = this.$config;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (companion.connectLoRa(applicationContext, loRaConfig, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        LoRaClient.INSTANCE.getInstance().addListener("LoRaHelper", new MsgReceiveHandle.Listener() { // from class: com.pudu.library.loracall.LoRaHelper$init$2.1
            @Override // com.pudu.library.loracall.MsgReceiveHandle.Listener
            public void receive(ReceiveMsgType msgType) {
                Intrinsics.checkParameterIsNotNull(msgType, "msgType");
                LoRaHelper.INSTANCE.handleReceiveMsg(msgType);
            }
        });
        LoRaHelper.INSTANCE.switchState(LoRaDeviceState.IDLE);
        return Unit.INSTANCE;
    }
}
