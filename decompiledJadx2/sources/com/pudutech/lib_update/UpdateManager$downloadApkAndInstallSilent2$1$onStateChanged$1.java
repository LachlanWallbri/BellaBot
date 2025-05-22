package com.pudutech.lib_update;

import com.pudutech.lib_update.listener.IShowProgress2;
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

/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$downloadApkAndInstallSilent2$1$onStateChanged$1", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes5.dex */
final class UpdateManager$downloadApkAndInstallSilent2$1$onStateChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5431p$;
    final /* synthetic */ UpdateManager$downloadApkAndInstallSilent2$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateManager$downloadApkAndInstallSilent2$1$onStateChanged$1(UpdateManager$downloadApkAndInstallSilent2$1 updateManager$downloadApkAndInstallSilent2$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = updateManager$downloadApkAndInstallSilent2$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateManager$downloadApkAndInstallSilent2$1$onStateChanged$1 updateManager$downloadApkAndInstallSilent2$1$onStateChanged$1 = new UpdateManager$downloadApkAndInstallSilent2$1$onStateChanged$1(this.this$0, completion);
        updateManager$downloadApkAndInstallSilent2$1$onStateChanged$1.f5431p$ = (CoroutineScope) obj;
        return updateManager$downloadApkAndInstallSilent2$1$onStateChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateManager$downloadApkAndInstallSilent2$1$onStateChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f5431p$;
        IShowProgress2 iShowProgress2 = this.this$0.$showDownFileProgress2;
        if (iShowProgress2 != null) {
            iShowProgress2.onStartProgress(this.this$0.$mVersionCode, this.this$0.$mVersionName);
        }
        return Unit.INSTANCE;
    }
}
