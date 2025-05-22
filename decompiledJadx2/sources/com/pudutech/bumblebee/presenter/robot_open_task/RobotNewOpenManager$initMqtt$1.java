package com.pudutech.bumblebee.presenter.robot_open_task;

import android.content.Context;
import com.pudutech.base.Pdlog;
import com.pudutech.bumblebee.presenter.Constant;
import com.pudutech.bumblebee.presenter.robot_open_task.config.MqttConfig;
import com.pudutech.bumblebee.presenter.robot_open_task.manager.MqttManager;
import com.pudutech.bumblebee.presenter.utils.WifiUtil;
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
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: RobotNewOpenManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$initMqtt$1", m3970f = "RobotNewOpenManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
public final class RobotNewOpenManager$initMqtt$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4703p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RobotNewOpenManager$initMqtt$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RobotNewOpenManager$initMqtt$1 robotNewOpenManager$initMqtt$1 = new RobotNewOpenManager$initMqtt$1(completion);
        robotNewOpenManager$initMqtt$1.f4703p$ = (CoroutineScope) obj;
        return robotNewOpenManager$initMqtt$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RobotNewOpenManager$initMqtt$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String mac;
        Context context;
        Function2<? super String, ? super String, Unit> function2;
        Function1<? super Integer, Unit> function1;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f4703p$;
        try {
            mac = WifiUtil.INSTANCE.getMac();
        } catch (Exception e) {
            Pdlog.m3274e(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "initMqtt e: " + e);
            RobotNewOpenManager.INSTANCE.delayInit();
        }
        if (mac == null) {
            Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "initMqtt mac is null");
            RobotNewOpenManager.INSTANCE.delayInit();
            return Unit.INSTANCE;
        }
        MqttConfig.INSTANCE.setMac(mac);
        RobotNewOpenManager.INSTANCE.setShopId(Constant.INSTANCE.getShopId());
        RobotNewOpenManager robotNewOpenManager = RobotNewOpenManager.INSTANCE;
        context = RobotNewOpenManager.context;
        if (context != null) {
            MqttManager.INSTANCE.init(context, new Function1<Boolean, Unit>() { // from class: com.pudutech.bumblebee.presenter.robot_open_task.RobotNewOpenManager$initMqtt$1$1$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Boolean bool) {
                    invoke(bool.booleanValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(boolean z) {
                    Pdlog.m3273d(RobotNewOpenManager.access$getTAG$p(RobotNewOpenManager.INSTANCE), "initMqtt it:" + z + ' ');
                    if (!z) {
                        RobotNewOpenManager.INSTANCE.delayInit();
                    } else {
                        RobotNewOpenManager.INSTANCE.reportRobotStatus();
                    }
                }
            });
            MqttManager mqttManager = MqttManager.INSTANCE;
            RobotNewOpenManager robotNewOpenManager2 = RobotNewOpenManager.INSTANCE;
            function2 = RobotNewOpenManager.subscribeListener;
            mqttManager.setSubscribeListener(function2);
            MqttManager mqttManager2 = MqttManager.INSTANCE;
            RobotNewOpenManager robotNewOpenManager3 = RobotNewOpenManager.INSTANCE;
            function1 = RobotNewOpenManager.connectStatusListener;
            mqttManager2.setConnectStatusListener(function1);
        }
        return Unit.INSTANCE;
    }
}
