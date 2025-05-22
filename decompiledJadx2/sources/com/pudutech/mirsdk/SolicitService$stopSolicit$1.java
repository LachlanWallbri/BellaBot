package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.mirsdk.SolicitService;
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
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SolicitService$stopSolicit$1", m3970f = "SolicitService.kt", m3971i = {0}, m3972l = {436}, m3973m = "invokeSuspend", m3974n = {"$this$runBlocking"}, m3975s = {"L$0"})
/* loaded from: classes4.dex */
public final class SolicitService$stopSolicit$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5594p$;
    final /* synthetic */ SolicitService this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SolicitService$stopSolicit$1(SolicitService solicitService, Continuation continuation) {
        super(2, continuation);
        this.this$0 = solicitService;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SolicitService$stopSolicit$1 solicitService$stopSolicit$1 = new SolicitService$stopSolicit$1(this.this$0, completion);
        solicitService$stopSolicit$1.f5594p$ = (CoroutineScope) obj;
        return solicitService$stopSolicit$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SolicitService$stopSolicit$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        String str2;
        SolicitService.Companion unused;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5594p$;
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "stopSolicit start ----------");
            this.this$0.personDetectTime = 0;
            this.this$0.faceDetectCount = 0;
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
        SolicitService solicitService2 = this.this$0;
        unused = SolicitService.INSTANCE;
        solicitService2.solicitState = (byte) 0;
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "stopSolicit end ----------");
        return Unit.INSTANCE;
    }
}
