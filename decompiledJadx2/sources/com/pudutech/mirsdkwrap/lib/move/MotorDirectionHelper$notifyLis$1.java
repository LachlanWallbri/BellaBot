package com.pudutech.mirsdkwrap.lib.move;

import com.pudutech.mirsdkwrap.lib.interf.MotorDirection;
import com.pudutech.mirsdkwrap.lib.interf.MotorDirectionListener;
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

/* JADX WARN: Classes with same name are omitted:
  classes4.dex
 */
/* compiled from: MotorDirectionHelper.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdkwrap.lib.move.MotorDirectionHelper$notifyLis$1", m3970f = "MotorDirectionHelper.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
final class MotorDirectionHelper$notifyLis$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MotorDirection $state;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6544p$;
    final /* synthetic */ MotorDirectionHelper this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MotorDirectionHelper$notifyLis$1(MotorDirectionHelper motorDirectionHelper, MotorDirection motorDirection, Continuation continuation) {
        super(2, continuation);
        this.this$0 = motorDirectionHelper;
        this.$state = motorDirection;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MotorDirectionHelper$notifyLis$1 motorDirectionHelper$notifyLis$1 = new MotorDirectionHelper$notifyLis$1(this.this$0, this.$state, completion);
        motorDirectionHelper$notifyLis$1.f6544p$ = (CoroutineScope) obj;
        return motorDirectionHelper$notifyLis$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MotorDirectionHelper$notifyLis$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6544p$;
        MotorDirectionListener onMotorDirectionListener = this.this$0.getOnMotorDirectionListener();
        if (onMotorDirectionListener != null) {
            onMotorDirectionListener.onMotorDirectionChange(this.$state);
        }
        return Unit.INSTANCE;
    }
}
