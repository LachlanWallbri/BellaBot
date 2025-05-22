package com.pudutech.pd_network.storage;

import android.content.Context;
import com.pudutech.pd_network.IOSSClient;
import com.pudutech.pd_network.OssCallback;
import com.pudutech.pd_network.OssState;
import com.pudutech.pd_network.bean.StorageBucketType;
import com.pudutech.pd_network.log.NetWorkLog;
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
/* compiled from: StorageComponent.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.storage.StorageComponent$delete$1", m3970f = "StorageComponent.kt", m3971i = {0}, m3972l = {74}, m3973m = "invokeSuspend", m3974n = {"$this$launch"}, m3975s = {"L$0"})
/* loaded from: classes6.dex */
public final class StorageComponent$delete$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ StorageBucketType $bucketType;
    final /* synthetic */ OssCallback $callback;
    final /* synthetic */ String $objectKey;
    Object L$0;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6852p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StorageComponent$delete$1(StorageBucketType storageBucketType, String str, OssCallback ossCallback, Continuation continuation) {
        super(2, continuation);
        this.$bucketType = storageBucketType;
        this.$objectKey = str;
        this.$callback = ossCallback;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        StorageComponent$delete$1 storageComponent$delete$1 = new StorageComponent$delete$1(this.$bucketType, this.$objectKey, this.$callback, completion);
        storageComponent$delete$1.f6852p$ = (CoroutineScope) obj;
        return storageComponent$delete$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((StorageComponent$delete$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String TAG;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                CoroutineScope coroutineScope = this.f6852p$;
                Context access$getContext$p = StorageComponent.access$getContext$p(StorageComponent.INSTANCE);
                StorageBucketType storageBucketType = this.$bucketType;
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = ExtKt.getStorageClient(access$getContext$p, storageBucketType, null, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            ((IOSSClient) obj).delete(this.$objectKey, this.$callback);
        } catch (Exception e) {
            e.printStackTrace();
            NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
            StorageComponent storageComponent = StorageComponent.INSTANCE;
            TAG = StorageComponent.TAG;
            Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
            netWorkLog.mo3280i(TAG, "delete.error > " + e);
            StorageComponentKt.toMain(new C54811(e, null));
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: StorageComponent.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.pd_network.storage.StorageComponent$delete$1$1", m3970f = "StorageComponent.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.pd_network.storage.StorageComponent$delete$1$1 */
    /* loaded from: classes6.dex */
    public static final class C54811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $e */
        final /* synthetic */ Exception f6853$e;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f6854p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C54811(Exception exc, Continuation continuation) {
            super(2, continuation);
            this.f6853$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C54811 c54811 = new C54811(this.f6853$e, completion);
            c54811.f6854p$ = (CoroutineScope) obj;
            return c54811;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C54811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f6854p$;
            StorageComponent$delete$1.this.$callback.onStateChanged(OssState.FAILED);
            StorageComponent$delete$1.this.$callback.onError(this.f6853$e);
            return Unit.INSTANCE;
        }
    }
}
