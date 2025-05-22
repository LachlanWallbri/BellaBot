package com.pudutech.robot.peripherals.disinfection.device;

import android.util.Log;
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
/* compiled from: BasePeripheral.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.robot.peripherals.disinfection.device.BasePeripheral$switch$1", m3970f = "BasePeripheral.kt", m3971i = {0, 1}, m3972l = {135, 143}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch"}, m3975s = {"L$0", "L$0"})
/* loaded from: classes6.dex */
public final class BasePeripheral$switch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7350p$;
    final /* synthetic */ BasePeripheral this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BasePeripheral$switch$1(BasePeripheral basePeripheral, Continuation continuation) {
        super(2, continuation);
        this.this$0 = basePeripheral;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BasePeripheral$switch$1 basePeripheral$switch$1 = new BasePeripheral$switch$1(this.this$0, completion);
        basePeripheral$switch$1.f7350p$ = (CoroutineScope) obj;
        return basePeripheral$switch$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BasePeripheral$switch$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            try {
            } catch (Exception e) {
                e.printStackTrace();
                Pdlog.m3273d(BasePeripheral.TAG, "switch close Device occur error " + Log.getStackTraceString(e));
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            Pdlog.m3273d(BasePeripheral.TAG, "switch open Device occur error " + Log.getStackTraceString(e2));
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
        CoroutineScope coroutineScope = this.f7350p$;
        Pdlog.m3273d(BasePeripheral.TAG, "setSwitch " + this.this$0.getSwitchStatus());
        if (this.this$0.getSwitchStatus()) {
            this.this$0.startCheck(10001);
            BasePeripheral basePeripheral = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (basePeripheral.openDevice(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
        this.this$0.startCheck(BasePeripheral.RETRY_CLOSE);
        BasePeripheral basePeripheral2 = this.this$0;
        this.L$0 = coroutineScope;
        this.label = 2;
        if (basePeripheral2.closeDevice(this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
