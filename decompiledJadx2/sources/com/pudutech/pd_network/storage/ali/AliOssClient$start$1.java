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
@DebugMetadata(m3969c = "com.pudutech.pd_network.storage.ali.AliOssClient$start$1", m3970f = "AliOssClient.kt", m3971i = {0}, m3972l = {112}, m3973m = "invokeSuspend", m3974n = {"$this$toMain"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
final class AliOssClient$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6857p$;
    final /* synthetic */ AliOssClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliOssClient$start$1(AliOssClient aliOssClient, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aliOssClient;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliOssClient$start$1 aliOssClient$start$1 = new AliOssClient$start$1(this.this$0, completion);
        aliOssClient$start$1.f6857p$ = (CoroutineScope) obj;
        return aliOssClient$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AliOssClient$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            CoroutineScope coroutineScope = this.f6857p$;
            this.this$0.ossState = OssState.COMPLETED;
            ossCallback = this.this$0.callback;
            if (ossCallback != null) {
                ossState = this.this$0.ossState;
                ossCallback.onStateChanged(ossState);
            }
            CoroutineDispatcher io2 = Dispatchers.getIO();
            AliOssClient$start$1$url$1 aliOssClient$start$1$url$1 = new AliOssClient$start$1$url$1(this, null);
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = BuildersKt.withContext(io2, aliOssClient$start$1$url$1, this);
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
        ossCallback2 = this.this$0.callback;
        if (ossCallback2 != null) {
            Intrinsics.checkExpressionValueIsNotNull(url, "url");
            ossCallback2.onCompleted(url);
        }
        return Unit.INSTANCE;
    }
}
