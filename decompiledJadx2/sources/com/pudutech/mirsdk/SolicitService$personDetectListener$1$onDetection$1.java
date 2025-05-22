package com.pudutech.mirsdk;

import com.pudutech.base.Pdlog;
import com.pudutech.base.architecture.AIDLConnection;
import com.pudutech.mirsdk.mircore.MirCoreInterface;
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

/* compiled from: SolicitService.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.mirsdk.SolicitService$personDetectListener$1$onDetection$1", m3970f = "SolicitService.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes4.dex */
final class SolicitService$personDetectListener$1$onDetection$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5593p$;
    final /* synthetic */ SolicitService$personDetectListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SolicitService$personDetectListener$1$onDetection$1(SolicitService$personDetectListener$1 solicitService$personDetectListener$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = solicitService$personDetectListener$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        SolicitService$personDetectListener$1$onDetection$1 solicitService$personDetectListener$1$onDetection$1 = new SolicitService$personDetectListener$1$onDetection$1(this.this$0, completion);
        solicitService$personDetectListener$1$onDetection$1.f5593p$ = (CoroutineScope) obj;
        return solicitService$personDetectListener$1$onDetection$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SolicitService$personDetectListener$1$onDetection$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        boolean z;
        AIDLConnection aIDLConnection;
        SolicitService$faceDetectListener$1 solicitService$faceDetectListener$1;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5593p$;
        str = this.this$0.this$0.TAG;
        Pdlog.m3273d(str, "start face detect");
        z = this.this$0.this$0.NPUFlag;
        if (!z) {
            this.this$0.this$0.controlFaceDetect(true);
        } else {
            aIDLConnection = this.this$0.this$0.coreService;
            MirCoreInterface mirCoreInterface = (MirCoreInterface) aIDLConnection.getInterface();
            if (mirCoreInterface != null) {
                solicitService$faceDetectListener$1 = this.this$0.this$0.faceDetectListener;
                mirCoreInterface.addFaceDetectListener("solicitFaceControl", solicitService$faceDetectListener$1);
            }
        }
        return Unit.INSTANCE;
    }
}
