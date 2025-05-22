package com.pudutech.location;

import com.pudutech.base.Pdlog;
import com.pudutech.location.net.FlowCardReportReq;
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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AMapLocationManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.location.AMapLocationManager$reportData$1", m3970f = "AMapLocationManager.kt", m3971i = {0, 0}, m3972l = {104}, m3973m = "invokeSuspend", m3974n = {"$this$launch", "$this$runCatching"}, m3975s = {"L$0", "L$1"})
/* loaded from: classes5.dex */
public final class AMapLocationManager$reportData$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.ObjectRef $flowCardReportReq;
    Object L$0;
    Object L$1;
    int label;

    /* renamed from: p$ */
    private CoroutineScope f5480p$;
    final /* synthetic */ AMapLocationManager this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AMapLocationManager$reportData$1(AMapLocationManager aMapLocationManager, Ref.ObjectRef objectRef, Continuation continuation) {
        super(2, continuation);
        this.this$0 = aMapLocationManager;
        this.$flowCardReportReq = objectRef;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        AMapLocationManager$reportData$1 aMapLocationManager$reportData$1 = new AMapLocationManager$reportData$1(this.this$0, this.$flowCardReportReq, completion);
        aMapLocationManager$reportData$1.f5480p$ = (CoroutineScope) obj;
        return aMapLocationManager$reportData$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AMapLocationManager$reportData$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00b0  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final Object invokeSuspend(Object obj) {
        Object m4510constructorimpl;
        Throwable m4513exceptionOrNullimpl;
        String str;
        String str2;
        String str3;
        NetWorkManager netWorkManager;
        FlowCardRes flowCardRes;
        NetWorkManager.ServerApi serverApi;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        try {
        } catch (Throwable th) {
            Result.Companion companion = Result.INSTANCE;
            m4510constructorimpl = Result.m4510constructorimpl(ResultKt.createFailure(th));
        }
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = this.f5480p$;
            Result.Companion companion2 = Result.INSTANCE;
            netWorkManager = this.this$0.mNetWorkManager;
            if (netWorkManager == null || (serverApi = netWorkManager.getServerApi()) == null) {
                flowCardRes = null;
                m4510constructorimpl = Result.m4510constructorimpl(flowCardRes);
                if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
                    FlowCardRes flowCardRes2 = (FlowCardRes) m4510constructorimpl;
                    if (flowCardRes2 == null || flowCardRes2.getCode() != 0) {
                        str2 = this.this$0.TAG;
                        Pdlog.m3273d(str2, "reportData onSuccess fail res = " + flowCardRes2);
                    } else {
                        str3 = this.this$0.TAG;
                        Pdlog.m3273d(str3, "reportData onSuccess res = " + flowCardRes2);
                    }
                }
                m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
                if (m4513exceptionOrNullimpl != null) {
                    str = this.this$0.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("reportData onFailure ");
                    m4513exceptionOrNullimpl.printStackTrace();
                    sb.append(Unit.INSTANCE);
                    Pdlog.m3273d(str, sb.toString());
                }
                return Unit.INSTANCE;
            }
            FlowCardReportReq flowCardReportReq = (FlowCardReportReq) this.$flowCardReportReq.element;
            this.L$0 = coroutineScope;
            this.L$1 = coroutineScope;
            this.label = 1;
            obj = serverApi.reportFlowCard(flowCardReportReq, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        flowCardRes = (FlowCardRes) obj;
        m4510constructorimpl = Result.m4510constructorimpl(flowCardRes);
        if (Result.m4517isSuccessimpl(m4510constructorimpl)) {
        }
        m4513exceptionOrNullimpl = Result.m4513exceptionOrNullimpl(m4510constructorimpl);
        if (m4513exceptionOrNullimpl != null) {
        }
        return Unit.INSTANCE;
    }
}
