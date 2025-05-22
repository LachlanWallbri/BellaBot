package com.pudutech.factory_test.single_test;

import com.pudutech.base.Pdlog;
import com.pudutech.factory_test.ServiceConnectionKt;
import com.pudutech.factory_test.single_test.ESP32TestActivity;
import com.pudutech.factory_test.test_pack.WifiUtil;
import com.pudutech.mirsdk.hardware.HardwareInterface;
import com.pudutech.mirsdk.hardware.serialize.RobotScheduleInfo;
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
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: ESP32TestActivity.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.factory_test.single_test.ESP32TestActivity$runStepChecking$2", m3970f = "ESP32TestActivity.kt", m3971i = {0, 0, 0}, m3972l = {219}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "times", "currentSchInfo"}, m3975s = {"L$0", "I$0", "L$1"})
/* loaded from: classes.dex */
public final class ESP32TestActivity$runStepChecking$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.IntRef $cnt;
    int I$0;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5173p$;
    final /* synthetic */ ESP32TestActivity this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESP32TestActivity$runStepChecking$2(ESP32TestActivity eSP32TestActivity, Ref.IntRef intRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = eSP32TestActivity;
        this.$cnt = intRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        ESP32TestActivity$runStepChecking$2 eSP32TestActivity$runStepChecking$2 = new ESP32TestActivity$runStepChecking$2(this.this$0, this.$cnt, completion);
        eSP32TestActivity$runStepChecking$2.f5173p$ = (CoroutineScope) obj;
        return eSP32TestActivity$runStepChecking$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ESP32TestActivity$runStepChecking$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        ESP32TestActivity$runStepChecking$2 eSP32TestActivity$runStepChecking$2;
        CoroutineScope coroutineScope;
        int i;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5173p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "start sending");
            this.this$0.getKeyInfo().put("服务端", null);
            eSP32TestActivity$runStepChecking$2 = this;
            coroutineScope = coroutineScope2;
            i = 0;
        } else {
            if (i2 != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            int i3 = this.I$0;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            i = i3;
            eSP32TestActivity$runStepChecking$2 = this;
        }
        while (true) {
            int i4 = i + 1;
            if (i < 50) {
                RobotScheduleInfo robotScheduleInfo = new RobotScheduleInfo();
                robotScheduleInfo.setMap_flag(String.valueOf(WifiUtil.INSTANCE.getMac()));
                HardwareInterface hdInterface = ServiceConnectionKt.getHdInterface();
                if (hdInterface == null) {
                    Intrinsics.throwNpe();
                }
                hdInterface.getScheduler().sendRobotScheduleCommunicationInfo(robotScheduleInfo);
                eSP32TestActivity$runStepChecking$2.L$0 = coroutineScope;
                eSP32TestActivity$runStepChecking$2.I$0 = i4;
                eSP32TestActivity$runStepChecking$2.L$1 = robotScheduleInfo;
                eSP32TestActivity$runStepChecking$2.label = 1;
                if (DelayKt.delay(100L, eSP32TestActivity$runStepChecking$2) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                i = i4;
            } else {
                eSP32TestActivity$runStepChecking$2.this$0.setOnReceive((Function1) null);
                eSP32TestActivity$runStepChecking$2.this$0.getKeyInfo().put("发送个数", "10");
                eSP32TestActivity$runStepChecking$2.this$0.getKeyInfo().put("接收个数", String.valueOf(eSP32TestActivity$runStepChecking$2.$cnt.element));
                str2 = eSP32TestActivity$runStepChecking$2.this$0.TAG;
                Pdlog.m3275i(str2, "end auto test. cnt=" + eSP32TestActivity$runStepChecking$2.$cnt.element);
                eSP32TestActivity$runStepChecking$2.this$0.setStep(eSP32TestActivity$runStepChecking$2.$cnt.element > 5 ? ESP32TestActivity.Step.SUCCESS : ESP32TestActivity.Step.FAIL);
                if (eSP32TestActivity$runStepChecking$2.this$0.getStep() == ESP32TestActivity.Step.FAIL) {
                    eSP32TestActivity$runStepChecking$2.this$0.getMTestItem().setFailDescription("接收到的数据返回次数较少");
                }
                eSP32TestActivity$runStepChecking$2.this$0.FSM();
                return Unit.INSTANCE;
            }
        }
    }
}
