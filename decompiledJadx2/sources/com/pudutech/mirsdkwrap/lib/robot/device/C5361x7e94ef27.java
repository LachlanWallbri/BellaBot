package com.pudutech.mirsdkwrap.lib.robot.device;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: BaseDeviceControl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/mirsdkwrap/lib/robot/device/BaseDeviceControl$switchSync$2$2"}, m3962k = 3, m3963mv = {1, 1, 16})
/* renamed from: com.pudutech.mirsdkwrap.lib.robot.device.BaseDeviceControl$switchSync$$inlined$suspendCancellableCoroutine$lambda$2 */
/* loaded from: classes6.dex */
public final class C5361x7e94ef27 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $b$inlined;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6643p$;
    final /* synthetic */ BaseDeviceControl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C5361x7e94ef27(Continuation continuation, BaseDeviceControl baseDeviceControl, boolean z) {
        super(2, continuation);
        this.this$0 = baseDeviceControl;
        this.$b$inlined = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        C5361x7e94ef27 c5361x7e94ef27 = new C5361x7e94ef27(completion, this.this$0, this.$b$inlined);
        c5361x7e94ef27.f6643p$ = (CoroutineScope) obj;
        return c5361x7e94ef27;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((C5361x7e94ef27) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6643p$;
            if (this.$b$inlined) {
                BaseDeviceControl baseDeviceControl = this.this$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                if (baseDeviceControl.openDevice(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                BaseDeviceControl baseDeviceControl2 = this.this$0;
                this.L$0 = coroutineScope;
                this.label = 2;
                if (baseDeviceControl2.closeDevice(this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1 || i == 2) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
