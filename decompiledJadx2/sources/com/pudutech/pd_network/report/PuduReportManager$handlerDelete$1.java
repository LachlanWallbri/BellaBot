package com.pudutech.pd_network.report;

import com.pudutech.pd_network.log.NetWorkLog;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: PuduReportManager.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@¢\u0006\u0004\b\u0003\u0010\u0004"}, m3961d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m3962k = 3, m3963mv = {1, 1, 16})
@DebugMetadata(m3969c = "com.pudutech.pd_network.report.PuduReportManager$handlerDelete$1", m3970f = "PuduReportManager.kt", m3971i = {}, m3972l = {}, m3973m = "invokeSuspend", m3974n = {}, m3975s = {})
/* loaded from: classes6.dex */
public final class PuduReportManager$handlerDelete$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;

    /* renamed from: p$ */
    private CoroutineScope f6845p$;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PuduReportManager$handlerDelete$1(Continuation continuation) {
        super(2, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkParameterIsNotNull(completion, "completion");
        PuduReportManager$handlerDelete$1 puduReportManager$handlerDelete$1 = new PuduReportManager$handlerDelete$1(completion);
        puduReportManager$handlerDelete$1.f6845p$ = (CoroutineScope) obj;
        return puduReportManager$handlerDelete$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PuduReportManager$handlerDelete$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        long j;
        String TAG;
        AtomicBoolean atomicBoolean;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        ResultKt.throwOnFailure(obj);
        CoroutineScope coroutineScope = this.f6845p$;
        long count = PuduReportManager.access$getDb$p(PuduReportManager.INSTANCE).getCount(0);
        long count2 = PuduReportManager.access$getDb$p(PuduReportManager.INSTANCE).getCount(1);
        if (count2 > 0) {
            PuduReportManager.access$getDb$p(PuduReportManager.INSTANCE).deleteByUpload(1);
        }
        PuduReportManager puduReportManager = PuduReportManager.INSTANCE;
        j = PuduReportManager.MAX_NUMS;
        double d = count - (j * 0.9d);
        if (d > 0) {
            PuduReportManager.access$getDb$p(PuduReportManager.INSTANCE).delete((long) d);
        }
        NetWorkLog netWorkLog = NetWorkLog.INSTANCE;
        PuduReportManager puduReportManager2 = PuduReportManager.INSTANCE;
        TAG = PuduReportManager.TAG;
        Intrinsics.checkExpressionValueIsNotNull(TAG, "TAG");
        netWorkLog.mo3278d(TAG, "handlerDelete: delete uploadCount = " + count2 + "  delete unUploadCount = " + d);
        PuduReportManager puduReportManager3 = PuduReportManager.INSTANCE;
        atomicBoolean = PuduReportManager.isHandlerRemove;
        atomicBoolean.set(false);
        return Unit.INSTANCE;
    }
}
