package com.pudutech.pd_network.storage.ali;

import com.alibaba.sdk.android.oss.OSSClient;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageConfig;
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
/* compiled from: AliOssClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.storage.ali.AliOssClient$upload$2$onSuccess$1$url$1", m3970f = "AliOssClient.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class AliOssClient$upload$2$onSuccess$1$url$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6862p$;
    final /* synthetic */ AliOssClient$upload$2$onSuccess$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AliOssClient$upload$2$onSuccess$1$url$1(AliOssClient$upload$2$onSuccess$1 aliOssClient$upload$2$onSuccess$1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aliOssClient$upload$2$onSuccess$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AliOssClient$upload$2$onSuccess$1$url$1 aliOssClient$upload$2$onSuccess$1$url$1 = new AliOssClient$upload$2$onSuccess$1$url$1(this.this$0, completion);
        aliOssClient$upload$2$onSuccess$1$url$1.f6862p$ = (CoroutineScope) obj;
        return aliOssClient$upload$2$onSuccess$1$url$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
        return ((AliOssClient$upload$2$onSuccess$1$url$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        OSSClient oSSClient;
        StorageConfig storageConfig;
        PdUploadConfig pdUploadConfig;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6862p$;
            oSSClient = this.this$0.this$0.this$0.mOSSClient;
            storageConfig = this.this$0.this$0.this$0.storageConfig;
            String bucket = storageConfig.getBucket();
            pdUploadConfig = this.this$0.this$0.this$0.uploadConfig;
            return oSSClient.presignPublicObjectURL(bucket, pdUploadConfig.getKey());
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
