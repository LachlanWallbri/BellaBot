package com.pudutech.mirsdkwrap.lib.robot.device;

import android.util.Log;
import com.pudutech.base.Pdlog;
import java.util.concurrent.CancellationException;
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
import kotlinx.coroutines.Job;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseDeviceControl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.robot.device.BaseDeviceControl$switch$2", m3970f = "BaseDeviceControl.kt", m3971i = {0, 1}, m3972l = {93, 100}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes6.dex */
public final class BaseDeviceControl$switch$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6645p$;
    final /* synthetic */ BaseDeviceControl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDeviceControl$switch$2(BaseDeviceControl baseDeviceControl, Continuation continuation) {
        super(2, continuation);
        this.this$0 = baseDeviceControl;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BaseDeviceControl$switch$2 baseDeviceControl$switch$2 = new BaseDeviceControl$switch$2(this.this$0, completion);
        baseDeviceControl$switch$2.f6645p$ = (CoroutineScope) obj;
        return baseDeviceControl$switch$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BaseDeviceControl$switch$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Job job;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            try {
            } catch (Exception e) {
                Pdlog.m3274e(this.this$0.getTAG(), "switch closeDevice: " + Log.getStackTraceString(e));
            }
        } catch (Exception e2) {
            Pdlog.m3274e(this.this$0.getTAG(), "switch openDevice: " + Log.getStackTraceString(e2));
            this.this$0.setSetSwitch$module_robot_mirsdk_wrapper_release(false);
        }
        if (i != 0) {
            if (i == 1) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            if (i == 2) {
                ResultKt.throwOnFailure(obj);
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6645p$;
        synchronized (this.this$0) {
            if (this.this$0.getSetSwitch() == this.this$0.getCurrentDeviceSwitch()) {
                Pdlog.m3273d(this.this$0.getTAG(), "switch success , do not need control");
                job = this.this$0.checkJob;
                if (job != null) {
                    Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
                }
                this.this$0.checkJob = (Job) null;
                return Unit.INSTANCE;
            }
            Unit unit = Unit.INSTANCE;
            Pdlog.m3273d(this.this$0.getTAG(), "switch " + this.this$0.getSetSwitch());
            if (this.this$0.getSetSwitch()) {
                BaseDeviceControl baseDeviceControl = this.this$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (baseDeviceControl.openDevice(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
            BaseDeviceControl baseDeviceControl2 = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 2;
            if (baseDeviceControl2.closeDevice(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }
}
