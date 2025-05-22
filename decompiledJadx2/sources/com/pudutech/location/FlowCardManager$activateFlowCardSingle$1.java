package com.pudutech.location;

import com.pudutech.base.Pdlog;
import com.pudutech.location.net.FlowCardReq;
import com.pudutech.location.net.FlowCardRes;
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
@DebugMetadata(m3969c = "com.pudutech.location.FlowCardManager$activateFlowCardSingle$1", m3970f = "FlowCardManager.kt", m3971i = {0, 0, 1, 1, 2, 2}, m3972l = {88, 90, 101}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$runCatching", "$this$launch", "it", "$this$launch", "it"}, m3975s = {"L$0", "L$1", "L$0", "L$2", "L$0", "L$2"})
/* loaded from: classes5.dex */
public final class FlowCardManager$activateFlowCardSingle$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $callback;
    final /* synthetic */ Ref.ObjectRef $simSerialNumber;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5488p$;
    final /* synthetic */ FlowCardManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowCardManager$activateFlowCardSingle$1(FlowCardManager flowCardManager, Ref.ObjectRef objectRef, Function1 function1, Continuation continuation) {
        super(2, continuation);
        this.this$0 = flowCardManager;
        this.$simSerialNumber = objectRef;
        this.$callback = function1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        FlowCardManager$activateFlowCardSingle$1 flowCardManager$activateFlowCardSingle$1 = new FlowCardManager$activateFlowCardSingle$1(this.this$0, this.$simSerialNumber, this.$callback, completion);
        flowCardManager$activateFlowCardSingle$1.f5488p$ = (CoroutineScope) obj;
        return flowCardManager$activateFlowCardSingle$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FlowCardManager$activateFlowCardSingle$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:16:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0094  */
    /* JADX WARN: Type inference failed for: r1v11, types: [kotlinx.coroutines.CoroutineScope] */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v5, types: [java.lang.Object, kotlinx.coroutines.CoroutineScope] */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object m4510constructorimpl;
        ?? r1;
        Object obj2;
        CoroutineScope coroutineScope;
        Throwable m4513exceptionOrNullimpl;
        String str;
        NetWorkManager netWorkManager;
        FlowCardRes flowCardRes;
        NetWorkManager.ServerApi serverApi;
        int i;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i2 = this.label;
        try {
        } catch (Throwable th) {
            Result.Companion companion = Result.INSTANCE;
            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
            r1 = i2;
        }
        if (i2 == 0) {
            ResultKt.throwOnFailure(obj);
            ?? r12 = this.f5488p$;
            Result.Companion companion2 = Result.INSTANCE;
            netWorkManager = this.this$0.mNetWorkManager;
            if (netWorkManager == null || (serverApi = netWorkManager.getServerApi()) == null) {
                flowCardRes = null;
                i2 = r12;
                m4510constructorimpl = Result.m4510constructorimpl(flowCardRes);
                r1 = i2;
                CoroutineScope coroutineScope2 = r1;
                obj2 = m4510constructorimpl;
                coroutineScope = coroutineScope2;
                if (Result.m4517isSuccessimpl(obj2)) {
                    FlowCardRes flowCardRes2 = (FlowCardRes) obj2;
                    MainCoroutineDispatcher main = Dispatchers.getMain();
                    C4763xc894cc48 c4763xc894cc48 = new C4763xc894cc48(flowCardRes2, null, this);
                    this.L$0 = coroutineScope;
                    this.L$1 = obj2;
                    this.L$2 = flowCardRes2;
                    this.label = 2;
                    if (BuildersKt.withContext(main, c4763xc894cc48, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
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
            this.L$0 = r12;
            this.L$1 = r12;
            this.label = 1;
            obj = serverApi.activateFlowCard(flowCardReq, this);
            i = r12;
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i2 != 1) {
                if (i2 != 2) {
                    if (i2 == 3) {
                        Object obj3 = this.L$1;
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                obj2 = this.L$1;
                CoroutineScope coroutineScope3 = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
                coroutineScope = coroutineScope3;
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
                if (m4513exceptionOrNullimpl != null) {
                    str = this.this$0.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("activateFlowCardSingle success onFailure = ");
                    m4513exceptionOrNullimpl.printStackTrace();
                    sb.append(Unit.INSTANCE);
                    Pdlog.m3273d(str, sb.toString());
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    C4762x8707a2a1 c4762x8707a2a1 = new C4762x8707a2a1(null, this);
                    this.L$0 = coroutineScope;
                    this.L$1 = obj2;
                    this.L$2 = m4513exceptionOrNullimpl;
                    this.label = 3;
                    if (BuildersKt.withContext(main2, c4762x8707a2a1, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                return Unit.INSTANCE;
            }
            ?? r13 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            i = r13;
        }
        flowCardRes = (FlowCardRes) obj;
        i2 = i;
        m4510constructorimpl = Result.m4510constructorimpl(flowCardRes);
        r1 = i2;
        CoroutineScope coroutineScope22 = r1;
        obj2 = m4510constructorimpl;
        coroutineScope = coroutineScope22;
        if (Result.m4517isSuccessimpl(obj2)) {
        }
        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(obj2);
        if (m4513exceptionOrNullimpl != null) {
        }
        return Unit.INSTANCE;
    }
}
