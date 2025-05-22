package com.pudutech.peanut.robot_ui.viewmodel;

import android.util.Log;
import com.aliyun.alink.linksdk.tmp.utils.TmpConstant;
import com.pudutech.base.Pdlog;
import com.pudutech.disinfect.baselib.network.NetWorkApiManager;
import com.pudutech.disinfect.baselib.network.req.TableNumberReq;
import com.pudutech.disinfect.baselib.network.response.ApiResponse;
import com.pudutech.disinfect.baselib.network.response.TableBean;
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
@DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.viewmodel.RowNumberVM$getTableNumber$1", m3970f = "RowNumberVM.kt", m3971i = {0, 0, 1, 1, 1}, m3972l = {74, 76}, m3973m = "invokeSuspend", m3974n = {"$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "$this$launch", TmpConstant.KEY_IOT_PERFORMANCE_EVENT_REQ, "resp"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$2"})
/* loaded from: classes5.dex */
public final class RowNumberVM$getTableNumber$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $mCode;
    final /* synthetic */ int $mShopId;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f7068p$;
    final /* synthetic */ RowNumberVM this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RowNumberVM$getTableNumber$1(RowNumberVM rowNumberVM, int i, String str, Continuation continuation) {
        super(2, continuation);
        this.this$0 = rowNumberVM;
        this.$mShopId = i;
        this.$mCode = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        RowNumberVM$getTableNumber$1 rowNumberVM$getTableNumber$1 = new RowNumberVM$getTableNumber$1(this.this$0, this.$mShopId, this.$mCode, completion);
        rowNumberVM$getTableNumber$1.f7068p$ = (CoroutineScope) obj;
        return rowNumberVM$getTableNumber$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RowNumberVM$getTableNumber$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Type inference failed for: r1v9, types: [com.pudutech.disinfect.baselib.network.response.ApiResponse, T] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        String str;
        Ref.ObjectRef objectRef;
        Object tableNumber$default;
        CoroutineScope coroutineScope;
        TableNumberReq tableNumberReq;
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
            CoroutineScope coroutineScope2 = this.f7068p$;
            TableNumberReq tableNumberReq2 = new TableNumberReq();
            tableNumberReq2.setShop_id(this.$mShopId);
            tableNumberReq2.setGroup_code(this.$mCode);
            objectRef = new Ref.ObjectRef();
            NetWorkApiManager.SolicitApiService solicit = NetWorkApiManager.INSTANCE.getSolicit();
            this.L$0 = coroutineScope2;
            this.L$1 = tableNumberReq2;
            this.L$2 = objectRef;
            this.L$3 = objectRef;
            this.label = 1;
            tableNumber$default = NetWorkApiManager.SolicitApiService.DefaultImpls.getTableNumber$default(solicit, tableNumberReq2, null, this, 2, null);
            if (tableNumber$default == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            tableNumberReq = tableNumberReq2;
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
            tableNumberReq = (TableNumberReq) this.L$1;
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            objectRef = objectRef3;
            tableNumber$default = obj;
        }
        objectRef.element = (ApiResponse) tableNumber$default;
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "getTableGroup " + ((ApiResponse) objectRef2.element));
        MainCoroutineDispatcher main = Dispatchers.getMain();
        C56141 c56141 = new C56141(objectRef2, null);
        this.L$0 = coroutineScope;
        this.L$1 = tableNumberReq;
        this.L$2 = objectRef2;
        this.label = 2;
        if (BuildersKt.withContext(main, c56141, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: RowNumberVM.kt */
    @Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
    @DebugMetadata(m3969c = "com.pudutech.peanut.robot_ui.viewmodel.RowNumberVM$getTableNumber$1$1", m3970f = "RowNumberVM.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
    /* renamed from: com.pudutech.peanut.robot_ui.viewmodel.RowNumberVM$getTableNumber$1$1 */
    /* loaded from: classes5.dex */
    public static final class C56141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.ObjectRef $resp;
        int label;

        /* renamed from: p$ */
        private CoroutineScope f7069p$;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C56141(Ref.ObjectRef objectRef, Continuation continuation) {
            super(2, continuation);
            this.$resp = objectRef;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
            Intrinsics.checkParameterIsNotNull(completion, "completion");
            C56141 c56141 = new C56141(this.$resp, completion);
            c56141.f7069p$ = (CoroutineScope) obj;
            return c56141;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C56141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f7069p$;
            TableBean tableBean = (TableBean) ((ApiResponse) this.$resp.element).getData();
            if (tableBean != null) {
                RowNumberVM$getTableNumber$1.this.this$0.getMTabeData().postValue(tableBean);
            }
            return Unit.INSTANCE;
        }
    }
}
