package com.pudutech.bumblebee.robot_ui.manager;

import android.content.Context;
import com.pudu.library.loracall.LoRaClient;
import com.pudu.library.loracall.LoRaConfig;
import com.pudutech.base.Pdlog;
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
/* compiled from: LoRaManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.robot_ui.manager.LoRaManager$init$1", m3970f = "LoRaManager.kt", m3971i = {0}, m3972l = {147}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes3.dex */
public final class LoRaManager$init$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4851p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LoRaManager$init$1(Context context, Continuation continuation) {
        super(2, continuation);
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        LoRaManager$init$1 loRaManager$init$1 = new LoRaManager$init$1(this.$context, completion);
        loRaManager$init$1.f4851p$ = (CoroutineScope) obj;
        return loRaManager$init$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LoRaManager$init$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        LoRaManager$listener$1 loRaManager$listener$1;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4851p$;
            Pdlog.m3275i(LoRaManager.access$getTAG$p(LoRaManager.INSTANCE), "init > start " + LoRaClient.INSTANCE.getInstance().isExistLora());
            LoRaClient companion = LoRaClient.INSTANCE.getInstance();
            Context context = this.$context;
            LoRaConfig loRaConfig = new LoRaConfig("10c4", "ea60", "CP2102N USB to UART Bridge Controller", "ttyUSB0", 0, 0, 2, null, 176, null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (companion.connectLoRa(context, loRaConfig, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        LoRaClient companion2 = LoRaClient.INSTANCE.getInstance();
        String TAG = LoRaManager.access$getTAG$p(LoRaManager.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        LoRaManager loRaManager = LoRaManager.INSTANCE;
        loRaManager$listener$1 = LoRaManager.listener;
        companion2.addListener(TAG, loRaManager$listener$1);
        Pdlog.m3275i(LoRaManager.access$getTAG$p(LoRaManager.INSTANCE), "init > end");
        return Unit.INSTANCE;
    }
}
