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
/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: BaseDeviceControl.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "com/pudutech/mirsdkwrap/lib/robot/device/BaseDeviceControl$restart$2$1"}, m3962k = 3, m3963mv = {1, 1, 16})
/* loaded from: classes6.dex */
public final class BaseDeviceControl$restart$$inlined$repeat$lambda$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
    final /* synthetic */ Continuation $continuation$inlined;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6641p$;
    final /* synthetic */ BaseDeviceControl this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseDeviceControl$restart$$inlined$repeat$lambda$1(Continuation continuation, BaseDeviceControl baseDeviceControl, Continuation continuation2) {
        super(2, continuation);
        this.this$0 = baseDeviceControl;
        this.$continuation$inlined = continuation2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        BaseDeviceControl$restart$$inlined$repeat$lambda$1 baseDeviceControl$restart$$inlined$repeat$lambda$1 = new BaseDeviceControl$restart$$inlined$repeat$lambda$1(completion, this.this$0, this.$continuation$inlined);
        baseDeviceControl$restart$$inlined$repeat$lambda$1.f6641p$ = (CoroutineScope) obj;
        return baseDeviceControl$restart$$inlined$repeat$lambda$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
        return ((BaseDeviceControl$restart$$inlined$repeat$lambda$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6641p$;
            BaseDeviceControl baseDeviceControl = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = baseDeviceControl.switchSync(false, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return obj;
    }
}
