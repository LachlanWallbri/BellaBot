package com.pudutech.lib_update;

import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.loc.C3898x;
import com.pudutech.lib_update.listener.IShowProgress;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
import com.pudutech.lib_update.module.model.VerionResult;
import com.pudutech.lib_update.module.service.UpdateRequestManager;
import com.pudutech.pd_network.bean.BaseResponse;
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
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$update$1", m3970f = "UpdateManager.kt", m3971i = {0, 1, 1, 2, 2}, m3972l = {77, 84, 90}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_RES, "$this$launch", C3898x.f4338g}, m3975s = {"L$0", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class UpdateManager$update$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CheckUpdateRequestParams $request;
    final /* synthetic */ IShowProgress $showDownFileProgress;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5434p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateManager$update$1(CheckUpdateRequestParams checkUpdateRequestParams, IShowProgress iShowProgress, Continuation continuation) {
        super(2, continuation);
        this.$request = checkUpdateRequestParams;
        this.$showDownFileProgress = iShowProgress;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateManager$update$1 updateManager$update$1 = new UpdateManager$update$1(this.$request, this.$showDownFileProgress, completion);
        updateManager$update$1.f5434p$ = (CoroutineScope) obj;
        return updateManager$update$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateManager$update$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [int, java.lang.Object] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
        } catch (Exception e) {
            NetWorkLog.INSTANCE.mo3279e("UpdateManager", "update > uri e:" + e);
            MainCoroutineDispatcher main = Dispatchers.getMain();
            C46682 c46682 = new C46682(e, null);
            this.L$0 = r1;
            this.L$1 = e;
            this.label = 3;
            if (BuildersKt.withContext(main, c46682, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5434p$;
            UpdateRequestManager updateRequestManager = UpdateRequestManager.INSTANCE;
            String version_name = this.$request.getVersion_name();
            String version_code = this.$request.getVersion_code();
            String mac = this.$request.getMac();
            String product_name = this.$request.getProduct_name();
            String language = this.$request.getLanguage();
            this.L$0 = coroutineScope;
            this.label = 1;
            obj = updateRequestManager.checkVersionUpdate(version_name, version_code, mac, product_name, language, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (r1 != 1) {
                if (r1 == 2) {
                    ResultKt.throwOnFailure(obj);
                } else {
                    if (r1 != 3) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        BaseResponse baseResponse = (BaseResponse) obj;
        NetWorkLog.INSTANCE.mo3280i("UpdateManager", "update > res = " + baseResponse);
        VerionResult verionResult = (VerionResult) baseResponse.getData();
        if ((verionResult != null ? verionResult.getVersion() : null) != null) {
            UpdateManager updateManager = UpdateManager.INSTANCE;
            Object data = baseResponse.getData();
            if (data == null) {
                Intrinsics.throwNpe();
            }
            updateManager.downloadApkAndInstallSilent((VerionResult) data, this.$showDownFileProgress);
        } else {
            NetWorkLog.INSTANCE.mo3279e("UpdateManager", "--checkSoftWareVersionSilent----->version is null ");
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            C46671 c46671 = new C46671(null);
            this.L$0 = coroutineScope;
            this.L$1 = baseResponse;
            this.label = 2;
            if (BuildersKt.withContext(main2, c46671, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$update$1$1", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.UpdateManager$update$1$1 */
    /* loaded from: classes5.dex */
    public static final class C46671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5435p$;

        C46671(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46671 c46671 = new C46671(completion);
            c46671.f5435p$ = (CoroutineScope) obj;
            return c46671;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5435p$;
            IShowProgress iShowProgress = UpdateManager$update$1.this.$showDownFileProgress;
            if (iShowProgress == null) {
                return null;
            }
            iShowProgress.onFail(new Throwable("--checkSoftWareVersionSilent----->version is null"));
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$update$1$2", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.UpdateManager$update$1$2 */
    /* loaded from: classes5.dex */
    public static final class C46682 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $e */
        final /* synthetic */ Exception f5436$e;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5437p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C46682(Exception exc, Continuation continuation) {
            super(2, continuation);
            this.f5436$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46682 c46682 = new C46682(this.f5436$e, completion);
            c46682.f5437p$ = (CoroutineScope) obj;
            return c46682;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46682) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5437p$;
            IShowProgress iShowProgress = UpdateManager$update$1.this.$showDownFileProgress;
            if (iShowProgress == null) {
                return null;
            }
            iShowProgress.onFail(this.f5436$e);
            return Unit.INSTANCE;
        }
    }
}
