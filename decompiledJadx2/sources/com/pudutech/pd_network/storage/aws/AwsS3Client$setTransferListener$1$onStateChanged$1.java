package com.pudutech.pd_network.storage.aws;

import com.amazonaws.mobileconnectors.p047s3.transferutility.TransferState;
import com.amazonaws.services.p048s3.AmazonS3Client;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
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

/* compiled from: AwsS3Client.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.storage.aws.AwsS3Client$setTransferListener$1$onStateChanged$1", m3970f = "AwsS3Client.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
final class AwsS3Client$setTransferListener$1$onStateChanged$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ TransferState $state;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6865p$;
    final /* synthetic */ AwsS3Client$setTransferListener$1 this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AwsS3Client$setTransferListener$1$onStateChanged$1(AwsS3Client$setTransferListener$1 awsS3Client$setTransferListener$1, TransferState transferState, Continuation continuation) {
        super(2, continuation);
        this.this$0 = awsS3Client$setTransferListener$1;
        this.$state = transferState;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AwsS3Client$setTransferListener$1$onStateChanged$1 awsS3Client$setTransferListener$1$onStateChanged$1 = new AwsS3Client$setTransferListener$1$onStateChanged$1(this.this$0, this.$state, completion);
        awsS3Client$setTransferListener$1$onStateChanged$1.f6865p$ = (CoroutineScope) obj;
        return awsS3Client$setTransferListener$1$onStateChanged$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AwsS3Client$setTransferListener$1$onStateChanged$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        OssState ossState;
        OssCallback ossCallback;
        AmazonS3Client amazonS3Client;
        StorageConfig storageConfig;
        OssCallback ossCallback2;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6865p$;
        ossState = this.this$0.this$0.toOssState(this.$state);
        ossCallback = this.this$0.this$0.callback;
        if (ossCallback != null) {
            ossCallback.onStateChanged(ossState);
        }
        if (ossState == OssState.COMPLETED) {
            amazonS3Client = this.this$0.this$0.client;
            storageConfig = this.this$0.this$0.storageConfig;
            String url = amazonS3Client.getResourceUrl(storageConfig.getBucket(), this.this$0.$key);
            ossCallback2 = this.this$0.this$0.callback;
            if (ossCallback2 != null) {
                Intrinsics.checkExpressionValueIsNotNull(url, "url");
                ossCallback2.onCompleted(url);
            }
        }
        return Unit.INSTANCE;
    }
}
