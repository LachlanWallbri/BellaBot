package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.aidl.serialize.RobotState;
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
/* compiled from: SolicitService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SolicitService$triggerError$2", m3970f = "SolicitService.kt", m3971i = {0}, m3972l = {630}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class SolicitService$triggerError$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $error;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5598p$;
    final /* synthetic */ SolicitService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SolicitService$triggerError$2(SolicitService solicitService, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = solicitService;
        this.$error = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SolicitService$triggerError$2 solicitService$triggerError$2 = new SolicitService$triggerError$2(this.this$0, this.$error, completion);
        solicitService$triggerError$2.f5598p$ = (CoroutineScope) obj;
        return solicitService$triggerError$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SolicitService$triggerError$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Function2 function2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5598p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "suspend error " + this.$error + " when moving");
            SolicitService solicitService = this.this$0;
            this.L$0 = coroutineScope;
            this.label = 1;
            if (solicitService.stopAndWaitBrake(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        function2 = this.this$0._onStateChange;
        function2.invoke(RobotState.Error, this.$error);
        return Unit.INSTANCE;
    }
}
