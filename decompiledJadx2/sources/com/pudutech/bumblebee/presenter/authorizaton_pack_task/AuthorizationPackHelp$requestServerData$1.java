package com.pudutech.bumblebee.presenter.authorizaton_pack_task;

import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.AuthorizationPackReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.AuthorizationPackResp;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AuthorizationPackHelp.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1", m3970f = "AuthorizationPackHelp.kt", m3971i = {0, 0}, m3972l = {235}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$collect$iv"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes4.dex */
public final class AuthorizationPackHelp$requestServerData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f4642p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public AuthorizationPackHelp$requestServerData$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AuthorizationPackHelp$requestServerData$1 authorizationPackHelp$requestServerData$1 = new AuthorizationPackHelp$requestServerData$1(completion);
        authorizationPackHelp$requestServerData$1.f4642p$ = (CoroutineScope) obj;
        return authorizationPackHelp$requestServerData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AuthorizationPackHelp$requestServerData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AuthorizationPackHelp.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/pudutech/disinfect/baselib/network/response/AuthorizationPackResp;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1$1", m3970f = "AuthorizationPackHelp.kt", m3971i = {0, 1, 1}, m3972l = {132, 138}, m3973m = "invokeSuspend", m3974n = {"$this$flow", "$this$flow", "authorizationPack"}, m3975s = {"L$0", "L$0", "L$1"})
    /* renamed from: com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1$1 */
    /* loaded from: classes4.dex */
    public static final class C40351 extends SuspendLambda implements Function2<FlowCollector<? super AuthorizationPackResp>, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private FlowCollector f4643p$;

        C40351(Continuation continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C40351 c40351 = new C40351(completion);
            c40351.f4643p$ = (FlowCollector) obj;
            return c40351;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super AuthorizationPackResp> flowCollector, Continuation<? super Unit> continuation) {
            return ((C40351) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            FlowCollector flowCollector;
            String str;
            String str2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                flowCollector = this.f4643p$;
                AuthorizationPackHelp authorizationPackHelp = AuthorizationPackHelp.INSTANCE;
                str = AuthorizationPackHelp.TAG;
                Pdlog.m3277w(str, "getAuthorizationPack 开始请求");
                NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
                AuthorizationPackReq authorizationPackReq = new AuthorizationPackReq();
                this.L$0 = flowCollector;
                this.label = 1;
                obj = cloudApi.getAuthorizationPack(authorizationPackReq, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                flowCollector = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            ApiResponse apiResponse = (ApiResponse) obj;
            AuthorizationPackHelp authorizationPackHelp2 = AuthorizationPackHelp.INSTANCE;
            str2 = AuthorizationPackHelp.TAG;
            Pdlog.m3277w(str2, "getAuthorizationPack 得到数据：" + apiResponse);
            ((AuthorizationPackResp) apiResponse.getData()).getAuthorization();
            if (apiResponse.getData() == null) {
                throw new NullPointerException();
            }
            Object data = apiResponse.getData();
            this.L$0 = flowCollector;
            this.L$1 = apiResponse;
            this.label = 2;
            if (flowCollector.emit(data, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f4642p$;
            Flow retryWhen = FlowKt.retryWhen(FlowKt.m5546catch(FlowKt.onCompletion(FlowKt.flow(new C40351(null)), new C40362(null)), new C40373(null)), new C40384(null));
            FlowCollector<AuthorizationPackResp> flowCollector = new FlowCollector<AuthorizationPackResp>() { // from class: com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1$invokeSuspend$$inlined$collect$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public Object emit(AuthorizationPackResp authorizationPackResp, Continuation continuation) {
                    String str;
                    AuthorizationPackResp authorizationPackResp2 = authorizationPackResp;
                    AuthorizationPackHelp authorizationPackHelp = AuthorizationPackHelp.INSTANCE;
                    str = AuthorizationPackHelp.TAG;
                    Pdlog.m3277w(str, "requestServerData，成功 resp = " + authorizationPackResp2);
                    Object withContext = BuildersKt.withContext(Dispatchers.getMain(), new C4034x7f33d092(authorizationPackResp2, null), continuation);
                    return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
                }
            };
            this.L$0 = coroutineScope;
            this.L$1 = retryWhen;
            this.label = 1;
            if (retryWhen.collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AuthorizationPackHelp.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/pudutech/disinfect/baselib/network/response/AuthorizationPackResp;", "cause", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1$2", m3970f = "AuthorizationPackHelp.kt", m3971i = {0, 0}, m3972l = {143}, m3973m = "invokeSuspend", m3974n = {"$this$onCompletion", "cause"}, m3975s = {"L$0", "L$1"})
    /* renamed from: com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1$2 */
    /* loaded from: classes4.dex */
    public static final class C40362 extends SuspendLambda implements Function3<FlowCollector<? super AuthorizationPackResp>, Throwable, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        /* renamed from: p$ */
        private FlowCollector f4644p$;
        private Throwable p$0;

        C40362(Continuation continuation) {
            super(3, continuation);
        }

        public final Continuation<Unit> create(FlowCollector<? super AuthorizationPackResp> create, Throwable th, Continuation<? super Unit> continuation) {
            Intrinsics.checkParameterIsNotNull(create, "$this$create");
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            C40362 c40362 = new C40362(continuation);
            c40362.f4644p$ = create;
            c40362.p$0 = th;
            return c40362;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super AuthorizationPackResp> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return ((C40362) create(flowCollector, th, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            String str2;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = this.f4644p$;
                Throwable th = this.p$0;
                if (th != null) {
                    AuthorizationPackHelp authorizationPackHelp = AuthorizationPackHelp.INSTANCE;
                    str2 = AuthorizationPackHelp.TAG;
                    Pdlog.m3277w(str2, "getAuthorizationPack 请求失败，throwable = " + th);
                    this.L$0 = flowCollector;
                    this.L$1 = th;
                    this.label = 1;
                    if (DelayKt.delay(8000L, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    AuthorizationPackHelp authorizationPackHelp2 = AuthorizationPackHelp.INSTANCE;
                    str = AuthorizationPackHelp.TAG;
                    Pdlog.m3277w(str, "getAuthorizationPack 完成");
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AuthorizationPackHelp.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/pudutech/disinfect/baselib/network/response/AuthorizationPackResp;", "it", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1$3", m3970f = "AuthorizationPackHelp.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1$3 */
    /* loaded from: classes4.dex */
    public static final class C40373 extends SuspendLambda implements Function3<FlowCollector<? super AuthorizationPackResp>, Throwable, Continuation<? super Unit>, Object> {
        int label;

        /* renamed from: p$ */
        private FlowCollector f4645p$;
        private Throwable p$0;

        C40373(Continuation continuation) {
            super(3, continuation);
        }

        public final Continuation<Unit> create(FlowCollector<? super AuthorizationPackResp> create, Throwable it, Continuation<? super Unit> continuation) {
            Intrinsics.checkParameterIsNotNull(create, "$this$create");
            Intrinsics.checkParameterIsNotNull(it, "it");
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            C40373 c40373 = new C40373(continuation);
            c40373.f4645p$ = create;
            c40373.p$0 = it;
            return c40373;
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super AuthorizationPackResp> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return ((C40373) create(flowCollector, th, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.f4645p$;
            Throwable th = this.p$0;
            AuthorizationPackHelp authorizationPackHelp = AuthorizationPackHelp.INSTANCE;
            str = AuthorizationPackHelp.TAG;
            Pdlog.m3277w(str, "getAuthorizationPack 请求失败，throwable = " + th);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: AuthorizationPackHelp.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@¢\u0006\u0004\b\b\u0010\t"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/pudutech/disinfect/baselib/network/response/AuthorizationPackResp;", "cause", "", "attempt", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1$4", m3970f = "AuthorizationPackHelp.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.bumblebee.presenter.authorizaton_pack_task.AuthorizationPackHelp$requestServerData$1$4 */
    /* loaded from: classes4.dex */
    public static final class C40384 extends SuspendLambda implements Function4<FlowCollector<? super AuthorizationPackResp>, Throwable, Long, Continuation<? super Boolean>, Object> {
        int label;

        /* renamed from: p$ */
        private FlowCollector f4646p$;
        private Throwable p$0;
        private long p$1;

        C40384(Continuation continuation) {
            super(4, continuation);
        }

        public final Continuation<Unit> create(FlowCollector<? super AuthorizationPackResp> create, Throwable cause, long j, Continuation<? super Boolean> continuation) {
            Intrinsics.checkParameterIsNotNull(create, "$this$create");
            Intrinsics.checkParameterIsNotNull(cause, "cause");
            Intrinsics.checkParameterIsNotNull(continuation, "continuation");
            C40384 c40384 = new C40384(continuation);
            c40384.f4646p$ = create;
            c40384.p$0 = cause;
            c40384.p$1 = j;
            return c40384;
        }

        @Override // kotlin.jvm.functions.Function4
        public final Object invoke(FlowCollector<? super AuthorizationPackResp> flowCollector, Throwable th, Long l, Continuation<? super Boolean> continuation) {
            return ((C40384) create(flowCollector, th, l.longValue(), continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            String str;
            int i;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FlowCollector flowCollector = this.f4646p$;
            Throwable th = this.p$0;
            long j = this.p$1;
            AuthorizationPackHelp authorizationPackHelp = AuthorizationPackHelp.INSTANCE;
            AuthorizationPackHelp.retryCount = (int) j;
            AuthorizationPackHelp authorizationPackHelp2 = AuthorizationPackHelp.INSTANCE;
            str = AuthorizationPackHelp.TAG;
            StringBuilder sb = new StringBuilder();
            sb.append("requestServerData，第");
            AuthorizationPackHelp authorizationPackHelp3 = AuthorizationPackHelp.INSTANCE;
            i = AuthorizationPackHelp.retryCount;
            sb.append(i);
            sb.append("次请求失败，正在重试，throwable = ");
            sb.append(th);
            Pdlog.m3277w(str, sb.toString());
            return Boxing.boxBoolean(th != null);
        }
    }
}
