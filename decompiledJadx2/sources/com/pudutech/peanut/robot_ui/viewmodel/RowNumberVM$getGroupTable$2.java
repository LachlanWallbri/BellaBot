package com.pudutech.peanut.robot_ui.viewmodel;

import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.google.gson.Gson;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.TableReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.TableGroupBean;
import com.pudutech.peanut.robot_ui.config.Constans;
import java.util.List;
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
/* compiled from: RowNumberVM.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.viewmodel.RowNumberVM$getGroupTable$2", m3970f = "RowNumberVM.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {48, 50}, m3973m = "invokeSuspend", m3974n = {"$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "resp"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
public final class RowNumberVM$getGroupTable$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ int $mShopId;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7066p$;
    final /* synthetic */ RowNumberVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RowNumberVM$getGroupTable$2(RowNumberVM rowNumberVM, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = rowNumberVM;
        this.$mShopId = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RowNumberVM$getGroupTable$2 rowNumberVM$getGroupTable$2 = new RowNumberVM$getGroupTable$2(this.this$0, this.$mShopId, completion);
        rowNumberVM$getGroupTable$2.f7066p$ = (CoroutineScope) obj;
        return rowNumberVM$getGroupTable$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RowNumberVM$getGroupTable$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v8, types: [com.pudutech.disinfect.baselib.network.response.ApiResponse, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Ref.ObjectRef objectRef;
        Object tableGroup$default;
        CoroutineScope coroutineScope;
        TableReq tableReq;
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
            CoroutineScope coroutineScope2 = this.f7066p$;
            TableReq tableReq2 = new TableReq();
            tableReq2.setShop_id(this.$mShopId);
            tableReq2.setStatus(1);
            objectRef = new Ref.ObjectRef();
            NetWorkApiManager.SolicitApiService solicit = NetWorkApiManager.INSTANCE.getSolicit();
            this.L$0 = coroutineScope2;
            this.L$1 = tableReq2;
            this.L$2 = objectRef;
            this.L$3 = objectRef;
            this.label = 1;
            tableGroup$default = NetWorkApiManager.SolicitApiService.DefaultImpls.getTableGroup$default(solicit, tableReq2, null, this, 2, null);
            if (tableGroup$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            tableReq = tableReq2;
            objectRef2 = objectRef;
        } else {
            if (i != 1) {
                if (i == 2) {
                    ResultKt.throwOnFailure(obj);
                    return Unit.INSTANCE;
                }
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            Ref.ObjectRef objectRef3 = (Ref.ObjectRef) this.L$3;
            objectRef2 = (Ref.ObjectRef) this.L$2;
            tableReq = (TableReq) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            objectRef = objectRef3;
            tableGroup$default = obj;
        }
        objectRef.element = (ApiResponse) tableGroup$default;
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "getTableGroup " + ((ApiResponse) objectRef2.element));
        MainCoroutineDispatcher main = Dispatchers.getMain();
        C56131 c56131 = new C56131(objectRef2, null);
        this.L$0 = coroutineScope;
        this.L$1 = tableReq;
        this.L$2 = objectRef2;
        this.label = 2;
        if (BuildersKt.withContext(main, c56131, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RowNumberVM.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.viewmodel.RowNumberVM$getGroupTable$2$1", m3970f = "RowNumberVM.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.peanut.robot_ui.viewmodel.RowNumberVM$getGroupTable$2$1 */
    /* loaded from: classes5.dex */
    public static final class C56131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $resp;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7067p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C56131(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$resp = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C56131 c56131 = new C56131(this.$resp, completion);
            c56131.f7067p$ = (CoroutineScope) obj;
            return c56131;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C56131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7067p$;
            List<TableGroupBean> list = (List) ((ApiResponse) this.$resp.element).getData();
            if (list != null) {
                Constans constans = Constans.INSTANCE;
                String json = new Gson().toJson(list);
                Intrinsics.checkExpressionValueIsNotNull(json, "Gson().toJson(it)");
                constans.setShopGroup(json);
                RowNumberVM$getGroupTable$2.this.this$0.getMGroupData().postValue(list);
            }
            return Unit.INSTANCE;
        }
    }
}
