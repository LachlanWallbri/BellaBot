package com.pudutech.location;

import com.pudutech.base.Pdlog;
import com.pudutech.location.net.FlowCardReq;
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
import kotlinx.coroutines.DelayKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: FlowCardManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.location.FlowCardManager$getStatusFlowCardSingle$1", m3970f = "FlowCardManager.kt", m3971i = {0, 0, 1, 1, 2, 2, 3, 3}, m3972l = {122, 125, 127, 139}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$runCatching", "$this$launch", "$this$runCatching", "$this$launch", "it", "$this$launch", "it"}, m3975s = {"L$0", "L$1", "L$0", "L$1", "L$0", "L$2", "L$0", "L$2"})
/* loaded from: classes5.dex */
public final class FlowCardManager$getStatusFlowCardSingle$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $callback;
    final /* synthetic */ long $delay;
    final /* synthetic */ Ref.ObjectRef $simSerialNumber;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5489p$;
    final /* synthetic */ FlowCardManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowCardManager$getStatusFlowCardSingle$1(FlowCardManager flowCardManager, long j, Ref.ObjectRef objectRef, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = flowCardManager;
        this.$delay = j;
        this.$simSerialNumber = objectRef;
        this.$callback = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FlowCardManager$getStatusFlowCardSingle$1 flowCardManager$getStatusFlowCardSingle$1 = new FlowCardManager$getStatusFlowCardSingle$1(this.this$0, this.$delay, this.$simSerialNumber, this.$callback, completion);
        flowCardManager$getStatusFlowCardSingle$1.f5489p$ = (CoroutineScope) obj;
        return flowCardManager$getStatusFlowCardSingle$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FlowCardManager$getStatusFlowCardSingle$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x00c0  */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v23 */
    /* JADX WARN: Type inference failed for: r1v24 */
    /* JADX WARN: Type inference failed for: r1v8 */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object m4510constructorimpl;
        CoroutineScope coroutineScope;
        Object obj2;
        CoroutineScope coroutineScope2;
        Throwable m4513exceptionOrNullimpl;
        String str;
        CoroutineScope coroutineScope3;
        CoroutineScope coroutineScope4;
        NetWorkManager netWorkManager;
        FlowCardStatusRes flowCardStatusRes;
        NetWorkManager.ServerApi serverApi;
        CoroutineScope coroutineScope5;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        ?? r1 = this.label;
        try {
        } catch (Throwable th) {
            th = th;
        }
        if (r1 == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope6 = this.f5489p$;
            Result.Companion companion = Result.INSTANCE;
            if (this.$delay != 0) {
                long j = this.$delay;
                this.L$0 = coroutineScope6;
                this.L$1 = coroutineScope6;
                this.label = 1;
                if (DelayKt.delay(j, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            coroutineScope3 = coroutineScope6;
            coroutineScope4 = coroutineScope6;
        } else {
            if (r1 != 1) {
                if (r1 == 2) {
                    CoroutineScope coroutineScope7 = (CoroutineScope) this.L$0;
                    ResultKt.throwOnFailure(obj);
                    coroutineScope5 = coroutineScope7;
                    flowCardStatusRes = (FlowCardStatusRes) obj;
                    r1 = coroutineScope5;
                    m4510constructorimpl = Result.m4510constructorimpl(flowCardStatusRes);
                    coroutineScope = r1;
                    CoroutineScope coroutineScope8 = coroutineScope;
                    obj2 = m4510constructorimpl;
                    coroutineScope2 = coroutineScope8;
                    if (Result.m4517isSuccessimpl(obj2)) {
                        FlowCardStatusRes flowCardStatusRes2 = (FlowCardStatusRes) obj2;
                        MainCoroutineDispatcher main = Dispatchers.getMain();
                        C4765x81513bb3 c4765x81513bb3 = new C4765x81513bb3(flowCardStatusRes2, null, this);
                        this.L$0 = coroutineScope2;
                        this.L$1 = obj2;
                        this.L$2 = flowCardStatusRes2;
                        this.label = 3;
                        if (BuildersKt.withContext(main, c4765x81513bb3, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                    m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                    if (m4513exceptionOrNullimpl != null) {
                    }
                    return Unit.INSTANCE;
                }
                if (r1 != 3) {
                    if (r1 == 4) {
                        Object obj3 = this.L$1;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                obj2 = this.L$1;
                CoroutineScope coroutineScope9 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope2 = coroutineScope9;
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                if (m4513exceptionOrNullimpl != null) {
                    str = this.this$0.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("getStatusFlowCardSingle success onFailure = ");
                    m4513exceptionOrNullimpl.printStackTrace();
                    sb.append(Unit.INSTANCE);
                    Pdlog.m3273d(str, sb.toString());
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    C4764x3fc4120c c4764x3fc4120c = new C4764x3fc4120c(null, this);
                    this.L$0 = coroutineScope2;
                    this.L$1 = obj2;
                    this.L$2 = m4513exceptionOrNullimpl;
                    this.label = 4;
                    if (BuildersKt.withContext(main2, c4764x3fc4120c, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
            CoroutineScope coroutineScope10 = (CoroutineScope) this.L$1;
            CoroutineScope coroutineScope11 = (CoroutineScope) this.L$0;
            try {
                ResultKt.throwOnFailure(obj);
                coroutineScope3 = coroutineScope10;
                coroutineScope4 = coroutineScope11;
            } catch (Throwable th2) {
                th = th2;
                r1 = coroutineScope11;
                Result.Companion companion2 = Result.INSTANCE;
                m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
                coroutineScope = r1;
                CoroutineScope coroutineScope82 = coroutineScope;
                obj2 = m4510constructorimpl;
                coroutineScope2 = coroutineScope82;
                if (Result.m4517isSuccessimpl(obj2)) {
                }
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                if (m4513exceptionOrNullimpl != null) {
                }
                return Unit.INSTANCE;
            }
        }
        netWorkManager = this.this$0.mNetWorkManager;
        if (netWorkManager == null || (serverApi = netWorkManager.getServerApi()) == null) {
            flowCardStatusRes = null;
            r1 = coroutineScope4;
            m4510constructorimpl = Result.m4510constructorimpl(flowCardStatusRes);
            coroutineScope = r1;
            CoroutineScope coroutineScope822 = coroutineScope;
            obj2 = m4510constructorimpl;
            coroutineScope2 = coroutineScope822;
            if (Result.m4517isSuccessimpl(obj2)) {
            }
            m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
            if (m4513exceptionOrNullimpl != null) {
            }
            return Unit.INSTANCE;
        }
        String str2 = (String) this.$simSerialNumber.element;
        if (str2 == null) {
            Intrinsics.throwNpe();
        }
        FlowCardReq flowCardReq = new FlowCardReq(str2);
        this.L$0 = coroutineScope4;
        this.L$1 = coroutineScope3;
        this.label = 2;
        obj = serverApi.getStatusFlowCard(flowCardReq, this);
        coroutineScope5 = coroutineScope4;
        if (obj == coroutine_suspended) {
            return coroutine_suspended;
        }
        flowCardStatusRes = (FlowCardStatusRes) obj;
        r1 = coroutineScope5;
        m4510constructorimpl = Result.m4510constructorimpl(flowCardStatusRes);
        coroutineScope = r1;
        CoroutineScope coroutineScope8222 = coroutineScope;
        obj2 = m4510constructorimpl;
        coroutineScope2 = coroutineScope8222;
        if (Result.m4517isSuccessimpl(obj2)) {
        }
        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
        if (m4513exceptionOrNullimpl != null) {
        }
        return Unit.INSTANCE;
    }
}
