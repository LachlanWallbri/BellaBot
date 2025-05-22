package com.pudutech.bumblebee.presenter.monitor_task;

import com.pudutech.mirsdkwrap.lib.MirSdkManager;
import com.pudutech.mirsdkwrap.lib.robot.DevicesControlHelper;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MonitorPresenter.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u008a@¢\u0006\u0004\b\u0002\u0010\u0003"}, m3961d2 = {"<anonymous>", "", "invoke", "(Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.monitor_task.MonitorPresenter$processAutoResumeBackground$2", m3970f = "MonitorPresenter.kt", m3971i = {}, m3972l = {286}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class MonitorPresenter$processAutoResumeBackground$2 extends SuspendLambda implements Function1<Continuation<? super Boolean>, Object> {
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    public MonitorPresenter$processAutoResumeBackground$2(Continuation continuation) {
        super(1, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        return new MonitorPresenter$processAutoResumeBackground$2(completion);
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Continuation<? super Boolean> continuation) {
        return ((MonitorPresenter$processAutoResumeBackground$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        boolean z;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            DevicesControlHelper deviceControlHelper = MirSdkManager.INSTANCE.getDeviceControlHelper();
            if (deviceControlHelper != null) {
                DevicesControlHelper.DeviceName deviceName = DevicesControlHelper.DeviceName.RGBD;
                this.label = 1;
                obj = deviceControlHelper.restartDevice(deviceName, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            z = false;
            return Boxing.boxBoolean(z);
        }
        if (i != 1) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        Boolean bool = (Boolean) obj;
        if (bool != null) {
            z = bool.booleanValue();
            return Boxing.boxBoolean(z);
        }
        z = false;
        return Boxing.boxBoolean(z);
    }
}
