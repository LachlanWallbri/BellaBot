package com.pudutech.peanut.robot_ui.viewmodel;

import android.util.Log;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.PromotionsReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.PromoBean;
import com.pudutech.peanut.robot_ui.config.Constans;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PromotionsViewModel.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.viewmodel.PromotionsViewModel$getPromotionsData$1", m3970f = "PromotionsViewModel.kt", m3971i = {0, 1, 1}, m3972l = {33, 35}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$launch", "resp"}, m3975s = {"L$0", "L$0", "L$1"})
/* loaded from: classes5.dex */
public final class PromotionsViewModel$getPromotionsData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $page;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7064p$;
    final /* synthetic */ PromotionsViewModel this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PromotionsViewModel$getPromotionsData$1(PromotionsViewModel promotionsViewModel, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = promotionsViewModel;
        this.$page = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PromotionsViewModel$getPromotionsData$1 promotionsViewModel$getPromotionsData$1 = new PromotionsViewModel$getPromotionsData$1(this.this$0, this.$page, completion);
        promotionsViewModel$getPromotionsData$1.f7064p$ = (CoroutineScope) obj;
        return promotionsViewModel$getPromotionsData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PromotionsViewModel$getPromotionsData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r15v11, types: [com.pudutech.disinfect.baselib.network.response.ApiResponse, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        CoroutineScope coroutineScope;
        Ref.ObjectRef objectRef;
        Ref.ObjectRef objectRef2;
        String str2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Exception e) {
            str = this.this$0.TAG;
            Pdlog.m3273d(str, "getTableGroup " + Log.getStackTraceString(e));
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f7064p$;
            objectRef = new Ref.ObjectRef();
            NetWorkApiManager.CloudApiService cloudApi = NetWorkApiManager.INSTANCE.getCloudApi();
            PromotionsReq promotionsReq = new PromotionsReq(this.$page * 10, 10, Constans.INSTANCE.getShopInfo().getShop_id());
            this.L$0 = coroutineScope;
            this.L$1 = objectRef;
            this.L$2 = objectRef;
            this.label = 1;
            obj = NetWorkApiManager.CloudApiService.DefaultImpls.getPromotionsData$default(cloudApi, promotionsReq, null, this, 2, null);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef2 = objectRef;
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            objectRef = (Ref.ObjectRef) this.L$2;
            objectRef2 = (Ref.ObjectRef) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        objectRef.element = (ApiResponse) obj;
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "getTableGroup " + ((ApiResponse) objectRef2.element));
        MainCoroutineDispatcher main = Dispatchers.getMain();
        C56121 c56121 = new C56121(objectRef2, null);
        this.L$0 = coroutineScope;
        this.L$1 = objectRef2;
        this.label = 2;
        if (BuildersKt.withContext(main, c56121, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: PromotionsViewModel.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.viewmodel.PromotionsViewModel$getPromotionsData$1$1", m3970f = "PromotionsViewModel.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.peanut.robot_ui.viewmodel.PromotionsViewModel$getPromotionsData$1$1 */
    /* loaded from: classes5.dex */
    public static final class C56121 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $resp;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7065p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C56121(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$resp = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C56121 c56121 = new C56121(this.$resp, completion);
            c56121.f7065p$ = (CoroutineScope) obj;
            return c56121;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C56121) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7065p$;
            if (((ApiResponse) this.$resp.element).getData() == null) {
                PromotionsViewModel$getPromotionsData$1.this.this$0.getMPromotionsDada().postValue(null);
            } else {
                PromoBean promoBean = (PromoBean) ((ApiResponse) this.$resp.element).getData();
                PromotionsViewModel$getPromotionsData$1.this.this$0.getMPromotionsDada().postValue(promoBean != null ? promoBean.getRows() : null);
            }
            return Unit.INSTANCE;
        }
    }
}
