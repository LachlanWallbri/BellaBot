package com.pudutech.pd_network.storage.ali;

import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;

/* compiled from: AliOssClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.storage.ali.AliOssClient$upload$2$onSuccess$1", m3970f = "AliOssClient.kt", m3971i = {0}, m3972l = {183}, m3973m = "invokeSuspend", m3974n = {"$this$toMain"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
final class AliOssClient$upload$2$onSuccess$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6861p$;
    final /* synthetic */ AliOssClient$upload$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliOssClient$upload$2$onSuccess$1(AliOssClient$upload$2 aliOssClient$upload$2, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aliOssClient$upload$2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliOssClient$upload$2$onSuccess$1 aliOssClient$upload$2$onSuccess$1 = new AliOssClient$upload$2$onSuccess$1(this.this$0, completion);
        aliOssClient$upload$2$onSuccess$1.f6861p$ = (CoroutineScope) obj;
        return aliOssClient$upload$2$onSuccess$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AliOssClient$upload$2$onSuccess$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        OssCallback ossCallback;
        OssState ossState;
        OssCallback ossCallback2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6861p$;
            this.this$0.this$0.ossState = OssState.COMPLETED;
            ossCallback = this.this$0.this$0.callback;
            if (ossCallback != null) {
                ossState = this.this$0.this$0.ossState;
                ossCallback.onStateChanged(ossState);
            }
            CoroutineDispatcher io2 = Dispatchers.getIO();
            AliOssClient$upload$2$onSuccess$1$url$1 aliOssClient$upload$2$onSuccess$1$url$1 = new AliOssClient$upload$2$onSuccess$1$url$1(this, null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = BuildersKt.withContext(io2, aliOssClient$upload$2$onSuccess$1$url$1, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        String url = (String) obj;
        ossCallback2 = this.this$0.this$0.callback;
        if (ossCallback2 != null) {
            Intrinsics.checkExpressionValueIsNotNull(url, "url");
            ossCallback2.onCompleted(url);
        }
        return Unit.INSTANCE;
    }
}
