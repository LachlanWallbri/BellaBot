package com.pudutech.pdmqtt.client;

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
/* compiled from: RemoteMqttClientImpl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pdmqtt.client.RemoteMqttClientImpl$realReconnect$1", m3970f = "RemoteMqttClientImpl.kt", m3971i = {0}, m3972l = {185}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class RemoteMqttClientImpl$realReconnect$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6897p$;
    final /* synthetic */ RemoteMqttClientImpl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RemoteMqttClientImpl$realReconnect$1(RemoteMqttClientImpl remoteMqttClientImpl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = remoteMqttClientImpl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RemoteMqttClientImpl$realReconnect$1 remoteMqttClientImpl$realReconnect$1 = new RemoteMqttClientImpl$realReconnect$1(this.this$0, completion);
        remoteMqttClientImpl$realReconnect$1.f6897p$ = (CoroutineScope) obj;
        return remoteMqttClientImpl$realReconnect$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteMqttClientImpl$realReconnect$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002b  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0039 -> B:5:0x003c). Please report as a decompilation issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        RemoteMqttClientImpl$realReconnect$1 remoteMqttClientImpl$realReconnect$1;
        boolean needConnect;
        long j;
        boolean needConnect2;
        long default_delay_time;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f6897p$;
            remoteMqttClientImpl$realReconnect$1 = this;
            needConnect = remoteMqttClientImpl$realReconnect$1.this$0.needConnect();
            if (needConnect) {
            }
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            remoteMqttClientImpl$realReconnect$1 = this;
            needConnect2 = remoteMqttClientImpl$realReconnect$1.this$0.needConnect();
            if (needConnect2) {
                remoteMqttClientImpl$realReconnect$1.this$0.realConnect();
                RemoteMqttClientImpl remoteMqttClientImpl = remoteMqttClientImpl$realReconnect$1.this$0;
                default_delay_time = remoteMqttClientImpl.getDEFAULT_DELAY_TIME();
                remoteMqttClientImpl.crtDelayTime = default_delay_time;
            }
            needConnect = remoteMqttClientImpl$realReconnect$1.this$0.needConnect();
            if (needConnect) {
                j = remoteMqttClientImpl$realReconnect$1.this$0.crtDelayTime;
                remoteMqttClientImpl$realReconnect$1.L$0 = coroutineScope;
                remoteMqttClientImpl$realReconnect$1.label = 1;
                if (DelayKt.delay(j, remoteMqttClientImpl$realReconnect$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                needConnect2 = remoteMqttClientImpl$realReconnect$1.this$0.needConnect();
                if (needConnect2) {
                }
                needConnect = remoteMqttClientImpl$realReconnect$1.this$0.needConnect();
                if (needConnect) {
                    return Unit.INSTANCE;
                }
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
