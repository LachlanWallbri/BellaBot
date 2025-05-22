package com.pudutech.location;

import com.pudutech.base.Pdlog;
import com.pudutech.location.net.DataCardStatus;
import com.pudutech.location.net.FlowCardReq;
import com.pudutech.location.net.FlowCardRes;
import com.pudutech.location.net.FlowCardStatusRes;
import com.pudutech.location.net.NetWorkManager;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FlowCardManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.location.FlowCardManager$activateFlowCard$1", m3970f = "FlowCardManager.kt", m3971i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 3, 3}, m3972l = {44, 48, 58, 67}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$runCatching", "activateFlowCard", "$this$launch", "$this$runCatching", "activateFlowCard", "statusFlowCard", "$this$launch", "it", "$this$launch", "it"}, m3975s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$2", "L$0", "L$2"})
/* loaded from: classes5.dex */
public final class FlowCardManager$activateFlowCard$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $callback;
    final /* synthetic */ Ref.ObjectRef $simSerialNumber;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5487p$;
    final /* synthetic */ FlowCardManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowCardManager$activateFlowCard$1(FlowCardManager flowCardManager, Ref.ObjectRef objectRef, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = flowCardManager;
        this.$simSerialNumber = objectRef;
        this.$callback = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FlowCardManager$activateFlowCard$1 flowCardManager$activateFlowCard$1 = new FlowCardManager$activateFlowCard$1(this.this$0, this.$simSerialNumber, this.$callback, completion);
        flowCardManager$activateFlowCard$1.f5487p$ = (CoroutineScope) obj;
        return flowCardManager$activateFlowCard$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FlowCardManager$activateFlowCard$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x017c  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        FlowCardRes flowCardRes;
        NetWorkManager netWorkManager;
        FlowCardStatusRes flowCardStatusRes;
        CoroutineScope coroutineScope2;
        NetWorkManager.ServerApi serverApi;
        Object statusFlowCard;
        String str;
        String str2;
        CoroutineScope coroutineScope3;
        DataCardStatus data;
        String str3;
        DataCardStatus data2;
        NetWorkManager netWorkManager2;
        NetWorkManager.ServerApi serverApi2;
        Object activateFlowCard;
        Object m4510constructorimpl;
        String str4;
        Throwable m4513exceptionOrNullimpl;
        String str5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            coroutineScope = this.f5487p$;
            try {
                Result.Companion companion = Result.INSTANCE;
                flowCardRes = (FlowCardRes) null;
                netWorkManager = this.this$0.mNetWorkManager;
            } catch (Throwable th) {
                th = th;
                Result.Companion companion2 = Result.INSTANCE;
                m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                coroutineScope3 = coroutineScope;
                if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                }
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                if (m4513exceptionOrNullimpl != null) {
                }
                return Unit.INSTANCE;
            }
            if (netWorkManager == null || (serverApi = netWorkManager.getServerApi()) == null) {
                flowCardStatusRes = null;
                coroutineScope2 = coroutineScope;
                str = this.this$0.TAG;
                Pdlog.m3273d(str, "activateFlowCard statusFlowCard = " + flowCardStatusRes);
                if (flowCardStatusRes == null && (data2 = flowCardStatusRes.getData()) != null && 1 == data2.getStatus()) {
                    netWorkManager2 = this.this$0.mNetWorkManager;
                    if (netWorkManager2 == null || (serverApi2 = netWorkManager2.getServerApi()) == null) {
                        flowCardRes = null;
                        coroutineScope3 = coroutineScope;
                        str4 = this.this$0.TAG;
                        Pdlog.m3273d(str4, "activateFlowCard activateFlowCard = " + flowCardRes);
                        m4510constructorimpl = Result.m4510constructorimpl(flowCardRes);
                        if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                        }
                        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                        if (m4513exceptionOrNullimpl != null) {
                        }
                        return Unit.INSTANCE;
                    }
                    String str6 = (String) this.$simSerialNumber.element;
                    if (str6 == null) {
                        Intrinsics.throwNpe();
                    }
                    FlowCardReq flowCardReq = new FlowCardReq(str6);
                    this.L$0 = coroutineScope;
                    this.L$1 = coroutineScope2;
                    this.L$2 = flowCardRes;
                    this.L$3 = flowCardStatusRes;
                    this.label = 2;
                    activateFlowCard = serverApi2.activateFlowCard(flowCardReq, this);
                    if (activateFlowCard == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    coroutineScope3 = coroutineScope;
                    flowCardRes = (FlowCardRes) activateFlowCard;
                    str4 = this.this$0.TAG;
                    Pdlog.m3273d(str4, "activateFlowCard activateFlowCard = " + flowCardRes);
                    m4510constructorimpl = Result.m4510constructorimpl(flowCardRes);
                    if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                    }
                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                    if (m4513exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                if (flowCardStatusRes != null || (data = flowCardStatusRes.getData()) == null || 2 != data.getStatus()) {
                    str2 = this.this$0.TAG;
                    Pdlog.m3273d(str2, "activateFlowCard activate fail");
                } else {
                    str3 = this.this$0.TAG;
                    Pdlog.m3273d(str3, "activateFlowCard haved activate");
                    flowCardRes = new FlowCardRes(0, "已经激活");
                }
                coroutineScope3 = coroutineScope;
                m4510constructorimpl = Result.m4510constructorimpl(flowCardRes);
                if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                }
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                if (m4513exceptionOrNullimpl != null) {
                }
                return Unit.INSTANCE;
            }
            String str7 = (String) this.$simSerialNumber.element;
            if (str7 == null) {
                Intrinsics.throwNpe();
            }
            FlowCardReq flowCardReq2 = new FlowCardReq(str7);
            this.L$0 = coroutineScope;
            this.L$1 = coroutineScope;
            this.L$2 = flowCardRes;
            this.label = 1;
            statusFlowCard = serverApi.getStatusFlowCard(flowCardReq2, this);
            if (statusFlowCard == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope2 = coroutineScope;
        } else {
            if (i != 1) {
                if (i != 2) {
                    if (i != 3) {
                        if (i == 4) {
                            Object obj2 = this.L$1;
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    m4510constructorimpl = this.L$1;
                    CoroutineScope coroutineScope4 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope3 = coroutineScope4;
                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                    if (m4513exceptionOrNullimpl != null) {
                        str5 = this.this$0.TAG;
                        StringBuilder sb = new StringBuilder();
                        sb.append("activateFlowCard onFailure = ");
                        m4513exceptionOrNullimpl.printStackTrace();
                        sb.append(Unit.INSTANCE);
                        Pdlog.m3273d(str5, sb.toString());
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        C4760x9d78e2b9 c4760x9d78e2b9 = new C4760x9d78e2b9(null, this);
                        this.L$0 = coroutineScope3;
                        this.L$1 = m4510constructorimpl;
                        this.L$2 = m4513exceptionOrNullimpl;
                        this.label = 4;
                        if (BuildersKt.withContext(main, c4760x9d78e2b9, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    return Unit.INSTANCE;
                }
                coroutineScope3 = (CoroutineScope) this.L$0;
                try {
                    ResultKt.throwOnFailure(obj);
                    activateFlowCard = obj;
                    flowCardRes = (FlowCardRes) activateFlowCard;
                    str4 = this.this$0.TAG;
                    Pdlog.m3273d(str4, "activateFlowCard activateFlowCard = " + flowCardRes);
                    m4510constructorimpl = Result.m4510constructorimpl(flowCardRes);
                } catch (Throwable th2) {
                    th = th2;
                    coroutineScope = coroutineScope3;
                    Result.Companion companion22 = Result.INSTANCE;
                    m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                    coroutineScope3 = coroutineScope;
                    if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                    }
                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                    if (m4513exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                    FlowCardRes flowCardRes2 = (FlowCardRes) m4510constructorimpl;
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    C4761xdf060c60 c4761xdf060c60 = new C4761xdf060c60(flowCardRes2, null, this);
                    this.L$0 = coroutineScope3;
                    this.L$1 = m4510constructorimpl;
                    this.L$2 = flowCardRes2;
                    this.label = 3;
                    if (BuildersKt.withContext(main2, c4761xdf060c60, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                if (m4513exceptionOrNullimpl != null) {
                }
                return Unit.INSTANCE;
            }
            flowCardRes = (FlowCardRes) this.L$2;
            CoroutineScope coroutineScope5 = (CoroutineScope) this.L$1;
            CoroutineScope coroutineScope6 = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                coroutineScope2 = coroutineScope5;
                coroutineScope = coroutineScope6;
                statusFlowCard = obj;
            } catch (Throwable th3) {
                th = th3;
                coroutineScope = coroutineScope6;
                Result.Companion companion222 = Result.INSTANCE;
                m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                coroutineScope3 = coroutineScope;
                if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                }
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                if (m4513exceptionOrNullimpl != null) {
                }
                return Unit.INSTANCE;
            }
        }
        flowCardStatusRes = (FlowCardStatusRes) statusFlowCard;
        str = this.this$0.TAG;
        Pdlog.m3273d(str, "activateFlowCard statusFlowCard = " + flowCardStatusRes);
        if (flowCardStatusRes == null) {
        }
        if (flowCardStatusRes != null) {
        }
        str2 = this.this$0.TAG;
        Pdlog.m3273d(str2, "activateFlowCard activate fail");
        coroutineScope3 = coroutineScope;
        m4510constructorimpl = Result.m4510constructorimpl(flowCardRes);
        if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
        }
        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
        if (m4513exceptionOrNullimpl != null) {
        }
        return Unit.INSTANCE;
    }
}
