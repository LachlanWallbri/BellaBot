package com.pudutech.pd_network.storage.ali;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: AliOssClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.storage.ali.AliOssClient$upload$2$onFailure$1", m3970f = "AliOssClient.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
final class AliOssClient$upload$2$onFailure$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ClientException $clientException;
    final /* synthetic */ ServiceException $serviceException;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6860p$;
    final /* synthetic */ AliOssClient$upload$2 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliOssClient$upload$2$onFailure$1(AliOssClient$upload$2 aliOssClient$upload$2, ClientException clientException, ServiceException serviceException, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aliOssClient$upload$2;
        this.$clientException = clientException;
        this.$serviceException = serviceException;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliOssClient$upload$2$onFailure$1 aliOssClient$upload$2$onFailure$1 = new AliOssClient$upload$2$onFailure$1(this.this$0, this.$clientException, this.$serviceException, completion);
        aliOssClient$upload$2$onFailure$1.f6860p$ = (CoroutineScope) obj;
        return aliOssClient$upload$2$onFailure$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AliOssClient$upload$2$onFailure$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        OssCallback ossCallback;
        OssCallback ossCallback2;
        OssState ossState;
        boolean z;
        OssState ossState2;
        OssCallback ossCallback3;
        OssState ossState3;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6860p$;
        ClientException clientException = this.$clientException;
        if (!Intrinsics.areEqual(clientException != null ? clientException.isCanceledException() : null, Boxing.boxBoolean(true))) {
            this.this$0.this$0.ossState = OssState.FAILED;
            ossCallback = this.this$0.this$0.callback;
            if (ossCallback != null) {
                ossState = this.this$0.this$0.ossState;
                ossCallback.onStateChanged(ossState);
            }
            ossCallback2 = this.this$0.this$0.callback;
            if (ossCallback2 != null) {
                Exception exc = this.$clientException;
                if (exc == null) {
                    exc = this.$serviceException;
                }
                Exception exc2 = exc;
                if (exc2 == null) {
                    exc2 = new Exception();
                }
                ossCallback2.onError(exc2);
            }
            return Unit.INSTANCE;
        }
        AliOssClient aliOssClient = this.this$0.this$0;
        z = this.this$0.this$0.canResume;
        if (z) {
            ossState2 = OssState.PAUSED;
        } else {
            ossState2 = OssState.CANCELED;
        }
        aliOssClient.ossState = ossState2;
        ossCallback3 = this.this$0.this$0.callback;
        if (ossCallback3 != null) {
            ossState3 = this.this$0.this$0.ossState;
            ossCallback3.onStateChanged(ossState3);
        }
        return Unit.INSTANCE;
    }
}
