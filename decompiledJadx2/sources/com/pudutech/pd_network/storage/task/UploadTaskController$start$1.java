package com.pudutech.pd_network.storage.task;

import android.content.Context;
import com.pudutech.pd_network.IOSSClient;
import com.pudutech.pd_network.IOssTaskController;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.bean.PdUploadConfig;
import com.pudutech.pd_network.bean.StorageBucketType;
import com.pudutech.pd_network.log.NetWorkLog;
import com.pudutech.pd_network.storage.ExtKt;
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

/* compiled from: UploadTaskController.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.storage.task.UploadTaskController$start$1", m3970f = "UploadTaskController.kt", m3971i = {0}, m3972l = {85}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
final class UploadTaskController$start$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6870p$;
    final /* synthetic */ UploadTaskController this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UploadTaskController$start$1(UploadTaskController uploadTaskController, Continuation continuation) {
        super(2, continuation);
        this.this$0 = uploadTaskController;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UploadTaskController$start$1 uploadTaskController$start$1 = new UploadTaskController$start$1(this.this$0, completion);
        uploadTaskController$start$1.f6870p$ = (CoroutineScope) obj;
        return uploadTaskController$start$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UploadTaskController$start$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        UploadTaskController$mCallback$1 uploadTaskController$mCallback$1;
        UploadTaskController$mCallback$1 uploadTaskController$mCallback$12;
        Context context;
        PdUploadConfig pdUploadConfig;
        PdUploadConfig pdUploadConfig2;
        UploadTaskController uploadTaskController;
        UploadTaskController$mCallback$1 uploadTaskController$mCallback$13;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f6870p$;
                UploadTaskController uploadTaskController2 = this.this$0;
                context = this.this$0.context;
                pdUploadConfig = this.this$0.config;
                StorageBucketType bucketType = pdUploadConfig.getBucketType();
                pdUploadConfig2 = this.this$0.config;
                this.L$0 = coroutineScope;
                this.L$1 = uploadTaskController2;
                this.label = 1;
                obj = ExtKt.getStorageClient(context, bucketType, pdUploadConfig2, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                uploadTaskController = uploadTaskController2;
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                uploadTaskController = (UploadTaskController) this.L$1;
                ResultKt.throwOnFailure(obj);
            }
            IOSSClient iOSSClient = (IOSSClient) obj;
            uploadTaskController$mCallback$13 = this.this$0.mCallback;
            iOSSClient.setCallback(uploadTaskController$mCallback$13);
            iOSSClient.start();
            uploadTaskController.controller = (IOssTaskController) obj;
        } catch (Exception e) {
            NetWorkLog.INSTANCE.mo3280i(this.this$0.TAG, "start.error " + e.getMessage());
            uploadTaskController$mCallback$1 = this.this$0.mCallback;
            uploadTaskController$mCallback$1.onStateChanged(OssState.FAILED);
            uploadTaskController$mCallback$12 = this.this$0.mCallback;
            uploadTaskController$mCallback$12.onError(e);
        }
        return Unit.INSTANCE;
    }
}
