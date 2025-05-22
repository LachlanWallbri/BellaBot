package com.pudutech.bumblebee.presenter.robot_open_task.manager;

import com.pudutech.base.Pdlog;
import com.pudutech.pdmqtt.OnPublishCallback;
import com.pudutech.pdmqtt.client.IMqttClient;
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
/* compiled from: MqttManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$sendMessage$1", m3970f = "MqttManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class MqttManager$sendMessage$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2 $callback;
    final /* synthetic */ String $message;
    final /* synthetic */ String $topic;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4715p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MqttManager$sendMessage$1(String str, String str2, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$message = str;
        this.$topic = str2;
        this.$callback = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MqttManager$sendMessage$1 mqttManager$sendMessage$1 = new MqttManager$sendMessage$1(this.$message, this.$topic, this.$callback, completion);
        mqttManager$sendMessage$1.f4715p$ = (CoroutineScope) obj;
        return mqttManager$sendMessage$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MqttManager$sendMessage$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IMqttClient iMqttClient;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4715p$;
        Pdlog.m3273d(MqttManager.INSTANCE.getTAG(), "发送消息：" + this.$message + ',' + this.$topic);
        MqttManager mqttManager = MqttManager.INSTANCE;
        iMqttClient = MqttManager.mqttClient;
        if (iMqttClient != null) {
            iMqttClient.publish(this.$topic, this.$message, new OnPublishCallback.Stub() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager$sendMessage$1.1
                @Override // com.pudutech.pdmqtt.OnPublishCallback
                public void onSuccess(String topic, String payload) {
                    Intrinsics.checkParameterIsNotNull(topic, "topic");
                    Intrinsics.checkParameterIsNotNull(payload, "payload");
                    Pdlog.m3274e(MqttManager.INSTANCE.getTAG(), "发送成功 " + topic);
                    Function2 function2 = MqttManager$sendMessage$1.this.$callback;
                    if (function2 != null) {
                    }
                }

                @Override // com.pudutech.pdmqtt.OnPublishCallback
                public void onFailue(String topic, String payload, int code, String message) {
                    Intrinsics.checkParameterIsNotNull(topic, "topic");
                    Intrinsics.checkParameterIsNotNull(payload, "payload");
                    Pdlog.m3274e(MqttManager.INSTANCE.getTAG(), "发送失败 " + topic);
                    Function2 function2 = MqttManager$sendMessage$1.this.$callback;
                    if (function2 != null) {
                    }
                }
            });
        }
        return Unit.INSTANCE;
    }
}
