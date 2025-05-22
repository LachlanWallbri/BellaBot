package com.pudutech.pd_network.storage.minio;

import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageConfig;
import io.minio.MinioClient;
import io.minio.ObjectWriteResponse;
import io.minio.PutObjectArgs;
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

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: MinioOssClient.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.storage.minio.MinioOssClient$upload$1", m3970f = "MinioOssClient.kt", m3971i = {0}, m3972l = {64}, m3973m = "invokeSuspend", m3974n = {"$this$toMain"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class MinioOssClient$upload$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PutObjectArgs $request;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6866p$;
    final /* synthetic */ MinioOssClient this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MinioOssClient$upload$1(MinioOssClient minioOssClient, PutObjectArgs putObjectArgs, Continuation continuation) {
        super(2, continuation);
        this.this$0 = minioOssClient;
        this.$request = putObjectArgs;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        MinioOssClient$upload$1 minioOssClient$upload$1 = new MinioOssClient$upload$1(this.this$0, this.$request, completion);
        minioOssClient$upload$1.f6866p$ = (CoroutineScope) obj;
        return minioOssClient$upload$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MinioOssClient$upload$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        OssCallback ossCallback;
        OssCallback ossCallback2;
        OssCallback ossCallback3;
        StorageConfig storageConfig;
        StorageConfig storageConfig2;
        PdUploadConfig pdUploadConfig;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6866p$;
            ossCallback = this.this$0.callback;
            if (ossCallback != null) {
                ossCallback.onStateChanged(OssState.IN_PROGRESS);
            }
            CoroutineDispatcher io2 = Dispatchers.getIO();
            C54831 c54831 = new C54831(null);
            this.L$0 = coroutineScope;
            this.label = 1;
            if (BuildersKt.withContext(io2, c54831, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        ossCallback2 = this.this$0.callback;
        if (ossCallback2 != null) {
            ossCallback2.onStateChanged(OssState.COMPLETED);
        }
        ossCallback3 = this.this$0.callback;
        if (ossCallback3 != null) {
            StringBuilder sb = new StringBuilder();
            storageConfig = this.this$0.ossConfig;
            sb.append(storageConfig.getOss_url());
            sb.append('/');
            storageConfig2 = this.this$0.ossConfig;
            sb.append(storageConfig2.getBucket());
            sb.append('/');
            pdUploadConfig = this.this$0.uploadConfig;
            sb.append(pdUploadConfig.getKey());
            ossCallback3.onCompleted(sb.toString());
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: MinioOssClient.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "Lio/minio/ObjectWriteResponse;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.pd_network.storage.minio.MinioOssClient$upload$1$1", m3970f = "MinioOssClient.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.pd_network.storage.minio.MinioOssClient$upload$1$1 */
    /* loaded from: classes6.dex */
    public static final class C54831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ObjectWriteResponse>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6867p$;

        C54831(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C54831 c54831 = new C54831(completion);
            c54831.f6867p$ = (CoroutineScope) obj;
            return c54831;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ObjectWriteResponse> continuation) {
            return ((C54831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MinioClient minioClient;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6867p$;
            minioClient = MinioOssClient$upload$1.this.this$0.client;
            return minioClient.putObject(MinioOssClient$upload$1.this.$request);
        }
    }
}
