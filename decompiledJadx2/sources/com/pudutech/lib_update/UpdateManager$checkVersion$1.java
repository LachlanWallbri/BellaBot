package com.pudutech.lib_update;

import com.loc.C3898x;
import com.pudutech.lib_update.module.model.CheckUpdateRequestParams;
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
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: UpdateManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$checkVersion$1", m3970f = "UpdateManager.kt", m3971i = {0, 1, 1, 2, 2, 3, 3}, m3972l = {41, 45, 49, 56}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "$this$run", "$this$launch", "$this$run", "$this$launch", C3898x.f4338g}, m3975s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class UpdateManager$checkVersion$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $callBack;
    final /* synthetic */ Function1 $onError;
    final /* synthetic */ CheckUpdateRequestParams $request;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5411p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UpdateManager$checkVersion$1(CheckUpdateRequestParams checkUpdateRequestParams, Function1 function1, Function1 function12, Continuation continuation) {
        super(2, continuation);
        this.$request = checkUpdateRequestParams;
        this.$callBack = function1;
        this.$onError = function12;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        UpdateManager$checkVersion$1 updateManager$checkVersion$1 = new UpdateManager$checkVersion$1(this.$request, this.$callBack, this.$onError, completion);
        updateManager$checkVersion$1.f5411p$ = (CoroutineScope) obj;
        return updateManager$checkVersion$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((UpdateManager$checkVersion$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v2 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object checkVersionUpdate;
        CoroutineScope coroutineScope;
        Object withContext;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        Object obj2 = 1;
        try {
        } catch (Exception e) {
            e = e;
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = this.f5411p$;
            try {
                UpdateRequestManager updateRequestManager = UpdateRequestManager.INSTANCE;
                String version_name = this.$request.getVersion_name();
                String version_code = this.$request.getVersion_code();
                String mac = this.$request.getMac();
                String product_name = this.$request.getProduct_name();
                String language = this.$request.getLanguage();
                this.L$0 = coroutineScope2;
                this.label = 1;
                checkVersionUpdate = updateRequestManager.checkVersionUpdate(version_name, version_code, mac, product_name, language, this);
                if (checkVersionUpdate == coroutine_suspended) {
                    return coroutine_suspended;
                }
                coroutineScope = coroutineScope2;
            } catch (Exception e2) {
                e = e2;
                obj2 = coroutineScope2;
                NetWorkLog.INSTANCE.mo3280i("UpdateManager", "checkVersion.error > " + e);
                MainCoroutineDispatcher main = Dispatchers.getMain();
                C46582 c46582 = new C46582(e, null);
                this.L$0 = obj2;
                this.L$1 = e;
                this.label = 4;
                if (BuildersKt.withContext(main, c46582, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            }
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                } else if (i == 3) {
                    ResultKt.throwOnFailure(obj);
                    withContext = obj;
                } else {
                    if (i != 4) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            checkVersionUpdate = obj;
        }
        BaseResponse baseResponse = (BaseResponse) checkVersionUpdate;
        NetWorkLog.INSTANCE.mo3280i("UpdateManager", "checkVersion > res = " + baseResponse);
        if (baseResponse.isSuccess() && baseResponse.getData() != null) {
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            UpdateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$1 updateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$1 = new UpdateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$1(baseResponse, null, this);
            this.L$0 = coroutineScope;
            this.L$1 = baseResponse;
            this.label = 2;
            if (BuildersKt.withContext(main2, updateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$1, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
        MainCoroutineDispatcher main3 = Dispatchers.getMain();
        UpdateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2 updateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2 = new UpdateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2(null, this);
        this.L$0 = coroutineScope;
        this.L$1 = baseResponse;
        this.label = 3;
        withContext = BuildersKt.withContext(main3, updateManager$checkVersion$1$invokeSuspend$$inlined$run$lambda$2, this);
        if (withContext == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: UpdateManager.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.lib_update.UpdateManager$checkVersion$1$2", m3970f = "UpdateManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.lib_update.UpdateManager$checkVersion$1$2 */
    /* loaded from: classes5.dex */
    public static final class C46582 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {

        /* renamed from: $e */
        final /* synthetic */ Exception f5412$e;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f5413p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C46582(Exception exc, Continuation continuation) {
            super(2, continuation);
            this.f5412$e = exc;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C46582 c46582 = new C46582(this.f5412$e, completion);
            c46582.f5413p$ = (CoroutineScope) obj;
            return c46582;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C46582) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5413p$;
            Function1 function1 = UpdateManager$checkVersion$1.this.$onError;
            if (function1 != null) {
                return (Unit) function1.invoke(this.f5412$e);
            }
            return null;
        }
    }
}
